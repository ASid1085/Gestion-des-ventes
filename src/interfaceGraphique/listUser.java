package interfaceGraphique;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import ConnexionBDD.connexion;
import DAO.UserDAO;
import Entites.User;

import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class listUser extends JFrame {

	/*
	 * Variables de classe 
	 */
	private JPanel contentPane;
	private JButton ajouter;
	private JButton supprimer;
	private JButton modifier;
	private JTable table;
	private JButton deconnexion;
	
	
	private DefaultTableModel listDesUsers() {
		Vector nomColonne = new Vector();
		nomColonne.add( "n° id");
		nomColonne.add( "User Name");
		nomColonne.add( "Mot de passe");
		nomColonne.add( "Droit d'accès");
		
		UserDAO uDAO = new UserDAO();
		Vector vUser = null;
		try {
			vUser = uDAO.afficherUsersVector();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new DefaultTableModel( vUser, nomColonne);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listUser frame = new listUser();
					frame.setLocationRelativeTo( null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public listUser() {
		
		
		setTitle("Liste des utilisateurs");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground( new Color(248, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(19, 20, 512, 336);
		panel.setBackground( new Color(248, 248, 255));
		panel.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(147, 112, 219)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 500, 324);
		scrollPane.setFont( new Font("Cochin", Font.PLAIN, 16));
		scrollPane.setEnabled( true);
		panel.add(scrollPane);
		
		table = new JTable();
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView( table);
		table.setModel( listDesUsers());
		table.setFont(new Font("Cochin", Font.PLAIN, 15));
		table.setShowGrid( true);
		table.setShowHorizontalLines( true);
		table.setShowVerticalLines( true);
		table.getTableHeader().setBounds( 6, 6, 500, 324);
		table.getTableHeader().setVisible( true);
		
		
		
		ajouter = new JButton("Ajouter");
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajoutEtModifierUser au = new ajoutEtModifierUser( "");
				au.setVisible( true);
				au.setLocationRelativeTo( au.getParent());
				//setVisible( false);
				//dispose();
			}
		});
		ajouter.setFont(new Font("Cochin", Font.PLAIN, 18));
		ajouter.setBackground(new Color(248, 248, 255));
		ajouter.setBounds(19, 368, 115, 46);
		contentPane.add(ajouter);
		
		supprimer = new JButton("Supprimer");
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDAO us = new UserDAO();
				String nameSelect = (String) listDesUsers().getValueAt( table.getSelectedRow(), 1);
				try {
					us.supprimerUserByUserName( nameSelect, JOptionPane.showInputDialog( "Saisir le mdp pour confirmer la supression"));
					JOptionPane.showMessageDialog( null, "L'utilisateur a bien été supprimé !", "Confirmation de suppression", JOptionPane.INFORMATION_MESSAGE);
					table.setModel( listDesUsers());
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				//System.out.println( nameSelect);
			}
		});
		supprimer.setFont(new Font("Cochin", Font.PLAIN, 18));
		supprimer.setBounds(331, 368, 115, 46);
		contentPane.add(supprimer);
		
		modifier = new JButton("Modifier");
		modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nameSelect = "";
				try {
					nameSelect = (String) listDesUsers().getValueAt( table.getSelectedRow(), 1);
					//System.out.println( nameSelect);
					ajoutEtModifierUser au = new ajoutEtModifierUser( nameSelect);
					au.setLocationRelativeTo( au.getParent());
					au.setVisible( true);
					setVisible( false);
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog( null, "Merci de selectionné un utilisateur !", "Erreur de sélection", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		modifier.setFont(new Font("Cochin", Font.PLAIN, 18));
		modifier.setBounds(174, 368, 115, 46);
		contentPane.add(modifier);
		
		deconnexion = new JButton("");
		deconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connexion.closeInstance();
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		deconnexion.setIcon(new ImageIcon("/Users/a.sid/Drive/Sid/04 - Formation/AFPA - CDA/Eclipse/gestionVentev2/Icon/logout24px.png"));
		deconnexion.setBounds(478, 368, 53, 47);
		contentPane.add(deconnexion);
	}
}
