package customers;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import databaseCon.DatabaseCon;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddCustomer {

	public JFrame frmAddCustomer;
	private JTextField nameInput;
	private JTextField lastInput;
	private JTextField NumberInput;
	
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
					AddCustomer window = new AddCustomer();
					window.frmAddCustomer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddCustomer() {
		con = DatabaseCon.connection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame. 
	 */
	private void initialize() {
		frmAddCustomer = new JFrame();
		frmAddCustomer.setResizable(false);
		frmAddCustomer.setIconImage(Toolkit.getDefaultToolkit().getImage(AddCustomer.class.getResource("/chip.png")));
		frmAddCustomer.setTitle("Add Customer");
		frmAddCustomer.setBounds(100, 100, 900, 600);
		frmAddCustomer.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAddCustomer.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmAddCustomer, 
		            "Are you sure you want to exit this program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		frmAddCustomer.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 900, 600);
		frmAddCustomer.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(48, 41, 223, 83);
		panel.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblLastName.setBounds(48, 164, 223, 83);
		panel.add(lblLastName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblPhoneNumber.setBounds(48, 280, 223, 83);
		panel.add(lblPhoneNumber);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAddCustomer.setVisible(false);
				CustomerList cusList = new CustomerList();
				cusList.frmCustomerList.setVisible(true);
			}
		});
		btnBack.setIcon(new ImageIcon(AddCustomer.class.getResource("/back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnBack.setBounds(523, 419, 253, 95);
		panel.add(btnBack);
		
		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					stmt = con.prepareStatement("INSERT INTO `customer`(`First Name`, `Last Name`, `Telephone Number`) VALUES (?, ?, ?)");
					stmt.setString(1, nameInput.getText());
					stmt.setString(2, lastInput.getText());
					stmt.setString(3, NumberInput.getText());
					
//					inserting the data and showing a message if successful
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data insertion is succesful");
					
					
//					Closing the frame and opening the new one
					frmAddCustomer.setVisible(false);
					CustomerList cuslist = new CustomerList();
					cuslist.frmCustomerList.setVisible(true); 
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAddCustomer.setIcon(new ImageIcon(AddCustomer.class.getResource("/about.png")));
		btnAddCustomer.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnAddCustomer.setBounds(48, 419, 253, 95);
		panel.add(btnAddCustomer);
		
		nameInput = new JTextField();
		nameInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		nameInput.setBounds(325, 58, 245, 60);
		panel.add(nameInput);
		nameInput.setColumns(10);
		
		lastInput = new JTextField();
		lastInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		lastInput.setColumns(10);
		lastInput.setBounds(325, 181, 245, 60);
		panel.add(lastInput);
		
		NumberInput = new JTextField();
		NumberInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		NumberInput.setColumns(10);
		NumberInput.setBounds(325, 303, 245, 60);
		panel.add(NumberInput);
	}
}
