package program;

//Surucu sinifi
public class Driver {
	private String driverID;
	private String name;
	private String surname;
	private String status;
	private int exp;
	private int points;
	
	public Driver(String driverID, String name, String surname, String status, int exp , int points) {
		this.driverID = driverID;
		this.name = name;
		this.surname = surname;
		this.status = status;
		this.exp = exp;
		this.points = points;
	}
	
	public String getDriverID() {
		return driverID;
	}
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
}
