package interfaceGraphique;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import DAO.UserDAO;
import Entites.User;


public class ajoutEtModifierUser extends JFrame {
	
	/*
	 * Variables de classe 
	 */
	private JPanel contentPane;
	private JTextField textUserName;
	private JTextField textPassword;
	private JComboBox<String> comboBoxDtAcces;
	private String [] dtAcces = { "", "admin", "employé" , "stagiaire"};
	private DefaultComboBoxModel<String> modelAcces = new DefaultComboBoxModel<>( dtAcces);
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajoutEtModifierUser frame = new ajoutEtModifierUser( "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ajoutEtModifierUser(String nameUser) {
		if (!nameUser.equals( "")) { 
		
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					UserDAO us = new UserDAO();
					try {
						User  modifUser = us.findUserByUSerName( nameUser);
						System.out.println( modifUser.getUserName());
						textUserName.setText( modifUser.getUserName());
						textPassword.setText( modifUser.getPassword());
						comboBoxDtAcces.setSelectedItem( modifUser.getAcces());
					} catch (SQLException ex) {
						ex.printStackTrace();
					}			
				}
			});
		}
		setTitle("Ajouter/ Modifier un utilisateur");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 469, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground( new Color(248, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(27, 60, 417, 215);
		panel.setBackground( new Color(248, 248, 255));
		panel.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(147, 112, 219)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUserName = new JLabel("User Name :");
		lblUserName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUserName.setFont(new Font("Cochin", Font.PLAIN, 16));
		lblUserName.setBounds(39, 41, 97, 16);
		panel.add(lblUserName);
		
		textUserName = new JTextField();
		textUserName.setBounds(173, 35, 184, 29);
		panel.add(textUserName);
		textUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Cochin", Font.PLAIN, 16));
		lblPassword.setBounds(39, 98, 97, 16);
		panel.add(lblPassword);
		
		textPassword = new JTextField();
		textPassword.setBounds(173, 92, 184, 29);
		panel.add(textPassword);
		textPassword.setColumns(10);
		
		JLabel lblDroitsAcces = new JLabel("Droit d'accès :");
		lblDroitsAcces.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDroitsAcces.setFont(new Font("Cochin", Font.PLAIN, 16));
		lblDroitsAcces.setBounds(39, 157, 97, 16);
		panel.add(lblDroitsAcces);
		
		comboBoxDtAcces = new JComboBox();
		comboBoxDtAcces.setFont(new Font("Cochin", Font.PLAIN, 16));
		comboBoxDtAcces.setBounds(186, 146, 176, 41);
		panel.add(comboBoxDtAcces);
		comboBoxDtAcces.setModel( modelAcces);
		

		JButton valider = new JButton("Valider");
		valider.setFont(new Font("Cochin", Font.PLAIN, 18));
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (nameUser.equals( "")) {
					// Ajouter une nouvel utilisateur
					User nouvelUser = new User( 
										textUserName.getText(), 
										textPassword.getText(), 
										(String) comboBoxDtAcces.getSelectedItem());
					UserDAO us = new UserDAO();
					
					try {
						us.addUser( nouvelUser);
						JOptionPane.showMessageDialog( null, "Le nouvel utilisateur a bien été crée !", "Confirmation d'ajout", JOptionPane.INFORMATION_MESSAGE);
						setVisible( false);
						dispose();
					} catch (SQLIntegrityConstraintViolationException icve) {
						//icve.printStackTrace();
						JOptionPane.showMessageDialog( null, "Le user name existe déjà !", "Erreur d'ajout", JOptionPane.WARNING_MESSAGE);
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				
				if (!nameUser.equals( "")) {
					// Modifier un utilisateur existant
					UserDAO us = new UserDAO();
					try {
						us.modifierUser( nameUser, textUserName.getText(), textPassword.getText(), (String) comboBoxDtAcces.getSelectedItem());
						JOptionPane.showMessageDialog( null, "L'utilisateur a bien été modifié !", "Confirmation de modification", JOptionPane.INFORMATION_MESSAGE);
						setVisible( false);
						dispose();
						listUser lu = new listUser();
						lu.setVisible( true);
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		valider.setBounds(57, 305, 152, 38);
		contentPane.add(valider);
		
		JButton btnSupprimer = new JButton("Annuler");
		btnSupprimer.setFont(new Font("Cochin", Font.PLAIN, 18));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUserName.setText( "");
				textPassword.setText( "");
			}
		});
		btnSupprimer.setBounds(261, 305, 152, 38);
		contentPane.add(btnSupprimer);
		
		JLabel lblNewLabel = new JLabel("Saisir les 3 champs ci-dessous");
		lblNewLabel.setFont(new Font("Cochin", Font.PLAIN, 15));
		lblNewLabel.setBounds(27, 26, 407, 22);
		contentPane.add(lblNewLabel);
	}
}
