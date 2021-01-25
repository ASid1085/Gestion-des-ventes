package IDAO;

import java.sql.SQLException;
import java.util.Vector;
import Entites.User;


public interface iUserDAO {

	public void addUser(User u) throws SQLException;
	
	public void modifierUser(String userName, String newName, String mdp, String acces) throws SQLException;
	
	public User findUserByUSerName(String userName) throws SQLException;
	
	public Vector<User> afficherUsersVector() throws SQLException;
	
	public void supprimerUserByUserName(String userName, String password) throws SQLException;
	
	public String validationUserMdp(String UserName) throws SQLException;
	
	public String validationUserAcces(String UserName, String mdp) throws SQLException;
	
}
