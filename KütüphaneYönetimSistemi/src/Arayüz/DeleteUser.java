package Arayüz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class DeleteUser extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser frame = new DeleteUser();
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
	public DeleteUser() {
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
		
		JLabel lblSileceinizyeninTc = new JLabel("Silece\u011Finiz \u00FCyenin TC Kimlik Numaras\u0131n\u0131 giriniz:");
		lblSileceinizyeninTc.setBounds(23, 116, 245, 14);
		contentPane.add(lblSileceinizyeninTc);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(23, 178, 215, 16);
		contentPane.add(textArea);
		
		JButton btnSil = new JButton("Bul");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSil.setBounds(131, 275, 89, 23);
		contentPane.add(btnSil);
		
		JLabel lblAd = new JLabel("Ad\u0131:");
		lblAd.setBounds(321, 116, 46, 14);
		contentPane.add(lblAd);
		
		JLabel lblSoyad = new JLabel("Soyad\u0131:");
		lblSoyad.setBounds(321, 178, 46, 14);
		contentPane.add(lblSoyad);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(414, 116, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(414, 178, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnSil_1 = new JButton("Sil");
		btnSil_1.setBounds(464, 275, 89, 23);
		contentPane.add(btnSil_1);
	}
}
