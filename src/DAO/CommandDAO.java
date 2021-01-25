package DAO;

import java.sql.*;
import java.util.Vector;
import ConnexionBDD.connexion;
import Entites.Command;
import Entites.Product;
import IDAO.iCommandDAO;

public class CommandDAO implements iCommandDAO {
	
	static Statement stmt;
	static PreparedStatement pstmt;
	static Connection myConnexion;
	
	@Override
	public void addCommand(Command cde) throws SQLException {
		
		myConnexion = connexion.getInstance();
		
		String query = "insert into COMMAND values ('"+cde.getIdCde()+"', '"+cde.getTotalPrice()+"', '"+cde.getDateCde()+"');";
		
		pstmt = myConnexion.prepareStatement( query);
		pstmt.executeUpdate();
		
		pstmt.close();		
		//connexion.closeInstance();
	}

	@Override
	public Command findCommandById(int id) throws SQLException {
		Command cde = null;
		
		String query = "SELECT * FROM COMMAND WHERE idCde ="+id+";";
        try {
        	myConnexion = connexion.getInstance();
        	stmt = myConnexion.createStatement();
        	ResultSet rs = stmt.executeQuery( query);
        	while ( rs.next()) {
        		cde = new Command( rs.getInt( "idCde"), rs.getFloat( "totalPrice"), rs.getString( "dateCde"));
        	}
        	stmt.close();
        	pstmt.close();
    } catch (SQLException ex) {
        System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
    }
		return cde;
	}

	@Override
	public Vector<String> afficherCommandVector(int id) throws SQLException {
		Vector vCde = new Vector();
		
		String query =	"select "
				+ "pr.nameProduct as 'Produit', "
				+ "pr.price as 'Prix unitaire', "
				+ "cdeLi.quantite as 'Qté', "
				+ "(pr.price * cdeLi.quantite) as 'Prix total'"
			+ "from COMMANDLINE as cdeLi "
				+ "INNER JOIN COMMAND as cde ON cdeLi.idCde = cde.idCde "
				+ "INNER JOIN PRODUCT as pr ON cdeLi.idProduct = pr.idProduct "
			+ "and cdeLi.idCde = '"+ id +"';";
		
	        try {
	        	myConnexion = connexion.getInstance();
	        	PreparedStatement pstmt = myConnexion.prepareStatement( query);
				pstmt.execute();
				
	        	Statement stmt = myConnexion.createStatement();
	        	ResultSet rs = stmt.executeQuery( query);

	            while( rs.next()) {   
	                Vector colonne = new Vector();
	                colonne.add( rs.getString( "Produit"));
	                colonne.add( rs.getFloat( "Prix unitaire"));
	                colonne.add( rs.getInt( "Qté"));
	                colonne.add( rs.getFloat( "Prix total"));
	                
	                vCde.add( colonne);
	            }
	            rs.close();
	            stmt.close();
	            pstmt.close();
	        } catch (SQLException ex) {
	            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());
	            return vCde;
	        }
		return vCde;
	}
	
	public int lastNumCde() {
		int numCde = 0;
		
		String query = "select COUNT(*) from COMMAND;";
		try {
			myConnexion = connexion.getInstance();
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				numCde = rs.getInt( 1);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//connexion.closeInstance();
		return numCde;
	}

}
