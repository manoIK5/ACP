package login;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JTextField;

import Home.Home;
import databaseCon.DatabaseCon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.sql.*;
public class Login {

	public static JFrame frmLogin;
	private JTextField InputUsername;
	private JPasswordField InputPassword;
	
	static Connection con;
	static PreparedStatement stmt;
	static ResultSet rs; 
	
	public static String emploeeId;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		GetBatch.get();
		System.out.println("started here");
		con = DatabaseCon.connection();
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setFont(new Font("Dialog", Font.BOLD, 14));
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/icons8-male-user-64.png")));
		frmLogin.setResizable(false);
		frmLogin.getContentPane().setBackground(new Color(223, 247, 238));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 380, 486);
		frmLogin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmLogin.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frmLogin, 
		            "Are you sure you want to exit this program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		frmLogin.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = InputUsername.getText();
				@SuppressWarnings("deprecation")
				String password = InputPassword.getText();
				
				InputUsername.setText("");
				InputPassword.setText(""); 
				
			try {
					stmt = con.prepareStatement("SELECT `Employee-id` FROM employees WHERE username = ? && password = ?");
					stmt.setString(1, username);
					stmt.setString(2, password);
					
					rs = stmt.executeQuery();
					
					if (rs.next()) {
						emploeeId = rs.getString("Employee-id");
						System.out.println(emploeeId);
						
						Home hm1 = new Home();
						hm1.frmHome.setVisible(true);
						frmLogin.setVisible(false);

					} else {
						JOptionPane.showMessageDialog(null, "Wrong username or password");
					}
					
					stmt.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/icons8-login-48.png")));
		btnLogin.setBounds(26, 385, 142, 50);
		frmLogin.getContentPane().add(btnLogin);
				
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancel.setIcon(new ImageIcon(Login.class.getResource("/icons8-cancel-48.png")));
		btnCancel.setBounds(200, 385, 142, 51);
		frmLogin.getContentPane().add(btnCancel);
		
		InputUsername = new JTextField();
		InputUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		InputUsername.setBounds(42, 216, 248, 40);
		frmLogin.getContentPane().add(InputUsername);
		InputUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("User name:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsername.setBounds(42, 167, 248, 38);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(42, 267, 161, 32);
		frmLogin.getContentPane().add(lblPassword);
		
		InputPassword = new JPasswordField();
		InputPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		InputPassword.setBounds(42, 310, 248, 40);
		frmLogin.getContentPane().add(InputPassword);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/iconfinder_Paul-17_2624626.png")));
		label.setBounds(60, 11, 227, 166);
		frmLogin.getContentPane().add(label);
		
	}
}

