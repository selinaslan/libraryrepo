package description;

import java.beans.Statement;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.tdb.TDBFactory;

public class KutuphaneStore {

	private Dataset dataset;

	private static KutuphaneStore instance;

	public KutuphaneStore() {
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

	public static KutuphaneStore getInstance() {
		if (instance == null) {
			instance = new KutuphaneStore();
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
}
