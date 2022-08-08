package coinsCount;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class showCoinCountReport {

	public JFrame frmCoinCountReport;
	private JTable table;

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
		initialize();
		getData();
	}
	
//	getting the table data through this function `getData`
	
	private void getData() {
		
		try {
			ResultSet rs = CoinCountReport.rs;
			
			while (rs.next()) {
				
				int Id = rs.getInt("Count Id");
				Date date = rs.getDate("Date");
				Time time = rs.getTime("Time");
				String amPm = rs.getString("AM/PM");
				int machNum = rs.getInt("Machine-num");
				double inAmount = rs.getDouble("InAmount");	
				double outAmunt = rs.getDouble("OutAmount");
				
//				adding the data to the table of customers.	
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {Id, date, time, amPm, machNum, inAmount, outAmunt});
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
	@SuppressWarnings("serial")
	private void initialize() {
		frmCoinCountReport = new JFrame();
		frmCoinCountReport.setIconImage(Toolkit.getDefaultToolkit().getImage(showCoinCountReport.class.getResource("/chip.png")));
		frmCoinCountReport.setResizable(false);
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
		panel.setBounds(10, 11, 900, 600);
		frmCoinCountReport.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 862, 432);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Date", "Time", "Machine Number", "In Amount", "Out Amount"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
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
		btnNewButton.setBounds(10, 461, 209, 61);
		panel.add(btnNewButton);
	}
}
