package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Model.Author;
import Model.Book;
import Model.Genre;
import Model.User;

public class BookDAO {
	private Connection conn;
	String textStatus = null;

	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public BookDAO(Connection con) {
		conn = con;
	}
	public boolean checkBook(Book book){		
		ResultSet rs = null;
		try {		
			PreparedStatement pstmt = conn.prepareStatement(
					"Select * from book where isbn like ?;");
			pstmt.setString(1, book.getIsbn());
			rs = pstmt.executeQuery();
		} catch (SQLException e){}
		if(rs == null){
			return false;
		}else{
			return true;
		}		
	}
	public Book addBook(Book newBook){
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO book (name, author_id, picture, pages, publisher, year, isbn, description, genre_id, filepdf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, newBook.getName());
			pstmt.setInt(2, newBook.getAuthor().getId());
			pstmt.setBytes(3, newBook.getPicture());
			pstmt.setInt(4, newBook.getPages());
			pstmt.setString(5, newBook.getPublisher());
			pstmt.setInt(6, newBook.getYear());
			pstmt.setString(7, newBook.getIsbn());
			pstmt.setString(8, newBook.getDescription());
			pstmt.setBytes(10, newBook.getFile());
			pstmt.setInt(9, newBook.getGenre().getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {	}		
		return newBook;
	}
	public Book updateBook(Book newBook){
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"Update book set name=?, author_id=?, picture=?, pages=?, publisher=?, year=?, isbn=?, description=?, genre_id=?, filepdf=? where id=?");
			pstmt.setString(1, newBook.getName());
			pstmt.setInt(2, newBook.getAuthor().getId());
			pstmt.setBytes(3, newBook.getPicture());
			pstmt.setInt(4, newBook.getPages());
			pstmt.setString(5, newBook.getPublisher());
			pstmt.setInt(6, newBook.getYear());
			pstmt.setString(7, newBook.getIsbn());
			pstmt.setString(8, newBook.getDescription()); 
			pstmt.setBytes(10, newBook.getFile());
			pstmt.setInt(9, newBook.getGenre().getId());
			pstmt.setInt(11, newBook.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {	}		
		return newBook;
	}
	public void deleteBookById(int id){
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"delete from book where id=?");			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {	}		
	}
	public List<Book> getBooksByCriteria(String criteria, int id){
		List<Book> listBooks = new LinkedList();
		try {
			PreparedStatement pstmt =null;
			if(criteria.equals("genre_id")){
				pstmt = conn.prepareStatement(
						"Select * from book where genre_id=?");
			}else if(criteria.equals("atuhor_id")){
				pstmt = conn.prepareStatement(
						"Select * from book where author_id=?");
			}			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				Author author = new Author();
				Genre genre = new Genre();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				author.setId(rs.getInt("author_id"));
				book.setAuthor(author);
				book.setPicture(rs.getBytes("picture"));
				book.setPages(rs.getInt("pages"));
				book.setPublisher(rs.getString("publisher"));
				book.setYear(rs.getInt("year"));
				book.setIsbn(rs.getString("isbn"));
				book.setDescription(rs.getString("description"));
				genre.setId(rs.getInt("genre_id"));
				book.setGenre(genre);
				book.setFile(rs.getBytes("filepdf"));
				listBooks.add(book);
			}
		}catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listBooks;
	}
	public List<Book> getBooksByTitle(String title){
		List<Book> listBooks = new LinkedList();
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"Select * from book where title=?");
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				Author author = new Author();
				Genre genre = new Genre();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				author.setId(rs.getInt("author_id"));
				book.setAuthor(author);
				book.setPicture(rs.getBytes("picture"));
				book.setPages(rs.getInt("pages"));
				book.setPublisher(rs.getString("publisher"));
				book.setYear(rs.getInt("year"));
				book.setIsbn(rs.getString("isbn"));
				book.setDescription(rs.getString("description"));
				genre.setId(rs.getInt("genre_id"));
				book.setGenre(genre);
				book.setFile(rs.getBytes("filepdf"));
				listBooks.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listBooks;
	}
	public Book getBookById(int id){
		Book book = new Book();
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"Select * from book where id=?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				Author author = new Author();
				Genre genre = new Genre();
				book.setId(rs.getInt("id"));
				book.setName(rs.getString("name"));
				author.setId(rs.getInt("author_id"));
				book.setAuthor(author);
				book.setPicture(rs.getBytes("picture"));
				book.setPages(rs.getInt("pages"));
				book.setPublisher(rs.getString("publisher"));
				book.setYear(rs.getInt("year"));
				book.setIsbn(rs.getString("isbn"));
				book.setDescription(rs.getString("description"));
				genre.setId(rs.getInt("genre_id"));
				book.setGenre(genre);
				book.setFile(rs.getBytes("filepdf"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
}
