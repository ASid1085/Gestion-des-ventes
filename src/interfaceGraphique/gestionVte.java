package interfaceGraphique;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.*;

import DAO.CommandDAO;
import DAO.CommandLineDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import Entites.Command;
import Entites.CommandLine;
import Entites.User;


public class gestionVte extends JFrame {

	/*
	 * Variables de classe 
	 */
	
	private JPanel contentPane;
	private JTextField totalTTCField;
	private JTextField totalQteField;
	private SpinnerNumberModel spinnerQt = new SpinnerNumberModel(0, 0, 5, 1);
	private DecimalFormat df = new DecimalFormat("##.00");
	private DateFormat dFormat = new SimpleDateFormat( "yyyy-MM-dd");
	private int iQte;
	private int totalQte;
	private float fMonTTC;
	private float totalTTC;
	private DefaultListModel<String> modelAchat = new DefaultListModel<String>();
	private DefaultListModel<String> modelMontant = new DefaultListModel<String>();
	private DefaultListModel<Integer> modelQte = new DefaultListModel<Integer>();

	
	public DefaultComboBoxModel<String> initModelProduit() {
		ProductDAO prDAO = new ProductDAO();
		return new DefaultComboBoxModel<>( prDAO.cbModelVector());
	}
	
	public static boolean isNumeric(String s) {
		try {
			float f = Float.parseFloat( s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestionVte frame = new gestionVte( "");
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
	public gestionVte( String dtAcces) {
		setTitle("Gestion des ventes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground( new Color(248, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Zone de gauche -- panLeft
		JPanel panLeft = new JPanel();
		panLeft.setBackground(new Color(248, 248, 255));
		panLeft.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(147, 112, 219)));
		panLeft.setBounds(6, 17, 226, 351);
		contentPane.add( panLeft);
		panLeft.setLayout(null);
		
		JLabel lblProduit = new JLabel("Choisir un produit");
		lblProduit.setFont(new Font("Cochin", Font.PLAIN, 14));
		lblProduit.setBounds(24, 36, 179, 16);
		panLeft.add( lblProduit);
		
		JLabel lblPrix = new JLabel("");
		lblPrix.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrix.setFont(new Font("Cochin", Font.PLAIN, 14));
		lblPrix.setBounds(34, 165, 155, 31);
		panLeft.add( lblPrix);
		
		JComboBox comboProduit = new JComboBox();
		comboProduit.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ProductDAO pDAO = new ProductDAO();
				Float prixUTTC = pDAO.getPriceByProduct( (String) comboProduit.getSelectedItem());
				lblPrix.setText( df.format( prixUTTC));
			}
		});
		comboProduit.setModel( initModelProduit());
		comboProduit.setToolTipText("");
		comboProduit.setFont(new Font("Cochin", Font.PLAIN, 14));
		comboProduit.setMaximumRowCount(10);
		comboProduit.setBounds(17, 47, 186, 52);
		panLeft.add( comboProduit);
		
		JLabel lblPrixUTTC = new JLabel(" Entrer le prix unitaire HT");
		lblPrixUTTC.setFont(new Font("Cochin", Font.PLAIN, 14));
		lblPrixUTTC.setToolTipText("");
		lblPrixUTTC.setBounds(24, 148, 179, 16);
		panLeft.add( lblPrixUTTC);
		
		JLabel lblQte = new JLabel(" Renseigner la quantité");
		lblQte.setFont(new Font("Cochin", Font.PLAIN, 14));
		lblQte.setBounds(24, 240, 179, 16);
		panLeft.add(lblQte);
		
		JSpinner spinnerQte = new JSpinner( spinnerQt);
		spinnerQte.setBounds(24, 258, 179, 39);
		panLeft.add(spinnerQte);
		
		//Zone de droite -- panRight
		JPanel panRight = new JPanel();
		panRight.setBackground(new Color(248, 248, 255));
		panRight.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(147, 112, 219)));
		panRight.setBounds(244, 17, 617, 351);
		contentPane.add( panRight);
		panRight.setLayout(null);
		
		JLabel lblListAchat = new JLabel("Liste d'achat");
		lblListAchat.setFont(new Font("Cochin", Font.PLAIN, 20));
		lblListAchat.setBounds(34, 17, 303, 29);
		panRight.add(lblListAchat);
		
		JList listAchat = new JList( modelAchat);
		listAchat.setFont(new Font("Cochin", Font.PLAIN, 14));
		listAchat.setBounds(24, 47, 313, 264);
		panRight.add(listAchat);
		
		JLabel lblListMontant = new JLabel("Montant TTC (€)");
		lblListMontant.setFont(new Font("Cochin", Font.PLAIN, 20));
		lblListMontant.setHorizontalAlignment(SwingConstants.CENTER);
		lblListMontant.setBounds(441, 17, 165, 29);
		panRight.add(lblListMontant);
		
		JList listMontant = new JList( modelMontant);
		listMontant.setFont(new Font("Cochin", Font.PLAIN, 14));
		listMontant.setBounds(441, 47, 159, 264);
		panRight.add( listMontant);
		
		JLabel lblNewLabel = new JLabel("Total € :");
		lblNewLabel.setFont(new Font("Cochin", Font.PLAIN, 16));
		lblNewLabel.setBounds(478, 314, 61, 32);
		panRight.add(lblNewLabel);
		
		totalTTCField = new JTextField();
		totalTTCField.setHorizontalAlignment(SwingConstants.RIGHT);
		totalTTCField.setFont(new Font("Cochin", Font.PLAIN, 16));
		totalTTCField.setBounds(536, 316, 70, 29);
		panRight.add(totalTTCField);
		totalTTCField.setColumns(10);
		
		JLabel lblTotalQte = new JLabel("Total Qté :");
		lblTotalQte.setFont(new Font("Cochin", Font.PLAIN, 16));
		lblTotalQte.setBounds(298, 310, 75, 40);
		panRight.add(lblTotalQte);
		
		totalQteField = new JTextField();
		totalQteField.setHorizontalAlignment(SwingConstants.RIGHT);
		totalQteField.setFont(new Font("Cochin", Font.PLAIN, 16));
		totalQteField.setColumns(10);
		totalQteField.setBounds(372, 316, 49, 29);
		panRight.add(totalQteField);
		
		JList listQte = new JList( modelQte);
		listQte.setFont(new Font("Cochin", Font.PLAIN, 14));
		listQte.setBounds(360, 46, 61, 265);
		panRight.add(listQte);
		
		JLabel lblListQte = new JLabel("Qté");
		lblListQte.setHorizontalAlignment(SwingConstants.CENTER);
		lblListQte.setFont(new Font("Cochin", Font.PLAIN, 20));
		lblListQte.setBounds(360, 17, 61, 29);
		panRight.add(lblListQte);
		
		//Zone du bas avec les buttons -- panBouton
		JPanel panBouton = new JPanel();
		panBouton.setBackground(new Color(248, 248, 255));
		panBouton.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(147, 112, 219)));
		panBouton.setBounds(7, 396, 854, 58);
		contentPane.add(panBouton);
		panBouton.setLayout(null);
		
		JButton ajouter = new JButton("Ajouter");
		if ( !dtAcces.equals("")) {
			ajouter.setEnabled( false);
		}
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sProduit = (String) comboProduit.getSelectedItem();
				int iQte = (int) spinnerQt.getValue();
				String textPrixTTC = lblPrix.getText().replace(",", ".");
				
				if ( !isNumeric( lblPrix.getText().replace(",", "."))) {
					JOptionPane.showMessageDialog(null, "Le prix ne peut pas contenir de lettres !", "Erreur de saisie", JOptionPane.WARNING_MESSAGE);
				}
				
				if ( !sProduit.equals( "") && (Integer) spinnerQt.getValue() > 0 && textPrixTTC != "") {
					
					boolean doublon = false;
					int indexDoub = 0;
					if (modelAchat.size() >= 0) {
						for (int i = 0; i < modelAchat.size(); i++ ) {
							String ajoutArt = (String) comboProduit.getSelectedItem();
							String art = modelAchat.getElementAt( i);
							if ( ajoutArt.equals( art)) {
								doublon = true;
								indexDoub = i;
								System.out.println( "Doublon détécté");
							} 
						}
					}
					
					float fPrixTTC = Float.parseFloat( textPrixTTC);
					if ( !doublon) {
						modelAchat.addElement( sProduit);
						modelQte.addElement( iQte);
						fMonTTC = iQte * fPrixTTC;
						String sMonTTC = df.format( fMonTTC);
						modelMontant.addElement( sMonTTC);
						totalQte += iQte;
						totalQteField.setText( String.valueOf( totalQte));
						totalTTC += fMonTTC;
						totalTTCField.setText( df.format( totalTTC) + " €");
					}
					
					if ( doublon) {
						int rQte = modelQte.getElementAt( indexDoub);
						int aQte = (int) spinnerQt.getValue();
						int nQte = rQte + aQte;
						modelQte.setElementAt( nQte, indexDoub);
						totalQte += aQte;
						totalQteField.setText( String.valueOf( totalQte));
						String rMonTTC = modelMontant.getElementAt( indexDoub).replace(",", ".");
						float rfMonTTC = Float.parseFloat( rMonTTC);
						float afMonTTC = aQte * fPrixTTC;
						float nfMonTTC = rfMonTTC + afMonTTC;
						modelMontant.setElementAt( df.format( nfMonTTC), indexDoub);
						totalTTC += afMonTTC;
						totalTTCField.setText( df.format( totalTTC) + " €");
					}
					lblPrix.setText( "");
					spinnerQte.setValue( 0);
				} else {
					JOptionPane.showMessageDialog(null, "L'un ou plusieurs des champs n'est pas renseigné !", "Informations manquantes", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		ajouter.setFont(new Font("Cochin", Font.PLAIN, 18));
		ajouter.setBackground(new Color(248, 248, 255));
		ajouter.setBounds(52, 6, 115, 46);
		panBouton.add(ajouter);
		
		JButton supprimer = new JButton("Supprimer");
		if ( !dtAcces.equals("")) {
			supprimer.setEnabled( false);
		}
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String sSuppAchat = (String) listAchat.getSelectedValue();
					int iIndAchat = listAchat.getSelectedIndex();
					iQte = modelQte.getElementAt( iIndAchat);
					totalQte -= iQte;
					totalQteField.setText( String.valueOf( totalQte));			
					String sSuppMontant = modelMontant.getElementAt( iIndAchat).replace(",", ".");
					float fSuppMontant = Float.parseFloat( sSuppMontant);
					totalTTC -= fSuppMontant;
					totalTTCField.setText( df.format( totalTTC) + " €");
					modelAchat.removeElementAt( iIndAchat);
					modelQte.removeElementAt( iIndAchat);
					modelMontant.removeElementAt( iIndAchat);
				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(null, "Aucune ligne n'est séléctionnée !", "Erreur de sélection", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		supprimer.setFont(new Font("Cochin", Font.PLAIN, 18));
		supprimer.setBounds(201, 6, 115, 46);
		panBouton.add(supprimer);
		
		JButton effacer = new JButton("Effacer");
		if ( !dtAcces.equals("")) {
			effacer.setEnabled( false);
		}
		effacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblPrix.setText( "");
				spinnerQte.setValue( 0);
				modelAchat.removeAllElements();
				modelMontant.removeAllElements();
				modelQte.removeAllElements();
				totalQteField.setText( "");
				totalTTCField.setText( "");
				totalQte = 0;
				totalTTC = 0;
			}
		});
		effacer.setFont(new Font("Cochin", Font.PLAIN, 18));
		effacer.setBounds(361, 6, 115, 46);
		panBouton.add(effacer);
		
		JButton rechercher = new JButton("Rechercher");
		rechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rechercherCde rc = new rechercherCde();
				rc.setVisible( true);
			}
		});
		rechercher.setFont(new Font("Cochin", Font.PLAIN, 18));
		rechercher.setBounds(523, 6, 115, 46);
		panBouton.add(rechercher);
		
		JButton valider = new JButton("Valider");
		if ( !dtAcces.equals("")) {
			valider.setEnabled( false);
		}
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!modelAchat.isEmpty()) {
					Date d = new Date();
					CommandDAO cdeDAO = new CommandDAO();
					//System.out.println( cdeDAO.lastNumCde());
					int numCde = cdeDAO.lastNumCde() + 1;
					Command cde = new Command( numCde, totalTTC, dFormat.format(d));
					try {
						cdeDAO.addCommand( cde);
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					
					ProductDAO pDAO = new ProductDAO();
					CommandLineDAO cdeLiDAO = new CommandLineDAO();
					CommandLine CdeLi = null;
					for (int i = 0; i < modelAchat.size(); i++) {
						int idP = pDAO.getIdByName( modelAchat.getElementAt( i));
						int qte = modelQte.getElementAt( i);
						//System.out.println( idP + "   " + qte);
						CdeLi = new CommandLine( idP, qte);
						try {
							cdeLiDAO.addCommandLine(CdeLi, numCde);
						} catch (SQLException ex) {
							ex.printStackTrace();
						}
					}
					JOptionPane.showMessageDialog(null, "La commande à bien été validée !", "Commande information", JOptionPane.INFORMATION_MESSAGE);
					spinnerQte.setValue( 0);
					modelAchat.removeAllElements();
					modelMontant.removeAllElements();
					modelQte.removeAllElements();
					totalQteField.setText( "");
					totalTTCField.setText( "");
					totalQte = 0;
					totalTTC = 0;
				} else {
					JOptionPane.showMessageDialog(null, "Votre commande est vide ! \nVeuillez ajouter au moins un article.", "Commande information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		valider.setFont(new Font("Cochin", Font.PLAIN, 18));
		valider.setBounds(685, 6, 115, 46);
		panBouton.add(valider);
		
		
	}
}
