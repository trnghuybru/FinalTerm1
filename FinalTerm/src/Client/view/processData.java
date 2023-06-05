package Client.view;

public class processData {
	public String processRegister(String username, String pass, String phone, String email) {
		return "{\"method\": \"DAOsRegister\","
				+ "\"data\": {\"username\": \""+username+"\" , \"password\": \""+pass+"\", \"phone\": \""+phone+"\", \"email\": \""+email+"\" }}";
	}
}
