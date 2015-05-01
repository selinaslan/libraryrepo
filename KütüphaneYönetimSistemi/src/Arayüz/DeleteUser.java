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
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

import description.ControlGui;
import description.LibraryStore;
import description.OntologyConstants;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteUser extends JFrame {

	private JPanel contentPane;
    private static Resource userRsc=null;
    private static long id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser frame = new DeleteUser(id);
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
	public DeleteUser(long id2) {
		
		id=id2;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Admin admn= new Admin(id);
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
		Image img= new ImageIcon(this.getClass().getResource("/Header.jpg")).getImage();
		HeaderLabel.setIcon(new ImageIcon(img));
		HeaderLabel.setBounds(0, 0, 590, 42);
		contentPane.add(HeaderLabel);
		
		JLabel lbl = new JLabel("Silece\u011Finiz \u00FCyenin TC Kimlik Numaras\u0131n\u0131 giriniz:");
		lbl.setBounds(23, 116, 243, 14);
		contentPane.add(lbl);
		
		JTextArea personTextArea = new JTextArea();
		personTextArea.setBounds(321, 116, 215, 16);
		contentPane.add(personTextArea);
		
		JButton findButton = new JButton("Bul");
		
		findButton.setBounds(396, 155, 89, 23);
		contentPane.add(findButton);
		
		JLabel nameLabel = new JLabel("Ad\u0131:");
		nameLabel.setBounds(23, 238, 46, 14);
		nameLabel.setVisible(false);
		contentPane.add(nameLabel);
		
		JLabel familyNameLabel = new JLabel("Soyad\u0131:");
		familyNameLabel.setBounds(23, 279, 46, 14);
		familyNameLabel.setVisible(false);
		contentPane.add(familyNameLabel);
		
		JLabel name2Label = new JLabel("");
		name2Label.setBounds(130, 238, 107, 14);
		contentPane.add(name2Label);
		
		JLabel familyName2Label = new JLabel("");
		familyName2Label.setBounds(130, 279, 107, 14);
		contentPane.add(familyName2Label);
		JLabel WarningLabel = new JLabel("");
		WarningLabel.setForeground(Color.RED);
		WarningLabel.setBounds(23, 332, 513, 14);
		contentPane.add(WarningLabel);
		JButton deleteButton = new JButton("Sil");
		deleteButton.setForeground(Color.BLACK);
		
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!personTextArea.getText().equals(""))
				{	
					WarningLabel.setText("");
				
				String tc =  personTextArea.getText();
				
			userRsc=new ControlGui().queryForTC(Long.parseLong(tc));
			nameLabel.setVisible(true);
			familyNameLabel.setVisible(true);
				name2Label.setText( userRsc
						.getProperty(FOAF.name).getObject()
						.asLiteral().getString()  );
				
		 
				familyName2Label.setText( userRsc
						.getProperty(FOAF.family_name).getObject()
						.asLiteral().getString()  );
				
			}else WarningLabel.setText("Silinecek kiþinin TC Kimlik Numarasýný giriniz!");
				
				
			
				
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (personTextArea.getText().equals(""))
				{
					WarningLabel.setText("Silinecek kiþinin TC Kimlik Numarasýný giriniz!");
					
					
				}else{
					WarningLabel.setText("");
					
					
					
					LibraryStore.getInstance().deleteResource(userRsc);
					
					JOptionPane.showMessageDialog(null, "Üye Silindi!");
					
					
				}
				
				
				
			}
		});
		deleteButton.setBounds(396, 275, 89, 23);
		contentPane.add(deleteButton);
		
		
		
		
		
		
		
		
	}
}
