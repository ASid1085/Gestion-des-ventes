package IDAO;

import java.sql.*;
import java.util.Vector;
import Entites.CommandLine;

public interface iCommandeLineDAO {

	public void addCommandLine(CommandLine cdeLine, int idCde) throws SQLException;
	
	public Vector<CommandLine> afficherCommandLinesVector() throws SQLException;
}
