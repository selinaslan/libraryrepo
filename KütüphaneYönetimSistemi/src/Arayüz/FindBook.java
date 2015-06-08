package Arayüz;

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

import java.awt.Component;

import javax.swing.Box;

import java.awt.SystemColor;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JScrollPane;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

import description.ControlGui;
import description.LibraryStore;
import description.OntologyConstants;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FindBook extends JFrame {

	private JPanel contentPane;
	private JTextField bookNameTextField;
	private JTextField authorNameTextField;
	private JTextField publisherTextField;
	public static long id;
	int index =0;
	 List<Resource> bookList = new ArrayList<Resource>();
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindBook frame = new FindBook(id);
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
	
	
	public FindBook(long id2) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				User usr = new User(id);
				usr.setVisible(true);
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
		Image img= new ImageIcon(this.getClass().getResource("/Header.jpg")).getImage();
		HeaderLabel.setIcon(new ImageIcon(img));
		HeaderLabel.setBounds(0, 0, 590, 42);
		contentPane.add(HeaderLabel);
		
		JPanel searchButton = new JPanel();
		searchButton.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchButton.setBounds(33, 75, 265, 274);
		contentPane.add(searchButton);
		searchButton.setLayout(null);
		
		JLabel bookNameLabel = new JLabel("Kitap ad\u0131na g\u00F6re arama:");
		bookNameLabel.setBounds(10, 11, 159, 14);
		searchButton.add(bookNameLabel);
		
		bookNameTextField = new JTextField();
		bookNameTextField.setBounds(10, 36, 196, 20);
		searchButton.add(bookNameTextField);
		bookNameTextField.setColumns(10);
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(322, 75, 258, 274);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 238, 172);
		panel_1.add(scrollPane);
		
		JList list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index =list.locationToIndex(arg0.getPoint());
			}
		});
		scrollPane.setViewportView(list);
		
		JLabel authorNameLabel = new JLabel("Yazar ad\u0131na g\u00F6re arama:");
		authorNameLabel.setBounds(10, 67, 169, 14);
		searchButton.add(authorNameLabel);
		
		authorNameTextField = new JTextField();
		authorNameTextField.setBounds(10, 92, 196, 20);
		searchButton.add(authorNameTextField);
		authorNameTextField.setColumns(10);
		
		JButton btnAra = new JButton("Ara");
		btnAra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//TODO like operasyonu			
				if(!(bookNameTextField.getText().equals(""))){
				if(authorNameTextField.getText().equals("")){
				
					System.out.print("   $$$$$$$$$$$$$");
					String title = bookNameTextField.getText();
					
					Vector<Resource> books =  new ControlGui().searchBookbyName(title);
					Resource bookRsc=null;
					for (int i = 0; i < books.size(); i++) {
						bookRsc = books.get(i);
						System.out.println(bookRsc
								.getProperty(
										ResourceFactory
												.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
														+ "title")).getObject()
								.asLiteral().getString());
						
						
						 bookList.add(bookRsc);
						 
						 }
					list.setModel(new ControlGui().BookListFill(bookList));
				}else{// kitap + yazar
					
					
					
					System.out.print("  &&&&&&&&&&&");
					String title = bookNameTextField.getText();
					String author = authorNameTextField.getText();
					Vector<Resource> books =  new ControlGui().searchBookbyAuthorAndTitle(author, title);
					Resource bookRsc=null;
					for (int i = 0; i < books.size(); i++) {
						System.out.print(books.size());
						
						bookRsc = books.get(i);
						System.out.println(bookRsc
								.getProperty(
										ResourceFactory
												.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
														+ "title")).getObject()
								.asLiteral().getString());
						
						
						 bookList.add(bookRsc);
						 list.setModel(new ControlGui().BookListFill(bookList));
					
				} 
					
					
				}
				}else {System.out.print("   !!!!!!!!!!!!!!!!!!!!");
				String author = authorNameTextField.getText();
				
				Vector<Resource> books =  new ControlGui().searchBookbyAuthor(author);
				Resource bookRsc=null;
				for (int i = 0; i < books.size(); i++) {
					bookRsc = books.get(i);
					System.out.println(bookRsc
							.getProperty(
									ResourceFactory
											.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
													+ "title")).getObject()
							.asLiteral().getString());
					
					
					 bookList.add(bookRsc);
					 
					 }
				list.setModel(new ControlGui().BookListFill(bookList));
					}
				
				
				
				
				
				
			}
		});
		btnAra.setBounds(157, 118, 89, 23);
		searchButton.add(btnAra);
		
		JLabel publisherLabel = new JLabel("Yay\u0131nevine g\u00F6re arama:");
		publisherLabel.setBounds(7, 159, 199, 14);
		searchButton.add(publisherLabel);
		
		publisherTextField = new JTextField();
		publisherTextField.setBounds(10, 178, 189, 20);
		searchButton.add(publisherTextField);
		publisherTextField.setColumns(10);
		
		JButton searchbutton2 = new JButton("Ara");
		searchbutton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
	String publisher = publisherTextField.getText();
				
				Vector<Resource> books =  new ControlGui().searchBookbyPublisher(publisher);
				Resource bookRsc=null;
				for (int i = 0; i < books.size(); i++) {
					bookRsc = books.get(i);
					System.out.println(bookRsc
							.getProperty(
									ResourceFactory
											.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
													+ "title")).getObject()
							.asLiteral().getString());
					
					
					 bookList.add(bookRsc);
					 
					 }
				list.setModel(new ControlGui().BookListFill(bookList));
				
				
				
			}
		});
		searchbutton2.setBounds(157, 209, 89, 23);
		searchButton.add(searchbutton2);
		
	
		
		JButton addButton = new JButton("Ekle");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			
		int	stockc=0;
		stockc=new ControlGui().StockControl(bookList.get(index)
				.getProperty(
						ResourceFactory
								.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
										+ "isbn")).getObject()
				.asLiteral().getInt());
		if(stockc==0){
			JOptionPane.showMessageDialog(null, "Kitap Stokta bulunmuyor");
			
			}else { 
				
				//TODO kitap ekleyemiyoruz :(((
				Property readPrp = ResourceFactory
				.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
						+ "read");
				
				//Seçilen kitap kontrolü
				/*System.out.println(bookList.get(index).getProperty(
						ResourceFactory
						.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
								+ "author")).getObject()
		.asLiteral().getString());*/
				
				LibraryStore
				.getInstance()
						.addStatement(
								ResourceFactory
										.createResource(OntologyConstants.RESOURCE_BASE_URI
												+ FindBook.id), readPrp,
								bookList.get(index));
			/*	
				ControlGui control = new ControlGui();
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");
				Calendar c = Calendar.getInstance();
				c.setTime(new Date()); // Now use today date.
				dateFormat.format(c)
			
				Model rentalmodel = control.CreateRental(FindBook.id, bookList.get(index)
						.getProperty(
								ResourceFactory
										.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
												+ "isbn")).getObject()
						.asLiteral().getInt(), c, c.add(Calendar.DATE, 7));
				*/
				
				JOptionPane.showMessageDialog(null,
						"Kitap alýnmýþtýr.");
				
				
				
				
				
				
				
				
				
				
			
				
				
			}
		
		
				
				
			}
		});
		addButton.setBounds(159, 210, 89, 23);
		panel_1.add(addButton);
		

}
}
