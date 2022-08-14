package Home;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import customers.CustomerList;
import databaseCon.DatabaseCon;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class showDeleted {

	public JFrame frmShowDelted;
	private JTable table;
	
	static Connection con;
	static PreparedStatement stmt;   
	static ResultSet rs; 
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showDeleted window = new showDeleted();
					window.frmShowDelted.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public showDeleted() {
		con = DatabaseCon.connection();
		initialize();
		getData();
	}
	
	private void getData() {
		try {
			stmt = con.prepareStatement("SELECT * FROM `deleted`");
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {rs.getString("employeeName"), rs.getDate("Date"), rs.getDouble("cashIn"), rs.getDouble("cashOut"), rs.getDouble("bonus")});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShowDelted = new JFrame();
		frmShowDelted.setTitle("Show Deleted");
		frmShowDelted.setIconImage(Toolkit.getDefaultToolkit().getImage(CustomerList.class.getResource("/chip.png")));
		frmShowDelted.setBounds(100, 100, 1386, 612);
		frmShowDelted.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShowDelted.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1422, 900);
		frmShowDelted.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 11, 1310, 441);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee Name", "Date", "Cash In", "Cash Out", "Bonus"
			}
		));
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmShowDelted.setVisible(false);
				Home object = new Home();
				object.frmHome.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(20, 485, 208, 51);
		panel.add(btnNewButton);
	}
}
