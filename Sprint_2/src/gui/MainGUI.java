package gui;

import javax.imageio.ImageIO;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.invalidID;
import exceptions.invalidPW;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;
	private JButton  btnLogIn = null;
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
	private JLabel titleLabel;
	private JLabel logoLabel;

	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		this.setResizable(false);
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
		this.setBounds(600, 200, 467, 431);
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
			gbl_jContentPane.columnWidths = new int[]{36, 21, 0, 109, 61, 119, 10, 0, 21, 0};
			gbl_jContentPane.rowHeights = new int[]{-4, 27, 0, 25, 36, 57, 57, 17, 31, 10, 10, 0};
			gbl_jContentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_jContentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			jContentPane.setLayout(gbl_jContentPane);
			GridBagConstraints gbc_titleLabel = new GridBagConstraints();
			gbc_titleLabel.gridwidth = 2;
			gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
			gbc_titleLabel.gridx = 3;
			gbc_titleLabel.gridy = 2;
			jContentPane.add(getTitleLabel(), gbc_titleLabel);
			GridBagConstraints gbc_logoLabel = new GridBagConstraints();
			gbc_logoLabel.insets = new Insets(0, 0, 5, 5);
			gbc_logoLabel.gridx = 5;
			gbc_logoLabel.gridy = 2;
			jContentPane.add(getLabel_1(), gbc_logoLabel);
			GridBagConstraints gbc_jLabelSelectOption = new GridBagConstraints();
			gbc_jLabelSelectOption.gridwidth = 6;
			gbc_jLabelSelectOption.fill = GridBagConstraints.VERTICAL;
			gbc_jLabelSelectOption.insets = new Insets(0, 0, 5, 5);
			gbc_jLabelSelectOption.gridx = 2;
			gbc_jLabelSelectOption.gridy = 4;
			jContentPane.add(getLblSelectOption(), gbc_jLabelSelectOption);
			GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
			gbc_btnLogIn.fill = GridBagConstraints.BOTH;
			gbc_btnLogIn.gridwidth = 3;
			gbc_btnLogIn.insets = new Insets(0, 0, 5, 5);
			gbc_btnLogIn.gridx = 2;
			gbc_btnLogIn.gridy = 5;
			jContentPane.add(getBtnLogIn(), gbc_btnLogIn);
			GridBagConstraints gbc_btnRegister = new GridBagConstraints();
			gbc_btnRegister.fill = GridBagConstraints.BOTH;
			gbc_btnRegister.gridwidth = 3;
			gbc_btnRegister.insets = new Insets(0, 0, 5, 5);
			gbc_btnRegister.gridx = 5;
			gbc_btnRegister.gridy = 5;
			jContentPane.add(getBtnRegister(), gbc_btnRegister);
			GridBagConstraints gbc_jButtonQueryQueries = new GridBagConstraints();
			gbc_jButtonQueryQueries.gridwidth = 6;
			gbc_jButtonQueryQueries.fill = GridBagConstraints.BOTH;
			gbc_jButtonQueryQueries.insets = new Insets(0, 0, 5, 5);
			gbc_jButtonQueryQueries.gridx = 2;
			gbc_jButtonQueryQueries.gridy = 6;
			jContentPane.add(getBoton3(), gbc_jButtonQueryQueries);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 4;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.VERTICAL;
			gbc_panel.gridx = 3;
			gbc_panel.gridy = 8;
			jContentPane.add(getPanel(), gbc_panel);
		}
		return jContentPane;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JDialog a = new FindQuestionsGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}


	private JLabel getLblSelectOption() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 16));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setBounds(212, 5, 59, 23);
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
			rdbtnNewRadioButton_1.setBounds(64, 5, 63, 23);
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
			rdbtnNewRadioButton_2.setBounds(132, 5, 75, 23);
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
		btnLogIn.setEnabled(false);
		btnLogIn.setVisible(false);
		btnRegister.setEnabled(false);
		btnRegister.setVisible(false);
	}
	
	private JButton getBtnLogIn() {
		if (btnLogIn == null) {
			btnLogIn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LogIn")); //$NON-NLS-1$ //$NON-NLS-2$
			btnLogIn.setBounds(276, 5, 61, 23);
			btnLogIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame user = new UserLoginGUI();
					dispose();
					user.setVisible(true);
					user.setResizable(false);
				}
			});
		}
		return btnLogIn;
	}
	private JButton getBtnRegister() {
		if (btnRegister == null) {
			btnRegister = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register")); //$NON-NLS-1$ //$NON-NLS-2$
			btnRegister.setBounds(342, 5, 73, 23);
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JDialog login = new RegisterGUI(false);
					login.setVisible(true);
				}
			});
		}
		return btnRegister; 
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}

	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	private JLabel getTitleLabel() {
		if (titleLabel == null) {
			titleLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BetAndRuin")); //$NON-NLS-1$ //$NON-NLS-2$
			titleLabel.setFont(new Font("Source Sans Pro Black", Font.BOLD, 25));
		}
		return titleLabel;
	}
	private JLabel getLabel_1() {
		if (logoLabel == null) {
			try {
				logoLabel = new JLabel(new ImageIcon(ImageIO.read(new File("images/beticon.png"))));//
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return logoLabel;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

