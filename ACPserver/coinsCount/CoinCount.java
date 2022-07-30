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

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CoinCount {

	public JFrame frmCoinCount;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 21));
		textField.setBounds(232, 136, 158, 47);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblInAmount = new JLabel("In Amount:");
		lblInAmount.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblInAmount.setBounds(36, 244, 195, 58);
		panel.add(lblInAmount);
		
		JLabel lblOutAmount = new JLabel("Out Amount:");
		lblOutAmount.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblOutAmount.setBounds(36, 359, 195, 58);
		panel.add(lblOutAmount);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		textField_1.setColumns(10);
		textField_1.setBounds(232, 255, 158, 47);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.BOLD, 21));
		textField_2.setColumns(10);
		textField_2.setBounds(232, 359, 158, 47);
		panel.add(textField_2);
		
		JButton btnNewButton = new JButton("Submit");
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
