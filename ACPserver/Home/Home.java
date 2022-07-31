package Home;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;

import coinsCount.CoinCount;
import customers.CustomerList;
import employees.EmployeesList;
import login.Login;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frmHome.setBounds(100, 100, 900, 600);
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
		btnNewButton.setBounds(23, 32, 203, 55);
		panel.add(btnNewButton);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmHome.setVisible(false);
				
				Login.frmLogin.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnLogOut.setBounds(691, 486, 176, 55);
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
		btnCoinsCount.setBounds(23, 142, 203, 55);
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
		btnManageEmployees.setBounds(598, 32, 258, 55);
		panel.add(btnManageEmployees);
	}
}
