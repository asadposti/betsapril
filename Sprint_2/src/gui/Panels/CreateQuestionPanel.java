package gui.Panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import gui.MainGUI;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class CreateQuestionPanel extends JPanel {


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
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelAnswerError = new JLabel();
	private final JTextField answerTextField = new JTextField();
	private JTextField oddTextField;
	private int count = 0;
	private ArrayList<String> AnswerList = new ArrayList<String>();
	private ArrayList<Float> OddLsit = new ArrayList<Float>();

	/**
	 * Create the panel.
	 */
	public CreateQuestionPanel() {
		setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setBackground(new Color(255, 255, 255));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 68, 59, 83, 10, 44, 11, 78, 39, 37, 122, 52, 23, 0, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 131, 0, 23, 25, 0, 25, 0, 25, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		GridBagConstraints gbc_jLabelEventDate = new GridBagConstraints();
		gbc_jLabelEventDate.anchor = GridBagConstraints.WEST;
		gbc_jLabelEventDate.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelEventDate.gridwidth = 3;
		gbc_jLabelEventDate.gridx = 1;
		gbc_jLabelEventDate.gridy = 1;
		add(jLabelEventDate, gbc_jLabelEventDate);
		jLabelListOfEvents.setBounds(new Rectangle(290, 18, 277, 20));
		GridBagConstraints gbc_jLabelListOfEvents = new GridBagConstraints();
		gbc_jLabelListOfEvents.anchor = GridBagConstraints.WEST;
		gbc_jLabelListOfEvents.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelListOfEvents.gridwidth = 6;
		gbc_jLabelListOfEvents.gridx = 5;
		gbc_jLabelListOfEvents.gridy = 1;
		this.add(jLabelListOfEvents, gbc_jLabelListOfEvents);
		jCalendar.getDayChooser().getDayPanel().setBackground(Color.WHITE);
		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));

		GridBagConstraints gbc_jCalendar = new GridBagConstraints();
		gbc_jCalendar.gridheight = 2;
		gbc_jCalendar.anchor = GridBagConstraints.NORTHWEST;
		gbc_jCalendar.insets = new Insets(0, 0, 5, 5);
		gbc_jCalendar.gridwidth = 3;
		gbc_jCalendar.gridx = 1;
		gbc_jCalendar.gridy = 2;
		this.add(jCalendar, gbc_jCalendar);
		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
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

						jLabelAnswerError.setText(e1.getMessage());
					}

				}
				paintDaysWithEvents(jCalendar);
			}
		});
		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(275, 47, 250, 20));
		GridBagConstraints gbc_jComboBoxEvents = new GridBagConstraints();
		gbc_jComboBoxEvents.fill = GridBagConstraints.HORIZONTAL;
		gbc_jComboBoxEvents.gridwidth = 6;
		gbc_jComboBoxEvents.anchor = GridBagConstraints.NORTH;
		gbc_jComboBoxEvents.insets = new Insets(0, 0, 5, 5);
		gbc_jComboBoxEvents.gridx = 5;
		gbc_jComboBoxEvents.gridy = 2;
		this.add(jComboBoxEvents, gbc_jComboBoxEvents);
		jLabelQuery.setBounds(new Rectangle(25, 211, 75, 20));
		GridBagConstraints gbc_jLabelQuery = new GridBagConstraints();
		gbc_jLabelQuery.anchor = GridBagConstraints.WEST;
		gbc_jLabelQuery.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelQuery.gridx = 1;
		gbc_jLabelQuery.gridy = 5;
		this.add(jLabelQuery, gbc_jLabelQuery);
		jTextFieldQuery.setBackground(new Color(255, 255, 255));
		jTextFieldQuery.setBounds(new Rectangle(100, 211, 429, 20));
		
		GridBagConstraints gbc_jTextFieldQuery = new GridBagConstraints();
		gbc_jTextFieldQuery.gridwidth = 9;
		gbc_jTextFieldQuery.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldQuery.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldQuery.gridx = 2;
		gbc_jTextFieldQuery.gridy = 5;
		this.add(jTextFieldQuery, gbc_jTextFieldQuery);


		JLabel answerLabel = new JLabel("Answer"); 
		answerLabel.setBounds(25, 254, 75, 20);
		GridBagConstraints gbc_answerLabel = new GridBagConstraints();
		gbc_answerLabel.anchor = GridBagConstraints.WEST;
		gbc_answerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_answerLabel.gridx = 1;
		gbc_answerLabel.gridy = 7;
		add(answerLabel, gbc_answerLabel);


		answerTextField.setBounds(100, 254, 299, 20);
		answerTextField.setColumns(10);

		GridBagConstraints gbc_answerTextField = new GridBagConstraints();
		gbc_answerTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_answerTextField.insets = new Insets(0, 0, 5, 5);
		gbc_answerTextField.gridwidth = 7;
		gbc_answerTextField.gridx = 2;
		gbc_answerTextField.gridy = 7;
		add(answerTextField, gbc_answerTextField);


		JButton answerButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		answerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jLabelAnswerError.setText("");
					jLabelMsg.setText("");
					if (answerTextField.getText().equals("") ) {
						jLabelAnswerError.setText("Enter an answer");
						answerTextField.setBorder(new LineBorder(Color.RED, 2));
					}else if(oddTextField.getText().equals("")) {
						jLabelAnswerError.setText("Enter odd number");
						oddTextField.setBorder(new LineBorder(Color.RED, 2));
						answerTextField.setBorder(new LineBorder(Color.BLACK, 1));
					}else if(Float.parseFloat(oddTextField.getText()) < 1) {
						jLabelAnswerError.setText("Please enter a valid odd number");
						answerTextField.setBorder(new LineBorder(Color.BLACK, 1));
					}else {
						count++;
						String answer = answerTextField.getText();
						float odd = Float.parseFloat(oddTextField.getText());
						oddTextField.setBorder(new LineBorder(Color.RED, 1));
						answerTextField.setBorder(new LineBorder(Color.BLACK, 1));
						AnswerList.add(answer);
						OddLsit.add(odd);
						jLabelAnswerError.setText("Answer added successfully");
						answerTextField.setText("");
						oddTextField.setText("");
					}
				}
				catch(NumberFormatException n) {
					jLabelAnswerError.setText("Please enter a valid odd number");
				}
			}
		});
		answerButton.setBounds(409, 253, 120, 23);
		GridBagConstraints gbc_answerButton = new GridBagConstraints();
		gbc_answerButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_answerButton.insets = new Insets(0, 0, 5, 5);
		gbc_answerButton.anchor = GridBagConstraints.NORTH;
		gbc_answerButton.gridx = 10;
		gbc_answerButton.gridy = 7;
		add(answerButton, gbc_answerButton);
		jLabelAnswerError.setHorizontalAlignment(SwingConstants.CENTER);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelAnswerError.setBounds(new Rectangle(196, 322, 371, 30));
		jLabelAnswerError.setForeground(Color.red);
		GridBagConstraints gbc_jLabelAnswerError = new GridBagConstraints();
		gbc_jLabelAnswerError.gridwidth = 3;
		gbc_jLabelAnswerError.anchor = GridBagConstraints.NORTH;
		gbc_jLabelAnswerError.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelAnswerError.gridx = 9;
		gbc_jLabelAnswerError.gridy = 8;
		this.add(jLabelAnswerError, gbc_jLabelAnswerError);


		JLabel oddLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionGUI.oddLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$
		oddLabel.setBounds(25, 304, 75, 20);
		GridBagConstraints gbc_oddLabel = new GridBagConstraints();
		gbc_oddLabel.anchor = GridBagConstraints.WEST;
		gbc_oddLabel.insets = new Insets(0, 0, 5, 5);
		gbc_oddLabel.gridx = 1;
		gbc_oddLabel.gridy = 9;
		add(oddLabel, gbc_oddLabel);

		oddTextField = new JTextField();
		oddTextField.setBounds(100, 304, 86, 20);
		GridBagConstraints gbc_oddTextField = new GridBagConstraints();
		gbc_oddTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_oddTextField.insets = new Insets(0, 0, 5, 5);
		gbc_oddTextField.gridwidth = 2;
		gbc_oddTextField.gridx = 2;
		gbc_oddTextField.gridy = 9;
		add(oddTextField, gbc_oddTextField);
		oddTextField.setColumns(10);


		jButtonCreate.setBounds(new Rectangle(100, 390, 130, 30));

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		GridBagConstraints gbc_jButtonCreate = new GridBagConstraints();
		gbc_jButtonCreate.anchor = GridBagConstraints.NORTHWEST;
		gbc_jButtonCreate.insets = new Insets(0, 0, 5, 5);
		gbc_jButtonCreate.gridx = 10;
		gbc_jButtonCreate.gridy = 9;
		this.add(jButtonCreate, gbc_jButtonCreate);
		jLabelMsg.setHorizontalAlignment(SwingConstants.CENTER);

		jLabelMsg.setBounds(new Rectangle(275, 182, 305, 20));
		jLabelMsg.setForeground(Color.red);

		GridBagConstraints gbc_jLabelMsg = new GridBagConstraints();
		gbc_jLabelMsg.gridwidth = 5;
		gbc_jLabelMsg.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelMsg.gridx = 8;
		gbc_jLabelMsg.gridy = 10;
		this.add(jLabelMsg, gbc_jLabelMsg);
		jLabelMinBet.setBounds(new Rectangle(25, 359, 75, 20));

		GridBagConstraints gbc_jLabelMinBet = new GridBagConstraints();
		gbc_jLabelMinBet.anchor = GridBagConstraints.WEST;
		gbc_jLabelMinBet.insets = new Insets(0, 0, 5, 5);
		gbc_jLabelMinBet.gridx = 1;
		gbc_jLabelMinBet.gridy = 11;
		this.add(jLabelMinBet, gbc_jLabelMinBet);
		jTextFieldPrice.setBounds(new Rectangle(100, 359, 60, 20));
		GridBagConstraints gbc_jTextFieldPrice = new GridBagConstraints();
		gbc_jTextFieldPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_jTextFieldPrice.insets = new Insets(0, 0, 5, 5);
		gbc_jTextFieldPrice.gridx = 2;
		gbc_jTextFieldPrice.gridy = 11;
		this.add(jTextFieldPrice, gbc_jTextFieldPrice);
	}

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
			jLabelAnswerError.setText("");
			jLabelMsg.setText("");

			// Displays an exception if the query field is empty
			String inputQuery = jTextFieldQuery.getText(); 

			if (inputQuery.length() > 0) {
				if (count < 2) {
					jLabelAnswerError.setText("Introduce answers");
				}else {
					// It could be to trigger an exception if the introduced string is not a number
					float inputPrice = Float.parseFloat(jTextFieldPrice.getText());
					if (inputPrice <= 0)
						jLabelAnswerError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
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
			jLabelAnswerError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}


}
