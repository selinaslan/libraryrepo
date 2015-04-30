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

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

import description.ControlGui;
import description.LibraryStore;
import description.OntologyConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditUser extends JFrame {

	private JPanel contentPane;
	private JTextField tcNoTextField;
	private JTextField nameTextField;
	private JTextField familtNameTextField;
	private JTextField passwordTextField;
	private JTextField eMailTextField;
	public static Resource userRsc = null;
	private static long id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditUser frame = new EditUser(id);
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
	public EditUser(long id2) {

		id = id2;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Admin admn = new Admin(id);
				admn.setVisible(true);

			}
		});
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

		JPanel panel = new JPanel();
		panel.setBounds(21, 83, 515, 87);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel(
				"D\u00FCzenlemek  istedi\u011Finiz \u00FCyenin TC Kimlik Numaras\u0131n\u0131 giriniz:");
		label.setBounds(10, 11, 443, 22);
		panel.add(label);

		tcNoTextField = new JTextField();
		tcNoTextField.setBounds(10, 44, 208, 20);
		panel.add(tcNoTextField);
		tcNoTextField.setColumns(10);

		JButton findButton = new JButton("Bul");

		findButton.setBounds(337, 43, 89, 23);
		panel.add(findButton);

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

		JLabel lbl = new JLabel("");
		lbl.setBounds(109, 220, 139, 14);
		contentPane.add(lbl);

		JLabel lbl2 = new JLabel("");
		lbl2.setBounds(109, 261, 139, 14);
		contentPane.add(lbl2);

		JLabel lbl3 = new JLabel("");
		lbl3.setBounds(109, 299, 139, 14);
		contentPane.add(lbl3);

		JLabel lbl4 = new JLabel("");
		lbl4.setBounds(109, 342, 139, 14);
		contentPane.add(lbl4);

		nameTextField = new JTextField();
		nameTextField.setBounds(297, 217, 152, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);

		familtNameTextField = new JTextField();
		familtNameTextField.setBounds(297, 258, 152, 20);
		contentPane.add(familtNameTextField);
		familtNameTextField.setColumns(10);

		passwordTextField = new JTextField();
		passwordTextField.setBounds(297, 296, 152, 20);
		contentPane.add(passwordTextField);
		passwordTextField.setColumns(10);

		eMailTextField = new JTextField();
		eMailTextField.setBounds(297, 339, 153, 20);
		contentPane.add(eMailTextField);
		eMailTextField.setColumns(10);

		JButton editButton = new JButton("D\u00FCzenle");

		editButton.setBounds(491, 338, 89, 23);
		contentPane.add(editButton);

		JLabel WarningLabel = new JLabel("");
		WarningLabel.setForeground(Color.RED);
		WarningLabel.setBounds(31, 181, 507, 14);
		contentPane.add(WarningLabel);

		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tcNoTextField.getText().equals("")) {
					WarningLabel
							.setText("Silinecek kiþinin TC Kimlik Numarasýný giriniz!");

				} else {
					// TODO sorgu boþ dönerse kontrolü
					WarningLabel.setText("");

					String tc = tcNoTextField.getText();

					userRsc = new ControlGui().queryForTC(Long.parseLong(tc));

					lbl.setText(userRsc.getProperty(FOAF.name).getObject()
							.asLiteral().getString());
					lbl2.setText(userRsc.getProperty(FOAF.family_name)
							.getObject().asLiteral().getString());
					lbl3.setText(userRsc
							.getProperty(
									ResourceFactory
											.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
													+ "password")).getObject()
							.asLiteral().getString());
					lbl4.setText(userRsc
							.getProperty(
									ResourceFactory
											.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
													+ "email")).getObject()
							.asLiteral().getString());

				}
			}
		});

		editButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String name = nameTextField.getText();
				String familyName = familtNameTextField.getText();
				String email = eMailTextField.getText();
				String password = passwordTextField.getText();

				// TODO: name yerine firstName
				LibraryStore.getInstance().updatePropertyValue(EditUser.userRsc, FOAF.name, name);
				LibraryStore.getInstance().updatePropertyValue(
						EditUser.userRsc, FOAF.family_name, familyName);
				LibraryStore.getInstance().updatePropertyValue(
						EditUser.userRsc, OntologyConstants.EMAIL_PROPERTY, email);
				LibraryStore.getInstance().updatePropertyValue(
						EditUser.userRsc, OntologyConstants.PASSWORD_PROPERTY, password);

			}
		});

	}
}
