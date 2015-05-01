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
	
		//+ "SELECT * WHERE {" + "?s library:tc \"" + tc + "\"^^xsd:long." 
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				
				+ "SELECT * WHERE {" + 
				 "?s foaf:name ?name." 
				+ "?s library:tc  ?tcc."
				+ "?s foaf:family_name ?soyad ."
				+ "?s library:password ?pass ."
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
      
       
		
      
		ResultSet ppersonResultSet = LibraryStore.getInstance().queryModelAsSelect(
				sparqlTxt);
		
		 System.out.println(sparqlTxt);
		 
		while (ppersonResultSet.hasNext()) {
			
			
			
			QuerySolution querySolution = (QuerySolution) ppersonResultSet.next();
			String name = querySolution.getLiteral("name").getString();
			Long tcValue = querySolution.getLiteral("tcc").getLong();
			String soyad = querySolution.getLiteral("soyad").getString();
			String password = querySolution.getLiteral("pass").getString();
			//String email = querySolution.getLiteral("mail").getString();
			//String friendName = querySolution.getLiteral("friendName").getString();
			//System.out.println(name + "    " + soyad+"    " +" sifre:"+ password + " email  :  "+ email+" friend name: "+friendName) ;
			System.out.println("     ad:"+ name  +" soyad:  "+ soyad +"  tcValue :  " + tcValue + " þifre   : "+ password);
		}
			
	
			
			
			
			
		
		 
		
				
		
		
		
		 
	 }
	
	
	
public static void kitapYaz (int isbn){
	
		 
		String sparqlTxt = "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
//				
				+ "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT * WHERE {"  
				+ " ?s library:title ?title."
				+ " ?s library:isbn ?b."
				+ "?s library:author ?author ."
				+"}";

		
		ResultSet kitapResultSet = LibraryStore.getInstance().queryModelAsSelect(
				sparqlTxt);
		
		 System.out.println(sparqlTxt);
		 System.out.println("!!!!!!!!!!!!kitap yazdýrýlýyor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		while (kitapResultSet.hasNext()) {
			System.out.println(" !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  ");
			QuerySolution querySolution = (QuerySolution) kitapResultSet.next();
			String title = querySolution.getLiteral("title").getString();
			String aa = querySolution.getLiteral("author").getString();
			int isbnn = querySolution.getLiteral("b").getInt();
			System.out.println("     kitap:"+ title+"    "+ isbnn + "     author:" + aa);
		}
		
		 
	 }



	public static void main(String[] args) {
		
//		KutuphaneStore.getInstance().printModel();
	   String tcc= "10856213714";
	  long t = 112551;
		System.out.println("sorgu baþý");
		
		
	
		yaz(Long.parseLong(tcc));
		
		System.out.println("parse edilmiþ long :    "+tcc);
		
		String is="123";
		kitapYaz(Integer.parseInt(is));
		
		
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
				+ "?s library:read ?read ."
				+ "?read library:title ?title ."
				+  "?s library:tc ?tc}"; 
		
		String txt2 = "PREFIX rdf:<" + RDF.getURI() + "> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
				+ "SELECT * WHERE {" 
				+ "?s library:read ?read ."
				+ "?read library:title ?title .}"
				; 
		
		ResultSet personResultSet = LibraryStore.getInstance().queryModelAsSelect(
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
			String title = querySolution.getLiteral("title").getString();
			System.out.println("*******************************");
            System.out.println(querySolution.getLiteral("tc"));
			//  System.out.println(" !!!!!!!!!  read :     "+querySolution.getLiteral("read"));
			
			System.out.println("*******************************");
			System.out.println(name + "    " + soyad+"    " + tcValue + " sifre:"+ password + " email  :  "+ email + "   arkadaþý: "+friend+"    :"+friendm + "  okuduðu kitap:  "+title) ;
			System.out.println("title :  "+title);
			System.out.println("kiþi bitti");
			
		}
		
		
		String sparqlKitap ="PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> "
				+ "PREFIX foaf:<" + FOAF.getURI() + "> " + "PREFIX library:<"
				+ OntologyConstants.ONTOLOGY_BASE_URI + "> "
					+ "SELECT * WHERE {" + "?s library:title ?title. "
					+ "?s library:isbn ?isbn ."
					+ "?s library:publicationDate ?publicationDate ."
					+ "?s library:bookCount ?bookCount ."
					+ "?s library:author ?author ."
					+  "}"; 
			
			
			ResultSet bookkResultSet = LibraryStore.getInstance().queryModelAsSelect(
					sparqlKitap);
			
			System.out.println("kitaplarýn hepsini sorgula^^^^^^^^^^^^^^^^^^");
			
			while (bookkResultSet.hasNext()) {
				QuerySolution querySolution = (QuerySolution) bookkResultSet.next();
				String title = querySolution.getLiteral("title").getString();
				int isbn = querySolution.getLiteral("isbn").getInt();
				String authorList = querySolution.getLiteral("author").getString();
				String publicationDate = querySolution.getLiteral("publicationDate").getString();
				int bookCount = querySolution.getLiteral("bookCount").getInt();
	            System.out.println(querySolution.getLiteral("isbn"));
				System.out.println(title + "    " + isbn+"    "  +  " Date  :  " + publicationDate+ "   adet: "+ bookCount +" author: " +authorList) ;
				
				
			}
	
		
		
		
	// ilk admini ekleme	
//		String tccc="11255146493";
//		Model adminModel = IndividualCreator
//				.createAdmin("Seha", "AKYOL",Long.parseLong(tccc),"12345", "seha@gmail.com", "sehoþ");
//		
//		KutuphaneStore.getInstance().addResourceModel(adminModel);
//		
		
		

	
	
	}
	
}
