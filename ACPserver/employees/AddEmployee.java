package employees;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import databaseCon.DatabaseCon;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddEmployee {

	public JFrame frmAddEmployee;
	private JTextField textField;
	private JPasswordField passwordField;
	
	static Connection con;
	static PreparedStatement stmt;
	static ResultSet rs; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee window = new AddEmployee();
					window.frmAddEmployee.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddEmployee() {
		con = DatabaseCon.connection(); 
		initialize(); 
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmAddEmployee = new JFrame();
		frmAddEmployee.setResizable(false);
		frmAddEmployee.setIconImage(Toolkit.getDefaultToolkit().getImage(AddEmployee.class.getResource("/chip.png")));
		frmAddEmployee.setTitle("Add Employee");
		frmAddEmployee.setBounds(100, 100, 548, 451);
		frmAddEmployee.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAddEmployee.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmAddEmployee, 
		            "Are you sure you want to exit this program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		frmAddEmployee.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 900, 600);
		frmAddEmployee.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(24, 35, 210, 52);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblPassword.setBounds(24, 122, 210, 52);
		panel.add(lblPassword);
		
		JLabel lblRole = new JLabel("Role:");
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblRole.setBounds(24, 209, 210, 52);
		panel.add(lblRole);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 21));
		textField.setBounds(244, 35, 247, 52);
		panel.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 21));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"User", "Admin"}));
		comboBox.setBounds(246, 209, 121, 52);
		panel.add(comboBox);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 21));
		passwordField.setBounds(244, 122, 247, 52);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					stmt = con.prepareStatement("INSERT INTO `employees`(`username`, `password`, `Role`) VALUES (?, ?, ?) ");
					stmt.setString(1, textField.getText());
					stmt.setString(2, passwordField.getText());
					stmt.setString(3, (String) comboBox.getSelectedItem());
					
//					inserting the data and showing a message if successful
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data insertion is succesful");
					
					
//					Closing the frame and opening the new one
					frmAddEmployee.setVisible(false);
					EmployeesList object = new EmployeesList();
					object.frmEmployeesList.setVisible(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(AddEmployee.class.getResource("/submit.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(24, 330, 171, 61);
		panel.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAddEmployee.setVisible(false);
				EmployeesList object = new EmployeesList();
				object.frmEmployeesList.setVisible(true);
			}
		});
		btnBack.setIcon(new ImageIcon(AddEmployee.class.getResource("/back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnBack.setBounds(320, 330, 171, 61);
		panel.add(btnBack);
	}
}
