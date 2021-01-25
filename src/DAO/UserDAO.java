package DAO;


import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

import ConnexionBDD.connexion;
import Entites.*;
import IDAO.*;
import interfaceGraphique.choixAdmin;
import interfaceGraphique.gestionVte;

	public class UserDAO implements iUserDAO {
		
		static Statement stmt;
		static PreparedStatement pstmt;
		static Connection myConnexion;

		@Override
		public String validationUserMdp(String userName) throws SQLException {
			String passw = "";
			
			String query = "SELECT mdp FROM USER WHERE userName ='"+userName+"';";
			try {
				myConnexion = connexion.getInstance();
				stmt = myConnexion.createStatement();
				ResultSet rs = stmt.executeQuery( query);

				while (rs.next()) {
					passw = rs.getString( "mdp");
				}
				
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//myConnexion = connexion.closeInstance();
			return passw;
		}
		
		@Override
		public String validationUserAcces(String userName, String mdp) throws SQLException {
			
			String dtAcces = "";
			
			String query = "SELECT acces FROM USER WHERE userName ='"+userName+"' AND mdp ='"+mdp+"';";
			try {
				myConnexion = connexion.getInstance();
				stmt = myConnexion.createStatement();
				ResultSet rs = stmt.executeQuery( query);
				
				while (rs.next()) {
					dtAcces = rs.getString( "acces");
				}	
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//myConnexion = connexion.closeInstance();
			return dtAcces;
		}
		
		@Override
		public void addUser(User u) throws SQLException {
			
			myConnexion = connexion.getInstance();
			
			String query = "insert into USER(userName, mdp, acces) values ('"
					+u.getUserName()+"','"
					+u.getPassword()+"','"
					+u.getAcces()+"');";
			
			pstmt = myConnexion.prepareStatement( query);
			pstmt.executeUpdate();
			
			pstmt.close();		
			//connexion.closeInstance();
		}

		@Override
		public void modifierUser(String userName, String newName, String mdp, String acces) throws SQLException {
			
			myConnexion = connexion.getInstance();
			String query = "update USER set userName ='"+ newName +"', mdp ='"+ mdp +"', acces ='"+ acces +"' where userName = '"+userName+"';"; 
			
			pstmt = myConnexion.prepareStatement( query);
			pstmt.executeUpdate();
			//connexion.closeInstance();	
		}
		
		@Override
		public User findUserByUSerName(String userName) throws SQLException {
			User us = null;
			
			String query = "SELECT * FROM USER WHERE userName ='"+userName+"';";
	        try {
	        	myConnexion = connexion.getInstance();
	        	stmt = myConnexion.createStatement();
	        	ResultSet rs = stmt.executeQuery( query);
	        	while ( rs.next()) {
	        		us = new User( 	rs.getString( "userName"),
            						rs.getString( "mdp"),
            						rs.getString( "acces"));
	        	}
	        	rs.close();
	        	stmt.close();
        } catch (SQLException ex) {
            System.err.println("Oops:SQL:" + ex.getErrorCode() + ":" + ex.getMessage());    
        }
			return us;
		}
		
		@Override
		public Vector<User> afficherUsersVector() throws SQLException {
			Vector vUs = new Vector<>();
			
			String query = "SELECT * FROM USER ORDER BY userName";
			try {
				myConnexion = connexion.getInstance();
				stmt = myConnexion.createStatement();
				ResultSet rs = stmt.executeQuery( query);
				while ( rs.next()) {
					Vector colonne = new Vector();
	                colonne.add( rs.getInt( "idUser"));
	                colonne.add( rs.getString( "userName"));
	                colonne.add( rs.getString( "mdp"));
	                colonne.add(rs.getString( "acces"));
	                
	                vUs.add( colonne);
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//myConnexion = connexion.getInstance();
			return vUs;
		}

		@Override
		public void supprimerUserByUserName(String userName, String password) throws SQLException {
			
			myConnexion = connexion.getInstance();
			
			String query = "delete from USER where UserName = '"
							+userName+"' and mdp = '"
							+password+"';";
			
			pstmt = myConnexion.prepareStatement( query);
			pstmt.executeUpdate();
			
			pstmt.close();	
			//connexion.closeInstance();
		}

		public void connectionCompte(String userName, String mdp) {
			// Test que les 2 champs soient saisies.
			if ( userName.equals( "") || mdp.equals( "")) {
				JOptionPane.showMessageDialog(null, "Merci de saisir les deux champs !", "Erreur de saisie", JOptionPane.WARNING_MESSAGE);
			}
			
			// Test que le mot de passe correspond au user saisi.
			UserDAO uDAO = new UserDAO();
			String passw = "";
			String dtAcces = "";
			String passSaisi = mdp;
			try {
				passw = uDAO.validationUserMdp(userName);
				dtAcces = uDAO.validationUserAcces( userName, passSaisi);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if ( !passw.equals( passSaisi)) {
				JOptionPane.showMessageDialog(null, "User name et/ou Password incorrect !", "Erreur de saisie", JOptionPane.WARNING_MESSAGE);
			}
			
			// Test le type d'accès pour donner accès au bonnes fenêtres
			if ( dtAcces.equals( "admin")) {
				choixAdmin ca = new choixAdmin();
				ca.setVisible( true);
			} 
			
			if ( dtAcces.equals( "employé")) {
				gestionVte gv = new gestionVte( dtAcces);
				gv.setVisible( true);
			}
		}
}
