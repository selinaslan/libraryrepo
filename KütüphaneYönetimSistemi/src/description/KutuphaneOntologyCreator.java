package description;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import com.hp.hpl.jena.datatypes.RDFDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.sparql.modify.TemplateLib;
import com.hp.hpl.jena.sparql.syntax.Template;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.XSD;
public class KutuphaneOntologyCreator {
	public static void main(String[] args) throws IOException {
		createKutuphaneOntology();
	}
	public static void createKutuphaneOntology() throws IOException {
		// ontoloji tanýmý
		OntModel ontModel = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_MEM);
		Ontology kutuphaneOntology = ontModel
				.createOntology(OntologyConstants.ONTOLOGY_BASE_URI);
		// Ontology memberOntology =
		// ontModel.createOntology(OntologyConstants.ONTOLOGY_BASE_URI);
		// Ontology bookOntology =
		// ontModel.createOntology(OntologyConstants.ONTOLOGY_BASE_URI);
		// Ontology adminOntology =
		// ontModel.createOntology(OntologyConstants.ONTOLOGY_BASE_URI);
		// Ontology rentOntology =
		// ontModel.createOntology(OntologyConstants.ONTOLOGY_BASE_URI);
		// Ontology authorOntology =
		// ontModel.createOntology(OntologyConstants.ONTOLOGY_BASE_URI);
		// class tanýmlarý
		OntClass libraryCls = ontModel
				.createClass(OntologyConstants.LIBRARY_URI);
		OntClass bookCls = ontModel.createClass(OntologyConstants.BOOK_URI);
		OntClass memberCls = ontModel.createClass(OntologyConstants.MEMBER_URI);
		OntClass adminCls = ontModel.createClass(OntologyConstants.ADMIN_URI);
		OntClass rentalCls = ontModel.createClass(OntologyConstants.RENTAL_URI);
		OntClass authorCls = ontModel.createClass(OntologyConstants.AUTHOR_URI);
	
		
		
		// property tanýmlarý
		
		
		// Library Properties
		DatatypeProperty namePrp = ontModel
				.createDatatypeProperty(FOAF.name.getURI());
		namePrp.addDomain(libraryCls);

		ObjectProperty libraryBookPrp = ontModel
				.createObjectProperty(OntologyConstants.LIBBOOK_PROPERTY_URI);
		libraryBookPrp.addDomain(libraryCls);
		libraryBookPrp.addRange(bookCls);

		ObjectProperty libraryMemberPrp = ontModel
				.createObjectProperty(OntologyConstants.MEMBER_PROPERTY_URI);
		libraryMemberPrp.addDomain(libraryCls);
		libraryMemberPrp.addRange(memberCls);

		
	//TODO:	
		
		// Book Properties
		DatatypeProperty titlePrp = ontModel
				.createDatatypeProperty(OntologyConstants.TITLE_PROPERTY_URI);
		titlePrp.addDomain(bookCls);
		titlePrp.addRange(XSD.xstring);

		DatatypeProperty isbnPrp = ontModel
				.createDatatypeProperty(OntologyConstants.ISBN_PROPERTY_URI);
		isbnPrp.addDomain(bookCls);
		isbnPrp.addRange(XSD.xint);

		DatatypeProperty publisherPrp = ontModel
				.createDatatypeProperty(OntologyConstants.PUBLISHER_PROPERTY_URI);
		publisherPrp.addDomain(bookCls);
		 publisherPrp.addRange(XSD.xstring);

		DatatypeProperty publicationDatePrp = ontModel
				.createDatatypeProperty(OntologyConstants.PUBLICDATE_PROPERTY_URI);
		publicationDatePrp.addDomain(bookCls);
		publicationDatePrp.addRange(ResourceFactory
				.createResource(XSDDatatype.XSDdateTime.getURI()));
		// Calendar calendar = Calendar.getInstance();
		// calendar.set(year, month, date);

		DatatypeProperty pricePrp = ontModel
				.createDatatypeProperty(OntologyConstants.PRICE_PROPERTY_URI);
		pricePrp.addDomain(bookCls);
		 pricePrp.addRange(XSD.xint);

		DatatypeProperty formatPrp = ontModel
				.createDatatypeProperty(OntologyConstants.FORMAT_PROPERTY_URI);
		formatPrp.addDomain(bookCls);
		 formatPrp.addRange(XSD.xstring);

		DatatypeProperty editionPrp = ontModel
				.createDatatypeProperty(OntologyConstants.EDITION_PROPERTY_URI);
		editionPrp.addDomain(bookCls);
		 editionPrp.addRange(XSD.xint);

		ObjectProperty authorPrp = ontModel
				.createObjectProperty(OntologyConstants.AUTHOR_PROPERTY_URI);
		authorPrp.addDomain(bookCls);
		authorPrp.addRange(authorCls);
		
		
		
		
		
		// Member Properties
		namePrp.addDomain(memberCls);
		
		
		DatatypeProperty familyNamePrp = ontModel
				.createDatatypeProperty(FOAF.family_name.getURI());
		familyNamePrp.addDomain(memberCls);
		 
		
		DatatypeProperty tcPrp = ontModel
				.createDatatypeProperty(OntologyConstants.TC_PROPERTY_URI);
		tcPrp.addDomain(memberCls);
		 tcPrp.addRange(XSD.xlong);
		 
		 
	// TODO:	
		 
		 DatatypeProperty emailPrp = ontModel
					.createDatatypeProperty(FOAF.mbox.getURI());
		 emailPrp.addDomain(memberCls);
		 
		 DatatypeProperty passwordPrp = ontModel
					.createDatatypeProperty(OntologyConstants.PASSWORD_PROPERTY_URI);
			passwordPrp.addDomain(memberCls);
			passwordPrp.addRange(XSD.xstring);
		 
	

		ObjectProperty friendPrp = ontModel.createObjectProperty(FOAF.knows
				.getURI());
		friendPrp.addDomain(memberCls);
		friendPrp.addRange(memberCls);
		
	// TODO:	
		ObjectProperty bookListPrp = ontModel
				.createObjectProperty(OntologyConstants.ONTOLOGY_BASE_URI
						+ "read");
		bookListPrp.addDomain(memberCls);
		bookListPrp.addRange(bookCls);
		
		
		
		// Admin Properties
		
		namePrp.addDomain(adminCls);
		familyNamePrp.addDomain(adminCls);
		tcPrp.addDomain(adminCls);	
		passwordPrp.addDomain(adminCls);
		emailPrp.addDomain(adminCls);
		
		DatatypeProperty UserNamePrp = ontModel
				.createDatatypeProperty(OntologyConstants.USERNAME_PROPERTY_URI);
		UserNamePrp.addDomain(adminCls);
		UserNamePrp.addRange(XSD.xstring);
		
		
		

		// Rent Properties

		ObjectProperty renterPrp = ontModel
				.createObjectProperty(OntologyConstants.RENTER_ROPERTY_URI);
		renterPrp.addDomain(rentalCls);
		renterPrp.addRange(memberCls);

		titlePrp.addDomain(rentalCls);
		
//		ObjectProperty rentBookNamePrp = ontModel
//				.createObjectProperty(OntologyConstants.RENTBOOK_PROPERTY_URI);
//		rentBookNamePrp.addDomain(rentalCls);
//		rentBookNamePrp.addRange(bookCls);

		DatatypeProperty rentDatePrp = ontModel
				.createDatatypeProperty(OntologyConstants.RENTDATE_PROPERTY_URI);
		rentDatePrp.addDomain(rentalCls);
		rentDatePrp.addRange(ResourceFactory
		.createResource(XSDDatatype.XSDdateTime.getURI()));

		DatatypeProperty dueDatePrp = ontModel
				.createDatatypeProperty(OntologyConstants.DUEDATE_PROPERTY_URI);
		dueDatePrp.addDomain(rentalCls);
		 dueDatePrp.addRange(ResourceFactory
					.createResource(XSDDatatype.XSDdateTime.getURI()));
		
		
		//write
		ontModel.write(new FileWriter(new File("kutuphane.owl")));
	}
}