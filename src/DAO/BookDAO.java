package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Book;
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

}
