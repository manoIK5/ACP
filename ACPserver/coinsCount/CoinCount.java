package coinsCount;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Home.Home;
import databaseCon.DatabaseCon;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CoinCount {

	public JFrame frmCoinCount;
	private JTextField machineNumInput;
	private JTextField InAmountAmInput;
	private JTextField outAmountAmInput;
	
	static Connection con;
	static PreparedStatement stmt;
	static ResultSet rs; 
	private JTextField inAmountPmInput;
	private JTextField outAmountPmInput;

	/**
	 * Launch the application.
	 */ 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoinCount window = new CoinCount();
					window.frmCoinCount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CoinCount() {
		con = DatabaseCon.connection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCoinCount = new JFrame();
		frmCoinCount.setTitle("Coin Count");
		frmCoinCount.setIconImage(Toolkit.getDefaultToolkit().getImage(CoinCount.class.getResource("/chip.png")));
		frmCoinCount.setBounds(100, 100, 900, 600);
		frmCoinCount.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmCoinCount.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmCoinCount, 
		            "Are you sure you want to exit this program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		frmCoinCount.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 900, 600);
		frmCoinCount.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Submit");
		
		JLabel lblNewLabel = new JLabel("Shift:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(36, 29, 175, 58);
		panel.add(lblNewLabel);
		
		JLabel lblMachineNumber = new JLabel("Machine Number:");
		lblMachineNumber.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblMachineNumber.setBounds(36, 136, 195, 58);
		panel.add(lblMachineNumber);
		
		machineNumInput = new JTextField();
		machineNumInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		machineNumInput.setBounds(232, 136, 158, 47);
		panel.add(machineNumInput);
		machineNumInput.setColumns(10);
		machineNumInput.setText("1");
		
		JLabel lblInAmount = new JLabel("In Amount:");
		lblInAmount.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblInAmount.setBounds(36, 244, 195, 58);
		panel.add(lblInAmount);
		
		JLabel lblOutAmount = new JLabel("Out Amount:");
		lblOutAmount.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblOutAmount.setBounds(36, 359, 195, 58);
		panel.add(lblOutAmount);
		
		InAmountAmInput = new JTextField();
		InAmountAmInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		InAmountAmInput.setColumns(10);
		InAmountAmInput.setBounds(232, 255, 158, 47);
		panel.add(InAmountAmInput);
		
		outAmountAmInput = new JTextField();
		outAmountAmInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		outAmountAmInput.setColumns(10);
		outAmountAmInput.setBounds(232, 359, 158, 47);
		panel.add(outAmountAmInput);
		outAmountAmInput.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	btnNewButton.doClick();
			    }};		
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				Double inAmount;
				Double outAmount;
				 
				try {
					
//					checks if the am inputs are null, if not it puts them into the database
					if( !(InAmountAmInput.getText().equals("") && outAmountAmInput.getText().equals("") )) {
						stmt = con.prepareStatement("INSERT INTO `coincount`(`AM/PM`, `Machine-num`, `InAmount`, `OutAmount`) VALUES (?, ?, ?, ?)");
						stmt.setString(1, "AM");
						stmt.setInt(2, Integer.parseInt(machineNumInput.getText()));
						
						
						if (InAmountAmInput.getText().equals("")) {
							inAmount = 0.00;
						} else {
							inAmount = Double.parseDouble(InAmountAmInput.getText());
						}
						
						if (outAmountAmInput.getText().equals("")) {
							outAmount = 0.00;
						} else {
							outAmount = Double.parseDouble(outAmountAmInput.getText());
						}
						
						stmt.setDouble(3, inAmount);
						stmt.setDouble(4, outAmount);
						
//						Inserting the data
						stmt.executeUpdate();
						stmt.close();
					} 
//					checks if the pm inputs are null, if not it puts them in the database
					if( !(inAmountPmInput.getText().equals("") && outAmountPmInput.getText().equals("")) ) {
						stmt = con.prepareStatement("INSERT INTO `coincount`(`AM/PM`, `Machine-num`, `InAmount`, `OutAmount`) VALUES (?, ?, ?, ?)");
						stmt.setString(1, "PM");
						stmt.setInt(2, Integer.parseInt(machineNumInput.getText()));
						
						
						if (inAmountPmInput.getText().equals("")) {
							inAmount = 0.00;
						} else {
							inAmount = Double.parseDouble(inAmountPmInput.getText());
						}
						
						if (outAmountPmInput.getText().equals("")) {
							outAmount = 0.00;
						} else {
							outAmount = Double.parseDouble(outAmountPmInput.getText());
						}
						
						stmt.setDouble(3, inAmount);
						stmt.setDouble(4, outAmount);
						
//						Inserting the data
						stmt.executeUpdate();
						stmt.close();
					}
							
					JOptionPane.showMessageDialog(null, "Data insertion is succesful");

					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				int numReached = Integer.parseInt(machineNumInput.getText());
				machineNumInput.setText(String.valueOf(numReached+1));
				InAmountAmInput.setText("");
				outAmountAmInput.setText("");
				inAmountPmInput.setText("");
				outAmountPmInput.setText("");
				InAmountAmInput.requestFocus();
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(CoinCount.class.getResource("/icons8-login-48.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(36, 451, 251, 58);
		panel.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCoinCount.setVisible(false);
				Home hom = new Home();
				hom.frmHome.setVisible(true);
			}
		});
		btnBack.setIcon(new ImageIcon(CoinCount.class.getResource("/back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnBack.setBounds(565, 451, 251, 58);
		panel.add(btnBack);
		
		JLabel lblAm = new JLabel("AM");
		lblAm.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblAm.setBounds(231, 29, 175, 58);
		panel.add(lblAm);
		
		JLabel lblPm = new JLabel("PM");
		lblPm.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblPm.setBounds(544, 29, 175, 58);
		panel.add(lblPm);
		
		inAmountPmInput = new JTextField();
		inAmountPmInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		inAmountPmInput.setColumns(10);
		inAmountPmInput.setBounds(531, 255, 158, 47);
		panel.add(inAmountPmInput);
		
		outAmountPmInput = new JTextField();
		outAmountPmInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		outAmountPmInput.setColumns(10);
		outAmountPmInput.setBounds(531, 359, 158, 47);
		panel.add(outAmountPmInput);
	}
}
