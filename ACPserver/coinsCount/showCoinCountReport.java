package coinsCount;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import databaseCon.DatabaseCon;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class showCoinCountReport {

	public JFrame frmCoinCountReport;
	private JTable table;
	
	static Connection con;
	static PreparedStatement stmt;   
	static ResultSet rs;
	
	static PreparedStatement stmt2;   
	static ResultSet rs2;
	
	static PreparedStatement stmt3;   
	static ResultSet rs3;
	
	static PreparedStatement stmt4;   
	static ResultSet rs4;
	
	private JLabel ttlIn;
	private JLabel ttlOut;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;

	/**
	 * Launch the application.
	 */ 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					showCoinCountReport window = new showCoinCountReport();
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
	public showCoinCountReport() {
		con = DatabaseCon.connection();
		initialize();
		getData();
		getDataTwo();
		getDataThree();
		getDataFour();
		getTotalData();
	}
	
//	getting the table data through this function `getData`
	
	private void getData() {
		
		try {
			ResultSet rs = CoinCountReport.stmt.executeQuery();

			while (rs.next()) {
				
				Date date = rs.getDate("Date");
				Time time = rs.getTime("Time");
				String amPm = rs.getString("AM/PM");
				int machNum = rs.getInt("Machine-num");
				double inAmount = rs.getDouble("InAmount");	
				double outAmunt = rs.getDouble("OutAmount");
								
//				adding the data to the table of customers.	
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {date, time, amPm, machNum, inAmount, outAmunt});
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
// end of function
	
	private void getDataTwo() {
		
		try {
			ResultSet rss = CoinCountReport.stmt.executeQuery();
			
			while(rss.next()) {
				int tempMachNum = rss.getInt(("Machine-num"));
				
				double totIn = 0.00;
				double totOut = 0.00; 
				double finalTot = 0.00;; 
				
				double inAmountAm = 0.00;
				double outAmountAm = 0.00;
				
				double inAmountPm = 0.00;
				double outAmountPm = 0.00;
				DefaultTableModel model2 = (DefaultTableModel) table_2.getModel();
				
				if(rss.getInt("Machine-num") == tempMachNum) {
					stmt2 = con.prepareStatement("SELECT * FROM `coincount` WHERE `Machine-num` = ? && `date` between date_sub(curdate(),interval 0 day) and curdate()");
					stmt2.setInt(1, tempMachNum);
					rs2 = stmt2.executeQuery();
						
					while(rs2.next()) {
						if(rs2.getString("Am/Pm").equals("AM")) {
							inAmountAm = rs2.getDouble("InAmount");
							outAmountAm = rs2.getDouble("OutAmount");
						} else {
							inAmountPm = rs2.getDouble("InAmount");
							outAmountPm = rs2.getDouble("OutAmount");
						}
						
						totIn = inAmountPm - inAmountAm;;
						totOut = outAmountPm - outAmountAm;
						
						if(totOut <0) {
							finalTot = totIn + totOut;
						} else {
							finalTot = totIn - totOut;
						}
						
					}
					// its showing data in table 2  
					model2.addRow(new Object[] {tempMachNum, totIn, totOut, finalTot});	

					for(int i = 1; i<model2.getRowCount(); i++) {
						int machNum = (int) table_2.getValueAt(i-1, 0);
						int machNum2 = (int) table_2.getValueAt(i, 0);
						if(machNum == machNum2) {
							model2.removeRow(machNum2);
						}
					}
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	};
	
	private void getDataThree() {
		
		try {
			stmt3 = con.prepareStatement("SELECT * FROM `coincount` WHERE `Date` = date_sub(curdate(),interval 1 day)");
			rs3 = stmt3.executeQuery();
			
			while (rs3.next()) {
				
				Date date = rs3.getDate("Date");
				Time time = rs3.getTime("Time");
				String amPm = rs3.getString("AM/PM");
				int machNum = rs3.getInt("Machine-num");
				double inAmount = rs3.getDouble("InAmount");	
				double outAmunt = rs3.getDouble("OutAmount");
								
//				adding the data to the table of customers.	
				DefaultTableModel model3 = (DefaultTableModel) table_1.getModel();
				model3.addRow(new Object[] {date, time, amPm, machNum, inAmount, outAmunt});
				
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getDataFour() {
		
		
		try {
			ResultSet res = CoinCountReport.stmt.executeQuery();
			
			while(res.next()) {
				int tempMachNum = res.getInt(("Machine-num"));
				
				double totIn = 0.00;
				double totOut = 0.00; 
				
				double inAmountAm = 0.00;
				double outAmountAm = 0.00;
				
				double inAmountPm = 0.00;
				double outAmountPm = 0.00;
				double finalTot = 0.00;
				
				if(res.getInt("Machine-num") == tempMachNum) {
					stmt4 = con.prepareStatement("SELECT * FROM `coincount` WHERE `Machine-num` = ? && `date` between date_sub(curdate(),interval 0 day) and curdate()");
					stmt4.setInt(1, tempMachNum);
					rs4 = stmt4.executeQuery();
					
					while(rs4.next()) {
						if(rs4.getString("Am/Pm").equals("AM")) {
							inAmountAm = rs4.getDouble("InAmount");
							outAmountAm = rs4.getDouble("OutAmount");
						}
					}
					stmt4.close();
//					here the code varies from getDataTwo, above we got the am in and out amounts, we
//					... we need to get the pm from yesterday
					stmt4 = con.prepareStatement("SELECT * FROM `coincount` WHERE `AM/PM` = 'PM' && `Date` = date_sub(curdate(),interval 1 day) && `Machine-num` = ?");
					stmt4.setInt(1, tempMachNum);
					rs4 = stmt4.executeQuery();
					while(rs4.next()) {
						inAmountPm = rs4.getDouble("InAmount");
						outAmountPm = rs4.getDouble("OutAmount");
					}
//					System.out.println("here      : " + inAmountPm) ;
					stmt4.close();
					
					totIn = inAmountPm - inAmountAm;;
					totOut = outAmountPm - outAmountAm;
					
					if(totOut <0) {
						finalTot = totIn + totOut;
					} else {
						finalTot = totIn - totOut;
					}
					
					// showing the data in table 4
					DefaultTableModel model4 = (DefaultTableModel) table_3.getModel();
					model4.addRow(new Object[] {tempMachNum, totIn, totOut, finalTot});	
					
					for(int i = 1; i<model4.getRowCount(); i++) {
						int machNum = (int) table_3.getValueAt(i-1, 0);
						int machNum2 = (int) table_3.getValueAt(i, 0);
						if(machNum == machNum2) {
							model4.removeRow(machNum2);
						}
					}
					
					
				}
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
	private void getTotalData() {
		double TotalIn;
		double TotalOut;
		
		double cashInAm = 0.00;
		double cashOutAm = 0.00;
		double cashInPm = 0.00;
		double cashOutPm = 0.00;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM `coincount` WHERE `AM/PM` = 'AM' && `Date` between date_sub(curdate(),interval 0 day) and curdate()");
			rs = stmt.executeQuery();
			while(rs.next()) {
				cashInAm += rs.getDouble("InAmount");
				cashOutAm += rs.getDouble("OutAmount");
			}
			stmt.close();
			
			stmt = con.prepareStatement("SELECT * FROM `coincount` WHERE `AM/PM` = 'PM' && `Date` between date_sub(curdate(),interval 0 day) and curdate()");
			rs = stmt.executeQuery();
			while(rs.next()) {
				cashInPm += rs.getDouble("InAmount");
				cashOutPm += rs.getDouble("OutAmount");
			}
			stmt.close();
			
			TotalIn = cashInPm - cashInAm;
			TotalOut = cashOutPm - cashOutAm;
			
			ttlIn.setText(String.valueOf(TotalIn));
			ttlOut.setText(String.valueOf(TotalOut));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frmCoinCountReport = new JFrame();
		frmCoinCountReport.setIconImage(Toolkit.getDefaultToolkit().getImage(showCoinCountReport.class.getResource("/chip.png")));
		frmCoinCountReport.setTitle("Coin Count Report");
		frmCoinCountReport.setBounds(100, 100, 1280, 917);
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
		panel.setBounds(0, 0, 1264, 878);
		frmCoinCountReport.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 18, 660, 389);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Time", "Am/Pm", "Machine Number", "In Amount", "Out Amount"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCoinCountReport.setVisible(false);
				CoinCountReport object = new CoinCountReport();
				object.frmCoinCountReport.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(showCoinCountReport.class.getResource("/back.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setBounds(461, 584, 209, 61);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Total In:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 581, 248, 67);
		panel.add(lblNewLabel);
		
		JLabel lblTotalOut = new JLabel("Total Out:");
		lblTotalOut.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblTotalOut.setBounds(10, 671, 248, 67);
		panel.add(lblTotalOut);
		
		ttlIn = new JLabel("0.00");
		ttlIn.setFont(new Font("Tahoma", Font.BOLD, 21));
		ttlIn.setBounds(124, 581, 248, 67);
		panel.add(ttlIn);
		
		ttlOut = new JLabel("0.00");
		ttlOut.setFont(new Font("Tahoma", Font.BOLD, 21));
		ttlOut.setBounds(124, 671, 248, 67);
		panel.add(ttlOut);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(699, 18, 555, 389);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Time", "Am/Pm", "Machine Number", "In Amount", "Out Amount"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 419, 660, 151);
		panel.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Machine Num", "Total In", "Total Out", "Final Total"
			}
		));
		scrollPane_2.setViewportView(table_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(699, 418, 555, 151);
		panel.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Machine Num", "Total In", "Total Out", "Final Total"
			}
		));
		table_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane_3.setViewportView(table_3);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Date date = (Date) table.getValueAt(row, 0);
				Time time = (Time) table.getValueAt(row, 1);
				String amPm = (String) table.getValueAt(row, 2);
				int machNum = (int) table.getValueAt(row, 3);
				double inAmount = (double) table.getValueAt(row, 4);
				double outAmount = (double) table.getValueAt(row, 5);
				 
				try {
					stmt = con.prepareStatement("DELETE FROM `coincount` WHERE `Date` =? && `Time` =? && `AM/PM` =? && `Machine-num` =? && `InAmount` =? && `OutAmount` =?");
					stmt.setDate(1, (java.sql.Date) date);
					stmt.setTime(2, time);
					stmt.setString(3, amPm);
					stmt.setInt(4, machNum);
					stmt.setDouble(5, inAmount);
					stmt.setDouble(6, outAmount);
					
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted!");
					stmt.close();
					
					frmCoinCountReport.setVisible(false);
					showCoinCountReport object = new showCoinCountReport();
					object.frmCoinCountReport.setVisible(true);
					

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(showCoinCountReport.class.getResource("/icons8-cancel-48.png")));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnDelete.setBounds(461, 665, 209, 61);
		panel.add(btnDelete);
	}
}
