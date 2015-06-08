package description;

import java.util.ArrayList;
import java.util.List;

import Arayüz.Suggestion;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.tdb.TDBFactory;

public class LibraryStore {

	private Dataset dataset;

	private static LibraryStore instance;

	public LibraryStore() {
		super();
		dataset = TDBFactory
				.createDataset(DatasetConstants.LIBRARY_DATASET_NAME);

	}

	public void addResourceModel(Model resourceModel) {
		dataset.begin(ReadWrite.WRITE);
		Model model = dataset.getDefaultModel();

		model.add(resourceModel);
		dataset.commit();
		dataset.end();
	}

	public void deleteResource(Resource resource) {
		dataset.begin(ReadWrite.WRITE);
		Model model = dataset.getDefaultModel();
		model.removeAll(resource, null, null);
		model.removeAll(null, null, resource);
		dataset.commit();
		dataset.end();
	}

	public static LibraryStore getInstance() {
		if (instance == null) {
			instance = new LibraryStore();
		}
		return instance;
	}

	public ResultSet queryModelAsSelect(String sparqlTxt) {
		ResultSet result = null;
		dataset.begin(ReadWrite.READ);
		result = QueryExecutionFactory.create(sparqlTxt, dataset).execSelect();
		dataset.end();
		return result;
	}

	public void printModel() {
		dataset.begin(ReadWrite.READ);
		Model model = dataset.getDefaultModel();
		model.write(System.out);
		dataset.end();
	}

	public void addStatement(Resource resource, Property property, RDFNode node) {
		dataset.begin(ReadWrite.WRITE);
		Model model = dataset.getDefaultModel();
		model.add(resource, property, node);
		dataset.commit();
		dataset.end();
	}

	public void updatePropertyStringValue(Resource resource, Property property,
			String name) {
		dataset.begin(ReadWrite.WRITE);
		Model model = dataset.getDefaultModel();
		Statement nameStmt = model.listStatements(resource, property,
				(RDFNode) null).nextStatement();
		if (name != null && !name.equals("")) {
			nameStmt.changeObject(ResourceFactory.createTypedLiteral(name,
					XSDDatatype.XSDstring));
		}
		dataset.commit();
		dataset.end();
	}

	public void decreaseBookCount(Resource book) {
		dataset.begin(ReadWrite.WRITE);
		Model model = dataset.getDefaultModel();
		Statement countPrp = model.listStatements(book,
				OntologyConstants.BOOK_COUNT_PRP, (RDFNode) null)
				.nextStatement();
		int count = countPrp.getObject().asLiteral().getInt();
		countPrp.changeLiteralObject(--count);
		dataset.commit();
		dataset.end();
	}

	public Resource findUser(String tcNo) {
		Resource user = null;
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT * WHERE {" + " ?s library:tc \"" + tcNo
				+ "\"^^xsd:long." + "}";
		ResultSet resultSet = queryModelAsSelect(sparqlTxt);
		if (resultSet.hasNext()) {
			user = resultSet.next().getResource("s");
		}
		return user;
	}

	public static List<Suggestion> suggestBook(Resource user) {
		List<Suggestion> suggestions = new ArrayList<Suggestion>();
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT * WHERE { " + "<" + user.getURI()
				+ "> foaf:knows ?friend." + " <" + user.getURI()
				+ "> library:read ?bookSelf. "
				+ "?bookSelf library:author ?author. "
				+ "?friend library:read ?friendBook. "
				+ "?friendBook library:author ?author."
				+ "?friendBook library:title ?title."
				+ "?friend foaf:name ?name."
				+ "?friend foaf:family_name ?fname."
				+ "FILTER (?bookSelf NOT IN (?friendBook)) } ";
		ResultSet resultSet = getInstance().queryModelAsSelect(sparqlTxt);
		while (resultSet.hasNext()) {
			QuerySolution querySolution = (QuerySolution) resultSet.next();
			String author = querySolution.getLiteral("author").getString();
			String title = querySolution.getLiteral("title").getString();
			String name = querySolution.getLiteral("name").getString();
			String fname = querySolution.getLiteral("fname").getString();
			Resource book = querySolution.getResource("friendBook");
			suggestions.add(new Suggestion(book, title, author, name + " "
					+ fname));
		}

		return suggestions;
	}
}
