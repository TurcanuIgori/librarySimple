package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

	public Book addBook(Book newBook) throws SQLException{
			if(bookDAO.checkBook(newBook)){
				throw new SQLException("Book with this isbn already exists in database");
			}
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
		return bookDAO.addBook(newBook);
	}	
	public Book updateBook(Book book){
		try {
			int author_id =	authorDAO.checkAuthor(book.getAuthor());
			if(author_id != 0){
				Author author = book.getAuthor();
				author.setId(author_id);
				book.setAuthor(author);
			}else{
				authorDAO.addAuthor(book.getAuthor());
				author_id =	authorDAO.checkAuthor(book.getAuthor());
				Author author = book.getAuthor();
				author.setId(author_id);
				book.setAuthor(author);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return bookDAO.updateBook(book);
	}
	public List<Book> getBooksByCriteria(String criteria, int id){
		List<Book> listBooks = bookDAO.getBooksByCriteria(criteria, id);
		for(Book book : listBooks){
			book.setAuthor(authorDAO.getAuthorById(book.getAuthor()));
		}
		return listBooks;
	}
	public void deleteBookById(int id){
		bookDAO.deleteBookById(id);
	}
	public List<Book> getBooksByTitle(String title){
		List<Book> listBooks =  bookDAO.getBooksByTitle(title);
		for(Book book : listBooks){
			book.setAuthor(authorDAO.getAuthorById(book.getAuthor()));
		}
		return listBooks;
	}
	public Book getBookById(int id){
		Book book =  bookDAO.getBookById(id);
		System.out.println(book.getId());
		Author author = book.getAuthor();
		author = authorDAO.getAuthorById(author);
		book.setAuthor(author);
		return book;
	}
}
