package program;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

//Kullanici detaylarinin ve duzenleme islemlerinin yer aldigi arayuz
public class UserDetail extends JFrame {
	private JPanel contentPane;
	private JTextField user_uname_det;
	private JTextField user_name_det;
	private JTextField user_lastname_det;
	private JTextField user_mail_det;
	private static String openMode;
	private static ArrayList<String> details;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDetail frame = new UserDetail(openMode,details);
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
	public static void infoBox(String infoMessage, String titleBar){
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " +
		titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	public UserDetail(String openMode,ArrayList<String> details) throws IOException {
		setResizable(false);
		UserDetail.openMode = openMode;
		UserDetail.details = details;
		DatabaseConnect sqlConnect = new DatabaseConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTextPane txtpnKullancDetaylar = new JTextPane();
		txtpnKullancDetaylar.setEditable(false);
		txtpnKullancDetaylar.setText("   Kullan\u0131c\u0131 Detaylar\u0131");
		txtpnKullancDetaylar.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtpnKullancDetaylar.setBackground(new Color(255, 255, 255));
		txtpnKullancDetaylar.setBounds(126, 11, 230, 41);
		contentPane.add(txtpnKullancDetaylar);
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Kullan\u0131c\u0131 Ad\u0131:");
		textPane_1.setEditable(false);
		textPane_1.setBackground(new Color(255, 255, 255));
		textPane_1.setBounds(126, 71, 89, 20);
		contentPane.add(textPane_1);
		user_uname_det = new JTextField();
		user_uname_det.setHorizontalAlignment(SwingConstants.CENTER);
		user_uname_det.setEditable(false);
		user_uname_det.setText((String) null);
		user_uname_det.setColumns(10);
		user_uname_det.setBackground(new Color(255, 255, 255));
		user_uname_det.setBounds(225, 71, 131, 20);
		contentPane.add(user_uname_det);
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("Ad\u0131:");
		textPane_2.setEditable(false);
		textPane_2.setBackground(new Color(255, 255, 255));
		textPane_2.setBounds(126, 102, 89, 20);
		contentPane.add(textPane_2);
		user_name_det = new JTextField();
		user_name_det.setHorizontalAlignment(SwingConstants.CENTER);
		user_name_det.setEditable(false);
		user_name_det.setText((String) null);
		user_name_det.setColumns(10);
		user_name_det.setBackground(new Color(255, 255, 255));
		user_name_det.setBounds(225, 102, 131, 20);
		contentPane.add(user_name_det);
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("Soyad\u0131:");
		textPane_3.setEditable(false);
		textPane_3.setBackground(new Color(255, 255, 255));
		textPane_3.setBounds(126, 133, 89, 20);
		contentPane.add(textPane_3);
		user_lastname_det = new JTextField();
		user_lastname_det.setHorizontalAlignment(SwingConstants.CENTER);
		user_lastname_det.setEditable(false);
		user_lastname_det.setText((String) null);
		user_lastname_det.setColumns(10);
		user_lastname_det.setBackground(new Color(255, 255, 255));
		user_lastname_det.setBounds(225, 133, 131, 20);
		contentPane.add(user_lastname_det);
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("E-mail:");
		textPane_4.setEditable(false);
		textPane_4.setBackground(new Color(255, 255, 255));
		textPane_4.setBounds(126, 164, 89, 20);
		contentPane.add(textPane_4);
		user_mail_det = new JTextField();
		user_mail_det.setHorizontalAlignment(SwingConstants.CENTER);
		user_mail_det.setEditable(false);
		user_mail_det.setText((String) null);
		user_mail_det.setColumns(10);
		user_mail_det.setBackground(new Color(255, 255, 255));
		user_mail_det.setBounds(225, 164, 131, 20);
		contentPane.add(user_mail_det);
		JButton del_user = new JButton("Sil");
		del_user.setBackground(new Color(255, 0, 0));
		del_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		del_user.setForeground(Color.BLACK);
		del_user.setBounds(126, 226, 103, 23);
		contentPane.add(del_user);
		JButton closeButton = new JButton("Geri");
		closeButton.setForeground(UIManager.getColor("Button.foreground"));
		closeButton.setBackground(UIManager.getColor("Button.background"));
		closeButton.setBounds(239, 226, 117, 23);
		contentPane.add(closeButton);
		user_uname_det.setText(details.get(0));
		user_name_det.setText(details.get(1));
		user_lastname_det.setText(details.get(2));
		user_mail_det.setText(details.get(3));
		
		closeButton.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if(UserDetail.openMode.compareTo("user") == 0) {
						UserPanel userpanel = new UserPanel(details.get(0));
						userpanel.setLocationRelativeTo(null); 
						userpanel.setResizable(false);
						userpanel.setUndecorated(true);
						userpanel.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
						userpanel.setVisible(true);
					}
					sqlConnect.refreshRides();
					dispose();
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		del_user.addMouseListener(new MouseAdapter() {
		@Override
			public void mouseClicked(MouseEvent e) {
				try {
					sqlConnect.cancelAllRides(details.get(0));
					infoBox(sqlConnect.deleteUser(details.get(0)), "Bilgi");
					sqlConnect.refreshRides();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				StartMenu mainMenu;
				try {
					mainMenu = new StartMenu();
					mainMenu.setLocationRelativeTo(null); 
					mainMenu.setResizable(false);
					mainMenu.setUndecorated(true);
					mainMenu.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
					mainMenu.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
	}
}
