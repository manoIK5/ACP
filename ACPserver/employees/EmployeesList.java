package employees;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

import databaseCon.DatabaseCon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Home.Home;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeesList {

	public JFrame frmEmployeesList;
	
	static Connection con;
	static PreparedStatement stmt;
	static ResultSet rs; 
	
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeesList window = new EmployeesList();
					window.frmEmployeesList.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeesList() {
		con = DatabaseCon.connection();
		initialize();
		getData();
	}
	
	// The function that gets the data
	
	public void getData() {
		
		try {
			stmt = con.prepareStatement("SELECT * FROM `employees`");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				int empId = rs.getInt("Employee-id");
				String empName = rs.getString("username");
				String empPassword = rs.getString("password");
				String empRole = rs.getString("Role");
				
//				adding the data to the table of customers.	
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {empId, empName, empPassword, empRole});
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmEmployeesList = new JFrame();
		frmEmployeesList.setIconImage(Toolkit.getDefaultToolkit().getImage(EmployeesList.class.getResource("/chip.png")));
		frmEmployeesList.setResizable(false);
		frmEmployeesList.setTitle("Employees List");
		frmEmployeesList.setBounds(100, 100, 900, 600);
		frmEmployeesList.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmEmployeesList.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmEmployeesList, 
		            "Are you sure you want to exit this program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		frmEmployeesList.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 900, 600);
		frmEmployeesList.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 838, 390);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee Id", "Employee Name", "Password", "Role"
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
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);
		 
		JButton btnNewButton = new JButton("Add Employee");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEmployeesList.setVisible(false);
				AddEmployee object = new AddEmployee();
				object.frmAddEmployee.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(EmployeesList.class.getResource("/about.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(37, 455, 229, 65);
		panel.add(btnNewButton);
		
		JButton btnDeleteEmployee = new JButton("Delete Employee");
		btnDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int id = (int) table.getValueAt(row, 0);
				 
				try {
					stmt = con.prepareStatement("DELETE FROM employees WHERE `Employee-id` =?");
					stmt.setInt(1, id);
					
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Employee Deleted!");
					stmt.close();
					
					frmEmployeesList.setVisible(false);
					EmployeesList object = new EmployeesList();
					object.frmEmployeesList.setVisible(true);
					

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDeleteEmployee.setIcon(new ImageIcon(EmployeesList.class.getResource("/icons8-cancel-48.png")));
		btnDeleteEmployee.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnDeleteEmployee.setBounds(317, 455, 266, 65);
		panel.add(btnDeleteEmployee);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmEmployeesList.setVisible(false);
				Home object = new Home();
				object.frmHome.setVisible(true);
			}
		});
		btnBack.setIcon(new ImageIcon(EmployeesList.class.getResource("/back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnBack.setBounds(641, 455, 207, 65);
		panel.add(btnBack);
	}
}
