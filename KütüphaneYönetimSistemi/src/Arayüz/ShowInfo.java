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
import description.OntologyConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ShowInfo extends JFrame {

	private JPanel contentPane;
	private static long id;
	private JTextField tcTextField;
	private JTextField nameTextField;
	private JTextField fNameTextField;
	private JTextField eMAilTextField;
	private JTextField passwordTextField;
    
	public long getId() {
		return id;
	}
	public JPanel panel = new JPanel();
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowInfo frame = new ShowInfo(id);
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void showinf() {
				
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowInfo(long id2) {
		
		id=id2;
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
		
		JLabel tcLbl = new JLabel("TC Kimlik No:");
		tcLbl.setBounds(23, 97, 101, 14);
		contentPane.add(tcLbl);
		
		JLabel nameLbl = new JLabel("Ad\u0131:");
		nameLbl.setBounds(23, 130, 46, 14);
		contentPane.add(nameLbl);
		
		JLabel fNameLbl = new JLabel("Soyad\u0131:");
		fNameLbl.setBounds(23, 166, 57, 14);
		contentPane.add(fNameLbl);
		
		JLabel eMailLbl = new JLabel("E-Posta:");
		eMailLbl.setBounds(23, 204, 57, 14);
		contentPane.add(eMailLbl);
		
		JLabel passwordLbl = new JLabel("\u015Eifre:");
		passwordLbl.setBounds(23, 240, 46, 14);
		contentPane.add(passwordLbl);
		
		JPanel panel = new JPanel();
		panel.setBounds(321, 99, 259, 259);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(0, 30, 164, 20);
		panel.add(nameTextField);
		nameTextField.setColumns(10);
		
		fNameTextField = new JTextField();
		fNameTextField.setBounds(0, 70, 164, 20);
		panel.add(fNameTextField);
		fNameTextField.setColumns(10);
		
		eMAilTextField = new JTextField();
		eMAilTextField.setBounds(0, 103, 164, 20);
		panel.add(eMAilTextField);
		eMAilTextField.setColumns(10);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(0, 134, 164, 20);
		panel.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JButton editButton = new JButton("KAYDET");
	
		editButton.setBounds(160, 208, 89, 23);
		panel.add(editButton);
		
		tcTextField = new JTextField();
		tcTextField.setBounds(0, -1, 164, 20);
		panel.add(tcTextField);
		tcTextField.setColumns(10);
		
		JLabel tc2Lbl = new JLabel("");
		tc2Lbl.setBounds(119, 97, 164, 14);
		
		contentPane.add(tc2Lbl);
		
		JLabel name2Lbl = new JLabel("");
		name2Lbl.setBounds(119, 130, 164, 14);
		contentPane.add(name2Lbl);
		
		JLabel fName2Lbl = new JLabel("");
		fName2Lbl.setBounds(119, 166, 164, 14);
		contentPane.add(fName2Lbl);
		
		JLabel eMail2Lbl = new JLabel("");
		eMail2Lbl.setBounds(119, 204, 164, 14);
		contentPane.add(eMail2Lbl);
		
		JLabel password2Lbl = new JLabel("");
		password2Lbl.setBounds(119, 240, 164, 14);
		contentPane.add(password2Lbl);
		
		
		
		
		
	/*	Resource userRsc=null;
		
		
	    userRsc=new ControlGui().queryForTC(id);
        
	    tc2Lbl.setText( userRsc
				.getProperty(
						ResourceFactory
								.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
										+ "tc")).getObject()
				.asLiteral().getString() );
	    name2Lbl.setText( userRsc
				.getProperty(FOAF.name).getObject()
				.asLiteral().getString()  );
		
	    fName2Lbl.setText( userRsc
				.getProperty(FOAF.family_name).getObject()
				.asLiteral().getString()  );
	    
	    eMail2Lbl.setText( userRsc
				.getProperty(
						ResourceFactory
								.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
										+ "email")).getObject()
				.asLiteral().getString() );
	    password2Lbl.setText( userRsc
				.getProperty(
						ResourceFactory
								.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
										+ "password")).getObject()
				.asLiteral().getString() );*/
		
		//btnNewButton.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent arg0) {
				
		 if(id!=0) {       Resource userRsc=null;
				
				
			    userRsc=new ControlGui().queryForTC(id);
		        
			    tc2Lbl.setText( Long.toString(id));
			    name2Lbl.setText( userRsc
						.getProperty(FOAF.name).getObject()
						.asLiteral().getString()  );
				
			    fName2Lbl.setText( userRsc
						.getProperty(FOAF.family_name).getObject()
						.asLiteral().getString()  );
			    
			    eMail2Lbl.setText( userRsc
						.getProperty(
								ResourceFactory
										.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
												+ "email")).getObject()
						.asLiteral().getString() );
			    password2Lbl.setText( userRsc
						.getProperty(
								ResourceFactory
										.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
												+ "password")).getObject()
						.asLiteral().getString() ); }
			    
			    JButton btnDzenle = new JButton("D\u00FCzenle");
			   
			    btnDzenle.setBounds(23, 290, 89, 23);
			    contentPane.add(btnDzenle);
			    
			    JLabel warningLabel = new JLabel("");
			    warningLabel.setForeground(Color.RED);
			    warningLabel.setBounds(23, 336, 288, 14);
			    contentPane.add(warningLabel);
			    
			    JLabel warning2Label = new JLabel("");
			    warning2Label.setForeground(Color.MAGENTA);
			    warning2Label.setBounds(23, 365, 260, 14);
			    contentPane.add(warning2Label);
				
			    btnDzenle.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
			    		warningLabel.setText("Lütfen bütün boþuklarý doldurunuz!");
			    		
			    		panel.setVisible(true);
			    		
			    	}
			    });
				
		//	}
		//});
			    
				editButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (tcTextField.getText().equals(null) && nameTextField.getText().equals(null) && fNameTextField.getText().equals(null)
								&&  eMAilTextField.getText().equals(null) && passwordTextField.getText().equals(null) ){
							
							
							
							
						}else {warning2Label.setText("Bütün alanlarý doldurmadýnýz!");}
						
						
						
						
						
						
					}
				});  
			    
		
		
	}
}
