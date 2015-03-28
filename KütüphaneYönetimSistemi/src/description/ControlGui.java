package description;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import ObjectClass.author;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

public class ControlGui {

	public Model CreateMember(String ad, String soyad, long tc, String sifre,
			String email) {

		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT ?tc WHERE {" + "?s library:tc \"" + tc
				+ "\"^^xsd:long." + "}";

		ResultSet personResultSet = KutuphaneStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		Model model = null;
		if (!personResultSet.hasNext()) {

			model = IndividualCreator.createUye(ad, soyad, tc, sifre, email);

		}
		return model;
	}
	
	
	
	public Model CreateBook(String title, String publisher, String format, int isbn,   //ArrayList<author>
			int price, int edition, 
			String publicationDate , int bookCount) {

		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT ?isbn WHERE {" + "?s library:isbn \"" + isbn
				+ "\"^^xsd:int." + "}";

		ResultSet bookResultSet = KutuphaneStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		Model model = null;
		if (!bookResultSet.hasNext()) {

			model = IndividualCreator.createBook(title, publisher, format, isbn, price, edition, publicationDate, bookCount);

		}
		return model;
	}

	public boolean MemberLoginControl(Long tc, String password) {

		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT ?tc ?password WHERE {" + "?s library:tc \"" + tc
				+ "\"^^xsd:long." + "?s library:password \"" + password
				+ "\"^^xsd:string " + "}";

		ResultSet personResultSet = KutuphaneStore.getInstance()
				.queryModelAsSelect(sparqlTxt);

		// Bir Resultset'i tekrar tekrar dolaþabilmek için
		// ResultSetRewindable rewindable =
		// ResultSetFactory.copyResults(personResultSet);
		// rewindable.reset();

		//
		// while (personResultSet.hasNext()) {
		// QuerySolution querySolution = (QuerySolution) personResultSet.next();
		// Long tcValue = querySolution.getLiteral("tc").getLong();
		// String passwordvalue =
		// querySolution.getLiteral("password").getString();
		// }

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
				+ "\"^^xsd:string. ?s library:email ?email . ?s foaf:family_name \"" + surname
				+ "\"^^xsd:string. ?s library:tc ?tc" + "}";
		ResultSet resultSet = KutuphaneStore.getInstance().queryModelAsSelect(sparqlTxt);
		while (resultSet.hasNext()) {
			QuerySolution querySolution = (QuerySolution) resultSet.next();
			Resource resource = querySolution.getResource("s");
			userList.add(resource);
		}
		return userList;
	}

	
	public  DefaultListModel BookListFill(List<Resource> bookList){
		
		DefaultListModel listModel = new DefaultListModel<Resource>();
		
		 for(int i =0;i<bookList.size();i++)
		 {
			 listModel.addElement( bookList.get(i).getProperty(
						ResourceFactory
						.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
								+ "title")).getObject().asLiteral().getString() );	
			 
			 
		 }
		
		
		return listModel;
	}
	
public  DefaultListModel ListeDoldur(List<Resource> friendList){
		
		DefaultListModel listModel = new DefaultListModel<Resource>();
		
		 for(int i =0;i<friendList.size();i++)
		 {
			 listModel.addElement( friendList.get(i).getProperty(
						ResourceFactory
						.createProperty(FOAF.getURI()
								+ "name")).getObject().asLiteral().getString()+" " + friendList.get(i).getProperty(
										ResourceFactory
										.createProperty(FOAF.getURI()
												+ "family_name")).getObject().asLiteral().getString()+"  : " + friendList.get(i).getProperty(
														ResourceFactory
														.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
																+ "email")).getObject().asLiteral().getString() );	
			 
			 
		 }
		
		
		return listModel;
	}
	
	public Vector<Resource> searchBookbyName(String title){
		Vector<Resource> bookList = new Vector<Resource>();
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT * WHERE {"  
				+ "?s library:title \"" + title+ "\"^^xsd:string." 	
				+"}";

		
		ResultSet bookResultSet = KutuphaneStore.getInstance().queryModelAsSelect(
				sparqlTxt);
		
		 
		while (bookResultSet.hasNext()) {
			
			QuerySolution querySolution = (QuerySolution) bookResultSet.next();
			Resource resource = querySolution.getResource("s");
			bookList.add(resource);
		}
		
		return bookList;
		
	}
	
	
}
