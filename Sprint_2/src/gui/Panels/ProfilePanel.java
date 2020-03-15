package gui.Panels;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import businessLogic.BLFacade;
import domain.Bet;
import gui.MainGUI;

import java.awt.Color;

public class ProfilePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProfilePanel() {
		setBackground(Color.WHITE);

		BLFacade facade = MainGUI.getBusinessLogic();
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 0, 45, 0, 40, 0, 61, 20, 0};
		gbl_contentPane.rowHeights = new int[]{20, 0, 25, 25, 25, 25, 124, 15, 0, 20, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gbl_contentPane);
		
		JLabel lblUserProfile = new JLabel("User Profile");
		lblUserProfile.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblUserProfile = new GridBagConstraints();
		gbc_lblUserProfile.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserProfile.gridx = 1;
		gbc_lblUserProfile.gridy = 1;
		add(lblUserProfile, gbc_lblUserProfile);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 3;
		add(lblUsername, gbc_lblUsername);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(facade.getProfile().getID());
		textArea.setBackground(SystemColor.menu);
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 3;
		add(textArea, gbc_textArea);
		
		JLabel lblAvailableMoney = new JLabel("Available money:");
		GridBagConstraints gbc_lblAvailableMoney = new GridBagConstraints();
		gbc_lblAvailableMoney.gridwidth = 2;
		gbc_lblAvailableMoney.anchor = GridBagConstraints.WEST;
		gbc_lblAvailableMoney.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvailableMoney.gridx = 4;
		gbc_lblAvailableMoney.gridy = 3;
		add(lblAvailableMoney, gbc_lblAvailableMoney);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setText(String.valueOf(facade.getProfile().getCash()));
		textArea_3.setBackground(SystemColor.menu);
		textArea_3.setEditable(false);
		GridBagConstraints gbc_textArea_3 = new GridBagConstraints();
		gbc_textArea_3.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_3.fill = GridBagConstraints.BOTH;
		gbc_textArea_3.gridx = 6;
		gbc_textArea_3.gridy = 3;
		add(textArea_3, gbc_textArea_3);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 4;
		add(lblName, gbc_lblName);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setText(facade.getProfile().getName());
		textArea_1.setBackground(SystemColor.menu);
		textArea_1.setEditable(false);
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 2;
		gbc_textArea_1.gridy = 4;
		add(textArea_1, gbc_textArea_1);
		
		JLabel lblLastName = new JLabel("Surname:");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.gridwidth = 2;
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 4;
		gbc_lblLastName.gridy = 4;
		add(lblLastName, gbc_lblLastName);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setText(facade.getProfile().getSurname());
		textArea_2.setBackground(SystemColor.menu);
		textArea_2.setEditable(false);
		GridBagConstraints gbc_textArea_2 = new GridBagConstraints();
		gbc_textArea_2.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_2.fill = GridBagConstraints.BOTH;
		gbc_textArea_2.gridx = 6;
		gbc_textArea_2.gridy = 4;
		add(textArea_2, gbc_textArea_2);
		
		JLabel lblActiveBets = new JLabel("Active bets:");
		GridBagConstraints gbc_lblActiveBets = new GridBagConstraints();
		gbc_lblActiveBets.anchor = GridBagConstraints.WEST;
		gbc_lblActiveBets.insets = new Insets(0, 0, 5, 5);
		gbc_lblActiveBets.gridx = 1;
		gbc_lblActiveBets.gridy = 5;
		add(lblActiveBets, gbc_lblActiveBets);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		add(scrollPane, gbc_scrollPane);
		

	
		  DefaultListModel<String> list = 
				new DefaultListModel<String>();
		  for (Bet v : facade.retrieveBets()) {
			  list.addElement(""+"Your Bet Amount:>  "+v.getAmount()+"  "+"And Your Question:>  "+
		  v.getQuestion().getQuestion());
			  }
		  JList<String> model = 
					new JList<String>(list);
		  
		scrollPane.setViewportView(model);
		
	}

}
