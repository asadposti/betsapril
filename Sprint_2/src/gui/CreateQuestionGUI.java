package gui;

import java.text.DateFormat;
import java.util.*;
import javax.swing.*;
import com.toedter.calendar.JCalendar;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionGUI extends JDialog {
	private static final long serialVersionUID = 1L;

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelQuery = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Query"));
	private JLabel jLabelMinBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MinimumBetPrice"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));

	private JTextField jTextFieldQuery = new JTextField();
	private JTextField jTextFieldPrice = new JTextField();
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarMio = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private final JTextField answerTextField = new JTextField();
	private JTextField textField;
	private int count = 0;
	private ArrayList<String> AnswerList = new ArrayList<String>();
	private ArrayList<Float> OddLsit = new ArrayList<Float>();
	

	public CreateQuestionGUI(Vector<domain.Event> v) {

		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {

		this.setModal(true);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 470));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));

		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(275, 47, 250, 20));
		jLabelListOfEvents.setBounds(new Rectangle(290, 18, 277, 20));
		jLabelQuery.setBounds(new Rectangle(25, 211, 75, 20));
		jTextFieldQuery.setBounds(new Rectangle(100, 211, 429, 20));
		jLabelMinBet.setBounds(new Rectangle(25, 359, 75, 20));
		jTextFieldPrice.setBounds(new Rectangle(100, 359, 60, 20));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonCreate.setBounds(new Rectangle(100, 390, 130, 30));

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(263, 390, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(196, 322, 371, 30));
		jLabelError.setForeground(Color.red);

		
		answerTextField.setBounds(100, 254, 299, 20);
		answerTextField.setColumns(10);
		
		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldQuery, null);
		this.getContentPane().add(jLabelQuery, null);
		this.getContentPane().add(jTextFieldPrice, null);

		this.getContentPane().add(jLabelMinBet, null);
		this.getContentPane().add(jLabelListOfEvents, null);
		this.getContentPane().add(jComboBoxEvents, null);

		this.getContentPane().add(jCalendar, null);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);
		
		JLabel answerLabel = new JLabel("Answer"); //$NON-NLS-1$ //$NON-NLS-2$
		answerLabel.setBounds(25, 254, 75, 20);
		getContentPane().add(answerLabel);
		
	

		
		JLabel oddLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.oddLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$
		oddLabel.setBounds(25, 304, 75, 20);
		getContentPane().add(oddLabel);
		
		getContentPane().add(answerTextField);
		
		textField = new JTextField();
		textField.setBounds(100, 304, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton answerButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		answerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (answerLabel.getText().equals("") || textField.getText().equals("") ) {
					jLabelError.setText("Please enter an answer and an odd in order to submit it");
				}else if(Float.parseFloat(textField.getText()) < 1) {
					jLabelError.setText("Please enter a valid odd");
			    }else {
					count++;
					String answer = answerTextField.getText();
					float odd = Float.parseFloat(textField.getText());
					AnswerList.add(answer);
					OddLsit.add(odd);
					jLabelError.setText("Answer added successfully");
					answerTextField.setText("");
					textField.setText("");
				}
			}
		});
		answerButton.setBounds(409, 253, 120, 23);
		getContentPane().add(answerButton);
		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					jCalendar.setCalendar(calendarMio);
					Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));

					try {
						BLFacade facade = MainGUI.getBusinessLogic();

						Vector<domain.Event> events = facade.getEvents(firstDay);

						if (events.isEmpty())
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarMio.getTime()));
						else
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarMio.getTime()));
						jComboBoxEvents.removeAllItems();
						System.out.println("Events " + events);

						for (domain.Event ev : events)
							modelEvents.addElement(ev);
						jComboBoxEvents.repaint();

						if (events.size() == 0)
							jButtonCreate.setEnabled(false);
						else
							jButtonCreate.setEnabled(true);

					} catch (Exception e1) {

						jLabelError.setText(e1.getMessage());
					}

				}
				paintDaysWithEvents(jCalendar);
			}
		});
	}



/* Less eficient version: too many calls to business logic 
	  
	 
	public static void paintDaysWithEvents(JCalendar jCalendar) {
		// For each day in current month, it is checked if there are events, and in that
		// case, the background color for that day is changed.

		BLFacade facade = MainGUI.getBusinessLogic();

		Calendar calendar = jCalendar.getCalendar();

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);

		int offset = calendar.get(Calendar.DAY_OF_WEEK);
		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;

		int month = calendar.get(Calendar.MONTH);
		while (month == calendar.get(Calendar.MONTH)) {
			Vector<domain.Event> events = facade.getEvents(calendar.getTime());
			if (events.size() > 0) {
				// Obtain the component of the day in the panel of the DayChooser of the
				// JCalendar.
				// The component is located after the decorator buttons of "Sun", "Mon",... or
				// "Lun", "Mar"...,
				// the empty days before day 1 of month, and all the days previous to each day.
				// That number of components is calculated with "offset" and is different in
				// English and Spanish
//				    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
				Component o = (Component) jCalendar.getDayChooser().getDayPanel()
						.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
				o.setBackground(Color.CYAN);
			}
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
		}
		calendar.set(Calendar.MONTH, month);
	}

*/
	
	public static void paintDaysWithEvents(JCalendar jCalendar) {
		// For each day in current month, it is checked if there are events, and in that
		// case, the background color for that day is changed.

		BLFacade facade = MainGUI.getBusinessLogic();

		Vector<Date> dates=facade.getEventsMonth(jCalendar.getDate());
			
		Calendar calendar = jCalendar.getCalendar();
		
		int month = calendar.get(Calendar.MONTH);
		//int today=calendar.get(Calendar.DAY_OF_MONTH);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;
		
		
	 	for (Date d:dates){

	 		calendar.setTime(d);
	 		System.out.println(d);
	 		

			
			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
	 	}
	 	
	 		calendar.set(Calendar.DAY_OF_MONTH, 1);
	 		calendar.set(Calendar.MONTH, month);
	 	
	}
	
	
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		domain.Event event = ((domain.Event) jComboBoxEvents.getSelectedItem());

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			// Displays an exception if the query field is empty
			String inputQuery = jTextFieldQuery.getText(); 

			if (inputQuery.length() > 0) {
				if (count < 2) {
					jLabelError.setText("Pls enter at least 1 answer for the question");
				}else {
					// It could be to trigger an exception if the introduced string is not a number
					float inputPrice = Float.parseFloat(jTextFieldPrice.getText());
					if (inputPrice <= 0)
						jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
					else {
	
						// Obtain the business logic from a StartWindow class (local or remote)
						BLFacade facade = MainGUI.getBusinessLogic();
	
						facade.createQuestion(event, inputQuery, inputPrice, AnswerList, OddLsit);
	
						jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryCreated"));
						
						OddLsit.clear();
						AnswerList.clear();
					}
				}
			} else
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuery"));
		} catch (EventFinished e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished") + ": "
					+ event.getDescription());
		} catch (QuestionAlreadyExist e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		} catch (java.lang.NumberFormatException e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}