package gui.components;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Clock extends JPanel {

	private static final long serialVersionUID = 1L;
	
	Timer timer;
	JLabel dateLabel;
	JLabel timerLabel;
	
	/**
	 * Create the panel.
	 */
	public Clock() {
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(255, 255, 255));
		setLayout(null);
		initializeComponents();
		ActionListener a = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Date d = new Date();
				
				//Time in 24H format
				DateFormat tf = new SimpleDateFormat("HH:mm:ss");	
				String time = tf.format(d);
				timerLabel.setText(time);
				
				//Todays date
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.ENGLISH);
				sdf.applyPattern("EEE, d MMM yyyy");
				String sDate = sdf.format(d);
				dateLabel.setText(sDate);
			}
		};
		timer = new Timer(1000,a);
		timer.setInitialDelay(0);
		timer.start();
		
		
	}
	
	/**
	 * Initializes components on the panel
	 */
	public void initializeComponents() {
		dateLabel = new JLabel("");
		dateLabel.setBackground(new Color(0, 0, 0));
		dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dateLabel.setFont(new Font("Source Code Pro Medium", Font.BOLD, 15));
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setBounds(21, 0, 165, 37);
		add(dateLabel);
		
		timerLabel = new JLabel("");
		timerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		timerLabel.setBackground(new Color(0, 0, 0));
		timerLabel.setFont(new Font("Source Code Pro Medium", Font.BOLD, 19));
		timerLabel.setForeground(new Color(255, 255, 255));
		timerLabel.setBounds(21, 27, 165, 37);
		add(timerLabel);
	}
}
