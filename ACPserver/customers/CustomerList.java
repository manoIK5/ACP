package customers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Home.Home;
import databaseCon.DatabaseCon;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class CustomerList {

	public JFrame frmCustomerList;
	private JTable table;
	
	static Connection con;
	static PreparedStatement stmt;
	static ResultSet rs; 
	
	private JButton btnNewButton;
	private JButton btnBack;
	private JButton btnDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) { 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerList window = new CustomerList();
					window.frmCustomerList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerList() {
		initialize();
		con = DatabaseCon.connection();
		getData();
	}
	
//	getting the table data through this function `getData`
	
	private void getData() {
		
		try {
			stmt = con.prepareStatement("SELECT * FROM `customer`");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				int cusId = rs.getInt("Customer ID");
				String cusName = rs.getString("First Name");
				String cusLastName = rs.getString("Last Name");
				String cusNumber = rs.getString("Telephone Number");
				
//				adding the data to the table of customers.	
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {cusId, cusName, cusLastName, cusNumber});
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
// end of function
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmCustomerList = new JFrame();
		frmCustomerList.setTitle("Customer List");
		frmCustomerList.setResizable(false);
		frmCustomerList.setIconImage(Toolkit.getDefaultToolkit().getImage(CustomerList.class.getResource("/chip.png")));
		frmCustomerList.setBounds(100, 100, 900, 600);
		frmCustomerList.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmCustomerList.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 900, 600);
		frmCustomerList.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 11, 823, 367);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] { 
				"Customer ID", "Name", "Last Name ", "Phone Number"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(0).setMinWidth(31);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);

		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Add customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCustomer addcus = new AddCustomer();
				addcus.frmAddCustomer.setVisible(true);
				frmCustomerList.setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(CustomerList.class.getResource("/about.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(38, 422, 232, 95);
		panel.add(btnNewButton);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCustomerList.setVisible(false);
				Home hm1 = new Home();
				hm1.frmHome.setVisible(true);
			}
		});
		btnBack.setIcon(new ImageIcon(CustomerList.class.getResource("/back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnBack.setBounds(629, 422, 232, 95);
		panel.add(btnBack);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int id = (int) table.getValueAt(row, 0);
				 
				try {
					stmt = con.prepareStatement("DELETE FROM customer WHERE `Customer Id` =?");
					stmt.setInt(1, id);
					
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Customer Deleted!");
					stmt.close();
					
					frmCustomerList.setVisible(false);
					CustomerList object = new CustomerList();
					object.frmCustomerList.setVisible(true);
					

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(CustomerList.class.getResource("/icons8-cancel-48.png")));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnDelete.setBounds(331, 422, 232, 95);
		panel.add(btnDelete);
		frmCustomerList.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmCustomerList, 
		            "Are you sure you want to exit this program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
	}
}
