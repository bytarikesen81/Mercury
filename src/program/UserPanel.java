package program;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

//Kullanici giris yaptiktan sonra kendisine ait gelen arayuz
public class UserPanel extends JFrame {
	//Front end Fields
	private JPanel contentPane;

	
	//Back end Fields
	private static String UsrName;
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserPanel frame = new UserPanel(UsrName);
					frame.setLocationRelativeTo(null); 
					frame.setResizable(false);
					frame.setUndecorated(true);
					frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public UserPanel(String UsrName) throws SQLException,IOException {
		setResizable(false);
		
		UserPanel.UsrName = UsrName;
		DatabaseConnect sqlConnect = new DatabaseConnect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 347, 535);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextPane txtpnKtphaneUygulamas = new JTextPane();
		txtpnKtphaneUygulamas.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnKtphaneUygulamas.setForeground(Color.DARK_GRAY);
		txtpnKtphaneUygulamas.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnKtphaneUygulamas.setEditable(false);
		ArrayList<String> user_details = sqlConnect.detailUser(UsrName);
		String welcomeText = "Welcome ,"+ user_details.get(1);
		txtpnKtphaneUygulamas.setText(welcomeText);
		txtpnKtphaneUygulamas.setBounds(0, 0, 338, 23);
		contentPane.add(txtpnKtphaneUygulamas);
		
		JButton btnUser = new JButton("Kullan\u0131c\u0131 Detaylar\u0131");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UserDetail userdetail = new UserDetail("user",user_details);
					userdetail.setLocationRelativeTo(null); 
					userdetail.setResizable(false);
					userdetail.setUndecorated(true);
					userdetail.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
					userdetail.setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUser.setBounds(31, 65, 264, 112);
		contentPane.add(btnUser);
		
		JButton btnDrive = new JButton("S\u00FCr\u00FC\u015F");
		btnDrive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RidePanel newridepanel = new RidePanel(UsrName);
					newridepanel.setLocationRelativeTo(null); 
					newridepanel.setResizable(false);
					newridepanel.setUndecorated(true);
					newridepanel.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
					newridepanel.setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDrive.setBounds(31, 188, 264, 112);
		contentPane.add(btnDrive);
		
		JButton btnExit = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					StartMenu anaekran = new StartMenu();
					anaekran.setLocationRelativeTo(null); 
					anaekran.setResizable(false);
					anaekran.setUndecorated(true);
					anaekran.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
					anaekran.setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnExit.setBounds(31, 312, 264, 112);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(UserPanel.class.getResource("/program/location.png")));
		lblNewLabel.setBounds(-88, -51, 426, 547);
		contentPane.add(lblNewLabel);
	}
}
