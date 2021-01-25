package DAO;

import java.sql.*;
import java.util.Vector;

import ConnexionBDD.connexion;
import Entites.Command;
import Entites.CommandLine;
import IDAO.iCommandeLineDAO;

public class CommandLineDAO implements iCommandeLineDAO {

	static Statement stmt;
	static PreparedStatement pstmt;
	static Connection myConnexion;
	
	@Override
	public void addCommandLine(CommandLine cdeLine, int idCde) throws SQLException {
		
		myConnexion = connexion.getInstance();
		
		String query = "insert into COMMANDLINE values ("
				+cdeLine.getIdCdeLine()+","
				+cdeLine.getIdProduct()+","
				+cdeLine.getQuantite()+","
				+idCde+");";		
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();		
		//connexion.closeInstance();
		
	}

	@Override
	public Vector<CommandLine> afficherCommandLinesVector() throws SQLException {
		Vector vCdeLine = new Vector<>();
		
		
		String query = "SELECT * FROM COMMANDLINE ORDER BY idCde";
		try {
			myConnexion = connexion.getInstance();
			Statement stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				Vector colonne = new Vector();
                colonne.add( new CommandLine(
                				rs.getInt( "idCdeLine"),
                				rs.getInt( "idProduct"),
                				rs.getInt( "quantite"),
                				rs.getInt( "idCde")));
                vCdeLine.add( colonne);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//connexion.getInstance();
		return vCdeLine;
	}

}
