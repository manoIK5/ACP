package coinsCount;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import Home.Home;
import databaseCon.DatabaseCon;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CoinCountReport {

	public JFrame frmCoinCountReport;
	private JTextField machNumInput;
	
	static Connection con;
	static PreparedStatement stmt;
	public static ResultSet rs; 
	
	static String dateSpecified = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoinCountReport window = new CoinCountReport();
					window.frmCoinCountReport.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CoinCountReport() {
		con = DatabaseCon.connection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frmCoinCountReport = new JFrame();
		frmCoinCountReport.setResizable(false);
		frmCoinCountReport.setIconImage(Toolkit.getDefaultToolkit().getImage(CoinCountReport.class.getResource("/chip.png")));
		frmCoinCountReport.setTitle("Coin Count Report");
		frmCoinCountReport.setBounds(100, 100, 900, 600);
		frmCoinCountReport.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmCoinCountReport.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmCoinCountReport, 
		            "Are you sure you want to exit this program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		frmCoinCountReport.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 900, 600);
		frmCoinCountReport.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Filter Coin Count Reorts By Date:\r\n");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 11, 361, 52);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Today:");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateSpecified = "between date_sub(curdate(),interval 0 day) and curdate()";
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(10, 74, 176, 52);
		panel.add(btnNewButton);
		
		JButton btnThisWeek = new JButton("This Week:");
		btnThisWeek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateSpecified = "between date_sub(curdate(),interval 7 day) and curdate()";
			}
		});
		btnThisWeek.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnThisWeek.setBounds(262, 74, 176, 52);
		panel.add(btnThisWeek);
		
		JButton btnThisMonth = new JButton("This Month:");
		btnThisMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateSpecified = "between date_sub(curdate(),interval 1 month) and curdate()";
			}
		});
		btnThisMonth.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnThisMonth.setBounds(482, 74, 176, 52);
		panel.add(btnThisMonth);
		
		JButton btnThisYear = new JButton("This Year:");
		btnThisYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateSpecified = "between date_sub(curdate(),interval 1 year) and curdate()";
			}
		});
		btnThisYear.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnThisYear.setBounds(10, 166, 176, 52);
		panel.add(btnThisYear);
		
		JButton btnAllData = new JButton("All Data:");
		btnAllData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateSpecified = "all";
			}
		});
		btnAllData.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnAllData.setBounds(262, 166, 176, 52);
		panel.add(btnAllData);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"All", "AM", "PM"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 21));
		comboBox.setBounds(381, 267, 113, 46);
		panel.add(comboBox);
		
		machNumInput = new JTextField();
		machNumInput.setFont(new Font("Tahoma", Font.BOLD, 21));
		machNumInput.setBounds(512, 353, 247, 52);
		panel.add(machNumInput);
		machNumInput.setColumns(10);
		
		JLabel lblFilterCoinCount = new JLabel("Filter Coin Count Reorts By Shift:");
		lblFilterCoinCount.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblFilterCoinCount.setBounds(10, 264, 361, 52);
		panel.add(lblFilterCoinCount);
		
		JLabel lblFilterCoinCount_2 = new JLabel("Filter Coin Count Reorts By Machine Number:");
		lblFilterCoinCount_2.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblFilterCoinCount_2.setBounds(10, 353, 512, 52);
		panel.add(lblFilterCoinCount_2);
		
		JButton btnGetData = new JButton("Get Data");
		btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
//					checks for the date
					if(dateSpecified.equals("all")) {
						stmt = con.prepareStatement("SELECT * FROM `coincount`");
						// checks for the date
						if(comboBox.getSelectedItem().equals("All")) {
							stmt = con.prepareStatement("SELECT * FROM `coincount`");
//							checks for the machine number		
							if(machNumInput.getText().equals("")) {
								stmt = con.prepareStatement("SELECT * FROM `coincount`");
//							third else AKA machine number else
							} else {
								stmt = con.prepareStatement("SELECT * FROM `coincount` WHERE `Machine-num` = ?");
								stmt.setInt(1, Integer.parseInt(machNumInput.getText()));
							}
							
						// Second else AKA as shift else
						} else {
							if(machNumInput.getText().equals("")) {
								stmt = con.prepareStatement("SELECT * FROM `coincount` WHERE `AM/PM` = ?");
								stmt.setString(1, (String) comboBox.getSelectedItem());
							} else {
								stmt = con.prepareStatement("SELECT * FROM `coincount` WHERE `Machine-num` = ? && `AM/PM` = ?");
								stmt.setInt(1, Integer.parseInt(machNumInput.getText()));
								stmt.setString(2, (String) comboBox.getSelectedItem());
							}
						}
						
					// the following else is the first else AKA the time else
					} else {
							if((comboBox.getSelectedItem().equals("All"))) {
								if(machNumInput.getText().equals("")) {
									stmt = con.prepareStatement("SELECT * FROM `coincount` WHERE `Date` " + dateSpecified);
									System.out.println(stmt);
								} else {
									stmt = con.prepareStatement("SELECT * FROM `coincount` WHERE `Date` " + dateSpecified + " && `Machine-num` = ?");
									stmt.setInt(1, Integer.parseInt(machNumInput.getText()));
								}
							} else {
								if (machNumInput.getText().equals("")) {
									stmt = con.prepareStatement("SELECT * FROM `coincount` WHERE `Date` " + dateSpecified + " && `AM/PM` = ?");
									stmt.setString(1, (String) comboBox.getSelectedItem());
									System.out.println(stmt);
								} else {
									stmt = con.prepareStatement("SELECT * FROM `coincount` WHERE `Date` " + dateSpecified + " && `AM/PM` = ? && `Machine-num` = ?");
									stmt.setString(1, (String) comboBox.getSelectedItem());
									stmt.setInt(2, Integer.parseInt(machNumInput.getText()));
									System.out.println(stmt);
								}
							}
					}
					
					rs = stmt.executeQuery();
//					while(rs.next()) {
//						System.out.println(rs.getDate("Date"));
//						System.out.println(rs.getString("AM/PM"));
//						System.out.println(rs.getInt("Machine-num"));
//					}
//					System.out.println("ENDDDDDDDDDDDDDDDDDD");
					
					frmCoinCountReport.setVisible(false);
					showCoinCountReport object = new showCoinCountReport();
					object.frmCoinCountReport.setVisible(true);					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnGetData.setIcon(new ImageIcon(CoinCountReport.class.getResource("/loupe.png")));
		btnGetData.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnGetData.setBounds(10, 457, 187, 65);
		panel.add(btnGetData);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCoinCountReport.setVisible(false);
				Home object = new Home();
				object.frmHome.setVisible(true);
			}
		});
		btnBack.setIcon(new ImageIcon(CoinCountReport.class.getResource("/back.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnBack.setBounds(666, 457, 187, 65);
		panel.add(btnBack);
	}
}
