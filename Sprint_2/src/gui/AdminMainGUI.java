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

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class AdminMainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private User user; //logged user
	
	private JPanel jContentPane = null;
	private JButton JButtonCheckProfile = null;
	private JButton jButtonQueryQueries = null;
	private JButton JButtonManageUsers = null;
	private JButton JButtonCreateQueries = null;
	private JButton  btnLogOut = null;
	private JButton  btnRegister = null; 
	private static BLFacade appFacadeInterface;

	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}

	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelTitle;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel logoLabel;
	private JLabel selectOptLabel;
	private JLabel lblAccount;
	private JLabel lblCash;
	private JLabel IDLabel;
	private JLabel incomeLabel;

	/**
	 * This is the default constructor
	 */
	public AdminMainGUI(User u) {
		super();
		user = u;
		setResizable(false);

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
		this.setBounds(600, 200, 519, 486);
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
			
			JLabel logoLabel = null;
			
			jContentPane.setLayout(null);
			jContentPane.add(getPanel());
			jContentPane.add(getBoton5());
			jContentPane.add(getBoton4());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getjLabelTitle());
			jContentPane.add(getLogoLabel());
			jContentPane.add(getSelectOptLabel());
			jContentPane.add(getLblAccount());
			jContentPane.add(getLblCash());
			jContentPane.add(getIDLabel());
			jContentPane.add(getIncomeLabel());
			
			JLabel adminLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AdminMainGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
			adminLabel.setFont(new Font("Source Sans Pro Black", Font.ITALIC, 14));
			adminLabel.setForeground(Color.RED);
			adminLabel.setBounds(160, 66, 125, 42);
			jContentPane.add(adminLabel);
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (JButtonCheckProfile == null) {
			JButtonCheckProfile = new JButton();
			JButtonCheckProfile.setBounds(255, 295, 228, 62);
			JButtonCheckProfile.setText(ResourceBundle.getBundle("Etiquetas").getString("CheckProfile")); //$NON-NLS-1$ //$NON-NLS-2$
			JButtonCheckProfile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			JButtonCheckProfile.setEnabled(true);
			if (JButtonCheckProfile.isEnabled()) {
				JButtonCheckProfile.setText(ResourceBundle.getBundle("Etiquetas").getString("CheckProfile"));
				JButtonCheckProfile.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						BLFacade facade=MainGUI.getBusinessLogic();
						//Vector<Event> events=facade.getAllEvents();
						JDialog a = new UserProfileGUI(user);
						a.setVisible(true);
					}
				});
			}

		}
		return JButtonCheckProfile;
	}

	/**
	 * This method initializes boton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(255, 222, 228, 62);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries")); //$NON-NLS-1$ //$NON-NLS-2$
			jButtonQueryQueries.setEnabled(true);
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JDialog a = new UserFindQuestionsGUI(user);
					a.setVisible(true);
					incomeLabel.setText(user.getCash() +"€");
				}
			});
		}
		return jButtonQueryQueries;
	}

	/**
	 * This method initializes boton4
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton4() {
		if (JButtonCreateQueries == null) {
			JButtonCreateQueries = new JButton();
			JButtonCreateQueries.setBounds(22, 222, 223, 62);
			JButtonCreateQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery")); //$NON-NLS-1$ //$NON-NLS-2$
			JButtonCreateQueries.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			JButtonCreateQueries.setEnabled(true);
			if (JButtonCreateQueries.isEnabled()) {
				JButtonCreateQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
				JButtonCreateQueries.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						BLFacade facade=MainGUI.getBusinessLogic();
						//Vector<Event> events=facade.getAllEvents();
						JDialog a = new CreateQuestionGUI(new Vector<Event>());
						a.setVisible(true);
					}
				});
			}

		}
		return JButtonCreateQueries;
	}
	
	/**
	 * This method initializes boton5
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton5() {
		if (JButtonManageUsers == null) {
			JButtonManageUsers = new JButton();
			JButtonManageUsers.setBounds(22, 295, 223, 62);
			JButtonManageUsers.setText(ResourceBundle.getBundle("Etiquetas").getString("ManageUsers")); //$NON-NLS-1$ //$NON-NLS-2$
			JButtonManageUsers.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JDialog a = new UserManagementGUI();

					a.setVisible(true);
				}
			});
		}
		return JButtonManageUsers;
	}
	



	private JLabel getjLabelTitle() {
		if (jLabelTitle == null) {
			jLabelTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BetAndRuin")); //$NON-NLS-1$ //$NON-NLS-2$
			jLabelTitle.setBounds(10, 29, 322, 62);
			jLabelTitle.setFont(new Font("Source Sans Pro Black", Font.BOLD, 25));
			jLabelTitle.setForeground(new Color(0, 0, 0));
			jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelTitle;
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
					AdminMainGUI.super.dispose();
				}
			});
			btnLogOut.setBounds(36, 36, 117, 25);
		}
		return btnLogOut;
	}
	
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(22, 378, 457, 62);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
			panel.add(getBtnLogOut());
		}
		return panel;
	}

	private void redibujar() {
		selectOptLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		JButtonCheckProfile.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	private JLabel getLogoLabel() {
		if (logoLabel == null) {
			try {
				logoLabel = new JLabel(new ImageIcon(ImageIO.read(new File("images/beticon.png"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
			logoLabel.setBounds(241, -16, 199, 160);
		}
		return logoLabel;
	}
	private JLabel getSelectOptLabel() {
		if (selectOptLabel == null) {
			selectOptLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption")); //$NON-NLS-1$ //$NON-NLS-2$
			selectOptLabel.setHorizontalAlignment(SwingConstants.CENTER);
			selectOptLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			selectOptLabel.setBounds(90, 182, 322, 24);
		}
		return selectOptLabel;
	}
	private JLabel getLblAccount() {
		if (lblAccount == null) {
			lblAccount = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AdminMainGUI.lblAccount.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblAccount.setBounds(32, 130, 56, 14);
		}
		return lblAccount;
	}
	private JLabel getLblCash() {
		if (lblCash == null) {
			lblCash = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AdminMainGUI.lblAvailableIncome.text")); //$NON-NLS-1$ //$NON-NLS-2$
			lblCash.setBounds(32, 155, 56, 14);
		}
		return lblCash;
	}
	private JLabel getIDLabel() {
		if (IDLabel == null) {
			IDLabel = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
			IDLabel.setBounds(90, 130, 141, 14);
		}
		return IDLabel;
	}
	private JLabel getIncomeLabel() {
		if (incomeLabel == null) {
			incomeLabel = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
			incomeLabel.setBounds(90, 155, 141, 14);
		}
		return incomeLabel;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

