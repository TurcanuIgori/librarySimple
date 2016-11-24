package Model;

import java.util.List;

public class GsonModel {
	private List<Book> listBooks;

	
	
	public GsonModel(List<Book> listBooks) {
		super();
		this.listBooks = listBooks;
	}

	public List<Book> getListBooks() {
		return listBooks;
	}

	public void setListBooks(List<Book> listBooks) {
		this.listBooks = listBooks;
	}
}
