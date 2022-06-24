package program;
import java.util.ArrayList;
import java.util.List;

//Standard kullanici sinifi
public class User extends Account{
	public User(String userName, String userPassword, String firstName, String lastName, String eMail) {
		super(userName, userPassword, firstName, lastName, eMail);
	}
}
