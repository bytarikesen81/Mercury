package program;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.postgresql.util.PSQLException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import java.awt.Window.Type;

//Admin Paneli
public class AdminPanel extends JFrame {
	private JPanel contentPane;
	private JTextField dr_id;
	private JTextField dr_firstname;
	private JTextField dr_lastname;
	private JTextField sil_driver_ID;
	private JTextField searchDrivertf;
	private JTable drivers_table;
	private JTable users_table;
	private JTable useremp_table;
	private JTextField searchUsertf;
	private JTextField sil_User_ID;
	private JTable tableAllRides;

	DefaultTableModel dremp_model = new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
				"ID", "Ad", "Soyad", "Deneyim" ,"Toplam Puan"
		}
	) {
		boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};


	DefaultTableModel useremp_model = new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
				"Kullanıcı Adı", "Ad", "Soyad", "E-Mail" ,"Şifre"
		}
	) {
		boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};

	DefaultTableModel rideemp_model = new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
				"S\u00FCr\u00FC\u015FID", "Kullan\u0131c\u0131", "S\u00FCr\u00FCc\u00FCID", "Araba", "Nereden", "Nereye", "Ba\u015Flang\u0131\u00E7", "Biti\u015F", "Durum"
		}
	) {
		boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};


public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
		try {
			AdminPanel frame = new AdminPanel();
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

public AdminPanel() throws IOException, SQLException {
	setResizable(false);
	
	DatabaseConnect sqlConnect = new DatabaseConnect();

	setForeground(SystemColor.desktop);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 780, 533);
	contentPane = new JPanel();
	contentPane.setBackground(SystemColor.inactiveCaption);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	tabbedPane.setBounds(0, 39, 778, 485);
	contentPane.add(tabbedPane);

	JButton detailButton = new JButton("Show");
	detailButton.setBounds(465, 318, 48, 14);
	JPanel panelDrOp = new JPanel();
	panelDrOp.setBackground(SystemColor.inactiveCaption);
	tabbedPane.addTab("Sürücü İşlemleri", null,
	panelDrOp, null);
	tabbedPane.setBackgroundAt(0, SystemColor.inactiveCaption);
	panelDrOp.setLayout(null);
	JTextPane txtpnDriverDel = new JTextPane();
	txtpnDriverDel.setEditable(false);
	txtpnDriverDel.setBackground(SystemColor.inactiveCaption);
	txtpnDriverDel.setFont(new Font("Tahoma", Font.BOLD, 16));
	txtpnDriverDel.setText("Sürücü Detayları");
	txtpnDriverDel.setBounds(531, 203, 155, 26);
	panelDrOp.add(txtpnDriverDel);
	JTextPane txtpnSrcId = new JTextPane();
	txtpnSrcId.setEditable(false);
	txtpnSrcId.setText("Sürücü ID:");
	txtpnSrcId.setBackground(SystemColor.inactiveCaption);
	txtpnSrcId.setBounds(531, 240, 67, 20);
	panelDrOp.add(txtpnSrcId);
	sil_driver_ID = new JTextField();
	sil_driver_ID.setColumns(10);
	sil_driver_ID.setBounds(531, 266, 130, 20);
	panelDrOp.add(sil_driver_ID);
	JButton delete_dr = new JButton("Ara");
	delete_dr.setBackground(new Color(30, 144, 255));
	delete_dr.setFont(new Font("Tahoma", Font.BOLD, 13));
	delete_dr.setForeground(new Color(0, 0, 0));
	delete_dr.setBounds(671, 264, 67, 23);
	panelDrOp.add(delete_dr);
	JTextPane txtpnArama = new JTextPane();
	txtpnArama.setEditable(false);
	txtpnArama.setText("Arama:");
	txtpnArama.setBackground(SystemColor.inactiveCaption);
	txtpnArama.setBounds(10, 87, 55, 20);
	panelDrOp.add(txtpnArama);
	searchDrivertf = new JTextField();
	searchDrivertf.setColumns(10);
	searchDrivertf.setBounds(67, 87, 215, 20);
	panelDrOp.add(searchDrivertf);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(10, 118, 491, 332);
	panelDrOp.add(scrollPane);
	drivers_table = new JTable();
	drivers_table.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
				"ID", "Ad", "Soyad", "Deneyim" ,"Toplam Puan"
		}
	) {
		boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	});
	scrollPane.setViewportView(drivers_table);
	
		searchDrivertf.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			try {
				sqlConnect.refreshRides();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
			dremp_model = sqlConnect.searchDriver(searchDrivertf.getText());
			drivers_table.setModel(dremp_model);
			} catch (SQLException e1) {
			e1.printStackTrace();
			}
		}
		});
		
			delete_dr.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						sqlConnect.refreshRides();
						if(sqlConnect.detailDriver(sil_driver_ID.getText()) == null){
							infoBox("Belirtilen ID'ye sahip bir sürücü bulunamadı.", "Bilgi");
						}
						else {
							ArrayList<String> details = sqlConnect.detailDriver(sil_driver_ID.getText());
							DriverDetail drdet = new DriverDetail(details);
							drdet.setLocationRelativeTo(null); 
							drdet.setResizable(false);
							drdet.setUndecorated(true);
							drdet.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
							drdet.setVisible(true);
						}
						sil_driver_ID.setText("");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Geçersiz Input", "HATA", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			});
	JPanel panelDrReg = new JPanel();
	panelDrReg.setBackground(SystemColor.inactiveCaption);
	tabbedPane.addTab("Sürücü Ekle", null, panelDrReg, null);
	tabbedPane.setForegroundAt(1, SystemColor.desktop);
	tabbedPane.setBackgroundAt(1, SystemColor.inactiveCaption);
	panelDrReg.setLayout(null);
	JTextPane txtpnSurucuKaytFormu = new JTextPane();
	txtpnSurucuKaytFormu.setBackground(SystemColor.inactiveCaption);
	txtpnSurucuKaytFormu.setFont(new Font("Tahoma", Font.BOLD, 20));
	txtpnSurucuKaytFormu.setText("SÜRÜCÜ EKLE");
	txtpnSurucuKaytFormu.setBounds(282, 11, 163, 36);
	panelDrReg.add(txtpnSurucuKaytFormu);
	JTextPane txtpnID = new JTextPane();
	txtpnID.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtpnID.setEditable(false);
	txtpnID.setBackground(SystemColor.inactiveCaption);
	txtpnID.setText("S\u00FCr\u00FCc\u00FC ID:");
	txtpnID.setBounds(224, 152, 89, 20);
	panelDrReg.add(txtpnID);
	JTextPane txtpnAd = new JTextPane();
	txtpnAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtpnAd.setEditable(false);
	txtpnAd.setBackground(SystemColor.inactiveCaption);
	txtpnAd.setText("Ad\u0131:");
	txtpnAd.setBounds(224, 193, 89, 20);
	panelDrReg.add(txtpnAd);
	JTextPane txtpnSoyad = new JTextPane();
	txtpnSoyad.setFont(new Font("Tahoma", Font.PLAIN, 14));
	txtpnSoyad.setEditable(false);
	txtpnSoyad.setBackground(SystemColor.inactiveCaption);
	txtpnSoyad.setText("Soyad\u0131:");
	txtpnSoyad.setBounds(224, 232, 89, 20);
	panelDrReg.add(txtpnSoyad);
	dr_id = new JTextField();
	dr_id.setBounds(323, 152, 163, 20);
	panelDrReg.add(dr_id);
	dr_id.setColumns(10);
	dr_firstname = new JTextField();
	dr_firstname.setColumns(10);
	dr_firstname.setBounds(323, 193, 163, 20);
	panelDrReg.add(dr_firstname);
	dr_lastname = new JTextField();
	dr_lastname.setColumns(10);
	dr_lastname.setBounds(323, 232, 163, 20);
	panelDrReg.add(dr_lastname);
	JButton dr_ekle_buton = new JButton("EKLE");
	dr_ekle_buton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	dr_ekle_buton.setBounds(224, 320, 262, 49);
	panelDrReg.add(dr_ekle_buton);
	
	dr_ekle_buton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) throws NumberFormatException{
			String userName = dr_id.getText();
			String firstName = dr_firstname.getText();
			String lastName = dr_lastname.getText();
			try {
				if(Integer.parseInt(userName) <= 0) {
					infoBox("Geçersiz ID aralığı", "Error");
					return;
				}
			}catch(NumberFormatException n1) {
				JOptionPane.showMessageDialog(null, "Geçersiz Input", "HATA", JOptionPane.ERROR_MESSAGE);
			}
			if (firstName.length() == 0) {
				infoBox("İsim Alanı Boş Bırakılamaz", "Error");
				return;
			}
			if (lastName.length() == 0) {
				infoBox("Soyisim Alanı Boş Bırakılamaz", "Error");
				return;
			}
		
			Driver newDriver = new Driver(userName, firstName, lastName, "active", 0 ,0 );
			try {
				String addDriverResult= sqlConnect.addDriver(newDriver);
				infoBox(addDriverResult, "Bilgi");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				sqlConnect.refreshRides();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dr_id.setText("");
			dr_firstname.setText("");
			dr_lastname.setText("");
		}
	});

	JPanel panelRideDB = new JPanel();
	tabbedPane.addTab("Sürüş Veritabanı", null, panelRideDB, null);
	SpringLayout sl_panelRideDB = new SpringLayout();
	panelRideDB.setLayout(sl_panelRideDB);
	JScrollPane scrollUser_1 = new JScrollPane();
	sl_panelRideDB.putConstraint(SpringLayout.NORTH, scrollUser_1, -451, SpringLayout.SOUTH, panelRideDB);
	sl_panelRideDB.putConstraint(SpringLayout.WEST, scrollUser_1, 10, SpringLayout.WEST, panelRideDB);
	sl_panelRideDB.putConstraint(SpringLayout.SOUTH, scrollUser_1, -50, SpringLayout.SOUTH, panelRideDB);
	sl_panelRideDB.putConstraint(SpringLayout.EAST, scrollUser_1, -10, SpringLayout.EAST, panelRideDB);
	panelRideDB.add(scrollUser_1);
	tableAllRides = new JTable();
	tableAllRides.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"S\u00FCr\u00FC\u015FID", "Kullan\u0131c\u0131", "S\u00FCr\u00FCc\u00FCID", "Araba", "Nereden", "Nereye", "Ba\u015Flang\u0131\u00E7", "Biti\u015F", "Durum"
		}
	) {
		boolean[] columnEditables = new boolean[] {
			false, false, false, false, false, false, false, false, true
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	});
	scrollUser_1.setViewportView(tableAllRides);
	JButton btnRefAllRides = new JButton("YENİLE");
	sl_panelRideDB.putConstraint(SpringLayout.WEST, btnRefAllRides, 0, SpringLayout.WEST, scrollUser_1);
	sl_panelRideDB.putConstraint(SpringLayout.SOUTH, btnRefAllRides, 0, SpringLayout.SOUTH, panelRideDB);
	sl_panelRideDB.putConstraint(SpringLayout.EAST, btnRefAllRides, -10, SpringLayout.EAST, panelRideDB);
	btnRefAllRides.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				sqlConnect.refreshRides();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			listRideAllTable(sqlConnect,tableAllRides);
		}
	});
	sl_panelRideDB.putConstraint(SpringLayout.NORTH, btnRefAllRides, 6, SpringLayout.SOUTH, scrollUser_1);
	panelRideDB.add(btnRefAllRides);
	sqlConnect.refreshRides();
	listRideAllTable(sqlConnect,tableAllRides);
	JPanel panelUsrOp = new JPanel();
	panelUsrOp.setBackground(SystemColor.inactiveCaption);
	tabbedPane.addTab("Kullanıcı İşlemleri", null, panelUsrOp, null);
	SpringLayout sl_panelUsrOp = new SpringLayout();
	panelUsrOp.setLayout(sl_panelUsrOp);
	JScrollPane scrollUser = new JScrollPane();
	sl_panelUsrOp.putConstraint(SpringLayout.NORTH, scrollUser, 137, SpringLayout.NORTH, panelUsrOp);
	sl_panelUsrOp.putConstraint(SpringLayout.WEST, scrollUser, 10, SpringLayout.WEST, panelUsrOp);
	sl_panelUsrOp.putConstraint(SpringLayout.SOUTH, scrollUser, -10, SpringLayout.SOUTH, panelUsrOp);
	panelUsrOp.add(scrollUser);
	users_table = new JTable();
	users_table.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Kullan\u0131c\u0131 Ad\u0131", "Ad", "Soyad", "E-mail", "\u015Eifre"
		}
	) {
		boolean[] columnEditables = new boolean[] {
			false, false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	});
	scrollUser.setViewportView(users_table);
	searchUsertf = new JTextField();
	sl_panelUsrOp.putConstraint(SpringLayout.SOUTH, searchUsertf, -6, SpringLayout.NORTH, scrollUser);
	sl_panelUsrOp.putConstraint(SpringLayout.EAST, searchUsertf, -482, SpringLayout.EAST, panelUsrOp);
	searchUsertf.setColumns(10);
	panelUsrOp.add(searchUsertf);
	JTextPane txtpnArama_1 = new JTextPane();
	sl_panelUsrOp.putConstraint(SpringLayout.WEST, txtpnArama_1, 10, SpringLayout.WEST, panelUsrOp);
	sl_panelUsrOp.putConstraint(SpringLayout.SOUTH, txtpnArama_1, -6, SpringLayout.NORTH, scrollUser);
	sl_panelUsrOp.putConstraint(SpringLayout.WEST, searchUsertf, 6, SpringLayout.EAST, txtpnArama_1);
	txtpnArama_1.setText("Arama:");
	txtpnArama_1.setBackground(SystemColor.inactiveCaption);
	panelUsrOp.add(txtpnArama_1);
	JTextPane txtpnUserDel = new JTextPane();
	sl_panelUsrOp.putConstraint(SpringLayout.WEST, txtpnUserDel, 16, SpringLayout.EAST, scrollUser);
	sl_panelUsrOp.putConstraint(SpringLayout.EAST, txtpnUserDel, -46, SpringLayout.EAST, panelUsrOp);
	txtpnUserDel.setText("Kullanıcı Detayları");
	txtpnUserDel.setFont(new Font("Tahoma", Font.BOLD, 16));
	txtpnUserDel.setEditable(false);
	txtpnUserDel.setBackground(SystemColor.inactiveCaption);
	panelUsrOp.add(txtpnUserDel);
	JTextPane txtpnKullancAd = new JTextPane();
	sl_panelUsrOp.putConstraint(SpringLayout.EAST, scrollUser, -16, SpringLayout.WEST, txtpnKullancAd);
	sl_panelUsrOp.putConstraint(SpringLayout.SOUTH, txtpnUserDel, -6, SpringLayout.NORTH, txtpnKullancAd);
	sl_panelUsrOp.putConstraint(SpringLayout.WEST, txtpnKullancAd, 519, SpringLayout.WEST, panelUsrOp);
	txtpnKullancAd.setText("Kullanıcı Adı:");
	txtpnKullancAd.setEditable(false);
	txtpnKullancAd.setBackground(SystemColor.inactiveCaption);
	panelUsrOp.add(txtpnKullancAd);
	sil_User_ID = new JTextField();
	sl_panelUsrOp.putConstraint(SpringLayout.WEST, sil_User_ID, 16, SpringLayout.EAST, scrollUser);
	sl_panelUsrOp.putConstraint(SpringLayout.SOUTH, txtpnKullancAd, -6, SpringLayout.NORTH, sil_User_ID);
	sl_panelUsrOp.putConstraint(SpringLayout.SOUTH, sil_User_ID, -168, SpringLayout.SOUTH, panelUsrOp);
	sil_User_ID.setColumns(10);
	panelUsrOp.add(sil_User_ID);
	JButton delete_usr = new JButton("Ara");
	delete_usr.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	});
	sl_panelUsrOp.putConstraint(SpringLayout.WEST, delete_usr, 643, SpringLayout.WEST, panelUsrOp);
	sl_panelUsrOp.putConstraint(SpringLayout.EAST, sil_User_ID, -6, SpringLayout.WEST, delete_usr);
	sl_panelUsrOp.putConstraint(SpringLayout.NORTH, delete_usr, -3, SpringLayout.NORTH, sil_User_ID);
	sl_panelUsrOp.putConstraint(SpringLayout.EAST, delete_usr, -10, SpringLayout.EAST, panelUsrOp);
	delete_usr.setBackground(new Color(30, 144, 255));
	delete_usr.setForeground(new Color(0, 0, 0));
	delete_usr.setFont(new Font("Tahoma", Font.BOLD, 13));
	panelUsrOp.add(delete_usr);
	
		searchUsertf.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			try {
				sqlConnect.refreshRides();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				useremp_model = sqlConnect.scanUser(searchUsertf.getText());
				users_table.setModel(useremp_model);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		});
		delete_usr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					sqlConnect.refreshRides();
				if(sqlConnect.detailUser(sil_User_ID.getText()) == null)
					infoBox("Belirtilen kullanıcı adına sahip bir kullanıcı bulunamadı. ", "Bilgi");
				else {
					ArrayList<String> details = sqlConnect.detailUser(sil_User_ID.getText());
					UserDetail usrdet = new UserDetail("admin",details);
					usrdet.setLocationRelativeTo(null); 
					usrdet.setResizable(false);
					usrdet.setUndecorated(true);
					usrdet.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
					usrdet.setVisible(true);
				}
				sil_User_ID.setText("");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Geçersiz Input", "HATA", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	JButton btnNewButton_1 = new JButton("Ana Menü");
	btnNewButton_1.addActionListener(new ActionListener() {
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
	btnNewButton_1.setBounds(535, 11, 233, 23);
	contentPane.add(btnNewButton_1);
	}
	public void listRideAllTable(DatabaseConnect sqlConnect,JTable RideTable) {
		try {
				DefaultTableModel newModel = sqlConnect.listAllRides();
				RideTable.setModel(newModel);
				} catch (SQLException e1) {
				e1.printStackTrace();
			}
	}
}
