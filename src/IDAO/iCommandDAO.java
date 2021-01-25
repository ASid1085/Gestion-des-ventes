package IDAO;

import java.sql.SQLException;
import java.util.Vector;
import Entites.Command;

public interface iCommandDAO {
	
	public void addCommand(Command cde) throws SQLException;
	
	public Command findCommandById(int id) throws SQLException;
	
	public Vector<String> afficherCommandVector(int id) throws SQLException;

}
