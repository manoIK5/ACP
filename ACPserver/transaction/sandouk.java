package transaction;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import databaseCon.DatabaseCon;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class sandouk {

	public JFrame frmSandouk;
	private JTable table;
	ResultSet  rs;
	private JLabel lblNewLabel;
	private JLabel tltCash;
	private JButton btnNewButton;
	private JButton btnBack;
	
	static Connection con;
	static PreparedStatement stmt;
	static ResultSet rs2; 
	
	double CGT = 0.00;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sandouk window = new sandouk();
					window.frmSandouk.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
	} 

	/**
	 * Create the application.
	 */
	public sandouk() {
		rs = sandoukFilter.rs;
		con = DatabaseCon.connection();
		initialize();
		getStartingCash();
		getData();
	}
	
	// the following function gets the cash given in the morning from the database
	public void getStartingCash() {
		try {
			stmt = con.prepareStatement("SELECT * FROM `mrt` WHERE `date` between date_sub(curdate(),interval 0 day) and curdate()");
			rs2 = stmt.executeQuery();
			while(rs2.next()) {
				CGT = rs2.getDouble("amountRecivied");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getData() {
		Double tltCashInSandouk = CGT;
		try {
			while(rs.next()) {
				Double inamount = rs.getDouble("cashIn");
				Double outAmount = rs.getDouble("cashOut");
				Double sandouk = inamount - outAmount;
				tltCashInSandouk += sandouk;
				tltCash.setText(String.valueOf(tltCashInSandouk));
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {inamount, outAmount, sandouk});
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSandouk = new JFrame();
		frmSandouk.setTitle("Sandouk");
		frmSandouk.setIconImage(Toolkit.getDefaultToolkit().getImage(sandouk.class.getResource("/chip.png")));
		frmSandouk.setBounds(100, 100, 1280, 900);
		frmSandouk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSandouk.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1280, 824);
		frmSandouk.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1249, 570);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD, 19));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Total In", "Total Out", "Sandouk"
			}
		));
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("Total cash In SandouK:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 592, 258, 51);
		panel.add(lblNewLabel);
		
		tltCash = new JLabel("0.00");
		tltCash.setFont(new Font("Tahoma", Font.BOLD, 21));
		tltCash.setBounds(285, 592, 258, 51);
		panel.add(tltCash);
		
		btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSandouk.setVisible(false);
				sandouk object = new sandouk();
				object.frmSandouk.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(sandouk.class.getResource("/icons8-login-48.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(10, 654, 206, 58);
		panel.add(btnNewButton);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSandouk.setVisible(false);
				sandoukFilter object = new sandoukFilter();
				object.frmSandoukFilter.setVisible(true);
			}
		});
		btnBack.setIcon(new ImageIcon(sandouk.class.getResource("/back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnBack.setBounds(363, 654, 206, 58);
		panel.add(btnBack);
	}
}
