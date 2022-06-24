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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.util.*;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

//Tum genel surus islemlerinin yapildigi en kapsamli arayuz
public class RidePanel extends JFrame {
	private static User user;
	private JPanel contentPane;
	private Date curDate = new Date();
	private static String UsrName;
	private static String selDate;
	private static String butDate;
	private JTable userRides;
	DefaultTableModel empty_model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nereden", "Nereye", "Ba\u015Flang\u0131\u00E7", "Biti\u015F", "Araba", "S\u00FCr\u00FCc\u00FC", "Durum"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
	};
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
				try {
					RidePanel frame = new RidePanel(UsrName);
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
	public RidePanel(String UsrName) throws IOException{
		setResizable(false);
		RidePanel.UsrName = UsrName;
		DatabaseConnect sqlConnect = new DatabaseConnect();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy,M,d,HH:mm");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 821, 580);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 72, 805, 472);
		contentPane.add(tabbedPane);
		
		JPanel panelStart = new JPanel();
		panelStart.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Sürüş Başlat", null, panelStart, null);
		SpringLayout sl_panelStart = new SpringLayout();
		panelStart.setLayout(sl_panelStart);
	
		
		JLabel lblLocation = new JLabel("Lokasyon Seçimi");
		sl_panelStart.putConstraint(SpringLayout.WEST, lblLocation, 10, SpringLayout.WEST, panelStart);
		sl_panelStart.putConstraint(SpringLayout.EAST, lblLocation, -577, SpringLayout.EAST, panelStart);
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 14));
		sl_panelStart.putConstraint(SpringLayout.NORTH, lblLocation, 10, SpringLayout.NORTH, panelStart);
		sl_panelStart.putConstraint(SpringLayout.SOUTH, lblLocation, 43, SpringLayout.NORTH, panelStart);
		panelStart.add(lblLocation);
		
		JTextPane txtpnNereden = new JTextPane();
		txtpnNereden.setEditable(false);
		sl_panelStart.putConstraint(SpringLayout.NORTH, txtpnNereden, 6, SpringLayout.SOUTH, lblLocation);
		sl_panelStart.putConstraint(SpringLayout.WEST, txtpnNereden, 30, SpringLayout.WEST, panelStart);
		txtpnNereden.setBackground(new Color(255, 255, 255));
		txtpnNereden.setText("Nereden:");
		panelStart.add(txtpnNereden);
		
		JTextPane txtpnNereye = new JTextPane();
		txtpnNereye.setEditable(false);
		sl_panelStart.putConstraint(SpringLayout.WEST, txtpnNereye, 290, SpringLayout.WEST, panelStart);
		sl_panelStart.putConstraint(SpringLayout.EAST, txtpnNereden, -99, SpringLayout.WEST, txtpnNereye);
		sl_panelStart.putConstraint(SpringLayout.NORTH, txtpnNereye, 0, SpringLayout.NORTH, txtpnNereden);
		txtpnNereye.setText("Nereye");
		txtpnNereye.setBackground(new Color(255, 255, 255));
		panelStart.add(txtpnNereye);
		
		JComboBox cBox_LocFrom = new JComboBox();
		cBox_LocFrom.setBackground(new Color(255, 255, 240));
		sl_panelStart.putConstraint(SpringLayout.SOUTH, txtpnNereden, -6, SpringLayout.NORTH, cBox_LocFrom);
		cBox_LocFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		sl_panelStart.putConstraint(SpringLayout.NORTH, cBox_LocFrom, 75, SpringLayout.NORTH, panelStart);
		sl_panelStart.putConstraint(SpringLayout.WEST, cBox_LocFrom, 30, SpringLayout.WEST, panelStart);
		cBox_LocFrom.setModel(new DefaultComboBoxModel(new String[] {"İstanbul Havalimanı", "Sabiha Gökçen Havalimanı", "Atatürk Havalimanı"}));
		cBox_LocFrom.setSelectedIndex(0);
		panelStart.add(cBox_LocFrom);
		
		JComboBox cBox_LocTo = new JComboBox();
		cBox_LocTo.setBackground(new Color(255, 255, 240));
		sl_panelStart.putConstraint(SpringLayout.NORTH, cBox_LocTo, 0, SpringLayout.NORTH, cBox_LocFrom);
		sl_panelStart.putConstraint(SpringLayout.WEST, cBox_LocTo, 0, SpringLayout.WEST, txtpnNereye);
		sl_panelStart.putConstraint(SpringLayout.EAST, cBox_LocTo, 260, SpringLayout.EAST, cBox_LocFrom);
		cBox_LocTo.setModel(new DefaultComboBoxModel(new String[] {"İstanbul Havalimanı", "Sabiha Gökçen Havalimanı", "Atatürk Havalimanı"}));
		cBox_LocTo.setSelectedIndex(1);
		panelStart.add(cBox_LocTo);
		
		JLabel lblTime = new JLabel("Sefer Seçimi");
		sl_panelStart.putConstraint(SpringLayout.NORTH, lblTime, 20, SpringLayout.SOUTH, cBox_LocFrom);
		sl_panelStart.putConstraint(SpringLayout.WEST, lblTime, 0, SpringLayout.WEST, lblLocation);
		sl_panelStart.putConstraint(SpringLayout.EAST, lblTime, 0, SpringLayout.EAST, lblLocation);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelStart.add(lblTime);
		
		JComboBox cBox_Time = new JComboBox();
		cBox_Time.setBackground(new Color(255, 255, 240));
		sl_panelStart.putConstraint(SpringLayout.NORTH, cBox_Time, 148, SpringLayout.NORTH, panelStart);
		sl_panelStart.putConstraint(SpringLayout.SOUTH, lblTime, -6, SpringLayout.NORTH, cBox_Time);
		sl_panelStart.putConstraint(SpringLayout.EAST, cBox_LocFrom, 0, SpringLayout.EAST, cBox_Time);
		sl_panelStart.putConstraint(SpringLayout.WEST, cBox_Time, 30, SpringLayout.WEST, panelStart);
		sl_panelStart.putConstraint(SpringLayout.EAST, cBox_Time, 20, SpringLayout.EAST, lblLocation);
		cBox_Time.setModel(new DefaultComboBoxModel(new String[0]));
		CreateTimeCbox(cBox_Time);
		panelStart.add(cBox_Time);
		cBox_Time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblClassSel = new JLabel("Sınıf Seçimi");
		sl_panelStart.putConstraint(SpringLayout.NORTH, lblClassSel, 24, SpringLayout.SOUTH, cBox_Time);
		sl_panelStart.putConstraint(SpringLayout.WEST, lblClassSel, 0, SpringLayout.WEST, lblLocation);
		sl_panelStart.putConstraint(SpringLayout.EAST, lblClassSel, 0, SpringLayout.EAST, txtpnNereden);
		lblClassSel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelStart.add(lblClassSel);
		
		JComboBox cBoxClass = new JComboBox();
		cBoxClass.setBackground(new Color(255, 255, 240));
		sl_panelStart.putConstraint(SpringLayout.NORTH, cBoxClass, 14, SpringLayout.SOUTH, lblClassSel);
		sl_panelStart.putConstraint(SpringLayout.WEST, cBoxClass, 0, SpringLayout.WEST, txtpnNereden);
		sl_panelStart.putConstraint(SpringLayout.EAST, cBoxClass, 0, SpringLayout.EAST, cBox_Time);
		cBoxClass.setModel(new DefaultComboBoxModel(new String[] {"Ekonomi", "Standard", "VIP"}));
		cBoxClass.setSelectedIndex(0);
		panelStart.add(cBoxClass);
		
		JButton btnDrive = new JButton("SÜR");
		btnDrive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int hour,min;
				String str = cBox_Time.getItemAt(cBox_Time.getSelectedIndex()).toString();
				String selHourMin[] = str.split(":");
				c.set(curDate.getYear()+1900, curDate.getMonth(), curDate.getDate(), Integer.parseInt(selHourMin[0]), Integer.parseInt(selHourMin[1]));
				RidePanel.selDate = format.format(c.getTime());
				
				c.set(curDate.getYear()+1900, curDate.getMonth(), curDate.getDate(), curDate.getHours(), curDate.getMinutes());
				RidePanel.butDate = format.format(c.getTime());
				
				if(cBox_LocFrom.getSelectedIndex() != cBox_LocTo.getSelectedIndex()) {
					if(RidePanel.butDate.compareTo(RidePanel.selDate) < 0) {
						StartRide startridepanel;
						try {
									startridepanel = new StartRide(UsrName,
										cBox_LocFrom.getItemAt(cBox_LocFrom.getSelectedIndex()).toString(),
										cBox_LocTo.getItemAt(cBox_LocTo.getSelectedIndex()).toString(),
										cBox_Time.getItemAt(cBox_Time.getSelectedIndex()).toString(),
										cBoxClass.getItemAt(cBoxClass.getSelectedIndex()).toString()
									);
									startridepanel.setLocationRelativeTo(null); 
									startridepanel.setResizable(false);
									startridepanel.setUndecorated(true);
									startridepanel.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
									startridepanel.setVisible(true);
									dispose();
						} catch (IOException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
								
					}
					else
						JOptionPane.showMessageDialog(null, "Seçtiğiniz Seferin Saati Geçti", "UYARI", JOptionPane.WARNING_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, 
                            "Seçilen Lokasyonlar Aynı Olamaz", 
                            "UYARI", 
                            JOptionPane.WARNING_MESSAGE);
				}
				cBox_Time.removeAllItems();
				CreateTimeCbox(cBox_Time);
			}
		});
		
		sl_panelStart.putConstraint(SpringLayout.NORTH, btnDrive, -54, SpringLayout.SOUTH, panelStart);
		sl_panelStart.putConstraint(SpringLayout.WEST, btnDrive, 10, SpringLayout.WEST, panelStart);
		sl_panelStart.putConstraint(SpringLayout.SOUTH, btnDrive, -10, SpringLayout.SOUTH, panelStart);
		sl_panelStart.putConstraint(SpringLayout.EAST, btnDrive, 547, SpringLayout.EAST, cBox_Time);
		panelStart.add(btnDrive);

		//Suruslerim Paneli
		JPanel panelList = new JPanel();
		panelList.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Sürüşlerim", null, panelList, null);
		SpringLayout sl_panelList = new SpringLayout();
		panelList.setLayout(sl_panelList);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_panelList.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, panelList);
		sl_panelList.putConstraint(SpringLayout.SOUTH, scrollPane, 373, SpringLayout.NORTH, panelList);
		sl_panelList.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, panelList);
		panelList.add(scrollPane);
		
		userRides = new JTable();
		userRides.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Nereden", "Nereye", "Ba\u015Flang\u0131\u00E7", "Biti\u015F", "Araba", "S\u00FCr\u00FCc\u00FC", "Durum"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			boolean[] columnResizables = new boolean[] {
					false, false, false, false, false, false, false, false
			};
			public boolean isCellResizable(int row, int column) {
				return columnEditables[column];
			}
		});
		userRides.getColumnModel().getColumn(0).setResizable(false);
		userRides.getColumnModel().getColumn(0).setPreferredWidth(50);
		userRides.getColumnModel().getColumn(0).setMinWidth(50);
		userRides.getColumnModel().getColumn(0).setMaxWidth(50);
		userRides.getColumnModel().getColumn(1).setResizable(false);
		userRides.getColumnModel().getColumn(1).setPreferredWidth(120);
		userRides.getColumnModel().getColumn(1).setMinWidth(120);
		userRides.getColumnModel().getColumn(1).setMaxWidth(120);
		userRides.getColumnModel().getColumn(2).setResizable(false);
		userRides.getColumnModel().getColumn(2).setPreferredWidth(120);
		userRides.getColumnModel().getColumn(2).setMinWidth(120);
		userRides.getColumnModel().getColumn(2).setMaxWidth(120);
		userRides.getColumnModel().getColumn(3).setResizable(false);
		userRides.getColumnModel().getColumn(3).setPreferredWidth(100);
		userRides.getColumnModel().getColumn(3).setMinWidth(100);
		userRides.getColumnModel().getColumn(3).setMaxWidth(100);
		userRides.getColumnModel().getColumn(4).setResizable(false);
		userRides.getColumnModel().getColumn(4).setPreferredWidth(100);
		userRides.getColumnModel().getColumn(4).setMinWidth(100);
		userRides.getColumnModel().getColumn(4).setMaxWidth(100);
		userRides.getColumnModel().getColumn(5).setResizable(false);
		userRides.getColumnModel().getColumn(5).setMinWidth(75);
		userRides.getColumnModel().getColumn(5).setMaxWidth(75);
		userRides.getColumnModel().getColumn(6).setResizable(false);
		userRides.getColumnModel().getColumn(6).setMinWidth(75);
		userRides.getColumnModel().getColumn(6).setMaxWidth(75);
		userRides.getColumnModel().getColumn(7).setResizable(false);
		userRides.getColumnModel().getColumn(7).setPreferredWidth(90);
		userRides.getColumnModel().getColumn(7).setMinWidth(90);
		userRides.getColumnModel().getColumn(7).setMaxWidth(90);
		scrollPane.setViewportView(userRides);
		
		//Surusleri Otomatik Guncelle
		try {
			sqlConnect.refreshRides();
			listRideTable(sqlConnect,userRides,UsrName);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		JButton btnNewButton = new JButton("YENİLE");
		sl_panelList.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, btnNewButton);
		sl_panelList.putConstraint(SpringLayout.EAST, btnNewButton, -521, SpringLayout.EAST, panelList);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					sqlConnect.refreshRides();
					listRideTable(sqlConnect,userRides,UsrName);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		sl_panelList.putConstraint(SpringLayout.NORTH, btnNewButton, -48, SpringLayout.SOUTH, panelList);
		sl_panelList.putConstraint(SpringLayout.WEST, btnNewButton, 10, SpringLayout.WEST, panelList);
		sl_panelList.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, panelList);
		panelList.add(btnNewButton);
		
		JButton btnPuanla = new JButton("PUANLA");
		sl_panelList.putConstraint(SpringLayout.NORTH, btnPuanla, 23, SpringLayout.SOUTH, scrollPane);
		sl_panelList.putConstraint(SpringLayout.WEST, btnPuanla, 6, SpringLayout.EAST, btnNewButton);
		sl_panelList.putConstraint(SpringLayout.SOUTH, btnPuanla, -10, SpringLayout.SOUTH, panelList);
		btnPuanla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RatingPanel ratingpanel;
				try {
							ratingpanel = new RatingPanel(RidePanel.UsrName);
							ratingpanel.setLocationRelativeTo(null); 
							ratingpanel.setResizable(false);
							ratingpanel.setUndecorated(true);
							ratingpanel.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
							ratingpanel.setVisible(true);
							dispose();
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelList.add(btnPuanla);
		
		JButton btnNewButton_1_1 = new JButton("İPTAL ET");
		sl_panelList.putConstraint(SpringLayout.NORTH, btnNewButton_1_1, 23, SpringLayout.SOUTH, scrollPane);
		sl_panelList.putConstraint(SpringLayout.SOUTH, btnNewButton_1_1, -10, SpringLayout.SOUTH, panelList);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelPanel cancelpanel;
				try {
							cancelpanel = new CancelPanel(RidePanel.UsrName);
							cancelpanel.setLocationRelativeTo(null); 
							cancelpanel.setResizable(false);
							cancelpanel.setUndecorated(true);
							cancelpanel.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
							cancelpanel.setVisible(true);
							dispose();
				} catch (IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		sl_panelList.putConstraint(SpringLayout.EAST, btnPuanla, -6, SpringLayout.WEST, btnNewButton_1_1);
		sl_panelList.putConstraint(SpringLayout.WEST, btnNewButton_1_1, 540, SpringLayout.WEST, panelList);
		sl_panelList.putConstraint(SpringLayout.EAST, btnNewButton_1_1, -10, SpringLayout.EAST, panelList);
		panelList.add(btnNewButton_1_1);
		
		JPanel panelRideShare = new JPanel();
		tabbedPane.addTab("RideShare", null, panelRideShare, null);
		
		JLabel lblNewLabel_1 = new JLabel("RideShare is currently not available in your location.");
		panelRideShare.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("SÜRÜŞ MENÜSÜ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 152, 50);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Kullanıcı Menüsü");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						UserPanel userpanel = new UserPanel(UsrName);
						userpanel.setLocationRelativeTo(null); 
						userpanel.setResizable(false);
						userpanel.setUndecorated(true);
						userpanel.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
						userpanel.setVisible(true);
						dispose();
					} catch (IOException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		});
		btnNewButton_1.setBounds(562, 28, 233, 23);
		contentPane.add(btnNewButton_1);
	}
		private void CreateTimeCbox(JComboBox cBox) {
			for(int i=(curDate.getHours()+1)%24; i<24; i++) {
				cBox.addItem(i+":00");
		}
	}
		public void listRideTable(DatabaseConnect sqlConnect,JTable RideTable,String UsrName) {
			try {
				DefaultTableModel newModel = sqlConnect.listRide(UsrName);
				RideTable.setModel(newModel);
				} catch (SQLException e1) {
				e1.printStackTrace();
				}
		}
}
