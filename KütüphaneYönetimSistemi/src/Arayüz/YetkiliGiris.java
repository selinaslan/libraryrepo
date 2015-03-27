package Arayüz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import description.ControlGui;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class YetkiliGiris extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	public static int id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YetkiliGiris frame = new YetkiliGiris(id);
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
	public YetkiliGiris(int id2) {
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
		
		JLabel lblKullancAd = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		lblKullancAd.setBounds(399, 148, 106, 14);
		contentPane.add(lblKullancAd);
		
		textField = new JTextField();
		textField.setBounds(399, 178, 106, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setBounds(399, 225, 46, 14);
		contentPane.add(lblifre);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(399, 265, 106, 20);
		contentPane.add(passwordField);
		
		JButton btnGiri = new JButton("Giri\u015F");
		btnGiri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
ControlGui control = new ControlGui();
				
				long l = Long.parseLong(textField.getText()); 
				System.out.println("Parsed id: "+l);
				boolean c = control.MemberLoginControl(l, passwordField.getText());
				
				if(c==true){
				setVisible(false);
				Kullanýcý kul = new Kullanýcý(l);
				kul.setVisible(true);}
				
				
				
			}
		});
		btnGiri.setBounds(473, 337, 89, 23);
		contentPane.add(btnGiri);
		
		JLabel lblNewLabel = new JLabel("New label");
		Image img2= new ImageIcon(this.getClass().getResource("/Admin-icon.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img2));
		
		lblNewLabel.setBounds(43, 90, 239, 236);
		contentPane.add(lblNewLabel);
	}
}
