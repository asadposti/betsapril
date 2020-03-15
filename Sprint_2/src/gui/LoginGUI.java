package gui;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import businessLogic.BLFacade;
import exceptions.invalidID;
import exceptions.invalidPW;
import gui.components.HintPassField;
import gui.components.HintTextField;
import gui.components.passVisibleLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import gui.components.cancelLabel;

public class LoginGUI extends JDialog {
	private static final long serialVersionUID = 1L;

	private Boolean status; //status of the logged user

	private JPanel contentPane;
	private HintTextField usernameTextField;
	private HintPassField passwordField;
	
	private JButton loginButton;
	
	private JLabel titleLabel;
	private JLabel usernameIconLabel_1;
	private JLabel passIconLabel_1;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel usernameErrorLabel;
	private JLabel passErrorLabel;
	private JLabel registerLabel;
	private JLabel createAccountLabel;
	
	/**
	 * Create the frame.
	 */
	public LoginGUI() {

		BLFacade facade = MainGUI.getBusinessLogic();
		
		setTitle("Login");
		
		setModal(true);
		setResizable(false);
		setBounds(600, 200, 551, 422);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(0, 37, 545, 51);
		panel.setBackground(new Color(0, 0, 0));
		panel.setLayout(null);
		contentPane.add(panel);

		titleLabel = new JLabel(" Log into Bet & Ruin");
		titleLabel.setBounds(10, 11, 525, 26);
		panel.add(titleLabel);
		titleLabel.setForeground(new Color(153, 153, 153));
		titleLabel.setBackground(new Color(255, 255, 255));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Source Code Pro ExtraLight", Font.BOLD, 24));

		usernameTextField = new HintTextField("Username");
		usernameTextField.setBounds(108, 117, 345, 25);
		usernameTextField.setColumns(10);
		contentPane.add(usernameTextField);

		
		usernameErrorLabel = new JLabel("");
		usernameErrorLabel.setFont(new Font("Source Code Pro", Font.BOLD, 13));
		usernameErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameErrorLabel.setForeground(new Color(255, 255, 255));
		usernameErrorLabel.setBounds(108, 143, 355, 37);
		contentPane.add(usernameErrorLabel);
		
		passwordField = new HintPassField("Password");
		passwordField.setBackground(new Color(0, 0, 0));
		passwordField.setBounds(108, 179, 345, 25);
		contentPane.add(passwordField);
		passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		
		passErrorLabel = new JLabel("");
		passErrorLabel.setFont(new Font("Source Code Pro", Font.BOLD, 13));
		passErrorLabel.setBounds(108, 202, 355, 37);
		passErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passErrorLabel.setForeground(new Color(255, 255, 255));
		contentPane.add(passErrorLabel);

		loginButton = new JButton("Log in");
		loginButton.setBorderPainted(false);
		loginButton.setFocusPainted(false);
		loginButton.setBounds(108, 241, 345, 29);
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBackground(new Color(102, 102, 102));
		loginButton.setFont(new Font("Source Code Pro ExtraLight", Font.BOLD, 18));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				usernameErrorLabel.setText("");
				passErrorLabel.setText("");
				String username = usernameTextField.getText();
				String pass =  new String (passwordField.getPassword());
				try {
					status = facade.checkCredentials(username, pass);
					JOptionPane.showMessageDialog(null, "Login successful");
					dispose();
				} catch (invalidID e) {
					usernameErrorLabel.setText(e.getMessage());
				} catch (invalidPW e) {
					passErrorLabel.setText(e.getMessage());
				}
			}
		});
		contentPane.add(loginButton);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(52, 99, 46, 51);
		contentPane.add(panel_1);

		usernameIconLabel_1 = new JLabel();
		usernameIconLabel_1.setIcon(new ImageIcon("images/usericon1.png"));
		panel_1.add(usernameIconLabel_1);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 0));
		panel_2.setBounds(52, 165, 46, 51);
		contentPane.add(panel_2);
		passIconLabel_1 = new JLabel();
		passIconLabel_1.setIcon(new ImageIcon("images/passicon.png"));

		panel_2.add(passIconLabel_1);
		
		registerLabel = new JLabel("Not yet a member? ");
		registerLabel.setBackground(new Color(153, 153, 153));
		registerLabel.setFont(new Font("Source Code Pro ExtraLight", Font.BOLD, 14));
		registerLabel.setForeground(new Color(204, 204, 204));
		registerLabel.setBounds(20, 313, 147, 37);
		contentPane.add(registerLabel);

		
		createAccountLabel = new JLabel("Create an account");
		createAccountLabel.setBackground(new Color(153, 153, 153));
		createAccountLabel.setFont(new Font("Source Code Pro ExtraLight", Font.BOLD, 16));
		createAccountLabel.setForeground(new Color(204, 204, 204));
		createAccountLabel.setBounds(20, 338, 194, 38);
		contentPane.add(createAccountLabel);
		createAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				createAccountLabel.setForeground(new Color(153,153,153));		
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				createAccountLabel.setForeground(new Color(255,255,255));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{   
				dispose();
				JDialog d = new RegisterGUI(false);
				d.setVisible(true);

			}		
		});
								
		
		passVisibleLabel pvl = new passVisibleLabel(passwordField);
		pvl.setBounds(460, 179, 65, 29);
		pvl.setVisible(true);
		contentPane.add(pvl);
		
		cancelLabel cancelLabel_ = new cancelLabel(this);
		cancelLabel_.setBounds(429, 345, 96, 23);
		contentPane.add(cancelLabel_);
	}

	
	
	public Boolean getResult() {
		this.setVisible(true);
		return status;
	}
}
