package Arayüz;

import description.ControlGui;

import description.KutuphaneStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.hp.hpl.jena.rdf.model.Model;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public static int id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser frame = new CreateUser(id);
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
	public CreateUser(int id2) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel HeaderLabel = new JLabel("New label");
		Image img = new ImageIcon(this.getClass().getResource("/Header.jpg"))
				.getImage();
		HeaderLabel.setIcon(new ImageIcon(img));
		HeaderLabel.setBounds(0, 0, 590, 42);
		contentPane.add(HeaderLabel);

		JLabel lblTcNo = new JLabel("TC No:");
		lblTcNo.setBounds(20, 78, 46, 14);
		contentPane.add(lblTcNo);

		JLabel lblAd = new JLabel("Ad:");
		lblAd.setBounds(20, 115, 46, 14);
		contentPane.add(lblAd);

		JLabel lblSoyad = new JLabel("Soyad:");
		lblSoyad.setBounds(20, 151, 46, 14);
		contentPane.add(lblSoyad);

		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setBounds(20, 191, 46, 14);
		contentPane.add(lblifre);

		textField = new JTextField();
		textField.setBounds(88, 75, 125, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(88, 112, 125, 20);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(88, 148, 125, 20);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(88, 188, 125, 20);
		contentPane.add(textField_3);

		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(20, 238, 46, 14);
		contentPane.add(lblEmail);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(88, 235, 125, 20);
		contentPane.add(textField_4);

		JButton btnyeEkle = new JButton("\u00DCYE EKLE");
		btnyeEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ControlGui control = new ControlGui();

				long tc = Long.parseLong(textField.getText());

				Model uyeModel = control.CreateMember(textField_1.getText(),
						textField_2.getText(), tc, textField_3.getText(),
						textField_4.getText());

				if (uyeModel != null) {
					KutuphaneStore.getInstance().addResourceModel(uyeModel);
					// TODO:
					JOptionPane.showMessageDialog(null,
							"Üye baþarýyla eklenmiþtir.");
				}else{
					JOptionPane.showMessageDialog(null,
							"tc vardýr.");
					
				}


			}
		});
		btnyeEkle.setBounds(440, 317, 89, 23);
		contentPane.add(btnyeEkle);

	}
}
