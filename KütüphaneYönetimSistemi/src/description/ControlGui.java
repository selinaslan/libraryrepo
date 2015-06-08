package description;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import ObjectClass.author;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

public class ControlGui {

	public Model CreateMember(String name, String familyName, long tc,
			String password, String email) {

		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT ?tc WHERE {" + "?s library:tc \"" + tc
				+ "\"^^xsd:long." + "}";

		ResultSet personResultSet = LibraryStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		Model model = null;
		if (!personResultSet.hasNext()) {

			model = IndividualCreator.createMember(name, familyName, tc,
					password, email);

		}
		return model;
	}

	public Model CreateBook(String title, String publisher, String format,
			int isbn, int price, int edition, String author,
			String publicationDate, int bookCount) {

		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT ?isbn WHERE {" + "?s library:isbn \"" + isbn
				+ "\"^^xsd:int." + "}";

		ResultSet bookResultSet = LibraryStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		Model model = null;
		if (!bookResultSet.hasNext()) {

			model = IndividualCreator.createBook(title, publisher, format,
					isbn, price, edition, author, publicationDate, bookCount);

		}
		return model;
	}

	public Model CreateRental(long renterId, int isbn, Date rentDate,
			Date dueDate) {

		Model model = IndividualCreator.createRental(renterId, isbn, rentDate,
				dueDate);
		LibraryStore.getInstance().addResourceModel(model);

		return model;
	}

	public boolean MemberLoginControl(Long tc, String password) {

		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT ?tc ?password WHERE {" + "?s library:tc \"" + tc
				+ "\"^^xsd:long." + "?s library:password \"" + password
				+ "\"^^xsd:string " + "}";

		ResultSet personResultSet = LibraryStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		if (personResultSet.hasNext())
			return true;

		return false;
	}

	public boolean AdminLoginControl(Long tc, String password) {

		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT ?tc ?password ?username WHERE {" + "?s library:tc \""
				+ tc + "\"^^xsd:long." + "?s library:password \"" + password
				+ "\"^^xsd:string ." + " ?s library:userName ?username." + "}";

		ResultSet personResultSet = LibraryStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		if (personResultSet.hasNext())
			return true;

		return false;
	}

	public Vector<Resource> queryForFriends(String name, String surname) {
		Vector<Resource> userList = new Vector<Resource>();
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT * WHERE {" + "?s foaf:name \"" + name
				+ "\"^^xsd:string. " + " ?s foaf:family_name \"" + surname
				+ "\"^^xsd:string. " + "?s library:email ?email ."
				+ "?s library:tc ?tc"

				+ "}";
		ResultSet resultSet = LibraryStore.getInstance().queryModelAsSelect(
				sparqlTxt);
		while (resultSet.hasNext()) {
			QuerySolution querySolution = (QuerySolution) resultSet.next();
			Resource resource = querySolution.getResource("s");
			userList.add(resource);
		}
		return userList;
	}

	public Vector<Resource> queryForAllFriends(Long tc) {
		Vector<Resource> userList = new Vector<Resource>();
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT * WHERE {" + "?s library:tc \"" + tc
				+ "\"^^xsd:long. "

				+ "?s foaf:knows ?friend. " + "?friend foaf:name ?friendName ."
				+ "?friend library:email ?friendMail ."

				+ "}";
		ResultSet resultSet = LibraryStore.getInstance().queryModelAsSelect(
				sparqlTxt);
		while (resultSet.hasNext()) {

			QuerySolution querySolution = (QuerySolution) resultSet.next();

			// Resource resource = querySolution.getResource("s");
			Resource resource = querySolution.getResource("friend");

			userList.add(resource);
			// System.out.println("  yazzzzzzz "+ resource.getProperty(
			// ResourceFactory
			// .createProperty(OntologyConstants.ONTOLOGY_BASE_URI
			// + "friendName")).getObject()
			// .asLiteral().getString() ) ;

			String friend = querySolution.getLiteral("friendName").getString();
			System.out.println("arkadaþ::" + friend);
		}
		return userList;
	}

	public DefaultListModel BookListFill(List<Resource> bookList) {

		DefaultListModel listModel = new DefaultListModel<Resource>();

		for (int i = 0; i < bookList.size(); i++) {
			listModel
					.addElement(bookList
							.get(i)
							.getProperty(
									ResourceFactory
											.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
													+ "title")).getObject()
							.asLiteral().getString());

		}

		return listModel;
	}

	public DefaultListModel allFriendsListFill(List<Resource> friendsList) {

		DefaultListModel listModel = new DefaultListModel<Resource>();

		for (int i = 0; i < friendsList.size(); i++) {
			listModel.addElement(friendsList
					.get(i)
					.getProperty(
							ResourceFactory.createProperty(FOAF.getURI()
									+ "name")).getObject().asLiteral()
					.getString());

		}

		return listModel;
	}

	public DefaultListModel ListeDoldur(List<Resource> friendList) {

		DefaultListModel listModel = new DefaultListModel<Resource>();

		for (int i = 0; i < friendList.size(); i++) {
			listModel
					.addElement(friendList
							.get(i)
							.getProperty(
									ResourceFactory.createProperty(FOAF
											.getURI() + "name")).getObject()
							.asLiteral().getString()
							+ " "
							+ friendList
									.get(i)
									.getProperty(
											ResourceFactory.createProperty(FOAF
													.getURI() + "family_name"))
									.getObject().asLiteral().getString()
							+ "  : "
							+ friendList
									.get(i)
									.getProperty(
											ResourceFactory
													.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
															+ "email"))
									.getObject().asLiteral().getString());

		}

		return listModel;
	}

	public DefaultListModel kitapListeDoldur(List<Resource> bookList) {

		DefaultListModel listModel = new DefaultListModel<Resource>();

		for (int i = 0; i < bookList.size(); i++) {
			listModel.addElement(((QuerySolution) bookList.get(i)).getLiteral(
					"pubDate").getString());

		}

		return listModel;
	}

	public Vector<Resource> searchBookbyName(String title) {
		Vector<Resource> bookList = new Vector<Resource>();
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX library:<" + OntologyConstants.ONTOLOGY_BASE_URI
				+ "> " + "SELECT * WHERE {" + "?s library:title ?title."
				+ "FILTER contains(lcase(?title),lcase(\"" + title
				+ "\"^^xsd:string))"
				// + "FILTER regex(?title,\"^"+title+"\"^^xsd:string,\"i\" )"
				// + "?s library:title \"" + title+ "\"^^xsd:string."
				+ "}";

		ResultSet bookResultSet = LibraryStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		while (bookResultSet.hasNext()) {

			QuerySolution querySolution = (QuerySolution) bookResultSet.next();
			Resource resource = querySolution.getResource("s");
			bookList.add(resource);
		}

		return bookList;

	}

	public Vector<Resource> searchBookbyAuthor(String author) {

		Vector<Resource> bookList = new Vector<Resource>();
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX library:<" + OntologyConstants.ONTOLOGY_BASE_URI
				+ "> " + "SELECT * WHERE {" + "?s library:author ?author."
				+ "FILTER contains(lcase(?author),lcase(\"" + author
				+ "\"^^xsd:string))"

				+ "}";

		ResultSet bookResultSet = LibraryStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		System.out.println(sparqlTxt);
		while (bookResultSet.hasNext()) {

			QuerySolution querySolution = (QuerySolution) bookResultSet.next();
			Resource resource = querySolution.getResource("s");
			bookList.add(resource);
		}

		return bookList;

	}

	public Vector<Resource> searchBookbyPublisher(String publisher) {
		Vector<Resource> bookList = new Vector<Resource>();
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX library:<" + OntologyConstants.ONTOLOGY_BASE_URI
				+ "> " + "SELECT * WHERE {"
				+ " ?s library:publisher ?publisher .  "
				+ "FILTER contains(lcase(?publisher),lcase(\"" + publisher
				+ "\"^^xsd:string))"

				+ "}";

		ResultSet bookResultSet = LibraryStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		while (bookResultSet.hasNext()) {

			QuerySolution querySolution = (QuerySolution) bookResultSet.next();
			Resource resource = querySolution.getResource("s");
			bookList.add(resource);
		}

		return bookList;

	}

	public Vector<Resource> searchBookbyAuthorAndTitle(String author,
			String title) {
		Vector<Resource> bookList = new Vector<Resource>();
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX library:<" + OntologyConstants.ONTOLOGY_BASE_URI
				+ "> " + "SELECT * WHERE {" + "?s library:author \"" + author
				+ "\"^^xsd:string." + "  ?s library:title \"" + title
				+ "\"^^xsd:string." + "}";

		ResultSet bookResultSet = LibraryStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		while (bookResultSet.hasNext()) {

			QuerySolution querySolution = (QuerySolution) bookResultSet.next();
			Resource resource = querySolution.getResource("s");
			bookList.add(resource);
		}

		return bookList;

	}

	public int StockControl(int isbn) {

		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX library:<" + OntologyConstants.ONTOLOGY_BASE_URI
				+ "> " + "SELECT * WHERE {" + "?s library:isbn \"" + isbn
				+ "\"^^xsd:int." + "?s library:bookCount ?bookCount." + "}";

		ResultSet stockResultSet = LibraryStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		int bookC = 0;
		if (!(stockResultSet.hasNext())) {
			return 0;

		} else {

			QuerySolution querySolution = (QuerySolution) stockResultSet.next();

			bookC = querySolution.getLiteral("bookCount").getInt();

		}

		return bookC;

	}

	public Resource queryForTC(Long tc) {
		Resource userRsc = null;
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT * WHERE {" + "?s foaf:name ?name ."
				+ " ?s library:email ?email . "
				+ "?s library:password ?password." + " ?s library:tc \"" + tc
				+ "\"^^xsd:long." + "}";

		ResultSet resultSet = LibraryStore.getInstance().queryModelAsSelect(
				sparqlTxt);

		while (resultSet.hasNext()) {
			QuerySolution querySolution = (QuerySolution) resultSet.next();
			Resource resource = querySolution.getResource("s");
			userRsc = resource;
			// kontrol için konsola yazdýrýr
			/*
			 * String name = querySolution.getLiteral("name").getString();
			 * String soyad =
			 * querySolution.getLiteral("familyName").getString(); String email
			 * = querySolution.getLiteral("email").getString();
			 * System.out.println("     ad:"+ name + soyad+ email);
			 */

		}
		return userRsc;
	}

	public Resource queryForAdmin(Long tc) {
		Resource userRsc = null;
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT * WHERE {" + "?s foaf:name ?name ."
				+ " ?s library:email ?email . "
				+ "?s library:password ?password." + " ?s library:tc \"" + tc
				+ "\"^^xsd:long." + " ?s library:username ?username." + "}";

		ResultSet resultSet = LibraryStore.getInstance().queryModelAsSelect(
				sparqlTxt);

		while (resultSet.hasNext()) {
			QuerySolution querySolution = (QuerySolution) resultSet.next();
			Resource resource = querySolution.getResource("s");
			userRsc = resource;
			// kontrol için konsola yazdýrýr
			/*
			 * String name = querySolution.getLiteral("name").getString();
			 * String soyad =
			 * querySolution.getLiteral("familyName").getString(); String email
			 * = querySolution.getLiteral("email").getString();
			 * System.out.println("     ad:"+ name + soyad+ email);
			 */

		}
		return userRsc;
	}

	public Resource queryForISBN(int isbn) {
		Resource userRsc = null;
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				//
				+ "PREFIX library:<" + OntologyConstants.ONTOLOGY_BASE_URI
				+ "> " + "SELECT * WHERE {" + " ?s library:title ?title."
				+ " ?s library:isbn \"" + isbn + "\"^^xsd:int."
				+ "?s library:author ?author ." + "}";

		ResultSet bookResultSet = LibraryStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		while (bookResultSet.hasNext()) {
			QuerySolution querySolution = (QuerySolution) bookResultSet.next();
			Resource resource = querySolution.getResource("s");
			userRsc = resource;

		}
		return userRsc;
	}

}
