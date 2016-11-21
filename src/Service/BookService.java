package Service;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.AuthorDAO;
import DAO.BookDAO;
import Model.Author;
import Model.Book;
import Utils.DBUtils;

public class BookService {
	
	BookDAO bookDAO;
	AuthorDAO authorDAO;
	DBUtils dbConn= new DBUtils();
	Connection conn = dbConn.getConnect();

	public BookService() {
		bookDAO = new BookDAO(conn);
		authorDAO = new AuthorDAO(conn);
	}

	public Book addBook(Book newBook){
		try {
		int author_id =	authorDAO.checkAuthor(newBook.getAuthor());
		if(author_id != 0){
			Author author = newBook.getAuthor();
			author.setId(author_id);
			newBook.setAuthor(author);
		}else{
			authorDAO.addAuthor(newBook.getAuthor());
			author_id =	authorDAO.checkAuthor(newBook.getAuthor());
			Author author = newBook.getAuthor();
			author.setId(author_id);
			newBook.setAuthor(author);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookDAO.addBook(newBook);
	}	
	public Book updateBook(Book book){
		return bookDAO.updateBook(book);
	}
}
