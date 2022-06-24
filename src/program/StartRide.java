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
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.util.*;
import java.math.*;

//Surusu baslatma isleminin yapildigi ve onaylandigi arayuz
public class StartRide extends JFrame {
	private JPanel contentPane;
	private Date curDate = new Date();
	private static String UsrName;
	private static String locFrom;
	private static String locTo;
	private static String time;
	private static String dateStart;
	private static String dateEnd;
	private static String carClass;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
				try {
					StartRide frame = new StartRide(UsrName,locFrom,locTo,time,carClass);
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
	
	public StartRide(String usrName,String locFrom,String locTo,String time,String carClass) throws IOException,SQLException{
		setResizable(false);
		int hour,min,cHour,cMin;
		double dist,duration;
		String cHourandMin[],timeStart,timeEnd;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 340);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("S\u00DCR\u00DC\u015E B\u0130LG\u0130LER\u0130");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(183, 10, 244, 30);
		contentPane.add(lblNewLabel);
		
		JTextPane txtpnNereden = new JTextPane();
		txtpnNereden.setEditable(false);
		txtpnNereden.setText("Nereden:");
		txtpnNereden.setBackground(new Color(255, 255, 255));
		txtpnNereden.setBounds(10, 51, 79, 20);
		contentPane.add(txtpnNereden);
		
		JTextPane txtpnlocfrom = new JTextPane();
		txtpnlocfrom.setEditable(false);
		txtpnlocfrom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnlocfrom.setBackground(new Color(255, 255, 255));
		txtpnlocfrom.setBounds(10, 82, 288, 30);
		contentPane.add(txtpnlocfrom);
		
		JTextPane txtpnNereden_1 = new JTextPane();
		txtpnNereden_1.setEditable(false);
		txtpnNereden_1.setText("Nereye:");
		txtpnNereden_1.setBackground(new Color(255, 255, 255));
		txtpnNereden_1.setBounds(317, 51, 79, 20);
		contentPane.add(txtpnNereden_1);
		
		JTextPane txtpnlocto = new JTextPane();
		txtpnlocto.setEditable(false);
		txtpnlocto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnlocto.setBackground(new Color(255, 255, 255));
		txtpnlocto.setBounds(317, 82, 288, 30);
		contentPane.add(txtpnlocto);
		
		JTextPane txtpnMesafe = new JTextPane();
		txtpnMesafe.setEditable(false);
		txtpnMesafe.setText("Mesafe:");
		txtpnMesafe.setBackground(new Color(255, 255, 255));
		txtpnMesafe.setBounds(229, 154, 79, 20);
		contentPane.add(txtpnMesafe);
		
		JTextPane txtpndist = new JTextPane();
		txtpndist.setEditable(false);
		txtpndist.setBackground(new Color(255, 255, 255));
		txtpndist.setBounds(321, 154, 106, 20);
		contentPane.add(txtpndist);
		
		JTextPane txtpnSre = new JTextPane();
		txtpnSre.setEditable(false);
		txtpnSre.setText("S\u00FCre:");
		txtpnSre.setBackground(new Color(255, 255, 255));
		txtpnSre.setBounds(229, 185, 79, 20);
		contentPane.add(txtpnSre);
		
		JTextPane txtpnTime = new JTextPane();
		txtpnTime.setEditable(false);
		txtpnTime.setBackground(new Color(255, 255, 255));
		txtpnTime.setBounds(318, 185, 249, 20);
		contentPane.add(txtpnTime);
		
		JTextPane txtpnYolculukBilgileri = new JTextPane();
		txtpnYolculukBilgileri.setEditable(false);
		txtpnYolculukBilgileri.setBackground(new Color(255, 255, 255));
		txtpnYolculukBilgileri.setBounds(226, 123, 154, 20);
		contentPane.add(txtpnYolculukBilgileri);
		
		JButton btnStartRide = new JButton("S\u00DCR\u00DC\u015E\u00DC BA\u015ELAT");
		btnStartRide.setBounds(318, 260, 310, 41);
		contentPane.add(btnStartRide);
		
		JButton btnBack = new JButton("VAZGE\u00C7");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RidePanel newridepanel = new RidePanel(usrName);
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
		btnBack.setBounds(0, 260, 308, 41);
		contentPane.add(btnBack);
		
		/*Islemler*/
		
		//Database baglantisini kur
		DatabaseConnect sqlConnect = new DatabaseConnect();
		
		//Onceki formdan gelen degerleri tayin et
		StartRide.UsrName = usrName;
		StartRide.locFrom = locFrom;
		StartRide.locTo = locTo;
		StartRide.time = time;
		StartRide.carClass = carClass;
		
		//Formu alinan degerlere gore doldur
		txtpnlocfrom.setText(locFrom);
		txtpnlocto.setText(locTo);
		txtpnYolculukBilgileri.setText("Yolculuk Bilgileri("+carClass+")");
		dist = Ride.calcDistance(locFrom,locTo);
		duration = Ride.calcTime(dist,carClass);
		txtpndist.setText(dist+" km");
		
		
		//Saat-dk hesabı yap
		hour = (int)Math.floor(duration);
		min = (int)Math.ceil((duration-Math.floor(duration))*60);
		cHourandMin = time.split(":");
		cHour = Integer.parseInt(cHourandMin[0]);
		cMin = Integer.parseInt(cHourandMin[1]);
		txtpnTime.setText(hour+" saat "+min+"dk.");
		
		//Kalkis ve inis tarihlerini ayarla
		Calendar c = Calendar.getInstance();
		c.set(curDate.getYear()+1900, curDate.getMonth(), curDate.getDate(), cHour, cMin);
		SimpleDateFormat format = new SimpleDateFormat("yyyy,M,d,HH:mm");
		StartRide.dateStart = format.format(c.getTime());
		c.add(Calendar.HOUR_OF_DAY, hour);
		c.add(Calendar.MINUTE, min);
		StartRide.dateEnd = format.format(c.getTime());
		
		btnStartRide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Surus icin aktif bir surucu sec
					Driver newDriver = sqlConnect.findActiveDriver();
					if(newDriver == null) {
						JOptionPane.showMessageDialog(null, "Şu anda uygun bir sürücü bulunmamaktadır. Anlayışınız için teşekkür ederiz.", "UYARI", JOptionPane.WARNING_MESSAGE);
					}
					else {
						Ride newRide = new Ride(sqlConnect.detRideID(), usrName, newDriver, carClass, locFrom, locTo, StartRide.dateStart, StartRide.dateEnd, "waiting");
						sqlConnect.addRide(newRide);
						JOptionPane.showMessageDialog(null, "Sürüş başlatıldı. \nSürüşlerinizi 'sürüşlerim' menüsünden görüntüleyebilir ve düzenleyebilirsiniz.", "İŞLEM BAŞARILI", JOptionPane.INFORMATION_MESSAGE);
						btnStartRide.setEnabled(false);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					sqlConnect.refreshRides();
					RidePanel newridepanel = new RidePanel(usrName);
					newridepanel.setLocationRelativeTo(null); 
					newridepanel.setResizable(false);
					newridepanel.setUndecorated(true);
					newridepanel.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
					newridepanel.setVisible(true);
					dispose();
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
	
	public double calcDistance(String from,String to) {
		int fromInd,toInd;
		float distances[][] = {
				{0,78,41},
				{84,0,56},
				{38,58,0},
		};
		switch(from) {
			case "Istanbul Havalimanı":
				fromInd = 0;
				break;
			case "Sabiha Gökçen Havalimanı":
				fromInd = 1;
				break;
			case "Atatürk Havalimanı":
				fromInd = 2;
				break;
			default:
				fromInd = 0;
			break;
		}
		switch(to) {
			case "Istanbul Havalimanı":
				toInd = 0;
				break;
			case "Sabiha Gökçen Havalimanı":
				toInd = 1;
				break;
			case "Atatürk Havalimanı":
				toInd = 2;
				break;
			default:
				toInd = 0;
			break;
		}
		return distances[fromInd][toInd];
	}
	
	public double calcTime(double distance,String carClass) {
		double multiple;
			switch(carClass) {
				case "Ekonomi":
					multiple = 0.02;
					break;
				case "Standard":
					multiple = 0.016;
					break;
				case "VIP":
					multiple = 0.014;
					break;
				default:
					multiple = 0;
				break;
			}
		return multiple*distance;
	}
}


