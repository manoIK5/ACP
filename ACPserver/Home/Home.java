package Home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;

import coinsCount.CoinCount;
import coinsCount.CoinCountReport;
import coinsCount.coinManagment;
import customers.CustomerList;
import employees.EmployeesList;
import login.Login;
import transaction.Transaction;
import transaction.TransactionReports;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Home {

	public JFrame frmHome;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHome = new JFrame();
		frmHome.setIconImage(Toolkit.getDefaultToolkit().getImage(CustomerList.class.getResource("/chip.png")));
		frmHome.setTitle("Home");
		frmHome.setResizable(false);
		frmHome.setBounds(100, 100, 723, 600);
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
		panel.setBounds(0, 0, 900, 600);
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
		btnNewButton.setBounds(138, 291, 258, 67);
		panel.add(btnNewButton);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				
				Login.frmLogin.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnLogOut.setBounds(10, 488, 176, 55);
		panel.add(btnLogOut);
		
		JButton btnCoinsCount = new JButton("Coins Count");
		btnCoinsCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				CoinCount coinCount1 = new CoinCount();
				coinCount1.frmCoinCount.setVisible(true);
			}
		});
		btnCoinsCount.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnCoinsCount.setBounds(429, 191, 258, 67);
		panel.add(btnCoinsCount);
		
		JButton btnManageEmployees = new JButton("Manage Employees");
		btnManageEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				EmployeesList object = new EmployeesList();
				object.frmEmployeesList.setVisible(true);
			}
		});
		btnManageEmployees.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnManageEmployees.setBounds(138, 391, 258, 67);
		panel.add(btnManageEmployees);
		
		JButton btnTransaction = new JButton("Transaction");
		btnTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				Transaction object = new Transaction();
				object.frmTransaction.setVisible(true);
			}
		});
		btnTransaction.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnTransaction.setBounds(138, 191, 258, 67);
		panel.add(btnTransaction);
		
		JButton btnCoinCountReports = new JButton("Coin Count Reports");
		btnCoinCountReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				CoinCountReport object = new CoinCountReport();
				object.frmCoinCountReport.setVisible(true);
			}
		});
		btnCoinCountReports.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnCoinCountReports.setBounds(138, 17, 258, 67);
		panel.add(btnCoinCountReports);
		
		JButton btnTransactionReports = new JButton("Transaction Reports");
		btnTransactionReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				TransactionReports object = new TransactionReports();
				object.frmTransactionReports.setVisible(true);
			}
		});
		btnTransactionReports.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnTransactionReports.setBounds(429, 17, 258, 67);
		panel.add(btnTransactionReports);
		
		JLabel lblReports = new JLabel("Reports:");
		lblReports.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblReports.setBounds(10, 23, 248, 55);
		panel.add(lblReports);
		
		JLabel lblActions = new JLabel("Actions:");
		lblActions.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblActions.setBounds(10, 191, 248, 55);
		panel.add(lblActions);
		
		JLabel lblCustomers = new JLabel("Customers:");
		lblCustomers.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblCustomers.setBounds(10, 291, 248, 55);
		panel.add(lblCustomers);
		
		JLabel lblEmploees = new JLabel("Employees:");
		lblEmploees.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblEmploees.setBounds(10, 391, 248, 55);
		panel.add(lblEmploees);
		
		JButton btnCoinCountManagment = new JButton("Coin Count Managment");
		btnCoinCountManagment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				coinManagment object = new coinManagment();
				object.frmCoinCountManagment.setVisible(true);
			}
		});
		btnCoinCountManagment.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnCoinCountManagment.setBounds(138, 105, 549, 67);
		panel.add(btnCoinCountManagment);
	}
}
