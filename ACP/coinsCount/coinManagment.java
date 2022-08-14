package coinsCount;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

import databaseCon.DatabaseCon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import Home.Home;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class coinManagment {

	public JFrame frmCoinCountManagment;
	
	static Connection con;
	static PreparedStatement stmt;   
	static ResultSet rs; 
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					coinManagment window = new coinManagment();
					window.frmCoinCountManagment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public coinManagment() {
		con = DatabaseCon.connection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCoinCountManagment = new JFrame();
		frmCoinCountManagment.setIconImage(Toolkit.getDefaultToolkit().getImage(coinManagment.class.getResource("/chip.png")));
		frmCoinCountManagment.setTitle("Coin Count Managment");
		frmCoinCountManagment.setBounds(100, 100, 789, 547);
		frmCoinCountManagment.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmCoinCountManagment.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmCoinCountManagment, 
		            "Are you sure you want to exit this program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		frmCoinCountManagment.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 900, 600);
		frmCoinCountManagment.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Formula 1: (coin-in AM + coin-in PM) – (coin-out AM + coin-out PM)");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 99, 850, 49);
		panel.add(lblNewLabel);
		
		JLabel lblMachineNumber = new JLabel("Machine Number:");
		lblMachineNumber.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblMachineNumber.setBounds(10, 21, 220, 49);
		panel.add(lblMachineNumber);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 21));
		textField.setBounds(215, 25, 220, 40);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblFormula = new JLabel("Formula 1 = ");
		lblFormula.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblFormula.setBounds(10, 159, 220, 49);
		panel.add(lblFormula);
		
		JLabel formulaOneAnswerLBL = new JLabel("0.00");
		formulaOneAnswerLBL.setFont(new Font("Tahoma", Font.BOLD, 21));
		formulaOneAnswerLBL.setBounds(146, 159, 477, 49);
		panel.add(formulaOneAnswerLBL);
		
		JLabel lblFormulaCoinout = new JLabel("Formula 2: coin-out / coin-in ");
		lblFormulaCoinout.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblFormulaCoinout.setBounds(10, 260, 850, 49);
		panel.add(lblFormulaCoinout);
		
		JLabel lblFormula_3 = new JLabel("Formula 2 = ");
		lblFormula_3.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblFormula_3.setBounds(10, 320, 220, 49);
		panel.add(lblFormula_3);
		
		JLabel formulaTwoAnswerLBL = new JLabel("0.00");
		formulaTwoAnswerLBL.setFont(new Font("Tahoma", Font.BOLD, 21));
		formulaTwoAnswerLBL.setBounds(146, 320, 426, 49);
		panel.add(formulaTwoAnswerLBL);
		
		JButton btnNewButton = new JButton("Get Data");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int machNum = Integer.parseInt(textField.getText());
				
				try {
					stmt = con.prepareStatement("SELECT * FROM `coincount` WHERE `Machine-num` = ?");
					stmt.setInt(1, machNum);
					
					rs = stmt.executeQuery();
					double cashInAM = 0;
					double cashOutAM = 0;
					double cashInPM = 0;
					double cashOutPM = 0;
					
					while(rs.next()) {
						if (rs.getString("AM/PM").equals("AM")) {
							cashInAM += rs.getDouble("InAmount");
							cashOutAM += rs.getDouble("OutAmount");
						} else {
							cashInPM += rs.getDouble("InAmount");
							cashOutPM += rs.getDouble("OutAmount");
						}
						
					}
					
					double formulaOneAnswer =   (cashInAM + cashInPM) - (cashOutAM + cashOutPM);
					formulaOneAnswerLBL.setText(String.valueOf(formulaOneAnswer));
					
					double formulaTwoAnswer =   (cashOutAM + cashOutPM) / (cashInAM + cashInPM);
					formulaTwoAnswerLBL.setText(String.valueOf(formulaTwoAnswer));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(coinManagment.class.getResource("/loupe.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(10, 422, 209, 56);
		panel.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCoinCountManagment.setVisible(false);
				Home object = new Home();
				object.frmHome.setVisible(true);
			}
		});
		btnBack.setIcon(new ImageIcon(coinManagment.class.getResource("/back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnBack.setBounds(551, 422, 209, 56);
		panel.add(btnBack);
	}
}
