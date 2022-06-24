package program;

//Kullanici hesabi sinifi
public class Account {
	private String userName;
	private String userPassword;
	private String firstName;
	private String lastName;
	private String eMail;
	
	public Account(String userName, String userPassword, String firstName, String lastName, String eMail) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String geteMail() {
		return eMail;
	}
}