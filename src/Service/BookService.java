package Service;

import java.sql.Connection;

import DAO.BookDAO;
import Model.Book;
import Utils.DBUtils;

public class BookService {
	BookDAO bookDAO;
	DBUtils dbConn= new DBUtils();
	Connection conn = dbConn.getConnect();
	
	public BookService() {
		bookDAO = new BookDAO(conn);
	}

	public Book addBook(Book newBook){
		return bookDAO.addBook(newBook);
	}
	public Book updateBook(Book book){
		return bookDAO.updateBook(book);
	}
}
