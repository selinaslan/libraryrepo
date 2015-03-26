package description;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;

public class Main2 {

	public static void main(String[] args) {
		String sparqlTxt = "PREFIX rdf:<" + RDF.getURI() + "> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT ?name ?tc WHERE {" + "?s rdf:type foaf:Person. "
				+ "?s foaf:name ?name." + "?s library:tc ?tc}";

		String bookQuery = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT ?count WHERE {" 
				+ "?book foaf:name \"Lord of the Rings\"^^xsd:string." + "?book library:bookCount ?count}";
		//Model uyeModel = IndividualCreator.createUye(123, "Selin ASLAN");
		Model bookModel = IndividualCreator
				.createBook("1234", "Lord of the Rings", 15);
		//KutuphaneStore.getI0nstance().addResourceModel(uyeModel);
		KutuphaneStore.getInstance().addResourceModel(bookModel);
		// KutuphaneStore.getInstance().printModel();
		ResultSet personResultSet = KutuphaneStore.getInstance().queryModelAsSelect(
				sparqlTxt);
		while (personResultSet.hasNext()) {
			QuerySolution querySolution = (QuerySolution) personResultSet.next();
			String name = querySolution.getLiteral("name").getString();
			int tcValue = querySolution.getLiteral("tc").getInt();
			System.out.println("Name: " + name + " TC: " + tcValue);
		}
		
		ResultSet bookResultSet = KutuphaneStore.getInstance().queryModelAsSelect(
				bookQuery);
		while (bookResultSet.hasNext()) {
			QuerySolution querySolution = (QuerySolution) bookResultSet.next();
			int count = querySolution.getLiteral("count").getInt();
			System.out.println("Book count: " + count);
		}
	}
}
