package interfaceGraphique;

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.*;
import ConnexionBDD.*;
import DAO.CommandDAO;

import java.awt.event.*;

public class rechercherCde extends JFrame {

	/*
	 * Variables de classe 
	 */
	private JPanel contentPane;
	private JTable tableDetailCde;
	private JLabel lblRechercherCde;
	private JTextField textNumCde;
	private JButton btnRechercher;
	private int cdeRecherche;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rechercherCde frame = new rechercherCde();
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
	public rechercherCde() {
		setTitle("Recherche de commande");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 513, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground( new Color(248, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 499, 382);
		panel.setBackground( new Color(248, 248, 255));
		panel.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(147, 112, 219)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 67, 487, 302);
		panel.add(scrollPane);
		
		tableDetailCde = new JTable();
		tableDetailCde.setBounds(0, 0, 487, 304);
		tableDetailCde.setFont(new Font("Cochin", Font.PLAIN, 15));
		scrollPane.setViewportView( tableDetailCde);
		tableDetailCde.setEnabled( false);
		tableDetailCde.setShowGrid( true);
		tableDetailCde.setColumnSelectionAllowed( true);
		tableDetailCde.getTableHeader().setBounds( 6, 49, 487, 25);
		tableDetailCde.getTableHeader().setVisible( true);
		
		lblRechercherCde = new JLabel("N° de commande rechercher");
		lblRechercherCde.setBounds(6, 22, 196, 31);
		lblRechercherCde.setFont(new Font("Cochin", Font.PLAIN, 16));
		panel.add(lblRechercherCde);
		
		textNumCde = new JTextField();
		textNumCde.setBounds(214, 21, 89, 34);
		textNumCde.setFont(new Font("Cochin", Font.PLAIN, 16));
		panel.add(textNumCde);
		textNumCde.setColumns(10);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(354, 17, 117, 42);
		btnRechercher.setFont(new Font("Cochin", Font.PLAIN, 16));
		btnRechercher.addActionListener(new ActionListener() {
			DefaultTableModel findCdeModel() {
				CommandDAO cdeDAO = new CommandDAO();
				
				Vector nomColonne = new Vector();
				nomColonne.add( "Produit");
				nomColonne.add( "Prix unitaire");
				nomColonne.add( "Qté");
				nomColonne.add( "Prix total");
				
				Vector vCde = null;
				cdeRecherche = Integer.parseInt( textNumCde.getText());
				try {
					vCde = cdeDAO.afficherCommandVector( cdeRecherche);
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog( null, "Le numéro de commande saisie est erroné !", "Erreur de saisie", JOptionPane.WARNING_MESSAGE);
					nfe.printStackTrace();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				return new DefaultTableModel( vCde, nomColonne);
			}
			
			public void actionPerformed(ActionEvent e) {
				tableDetailCde.setModel( findCdeModel());
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
				tableDetailCde.getColumnModel().getColumn( 0).setMinWidth( 270);
				tableDetailCde.getColumnModel().getColumn( 1).setCellRenderer(rightRenderer);
				tableDetailCde.getColumnModel().getColumn( 2).setCellRenderer(rightRenderer);
				tableDetailCde.getColumnModel().getColumn( 2).setMinWidth( 10);
				tableDetailCde.getColumnModel().getColumn( 3).setCellRenderer(rightRenderer);
			}
		});
		panel.add(btnRechercher);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible( false);
				dispose();
			}
		});
		btnQuitter.setFont(new Font("Cochin", Font.PLAIN, 16));
		btnQuitter.setBounds(388, 400, 117, 32);
		contentPane.add(btnQuitter);
	}
}
