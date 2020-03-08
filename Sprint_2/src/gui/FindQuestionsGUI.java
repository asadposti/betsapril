package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;
import com.toedter.calendar.JCalendar;

import domain.Question;
import domain.User;
import exceptions.InsufficientCash;
import exceptions.invalidID;
import exceptions.invalidPW;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;


public class FindQuestionsGUI extends JDialog {
	private static final long serialVersionUID = 1L;


	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private final JLabel cashTitleLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Cash"));
	private final JLabel accountTitleLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Account"));
	private final JLabel lblPlaceBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PlaceBet")); 
	private JLabel usernameLabel = new JLabel();
	private JLabel cashLabel = new JLabel();
	private JLabel errorlabel = new JLabel();
	
	private JButton jButtonClose; 
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private NonEditableTableModel tableModelEvents;
	private NonEditableTableModel tableModelQueries;


	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query"),
			"MinBet"

	};
	private JTextField betTextField;
	private JTextField usernameField;
	private JPasswordField passwordField;

	private JButton addIncomeButton; 
	private JButton loginButton;
	private JButton btnBet;

	private JLabel credentialErrorLabel = new JLabel("");
	private JLayeredPane layeredPane = new JLayeredPane();
	private final JPanel panel_1 = new JPanel();
	private JPanel panel_2 = new JPanel();
	private JLabel profilePicLabel = new JLabel();

	public FindQuestionsGUI()
	{
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception
	{
		this.setModal(false);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 576));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(42, 115, 140, 25));
		jLabelQueries.setBounds(40, 307, 406, 14);
		jLabelEvents.setBounds(289, 119, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);
		
		this.getContentPane().add(getjButtonClose(), null);


		jCalendar1.setBounds(new Rectangle(40, 146, 225, 150));


		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			} 
		});

		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(289, 146, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 332, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(4); // another column added to allocate ev object

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q.getBetMinimum());
					row.add(q); // Question object added in order to obtain it with tableModelEvents.getValueAt(i,3)
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().getColumn(2).setPreferredWidth(25);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(3));
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new NonEditableTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableEvents.getTableHeader().setReorderingAllowed(false);
		tableEvents.getTableHeader().setResizingAllowed(false);

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new NonEditableTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQueries.getTableHeader().setReorderingAllowed(false);
		tableQueries.getTableHeader().setResizingAllowed(false);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);


		lblPlaceBet.setBounds(497, 339, 70, 15);
		getContentPane().add(lblPlaceBet);

		betTextField = new JTextField();
		betTextField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$

		betTextField.setBounds(497, 365, 117, 19);
		getContentPane().add(betTextField);
		betTextField.setColumns(10);

		
		errorlabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorlabel.setBackground(Color.RED);
		errorlabel.setForeground(Color.RED);
		errorlabel.setBounds(161, 460, 406, 25);
		getContentPane().add(errorlabel);


		addIncomeButton = new JButton(new ImageIcon(ImageIO.read(new File("images/addIncome.png")))); 
		addIncomeButton.setToolTipText(ResourceBundle.getBundle("Etiquetas").getString("AddCash")); //$NON-NLS-1$ //$NON-NLS-2$

		getContentPane().add(getBtnBet());

		layeredPane.setBounds(389, 22, 246, 75);
		getContentPane().add(layeredPane);
		panel_2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));

		panel_2.setBounds(0, 0, 246, 75);
		layeredPane.add(panel_2);
		panel_2.setLayout(null);

		usernameField = new HintTextField("Username");
		usernameField.setBounds(10, 11, 114, 20);
		panel_2.add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(10, 42, 114, 20);
		panel_2.add(passwordField);


		credentialErrorLabel.setForeground(Color.RED);
		credentialErrorLabel.setBounds(134, 48, 112, 14);
		panel_2.add(credentialErrorLabel);		
		panel_2.add(getLoginBtn());
		panel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(0, 0, 246, 75);
		layeredPane.add(panel_1);
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(null);

		
		usernameLabel.setBounds(137, 14, 60, 14);
		panel_1.add(usernameLabel);


		cashLabel.setBounds(126, 39, 71, 14);
		panel_1.add(cashLabel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 60, 54);
		panel_1.add(panel);

		User u = UserLoginGUI.getLoggedUser();
		if(u != null) {
			if(u.getProfilepic() == null) {
				profilePicLabel.setIcon(new ImageIcon(ImageIO.read(new File("images/profilepic/smiley"))));
			}
			else {
				profilePicLabel.setIcon(new ImageIcon(ImageIO.read(new File(u.getProfilepic()))));
			}
			usernameLabel.setText(u.getID());
			cashLabel.setText(Float.toString(u.getCash()));
		}
		panel.add(profilePicLabel);
		
		panel_1.setVisible(u != null);
		panel_2.setVisible(u == null);

		addIncomeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean validinput = false;
				String amount = JOptionPane.showInputDialog(null, "Enter amount to store");
				if(amount != null) { //cancel option returns null
					while(!validinput && amount!=null) {
						try {
							UserLoginGUI.getLoggedUser().addCash(Float.parseFloat(amount));
							refreshCash();
							validinput = true;
						}
						catch(NumberFormatException n) {
							amount = JOptionPane.showInputDialog(null, "Entered input was invalid, try again");
						}
					}
				}	
			}
		});
		addIncomeButton.setBackground(Color.WHITE);
		addIncomeButton.setBounds(201, 36, 35, 29);
		panel_1.add(addIncomeButton);


		accountTitleLabel.setBounds(80, 14, 60, 14);
		panel_1.add(accountTitleLabel);


		cashTitleLabel.setBounds(80, 39, 48, 14);
		panel_1.add(cashTitleLabel);
	}

	private JButton getLoginBtn() {
		if(loginButton == null) {
			loginButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LogIn"));
			loginButton.setBounds(147, 16, 77, 23);
			loginButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					BLFacade facade = MainGUI.getBusinessLogic();
					String username = usernameField.getText();
					String pass =  new String (passwordField.getPassword());
					try {
						User u = facade.checkCredentials(username, pass);
						JOptionPane.showMessageDialog(null, "Login successful");
				
						u.setLastlogin(new Date());
						UserLoginGUI.setLoggedUser(u); 
						panel_1.setVisible(true);
						panel_2.setVisible(false);
						usernameLabel.setText(u.getID());
						cashLabel.setText(Float.toString(u.getCash()));
						try {
							profilePicLabel.setIcon(new ImageIcon(ImageIO.read(new File(u.getProfilepic()))));
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					} catch (invalidID e) {
						credentialErrorLabel.setText("Invalid credentials");
					} catch (invalidPW e) {
						credentialErrorLabel.setText("Invalid credentials");
					}
				}
			});
		}
		return loginButton;
	}



	private JButton getBtnBet() {
		if(btnBet == null) {
			btnBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet")); //$NON-NLS-1$ //$NON-NLS-2$
			btnBet.setBounds(497, 395, 117, 25);
			btnBet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(UserLoginGUI.getLoggedUser() == null) {
						String[] options = {"Log in","Register","Cancel"};
						int selectedoption = JOptionPane.showOptionDialog(null, "This action requires the user to be logged in", "Login required", JOptionPane.DEFAULT_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
						if(selectedoption == 0) {
							JFrame f = new UserLoginGUI();
							f.setVisible(true);
						}
						if(selectedoption == 1) {
							JDialog d = new RegisterGUI(false);
							d.setVisible(true);
						}
					}
					else {
						try {
							BLFacade facade = MainGUI.getBusinessLogic();

							int i = tableQueries.getSelectedRow();
							Question q = (Question)tableModelQueries.getValueAt(i,3);
							Float betAmount = Float.parseFloat(betTextField.getText());
							if(q.getBetMinimum() > betAmount) {
								errorlabel.setText(" Enter a valid amount to bet (Minimum not reached)");
							}
							else{
								facade.placeBet(q, UserLoginGUI.getLoggedUser(), betAmount,0);  //CHANGE THE 0 (ANSWER)!!!!!!!!
								errorlabel.setText("Bet placed sucessfully");
								refreshCash();
							}
						}
						catch(ArrayIndexOutOfBoundsException e) {
							errorlabel.setText("Please select an event and question before betting");
						}
						catch(NumberFormatException e){
							errorlabel.setText("Enter a valid amount to bet");
						}
						catch(InsufficientCash i) {
							errorlabel.setText("Not enough money to bet with that amount");
						}
					}

				}
			});
		}
		return btnBet;
	}
	
	private JButton getjButtonClose() {
		if(jButtonClose == null) {
			jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
			jButtonClose.setBounds(new Rectangle(277, 496, 130, 30));

			jButtonClose.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					dispose();
					User u = UserLoginGUI.getLoggedUser();
					JFrame f;
					if(u == null) {
						f = new MainGUI();
					}
					else if(u.isAdmin()) {
						f = new AdminMainGUI();
					}
					else {
						f = new UserMainGUI();
					}
					f.setVisible(true);
				}
			});

		}
		return jButtonClose;
	}
	
	public void refreshCash() {
		cashLabel.setText(Float.toString(UserLoginGUI.getLoggedUser().getCash()));
	}
}