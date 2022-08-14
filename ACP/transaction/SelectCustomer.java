package transaction;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;

import databaseCon.DatabaseCon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class SelectCustomer {

	public JFrame frmSelectCustomer;
	private final JPanel panel = new JPanel();
	
	static Connection con;
	static PreparedStatement stmt;
	static ResultSet rs; 
	
	private JTable table;
	private JButton btnNewButton;
	private JButton btnCancel;
	private JTextField searchInput;
	
	Set<String> s;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectCustomer window = new SelectCustomer();
					window.frmSelectCustomer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// function that gets the data
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
				
				String Items = rs.getString("First Name");
				s.add(Items);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// the following function gets the search results of the search input
	public void getSearchResualts(String SearchVariable){
		try {
			stmt = con.prepareStatement("SELECT * FROM `customer` WHERE `First Name` = ?");
			stmt.setString(1, SearchVariable);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				int cusId = rs.getInt("Customer ID");
				String cusName = rs.getString("First Name");
				String cusLastName = rs.getString("Last Name");
				String cusNumber = rs.getString("Telephone Number");
				
//				adding the data to the table of customers.	
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {cusId, cusName, cusLastName, cusNumber});
				
				String Items = rs.getString("First Name");
				s.add(Items);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	/**
	 * Create the application.
	 */
	public SelectCustomer() {
		con = DatabaseCon.connection();
		initialize();
		// creating the search list variable
		s = new TreeSet<String>();
		getData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmSelectCustomer = new JFrame();
		frmSelectCustomer.setResizable(false);
		frmSelectCustomer.setIconImage(Toolkit.getDefaultToolkit().getImage(SelectCustomer.class.getResource("/chip.png")));
		frmSelectCustomer.setTitle("Select Customer");
		frmSelectCustomer.setBounds(100, 100, 770, 600);
		frmSelectCustomer.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmSelectCustomer.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        frmSelectCustomer.setVisible(false);
		    }
		});
		frmSelectCustomer.getContentPane().setLayout(null);
		panel.setBounds(0, 0, 900, 600);
		frmSelectCustomer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 727, 428);
		panel.add(scrollPane);
		
		table = new JTable();
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
		
		btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// getting the id number
//				int row = table.getSelectedRow(); 
//				int id = (int) table.getValueAt(row, 0);
				
//				setting the public variable of the transaction frame to the above received variable and closing the frame
//				Transaction.cusID = id;
				frmSelectCustomer.setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(SelectCustomer.class.getResource("/submit.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(10, 475, 188, 68);
		panel.add(btnNewButton);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSelectCustomer.setVisible(false);
			}
		});
		btnCancel.setIcon(new ImageIcon(SelectCustomer.class.getResource("/icons8-cancel-48.png")));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnCancel.setBounds(559, 475, 188, 68);
		panel.add(btnCancel);
		
		searchInput = new JTextField();
		searchInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		searchInput.setBounds(246, 481, 197, 57);
		panel.add(searchInput);
		searchInput.setColumns(10);
		
		searchInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String to_check = searchInput.getText();
				int to_check_len = to_check.length();
				
				for (String data:s) {
					String check_from_data = "";
					
					for(int i=0;i<to_check_len;i++) {
						if (to_check_len<=data.length()) {
							check_from_data = check_from_data+data.charAt(i);
						}
					}
				if (check_from_data.equals(to_check)) {
					searchInput.setText(data);
					searchInput.setSelectionStart(to_check_len);	
					searchInput.setSelectionEnd(data.length());
					break;
				}
				}
			}
		});	
		
		searchInput.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	
			    	DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			    	dtm.setRowCount(0);
			    	
			    	String SearchVariable = searchInput.getText();
			    	getSearchResualts(SearchVariable);

			    }};		
		});
		
		
	}
}
