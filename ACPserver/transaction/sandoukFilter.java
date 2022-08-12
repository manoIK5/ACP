package transaction;

import java.awt.EventQueue;

import javax.swing.JFrame;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class sandoukFilter {

	public JFrame frmSandoukFilter;
	
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
					sandoukFilter window = new sandoukFilter();
					window.frmSandoukFilter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public sandoukFilter() {
		con = DatabaseCon.connection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSandoukFilter = new JFrame();
		frmSandoukFilter.setResizable(false);
		frmSandoukFilter.setIconImage(Toolkit.getDefaultToolkit().getImage(sandoukFilter.class.getResource("/chip.png")));
		frmSandoukFilter.setTitle("Sandouk Filter");
		frmSandoukFilter.setBounds(100, 100, 804, 368);
		frmSandoukFilter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSandoukFilter.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 799, 448);
		frmSandoukFilter.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Today");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					stmt = con.prepareStatement("SELECT * FROM `transaction` WHERE `Date` between date_sub(curdate(),interval 0 day) and curdate()");
					rs = stmt.executeQuery();
					frmSandoukFilter.setVisible(false);
					sandouk object = new sandouk();
					object.frmSandouk.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(28, 34, 185, 49);
		panel.add(btnNewButton);
		
		JButton btnThisWeek = new JButton("This Week");
		btnThisWeek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frmSandoukFilter.setVisible(false);
					stmt = con.prepareStatement("SELECT * FROM `transaction` WHERE `Date` between date_sub(curdate(),interval 7 day) and curdate()");
					rs = stmt.executeQuery();
					sandouk object = new sandouk();
					object.frmSandouk.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThisWeek.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnThisWeek.setBounds(277, 34, 185, 49);
		panel.add(btnThisWeek);
		
		JButton btnThisMonth = new JButton("This Month");
		btnThisMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frmSandoukFilter.setVisible(false);
					stmt = con.prepareStatement("SELECT * FROM `transaction` WHERE `Date` between date_sub(curdate(),interval 1 month) and curdate()");
					rs = stmt.executeQuery();
					sandouk object = new sandouk();
					object.frmSandouk.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThisMonth.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnThisMonth.setBounds(515, 34, 185, 49);
		panel.add(btnThisMonth);
		
		JButton btnThisYear = new JButton("This Year");
		btnThisYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frmSandoukFilter.setVisible(false);
					stmt = con.prepareStatement("SELECT * FROM `transaction` WHERE `Date` between date_sub(curdate(),interval 1 year) and curdate()");
					rs = stmt.executeQuery();
					sandouk object = new sandouk();
					object.frmSandouk.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnThisYear.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnThisYear.setBounds(117, 143, 185, 49);
		panel.add(btnThisYear);
		
		JButton btnAllData = new JButton("All Data");
		btnAllData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frmSandoukFilter.setVisible(false);
					stmt = con.prepareStatement("SELECT * FROM `transaction` WHERE 1");
					rs = stmt.executeQuery();
					sandouk object = new sandouk();
					object.frmSandouk.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAllData.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnAllData.setBounds(411, 143, 185, 49);
		panel.add(btnAllData);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSandoukFilter.setVisible(false);
				Home object = new Home();
				object.frmHome.setVisible(true);
			}
		});
		btnBack.setIcon(new ImageIcon(sandoukFilter.class.getResource("/back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnBack.setBounds(576, 247, 185, 49);
		panel.add(btnBack);
	}
}
