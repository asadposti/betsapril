package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.User;
import exceptions.invalidID;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;


public class RegisterGUI extends JDialog {
	
	private RegisterGUI guiInstance;
	private boolean adminview;
	
	User registeredUser;

	private JTextField usernameTextField;
	private HintTextField emailTextField;
	private JTextField nameTextField;
	private JTextField surnameTextField;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmField;

	JLabel usernameErrorLabel = new JLabel("");
	JLabel emailErrorLabel = new JLabel("");
	JLabel surnameErrorLabel = new JLabel("");
	JLabel confirmPwErrorLabel = new JLabel("");
	JLabel pwErrorLabel = new JLabel("");
	JLabel nameErrorLabel = new JLabel("");
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public User getRegisteredUser() {
		return registeredUser;
	}
	 /**
	 * Create the frame.
	 */
	public RegisterGUI(boolean isadmin) {
	 	setTitle("User register");
		adminview = isadmin;
		setModal(true);
		
		setBounds(600, 200, 455,474);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{35, 20, 120, 20, 25, 0, 60, 70, 20, 35, 0};
		gridBagLayout.rowHeights = new int[]{20, 15, 20, 12, 25, 28, 20, 25, 28, 20, 25, 28, 0, 25, 28, 23, 25, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblInsertRegistrationData;
		if (adminview) {
			lblInsertRegistrationData = new JLabel("Insert new user data");
		}
		else {
			lblInsertRegistrationData = new JLabel("Insert registration data");
		}
		lblInsertRegistrationData.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblInsertRegistrationData = new GridBagConstraints();
		gbc_lblInsertRegistrationData.gridwidth = 6;
		gbc_lblInsertRegistrationData.insets = new Insets(0, 0, 5, 5);
		gbc_lblInsertRegistrationData.gridx = 2;
		gbc_lblInsertRegistrationData.gridy = 1;
		getContentPane().add(lblInsertRegistrationData, gbc_lblInsertRegistrationData);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.gridwidth = 3;
		gbc_lblUsername.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 3;
		getContentPane().add(lblUsername, gbc_lblUsername);
		
		JLabel lblRights = new JLabel("Rights:");
		GridBagConstraints gbc_lblRights = new GridBagConstraints();
		gbc_lblRights.anchor = GridBagConstraints.WEST;
		gbc_lblRights.gridwidth = 4;
		gbc_lblRights.insets = new Insets(0, 0, 5, 5);
		gbc_lblRights.gridx = 5;
		gbc_lblRights.gridy = 3;
		getContentPane().add(lblRights, gbc_lblRights);
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.gridwidth = 3;
		gbc_usernameTextField.fill = GridBagConstraints.BOTH;
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_usernameTextField.gridx = 1;
		gbc_usernameTextField.gridy = 4;
		getContentPane().add(usernameTextField, gbc_usernameTextField);
		
		
		JRadioButton rdbtnUser = new JRadioButton("user");
		buttonGroup.add(rdbtnUser);
		rdbtnUser.setSelected(true);
		GridBagConstraints gbc_rdbtnUser = new GridBagConstraints();
		gbc_rdbtnUser.gridwidth = 2;
		gbc_rdbtnUser.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnUser.gridx = 5;
		gbc_rdbtnUser.gridy = 4;
		getContentPane().add(rdbtnUser, gbc_rdbtnUser);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin.");
		buttonGroup.add(rdbtnAdmin);
		GridBagConstraints gbc_rdbtnAdmin = new GridBagConstraints();
		gbc_rdbtnAdmin.gridwidth = 2;
		gbc_rdbtnAdmin.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAdmin.gridx = 7;
		gbc_rdbtnAdmin.gridy = 4;
		getContentPane().add(rdbtnAdmin, gbc_rdbtnAdmin);
		

		usernameErrorLabel.setForeground(new Color(255, 51, 51));
		GridBagConstraints gbc_usernameErrorLabel = new GridBagConstraints();
		gbc_usernameErrorLabel.gridwidth = 3;
		gbc_usernameErrorLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_usernameErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_usernameErrorLabel.gridx = 1;
		gbc_usernameErrorLabel.gridy = 5;
		getContentPane().add(usernameErrorLabel, gbc_usernameErrorLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.gridwidth = 3;
		gbc_lblPassword.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 6;
		getContentPane().add(lblPassword, gbc_lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
		gbc_lblConfirmPassword.gridwidth = 4;
		gbc_lblConfirmPassword.anchor = GridBagConstraints.WEST;
		gbc_lblConfirmPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmPassword.gridx = 5;
		gbc_lblConfirmPassword.gridy = 6;
		getContentPane().add(lblConfirmPassword, gbc_lblConfirmPassword);
		
		if(!adminview) {
			lblRights.setVisible(false);
			rdbtnAdmin.setVisible(false);
			rdbtnUser.setVisible(false);
		}
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				BLFacade facade = MainGUI.getBusinessLogic();
				
				usernameErrorLabel.setText("");
				pwErrorLabel.setText("");
				confirmPwErrorLabel.setText("");
				nameErrorLabel.setText("");
				surnameErrorLabel.setText("");
				emailErrorLabel.setText("");
				
				//retrieve input field data and check validity
				String username = usernameTextField.getText();
				String pass = new String(passwordField.getPassword());
				String passconfirm = new String(passwordConfirmField.getPassword());
				String name = nameTextField.getText();
				String surname = surnameTextField.getText();
				String email = emailTextField.getText();
				boolean rights = rdbtnAdmin.isSelected();

				
				if (username.equals("") || pass.equals("") || name.equals("") || surname.equals("") || email.equals("")) {
					emailErrorLabel.setText("Please fill all areas");
				}
				else if(pass.length()<8) {
					pwErrorLabel.setText("Min. password length : 8");
				}
				else if(!passconfirm.equals(pass)){
					confirmPwErrorLabel.setText("Passwords don't match");
				}
				else {	
					try {
						System.out.println(username + " " + pass + " " + passconfirm + " " + name + " " + surname + " " + email);
						registeredUser = facade.registerUser(username, pass, name, surname, email, rights);
						JOptionPane.showMessageDialog(guiInstance, "Registration sucessfull");
						dispose();	
					}
					catch (invalidID e) {
						usernameErrorLabel.setText(e.getMessage());
					}
				}
			}
		});	
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 7;
		getContentPane().add(passwordField, gbc_passwordField);
		
		passwordConfirmField = new JPasswordField();
		GridBagConstraints gbc_passwordConfirmField = new GridBagConstraints();
		gbc_passwordConfirmField.gridwidth = 4;
		gbc_passwordConfirmField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordConfirmField.fill = GridBagConstraints.BOTH;
		gbc_passwordConfirmField.gridx = 5;
		gbc_passwordConfirmField.gridy = 7;
		getContentPane().add(passwordConfirmField, gbc_passwordConfirmField);
		
		
		pwErrorLabel.setForeground(new Color(255, 51, 51));
		GridBagConstraints gbc_pwErrorLabel = new GridBagConstraints();
		gbc_pwErrorLabel.gridwidth = 3;
		gbc_pwErrorLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_pwErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_pwErrorLabel.gridx = 1;
		gbc_pwErrorLabel.gridy = 8;
		getContentPane().add(pwErrorLabel, gbc_pwErrorLabel);
		

		confirmPwErrorLabel.setForeground(new Color(255, 51, 51));
		GridBagConstraints gbc_confirmPwErrorLabel = new GridBagConstraints();
		gbc_confirmPwErrorLabel.gridwidth = 3;
		gbc_confirmPwErrorLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_confirmPwErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_confirmPwErrorLabel.gridx = 6;
		gbc_confirmPwErrorLabel.gridy = 8;
		getContentPane().add(confirmPwErrorLabel, gbc_confirmPwErrorLabel);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.gridwidth = 3;
		gbc_lblName.anchor = GridBagConstraints.NORTH;
		gbc_lblName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 9;
		getContentPane().add(lblName, gbc_lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		GridBagConstraints gbc_lblSurname = new GridBagConstraints();
		gbc_lblSurname.gridwidth = 4;
		gbc_lblSurname.anchor = GridBagConstraints.NORTH;
		gbc_lblSurname.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSurname.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurname.gridx = 5;
		gbc_lblSurname.gridy = 9;
		getContentPane().add(lblSurname, gbc_lblSurname);
		
		nameTextField = new JTextField();
		nameTextField.setToolTipText("");
		nameTextField.setForeground(new Color(0, 0, 0));
		nameTextField.setColumns(10);
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.gridwidth = 3;
		gbc_nameTextField.fill = GridBagConstraints.BOTH; 
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 10;
		getContentPane().add(nameTextField, gbc_nameTextField);
		
		surnameTextField = new JTextField();
		surnameTextField.setColumns(10);
		GridBagConstraints gbc_surnameTextField = new GridBagConstraints();
		gbc_surnameTextField.gridwidth = 4;
		gbc_surnameTextField.fill = GridBagConstraints.BOTH;
		gbc_surnameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_surnameTextField.gridx = 5;
		gbc_surnameTextField.gridy = 10;
		getContentPane().add(surnameTextField, gbc_surnameTextField);
		
		nameErrorLabel.setForeground(new Color(255, 51, 51));
		GridBagConstraints gbc_nameErrorLabel = new GridBagConstraints();
		gbc_nameErrorLabel.gridwidth = 3;
		gbc_nameErrorLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_nameErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameErrorLabel.gridx = 1;
		gbc_nameErrorLabel.gridy = 11;
		getContentPane().add(nameErrorLabel, gbc_nameErrorLabel);
		

		surnameErrorLabel.setForeground(new Color(255, 51, 51));
		GridBagConstraints gbc_surnameErrorLabel = new GridBagConstraints();
		gbc_surnameErrorLabel.gridwidth = 3;
		gbc_surnameErrorLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_surnameErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_surnameErrorLabel.gridx = 6;
		gbc_surnameErrorLabel.gridy = 11;
		getContentPane().add(surnameErrorLabel, gbc_surnameErrorLabel);
		
		JLabel lblEmail = new JLabel("E-mail:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.gridwidth = 3;
		gbc_lblEmail.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 12;
		getContentPane().add(lblEmail, gbc_lblEmail);
		
		emailTextField = new HintTextField("example@emailprovider.com");
		emailTextField.setToolTipText("");
		emailTextField.setColumns(10);
		GridBagConstraints gbc_emailTextField = new GridBagConstraints();
		gbc_emailTextField.gridwidth = 8;
		gbc_emailTextField.anchor = GridBagConstraints.NORTH;
		gbc_emailTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTextField.insets = new Insets(0, 0, 5, 5);
		gbc_emailTextField.gridx = 1;
		gbc_emailTextField.gridy = 13;
		getContentPane().add(emailTextField, gbc_emailTextField);
		

		emailErrorLabel.setForeground(new Color(255, 51, 51));
		GridBagConstraints gbc_emailErrorLabel = new GridBagConstraints();
		gbc_emailErrorLabel.anchor = GridBagConstraints.NORTH;
		gbc_emailErrorLabel.gridwidth = 8;
		gbc_emailErrorLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailErrorLabel.gridx = 1;
		gbc_emailErrorLabel.gridy = 14;
		getContentPane().add(emailErrorLabel, gbc_emailErrorLabel);
		GridBagConstraints gbc_registerButton = new GridBagConstraints();
		gbc_registerButton.insets = new Insets(0, 0, 5, 5);
		gbc_registerButton.anchor = GridBagConstraints.NORTH;
		gbc_registerButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_registerButton.gridx = 2;
		gbc_registerButton.gridy = 15;
		getContentPane().add(registerButton, gbc_registerButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.gridwidth = 2;
		gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButton.gridx = 6;
		gbc_cancelButton.gridy = 15;
		getContentPane().add(cancelButton, gbc_cancelButton);	
			}
	
}
