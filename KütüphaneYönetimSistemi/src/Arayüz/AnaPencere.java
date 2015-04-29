package Arayüz;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.Component;

import javax.swing.Box;

import description.ControlGui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnaPencere {

	private JFrame frame;
	private JTextField userNameTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaPencere window = new AnaPencere();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnaPencere() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 606, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel HeaderLabel = new JLabel("");
		HeaderLabel.addMouseListener(new MouseAdapter() {
			
		});
		Image img= new ImageIcon(this.getClass().getResource("/Header.jpg")).getImage();
		HeaderLabel.setIcon(new ImageIcon(img));
		HeaderLabel.setBounds(0, 0, 590, 68);
		frame.getContentPane().add(HeaderLabel);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(422, 93, 168, 203);
		frame.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel lblyeGirii = new JLabel("\u00DCye Giri\u015Fi:");
		lblyeGirii.setBounds(10, 11, 58, 14);
		loginPanel.add(lblyeGirii);
		
		JLabel lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		lblKullancAd.setBounds(10, 36, 66, 14);
		loginPanel.add(lblKullancAd);
		
		userNameTextField = new JTextField();
		userNameTextField.setBounds(10, 61, 107, 20);
		loginPanel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setBounds(10, 95, 46, 14);
		loginPanel.add(lblifre);
		
		JButton enterButton = new JButton("Giri\u015F");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ControlGui control = new ControlGui();
				
				long l = Long.parseLong(userNameTextField.getText()); 
				System.out.println("Parsed id: "+l);
				boolean c = control.MemberLoginControl(l, passwordField.getText());
				
				if(c==true){
				frame.setVisible(false);
				User kul = new User(l);
				kul.setVisible(true);}
				
			}
		});
		enterButton.setBounds(81, 178, 77, 14);
		loginPanel.add(enterButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 120, 107, 21);
		loginPanel.add(passwordField);
		
		JLabel adminLabel = new JLabel("Admin Giri\u015Fi");
		adminLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		adminLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				AdminLogin kulgir = new AdminLogin();
				kulgir.setVisible(true);
			}
		});
		adminLabel.setBounds(518, 68, 72, 14);
		frame.getContentPane().add(adminLabel);
		
		
	}
}
