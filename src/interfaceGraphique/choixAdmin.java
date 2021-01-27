package interfaceGraphique;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import ConnexionBDD.connexion;

import java.awt.event.*;
import java.awt.*;

public class choixAdmin extends JFrame {

	private JPanel contentPane;
	private static listUser li;
	private static gestionVte gv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					choixAdmin frame = new choixAdmin();
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
	public choixAdmin() {
		setTitle("Console administrateur");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 344, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground( new Color(248, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 31, 306, 273);
		panel.setBackground( new Color(248, 248, 255));
		panel.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(147, 112, 219)));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnGestionUser = new JButton("Gestion d'utilisateur");
		btnGestionUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				li = new listUser();
				li.setLocationRelativeTo( li.getParent());
				li.setVisible( true);
				//setVisible( false);
				//dispose();
			}
		});
		btnGestionUser.setBounds(25, 125, 246, 47);
		panel.add(btnGestionUser);
		btnGestionUser.setFont(new Font("Cochin", Font.PLAIN, 18));
		
		JButton btnGestionVtes = new JButton("Gestion des ventes");
		btnGestionVtes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gv = new gestionVte( "");
				gv.setLocationRelativeTo( gv.getParent());
				gv.setVisible( true);
				//setVisible( false);
				//dispose();
			}
		});
		btnGestionVtes.setBounds(25, 49, 246, 47);
		panel.add(btnGestionVtes);
		btnGestionVtes.setFont(new Font("Cochin", Font.PLAIN, 18));
		
		JButton deconnexion = new JButton("");
		deconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connexion.closeInstance();
				System.exit(DISPOSE_ON_CLOSE);
			}
		});
		deconnexion.setIcon(new ImageIcon("/Users/a.sid/Drive/Sid/04 - Formation/AFPA - CDA/Eclipse/gestionVentev2/Icon/logout24px.png"));
		deconnexion.setBounds(128, 203, 53, 47);
		panel.add(deconnexion);
		
	}
}
