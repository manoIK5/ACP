package coinsCount;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Home.Home;
import databaseCon.DatabaseCon;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CoinCount {

	public JFrame frmCoinCount;
	private JTextField machineNumInput;
	private JTextField InAmountInput;
	private JTextField outAmountInput;
	
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frmCoinCount = new JFrame();
		frmCoinCount.setTitle("Coin Count");
		frmCoinCount.setIconImage(Toolkit.getDefaultToolkit().getImage(CoinCount.class.getResource("/chip.png")));
		frmCoinCount.setResizable(false);
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 21));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"AM", "PM"}));
		comboBox.setBounds(232, 35, 76, 47);
		panel.add(comboBox);
		
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
		
		JLabel lblInAmount = new JLabel("In Amount:");
		lblInAmount.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblInAmount.setBounds(36, 244, 195, 58);
		panel.add(lblInAmount);
		
		JLabel lblOutAmount = new JLabel("Out Amount:");
		lblOutAmount.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblOutAmount.setBounds(36, 359, 195, 58);
		panel.add(lblOutAmount);
		
		InAmountInput = new JTextField();
		InAmountInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		InAmountInput.setColumns(10);
		InAmountInput.setBounds(232, 255, 158, 47);
		panel.add(InAmountInput);
		
		outAmountInput = new JTextField();
		outAmountInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		outAmountInput.setColumns(10);
		outAmountInput.setBounds(232, 359, 158, 47);
		panel.add(outAmountInput);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				Double inAmount;
				Double outAmount;
				 
				try {
					stmt = con.prepareStatement("INSERT INTO `coincount`(`AM/PM`, `Machine-num`, `InAmount`, `OutAmount`) VALUES (?, ?, ?, ?)");
					stmt.setString(1, (String) comboBox.getSelectedItem());
					stmt.setInt(2, Integer.parseInt(machineNumInput.getText()));
					
					
					if (InAmountInput.getText().equals("")) {
						inAmount = 0.00;
					} else {
						inAmount = Double.parseDouble(InAmountInput.getText());
					}
					
					if (outAmountInput.getText().equals("")) {
						outAmount = 0.00;
					} else {
						outAmount = Double.parseDouble(outAmountInput.getText());
					}
					
					stmt.setDouble(3, inAmount);
					stmt.setDouble(4, outAmount);
					
//					Inserting the data
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data insertion is succesful");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
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
	}
}
