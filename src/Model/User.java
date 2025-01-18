package Model;

public class User 
{
	private int Id;
	String TC,Password,Name,Type;
	
	public User(int id, String tC, String password, String name, String type) {
		Id = id;
		TC = tC;
		Password = password;
		Name = name;
		Type = type;
	}
	
	public User()
	{
		
	}
	
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTC() {
		return TC;
	}
	public void setTC(String tC) {
		TC = tC;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
	
}
