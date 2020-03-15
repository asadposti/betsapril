package gui;


import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Profile;
import domain.User;
import gui.Panels.BrowsePanel;
import gui.Panels.CreateQuestionPanel;
import gui.Panels.HomePanel;
import gui.Panels.ProfilePanel;
import gui.Panels.SettingsPanel;
import gui.Panels.userManagementPanel;
import gui.components.Clock;
import gui.components.MenuButton;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class MainGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	boolean admin;
	
	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	private JLayeredPane layeredPane;
	
	//JPanel
	private JPanel contentPane;
	private JPanel currentPanel;
	private JPanel menuPanel;
	//private JPanel profilepicPanel;
	private JPanel miniprofilePanel;
	private JPanel loggedPanel;
	private JPanel topPanel;
	private JPanel unloggedPanel;
	
	private HomePanel hmPanel;
	private BrowsePanel brwPanel;
	private userManagementPanel umPanel;
	private ProfilePanel pfPanel;
	private CreateQuestionPanel cqPanel;
	private SettingsPanel stgPanel;
	
	//JButton
	private JButton userManagementButton;
	private JButton createQuestionButton;
	private JButton settingsButton;
	private JButton profileButton; 
	private JButton browseButton;
	private JButton homeButton;
	private JButton registerButton;
	private JButton loginButton;
	private JButton logoutButton;
	private JButton addcashButton;
	private JButton menuButton;
	
	private ArrayList<JButton> menubuttons;
	
	//JLabel
	private JLabel IDTitleLabel;
	private JLabel cashTitleLabel;
	private JLabel profilepicLabel;
	private JLabel IDLabel;
	private JLabel cashLabel;
	private JButton feedbackButton;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setTitle("BET & RUIN");
		
		//will need to be removed for remote
		appFacadeInterface=new BLFacadeImplementation();
		
		admin = false;
		menubuttons = new ArrayList<JButton>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 940, 684);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{150, 200, 273, 0};
		gbl_contentPane.rowHeights = new int[]{76, 534, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		topPanel = new JPanel();
		topPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		topPanel.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_topPanel = new GridBagConstraints();
		gbc_topPanel.gridwidth = 3;
		gbc_topPanel.fill = GridBagConstraints.BOTH;
		gbc_topPanel.gridx = 0;
		gbc_topPanel.gridy = 0;
		contentPane.add(topPanel, gbc_topPanel);
		GridBagLayout gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[]{179, 157, 314, 315, 0};
		gbl_topPanel.rowHeights = new int[]{71, 0};
		gbl_topPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_topPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		topPanel.setLayout(gbl_topPanel);
		
		JPanel timerPanel = new Clock();
		timerPanel.setBackground(new Color(0, 0, 0));
		GridBagConstraints gbc_timerPanel = new GridBagConstraints();
		gbc_timerPanel.fill = GridBagConstraints.BOTH;
		gbc_timerPanel.gridx = 0;
		gbc_timerPanel.gridy = 0;
		topPanel.add(timerPanel, gbc_timerPanel);
		
		layeredPane = new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 3;
		gbc_layeredPane.gridy = 0;
		topPanel.add(layeredPane, gbc_layeredPane);
		
		loggedPanel = new JPanel();
		loggedPanel.setBackground(new Color(0, 0, 0));
		loggedPanel.setBounds(10, 0, 305, 71);
		layeredPane.add(loggedPanel);
		loggedPanel.setLayout(null);
		
		miniprofilePanel = new JPanel();
		miniprofilePanel.setLayout(null);
		miniprofilePanel.setBackground(Color.WHITE);
		miniprofilePanel.setBounds(4, 5, 234, 60);
		loggedPanel.add(miniprofilePanel);
		
		IDTitleLabel = new JLabel("ID:");
		IDTitleLabel.setBackground(new Color(255, 255, 255));
		IDTitleLabel.setForeground(Color.BLACK);
		IDTitleLabel.setFont(new Font("Source Code Pro Medium", Font.BOLD | Font.ITALIC, 13));
		IDTitleLabel.setBounds(77, 8, 45, 17);
		miniprofilePanel.add(IDTitleLabel);
		
		cashTitleLabel = new JLabel("Cash:\r\n");
		cashTitleLabel.setFont(new Font("Source Code Pro Medium", Font.BOLD | Font.ITALIC, 13));
		cashTitleLabel.setBounds(77, 34, 45, 17);
		miniprofilePanel.add(cashTitleLabel);
		
		addcashButton = new JButton("");
		addcashButton.setBorderPainted(false);
		addcashButton.setFocusPainted(false);
		addcashButton.setIcon(new ImageIcon("images/addIncome.png"));
		addcashButton.setBackground(new Color(255, 255, 255));
		addcashButton.setBounds(196, 22, 32, 29);
		miniprofilePanel.add(addcashButton);
		addcashButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean valid = false;
				String s = JOptionPane.showInputDialog(null, "Enter amount to add:");
				while(!valid) {
					try {	
						float addition = Float.parseFloat(s);
						if(addition < 0) {
							s = JOptionPane.showInputDialog(null, "Enter a valid amount (amount has to be > 0):");
						}
						else {
							float newcash = appFacadeInterface.addCash(addition);
							valid=true;	
							cashLabel.setText(Float.toString(newcash));
						}	
					}
					catch(NumberFormatException n) {
						s = JOptionPane.showInputDialog(null, "Enter a valid amount (amount has to be > 0):");
					}
					catch(NullPointerException nl) {
						break;
					}
				}	
			}
		});
		
		IDLabel = new JLabel("");
		IDLabel.setBounds(117, 8, 79, 18);
		miniprofilePanel.add(IDLabel);
		
		cashLabel = new JLabel("");
		cashLabel.setBounds(117, 34, 80, 17);
		miniprofilePanel.add(cashLabel);
		
		profilepicLabel = new JLabel("\r\n");
		profilepicLabel.setBounds(9, 5, 64, 50);
		miniprofilePanel.add(profilepicLabel);
		profilepicLabel.setIcon(null);
		profilepicLabel.setBackground(Color.WHITE);
		profilepicLabel.setVerticalAlignment(SwingConstants.TOP);
		profilepicLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		logoutButton = new JButton("");
		logoutButton.setToolTipText("Log out");
		logoutButton.setBorderPainted(false);
		logoutButton.setFocusPainted(false);
		logoutButton.setIcon(new ImageIcon("images/logout.png"));
		logoutButton.setBackground(new Color(0,0,0));	
		logoutButton.setBounds(239, 5, 66, 60);
		loggedPanel.add(logoutButton);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appFacadeInterface.logOut();
				admin=false;
				visitorView();
			}
		});
		logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				logoutButton.setBackground(new Color(102, 102, 102));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				logoutButton.setBackground(new Color(0,0,0));	
			}
		});
		
		unloggedPanel = new JPanel();
		unloggedPanel.setBackground(new Color(0, 0, 0));
		unloggedPanel.setBounds(10, 0, 305, 71);
		layeredPane.add(unloggedPanel);
		unloggedPanel.setLayout(null);
		
		loginButton = new JButton("Login");
		loginButton.setBorderPainted(false);
		loginButton.setFocusPainted(false);
		loginButton.setIcon(new ImageIcon("images/login.png"));
		loginButton.setFont(new Font("Source Code Pro Medium", Font.BOLD, 14));
		loginButton.setBackground(new Color(0, 0, 0));
		loginButton.setForeground(new Color(255, 255, 255));
		loginButton.setBounds(10, 0, 146, 71);
		unloggedPanel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				LoginGUI d = new LoginGUI();
				Boolean admin = d.getResult();
				if(admin != null) {
					if(admin) {
						adminView();
					}
					else {
						userView();
					}
				}	
			}
		});
		loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginButton.setBackground(new Color(102, 102, 102));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setBackground(new Color(0,0,0));	
			}
		});
		
		registerButton = new JButton("Register");
		registerButton.setForeground(new Color(255, 255, 255));
		registerButton.setBackground(new Color(0, 0, 0));
		registerButton.setBounds(150, 0, 155, 71);
		unloggedPanel.add(registerButton);
		registerButton.setBorderPainted(false);
		registerButton.setFocusPainted(false);
		registerButton.setIcon(new ImageIcon("images/register.png"));
		registerButton.setFont(new Font("Source Code Pro Medium", Font.BOLD, 14));
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog d = new RegisterGUI(admin);
				d.setVisible(true);
			}
		});
		registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				registerButton.setBackground(new Color(102, 102, 102));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				registerButton.setBackground(new Color(0,0,0));	
			}
		});
		
		loggedPanel.setEnabled(false);
		
		menuPanel = new JPanel();
		GridBagConstraints gbc_menuPanel = new GridBagConstraints();
		gbc_menuPanel.fill = GridBagConstraints.BOTH;
		gbc_menuPanel.gridx = 0;
		gbc_menuPanel.gridy = 1;
		contentPane.add(menuPanel, gbc_menuPanel);
		menuPanel.setBackground(new Color(51, 51, 51));
		menuPanel.setBorder(null);
		GridBagLayout gbl_menuPanel = new GridBagLayout();
		gbl_menuPanel.columnWidths = new int[] {40, 141};
		gbl_menuPanel.rowHeights = new int[]{40, 41, 41, 41, 41, 41, 41, 0, 0, 0, 0};
		gbl_menuPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_menuPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		menuPanel.setLayout(gbl_menuPanel);
		
		currentPanel = new JPanel();
		GridBagConstraints gbc_currentPanel = new GridBagConstraints();
		gbc_currentPanel.fill = GridBagConstraints.BOTH;
		gbc_currentPanel.gridwidth = 2;
		gbc_currentPanel.gridx = 1;
		gbc_currentPanel.gridy = 1;
		contentPane.add(currentPanel, gbc_currentPanel);
		currentPanel.setBackground(Color.WHITE);
		currentPanel.setLayout(new CardLayout(0, 0));
		
		menuButton = new MenuButton("Dashboard", new ImageIcon("images/menu.png"));
		GridBagConstraints gbc_menuButton = new GridBagConstraints();
		gbc_menuButton.fill = GridBagConstraints.BOTH;
		gbc_menuButton.gridwidth = 2;
		gbc_menuButton.gridx = 0;
		gbc_menuButton.gridy = 0;
		menuPanel.add(menuButton, gbc_menuButton);
		
		homeButton = new MenuButton("Home", new ImageIcon("images/home1.png"));
		GridBagConstraints gbc_homeButton = new GridBagConstraints();
		gbc_homeButton.gridwidth = 2;
		gbc_homeButton.fill = GridBagConstraints.BOTH;
		gbc_homeButton.gridx = 0;
		gbc_homeButton.gridy = 1;
		menuPanel.add(homeButton, gbc_homeButton);
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hmPanel == null) {
					hmPanel =  new HomePanel();
				}
				currentPanel.removeAll();
				currentPanel.add(hmPanel);
				currentPanel.updateUI();	
				select(1);
				
			}
		});
		homeButton.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				homeButton.setBackground(new Color(128,128,128));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(homeButton.isSelected()) {
					homeButton.setBackground(new Color(105, 105, 105));
				}
				else {
					homeButton.setBackground(new Color(51, 51, 51));
				}
				
			}
		});
		
		browseButton = new MenuButton("Browse", new ImageIcon("images/browse.png"));
		GridBagConstraints gbc_browseButton = new GridBagConstraints();
		gbc_browseButton.gridwidth = 2;
		gbc_browseButton.fill = GridBagConstraints.BOTH;
		gbc_browseButton.gridx = 0;
		gbc_browseButton.gridy = 2;
		menuPanel.add(browseButton, gbc_browseButton);
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(brwPanel == null) {
					brwPanel = new BrowsePanel();
				}
				currentPanel.removeAll();
				currentPanel.add(brwPanel);
				currentPanel.updateUI();
				select(2);
			}
		});
		
		
		settingsButton = new MenuButton("Settings", new ImageIcon("images/settings.png"));
		GridBagConstraints gbc_settingsButton = new GridBagConstraints();
		gbc_settingsButton.fill = GridBagConstraints.BOTH;
		gbc_settingsButton.gridwidth = 2;
		gbc_settingsButton.gridx = 0;
		gbc_settingsButton.gridy = 3;
		menuPanel.add(settingsButton, gbc_settingsButton);
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(stgPanel == null) {
					stgPanel = new SettingsPanel();
				}
				currentPanel.removeAll();
				currentPanel.add(stgPanel);
				currentPanel.updateUI();
				select(3);
				
			}
		});
		
		homeButton.setEnabled(true);
		homeButton.setVisible(true);
		browseButton.setEnabled(true);
		browseButton.setVisible(true);
		settingsButton.setVisible(true);
		settingsButton.setVisible(true);
		loggedPanel.setVisible(false);
		
		//needs to be removed for remote
		brwPanel = new BrowsePanel();
		umPanel = new userManagementPanel();
		
		
		feedbackButton = new MenuButton("Feedback", new ImageIcon("images/feedback1.png"));
		GridBagConstraints gbc_feedbackButton = new GridBagConstraints();
		gbc_feedbackButton.gridwidth = 2;
		gbc_feedbackButton.fill = GridBagConstraints.BOTH;
		gbc_feedbackButton.gridx = 0;
		gbc_feedbackButton.gridy = 4;
		menuPanel.add(feedbackButton, gbc_feedbackButton);		
		
		profileButton = new MenuButton("Profile\r\n", new ImageIcon("images/profileicon.png"));
		profileButton.setBackground(new Color(51, 51, 51));
		GridBagConstraints gbc_profileButton = new GridBagConstraints();
		gbc_profileButton.fill = GridBagConstraints.BOTH;
		gbc_profileButton.gridwidth = 3;
		gbc_profileButton.gridx = 0;
		gbc_profileButton.gridy = 5;
		menuPanel.add(profileButton, gbc_profileButton);
		profileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pfPanel== null) {
					pfPanel = new ProfilePanel();
				}
				currentPanel.removeAll();
				currentPanel.add(pfPanel);
				currentPanel.updateUI();
				select(5);
			}
		});
			
		createQuestionButton = new MenuButton("<html><left>  Create<br>  question</left></html>", new ImageIcon("images/create_question.png"));	
		GridBagConstraints gbc_createQuestionButton = new GridBagConstraints();
		gbc_createQuestionButton.fill = GridBagConstraints.BOTH;
		gbc_createQuestionButton.gridwidth = 3;
		gbc_createQuestionButton.gridx = 0;
		gbc_createQuestionButton.gridy = 6;
		menuPanel.add(createQuestionButton, gbc_createQuestionButton);
		createQuestionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cqPanel == null) {
					cqPanel = new CreateQuestionPanel();
				}
				currentPanel.removeAll();
				currentPanel.add(cqPanel);
				currentPanel.updateUI();
				select(6);
			}
		});
			
		userManagementButton = new MenuButton("<html><left>  User<br>  management</left></html>", new ImageIcon("images/user_management.png"));
		GridBagConstraints gbc_userManagementButton = new GridBagConstraints();
		gbc_userManagementButton.fill = GridBagConstraints.BOTH;
		gbc_userManagementButton.gridwidth = 3;
		gbc_userManagementButton.gridx = 0;
		gbc_userManagementButton.gridy = 7;
		menuPanel.add(userManagementButton, gbc_userManagementButton);
		userManagementButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(umPanel == null) {
					umPanel = new userManagementPanel();
				}
				currentPanel.removeAll();
				currentPanel.add(umPanel);
				currentPanel.updateUI();
				select(7);
			}
		});
		
		//set up menu button list(Order is important, starts from the top button in the menu vertically)
		menubuttons.add(menuButton);
		menubuttons.add(homeButton);
		menubuttons.add(browseButton);
		menubuttons.add(settingsButton);
		menubuttons.add(feedbackButton);
		menubuttons.add(profileButton);
		menubuttons.add(createQuestionButton);
		menubuttons.add(userManagementButton);
		
		//initialise on visitor view
		visitorView();
	}
	
	/**
	 * Displays the elements on screen that a regular user should be able to see/interact with.
	 */
	public void userView() {
		profileButton.setEnabled(true);
		profileButton.setVisible(true);
		userManagementButton.setEnabled(false);
		userManagementButton.setVisible(false);
		createQuestionButton.setEnabled(false);
		createQuestionButton.setVisible(false);

		
		unloggedPanel.setEnabled(false);
		unloggedPanel.setVisible(false);
		loggedPanel.setEnabled(true);
		loggedPanel.setVisible(true);
		
		Profile p = appFacadeInterface.getProfile();
		IDLabel.setText(p.getID());
		cashLabel.setText(Float.toString(p.getCash()));
		profilepicLabel.setIcon(new ImageIcon(p.getProfilepic()));
	}
	
	/**
	 * Displays the elements on screen that an admin should be able to see/interact with.
	 */
	public void adminView() {
		profileButton.setEnabled(true);
		profileButton.setVisible(true);
		userManagementButton.setEnabled(true);
		userManagementButton.setVisible(true);
		createQuestionButton.setEnabled(true);
		createQuestionButton.setVisible(true);
		
		unloggedPanel.setEnabled(false);
		unloggedPanel.setVisible(false);
		loggedPanel.setEnabled(true);
		loggedPanel.setVisible(true);
		
		Profile p = appFacadeInterface.getProfile();
		IDLabel.setText(p.getID());
		cashLabel.setText(Float.toString(p.getCash()));
		profilepicLabel.setIcon(new ImageIcon(p.getProfilepic()));
	}
	
	/**
	 * Displays the elements on screen that a logged out visitor should be able to see/interact with.
	 */
	public void visitorView() {
		profileButton.setEnabled(false);
		profileButton.setVisible(false);
		userManagementButton.setEnabled(false);
		userManagementButton.setVisible(false);
		createQuestionButton.setEnabled(false);
		createQuestionButton.setVisible(false);
		
		loggedPanel.setEnabled(false);
		loggedPanel.setVisible(false);
		unloggedPanel.setEnabled(true);
		unloggedPanel.setVisible(true);
		
		currentPanel.removeAll();
		currentPanel.updateUI();
	}
	
	public void select(int i) {
		for(JButton jb : menubuttons) {
			jb.setBackground(new Color(51,51,51));
			jb.setSelected(false);
		}
		menubuttons.get(i).setSelected(true);
		menubuttons.get(i).setBackground(new Color(105, 105, 105));
	}
	
	
}	