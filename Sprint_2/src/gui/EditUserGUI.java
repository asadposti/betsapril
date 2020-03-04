package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;
import exceptions.invalidID;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import javax.swing.ButtonGroup;

public class EditUserGUI extends JDialog {

	
	private JPanel contentPane;
	private JTextField idField;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField emailField;

	private JRadioButton rdbtnUser = new JRadioButton("User");
	private JRadioButton rdbtnAdmin = new JRadioButton("Admin");
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public EditUserGUI(String ID, String name, String surname, String email, String status) {
		setTitle("Edit user information");
		
		idField = new JTextField(ID);
		nameField = new JTextField(name);
		surnameField = new JTextField(surname);
		emailField = new JTextField(email);

		if(status.equals("Admin.")) {
			rdbtnAdmin.setSelected(true);
		}
		else {
			rdbtnUser.setSelected(true);
		}
		
		
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 457, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{60, 0, 0, 0, 39, 30, 40, 40, 30, 50, 40, 30, 0, 40, 0};
		gbl_panel.rowHeights = new int[]{30, 0, 30, 40, 40, 40, 40, 0, 20, 0, 30, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblEditUserInformation = new JLabel("Edit user information");
		lblEditUserInformation.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblEditUserInformation = new GridBagConstraints();
		gbc_lblEditUserInformation.anchor = GridBagConstraints.WEST;
		gbc_lblEditUserInformation.gridwidth = 10;
		gbc_lblEditUserInformation.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditUserInformation.gridx = 1;
		gbc_lblEditUserInformation.gridy = 1;
		panel.add(lblEditUserInformation, gbc_lblEditUserInformation);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.gridwidth = 2;
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 3;
		panel.add(lblUsername, gbc_lblUsername);
		

		GridBagConstraints gbc_idField = new GridBagConstraints();
		gbc_idField.gridwidth = 5;
		gbc_idField.insets = new Insets(0, 0, 5, 5);
		gbc_idField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idField.gridx = 3;
		gbc_idField.gridy = 3;
		panel.add(idField, gbc_idField);
		idField.setColumns(10);
		
		JLabel usernameError = new JLabel("ID already in use");
		usernameError.setForeground(Color.RED);
		GridBagConstraints gbc_usernameError = new GridBagConstraints();
		gbc_usernameError.gridwidth = 3;
		gbc_usernameError.insets = new Insets(0, 0, 5, 5);
		gbc_usernameError.gridx = 8;
		gbc_usernameError.gridy = 3;
		panel.add(usernameError, gbc_usernameError);
		usernameError.setVisible(false);;
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.gridwidth = 2;
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 4;
		panel.add(lblName, gbc_lblName);
		
		
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 5;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 4;
		panel.add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname:");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.gridwidth = 2;
		gbc_lblSurname.anchor = GridBagConstraints.EAST;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 1;
		gbc_lblSurname.gridy = 5;
		panel.add(lblSurname, gbc_lblSurname);
		
	
		surnameField.setColumns(10);
		GridBagConstraints gbc_surnameField = new GridBagConstraints();
		gbc_surnameField.gridwidth = 5;
		gbc_surnameField.insets = new Insets(0, 0, 5, 5);
		gbc_surnameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_surnameField.gridx = 3;
		gbc_surnameField.gridy = 5;
		panel.add(surnameField, gbc_surnameField);
		
		JLabel lblEmail = new JLabel("E-mail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.gridwidth = 2;
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 6;
		panel.add(lblEmail, gbc_lblEmail);
		

		emailField.setColumns(10);
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.gridwidth = 7;
		gbc_emailField.insets = new Insets(0, 0, 5, 5);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 3;
		gbc_emailField.gridy = 6;
		panel.add(emailField, gbc_emailField);
		
		JLabel lblRights = new JLabel("Rights:");
		GridBagConstraints gbc_lblRights = new GridBagConstraints();
		gbc_lblRights.gridwidth = 2;
		gbc_lblRights.anchor = GridBagConstraints.EAST;
		gbc_lblRights.insets = new Insets(0, 0, 5, 5);
		gbc_lblRights.gridx = 1;
		gbc_lblRights.gridy = 7;
		panel.add(lblRights, gbc_lblRights);
		
		
		GridBagConstraints gbc_rdbtnUser = new GridBagConstraints();
		gbc_rdbtnUser.gridwidth = 2;
		gbc_rdbtnUser.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnUser.gridx = 3;
		gbc_rdbtnUser.gridy = 7;
		buttonGroup.add(rdbtnUser);
		panel.add(rdbtnUser, gbc_rdbtnUser);
		

		GridBagConstraints gbc_rdbtnAdmin = new GridBagConstraints();
		gbc_rdbtnAdmin.gridwidth = 2;
		gbc_rdbtnAdmin.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAdmin.gridx = 5;
		gbc_rdbtnAdmin.gridy = 7;
		buttonGroup.add(rdbtnAdmin);
		panel.add(rdbtnAdmin, gbc_rdbtnAdmin);

		
		JButton btnSave = new JButton("Save changes");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameError.setVisible(false);
				try {
					String newId = idField.getText();
					String newName = nameField.getText();
					String newSurname = surnameField.getText();
					String newEmail = emailField.getText();
					boolean isAdmin;
					if(rdbtnAdmin.isSelected()) {
						isAdmin = true;
					}
					else {
						isAdmin = false;
					}
					BLFacade f = MainGUI.getBusinessLogic();
					f.updateUserInfo(ID, newId, newName, newSurname, newEmail, isAdmin);
					dispose();
				}
				catch(invalidID i) {
					usernameError.setVisible(true);
				}
			}
		});
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridwidth = 3;
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 9;
		panel.add(btnSave, gbc_btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idField.setText(ID);
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.NORTH;
		gbc_btnCancel.gridwidth = 3;
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 7;
		gbc_btnCancel.gridy = 9;
		panel.add(btnCancel, gbc_btnCancel);
		
	}
	public String[] newData() {
		String status;
		if(rdbtnAdmin.isSelected()) {
			status = "Admin.";
		}
		else {
			status = "User";
		}
		String[] s = {idField.getText(), nameField.getText(), surnameField.getText(), emailField.getText(),status};
		return s;
	}
}
