package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalFileChooserUI.FilterComboBoxRenderer;

import businessLogic.BLFacade;
import domain.User;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.RowSorter;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.JCheckBox;

public class UserManagementGUI extends JDialog{

	private JPanel contentPane;
	private JTextField searchField = new JTextField("");

	String searchinput;
	int searchfilter;

	private final int PAGESIZE = 15;
	List<User> searchResult;
	int currentpage;



	private JButton btnSearch = new JButton();
	private JButton btnNextPage = new JButton("Next page");
	private JButton btnPrevPage = new JButton("Previous page");
	String[] filters = { "ID", "Name", "Surname", "E-mail"};
	private JComboBox filterComboBox = new JComboBox(filters);
	JLabel showingCountLabel = new JLabel("");

	private String[] columnNamesUsers = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("ID"), 
			ResourceBundle.getBundle("Etiquetas").getString("Name"), 
			ResourceBundle.getBundle("Etiquetas").getString("Surname"), 
			ResourceBundle.getBundle("Etiquetas").getString("Email"), 
			ResourceBundle.getBundle("Etiquetas").getString("Status"), 
			"", ""
	};

	private NonEditableTableModel userTableModel = new NonEditableTableModel(null, columnNamesUsers);
	private JTable userTable;







	/** 
	 * Create the frame.
	 */	
	public UserManagementGUI()
	{
		setTitle("User management");
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception{

		setModal(true);
		setResizable(false);

		userTable = new JTable();
		userTable.setModel(userTableModel);
		userTable.getTableHeader().setReorderingAllowed(false);
		userTable.getTableHeader().setResizingAllowed(false);
		userTable.setRowHeight(25);




		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 881, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{48, 30, 30, 30, 30, 0, 20, 70, 47, 70, 20, 30, 60, 103, 20, 20, 20, 0};
		gbl_panel.rowHeights = new int[]{33, 20, 20, 0, 20, 0, 30, 30, 30, 185, 20, 30, 0, 20, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("User management");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblSearch = new JLabel("Search:");
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.anchor = GridBagConstraints.WEST;
		gbc_lblSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearch.gridx = 1;
		gbc_lblSearch.gridy = 3;
		panel.add(lblSearch, gbc_lblSearch);

		searchField = new JTextField();
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.gridwidth = 3;
		gbc_searchField.insets = new Insets(0, 0, 5, 5);
		gbc_searchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchField.gridx = 2;
		gbc_searchField.gridy = 3;
		panel.add(searchField, gbc_searchField);
		searchField.setColumns(10);


		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 5;
		gbc_btnSearch.gridy = 3;
		panel.add(btnSearch, gbc_btnSearch);
		btnSearch.setIcon(new ImageIcon(ImageIO.read(new File("images/searchicon2.png"))));


		JLabel lblSearchBy = new JLabel("Search by:");
		GridBagConstraints gbc_lblSearchBy = new GridBagConstraints();
		gbc_lblSearchBy.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearchBy.anchor = GridBagConstraints.EAST;
		gbc_lblSearchBy.gridx = 7;
		gbc_lblSearchBy.gridy = 3;
		panel.add(lblSearchBy, gbc_lblSearchBy);

		GridBagConstraints gbc_filterComboBox = new GridBagConstraints();
		gbc_filterComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_filterComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_filterComboBox.gridx = 8;
		gbc_filterComboBox.gridy = 3;
		
		JCheckBox chckbxCasesSensitive = new JCheckBox("case sensitive");
		GridBagConstraints gbc_chckbxCasesSensitive = new GridBagConstraints();
		gbc_chckbxCasesSensitive.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCasesSensitive.gridx = 9;
		gbc_chckbxCasesSensitive.gridy = 3;
		panel.add(chckbxCasesSensitive, gbc_chckbxCasesSensitive);


		GridBagConstraints gbc_showingCountLabel = new GridBagConstraints();
		gbc_showingCountLabel.gridwidth = 5;
		gbc_showingCountLabel.insets = new Insets(0, 0, 5, 5);
		gbc_showingCountLabel.gridx = 1;
		gbc_showingCountLabel.gridy = 10;
		panel.add(showingCountLabel, gbc_showingCountLabel);


		btnPrevPage.setEnabled(false);
		btnPrevPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentpage--;
				loadPage(currentpage);
				if(currentpage==1) {
					btnPrevPage.setEnabled(false);
				}
				btnNextPage.setEnabled(true);
			}
		});
		GridBagConstraints gbc_btnPrevPage = new GridBagConstraints();
		gbc_btnPrevPage.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPrevPage.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrevPage.gridx = 12;
		gbc_btnPrevPage.gridy = 10;
		panel.add(btnPrevPage, gbc_btnPrevPage);


		btnNextPage.setEnabled(false);
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentpage++;
				loadPage(currentpage);
				if((currentpage*PAGESIZE)>searchResult.size()) {
					btnNextPage.setEnabled(false);
				}
				btnPrevPage.setEnabled(true);
			}
		});
		GridBagConstraints gbc_btnNextPage = new GridBagConstraints();
		gbc_btnNextPage.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNextPage.insets = new Insets(0, 0, 5, 5);
		gbc_btnNextPage.gridx = 13;
		gbc_btnNextPage.gridy = 10;
		panel.add(btnNextPage, gbc_btnNextPage);


		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_cancelButton = new GridBagConstraints();
		gbc_cancelButton.gridwidth = 2;
		gbc_cancelButton.insets = new Insets(0, 0, 5, 5);
		gbc_cancelButton.gridx = 13;
		gbc_cancelButton.gridy = 12;
		panel.add(cancelButton, gbc_cancelButton);

		filterComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"ID", "Name", "Surname", "E-mail"}));
		panel.add(filterComboBox, gbc_filterComboBox);	

		BLFacade facade=MainGUI.getBusinessLogic();

		ActionListener searchListener =new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNextPage.setEnabled(false);
				searchinput = searchField.getText();		
				searchfilter = filterComboBox.getSelectedIndex();

				//restart model
				userTableModel.setDataVector(null, columnNamesUsers);
				userTableModel.setColumnCount(7); 

				//perform search and create the table model with the results
				searchResult=facade.searchByCriteria(searchinput,searchfilter,chckbxCasesSensitive.isSelected());		
				int entries = loadPage(1);
				if(searchResult.size() > PAGESIZE){
					btnNextPage.setEnabled(true);
				}
				currentpage = 1;
			}
		};
		btnSearch.addActionListener(searchListener);
		searchListener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));

		JButton btnAddAUser = new JButton("Add a user");
		btnAddAUser.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				RegisterGUI j = new RegisterGUI(true);
				j.setVisible(true);
				searchListener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,null));
			}
		});
		GridBagConstraints gbc_btnAddAUser = new GridBagConstraints();
		gbc_btnAddAUser.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddAUser.gridx = 13;
		gbc_btnAddAUser.gridy = 3;
		panel.add(btnAddAUser, gbc_btnAddAUser);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.gridwidth = 14;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		panel.add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(userTable);
		
		
	}


	//Actions for the Edit and Delete buttons on the table
	BLFacade facade=MainGUI.getBusinessLogic();
	Action delete = new AbstractAction()
	{
		public void actionPerformed(ActionEvent e)
		{
			String username = (String)userTableModel.getValueAt(userTable.getSelectedRow(), 0);

			int confirmed = JOptionPane.showConfirmDialog(getParent(), "Delete the user " + username + "?" , "Confirm deletion", JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION);
			if (confirmed==0){
				int index = (currentpage-1)*PAGESIZE;
				searchResult.remove(index+userTable.getSelectedRow());
				facade.removeUser((String)userTable.getValueAt(userTable.getSelectedRow(), 0));

				if((searchResult.size()-index)==0) { //if we deleted the last element of the page
					if(currentpage==1) { 
						loadPage(currentpage);
					}
					else {  
						currentpage--;
						loadPage(currentpage);
						btnNextPage.setEnabled(false);
						if(currentpage==1) {
							btnPrevPage.setEnabled(false);
						}
					}
				}
				else { 
					loadPage(currentpage);
					if(searchResult.size()<=(currentpage*PAGESIZE)) {//if the current page has become the last page
						btnNextPage.setEnabled(false);
					}
				}
			}

		}
		
	};

	Action edit = new AbstractAction()
	{
		public void actionPerformed(ActionEvent e)
		{
			int row = userTable.getSelectedRow();
			EditUserGUI j = new EditUserGUI((String)userTable.getValueAt(row, 0), (String)userTable.getValueAt(row, 1), 
					(String)userTable.getValueAt(row, 2),(String)userTable.getValueAt(row, 3), (String)userTable.getValueAt(row, 4));
			j.setVisible(true);

			//update row with new info
			String[] newData = j.newData();
			userTable.setValueAt(newData[0], row, 0);
			userTable.setValueAt(newData[1], row, 1);
			userTable.setValueAt(newData[2], row, 2);
			userTable.setValueAt(newData[3], row, 3);
			userTable.setValueAt(newData[4], row, 4);
		}
	};


	/**
	 * Loads a batch of up to PAGESIZE users found via search on the table.
	 * @param pageNumber    number of page to load.
	 * @return				number of elements in the current page.
	 */
	public int loadPage(int pageNumber) {
		userTableModel.setDataVector(null, columnNamesUsers);
		userTableModel.setColumnCount(7); 
		int elementsOnPage = 0;
		int index = (pageNumber-1)*PAGESIZE;
		int remainingelements = searchResult.size() - index;
		while(elementsOnPage<PAGESIZE && elementsOnPage<remainingelements) {
			addUserToTable(searchResult.get(index+elementsOnPage));
			elementsOnPage++;
		}

		if(remainingelements==0) {
			showingCountLabel.setText("Showing " + 0 + " to " + 0 + " of " + 0);
		}
		else {
			showingCountLabel.setText("Showing " + (index+1) + " to " + (index+elementsOnPage) + " of " + searchResult.size());
		}

		userTable.getColumnModel().getColumn(0).setPreferredWidth(75);
		userTable.getColumnModel().getColumn(1).setPreferredWidth(75);
		userTable.getColumnModel().getColumn(2).setPreferredWidth(75);
		userTable.getColumnModel().getColumn(3).setPreferredWidth(160);
		userTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		userTable.getColumnModel().getColumn(5).setPreferredWidth(20);
		userTable.getColumnModel().getColumn(6).setPreferredWidth(20);

		ButtonColumn editButtonColumn = new ButtonColumn(userTable, edit, 5);
		ButtonColumn deleteButtonColumn = new ButtonColumn(userTable, delete, 6);
		editButtonColumn.setMnemonic(KeyEvent.VK_E);
		deleteButtonColumn.setMnemonic(KeyEvent.VK_D);

		//Table sorting settings (edit and delete button collums sorting disabled)
		TableRowSorter<NonEditableTableModel> sorter = new TableRowSorter<NonEditableTableModel>(userTableModel);
		sorter.setSortable(5, false);
		sorter.setSortable(6, false);
		userTable.setRowSorter(sorter);
		
		return elementsOnPage;
	}

	/**
	 * Sorts the current page based on the selected criteria.
	 * 
	 * @param index
	 * @param criteria
	 */
	public void sortPage(int index, int criteria) {
		switch(criteria) {
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		default:

			break;
		}
	}	


	/**
	 * Creates a new row in the search table with a User's data.
	 * @param u		User to insert.
	 */
	public void addUserToTable(User u) {
		Vector<Object> row = new Vector<Object>();
		row.add(u.getID());
		row.add(u.getName());
		row.add(u.getSurname());
		row.add(u.getEmail());
		row.add(u.statusToString());
		try {
			row.add(new ImageIcon(ImageIO.read(new File("images/edit1.png"))));
			row.add(new ImageIcon(ImageIO.read(new File("images/delete.png"))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		userTableModel.addRow(row);		
	}

	/**
	 * Auxiliary class for making table model elements non editable
	 *
	 */
	public class NonEditableTableModel extends DefaultTableModel
	{	private static final long serialVersionUID = 1L;

	public NonEditableTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	public boolean isCellEditable (int row, int column)
	{
		if(column == 5 || column==6) {
			return true;
		}
		return false;
	}
	}


}
