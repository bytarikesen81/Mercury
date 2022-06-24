package program;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.awt.HeadlessException;
import java.sql.Connection;

// Veritabanina erisim ve gerekli tum DB islemlerinin saglandigi class
public class DatabaseConnect {
	private final String url = "jdbc:postgresql://localhost/mercury";
	private final String user = "postgres";
	private final String password = "tarikesen";
	public Connection connection;
	static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
	
	public DatabaseConnect() {
		try{
			this.connection = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException e) {
			//JOptionPane.showMessageDialog(null, "Veritabanina baglanilamadi.", "HATA",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}
	
	//Kullanici Ekle
	public String addUser(User usr) throws SQLException {
		Statement st = this.connection.createStatement();
		ResultSet uNameCheck = st.executeQuery("SELECT * FROM accounts WHERE accountid = '" + usr.getUserName() + "'");
		if (uNameCheck.next()) {
			return "Kullanıcı adı başka bir kullanıcı tarafından daha önce alınmış";
		}
		ResultSet mailCheck = st.executeQuery("SELECT * FROM accounts WHERE email = '" + usr.geteMail() + "'");
		if (mailCheck.next()) {
			return "Bu mail adresine sahip bir hesap zaten bulunmakta.";
		}
		String userQuery = "INSERT INTO accounts (accountid, firstname, lastname, email, accounttype, userpassword)\r\n" +
				"VALUES ('" + usr.getUserName() + "', '"+ usr.getFirstName() + "', '" + usr.getLastName() + "', '"+ usr.geteMail() +"', 'user', '"+ usr.getUserPassword() +"')";
		
		st.executeUpdate(userQuery);
		
		uNameCheck = st.executeQuery("SELECT * FROM accounts WHERE accountid= '" + usr.getUserName() + "'");
		if (uNameCheck.next()) {
			return "Kullanıcı başarıyla eklendi.";
		}
			return "Kullanıcı eklenmesi başarısız.";
	}
	
	//Surucu Ekle
	public String addDriver(Driver dr) throws SQLException{
		Statement st = this.connection.createStatement();
		ResultSet drIDCheck = st.executeQuery("SELECT * FROM drivers WHERE driverid = '" + dr.getDriverID() + "'");
		if (drIDCheck.next()) {
			return "Surucu IDsi Zaten Kullanımda.";
		}
		String driverQuery = "INSERT INTO drivers (driverid, firstname, lastname, status, experience, points)\r\n" +
				"VALUES ('" + dr.getDriverID() + "', '"+ dr.getName() + "', '" + dr.getSurname() + "', 'active', 0,0)";
		
		st.executeUpdate(driverQuery);
		
		drIDCheck = st.executeQuery("SELECT * FROM drivers WHERE driverid= '" + dr.getDriverID() + "'");
		if (drIDCheck.next()) {
			return "Sürücü başarıyla eklendi.";
		}
			return "Sürücü eklenmesi başarısız.";
	}
	
	//Surus Ekle
	public String addRide(Ride ride) throws SQLException{
		Statement st = this.connection.createStatement();
		ResultSet rideIDCheck = st.executeQuery("SELECT * FROM rides WHERE rideid = '" + ride.getRideID() + "'");
		if (rideIDCheck.next()) {
			return "Surus IDsi Zaten Kullanimda!";
		}
		String rideQuery = "INSERT INTO rides (rideid, accountid, driverid, locFrom, locTo, car, timeStart, timeEnd, status)\r\n" +
				"VALUES ('" + ride.getRideID() + "', '"+ ride.getUserID() + "', '" + ride.getDriver().getDriverID() + "', '"+ ride.getLocFrom() + "', '" + ride.getLocTo() + "', '" + ride.getCarClass()+"', '"+
				ride.getTimeStart()+ "', '" + ride.getTimeEnd() + "', '" + ride.getStatus()+"')";
		st.executeUpdate(rideQuery);
		
		rideIDCheck = st.executeQuery("SELECT * FROM rides WHERE rideid = '" + ride.getRideID() + "'");
		if (rideIDCheck.next()) {
			return "Sürüş planlandı.";
		}
		return "Sürüş eklenmesi başarısız.";
	}
	
	//Hesaba Baglan
	public String login(String userName, String password) throws SQLException {
		Statement st = this.connection.createStatement();
		ResultSet uNameCheck = st.executeQuery("SELECT * FROM accounts WHERE accountid = '" + userName + "'");
		if (!uNameCheck.next()) {
			return "Geçersiz kullanıcı adı veya şifre";
		}
		ResultSet passCheck = st.executeQuery("SELECT * FROM accounts WHERE accountid = '" + userName + "'");
		passCheck.next();
		if (passCheck.getString(6).equals(password) == false) {
			return "Hatalı şifre";
		}
		else {
			return passCheck.getString(5);
		}
	}
	
	//Tablo icin surucu Ara
	public DefaultTableModel searchDriver(String letters) throws SQLException {
		DefaultTableModel final_model = new DefaultTableModel(
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
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM drivers WHERE firstname LIKE'" + letters + "%'");
		if (!searchResult.next()) {
			return final_model;
		}
		int counter = 0;
		final_model.insertRow(counter, new Object[]{
			searchResult.getString(1), searchResult.getString(2),
			searchResult.getString(3), searchResult.getString(4),
			searchResult.getString(5)
		});
		counter++;
		while (searchResult.next()) {
			final_model.insertRow(counter, new Object[]{
				searchResult.getString(1), searchResult.getString(2),
				searchResult.getString(3), searchResult.getString(4),
				searchResult.getString(5)
			});
			counter++;
		}
		return final_model;
	}
	
	//Tablo icin kullanici Ara
	public DefaultTableModel scanUser(String letters) throws SQLException {
		DefaultTableModel final_model = new DefaultTableModel(
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
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM accounts WHERE accountid LIKE'" + letters + "%'");
		if (!searchResult.next()) {
			return final_model;
		}
		int counter = 0;
		final_model.insertRow(counter, new Object[]{
			searchResult.getString(1), searchResult.getString(2),
			searchResult.getString(3), searchResult.getString(4),
			searchResult.getString(6)
			});
		counter++;
		while (searchResult.next()) {
			final_model.insertRow(counter, new Object[]{
				searchResult.getString(1), searchResult.getString(2),
				searchResult.getString(3), searchResult.getString(4),
				searchResult.getString(6)
			});
			counter++;
		}
		return final_model;
	}
		
		
		// Kullanici Ara
	public DefaultTableModel searchUser(String letters) throws SQLException{
		DefaultTableModel final_model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Kullan\u0131c\u0131 Ad\u0131", "Ad", "Soyad", "E-Mail"
			}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM accounts WHERE accounttype = 'user' and userstatus = 'Active' and accountid LIKE '" + letters + "%'");
		if (!searchResult.next()) {
			return final_model;
		}
		int counter = 0;
			final_model.insertRow(counter, new Object[]{
				searchResult.getString(1), searchResult.getString(2),
				searchResult.getString(3), searchResult.getString(4),
				searchResult.getString(5)
			});
			counter++;
		while (searchResult.next()) {
			final_model.insertRow(counter, new Object[]{
				searchResult.getString(1), searchResult.getString(2),
				searchResult.getString(3), searchResult.getString(4),
				searchResult.getString(5)
			});
			counter++;
		}
		return final_model;
	}
	
	//Kullaniciya gore surus listele
	public DefaultTableModel listRide(String UsrName) throws SQLException {
		DefaultTableModel final_model = new DefaultTableModel(
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
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM rides WHERE accountid = '" + UsrName + "'");
		if (!searchResult.next()) {
			return final_model;
		}
		int counter = 0;
		final_model.insertRow(counter, new Object[]{
			searchResult.getString(9), searchResult.getString(4),
			searchResult.getString(5), searchResult.getString(7),
			searchResult.getString(8),searchResult.getString(3),
			searchResult.getString(2),searchResult.getString(6)
		});
		counter++;
		while (searchResult.next()) {
			final_model.insertRow(counter, new Object[]{
				searchResult.getString(9), searchResult.getString(4),
				searchResult.getString(5), searchResult.getString(7),
				searchResult.getString(8),searchResult.getString(3),
				searchResult.getString(2),searchResult.getString(6)
			});
			counter++;
		}
		return final_model;
	}
	
	//Tum surusleri listele
	public DefaultTableModel listAllRides() throws SQLException {
		DefaultTableModel final_model = new DefaultTableModel(
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
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM rides");
		if (!searchResult.next()) {
			return final_model;
		}
		int counter = 0;
			final_model.insertRow(counter, new Object[]{
				searchResult.getString(9), searchResult.getString(1),
				searchResult.getString(2), searchResult.getString(3),
				searchResult.getString(4),searchResult.getString(5),
				searchResult.getString(7),searchResult.getString(8),searchResult.getString(6)
			});
			counter++;
		while (searchResult.next()) {
			final_model.insertRow(counter, new Object[]{
				searchResult.getString(9), searchResult.getString(1),
				searchResult.getString(2), searchResult.getString(3),
				searchResult.getString(4),searchResult.getString(5),
				searchResult.getString(7),searchResult.getString(8),searchResult.getString(6)
			});
			counter++;
		}
		return final_model;
	}
	
	//Surusleri yenile
	public void refreshRides() throws SQLException{
		int driverID;
		Date curDate = new Date();
		String timeStart,timeEnd,curTime;
		Calendar c = Calendar.getInstance();
		double dist,duration;
		int hour,min;
		SimpleDateFormat format = new SimpleDateFormat("yyyy,M,d,HH:mm");
		Statement st = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet searchResult = st.executeQuery("SELECT * FROM rides");
		if(!searchResult.next()) return;
		if(searchResult.getString(6).compareTo("canceled") != 0 && searchResult.getString(6).compareTo("finished") != 0 && searchResult.getString(6).compareTo("rated") != 0) {
			//JOptionPane.showMessageDialog(null, "Guncellendi:"+searchResult.getString(3)+","+searchResult.getString(6), "UYARI", JOptionPane.WARNING_MESSAGE);
			timeStart = searchResult.getString(7);
			timeEnd = searchResult.getString(8);
			c.set(curDate.getYear()+1900, curDate.getMonth(), curDate.getDate(), curDate.getHours(), curDate.getMinutes());
			curTime = format.format(c.getTime());
			driverID = searchDriverInRide(searchResult.getInt(9));
			if(curTime.compareTo(timeStart) < 0) {
			    searchResult.updateString("status", "waiting");				
			    updateDriverStatus(driverID,"preparing");
			    searchResult.updateRow();
			}
			else if(curTime.compareTo(timeStart) >= 0 && curTime.compareTo(timeEnd) <= 0) {
				searchResult.updateString("status", "in progress");
				updateDriverStatus(driverID,"driving");
			    searchResult.updateRow();
			}
			else {
				searchResult.updateString("status", "finished");
				
				//Sure-Mesafe hesabı yap ve deneyime ekle
				dist = Ride.calcDistance(searchResult.getString(4),searchResult.getString(5));
				duration = Ride.calcTime(dist,searchResult.getString(3));
				hour = (int)Math.floor(duration);
				min = (int)Math.ceil((duration-Math.floor(duration))*60);
				
				updateDriverStatus(driverID,"active");
			    addDriverExp(driverID,((hour*60)+min));
			    searchResult.updateRow();
			}
		}
		while(searchResult.next()) {
			if(searchResult.getString(6).compareTo("canceled") != 0 && searchResult.getString(6).compareTo("finished") != 0 && searchResult.getString(6).compareTo("rated") != 0) {
				//JOptionPane.showMessageDialog(null, "Guncellendi:"+searchResult.getString(3)+","+searchResult.getString(6), "UYARI", JOptionPane.WARNING_MESSAGE);
				timeStart = searchResult.getString(7);
				timeEnd = searchResult.getString(8);
				c.set(curDate.getYear()+1900, curDate.getMonth(), curDate.getDate(), curDate.getHours(), curDate.getMinutes());
				curTime = format.format(c.getTime());
				driverID = searchDriverInRide(searchResult.getInt(9));
				if(curTime.compareTo(timeStart) < 0) {
				    searchResult.updateString("status", "waiting");				
				    updateDriverStatus(driverID,"preparing");
				    searchResult.updateRow();
				}
				else if(curTime.compareTo(timeStart) >= 0 && curTime.compareTo(timeEnd) <= 0) {
					searchResult.updateString("status", "in progress");
					updateDriverStatus(driverID,"driving");
				    searchResult.updateRow();
				}
				else {
					searchResult.updateString("status", "finished");
					
					//Sure-Mesafe hesabı yap ve deneyime ekle
					dist = Ride.calcDistance(searchResult.getString(4),searchResult.getString(5));
					duration = Ride.calcTime(dist,searchResult.getString(3));
					hour = (int)Math.floor(duration);
					min = (int)Math.ceil((duration-Math.floor(duration))*60);
					
					updateDriverStatus(driverID,"active");
				    addDriverExp(driverID,((hour*60)+min));
				    searchResult.updateRow();
				}
			}
		}
	}
	
	//Surus durum guncelle
	public void updateRideStatus(int RideID,String Status) throws SQLException {
		Statement st = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet searchResult = st.executeQuery("SELECT * FROM rides WHERE rideid = '"+ RideID +"'");
		if(!searchResult.next()) return;
		searchResult.updateString("status", Status);
		searchResult.updateRow();
	}
	
	//Surusun soforunu ara
	public int searchDriverInRide(int rideID) throws SQLException{
		Statement st = this.connection.createStatement();
		ResultSet searchRide;
		searchRide = st.executeQuery("SELECT * FROM rides WHERE rideid = '"+ rideID +"'");
		if(!searchRide.next()) return 0;
		return searchRide.getInt(2);
	}
	
	//Puanlama icin surus ara
	public ArrayList<String> searchRidetoRate(int rideID,String usrName) throws SQLException {
		ArrayList<String> rideInfo = new ArrayList<String>();
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM rides WHERE rideid = '"+ rideID +"' and accountid = '"+ usrName +"'");
		if(!searchResult.next()) {
			JOptionPane.showMessageDialog(null, "Sürüş Bulunamadı", "UYARI", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		else if(searchResult.getString(6).compareTo("rated") == 0) {
			JOptionPane.showMessageDialog(null, "Bu sürüş zaten daha önce oylanmış.", "UYARI", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		else if(searchResult.getString(6).compareTo("finished") != 0) {
			JOptionPane.showMessageDialog(null, "Sürüş henüz devam etmekte veya iptal edilmiş.\nOylamak için lütfen sürüşünüzün tamamlanmasını bekleyin.", "UYARI", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		else {
			//JOptionPane.showMessageDialog(null, "ID Bulundu!,"+searchResult.getInt(9)+"," + searchResult.getString(1), "UYARI", JOptionPane.WARNING_MESSAGE);
			rideInfo.add(searchResult.getString(2));
			rideInfo.add(searchResult.getString(3));
			rideInfo.add(searchResult.getString(4));
			rideInfo.add(searchResult.getString(5));
			rideInfo.add(searchResult.getString(7));
			rideInfo.add(searchResult.getString(8));
			rideInfo.add(searchResult.getString(9));
			return rideInfo;
		}
		
	}
	
	//Iptal icin surus ara
	public ArrayList<String> searchRidetoCancel(int rideID,String usrName) throws SQLException {
		ArrayList<String> rideInfo = new ArrayList<String>();
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM rides WHERE rideid = '"+ rideID +"' and accountid = '"+ usrName +"'");
		if(!searchResult.next()) {
			JOptionPane.showMessageDialog(null, "Sürüş Bulunamadı", "UYARI", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		else if(searchResult.getString(6).compareTo("canceled") == 0) {
			JOptionPane.showMessageDialog(null, "Bu sürüş zaten daha önce iptal edilmiş.", "UYARI", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		else if(searchResult.getString(6).compareTo("waiting") != 0) {
			JOptionPane.showMessageDialog(null, "Sürüş henüz devam etmekte veya tamamlanmış.\nİptal için sürüşün henüz başlamamış olması gerekmektedir.", "UYARI", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		else {
			//JOptionPane.showMessageDialog(null, "ID Bulundu!,"+searchResult.getInt(9)+"," + searchResult.getString(1), "UYARI", JOptionPane.WARNING_MESSAGE);
			rideInfo.add(searchResult.getString(2));
			rideInfo.add(searchResult.getString(3));
			rideInfo.add(searchResult.getString(4));
			rideInfo.add(searchResult.getString(5));
			rideInfo.add(searchResult.getString(7));
			rideInfo.add(searchResult.getString(8));
			rideInfo.add(searchResult.getString(9));
			return rideInfo;
		}
	}
	
	//IDsi uzerinden surucu ara
	public Driver findDriverbyID(int DriverID) throws SQLException {
		Driver foundDriver;
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM drivers WHERE driverid = '"+ DriverID +"'");
		if(!searchResult.next()) return null;
		else {
			foundDriver = new Driver(searchResult.getString(1),searchResult.getString(2),searchResult.getString(3),searchResult.getString(6),searchResult.getInt(4),searchResult.getInt(5));
			return foundDriver;
		}
	}
	
	//Surucu durum guncelle
	public void updateDriverStatus(int DriverID,String Status) throws SQLException {
		Statement st = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet searchResult = st.executeQuery("SELECT * FROM drivers WHERE driverid = '"+ DriverID +"'");
		if(!searchResult.next()) return;
		searchResult.updateString("status", Status);
		searchResult.updateRow();
	}
	
	//Surus sonrasi surucunun dk cinsinden deneyim suresini guncelle
	public void addDriverExp(int DriverID,int exp) throws SQLException {
		int curExp;
		Statement st = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet searchResult = st.executeQuery("SELECT * FROM drivers WHERE driverid = '"+ DriverID +"'");
		if(!searchResult.next()) return;
		curExp = searchResult.getInt(4);
		searchResult.updateInt("experience", curExp+exp);
		searchResult.updateRow();
	}
	
	//Surucuye musterinin verdigi puani ekle
	public void addDriverPts(int DriverID,int points) throws SQLException {
		int curPts;
		Statement st = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet searchResult = st.executeQuery("SELECT * FROM drivers WHERE driverid = '"+ DriverID +"'");
		if(!searchResult.next()) return;
		curPts = searchResult.getInt(5);
		searchResult.updateInt("points", curPts+points);
		searchResult.updateRow();
	}
	
	//Sürücü sil
	public String deleteDriver(int driverID) throws SQLException {
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM drivers WHERE driverid = '" + driverID + "'");
			if (!searchResult.next()) {
				return "Bu ID'ye ait sürücü bulunamadı!";
			}
			if(searchResult.getString(6).compareTo("active") == 0) {
				st.executeUpdate("DELETE FROM drivers WHERE driverid = '" + driverID + "'");
				return "Sürücü Silindi!";
			}
			else {
				return "Silmek istediğiniz sürücü şu anda başka bir müşteriye hizmet vermekte.\nSilmek için öncelikle hizmetin sonlanmasını bekleyiniz.";
			}
	}
	
	//Kullanici sil
	public String deleteUser(String userName) throws SQLException {
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM accounts WHERE accountid = '" + userName + "'");
		if (!searchResult.next()) {
			return "Bu kullanıcı adına sahip kullanıcı bulunamadı!";
		}
		st.executeUpdate("DELETE FROM accounts WHERE accountid = '" +userName + "'");
		return "Kullanıcı başarıyla silindi.";
	}
	
	//Silinen kullanicinin mevcut suruslerini iptal et
	public void cancelAllRides(String usrName) throws HeadlessException, SQLException {
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM rides WHERE accountid = '"+ usrName +"'");
		if(!searchResult.next()) {
			JOptionPane.showMessageDialog(null, "Sürüş Bulunamadı", "UYARI", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(searchResult.getString(6).compareTo("waiting") == 0) {
			//JOptionPane.showMessageDialog(null, "Sürüş henüz devam etmekte veya tamamlanmış.\nİptal için sürüşün henüz başlamamış olması gerekmektedir.", "UYARI", JOptionPane.WARNING_MESSAGE);			
			 updateDriverStatus(Integer.parseInt(searchResult.getString(2)),"active");
			 searchResult.updateString("status", "canceled");	
			 searchResult.updateRow();
			return;
		}
		while(searchResult.next()) {
			if(searchResult.getString(6).compareTo("waiting") == 0) {
				//JOptionPane.showMessageDialog(null, "Sürüş henüz devam etmekte veya tamamlanmış.\nİptal için sürüşün henüz başlamamış olması gerekmektedir.", "UYARI", JOptionPane.WARNING_MESSAGE);			
				 updateDriverStatus(Integer.parseInt(searchResult.getString(2)),"active");
				 searchResult.updateString("status", "canceled");	
				 searchResult.updateRow();
				return;
			}
		}
	}
	
	//Surucu Detaylari
	public ArrayList<String> detailDriver (String driverID) throws
	SQLException {
	Statement st = this.connection.createStatement();
	ResultSet searchResult = st.executeQuery("SELECT * FROM drivers WHERE driverid = '" + driverID + "'");
	if (!searchResult.next()) {
		return null;
	}
	ArrayList<String> DriverDetails = new ArrayList<String>();
	DriverDetails.add(searchResult.getString(1));
	DriverDetails.add(searchResult.getString(2));
	DriverDetails.add(searchResult.getString(3));
	DriverDetails.add(searchResult.getString(4));
	DriverDetails.add(searchResult.getString(5));
	return DriverDetails;
	}
	
	
	//Kullanici detaylari
	public ArrayList<String> detailUser (String userName) throws SQLException {
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM accounts WHERE accountid = '" + userName + "'");
		if (!searchResult.next()) {
			return null;
		}
		
		ArrayList<String> userDetails = new ArrayList<String>();
		userDetails.add(searchResult.getString(1));
		userDetails.add(searchResult.getString(2));
		userDetails.add(searchResult.getString(3));
		userDetails.add(searchResult.getString(4));
		userDetails.add(searchResult.getString(5));
		userDetails.add(searchResult.getString(6));
		return userDetails;
	}
	
	//Ekstra islemler
	public String getUserName(String userName) throws SQLException {
		String foundUsrName;
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM accounts WHERE accountid = '" + userName + "'");
		if (!searchResult.next()) {
			return null;
		}
		foundUsrName = searchResult.getString(1);
		return foundUsrName;
	}
	
	//Musteri almaya uygun bir driver bul
	public Driver findActiveDriver() throws SQLException{
		Statement st = this.connection.createStatement();
		ResultSet searchResult = st.executeQuery("SELECT * FROM drivers WHERE status = 'active'");
		if (!searchResult.next()) {
			return null;
		}
		Driver drFound = new Driver(searchResult.getString(1), searchResult.getString(2),
		searchResult.getString(3), searchResult.getString(6),Integer.parseInt(searchResult.getString(4)),
		Integer.parseInt(searchResult.getString(5)));
		return drFound;
	}
	
	//Guncel RideID tespiti
	public int detRideID() throws SQLException{
		int count = 0;
		Statement st = this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ResultSet searchResult = st.executeQuery("SELECT * FROM rides");
		searchResult.last();
		return(searchResult.getRow()+1);
	}
}