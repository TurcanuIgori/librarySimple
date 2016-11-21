package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Author;
import Model.Book;

public class AuthorDAO {
	private Connection conn;
	String textStatus = null;

	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public AuthorDAO(Connection con) {
		conn = con;
	}
	public int checkAuthor(Author author) throws SQLException{
		ResultSet rs = null;
			try {
				PreparedStatement pstmt = conn.prepareStatement(
						"Select * from author where firstname like ? and lastname like ?");			
				pstmt.setString(1, author.getFirstName());
				pstmt.setString(2, author.getLastName());
				rs = pstmt.executeQuery();
				if(rs != null){
					int i = rs.getInt("id");
					return i;
				}else{
					return 0;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(rs != null){
					int i = rs.getInt("id");
					return i;
				}else{
					return 0;
				}
			}
		
	}
	public Author addAuthor(Author newAuthor){
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO author (firstname, lastname) values (?, ?)");
			pstmt.setString(1, newAuthor.getFirstName());
			pstmt.setString(2, newAuthor.getLastName());
			pstmt.executeUpdate();
		} catch (SQLException e) {	}		
		return newAuthor;
	}
}
