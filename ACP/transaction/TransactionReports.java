package transaction;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

import Home.Home;
import databaseCon.DatabaseCon;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransactionReports {

	public JFrame frmTransactionReports;
	
	static Connection con;
	static PreparedStatement stmt;
	public static ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionReports window = new TransactionReports();
					window.frmTransactionReports.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TransactionReports() {
		con = DatabaseCon.connection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTransactionReports = new JFrame();
		frmTransactionReports.setResizable(false);
		frmTransactionReports.setIconImage(Toolkit.getDefaultToolkit().getImage(TransactionReports.class.getResource("/chip.png")));
		frmTransactionReports.setTitle("Transaction Reports");
		frmTransactionReports.setBounds(100, 100, 900, 600);
		frmTransactionReports.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmTransactionReports.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmTransactionReports, 
		            "Are you sure you want to exit this program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		frmTransactionReports.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 900, 600);
		frmTransactionReports.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Today");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						stmt = con.prepareStatement("SELECT * FROM `transaction` WHERE `Date` between date_sub(curdate(),interval 0 day) and curdate()");
						rs = stmt.executeQuery();
						
						frmTransactionReports.setVisible(false);
						ShowTransactionReports object = new ShowTransactionReports();
						object.frmTransactionReports.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(30, 52, 203, 76);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("This year\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						stmt = con.prepareStatement("SELECT * FROM `transaction` WHERE `Date` between date_sub(curdate(),interval 1 year) and curdate()");
						rs = stmt.executeQuery();
						
						frmTransactionReports.setVisible(false);
						ShowTransactionReports object = new ShowTransactionReports();
						object.frmTransactionReports.setVisible(true);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton_1.setBounds(146, 235, 203, 76);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("This week");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						stmt = con.prepareStatement("SELECT * FROM `transaction` WHERE `Date` between date_sub(curdate(),interval 7 day) and curdate()");
						rs = stmt.executeQuery();
						
						frmTransactionReports.setVisible(false);
						ShowTransactionReports object = new ShowTransactionReports();
						object.frmTransactionReports.setVisible(true);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				

			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton_2.setBounds(356, 52, 203, 76);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("This Month");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						stmt = con.prepareStatement("SELECT * FROM `transaction` WHERE `Date` between date_sub(curdate(),interval 1 month) and curdate()");
						rs = stmt.executeQuery();
						
						frmTransactionReports.setVisible(false);
						ShowTransactionReports object = new ShowTransactionReports();
						object.frmTransactionReports.setVisible(true);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton_3.setBounds(671, 52, 203, 76);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("All Data");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try {
						stmt = con.prepareStatement("SELECT * FROM `transaction` WHERE 1");
						rs = stmt.executeQuery();
						
						frmTransactionReports.setVisible(false);
						ShowTransactionReports object = new ShowTransactionReports();
						object.frmTransactionReports.setVisible(true);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton_4.setBounds(539, 235, 203, 76);
		panel.add(btnNewButton_4);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmTransactionReports.setVisible(false);
				Home object = new Home();
				object.frmHome.setVisible(true);
			}
		});
		btnBack.setIcon(new ImageIcon(TransactionReports.class.getResource("/back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnBack.setBounds(658, 474, 216, 76);
		panel.add(btnBack);
	}
}
