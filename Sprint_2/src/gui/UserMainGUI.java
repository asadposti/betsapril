package gui;

import javax.imageio.ImageIO;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.User;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class UserMainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private User user; //logged user
	
	private JPanel jContentPane = null;
	private JButton jButtonCheckProfile = null;
	private JButton jButtonQueryQueries = null;
	private JButton  btnLogOut = null;
	private JButton  btnRegister = null; 
	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblLogolabel;
	private JLabel lblTitlelabel;
	private JLabel accountLabel;
	private JLabel lblCash;
	private JLabel IDLabel;
	private JLabel incomeLabel;

	/**
	 * This is the default constructor
	 */
	public UserMainGUI(User u) {
		super();
		this.user = u;
		getIDLabel().setText(u.getID());
		getIncomeLabel().setText(u.getCash() + "€");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setBounds(600, 200, 474, 408);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			GridBagLayout gbl_jContentPane = new GridBagLayout();
			gbl_jContentPane.columnWidths = new int[]{40, 10, 20, 30, 94, 16, 0, 39, 61, 20, 10, 0};
			gbl_jContentPane.rowHeights = new int[]{20, 26, 0, 18, 50, 0, 0, 21, 13, 67, 15, 27, 15, 0};
			gbl_jContentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_jContentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			jContentPane.setLayout(gbl_jContentPane);
			GridBagConstraints gbc_lblTitlelabel = new GridBagConstraints();
			gbc_lblTitlelabel.anchor = GridBagConstraints.EAST;
			gbc_lblTitlelabel.gridheight = 2;
			gbc_lblTitlelabel.gridwidth = 4;
			gbc_lblTitlelabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblTitlelabel.gridx = 1;
			gbc_lblTitlelabel.gridy = 2;
			jContentPane.add(getLblTitlelabel(), gbc_lblTitlelabel);
			GridBagConstraints gbc_lblLogolabel = new GridBagConstraints();
			gbc_lblLogolabel.gridheight = 4;
			gbc_lblLogolabel.gridwidth = 4;
			gbc_lblLogolabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblLogolabel.gridx = 5;
			gbc_lblLogolabel.gridy = 1;
			jContentPane.add(getLblLogolabel(), gbc_lblLogolabel);
			GridBagConstraints gbc_accountLabel = new GridBagConstraints();
			gbc_accountLabel.gridwidth = 2;
			gbc_accountLabel.anchor = GridBagConstraints.WEST;
			gbc_accountLabel.insets = new Insets(0, 0, 5, 5);
			gbc_accountLabel.gridx = 1;
			gbc_accountLabel.gridy = 5;
			jContentPane.add(getAccountLabel(), gbc_accountLabel);
			GridBagConstraints gbc_IDLabel = new GridBagConstraints();
			gbc_IDLabel.gridwidth = 2;
			gbc_IDLabel.anchor = GridBagConstraints.WEST;
			gbc_IDLabel.insets = new Insets(0, 0, 5, 5);
			gbc_IDLabel.gridx = 3;
			gbc_IDLabel.gridy = 5;
			jContentPane.add(getIDLabel(), gbc_IDLabel);
			GridBagConstraints gbc_lblCash = new GridBagConstraints();
			gbc_lblCash.gridwidth = 2;
			gbc_lblCash.anchor = GridBagConstraints.WEST;
			gbc_lblCash.insets = new Insets(0, 0, 5, 5);
			gbc_lblCash.gridx = 1;
			gbc_lblCash.gridy = 6;
			jContentPane.add(getCashLabel(), gbc_lblCash);
			GridBagConstraints gbc_incomeLabel = new GridBagConstraints();
			gbc_incomeLabel.gridwidth = 2;
			gbc_incomeLabel.anchor = GridBagConstraints.WEST;
			gbc_incomeLabel.insets = new Insets(0, 0, 5, 5);
			gbc_incomeLabel.gridx = 3;
			gbc_incomeLabel.gridy = 6;
			jContentPane.add(getIncomeLabel(), gbc_incomeLabel);
			GridBagConstraints gbc_jLabelSelectOption = new GridBagConstraints();
			gbc_jLabelSelectOption.gridheight = 2;
			gbc_jLabelSelectOption.gridwidth = 8;
			gbc_jLabelSelectOption.fill = GridBagConstraints.BOTH;
			gbc_jLabelSelectOption.insets = new Insets(0, 0, 5, 5);
			gbc_jLabelSelectOption.gridx = 2;
			gbc_jLabelSelectOption.gridy = 7;
			jContentPane.add(getLbSelectOption(), gbc_jLabelSelectOption);
			GridBagConstraints gbc_jButtonQueryQueries = new GridBagConstraints();
			gbc_jButtonQueryQueries.gridwidth = 4;
			gbc_jButtonQueryQueries.fill = GridBagConstraints.BOTH;
			gbc_jButtonQueryQueries.insets = new Insets(0, 0, 5, 5);
			gbc_jButtonQueryQueries.gridx = 1;
			gbc_jButtonQueryQueries.gridy = 9;
			jContentPane.add(getBoton3(), gbc_jButtonQueryQueries);
			GridBagConstraints gbc_jButtonCheckProfile = new GridBagConstraints();
			gbc_jButtonCheckProfile.gridwidth = 4;
			gbc_jButtonCheckProfile.fill = GridBagConstraints.BOTH;
			gbc_jButtonCheckProfile.insets = new Insets(0, 0, 5, 5);
			gbc_jButtonCheckProfile.gridx = 6;
			gbc_jButtonCheckProfile.gridy = 9;
			jContentPane.add(getBoton2(), gbc_jButtonCheckProfile);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 7;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 2;
			gbc_panel.gridy = 11;
			jContentPane.add(getPanel(), gbc_panel);
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCheckProfile == null) {
			jButtonCheckProfile = new JButton();
			jButtonCheckProfile.setText(ResourceBundle.getBundle("Etiquetas").getString("CheckProfile")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonCheckProfile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			jButtonCheckProfile.setEnabled(true);
			if (jButtonCheckProfile.isEnabled()) {
				jButtonCheckProfile.setText(ResourceBundle.getBundle("Etiquetas").getString("CheckProfile"));
				jButtonCheckProfile.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						BLFacade facade=MainGUI.getBusinessLogic();
						//Vector<Event> events=facade.getAllEvents();
						JDialog a = new UserProfileGUI(user);
						a.setVisible(true);
					}
				});
			}

		}
		return jButtonCheckProfile;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JDialog a = new UserFindQuestionsGUI(user);
					a.setVisible(true);
					incomeLabel.setText(user.getCash()+"€");
				}
			});
		}
		return jButtonQueryQueries;
	}


	private JLabel getLbSelectOption() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	
	public  void hideLogInRegister() {
		btnLogOut.setEnabled(false);
		btnLogOut.setVisible(false);
		btnRegister.setEnabled(false);
		btnRegister.setVisible(false);
	}
	
	private JButton getBtnLogOut() {
		if (btnLogOut == null) {
			btnLogOut = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LogOut")); //$NON-NLS-1$ //$NON-NLS-2$
			btnLogOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame user = new MainGUI();
					user.setVisible(true);
					dispose();
				}
			});
			btnLogOut.setBounds(36, 36, 117, 25);
		}
		return btnLogOut;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
			panel.add(getBtnLogOut());
		}
		return panel;
	}

	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCheckProfile.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	private JLabel getLblLogolabel() {
		if (lblLogolabel == null) {
			try {
				lblLogolabel = new JLabel(new ImageIcon(ImageIO.read(new File("images/beticon.png"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lblLogolabel;
	}
	private JLabel getLblTitlelabel() {
		if (lblTitlelabel == null) {
			lblTitlelabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BetAndRuin")); //$NON-NLS-1$ //$NON-NLS-2$
			lblTitlelabel.setFont(new Font("Source Sans Pro Black", Font.BOLD, 25));
			lblTitlelabel.setForeground(new Color(0, 0, 0));
		}
		return lblTitlelabel;
	}
	private JLabel getAccountLabel() {
		if (accountLabel == null) {
			accountLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Account")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return accountLabel;
	}
	private JLabel getCashLabel() {
		if (lblCash == null) {
			lblCash = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserMainGUI.lblCash.text")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return lblCash;
	}
	private JLabel getIDLabel() {
		if (IDLabel == null) {
			IDLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserMainGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return IDLabel;
	}
	private JLabel getIncomeLabel() {
		if (incomeLabel == null) {
			incomeLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserMainGUI.incomeLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return incomeLabel;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

