package description;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import ObjectClass.author;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;

public class IndividualCreator {
	
	
	public static Model createUye(String ad , String soyad , long tc , String sifre , String email) {
		Model resourceModel = ModelFactory.createDefaultModel();
		Resource uyeRsc = resourceModel
				.createResource(OntologyConstants.RESOURCE_BASE_URI + tc);
		uyeRsc.addProperty(RDF.type, FOAF.Person);
		uyeRsc.addLiteral(FOAF.name, ad);
		uyeRsc.addLiteral(FOAF.family_name, soyad);
		uyeRsc.addLiteral(OntologyConstants.PASSWORD_PROPERTY, sifre);
		uyeRsc.addLiteral(OntologyConstants.TC_PROPERTY, new Long(tc));
		uyeRsc.addLiteral(OntologyConstants.EMAIL_PROPERTY, email);

		// TODO: book'ta kullanýlacak
		// uyeRsc.addProperty(RDF.type, OntologyConstants.BOOK_RSC);
		return resourceModel;
	}

	
	public static Model createAdmin(String ad , String soyad , long tc , String sifre , String email , String userName) {
		Model resourceModel = ModelFactory.createDefaultModel();
		Resource adminRsc = resourceModel
				.createResource(OntologyConstants.RESOURCE_BASE_URI + tc);
		adminRsc.addProperty(RDF.type, FOAF.Person);
		adminRsc.addLiteral(FOAF.name, ad);
		adminRsc.addLiteral(FOAF.family_name, soyad);
		adminRsc.addLiteral(OntologyConstants.PASSWORD_PROPERTY, sifre);
		adminRsc.addLiteral(OntologyConstants.TC_PROPERTY, new Long(tc));
		adminRsc.addLiteral(OntologyConstants.EMAIL_PROPERTY, email);
		adminRsc.addLiteral(OntologyConstants.USERNAME_PROPERTY, userName);
		// TODO: book'ta kullanýlacak
		adminRsc.addProperty(RDF.type, OntologyConstants.ADMIN_RSC);
		return resourceModel;
	}
	
	
	
//	public static Model createBook(String isbn, String bookName, int bookCount) {
//		Model resourceModel = ModelFactory.createDefaultModel();
//		Resource bookRsc = resourceModel
//				.createResource(OntologyConstants.RESOURCE_BASE_URI + isbn);
//		bookRsc.addProperty(RDF.type, OntologyConstants.BOOK_RSC);
//		bookRsc.addLiteral(FOAF.name, bookName);
//		Property bookCountPrp = ResourceFactory
//				.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
//						+ "bookCount");
//		bookRsc.addLiteral(bookCountPrp, bookCount);
//		bookRsc.getProperty(bookCountPrp).changeLiteralObject((bookCount-1));
//
//		return resourceModel;
//	}


	public static Model createBook(String title, String publisher,  //ArrayList<author> authorList
			String format, int isbn, int price, int edition,
			String publicationDate,int bookCount)
	
	{
		Model resourceModel = ModelFactory.createDefaultModel();
		Resource bookRsc = resourceModel
				.createResource(OntologyConstants.RESOURCE_BASE_URI + isbn);
		
		bookRsc.addLiteral(OntologyConstants.TITLE_PROPERTY, title);
		bookRsc.addLiteral(OntologyConstants.PUBLISHER_PROPERTY, publisher);
		bookRsc.addLiteral(OntologyConstants.FORMAT_PROPERTY, format);
		bookRsc.addLiteral(OntologyConstants.ISBN_PROPERTY, isbn);
		bookRsc.addLiteral(OntologyConstants.PRICE_PROPERTY, price);
		bookRsc.addLiteral(OntologyConstants.EDITION_PROPERTY, edition);
		bookRsc.addLiteral(OntologyConstants.PUBLICDATE_PROPERTY, publicationDate);
		Property bookCountPrp = ResourceFactory
				.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
						+ "bookCount");
				bookRsc.addLiteral(bookCountPrp, bookCount);
				
		//bookRsc.addLiteral(OntologyConstants.AUTHOR_PROPERTY, authorList);
//TODO: yazar listesi ekleme	ve count ekleme
				
				
//		for(int i =0;i<authorList.size();i++)
//			
//		{     	
//		bookRsc.addLiteral(OntologyConstants.AUTHOR_PROPERTY, authorList.get(i));
//		}	
	
		return resourceModel;
	}

}
