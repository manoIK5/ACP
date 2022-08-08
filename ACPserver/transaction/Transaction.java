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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import Home.Home;
import databaseCon.DatabaseCon;

import javax.swing.ImageIcon;

public class Transaction {

	public JFrame frmTransaction;
	private JTextField machNumInput;
	private JTextField cashInInput;
	private JTextField bonusInput;
	private JTextField cashOutInput;
	private JTextField extraBounusInput;
	private JTextField FraisInput;
	private JTextField PrizeInput;
	private JTextField welcomeInput;
	
	public static int cusID;
	
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTransaction = new JFrame();
		frmTransaction.setIconImage(Toolkit.getDefaultToolkit().getImage(Transaction.class.getResource("/chip.png")));
		frmTransaction.setResizable(false);
		frmTransaction.setTitle("Transaction");
		frmTransaction.setBounds(100, 100, 900, 600);
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
		panel.setBounds(10, 0, 900, 600);
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
		
		JButton btnNewButton = new JButton("Select cusomer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SelectCustomer object = new SelectCustomer();
				object.frmSelectCustomer.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(221, 103, 207, 49);
		panel.add(btnNewButton);
		
		machNumInput = new JTextField();
		machNumInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		machNumInput.setBounds(221, 11, 185, 49);
		panel.add(machNumInput);
		machNumInput.setColumns(10);
		
		JLabel lblCashIn = new JLabel("Cash In:");
		lblCashIn.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblCashIn.setBounds(10, 199, 184, 43);
		panel.add(lblCashIn);
		
		JLabel lblBonus = new JLabel("Bonus:");
		lblBonus.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblBonus.setBounds(10, 281, 184, 43);
		panel.add(lblBonus);
		
		JLabel lblCashOut = new JLabel("Cash Out:");
		lblCashOut.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblCashOut.setBounds(10, 371, 184, 43);
		panel.add(lblCashOut);
		
		JLabel lblExtraBonus = new JLabel("Extra Bonus:");
		lblExtraBonus.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblExtraBonus.setBounds(458, 11, 184, 43);
		panel.add(lblExtraBonus);
		
		JLabel lblFrais = new JLabel("Frais:");
		lblFrais.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblFrais.setBounds(458, 106, 184, 43);
		panel.add(lblFrais);
		
		JLabel lblPrize = new JLabel("Prize:");
		lblPrize.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblPrize.setBounds(458, 199, 184, 43);
		panel.add(lblPrize);
		
		JLabel lblWelcome = new JLabel("Welcome:");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblWelcome.setBounds(458, 292, 184, 43);
		panel.add(lblWelcome);
		
		cashInInput = new JTextField();
		cashInInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		cashInInput.setColumns(10);
		cashInInput.setBounds(221, 194, 185, 43);
		panel.add(cashInInput);
		
		bonusInput = new JTextField();
		bonusInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		bonusInput.setColumns(10);
		bonusInput.setBounds(221, 287, 185, 43);
		panel.add(bonusInput);
		
		cashOutInput = new JTextField();
		cashOutInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		cashOutInput.setColumns(10);
		cashOutInput.setBounds(221, 377, 185, 43);
		panel.add(cashOutInput);
		
		extraBounusInput = new JTextField();
		extraBounusInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		extraBounusInput.setColumns(10);
		extraBounusInput.setBounds(624, 11, 185, 49);
		panel.add(extraBounusInput);
		
		FraisInput = new JTextField();
		FraisInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		FraisInput.setColumns(10);
		FraisInput.setBounds(624, 106, 185, 49);
		panel.add(FraisInput);
		
		PrizeInput = new JTextField();
		PrizeInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		PrizeInput.setColumns(10);
		PrizeInput.setBounds(624, 199, 185, 49);
		panel.add(PrizeInput);
		
		welcomeInput = new JTextField();
		welcomeInput.setFont(new Font("Tahoma", Font.PLAIN, 21));
		welcomeInput.setColumns(10);
		welcomeInput.setBounds(624, 289, 185, 49);
		panel.add(welcomeInput);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					stmt = con.prepareStatement("INSERT INTO `transaction`(`MachineNum`, `CustomerId`, `CashIN`, `Bonus`, `CashOut`, `Extra Bonus`, `Frais`, `Prize`, `Welcome`) VALUES (?,?,?,?,?,?,?,?,?)");
					stmt.setInt(1, Integer.parseInt(machNumInput.getText()));
					stmt.setInt(2, cusID);
					
					if (cashInInput.getText().equals("")) {
						stmt.setDouble(3, 0.00);
					} else {
						stmt.setDouble(3, Double.parseDouble(cashInInput.getText()));
					}
					
					if (bonusInput.getText().equals("")) {
						stmt.setDouble(4, 0.00);
					} else {
						stmt.setDouble(4, Double.parseDouble(bonusInput.getText()));
					}
					
					if (cashOutInput.getText().equals("")) {
						stmt.setDouble(5, 0.00);
					} else {
						stmt.setDouble(5, Double.parseDouble(cashOutInput.getText()));
					}
					
					if (extraBounusInput.getText().equals("")) {
						stmt.setDouble(6, 0.00);
					} else {
						stmt.setDouble(6, Double.parseDouble(extraBounusInput.getText())); 
					}
					
					if (FraisInput.getText().equals("")) {
						stmt.setDouble(7, 0.00);
					} else {
						stmt.setDouble(7, Double.parseDouble(FraisInput.getText()));
					}
					
					if (PrizeInput.getText().equals("")) {
						stmt.setDouble(8, 0.00);
					} else {
						stmt.setDouble(8, Double.parseDouble(PrizeInput.getText()));
					}
					
					if (welcomeInput.getText().equals("")) {
						stmt.setDouble(9, 0.00);
					} else {
						stmt.setDouble(9, Double.parseDouble(welcomeInput.getText()));
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
		btnNewButton_1_1.setBounds(651, 465, 196, 66);
		panel.add(btnNewButton_1_1);
	}
}
