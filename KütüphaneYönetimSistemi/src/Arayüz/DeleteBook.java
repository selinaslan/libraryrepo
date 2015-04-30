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
import description.OntologyConstants;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteBook extends JFrame {

	private JPanel contentPane;
    public static long id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBook frame = new DeleteBook(id);
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
	public DeleteBook(long id2) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Admin admn = new Admin(id);
				admn.setVisible(true);
				
			}
		});
		id2=id;
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
		
		JLabel lbl = new JLabel("Silece\u011Finiz kitab\u0131n ISBN'ini giriniz:");
		lbl.setBounds(23, 116, 243, 14);
		contentPane.add(lbl);
		
		JTextArea isbnTextArea = new JTextArea();
		isbnTextArea.setBounds(321, 116, 215, 16);
		contentPane.add(isbnTextArea);
		
		JButton findButton = new JButton("Bul");
		
		findButton.setBounds(396, 155, 89, 23);
		contentPane.add(findButton);
		
		JLabel titleLabel = new JLabel("Kitap Ad\u0131:");
		titleLabel.setBounds(23, 238, 77, 14);
		titleLabel.setVisible(false);
		contentPane.add(titleLabel);
		
		JLabel authorLabel = new JLabel("Yazar\u0131:");
		authorLabel.setBounds(23, 279, 46, 14);
		authorLabel.setVisible(false);
		contentPane.add(authorLabel);
		
		JLabel title2Label = new JLabel("");
		title2Label.setBounds(130, 238, 136, 14);
		contentPane.add(title2Label);
		
		JLabel author2Label = new JLabel("");
		author2Label.setBounds(130, 279, 136, 14);
		contentPane.add(author2Label);
		JLabel WarningLabel = new JLabel("");
		WarningLabel.setForeground(Color.RED);
		WarningLabel.setBounds(23, 346, 513, 14);
		contentPane.add(WarningLabel);
		JButton deleteButton = new JButton("Sil");
		deleteButton.setForeground(Color.BLACK);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (isbnTextArea.getText().equals(""))
				{
					WarningLabel.setText("Silinecek kitabýn ISBN'ini giriniz!");
					
					
				}else{
					WarningLabel.setText("");
					//TODO kitap silme
					
					
				}
				
				
				
			}
		});
		deleteButton.setBounds(396, 275, 89, 23);
		contentPane.add(deleteButton);
		
		JLabel publisherLabel = new JLabel("Yayinevi:");
		publisherLabel.setBounds(23, 321, 64, 14);
		publisherLabel.setVisible(false);
		contentPane.add(publisherLabel);
		
		JLabel publisher2Label = new JLabel("");
		publisher2Label.setBounds(130, 321, 136, 14);
		contentPane.add(publisher2Label);
		
		
		
		
		
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!isbnTextArea.getText().equals(""))
				{	
					WarningLabel.setText("");
				Resource bookRsc=null;
				String isbn =  isbnTextArea.getText();
				
			bookRsc=new ControlGui().queryForISBN(Integer.parseInt(isbn));
			         if(bookRsc!=null){
			         titleLabel.setVisible(true);
			         authorLabel.setVisible(true);
			         publisherLabel.setVisible(true);
			         title2Label.setText( bookRsc
						.getProperty(
								ResourceFactory
										.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
												+ "title")).getObject()
						.asLiteral().getString() );
				
		 
			         	author2Label.setText( bookRsc
						.getProperty(
								ResourceFactory
										.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
												+ "author")).getObject()
						.asLiteral().getString() );
			         	publisher2Label.setText( bookRsc
						.getProperty(
								ResourceFactory
										.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
												+ "publisher")).getObject()
						.asLiteral().getString() );} else WarningLabel.setText("Kitap yok!");
				
			}else WarningLabel.setText("Silinecek kitabýn ISBN'ini  giriniz!");
				
				
			
				
			}
		});
		
		
	}
}
