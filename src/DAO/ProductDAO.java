package DAO;

import java.sql.*;
import java.util.*;

import ConnexionBDD.connexion;
import Entites.Product;
import Entites.User;

public class ProductDAO {

	static Statement stmt;
	static PreparedStatement pstmt;
	static Connection myConnexion;

	public Vector<String> cbModelVector() {
		Vector vPr = new Vector<>();
		
		String query = "SELECT * FROM PRODUCT ORDER BY nameProduct";
		try {
			myConnexion = connexion.getInstance();
			stmt = myConnexion.createStatement();
			ResultSet rs = stmt.executeQuery( query);
			while ( rs.next()) {
				vPr.add( rs.getString( "nameProduct"));
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//connexion.getInstance();
		return vPr;
	}
	
	public Float getPriceByProduct(String nameProduct) {
		Float f = null;
		
		String query = "SELECT * FROM PRODUCT WHERE nameProduct ='"+nameProduct.replace( "'", "''")+"';";
        try {
        	myConnexion = connexion.getInstance();
        	stmt = myConnexion.createStatement();
        	ResultSet rs = stmt.executeQuery( query);
        	while ( rs.next()) {
        		f = rs.getFloat( "price");
        	}
        	rs.close();
        	stmt.close();
    } catch (SQLException ex) {
        System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
    }
		return f;
	}
	
	public int getIdByName(String nameProduct) {
		int id = 0;
		
		String query = "SELECT idProduct FROM PRODUCT WHERE nameProduct ='"+nameProduct.replace( "'", "''")+"';";
        try {
        	myConnexion = connexion.getInstance();
        	stmt = myConnexion.createStatement();
        	ResultSet rs = stmt.executeQuery( query);
        	while ( rs.next()) {
        		id = rs.getInt( "idProduct");
        	}
        	rs.close();
        	stmt.close();
    } catch (SQLException ex) {
        System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
    }
       return id;
	}
	
}
