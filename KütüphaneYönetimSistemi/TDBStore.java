package semanticCartago.util.RDFStores;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URI;
import java.util.Iterator;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.shared.Command;
import com.hp.hpl.jena.tdb.TDB;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.util.FileManager;
//import com.mysql.jdbc.Statement;

public class TDBStore extends RDFStore {

	private Dataset dataset;

	public TDBStore(String rdfStorePath) {
		super(rdfStorePath);
		dataset = TDBFactory.createDataset(getTDBStorePath(rdfStorePath));
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		String[] owlFiles = getFilesOfExtension("owl", rdfStorePath);
		String[] rdfFiles = getFilesOfExtension("rdf", rdfStorePath);
		readFilesIntoDataset(rdfStorePath, owlFiles);
		readFilesIntoDataset(rdfStorePath, rdfFiles);

	}

	private String getTDBStorePath(String rdfStorePath) {
		return rdfStorePath + "/.store/";
	}

	private void readFilesIntoDataset(String rdfStorePath, String[] files) {
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i];
			if (!this.modelExists(fileName.substring(0,fileName.length() - 4)))
			{
				System.out.println("files[i]= " + fileName);
				Model model = ModelFactory.createDefaultModel();
				FileManager.get().readModel(model,
						getFilePath(rdfStorePath, fileName));
				Model namedModel = dataset.getNamedModel(fileName.substring(0,
						fileName.length() - 4));
				namedModel.add(model);
				datasetApplyChanges(namedModel);
			}
		}
	}

	private String getFilePath(String folder, String fileName) {
		if (!folder.endsWith("/")) {
			folder += "/";
		}
		return folder + fileName;
	}

	private String[] getFilesOfExtension(final String type, String dir) {
		File directory = new File(dir);

		FilenameFilter filefilter = new FilenameFilter() {

			public boolean accept(File dir, String name) {
				// if the file extension is .txt return true, else false
				return name.endsWith("." + type);
			}
		};

		String[] filenames = directory.list(filefilter);
		return filenames;
	}

	public boolean containsModel(String modelName) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		beginReadSession();
		boolean result = dataset.containsNamedModel(modelName);
		endDataset();
		return result;

	}
	
	

	public Model getNamedModel(String modelName) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		Model namedModel = dataset.getNamedModel(modelName);
		
		return namedModel;
	}

	public OntModel getNamedModelAsOntModel(String modelName) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		OntModel namedModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, dataset.getNamedModel(modelName));
		
		return namedModel;
	}

	public void addNewFile(String fileDir, String name) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		beginWriteSession();
		// Model model=ModelFactory.createDefaultModel();
		Model model = dataset.getNamedModel(name);
		FileManager.get().readModel(model, fileDir);
		addNamedModel(name, model);
		datasetApplyChanges(model);
	}

	private void addNamedModel(String name, Model model) {
		if (!this.dataset.containsNamedModel(name))
		{
			Model newModel=dataset.getNamedModel(name);
			newModel.add(model);
			StmtIterator stmtIterator=newModel.listStatements();
			while (stmtIterator.hasNext())
			{
				System.out.println(stmtIterator.nextStatement());
			}
			newModel.commit();
			//this.datasetApplyChanges(newModel);

			Iterator<String> modelList=this.listModels();
			while (modelList.hasNext())
				System.out.println(modelList.next());
		}
	}
	
	@Override
	public boolean modelExists(String name)
	{
		if (this.dataset.containsNamedModel(name))
			return true;
		else return false;
	}

	private void beginWriteSession() {
		// dataset.begin(ReadWrite.WRITE);
	}

	private void datasetApplyChanges(Model model) {
		model.commit();
		
		// dataset.commit();
		// dataset.end();
	}

	private void endDataset() {
		// dataset.end();
	}

	private void beginReadSession() {
		// dataset.begin(ReadWrite.READ);
	}

	public ResultSet runSelectQuery(String query, String modelName) {
		while (isInTransaction()) {
			System.out.println("in transaction");
		}
		
		
		
		Query q = QueryFactory.create(query);
		
		System.out.println("----------------------------------------Running select query: "+q.toString());
		QueryExecution qExec = QueryExecutionFactory.create(q,
				dataset.getNamedModel(modelName));
		// qExec.getContext().set(TDB.symUnionDefaultGraph, true) ;
		ResultSet results = qExec.execSelect();
		// qExec.close();
		
		endDataset();
		return results;
	}

	private boolean isInTransaction() {
		// dataset.isInTransaction();
		return false;
	}

	@Override
	public void addNewModel(Model model, String name) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		addNamedModel(name, model);
		if (this.dataset.containsNamedModel(name))
			System.out.println("Named model exists...");
		Iterator<String> modelList=this.listModels();
		while (modelList.hasNext())
			System.out.println(modelList.next());
		//datasetApplyChanges(model);
		//TDB.getContext().set(TDB.symUnionDefaultGraph, true);
	}
	
	@Override
	public void updateNamedModel(Model model, String name){
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		Iterator<String> modelList=this.listModels();
		while (modelList.hasNext())
			System.out.println(modelList.next());
		this.getNamedModel(name).removeAll();
		this.getNamedModel(name).add(model);
		
	}

	public Iterator<String> listModels() {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		Iterator<String> nameIterator = dataset.listNames();
		
		return nameIterator;
	}

	public Model runConstructQuery(String query) {
TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		Query q = QueryFactory.create(query);
		QueryExecution qExec = QueryExecutionFactory.create(q,
				dataset);
		// qExec.getContext().set(TDB.symUnionDefaultGraph, true) ;
		Model result = qExec.execConstruct();
		// qExec.close();
		
		return result;
	}

	public void listStatements() {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		Iterator<Node> iterator = dataset.asDatasetGraph().listGraphNodes();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
	}
		public void listStatements(String modelName) {
			TDB.getContext().set(TDB.symUnionDefaultGraph, true);
			
			Iterator<Statement> iterator = dataset.getNamedModel(modelName).listStatements();
			while (iterator.hasNext()) {
				System.out.println(modelName+" statements: "+
						iterator.next().toString());
			}
		

	}

	@Override
	public ResultSet runSelectQuery(String query) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		if (dataset == null)
			System.out.println("Dataset=null");
		Query q = QueryFactory.create(query);
		QueryExecution qExec = QueryExecutionFactory.create(q,
				dataset);
		// qExec.getContext().set(TDB.symUnionDefaultGraph, true) ;
		ResultSet results = qExec.execSelect();
		// qExec.close();
		
		return results;
		
	}

	public Resource createResource(String resourceName, String modelName) {

		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		Resource resource = dataset.getNamedModel(modelName).createResource(
				resourceName);
		datasetApplyChanges(dataset.getNamedModel(modelName));
		
		return resource;
	}

	public Resource createResource(URI resourceURI, String modelName) {

		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		Resource resource = dataset.getNamedModel(modelName).createResource(resourceURI.toString());
		datasetApplyChanges(dataset.getNamedModel(modelName));
		
		return resource;
	}

	@Override
	public Property createProperty(String propertyName, String modelName) {
			TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
			
			Property property = dataset.getNamedModel(modelName).createProperty(modelName, propertyName);
			datasetApplyChanges(dataset.getNamedModel(modelName));

		return property;
	}
	
	public Property createProperty(URI propertyURI, String modelName) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
	
		
		Property property = dataset.getNamedModel(modelName).createProperty(propertyURI.toString());
		datasetApplyChanges(dataset.getNamedModel(modelName));
		StmtIterator stmtIterator=dataset.getNamedModel(modelName).listStatements();
		while (stmtIterator.hasNext())
		{
			System.out.println("OMS Statement: "+stmtIterator.next().toString());
		}
	return property;
}


	@Override
	public Property getProperty(String propertyName, String modelName) {
		
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		
		Property property = dataset.getNamedModel(modelName).createProperty(propertyName);
		datasetApplyChanges(dataset.getNamedModel(modelName));
		StmtIterator stmtIterator=dataset.getNamedModel(modelName).listStatements();
		while (stmtIterator.hasNext())
		{
			System.out.println("OMS Statement: "+stmtIterator.next().toString());
		}
		return property;
	}

	@Override
	public Resource getResource(String resourceName, String modelName) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		Resource resource = dataset.getNamedModel(modelName).createResource(
				resourceName);
		datasetApplyChanges(dataset.getNamedModel(modelName));
		StmtIterator stmtIterator=dataset.getNamedModel(modelName).listStatements();
		while (stmtIterator.hasNext())
		{
			System.out.println("OMS Statement: "+stmtIterator.next().toString());
		}
		return resource;	}

	@Override
	public boolean runAskQuery(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Model runDescribeQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addStatementToModel(Statement s, String modelName) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		dataset.getNamedModel(modelName).add(s);
		datasetApplyChanges(dataset.getNamedModel(modelName));
	
			Iterator<Node> iterator = dataset.asDatasetGraph().listGraphNodes();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toString());
			}

	

	}

	@Override
	public void addPropertyToNode(Resource s, Property p, RDFNode n,
			String modelName) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		dataset.getNamedModel(modelName).add(s, p, n);
		datasetApplyChanges(dataset.getNamedModel(modelName));
		
			
			Iterator<Node> iterator = dataset.asDatasetGraph().listGraphNodes();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toString());
			}

	

	}

	@Override
	public void addLiteralToNode(Resource s, Property p, long l,
			String modelName) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		dataset.getNamedModel(modelName).addLiteral(s, p, l);
		datasetApplyChanges(dataset.getNamedModel(modelName));
		
			
			Iterator<Statement> iterator = dataset.getNamedModel(modelName).listStatements();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toString());
			}

	}

	@Override
	public void addStringPropertyToNode(Resource s, Property p, String string,
			String modelName) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		dataset.getNamedModel(modelName).add(s, p, string);
		datasetApplyChanges(dataset.getNamedModel(modelName));
		
			
			Iterator<Statement> iterator = dataset.getNamedModel(modelName).listStatements();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toString());
			}


	}

	@Override
	public boolean runAskQuery(String query, String modelName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Model runConstructQuery(String query, String modelName) {
		TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
		Query q = QueryFactory.create(query);
		QueryExecution qExec = QueryExecutionFactory.create(q,
				dataset);
		// qExec.getContext().set(TDB.symUnionDefaultGraph, true) ;
		Model result = qExec.execConstruct(dataset.getNamedModel(modelName));
		// qExec.close();
		
		return result;
		
	}

	@Override
	public Model runDescribeQuery(String query, String modelName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
