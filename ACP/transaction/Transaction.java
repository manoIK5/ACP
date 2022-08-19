package transaction;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import Home.Home;
import databaseCon.DatabaseCon;

import javax.swing.ImageIcon;
import java.awt.Color;

public class Transaction {

	public JFrame frmTransaction;
	private JTextField machNumInput;
	private JTextField cashInInput;
	private JTextField cashOutInput;
	private JTextField extraBounusInput;
	private JTextField FraisInput;
	private JTextField PrizeInput;
	private JTextField welcomeInput;
	
	
	static Connection con;
	static PreparedStatement stmt;
	static ResultSet rs; 
	
	static PreparedStatement stmt2;
	static ResultSet rs2;
	
	private JTextField cusNameInput;
	
	double tempBounus = 0.00;
	private JTextField bonusInput;
	
	Set<String> s;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transaction window = new Transaction();
					window.frmTransaction.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Transaction() {
		con = DatabaseCon.connection();
		initialize();
		
		// the search assistant
		s = new TreeSet<String>();
		getCusNames();
	}
	
	// gets the customers name and adds them to the Sting that the search assistant gets them from
	public void getCusNames() {
		try {
			stmt = con.prepareStatement("SELECT * FROM `customer`");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				String Items = rs.getString("First Name");
				s.add(Items);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	private void checkCustomerBalance(String cusName) {
//		double customerBalance = 0.00;
//		double bonus = 0.00;
//		try {
//			stmt2 = con.prepareStatement("SELECT * FROM `transaction` WHERE `CustomerName` = ? && `Date` between date_sub(curdate(),interval 0 day) and curdate()");
//			stmt2.setString(1, cusName);
//			rs2 = stmt2.executeQuery();
//			
//			while(rs2.next()) {
//				customerBalance = rs2.getDouble("balance");
//				bonus = rs2.getDouble("bonus");
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		if(bonus > 15000) {
//			tempBounus = 0.00;
//		} else {
//			if(customerBalance == -50000) {
//				tempBounus = 5000;
//			} else if(customerBalance == -100000) {
//				tempBounus = 10000;
//			} else if(customerBalance == -150000) {
//				tempBounus = 15000;
//			}
//		}		
//		
//		try {
//			stmt2 = con.prepareStatement("INSERT INTO `transaction` ('Bonus') VALUES (?) WHERE `CustomerName` = ? && `Date` between date_sub(curdate(),interval 0 day) and curdate()");
//			stmt.setDouble(1, tempBounus);
//			stmt.setString(1, cusName);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTransaction = new JFrame();
		frmTransaction.setIconImage(Toolkit.getDefaultToolkit().getImage(Transaction.class.getResource("/chip.png")));
		frmTransaction.setTitle("Transaction");
		frmTransaction.setBounds(100, 100, 1280, 600);
		frmTransaction.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmTransaction.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmTransaction, 
		            "Are you sure you want to exit this program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		frmTransaction.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1273, 600);
		frmTransaction.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Machine Number:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 11, 184, 43);
		panel.add(lblNewLabel);
		
		JLabel lblCustomer = new JLabel("Customer:");
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblCustomer.setBounds(10, 106, 184, 43);
		panel.add(lblCustomer);
		
		machNumInput = new JTextField();
		machNumInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		machNumInput.setBounds(221, 11, 347, 49);
		panel.add(machNumInput);
		machNumInput.setColumns(10);
		
		JLabel lblCashIn = new JLabel("Cash In:");
		lblCashIn.setForeground(Color.GRAY);
		lblCashIn.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblCashIn.setBounds(10, 199, 184, 43);
		panel.add(lblCashIn);
		
		JLabel lblCashOut = new JLabel("Cash Out:");
		lblCashOut.setForeground(Color.RED);
		lblCashOut.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblCashOut.setBounds(10, 371, 184, 43);
		panel.add(lblCashOut);
		
		JLabel lblExtraBonus = new JLabel("Extra Bonus:");
		lblExtraBonus.setForeground(Color.BLUE);
		lblExtraBonus.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblExtraBonus.setBounds(617, 11, 184, 43);
		panel.add(lblExtraBonus);
		
		JLabel lblFrais = new JLabel("Frais:");
		lblFrais.setForeground(Color.PINK);
		lblFrais.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblFrais.setBounds(617, 106, 184, 43);
		panel.add(lblFrais);
		
		JLabel lblPrize = new JLabel("Prize:");
		lblPrize.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblPrize.setBounds(617, 199, 184, 43);
		panel.add(lblPrize);
		
		JLabel lblWelcome = new JLabel("Welcome:");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblWelcome.setBounds(617, 292, 184, 43);
		panel.add(lblWelcome);
		
		cashInInput = new JTextField();
		cashInInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		cashInInput.setColumns(10);
		cashInInput.setBounds(221, 194, 347, 43);
		panel.add(cashInInput);
		
		cashOutInput = new JTextField();
		cashOutInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		cashOutInput.setColumns(10);
		cashOutInput.setBounds(221, 377, 347, 43);
		panel.add(cashOutInput);
		
		extraBounusInput = new JTextField();
		extraBounusInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		extraBounusInput.setColumns(10);
		extraBounusInput.setBounds(783, 11, 356, 49);
		panel.add(extraBounusInput);
		
		FraisInput = new JTextField();
		FraisInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		FraisInput.setColumns(10);
		FraisInput.setBounds(783, 106, 356, 49);
		panel.add(FraisInput);
		
		PrizeInput = new JTextField();
		PrizeInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		PrizeInput.setColumns(10);
		PrizeInput.setBounds(783, 199, 356, 49);
		panel.add(PrizeInput);
		
		welcomeInput = new JTextField();
		welcomeInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		welcomeInput.setColumns(10);
		welcomeInput.setBounds(783, 289, 356, 49);
		panel.add(welcomeInput);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					stmt = con.prepareStatement("INSERT INTO `transaction`(`MachineNum`, `CustomerName`, `CashIN`, `CashOut`, `Extra Bonus`, `Frais`, `Prize`, `Welcome`, `bonus`) VALUES (?,?,?,?,?,?,?,?,?)");
					stmt.setInt(1, Integer.parseInt(machNumInput.getText()));
					stmt.setString(2, cusNameInput.getText());
					
					if (cashInInput.getText().equals("")) {
						stmt.setDouble(3, 0.00);
					} else {
						stmt.setDouble(3, Double.parseDouble(cashInInput.getText()+"000"));
					}
					
					if (cashOutInput.getText().equals("")) {
						stmt.setDouble(4, 0.00);
					} else {
						stmt.setDouble(4, Double.parseDouble(cashOutInput.getText()+"000"));
					}
					
					if (extraBounusInput.getText().equals("")) {
						stmt.setDouble(5, 0.00);
					} else {
						stmt.setDouble(5, Double.parseDouble(extraBounusInput.getText()+"000")); 
					}
					
					if (FraisInput.getText().equals("")) {
						stmt.setDouble(6, 0.00);
					} else {
						stmt.setDouble(6, Double.parseDouble(FraisInput.getText()+"000"));
					}
					
					if (PrizeInput.getText().equals("")) {
						stmt.setDouble(7, 0.00);
					} else {
						stmt.setDouble(7, Double.parseDouble(PrizeInput.getText()+"000"));
					}
					
					if (welcomeInput.getText().equals("")) {
						stmt.setDouble(8, 0.00);
					} else {
						stmt.setDouble(8, Double.parseDouble(welcomeInput.getText()+"000"));
					}
					
					if(bonusInput.getText().equals("")) {
						stmt.setDouble(9, 0.00);
					} else {
						stmt.setDouble(9, Double.parseDouble(bonusInput.getText()+"000"));
					}
							
					stmt.executeUpdate();

					JOptionPane.showMessageDialog(null, "Data Insertion succesfull");
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Transaction.class.getResource("/submit.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton_1.setBounds(10, 465, 196, 66);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Back");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmTransaction.setVisible(false);
				Home object = new Home();
				object.frmHome.setVisible(true);
			}
		});
		btnNewButton_1_1.setIcon(new ImageIcon(Transaction.class.getResource("/back.png")));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton_1_1.setBounds(1044, 465, 196, 66);
		panel.add(btnNewButton_1_1);
		
		cusNameInput = new JTextField();
		cusNameInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		cusNameInput.setColumns(10);
		cusNameInput.setBounds(221, 106, 347, 49);
		panel.add(cusNameInput);
		cusNameInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String to_check = cusNameInput.getText();
				int to_check_len = to_check.length();
				
				for (String data:s) {
					String check_from_data = "";
					
					for(int i=0;i<to_check_len;i++) {
						if (to_check_len<=data.length()) {
							check_from_data = check_from_data+data.charAt(i);
						}
					}
				if (check_from_data.equals(to_check)) {
					cusNameInput.setText(data);
					cusNameInput.setSelectionStart(to_check_len);	
					cusNameInput.setSelectionEnd(data.length());
					break;
				}
				}
			}
		});	
		
		bonusInput = new JTextField();
		bonusInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		bonusInput.setColumns(10);
		bonusInput.setBounds(221, 292, 347, 43);
		panel.add(bonusInput);
		
		JLabel lblBonus = new JLabel("Bonus:");
		lblBonus.setForeground(Color.GREEN);
		lblBonus.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblBonus.setBounds(10, 289, 184, 43);
		panel.add(lblBonus);
	}
}
