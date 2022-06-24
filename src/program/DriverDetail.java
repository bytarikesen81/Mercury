package program;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

//Surucu detaylarinin ve duzenleme islemlerinin yer aldigi arayuz
public class DriverDetail extends JFrame {
	private JPanel contentPane;
	private static ArrayList<String> details;
	private JTextField drID_det;
	private JTextField drexp_det;
	private JTextField drPts_det;
	private JTextField drname_det;
	private JTextField drlastname_Det;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
	public void run() {
	try {
			DriverDetail frame = new DriverDetail(details);
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
	public DriverDetail(ArrayList<String> details) {
		setResizable(false);
	this.details = details;
	DatabaseConnect sqlConnect = new DatabaseConnect();
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBackground(new Color(255, 255, 255));
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	JTextPane title_detail = new JTextPane();
	title_detail.setEditable(false);
	title_detail.setBackground(new Color(255, 255, 255));
	title_detail.setFont(new Font("Tahoma", Font.BOLD, 20));
	title_detail.setText("S\u00FCr\u00FCc\u00FC Detaylar\u0131\r\n");
	title_detail.setBounds(123, 11, 194, 41);
	contentPane.add(title_detail);
	JTextPane txtpnSrcId = new JTextPane();
	txtpnSrcId.setText("S\u00FCr\u00FCc\u00FC ID");
	txtpnSrcId.setEditable(false);
	txtpnSrcId.setBackground(new Color(255, 255, 255));
	txtpnSrcId.setBounds(101, 71, 89, 20);
	contentPane.add(txtpnSrcId);
	JTextPane textPane_1 = new JTextPane();
	textPane_1.setText("Ad\u0131:");
	textPane_1.setEditable(false);
	textPane_1.setBackground(new Color(255, 255, 255));
	textPane_1.setBounds(101, 102, 89, 20);
	contentPane.add(textPane_1);
	JTextPane textPane_2 = new JTextPane();
	textPane_2.setText("Soyad\u0131:");
	textPane_2.setEditable(false);
	textPane_2.setBackground(new Color(255, 255, 255));
	textPane_2.setBounds(101, 133, 89, 20);
	contentPane.add(textPane_2);
	JTextPane txtpnSrDeneyimidk = new JTextPane();
	txtpnSrDeneyimidk.setText("S\u00FCr\u00FC\u015F Deneyimi(dk)");
	txtpnSrDeneyimidk.setEditable(false);
	txtpnSrDeneyimidk.setBackground(new Color(255, 255, 255));
	txtpnSrDeneyimidk.setBounds(101, 164, 117, 20);
	contentPane.add(txtpnSrDeneyimidk);
	JTextPane txtpnToplamPuan = new JTextPane();
	txtpnToplamPuan.setText("Toplam Puan");
	txtpnToplamPuan.setEditable(false);
	txtpnToplamPuan.setBackground(new Color(255, 255, 255));
	txtpnToplamPuan.setBounds(101, 195, 89, 20);
	contentPane.add(txtpnToplamPuan);
	drID_det = new JTextField();
	drID_det.setEditable(false);
	drID_det.setBackground(new Color(255, 255, 255));
	drID_det.setColumns(10);
	drID_det.setBounds(228, 71, 89, 20);
	contentPane.add(drID_det);
	drexp_det = new JTextField();
	drexp_det.setEditable(false);
	drexp_det.setBackground(new Color(255, 255, 255));
	drexp_det.setColumns(10);
	drexp_det.setBounds(228, 164, 89, 20);
	contentPane.add(drexp_det);
	drPts_det = new JTextField();
	drPts_det.setEditable(false);
	drPts_det.setBackground(new Color(255, 255, 255));
	drPts_det.setColumns(10);
	drPts_det.setBounds(228, 195, 89, 20);
	contentPane.add(drPts_det);
	drname_det = new JTextField();
	drname_det.setEditable(false);
	drname_det.setBackground(new Color(255, 255, 255));
	drname_det.setColumns(10);
	drname_det.setBounds(228, 102, 89, 20);
	contentPane.add(drname_det);
	drlastname_Det = new JTextField();
	drlastname_Det.setEditable(false);
	drlastname_Det.setBackground(new Color(255, 255, 255));
	drlastname_Det.setColumns(10);
	drlastname_Det.setBounds(228, 133, 89, 20);
	contentPane.add(drlastname_Det);
	drID_det.setText(details.get(0));
	drname_det.setText(details.get(1));
	drlastname_Det.setText(details.get(2));
	drexp_det.setText(details.get(3));
	drPts_det.setText(details.get(4));
	JButton closebutton = new JButton("Geri");
	closebutton.setBackground(UIManager.getColor("Button.background"));
	closebutton.setForeground(UIManager.getColor("Button.foreground"));
	closebutton.setBounds(213, 226, 104, 23);
	contentPane.add(closebutton);
	JButton del_dr = new JButton("Sil");
	del_dr.setBackground(new Color(255, 0, 0));
	del_dr.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	del_dr.setForeground(Color.BLACK);
	del_dr.setBounds(101, 226, 102, 23);
	contentPane.add(del_dr);
	closebutton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			dispose();
		}
	});
	del_dr.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				infoBox(sqlConnect.deleteDriver(Integer.parseInt(details.get(0))),"Bilgi");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			dispose();
		}
	});
	}
}

