package test3;

public class CustomUser {
	private String first_name;
	private String last_name;
	private String email_id;
	private String mobile_number;
	
	public CustomUser(String first_name, String last_name, String email_id, String mobile_number) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email_id = email_id;
		this.mobile_number = mobile_number;		
	}
	
	public String getFirstName() {
		return first_name;
	}
	
	public String getLastName() {
		return last_name;
	}
	
	public String getEmail() {
		return email_id;
	}
	
	public String getMobileNumber() {
		return mobile_number;
	}
	
	@Override
	public String toString() {
		return "FirstName: " + first_name + " LastName:" + last_name + " Email: " + email_id + " Phone: " + mobile_number;
	}

}
