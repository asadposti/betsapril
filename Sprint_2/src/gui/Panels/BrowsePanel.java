package gui.Panels;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import com.toedter.calendar.JCalendar;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import domain.Question;
import exceptions.InsufficientCash;
import exceptions.NoAnswers;
import exceptions.QuestionNotFound;
import gui.LoginGUI;
import gui.MainGUI;
import gui.RegisterGUI;
import gui.components.NonEditableTableModel;

import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class BrowsePanel extends JPanel {

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private final JLabel lblPlaceBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PlaceBet")); 
	private JLabel errorlabel = new JLabel();

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

	private JButton btnBet;
	
	private JComboBox<String> answerComboBox = new JComboBox<String>();
	/**
	 * Create the panel.
	 */
	public BrowsePanel() {
		setBackground(UIManager.getColor("Button.highlight"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 225, 30, 157, 23, 171, 40, 0};
		gridBagLayout.rowHeights = new int[]{40, 15, 25, 150, 15, 14, 22, 25, 0, 0, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		GridBagConstraints gbc_jLabelEventDate = new GridBagConstraints();
		gbc_jLabelEventDate.fill = GridBagConstraints.BOTH;
		gbc_jLabelEventDate.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelEventDate.gridx = 1;
		gbc_jLabelEventDate.gridy = 2;
		add(jLabelEventDate, gbc_jLabelEventDate);
		GridBagConstraints gbc_jLabelEvents = new GridBagConstraints();
		gbc_jLabelEvents.fill = GridBagConstraints.HORIZONTAL;
		gbc_jLabelEvents.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelEvents.gridwidth = 3;
		gbc_jLabelEvents.gridx = 3;
		gbc_jLabelEvents.gridy = 2;
		add(jLabelEvents, gbc_jLabelEvents);
		tableEvents.setBackground(Color.WHITE);

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
		tableModelQueries = new NonEditableTableModel(null, columnNamesQueries);
		
		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableEvents.getTableHeader().setReorderingAllowed(false);
		tableEvents.getTableHeader().setResizingAllowed(false);
		jCalendar1.getDayChooser().getDayPanel().setBackground(UIManager.getColor("Button.highlight"));
		jCalendar1.getMonthChooser().getSpinner().setBackground(Color.WHITE);
		jCalendar1.getDayChooser().getDayPanel().setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));


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
					errorlabel.setText("");
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
				CreateQuestionPanel.paintDaysWithEvents(jCalendar1);
			} 
		});

		GridBagConstraints gbc_jCalendar1 = new GridBagConstraints();
		gbc_jCalendar1.fill = GridBagConstraints.BOTH;
		gbc_jCalendar1.insets = new Insets(0, 0, 5, 5);
		gbc_jCalendar1.gridx = 1;
		gbc_jCalendar1.gridy = 3;
		add(jCalendar1, gbc_jCalendar1);

		GridBagConstraints gbc_scrollPaneEvents = new GridBagConstraints();
		gbc_scrollPaneEvents.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneEvents.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneEvents.gridwidth = 3;
		gbc_scrollPaneEvents.gridx = 3;
		gbc_scrollPaneEvents.gridy = 3;
		add(scrollPaneEvents, gbc_scrollPaneEvents);
		GridBagConstraints gbc_jLabelQueries = new GridBagConstraints();
		gbc_jLabelQueries.anchor = GridBagConstraints.NORTH;
		gbc_jLabelQueries.fill = GridBagConstraints.HORIZONTAL;
		gbc_jLabelQueries.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelQueries.gridwidth = 3;
		gbc_jLabelQueries.gridx = 1;
		gbc_jLabelQueries.gridy = 5;
		add(jLabelQueries, gbc_jLabelQueries);
		tableQueries.setBackground(Color.WHITE);

		scrollPaneQueries.setViewportView(tableQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQueries.getTableHeader().setReorderingAllowed(false);
		tableQueries.getTableHeader().setResizingAllowed(false);
		GridBagConstraints gbc_scrollPaneQueries = new GridBagConstraints();
		gbc_scrollPaneQueries.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneQueries.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneQueries.gridheight = 4;
		gbc_scrollPaneQueries.gridwidth = 3;
		gbc_scrollPaneQueries.gridx = 1;
		gbc_scrollPaneQueries.gridy = 6;
		add(scrollPaneQueries, gbc_scrollPaneQueries);
		tableQueries.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stuf
				answerComboBox.removeAllItems();
				errorlabel.setText("");
				int i = tableQueries.getSelectedRow();
				if (i != -1) {
					int questionId =  (int) tableQueries.getValueAt(i, 0);
					BLFacade facade = MainGUI.getBusinessLogic();
					try {
						ArrayList<String> answers = facade.getQuestionAnswers(questionId);
						ArrayList<Float> odds = facade.getOdds(questionId);
						for (int j = 0; j < answers.size(); j++) {
							String answer = answers.get(j) + "; " + odds.get(j).toString();
							answerComboBox.addItem(answer);
						}
					} catch (QuestionNotFound e2) {
						// TODO: handle exception
						errorlabel.setText(e2.getMessage());
						
					} catch (NoAnswers e2) {
						// TODO: handle exception
						errorlabel.setText(e2.getMessage());
					}

					
				}
				}
		});
		GridBagConstraints gbc_lblPlaceBet = new GridBagConstraints();
		gbc_lblPlaceBet.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblPlaceBet.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlaceBet.gridx = 5;
		gbc_lblPlaceBet.gridy = 6;
		add(lblPlaceBet, gbc_lblPlaceBet);

		betTextField = new JTextField();
		betTextField.setText("");
		GridBagConstraints gbc_betTextField = new GridBagConstraints();
		gbc_betTextField.fill = GridBagConstraints.BOTH;
		gbc_betTextField.insets = new Insets(0, 0, 5, 5);
		gbc_betTextField.gridx = 5;
		gbc_betTextField.gridy = 7;
		add(betTextField, gbc_betTextField);
		betTextField.setColumns(10);

		GridBagConstraints gbc_btnBet = new GridBagConstraints();
		gbc_btnBet.anchor = GridBagConstraints.NORTH;
		gbc_btnBet.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBet.insets = new Insets(0, 0, 5, 5);
		gbc_btnBet.gridx = 5;
		gbc_btnBet.gridy = 8;
		add(getBtnBet(), gbc_btnBet);
		

		errorlabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorlabel.setBackground(Color.RED);
		errorlabel.setForeground(Color.RED);
		GridBagConstraints gbc_errorlabel = new GridBagConstraints();
		gbc_errorlabel.insets = new Insets(0, 0, 0, 5);
		gbc_errorlabel.fill = GridBagConstraints.BOTH;
		gbc_errorlabel.gridwidth = 5;
		gbc_errorlabel.gridx = 1;
		gbc_errorlabel.gridy = 10;
		add(errorlabel, gbc_errorlabel);
		GridBagConstraints gbc_answerComboBox = new GridBagConstraints();
		gbc_answerComboBox.insets = new Insets(5, 0, 125, 45);
		gbc_answerComboBox.fill = GridBagConstraints.BOTH;
		gbc_answerComboBox.gridwidth = 2;
		gbc_answerComboBox.gridx = 5;
		gbc_answerComboBox.gridy = 9;
		add(answerComboBox, gbc_answerComboBox);
	}


	private JButton getBtnBet() {
		if(btnBet == null) {
			btnBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
			btnBet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					BLFacade facade = MainGUI.getBusinessLogic();
					if(facade.isLoggedIn()) {
						String[] options = {"Log in","Register","Cancel"};
						int selectedoption = JOptionPane.showOptionDialog(null, "This action requires the user to be logged in", "Login required", JOptionPane.DEFAULT_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
						if(selectedoption == 0) {
							JDialog d = new LoginGUI();
							d.setVisible(true);
						}
						if(selectedoption == 1) {
							JDialog d = new RegisterGUI(false);
							d.setVisible(true);
						}
					}
					else {
						try {
							int i = tableQueries.getSelectedRow();
							Question q = (Question)tableModelQueries.getValueAt(i,3);
							Float betAmount = Float.parseFloat(betTextField.getText());
							if(q.getBetMinimum() > betAmount) {
								errorlabel.setText(" Enter a valid amount to bet (Minimum not reached)");
							}
							else{
								facade.placeBet(q, facade.getProfile().getID(), betAmount,0);  //CHANGE THE 0 (ANSWER)!!!!!!!!
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

	public void refreshCash() {
		//cashLabel.setText(Float.toString(UserLoginGUI.getLoggedUser().getCash()));
	}

}
