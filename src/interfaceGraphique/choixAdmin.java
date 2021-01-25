package interfaceGraphique;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;

public class choixAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					choixAdmin frame = new choixAdmin();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JButton btnNewUser = new JButton("Gestion d'utilisateur");
		btnNewUser.setBounds(25, 156, 246, 47);
		panel.add(btnNewUser);
		btnNewUser.setFont(new Font("Cochin", Font.PLAIN, 18));
		
		JButton btnGestionVtes = new JButton("Gestion des ventes");
		btnGestionVtes.setBounds(25, 49, 246, 47);
		panel.add(btnGestionVtes);
		btnGestionVtes.setFont(new Font("Cochin", Font.PLAIN, 18));
		btnGestionVtes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionVte gv = new gestionVte( "");
				gv.setVisible( true);
				//setVisible( false);
				//dispose();
			}
		});
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listUser li = new listUser();
				li.setVisible( true);
				//setVisible( false);
				//dispose();
			}
		});
	}

}
