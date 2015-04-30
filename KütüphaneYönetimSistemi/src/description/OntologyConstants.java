package description;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class OntologyConstants {

	// TODO kütüphaneleri library
	public static final String ONTOLOGY_BASE_URI = "http://kutuphane.org/ontology#";
	public static final String RESOURCE_BASE_URI = "http://kutuphane.org/resource#";
	public static final String BOOK_URI = ONTOLOGY_BASE_URI + "Book";
	public static final String MEMBER_URI = ONTOLOGY_BASE_URI + "Member";
	public static final String LIBRARY_URI = ONTOLOGY_BASE_URI + "Library";
	public static final String ADMIN_URI = ONTOLOGY_BASE_URI + "Admin";
	public static final String RENTAL_URI = ONTOLOGY_BASE_URI + "Rental";
	public static final String AUTHOR_URI = ONTOLOGY_BASE_URI + "Author";
	public static final Resource LIBRARY_RSC = ResourceFactory
			.createResource(LIBRARY_URI);
	public static final Resource BOOK_RSC = ResourceFactory
			.createResource(BOOK_URI);
	public static final Resource MEMBER_RSC = ResourceFactory
			.createResource(MEMBER_URI);
	public static final Resource ADMIN_RSC = ResourceFactory
			.createResource(ADMIN_URI);
	public static final Resource RENT_RSC = ResourceFactory
			.createResource(RENTAL_URI);
	public static final Resource AUTHOR_RSC = ResourceFactory
			.createResource(AUTHOR_URI);
	public static final String TC_PROPERTY_URI = ONTOLOGY_BASE_URI + "tc";
	public static final Property TC_PROPERTY = ResourceFactory
			.createProperty(TC_PROPERTY_URI);

	// ***

	public static final String EMAIL_PROPERTY_URI = ONTOLOGY_BASE_URI + "email";
	public static final Property EMAIL_PROPERTY = ResourceFactory
			.createProperty(EMAIL_PROPERTY_URI);

	public static final String PASSWORD_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "password";
	public static final Property PASSWORD_PROPERTY = ResourceFactory
			.createProperty(PASSWORD_PROPERTY_URI);

	public static final String USERNAME_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "userName";
	public static final Property USERNAME_PROPERTY = ResourceFactory
			.createProperty(USERNAME_PROPERTY_URI);

	public static final String FAMILYNAME_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "familyName";
	public static final Property FAMILYNAME_PROPERTY = ResourceFactory
			.createProperty(FAMILYNAME_PROPERTY_URI);

	public static final String RENTER_ROPERTY_URI = ONTOLOGY_BASE_URI
			+ "renter";
	public static final Property RENTER_PROPERTY = ResourceFactory
			.createProperty(RENTER_ROPERTY_URI);

	public static final String RENTBOOK_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "rentedBook";
	public static final Property RENTBOOK_PROPERTY = ResourceFactory
			.createProperty(RENTBOOK_PROPERTY_URI);

	public static final String RENTDATE_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "rentDate";
	public static final Property RENTDATE_PROPERTY = ResourceFactory
			.createProperty(RENTDATE_PROPERTY_URI);

	public static final String DUEDATE_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "dueDate";
	public static final Property DUEDATE_PROPERTY = ResourceFactory
			.createProperty(DUEDATE_PROPERTY_URI);

	public static final String AUTHOR_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "author";
	public static final Property AUTHOR_PROPERTY = ResourceFactory
			.createProperty(AUTHOR_PROPERTY_URI);

	public static final String EDITION_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "edition";
	public static final Property EDITION_PROPERTY = ResourceFactory
			.createProperty(EDITION_PROPERTY_URI);

	public static final String FORMAT_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "format";
	public static final Property FORMAT_PROPERTY = ResourceFactory
			.createProperty(FORMAT_PROPERTY_URI);

	public static final String PRICE_PROPERTY_URI = ONTOLOGY_BASE_URI + "price";
	public static final Property PRICE_PROPERTY = ResourceFactory
			.createProperty(PRICE_PROPERTY_URI);

	public static final String PUBLICDATE_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "publicationDate";
	public static final Property PUBLICDATE_PROPERTY = ResourceFactory
			.createProperty(PUBLICDATE_PROPERTY_URI);

	public static final String PUBLISHER_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "publisher";
	public static final Property PUBLISHER_PROPERTY = ResourceFactory
			.createProperty(PUBLISHER_PROPERTY_URI);

	public static final String ISBN_PROPERTY_URI = ONTOLOGY_BASE_URI + "isbn";
	public static final Property ISBN_PROPERTY = ResourceFactory
			.createProperty(ISBN_PROPERTY_URI);

	public static final String TITLE_PROPERTY_URI = ONTOLOGY_BASE_URI + "title";
	public static final Property TITLE_PROPERTY = ResourceFactory
			.createProperty(TITLE_PROPERTY_URI);

	public static final String MEMBER_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "hasMember";
	public static final Property MEMBER_PROPERTY = ResourceFactory
			.createProperty(MEMBER_PROPERTY_URI);

	public static final String LIBBOOK_PROPERTY_URI = ONTOLOGY_BASE_URI
			+ "hasBook";
	public static final Property LIBBOOK_PROPERTY = ResourceFactory
			.createProperty(LIBBOOK_PROPERTY_URI);
	public static final String BOOK_COUNT_PRP_URI = ONTOLOGY_BASE_URI
	+ "bookCount";
	public static final Property BOOK_COUNT_PRP = ResourceFactory
	.createProperty(BOOK_COUNT_PRP_URI);

}