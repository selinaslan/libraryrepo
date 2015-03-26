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
import javax.swing.JButton;

public class EditUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditUser frame = new EditUser();
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
	public EditUser() {
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
		
		JPanel panel = new JPanel();
		panel.setBounds(34, 85, 515, 87);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDzenlemekIstediinizyenin = new JLabel("D\u00FCzenlemek  istedi\u011Finiz \u00FCyenin TC Kimlik Numaras\u0131n\u0131 giriniz:");
		lblDzenlemekIstediinizyenin.setBounds(10, 11, 443, 22);
		panel.add(lblDzenlemekIstediinizyenin);
		
		textField = new JTextField();
		textField.setBounds(10, 44, 208, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnBul = new JButton("Bul");
		btnBul.setBounds(337, 43, 89, 23);
		panel.add(btnBul);
		
		JLabel lblAd = new JLabel("Ad\u0131:");
		lblAd.setBounds(34, 220, 46, 14);
		contentPane.add(lblAd);
		
		JLabel lblSoyad = new JLabel("Soyad\u0131:");
		lblSoyad.setBounds(34, 261, 46, 14);
		contentPane.add(lblSoyad);
		
		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setBounds(34, 299, 46, 14);
		contentPane.add(lblifre);
		
		JLabel lblEposta = new JLabel("E-posta:");
		lblEposta.setBounds(34, 342, 46, 14);
		contentPane.add(lblEposta);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(158, 220, 139, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(158, 261, 112, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(158, 299, 139, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(158, 342, 112, 14);
		contentPane.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(294, 217, 139, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(294, 258, 139, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(294, 296, 139, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(293, 339, 140, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnDzenle = new JButton("D\u00FCzenle");
		btnDzenle.setBounds(479, 338, 89, 23);
		contentPane.add(btnDzenle);
	}
}
