package description;

import org.omg.CORBA.OMGVMCID;

import Arayüz.EditUser;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
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

	public void updatePropertyValue(Resource resource, Property property,
			String name) {
		dataset.begin(ReadWrite.WRITE);
		Model model = dataset.getDefaultModel();
		Statement nameStmt = model.listStatements(resource, property,
				(RDFNode) null).nextStatement();
		if (name != null && !name.equals("")) {
			nameStmt.changeObject(name);
		}
		dataset.commit();
		dataset.end();
	}
	
	public void decreaseBookCount(Resource book) {
		dataset.begin(ReadWrite.WRITE);
		Model model = dataset.getDefaultModel();
		Statement countPrp = model.listStatements(book, OntologyConstants.BOOK_COUNT_PRP,
				(RDFNode) null).nextStatement();
		int count = countPrp.getObject().asLiteral().getInt();
		countPrp.changeLiteralObject(--count);
		dataset.commit();
		dataset.end();
	}

}
