package Entites;

public class User {
	
	private int idUser;
	private String userName;
	private String password;
	private String acces;
	
	public User(int idUser, String userName, String password, String acces) {
		super();
		this.idUser = idUser;
		this.userName = userName;
		this.password = password;
		this.acces = acces;
	}

	public User(String userName, String password, String acces) {
		super();
		this.userName = userName;
		this.password = password;
		this.acces = acces;
	}
	
	public User() {
		super();
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAcces() {
		return acces;
	}

	public void setAcces(String acces) {
		this.acces = acces;
	}

	@Override
	public String toString() {
		return userName;
	}
	
	
	
}
