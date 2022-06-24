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
import javax.swing.SpringLayout;
import java.awt.Toolkit;

//Programin baslangic arayuzu
public class AboutScreen extends JFrame {
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				AboutScreen frame = new AboutScreen();
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
	public AboutScreen() throws IOException{
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutScreen.class.getResource("/program/location.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(15, 15, 386, 164);
		lblNewLabel.setIcon(new ImageIcon(AboutScreen.class.getResource("/program/mainlogo.png")));
		contentPane.add(lblNewLabel);
		
		JTextPane txtpnMercuryKullanclarnnBlgesinde = new JTextPane();
		txtpnMercuryKullanclarnnBlgesinde.setEditable(false);
		txtpnMercuryKullanclarnnBlgesinde.setText("Mercury, kullan\u0131c\u0131lar\u0131n\u0131n b\u00F6lgesinde uygun olan havalimanlar\u0131 aras\u0131nda 3 farkl\u0131 s\u0131n\u0131fa ayr\u0131lan ringler arac\u0131l\u0131\u011F\u0131yla tek vesait transfer yapabilmesini sa\u011Flayan bir ta\u015F\u0131ma sistemidir.");
		txtpnMercuryKullanclarnnBlgesinde.setBounds(15, 226, 386, 55);
		contentPane.add(txtpnMercuryKullanclarnnBlgesinde);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mercury Nedir?");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(15, 190, 157, 25);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Transfer");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(15, 302, 102, 25);
		contentPane.add(lblNewLabel_1_1_1);
		
		JTextPane txtpnTransferlerKullanclarnKendilerine = new JTextPane();
		txtpnTransferlerKullanclarnKendilerine.setEditable(false);
		txtpnTransferlerKullanclarnKendilerine.setText("Transferler, kullan\u0131c\u0131lar\u0131n kendilerine ait \"S\u00FCr\u00FC\u015Flerim\" mod\u00FCl\u00FC arac\u0131l\u0131\u011F\u0131yla yap\u0131l\u0131r.Kullan\u0131c\u0131lar dilerlerse s\u00FCr\u00FC\u015Flerini ba\u015Flamadan iptal edebilir, sonlanm\u0131\u015F s\u00FCr\u00FC\u015Flerini daha sonra s\u00FCr\u00FCc\u00FCs\u00FC \u00FCzerinden de\u011Ferlendireblirler.");
		txtpnTransferlerKullanclarnKendilerine.setBounds(15, 332, 386, 74);
		contentPane.add(txtpnTransferlerKullanclarnKendilerine);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Bize Ula\u015F\u0131n");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(15, 435, 102, 25);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JTextPane txtpnMercuryKullanclarnnBlgesinde_1_1 = new JTextPane();
		txtpnMercuryKullanclarnnBlgesinde_1_1.setEditable(false);
		txtpnMercuryKullanclarnnBlgesinde_1_1.setText("Mercury Havalimanlar\u0131 Aras\u0131 Bireysel Transfer Sistemi (c) v1.0");
		txtpnMercuryKullanclarnnBlgesinde_1_1.setBounds(15, 591, 386, 25);
		contentPane.add(txtpnMercuryKullanclarnnBlgesinde_1_1);
		
		JButton btnNewButton = new JButton("GER\u0130");
		btnNewButton.addActionListener(new ActionListener() {
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
		btnNewButton.setBounds(15, 627, 386, 43);
		contentPane.add(btnNewButton);
		
		JTextPane txtpnGelitiricilerTarkEsen = new JTextPane();
		txtpnGelitiricilerTarkEsen.setEditable(false);
		txtpnGelitiricilerTarkEsen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnGelitiricilerTarkEsen.setText("Geli\u015Ftiriciler:\r\nTar\u0131k Esen, tarik.esen@std.yildiz.edu.tr\r\n\r\nKatk\u0131da Bulunanlar:\r\nZafar Ruziev, zafar.ruziev@std.yildiz.edu.tr\r\nBar\u0131\u015F Aky\u0131ld\u0131z, baris.akyildiz@std.yildiz.edu.tr");
		txtpnGelitiricilerTarkEsen.setBounds(15, 471, 386, 109);
		contentPane.add(txtpnGelitiricilerTarkEsen);
	}
}
