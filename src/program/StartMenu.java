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
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Window.Type;

//Programin baslangic arayuzu
public class StartMenu extends JFrame {
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
				try {
					StartMenu frame = new StartMenu();
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
	public StartMenu() throws IOException{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnNewButton = new JButton("Giri\u015F Yap/Kaydol");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage loginekrani;
				try {
					loginekrani = new LoginPage();
					loginekrani.setLocationRelativeTo(null); 
					loginekrani.setResizable(false);
					loginekrani.setUndecorated(true);
					loginekrani.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
					loginekrani.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
			});
		btnNewButton.setBounds(20, 186, 373, 151);
		contentPane.add(btnNewButton);
		JButton btnAdminIlemleri = new JButton("Hakk\u0131nda");
		btnAdminIlemleri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutScreen aboutmenu;
				try {
					aboutmenu = new AboutScreen();
					aboutmenu.setLocationRelativeTo(null); 
					aboutmenu.setResizable(false);
					aboutmenu.setUndecorated(true);
					aboutmenu.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
					aboutmenu.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnAdminIlemleri.setBounds(20, 348, 373, 151);
		contentPane.add(btnAdminIlemleri);
		JButton btnk = new JButton("\u00C7\u0131k\u0131\u015F");
		btnk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnk.setBounds(20, 510, 373, 151);
		contentPane.add(btnk);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(StartMenu.class.getResource("/program/mainlogo.png")));
		lblNewLabel.setBounds(20, 12, 373, 139);
		contentPane.add(lblNewLabel);
	}
}
