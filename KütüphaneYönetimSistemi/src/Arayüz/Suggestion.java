package Arayüz;

import com.hp.hpl.jena.rdf.model.Resource;

public class Suggestion {
	private String friendName;
	private String title;
	private String authorMatched;
	private Resource book;

	public Suggestion(Resource book, String title, String authorMatched,
			String friendName) {
		super();
		this.friendName = friendName;
		this.title = title;
		this.authorMatched = authorMatched;
		this.book = book;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorMatched() {
		return authorMatched;
	}

	public void setAuthorMatched(String authorMatched) {
		this.authorMatched = authorMatched;
	}

	public Resource getBook() {
		return book;
	}

	public void setBook(Resource book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Suggestion [friendName=" + friendName + ", title=" + title
				+ ", authorMatched=" + authorMatched + ", book=" + book + "]";
	}

}
