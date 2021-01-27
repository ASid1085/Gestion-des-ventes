package interfaceGraphique;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class identification {

	private JFrame identification;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					identification window = new identification();
					window.identification.setLocationRelativeTo( null);
					window.identification.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public identification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		identification = new JFrame();
		identification.setBounds(100, 100, 498, 338);
		identification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		identification.getContentPane().setBackground( new Color(248, 248, 255));
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setToolTipText("src image : <div>Icons made by <a href=\"https://www.flaticon.com/authors/inipagistudio\" title=\"inipagistudio\">inipagistudio</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\">www.flaticon.com</a></div>");
		btnConnexion.setIcon(new ImageIcon("/Users/a.sid/Drive/Sid/04 - Formation/AFPA - CDA/Eclipse/gestionVentev2/Icon/login24px.png"));
		btnConnexion.setFont(new Font("Cochin", Font.PLAIN, 16));
		btnConnexion.setBounds(179, 225, 134, 43);
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login log = new login(identification, false);
				log.setLocationRelativeTo( log.getParent());
				log.setVisible( true);
			}
		});
		identification.getContentPane().setLayout(null);
		identification.getContentPane().add(btnConnexion);
		
		JPanel panel = new JPanel();
		panel.setBounds(54, 18, 385, 166);
		panel.setBackground( new Color(248, 248, 255));
		panel.setBorder( BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(147, 112, 219)));
		identification.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bonjour et bienvenue");
		lblNewLabel.setBounds(6, 23, 373, 43);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Cochin", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("dans votre logiciel de gestion");
		lblNewLabel_1.setBounds(6, 88, 373, 39);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Cochin", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		identification.setTitle( "Acceuil");
		identification.setVisible( false);
		
		
		
	}
}
