package Arayüz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import description.ControlGui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTextField;
	private JPasswordField passwordTextField;
	public static int id;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				
				
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
			}
		});
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel HeaderLabel = new JLabel("New label");
		Image img= new ImageIcon(this.getClass().getResource("/Header.jpg")).getImage();
		HeaderLabel.setIcon(new ImageIcon(img));
		HeaderLabel.setBounds(0, 0, 590, 42);
		contentPane.add(HeaderLabel);
		
		JLabel userNameLabel = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		userNameLabel.setBounds(399, 148, 106, 14);
		contentPane.add(userNameLabel);
		
		userNameTextField = new JTextField();
		userNameTextField.setBounds(399, 178, 106, 20);
		contentPane.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("\u015Eifre:");
		passwordLabel.setBounds(399, 225, 46, 14);
		contentPane.add(passwordLabel);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(399, 265, 106, 20);
		contentPane.add(passwordTextField);
		
		JButton loginButton = new JButton("Giri\u015F");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
        ControlGui control = new ControlGui();
				
				long l = Long.parseLong(userNameTextField.getText()); 
				System.out.println("Parsed id: "+l);
				boolean c = control.AdminLoginControl(l, passwordTextField.getText());
				
				if(c==true){
				setVisible(false);
				Admin admn = new Admin(l);
				admn.setVisible(true);}
				
				
				
			}
		});
		loginButton.setBounds(473, 337, 89, 23);
		contentPane.add(loginButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		Image img2= new ImageIcon(this.getClass().getResource("/Admin-icon.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img2));
		
		lblNewLabel.setBounds(43, 90, 239, 236);
		contentPane.add(lblNewLabel);
	}
}
