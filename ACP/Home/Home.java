package Home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;

import customers.CustomerList;
import databaseCon.DatabaseCon;
import login.Login;
import transaction.Transaction;
import transaction.TransactionReports;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Home {

	public JFrame frmHome;
	private JTextField textField;
	
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
					Home window = new Home();
					window.frmHome.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {	
		con = DatabaseCon.connection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHome = new JFrame();
		frmHome.setIconImage(Toolkit.getDefaultToolkit().getImage(CustomerList.class.getResource("/chip.png")));
		frmHome.setTitle("Home");
		frmHome.setBounds(100, 100, 1024, 662);
		frmHome.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmHome.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmHome, 
		            "Are you sure you want to exit this program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		frmHome.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1050, 600);
		frmHome.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Customer List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerList cusFrame = new CustomerList();
				cusFrame.frmCustomerList.setVisible(true);
				frmHome.setVisible(false);
			}
		}); 
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(138, 105, 380, 67);
		panel.add(btnNewButton);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				
				Login.frmLogin.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnLogOut.setBounds(829, 397, 176, 55);
		panel.add(btnLogOut);
		
		JButton btnTransaction = new JButton("Transaction");
		btnTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				Transaction object = new Transaction();
				object.frmTransaction.setVisible(true);
			}
		});
		btnTransaction.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnTransaction.setBounds(138, 11, 380, 67);
		panel.add(btnTransaction);
		
		JButton btnTransactionReports = new JButton("Transaction Reports");
		btnTransactionReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				TransactionReports object = new TransactionReports();
				object.frmTransactionReports.setVisible(true);
			}
		});
		btnTransactionReports.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnTransactionReports.setBounds(10, 221, 462, 67);
		panel.add(btnTransactionReports);
		
		JLabel lblActions = new JLabel("Actions:");
		lblActions.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblActions.setBounds(10, 11, 248, 55);
		panel.add(lblActions);
		
		JLabel lblCustomers = new JLabel("Customers:");
		lblCustomers.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblCustomers.setBounds(10, 111, 248, 55);
		panel.add(lblCustomers);
		
		JLabel lblMoneyRecievedToday = new JLabel("Money recieved today:");
		lblMoneyRecievedToday.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblMoneyRecievedToday.setBounds(10, 323, 276, 67);
		panel.add(lblMoneyRecievedToday);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setBounds(250, 334, 268, 52);
		panel.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	if(Double.parseDouble(textField.getText()) <0) {
						JOptionPane.showMessageDialog(null, "Cannot insert negative number");
			    	} else {
			    		try {
							stmt = con.prepareStatement("INSERT INTO `mrt`(`amountRecivied`) VALUES (?)");
							stmt.setDouble(1, Double.parseDouble(textField.getText()));
							stmt.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Data Insertion succesfull");
						textField.setText("");
			    	}
			    	

			    }};		
		});
	}
}
