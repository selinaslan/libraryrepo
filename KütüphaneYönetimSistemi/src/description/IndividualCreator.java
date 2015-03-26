package description;
import java.util.UUID;

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
		uyeRsc.addLiteral(OntologyConstants.TC_PROPERTY,new Long(tc));
		uyeRsc.addLiteral(OntologyConstants.EMAIL_PROPERTY, email);

		// TODO: book'ta kullanýlacak
		// uyeRsc.addProperty(RDF.type, OntologyConstants.BOOK_RSC);
		return resourceModel;
	}

	public static Model createBook(String isbn, String bookName, int bookCount) {
		Model resourceModel = ModelFactory.createDefaultModel();
		Resource bookRsc = resourceModel
				.createResource(OntologyConstants.RESOURCE_BASE_URI + isbn);
		bookRsc.addProperty(RDF.type, OntologyConstants.BOOK_RSC);
		bookRsc.addLiteral(FOAF.name, bookName);
		Property bookCountPrp = ResourceFactory
				.createProperty(OntologyConstants.ONTOLOGY_BASE_URI
						+ "bookCount");
		bookRsc.addLiteral(bookCountPrp, bookCount);
		bookRsc.getProperty(bookCountPrp).changeLiteralObject((bookCount-1));

		return resourceModel;
	}

}
