package Arayüz;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

import description.ControlGui;
import description.LibraryStore;

import javax.swing.JList;

public class CreateBook extends JFrame {

	private static final String DBPEDIA_SPARQL_ENDPOINT_URI = "http://dbpedia.org/sparql";
	private JPanel contentPane;
	private JTextField bookNameTextField;
	private JTextField publisherTextField;
	private JTextField formatTextField;
	private JTextField isbnTextField;
	private JTextField priceTextField;
	private JTextField editionTextField;
	private JTextField authorTextField;
	private JTextField pdateTextField;
	private JTextField countTextField;
	public static long id;
	List<Resource> bookList = new ArrayList<Resource>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateBook frame = new CreateBook(id);
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
	public CreateBook(long id2) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Admin admn = new Admin(id);
				admn.setVisible(true);

			}
		});
		id = id2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel HeaderLabel = new JLabel("New label");
		HeaderLabel.setBounds(0, 0, 590, 42);
		Image img = new ImageIcon(this.getClass().getResource("/Header.jpg"))
				.getImage();
		contentPane.setLayout(null);
		HeaderLabel.setIcon(new ImageIcon(img));
		contentPane.add(HeaderLabel);

		JPanel addBookPanel = new JPanel();
		addBookPanel.setBounds(21, 53, 559, 313);
		contentPane.add(addBookPanel);
		addBookPanel.setLayout(null);

		JLabel isbnLabel = new JLabel("ISBN:");
		isbnLabel.setBounds(10, 99, 91, 14);
		addBookPanel.add(isbnLabel);

		JLabel bookNameLabel = new JLabel("Kitab\u0131n Ad\u0131:");
		bookNameLabel.setBounds(10, 24, 91, 14);
		addBookPanel.add(bookNameLabel);

		JLabel publisherLabel = new JLabel("Yay\u0131nevi:");
		publisherLabel.setBounds(10, 49, 91, 14);
		addBookPanel.add(publisherLabel);

		JLabel formatLabel = new JLabel("T\u00FCr:");
		formatLabel.setBounds(10, 74, 91, 14);
		addBookPanel.add(formatLabel);

		JLabel priceLabel = new JLabel("Fiyat");
		priceLabel.setBounds(10, 127, 91, 14);
		addBookPanel.add(priceLabel);

		JLabel editionLabel = new JLabel("Bas\u0131m:");
		editionLabel.setBounds(10, 152, 91, 14);
		addBookPanel.add(editionLabel);

		JLabel dateLabel = new JLabel("Yay\u0131n Tarihi:");
		dateLabel.setBounds(10, 202, 91, 14);
		addBookPanel.add(dateLabel);

		JLabel authorLabel = new JLabel("Yazar:");
		authorLabel.setBounds(10, 177, 91, 14);
		addBookPanel.add(authorLabel);

		JLabel countLabel = new JLabel("Adet:");
		countLabel.setBounds(10, 227, 91, 14);
		addBookPanel.add(countLabel);

		bookNameTextField = new JTextField();
		bookNameTextField.setBounds(118, 21, 164, 20);
		addBookPanel.add(bookNameTextField);
		bookNameTextField.setColumns(10);

		publisherTextField = new JTextField();
		publisherTextField.setColumns(10);
		publisherTextField.setBounds(118, 49, 164, 20);
		addBookPanel.add(publisherTextField);

		formatTextField = new JTextField();
		formatTextField.setColumns(10);
		formatTextField.setBounds(118, 74, 164, 20);
		addBookPanel.add(formatTextField);

		isbnTextField = new JTextField();
		isbnTextField.setColumns(10);
		isbnTextField.setBounds(118, 99, 164, 20);
		addBookPanel.add(isbnTextField);

		priceTextField = new JTextField();
		priceTextField.setColumns(10);
		priceTextField.setBounds(118, 124, 164, 20);
		addBookPanel.add(priceTextField);

		editionTextField = new JTextField();
		editionTextField.setColumns(10);
		editionTextField.setBounds(118, 149, 164, 20);
		addBookPanel.add(editionTextField);

		authorTextField = new JTextField();
		authorTextField.setColumns(10);
		authorTextField.setBounds(118, 174, 164, 20);
		addBookPanel.add(authorTextField);

		pdateTextField = new JTextField();
		pdateTextField.setColumns(10);
		pdateTextField.setBounds(118, 199, 164, 20);
		addBookPanel.add(pdateTextField);

		countTextField = new JTextField();
		countTextField.setColumns(10);
		countTextField.setBounds(118, 224, 164, 20);
		addBookPanel.add(countTextField);

		JButton addButton = new JButton("EKLE");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ControlGui control = new ControlGui();

				Model bookModel = control.CreateBook(
						bookNameTextField.getText(),
						publisherTextField.getText(),
						formatTextField.getText(),
						Integer.parseInt(isbnTextField.getText()),
						Integer.parseInt(priceTextField.getText()),
						Integer.parseInt(editionTextField.getText()),
						authorTextField.getText(), pdateTextField.getText(),
						Integer.parseInt(countTextField.getText()));

				if (bookModel != null) {
					LibraryStore.getInstance().addResourceModel(bookModel);
					// TODO:
					JOptionPane.showMessageDialog(null,
							"Kitap baþarýyla eklenmiþtir.");
				} else {
					JOptionPane.showMessageDialog(null, "kitap vardýr.");

				}

			}
		});
		addButton.setBounds(403, 259, 89, 23);
		addBookPanel.add(addButton);

		JButton btnek = new JButton("\u00C7EK");

		btnek.setBounds(403, 223, 89, 23);
		addBookPanel.add(btnek);

		JList list = new JList();
		list.setBounds(349, 23, 177, 165);
		addBookPanel.add(list);

		JButton btnEkle = new JButton("EKLE");
		btnek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = bookNameTextField.getText();
				Vector<Resource> resources = new Vector<Resource>();
				String getBookQuery = "select * where {"
						+ "?book <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://dbpedia.org/ontology/Book>."
						+ "?book <http://dbpedia.org/property/name> \""
						+ title
						+ "\"@en."
						+ "?book <http://dbpedia.org/ontology/author> ?author."
						+ "?author <http://dbpedia.org/property/name> ?authorName."
						+ "?book <http://dbpedia.org/property/publisher> ?publisher."
						+ "?publisher <http://www.w3.org/2000/01/rdf-schema#label> ?pubLabel."
						+ "?book <http://dbpedia.org/property/pubDate> ?pubDate."
						// + "FILTER contains(lcase(?name),lcase(\"" + title
						// + "\"^^<http://www.w3.org/2001/XMLSchema#string>))"
						+ "} LIMIT 1";
				Query query = QueryFactory.create(getBookQuery);
				System.out.println(query);
				ResultSet resultSet = QueryExecutionFactory.sparqlService(
						DBPEDIA_SPARQL_ENDPOINT_URI, query).execSelect();
				while (resultSet.hasNext()) {
					QuerySolution querySolution = (QuerySolution) resultSet
							.next();
					System.out.println(querySolution);
					Resource bookRsc = querySolution.get("book").asResource();
					bookList.add(bookRsc);
					// System.out.println(bookRsc);
					String authorName = querySolution.get("authorName")
							.asLiteral().getString();
					String pubLabel = querySolution.get("pubLabel").asLiteral()
							.getString();
					String dateStr = querySolution.get("pubDate").asLiteral()
							.getString();

					publisherTextField.setText(pubLabel);
					authorTextField.setText(authorName);
					pdateTextField.setText(dateStr);
				}

				// friend eklemek için düzeltilecek

			}

		});
	}
}
