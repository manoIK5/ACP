package transaction;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.awt.event.ActionEvent;

public class ShowTransactionReports {

	public JFrame frmTransactionReports;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnDeleteTransaction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowTransactionReports window = new ShowTransactionReports();
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
	public ShowTransactionReports() {
		initialize();
		getData();
	}
	
//	getting the table data through this function `getData`
	
	private void getData() {
		
		try {
			ResultSet rs = TransactionReports.rs;
			
			while (rs.next()) {
				
				int Id = rs.getInt("TransId");
				Date date = rs.getDate("Date");
				Time time = rs.getTime("Time");
				int machNum = rs.getInt("MachineNum");
				String cusId = rs.getString("CustomerName");
				double cashIn = rs.getDouble("CashIN");
				double bonus = rs.getDouble("Bonus");
				double cashOut = rs.getDouble("CashOut");
				double extraBonus = rs.getDouble("Extra Bonus");
				double frais = rs.getDouble("Frais");
				double prize = rs.getDouble("Prize");
				double welcome = rs.getDouble("Welcome");

				
//				adding the data to the table of customers.	
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {Id, date, time, machNum, cusId, cashIn, bonus, cashOut, extraBonus, frais, prize, welcome});
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
// end of function

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTransactionReports = new JFrame();
		frmTransactionReports.setIconImage(Toolkit.getDefaultToolkit().getImage(ShowTransactionReports.class.getResource("/chip.png")));
		frmTransactionReports.setTitle("Transaction Reports");
		frmTransactionReports.setBounds(100, 100, 1280, 600);
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
		panel.setBounds(0, 0, 1274, 596);
		frmTransactionReports.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1254, 448);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Transaction ID", "Date", "Time", "Machine Number", "Customer ID", "Cash In", "Bonus", "Cash Out", "Extra Bonus", "Frais", "Prize", "Welcome"
			}
		));
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmTransactionReports.setVisible(false);
				TransactionReports object = new TransactionReports();
				object.frmTransactionReports.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnNewButton.setIcon(new ImageIcon(ShowTransactionReports.class.getResource("/back.png")));
		btnNewButton.setBounds(1092, 470, 172, 67);
		panel.add(btnNewButton);
		
		btnDeleteTransaction = new JButton("Delete Transaction");
		btnDeleteTransaction.setIcon(new ImageIcon(ShowTransactionReports.class.getResource("/icons8-cancel-48.png")));
		btnDeleteTransaction.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnDeleteTransaction.setBounds(10, 483, 304, 67);
		panel.add(btnDeleteTransaction);
	}

}
