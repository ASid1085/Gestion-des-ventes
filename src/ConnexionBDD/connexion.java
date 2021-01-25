package ConnexionBDD;

import java.sql.*;

public class connexion {

	private String BDD = "gestionVte";
	private String url = "jdbc:mysql://localhost:3306/" + BDD;
	private String user = "root";
	private String mdp = "Sidonie1";
	static private Connection conn;
	
	//Constructeur de ma calsse
	private connexion() {
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    conn = DriverManager.getConnection(url, user, mdp);
		    System.out.println("Connecter");
		} catch (Exception e){
		    e.printStackTrace();
		    System.out.println("Erreur");
		    System.exit(0);
		}
	}
	
	public static Connection getInstance() {
		if (conn == null) {
			new connexion();
		}
		return conn;
	}
	
	public static Connection closeInstance() {
		try {
            conn.close();
            System.out.println( "Déconnection de la BDD");
        } catch (SQLException e) {
        	e.printStackTrace();
        	
        }
		return conn;
	}
	
	
}
