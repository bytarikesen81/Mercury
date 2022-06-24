package program;
import java.awt.BorderLayout;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Giris ve kayit islemlerinin yer aldigi arayuz
public class LoginPage extends JFrame {
	private JPanel contentPane;
	private JTextField kullanici_adi;
	private JPasswordField sifre;
	private JTextField yeni_uname;
	private JTextField yeni_ad;
	private JTextField yeni_soyad;
	private JTextField yeni_mail;
	private JPasswordField yeni_pass;
	private JPasswordField yeni_pass_tekrar;
	
	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setLocationRelativeTo(null); 
					frame.setResizable(false);
					frame.setUndecorated(true);
					frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void infoBox(String infoMessage, String titleBar){
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	public LoginPage() throws IOException {
		setResizable(false);
		DatabaseConnect sqlConnect = new DatabaseConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 414);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 204, 102));
		panel.setBounds(0, 0, 270, 379);
		contentPane.add(panel);
		panel.setLayout(null);
		JButton girisButon = new JButton("Giri\u015F");
		girisButon.setBackground(Color.WHITE);
		girisButon.setBounds(10, 274, 240, 42);
		panel.add(girisButon);
		JTextPane txtpnKullancAd = new JTextPane();
		txtpnKullancAd.setForeground(new Color(255, 255, 255));
		txtpnKullancAd.setEditable(false);
		txtpnKullancAd.setBounds(32, 115, 86, 20);
		panel.add(txtpnKullancAd);
		txtpnKullancAd.setBackground(new Color(0, 204, 102));
		txtpnKullancAd.setText("Kullan\u0131c\u0131 Ad\u0131:");
		JTextPane txtpnifre = new JTextPane();
		txtpnifre.setForeground(new Color(255, 255, 255));
		txtpnifre.setEditable(false);
		txtpnifre.setBounds(32, 146, 86, 20);
		panel.add(txtpnifre);
		txtpnifre.setBackground(new Color(0, 204, 102));
		txtpnifre.setText("\u015Eifre:");
		kullanici_adi = new JTextField();
		kullanici_adi.setBounds(124, 115, 86, 20);
		panel.add(kullanici_adi);
		kullanici_adi.setColumns(10);
		sifre = new JPasswordField();
		sifre.setBounds(124, 146, 86, 20);
		panel.add(sifre);
		JTextPane txtpnKullancGirii = new JTextPane();
		txtpnKullancGirii.setBounds(0, 0, 89, 27);
		panel.add(txtpnKullancGirii);
		txtpnKullancGirii.setForeground(Color.WHITE);
		txtpnKullancGirii.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnKullancGirii.setBackground(new Color(0, 204, 102));
		txtpnKullancGirii.setEditable(false);
		txtpnKullancGirii.setText("GİRİŞ YAP");
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(269, 0, 291, 379);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		JTextPane textPane = new JTextPane();
		textPane.setForeground(new Color(0, 204, 102));
		textPane.setEditable(false);
		textPane.setBounds(28, 53, 86, 20);
		panel_1.add(textPane);
		textPane.setText("Kullan\u0131c\u0131 Ad\u0131:");
		textPane.setBackground(Color.WHITE);
		yeni_uname = new JTextField();
		yeni_uname.setForeground(Color.WHITE);
		yeni_uname.setBackground(new Color(0, 204, 102));
		yeni_uname.setBounds(140, 53, 86, 20);
		panel_1.add(yeni_uname);
		yeni_uname.setColumns(10);
		JTextPane txtpnifre_1 = new JTextPane();
		txtpnifre_1.setForeground(new Color(0, 204, 102));
		txtpnifre_1.setEditable(false);
		txtpnifre_1.setBounds(28, 84, 86, 20);
		panel_1.add(txtpnifre_1);
		txtpnifre_1.setText("\u015Eifre:");
		txtpnifre_1.setBackground(Color.WHITE);
		JTextPane txtpnifreTekrar = new JTextPane();
		txtpnifreTekrar.setForeground(new Color(0, 204, 102));
		txtpnifreTekrar.setEditable(false);
		txtpnifreTekrar.setBounds(28, 115, 86, 20);
		panel_1.add(txtpnifreTekrar);
		txtpnifreTekrar.setText("\u015Eifre Tekrar:");
		txtpnifreTekrar.setBackground(Color.WHITE);
		JTextPane txtpnIsim = new JTextPane();
		txtpnIsim.setForeground(new Color(0, 204, 102));
		txtpnIsim.setEditable(false);
		txtpnIsim.setBounds(28, 146, 86, 20);
		panel_1.add(txtpnIsim);
		txtpnIsim.setText("Ad:");
		txtpnIsim.setBackground(Color.WHITE);
		yeni_ad = new JTextField();
		yeni_ad.setForeground(Color.WHITE);
		yeni_ad.setBackground(new Color(0, 204, 102));
		yeni_ad.setBounds(140, 146, 86, 20);
		panel_1.add(yeni_ad);
		yeni_ad.setColumns(10);
		JTextPane txtpnSoyad = new JTextPane();
		txtpnSoyad.setForeground(new Color(0, 204, 102));
		txtpnSoyad.setEditable(false);
		txtpnSoyad.setBounds(28, 177, 86, 20);
		panel_1.add(txtpnSoyad);
		txtpnSoyad.setText("Soyad:");
		txtpnSoyad.setBackground(Color.WHITE);
		yeni_soyad = new JTextField();
		yeni_soyad.setForeground(Color.WHITE);
		yeni_soyad.setBackground(new Color(0, 204, 102));
		yeni_soyad.setBounds(140, 177, 86, 20);
		panel_1.add(yeni_soyad);
		yeni_soyad.setColumns(10);
		JTextPane txtpnEmail = new JTextPane();
		txtpnEmail.setForeground(new Color(0, 204, 102));
		txtpnEmail.setEditable(false);
		txtpnEmail.setBounds(28, 208, 86, 20);
		panel_1.add(txtpnEmail);
		txtpnEmail.setText("E-mail:");
		txtpnEmail.setBackground(Color.WHITE);
		yeni_mail = new JTextField();
		yeni_mail.setForeground(Color.WHITE);
		yeni_mail.setBackground(new Color(0, 204, 102));
		yeni_mail.setBounds(140, 208, 86, 20);
		panel_1.add(yeni_mail);
		yeni_mail.setColumns(10);
		JButton btnYeniyelik = new JButton("Kay\u0131t Ol");
		btnYeniyelik.setBackground(Color.WHITE);
		btnYeniyelik.setBounds(28, 273, 240, 43);
		panel_1.add(btnYeniyelik);
		yeni_pass = new JPasswordField();
		yeni_pass.setForeground(Color.WHITE);
		yeni_pass.setBackground(new Color(0, 204, 102));
		yeni_pass.setBounds(140, 84, 86, 20);
		panel_1.add(yeni_pass);
		yeni_pass_tekrar = new JPasswordField();
		yeni_pass_tekrar.setForeground(Color.WHITE);
		yeni_pass_tekrar.setBackground(new Color(0, 204, 102));
		yeni_pass_tekrar.setBounds(140, 115, 86, 20);
		panel_1.add(yeni_pass_tekrar);
		JTextPane txtpnYeniyelik = new JTextPane();
		txtpnYeniyelik.setForeground(new Color(0, 204, 102));
		txtpnYeniyelik.setBounds(0, 0, 66, 27);
		panel_1.add(txtpnYeniyelik);
		txtpnYeniyelik.setText("ÜYE OL");
		txtpnYeniyelik.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtpnYeniyelik.setEditable(false);
		txtpnYeniyelik.setBackground(Color.WHITE);
		
		JButton btnBackButton = new JButton("<< Geri");
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnBackButton.setBounds(0, 375, 560, 42);
		contentPane.add(btnBackButton);
		
		btnYeniyelik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String userName = yeni_uname.getText();
				String userPassword = String.valueOf(yeni_pass.getPassword());
				String REuserPassword = String.valueOf(yeni_pass_tekrar.getPassword());
				String firstName = yeni_ad.getText();
				String lastName = yeni_soyad.getText();
				String eMail = yeni_mail.getText();
				if (userName.length() < 4) {
					infoBox("Kullanıcı adı en az 4 karakterden oluşmalıdır", "Hata");
					return;
				}
				if (userPassword.length() < 4) {
					infoBox("Şifre en az 4 karakterden oluşmalıdır", "Hata");
					return;
				}
				if (REuserPassword.equals(userPassword) != true) {
					infoBox("Girilen şifreler eşleşmiyor", "Hata");
					return;
				}
				if (firstName.length() == 0) {
					infoBox("İsim alanı boş bırakılamaz", "Hata");
					return;
				}
				if (lastName.length() == 0) {
					infoBox("Soyisim alanı boş bırakılamaz", "Hata");
					return;
				}
				if (eMail.length() == 0) {
					infoBox("Mail alanı boş bırakılamaz", "Hata");
					return;
				}
				if(!(eMail.endsWith(".com") && eMail.contains("@"))) {
					infoBox("Geçersiz e-Mail", "Hata");
					return;
				}
				User YeniKullanici = new User(userName, userPassword, firstName, lastName, eMail);
				try {
					String addUserResult = sqlConnect.addUser(YeniKullanici);
					infoBox(addUserResult, "Bilgi");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				yeni_uname.setText("");
				yeni_pass.setText("");
				yeni_pass_tekrar.setText("");
				yeni_ad.setText("");
				yeni_soyad.setText("");
				yeni_mail.setText("");
			}
		});
		
		
		girisButon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					String userName = kullanici_adi.getText();
					String userPassword = String.valueOf(sifre.getPassword());
					try {
						String loginResult = sqlConnect.login(userName, userPassword);
						
						if (loginResult.equals("admin")) {
							AdminPanel adminPaneli = new AdminPanel();
							adminPaneli.setLocationRelativeTo(null); 
							adminPaneli.setResizable(false);
							adminPaneli.setUndecorated(true);
							adminPaneli.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
							adminPaneli.setVisible(true);
							dispose();
						}
						else if (loginResult.equals("user")) {
							UserPanel userpanel = new UserPanel(userName);
							userpanel.setLocationRelativeTo(null); 
							userpanel.setResizable(false);
							userpanel.setUndecorated(true);
							userpanel.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
							userpanel.setVisible(true);
							dispose();
						}
						else {
							infoBox(loginResult, "Hata");
						}
					} 
					catch (SQLException e1) {
						e1.printStackTrace();
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
		});
	}
}