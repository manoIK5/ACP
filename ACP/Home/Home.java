package Home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;

import coinsCount.CoinCount;
import customers.CustomerList;
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
		
		JButton btnCoinsCount = new JButton("Coins Count");
		btnCoinsCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				CoinCount coinCount1 = new CoinCount();
				coinCount1.frmCoinCount.setVisible(true);
			}
		});
		btnCoinsCount.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnCoinsCount.setBounds(541, 11, 464, 67);
		panel.add(btnCoinsCount);
		
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
		btnTransactionReports.setBounds(10, 391, 462, 67);
		panel.add(btnTransactionReports);
		
		JLabel lblActions = new JLabel("Actions:");
		lblActions.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblActions.setBounds(10, 11, 248, 55);
		panel.add(lblActions);
		
		JLabel lblCustomers = new JLabel("Customers:");
		lblCustomers.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblCustomers.setBounds(10, 111, 248, 55);
		panel.add(lblCustomers);
	}
}
