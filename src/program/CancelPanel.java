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
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Window.Type;
import javax.swing.UIManager;

public class CancelPanel extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private static String UsrName;
	private static Driver rideDriver;
	private Date curDate = new Date();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
				try {
					CancelPanel frame = new CancelPanel(UsrName);
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
	public CancelPanel(String UsrName) throws IOException,SQLException{
		setResizable(false);
		DatabaseConnect sqlConnect = new DatabaseConnect();
		CancelPanel.UsrName = UsrName;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 840);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(204, 0, 51));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("YOLCULUK ??PTAL??");
		lblNewLabel.setBounds(134, 11, 244, 30);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblNewLabel);
		
		JTextPane txtpnYolculukIdniziGiriniz = new JTextPane();
		txtpnYolculukIdniziGiriniz.setForeground(new Color(204, 0, 51));
		txtpnYolculukIdniziGiriniz.setBounds(15, 49, 227, 20);
		txtpnYolculukIdniziGiriniz.setText("Yolculuk ID:");
		txtpnYolculukIdniziGiriniz.setBackground(Color.WHITE);
		contentPane.add(txtpnYolculukIdniziGiriniz);
		
		textField = new JTextField();
		textField.setBounds(20, 80, 147, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnSrBilgileri = new JTextPane();
		txtpnSrBilgileri.setForeground(new Color(204, 0, 51));
		txtpnSrBilgileri.setBounds(15, 133, 152, 22);
		txtpnSrBilgileri.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnSrBilgileri.setText("S\u00FCr\u00FC\u015F Bilgileri");
		txtpnSrBilgileri.setBackground(Color.WHITE);
		contentPane.add(txtpnSrBilgileri);
		
		JTextPane txtpnRota = new JTextPane();
		txtpnRota.setForeground(new Color(204, 0, 51));
		txtpnRota.setBounds(15, 161, 34, 20);
		txtpnRota.setText("Rota:");
		txtpnRota.setBackground(Color.WHITE);
		contentPane.add(txtpnRota);
		
		JTextPane txtpnlocfromLocto = new JTextPane();
		txtpnlocfromLocto.setBounds(173, 161, 331, 20);
		txtpnlocfromLocto.setBackground(Color.WHITE);
		contentPane.add(txtpnlocfromLocto);
		
		JTextPane txtpnSrBalang = new JTextPane();
		txtpnSrBalang.setForeground(new Color(204, 0, 51));
		txtpnSrBalang.setBounds(15, 201, 85, 20);
		txtpnSrBalang.setText("S\u00FCr\u00FC\u015F Ba\u015Flang\u0131\u00E7:");
		txtpnSrBalang.setBackground(Color.WHITE);
		contentPane.add(txtpnSrBalang);
		
		JTextPane txtpnSrBiti = new JTextPane();
		txtpnSrBiti.setForeground(new Color(204, 0, 51));
		txtpnSrBiti.setBounds(15, 239, 60, 20);
		txtpnSrBiti.setText("S\u00FCr\u00FC\u015F Biti\u015F:");
		txtpnSrBiti.setBackground(Color.WHITE);
		contentPane.add(txtpnSrBiti);
		
		JTextPane txtpndatestart = new JTextPane();
		txtpndatestart.setBounds(173, 201, 331, 20);
		txtpndatestart.setBackground(Color.WHITE);
		contentPane.add(txtpndatestart);
		
		JTextPane txtpndateend = new JTextPane();
		txtpndateend.setBounds(173, 239, 331, 20);
		txtpndateend.setBackground(Color.WHITE);
		contentPane.add(txtpndateend);
		
		JTextPane txtpnArabaSnf = new JTextPane();
		txtpnArabaSnf.setForeground(new Color(204, 0, 51));
		txtpnArabaSnf.setBounds(15, 278, 65, 20);
		txtpnArabaSnf.setText("Araba S\u0131n\u0131f\u0131:");
		txtpnArabaSnf.setBackground(Color.WHITE);
		contentPane.add(txtpnArabaSnf);
		
		JTextPane txtpncarclass = new JTextPane();
		txtpncarclass.setBounds(173, 278, 331, 20);
		txtpncarclass.setBackground(Color.WHITE);
		contentPane.add(txtpncarclass);
		
		JTextPane txtpnMesafe = new JTextPane();
		txtpnMesafe.setForeground(new Color(204, 0, 51));
		txtpnMesafe.setBounds(15, 318, 46, 20);
		txtpnMesafe.setText("Mesafe:");
		txtpnMesafe.setBackground(Color.WHITE);
		contentPane.add(txtpnMesafe);
		
		JTextPane txtpnofr = new JTextPane();
		txtpnofr.setBounds(173, 318, 331, 20);
		txtpnofr.setBackground(Color.WHITE);
		contentPane.add(txtpnofr);
		
		JTextPane txtpnSre = new JTextPane();
		txtpnSre.setForeground(new Color(204, 0, 51));
		txtpnSre.setBounds(15, 356, 33, 20);
		txtpnSre.setText("S\u00FCre:");
		txtpnSre.setBackground(Color.WHITE);
		contentPane.add(txtpnSre);
		
		JTextPane txtpntime = new JTextPane();
		txtpntime.setBounds(173, 356, 331, 20);
		txtpntime.setBackground(Color.WHITE);
		contentPane.add(txtpntime);
		
		JTextPane txtpnofrBilgileri = new JTextPane();
		txtpnofrBilgileri.setForeground(new Color(204, 0, 51));
		txtpnofrBilgileri.setBounds(15, 477, 152, 22);
		txtpnofrBilgileri.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnofrBilgileri.setText("\u015Eof\u00F6r Bilgileri");
		txtpnofrBilgileri.setBackground(Color.WHITE);
		contentPane.add(txtpnofrBilgileri);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setForeground(new Color(204, 0, 51));
		txtpnId.setBounds(15, 505, 22, 20);
		txtpnId.setText("ID:");
		txtpnId.setBackground(Color.WHITE);
		contentPane.add(txtpnId);
		
		JTextPane txtpndriverid = new JTextPane();
		txtpndriverid.setBounds(173, 505, 331, 20);
		txtpndriverid.setBackground(Color.WHITE);
		contentPane.add(txtpndriverid);
		
		
		JTextPane txtpnId_1 = new JTextPane();
		txtpnId_1.setForeground(new Color(204, 0, 51));
		txtpnId_1.setBounds(15, 393, 22, 20);
		txtpnId_1.setText("ID:");
		txtpnId_1.setBackground(Color.WHITE);
		contentPane.add(txtpnId_1);
		
		JTextPane txtpnrideid = new JTextPane();
		txtpnrideid.setBounds(173, 393, 331, 20);
		txtpnrideid.setBackground(Color.WHITE);
		contentPane.add(txtpnrideid);
		
		JTextPane txtpnAd = new JTextPane();
		txtpnAd.setForeground(new Color(204, 0, 51));
		txtpnAd.setBounds(15, 542, 24, 20);
		txtpnAd.setText("Ad:");
		txtpnAd.setBackground(Color.WHITE);
		contentPane.add(txtpnAd);
		
		JTextPane txtpnSoyad = new JTextPane();
		txtpnSoyad.setForeground(new Color(204, 0, 51));
		txtpnSoyad.setBounds(15, 579, 41, 20);
		txtpnSoyad.setText("Soyad:");
		txtpnSoyad.setBackground(Color.WHITE);
		contentPane.add(txtpnSoyad);
		
		JTextPane txtpnSr = new JTextPane();
		txtpnSr.setForeground(new Color(204, 0, 51));
		txtpnSr.setBounds(15, 616, 84, 20);
		txtpnSr.setText("S??r???? Deneyimi:");
		txtpnSr.setBackground(Color.WHITE);
		contentPane.add(txtpnSr);
		
		JTextPane txtpnGenelPuan = new JTextPane();
		txtpnGenelPuan.setForeground(new Color(204, 0, 51));
		txtpnGenelPuan.setBounds(15, 651, 65, 20);
		txtpnGenelPuan.setText("Genel Puan:");
		txtpnGenelPuan.setBackground(Color.WHITE);
		contentPane.add(txtpnGenelPuan);
		
		JTextPane txtpndrname = new JTextPane();
		txtpndrname.setBounds(173, 542, 331, 20);
		txtpndrname.setBackground(Color.WHITE);
		contentPane.add(txtpndrname);
		
		JTextPane txtpndrSur = new JTextPane();
		txtpndrSur.setBounds(173, 579, 331, 20);
		txtpndrSur.setBackground(Color.WHITE);
		contentPane.add(txtpndrSur);
		
		JTextPane txtpndrExp = new JTextPane();
		txtpndrExp.setBounds(173, 616, 331, 20);
		txtpndrExp.setBackground(Color.WHITE);
		contentPane.add(txtpndrExp);
		
		JTextPane txtpndrPoints = new JTextPane();
		txtpndrPoints.setBounds(173, 651, 331, 20);
		txtpndrPoints.setBackground(Color.WHITE);
		contentPane.add(txtpndrPoints);
		
		JButton btnNewButton = new JButton("Sorgula");
		btnNewButton.setBackground(UIManager.getColor("Button.light"));
		btnNewButton.setBounds(173, 80, 147, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int rideID,hour,min;
				double dist,duration;
				ArrayList<String> foundRideInfo = new ArrayList<String>();
				try {
					rideID = Integer.parseInt(textField.getText());
					foundRideInfo = sqlConnect.searchRidetoCancel(rideID,CancelPanel.UsrName);
				}catch(NumberFormatException n1) {
					JOptionPane.showMessageDialog(null, "Yanl???? Input", "HATA", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(foundRideInfo != null) {
					txtpnlocfromLocto.setText(foundRideInfo.get(2)+"->"+foundRideInfo.get(3));
					txtpndatestart.setText(foundRideInfo.get(4));
					txtpndateend.setText(foundRideInfo.get(5));
					txtpncarclass.setText(foundRideInfo.get(1));
					//Sure-Mesafe hesab?? yap ve deneyime ekle
					dist = Ride.calcDistance(foundRideInfo.get(2),foundRideInfo.get(3));
					duration = Ride.calcTime(dist,foundRideInfo.get(1));
					hour = (int)Math.floor(duration);
					min = (int)Math.ceil((duration-Math.floor(duration))*60);
					txtpnofr.setText(dist+" km");
					txtpntime.setText(hour+" saat "+min+"dk");
					txtpnrideid.setText(foundRideInfo.get(6));
					try {
						CancelPanel.rideDriver = sqlConnect.findDriverbyID(Integer.parseInt(foundRideInfo.get(0)));
						txtpndriverid.setText(CancelPanel.rideDriver.getDriverID());
						txtpndrname.setText(CancelPanel.rideDriver.getName());
						txtpndrSur.setText(CancelPanel.rideDriver.getSurname());
						txtpndrExp.setText(CancelPanel.rideDriver.getExp()+"");
						txtpndrPoints.setText(CancelPanel.rideDriver.getPoints()+"");
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("??PTAL ET");
		btnNewButton_1.setBackground(new Color(204, 0, 51));
		btnNewButton_1.setBounds(15, 766, 232, 30);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel;
				try {
					sel = JOptionPane.showConfirmDialog(null, "??ptal Etme ????lemi Geri Al??namaz.\n"+"Yolculuk iptalini onayl??yor musunuz?", "??PTAL?? ONAYLA", JOptionPane.YES_NO_OPTION);
					if(sel == JOptionPane.YES_OPTION) {
						sqlConnect.updateDriverStatus(Integer.parseInt(CancelPanel.rideDriver.getDriverID()), "active");
						CancelPanel.rideDriver.setStatus("active");
						sqlConnect.updateRideStatus(Integer.parseInt(txtpnrideid.getText()), "canceled");
						try {
							sqlConnect.refreshRides();
							RidePanel newridepanel = new RidePanel(UsrName);
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
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("GER??");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sqlConnect.refreshRides();
					RidePanel newridepanel = new RidePanel(UsrName);
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
		btnNewButton_1_1.setBounds(272, 766, 232, 30);
		contentPane.add(btnNewButton_1_1);
	}
}
