package description;
import org.apache.jena.atlas.lib.Tuple;

import com.hp.hpl.jena.query.ParameterizedSparqlString;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.XSD;





 
 
 
public class Main {

	
	public static void yaz (long tc){
	
		
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT * WHERE {" + "?s library:tc \"" + tc
				+ "\"^^xsd:long." 
				+ "?s foaf:name ?name." 
				 + "}";
				
		
		
//		String sparqlTxt = "PREFIX rdf:<" + RDF.getURI() + "> "
//				+"PREFIX xsd:<" + XSD.getURI() +  "> "
//				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
//				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
//				+ "SELECT * WHERE {" + "?s rdf:type foaf:Person. "
//				+ "?s foaf:family_name ?soyad ."
//				+ "?s library:password ?password ."
//				+ "?s foaf:name ?name." 
//				+ "?s library:email ?mail." 
//				+  "?s library:tc  \"" +tc+  "\"^^xsd:long. "
//						+ "?s foaf:knows ?friend. "
//						+ "?friend foaf:name ?friendName }"   ;
//		
      
       
		
      
		ResultSet ppersonResultSet = KutuphaneStore.getInstance().queryModelAsSelect(
				sparqlTxt);
		
		 System.out.println(sparqlTxt);
		 
		while (ppersonResultSet.hasNext()) {
			
			
			
			QuerySolution querySolution = (QuerySolution) ppersonResultSet.next();
			String name = querySolution.getLiteral("name").getString();
//			int tcValue = querySolution.getLiteral("tc").getInt();
			//String soyad = querySolution.getLiteral("soyad").getString();
			//String password = querySolution.getLiteral("password").getString();
			//String email = querySolution.getLiteral("mail").getString();
			//String friendName = querySolution.getLiteral("friendName").getString();
			//System.out.println(name + "    " + soyad+"    " +" sifre:"+ password + " email  :  "+ email+" friend name: "+friendName) ;
			System.out.println("     ad:"+ name);
		}
			
	
			
			
			
			
		
		 
		
		/*
		
		ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
		queryStr.setNsPrefix("rdf", RDF.getURI());
		queryStr.setNsPrefix("xsd", XSD.getURI());
		queryStr.setNsPrefix("foaf", FOAF.getURI());
		queryStr.setNsPrefix("library", OntologyConstants.ONTOLOGY_BASE_URI);
		
		queryStr.append("?name ?soyad ?password ?tc");
		queryStr.append("{");
		queryStr.append("  ?s foaf:name ?name .");
		queryStr.append("  ?s foaf:family_name ?soyad .");
		queryStr.append("  ?s library:password ?password . ");
		queryStr.append(" ?tc  library:tc ");
		queryStr.append(tc);
		queryStr.append(".");
	
		queryStr.append("  ?s rdf:type foaf:Person .");
		
	
		

		Query q = queryStr.asQuery(); */
		
		
		
		
		
		
		 
	 }
	
	
	
	public static void main(String[] args) {
		
//		KutuphaneStore.getInstance().printModel();
	   String tcc= "10856213714";
	  long t = 112551;
		System.out.println("sorgu baþý");
	
		yaz(Long.parseLong(tcc));
		
		System.out.println("parse edilmiþ long :    "+tcc);
		System.out.println("##################################");
		System.out.println("sorgu sonucu");
		
		String sparqlTxt = "PREFIX rdf:<" + RDF.getURI() + "> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT * WHERE {" + "?s rdf:type foaf:Person. "
				+ "?s foaf:family_name ?soyad ."
				+ "?s library:password ?password ."
				+ "?s foaf:name ?name." 
				+ "?s library:email ?mail."
				+ "?s foaf:knows ?friend. "
				+ "?friend foaf:name ?friendName ."
				+ "?friend library:email ?friendMail ."
				+  "?s library:tc ?tc}"; 
		
		
		ResultSet personResultSet = KutuphaneStore.getInstance().queryModelAsSelect(
				sparqlTxt);
		
		System.out.println("ikinci sorgu hepsini listeliyor");
		while (personResultSet.hasNext()) {
			QuerySolution querySolution = (QuerySolution) personResultSet.next();
			String name = querySolution.getLiteral("name").getString();
			long tcValue = querySolution.getLiteral("tc").getLong();
			String soyad = querySolution.getLiteral("soyad").getString();
			String password = querySolution.getLiteral("password").getString();
			String email = querySolution.getLiteral("mail").getString();
			String friend = querySolution.getLiteral("friendName").getString();
			String friendm = querySolution.getLiteral("friendMail").getString();
//			System.out.println("*******************************");
System.out.println(querySolution.getLiteral("tc"));
//			System.out.println("*******************************");
			System.out.println(name + "    " + soyad+"    " + tcValue + " sifre:"+ password + " email  :  "+ email + "   arkadaþý: "+friend+"    :"+friendm) ;
			
			
		}
		
		
		
	// ilk admini ekleme	
//		String tccc="11255146493";
//		Model adminModel = IndividualCreator
//				.createAdmin("Seha", "AKYOL",Long.parseLong(tccc),"12345", "seha@gmail.com", "sehoþ");
//		
//		KutuphaneStore.getInstance().addResourceModel(adminModel);
//		
		
		

		String bookQuery = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT ?count WHERE {" 
				+ "?book foaf:name \"Lord of the Rings\"^^xsd:string." + "?book library:bookCount ?count}";
		//Model uyeModel = IndividualCreator.createUye(123, "Selin ASLAN");
//		Model bookModel = IndividualCreator
//				.createBook("1234", "Lord of the Rings", 15);
		//KutuphaneStore.getI0nstance().addResourceModel(uyeModel);
	//	KutuphaneStore.getInstance().addResourceModel(bookModel);
		// KutuphaneStore.getInstance().printModel();
		
		
		ResultSet bookResultSet = KutuphaneStore.getInstance().queryModelAsSelect(
				bookQuery);
		while (bookResultSet.hasNext()) {
			QuerySolution querySolution = (QuerySolution) bookResultSet.next();
			int count = querySolution.getLiteral("count").getInt();
			System.out.println("Book count: " + count);
		}
	}
}
