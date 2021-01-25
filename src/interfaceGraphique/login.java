package interfaceGraphique;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import IDAO.*;
import DAO.UserDAO;
import Entites.User;

public class login extends JDialog {
	
	/**
	 * constructor du dialog.
	 */
	public login(Frame parent, boolean modal) {
		super(parent, modal);
		loginUser();
	}
	
	private final JPanel contentPanel = new JPanel();
	private JTextField UserNameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			login dialog = new login(new JFrame(), true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo( null);
			dialog.setVisible( true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private void loginUser() {
		setBounds(100, 100, 433, 239);
		getContentPane().setLayout(new BorderLayout());
		//contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground( new Color(248, 248, 255));
		
		JLabel lblId = new JLabel("User name :");
		lblId.setFont(new Font("Cochin", Font.PLAIN, 16));
		lblId.setBounds(68, 45, 90, 16);
		contentPanel.add(lblId);
		
		
		JLabel lblMdp = new JLabel("Password :");
		lblMdp.setFont(new Font("Cochin", Font.PLAIN, 16));
		lblMdp.setBounds(68, 91, 90, 16);
		contentPanel.add(lblMdp);
		
		UserNameField = new JTextField();
		UserNameField.setFont(new Font("Cochin", Font.PLAIN, 16));
		UserNameField.setBounds(170, 36, 236, 34);
		contentPanel.add(UserNameField);
		UserNameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Cochin", Font.PLAIN, 16));
		passwordField.setBounds(170, 82, 236, 34);
		contentPanel.add(passwordField);
		
			JButton okButton = new JButton("Valider");
			okButton.setFont(new Font("Cochin", Font.PLAIN, 18));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {			
					UserDAO us = new UserDAO();
					us.connectionCompte( UserNameField.getText(), passwordField.getText());
					setVisible( false);
					dispose();
				}
			});
			okButton.setBounds(83, 152, 100, 29);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		
		
			JButton cancelButton = new JButton("Annuler");
			cancelButton.setFont(new Font("Cochin", Font.PLAIN, 18));
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					UserNameField.setText( "");
					passwordField.setText( "");
				}
			});
			cancelButton.setBounds(256, 152, 100, 29);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		
	}
}
