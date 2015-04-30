package Arayüz;

import description.ControlGui;
import description.LibraryStore;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateUser extends JFrame {

	private JPanel contentPane;
	private JTextField tcTextField;
	private JTextField nameTextField;
	private JTextField familyNameTextField;
	private JTextField passwordTextField;
	private JTextField eMailTextField;
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Admin admn= new Admin(id);
				admn.setVisible(true);
			}
		});
		
		id=id2;
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

		JLabel tcLabel = new JLabel("TC No:");
		tcLabel.setBounds(20, 78, 46, 14);
		contentPane.add(tcLabel);

		JLabel nameLabel = new JLabel("Ad:");
		nameLabel.setBounds(20, 115, 46, 14);
		contentPane.add(nameLabel);

		JLabel familyNameLabel = new JLabel("Soyad:");
		familyNameLabel.setBounds(20, 151, 46, 14);
		contentPane.add(familyNameLabel);

		JLabel PasswordLabel = new JLabel("\u015Eifre:");
		PasswordLabel.setBounds(20, 191, 46, 14);
		contentPane.add(PasswordLabel);

		tcTextField = new JTextField();
		tcTextField.setBounds(88, 75, 125, 20);
		contentPane.add(tcTextField);
		tcTextField.setColumns(10);

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(88, 112, 125, 20);
		contentPane.add(nameTextField);

		familyNameTextField = new JTextField();
		familyNameTextField.setColumns(10);
		familyNameTextField.setBounds(88, 148, 125, 20);
		contentPane.add(familyNameTextField);

		passwordTextField = new JTextField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(88, 188, 125, 20);
		contentPane.add(passwordTextField);

		JLabel eMailLabel = new JLabel("E-Mail");
		eMailLabel.setBounds(20, 238, 46, 14);
		contentPane.add(eMailLabel);

		eMailTextField = new JTextField();
		eMailTextField.setColumns(10);
		eMailTextField.setBounds(88, 235, 125, 20);
		contentPane.add(eMailTextField);

		JButton addMemberButton = new JButton("\u00DCYE EKLE");
		addMemberButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ControlGui control = new ControlGui();

				long tc = Long.parseLong(tcTextField.getText());

				Model uyeModel = control.CreateMember(nameTextField.getText(),
						familyNameTextField.getText(), tc, passwordTextField.getText(),
						eMailTextField.getText());

				if (uyeModel != null) {
					LibraryStore.getInstance().addResourceModel(uyeModel);
					// TODO:
					JOptionPane.showMessageDialog(null,
							"Üye baþarýyla eklenmiþtir.");
				}else{
					JOptionPane.showMessageDialog(null,
							"tc vardýr.");
					
				}


			}
		});
		addMemberButton.setBounds(440, 317, 89, 23);
		contentPane.add(addMemberButton);

	}
}
