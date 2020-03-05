package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Question;
import domain.User;

import exceptions.InsufficientCash;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import javax.swing.table.DefaultTableModel;


public class UserFindQuestionsGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private User user;
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
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

	public UserFindQuestionsGUI(User user)
	{
		try
		{
			this.user = user;
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception
	{
		this.setModal(true);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 522));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 248, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(277, 433, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));


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

		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 274, 406, 116));

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

		JLabel lblPlaceBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PlaceBet")); //$NON-NLS-1$ //$NON-NLS-2$
		lblPlaceBet.setBounds(497, 248, 70, 15);
		getContentPane().add(lblPlaceBet);

		betTextField = new JTextField();
		betTextField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$

		betTextField.setBounds(497, 273, 117, 19);
		getContentPane().add(betTextField);
		betTextField.setColumns(10);

		JLabel errorlabel = new JLabel();
		errorlabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorlabel.setBackground(Color.RED);
		errorlabel.setForeground(Color.RED);
		errorlabel.setBounds(161, 396, 406, 25);
		getContentPane().add(errorlabel);

		JButton btnBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet")); //$NON-NLS-1$ //$NON-NLS-2$
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				try {
					if (betTextField.getText().equals("")) {
						errorlabel.setText(" Enter a valid amount to bet");
					}else {
						int betAmount = Integer.parseInt(betTextField.getText());
						if (betAmount > user.getCash()) {
							errorlabel.setText("Not enough money to bet with that amount");
						}else {
							int i=tableEvents.getSelectedRow();
							
							domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
							Vector<Question> queries=ev.getQuestions();
							int j = tableQueries.getSelectedRow();
							int qtNum = (Integer)tableQueries.getValueAt(j, 0);
							for (Question question : queries) {
								if (question.getQuestionNumber() == qtNum) {
									if (question.getBetMinimum() > betAmount) {
										errorlabel.setText(" Enter a valid amount to bet (Minimum not reached)");
									}
									user.placeBet(question, betAmount);
									break;
								}
							}
							
						}
					}
				} catch (Exception e) {
					errorlabel.setText("Please select a event \nand question before betting");
				}
				*/
				try {
					BLFacade facade = MainGUI.getBusinessLogic();
					
					int i = tableQueries.getSelectedRow();
					Question q = (Question)tableModelQueries.getValueAt(i,3);
					Float betAmount = Float.parseFloat(betTextField.getText());
					if(q.getBetMinimum() > betAmount) {
						errorlabel.setText(" Enter a valid amount to bet (Minimum not reached)");
					}
					else{
						facade.placeBet(q, user, betAmount);
						errorlabel.setText("Bet placed sucessfully");
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
		});
		btnBet.setBounds(497, 296, 117, 25);
		getContentPane().add(btnBet);



	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
