package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Genre;
import Model.User;

public class UserDAO {
	private Connection conn;
	String textStatus = null;

	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public UserDAO(Connection con) {
		 conn = con;
	}
	
	public boolean checkUser(User user){
		ResultSet rs = null;
		try {
			if(conn != null){
				System.out.println("conn este");
			}
			PreparedStatement pstmt = conn.prepareStatement(
					"Select * from users where email like ?;");
			pstmt.setString(1, user.getEmail());
			rs = pstmt.executeQuery();
		} catch (SQLException e){}
		if(rs == null){
			return false;
		}else{
			return true;
		}		
	}
	
	//method to create a new user and add in database
	public User addUser(User newUser){
		try {
			System.out.println(newUser.getEmail() + "++++++++++++");
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO users (firstname, lastname, email, picture, pass, phone, dob, gender, isadmin) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, newUser.getFirstName());
			pstmt.setString(2, newUser.getLastName());
			pstmt.setString(3, newUser.getEmail());
			pstmt.setBytes(4, newUser.getPicture());
			pstmt.setString(5, newUser.getPassword());
			pstmt.setString(6, newUser.getPhone());
			pstmt.setDate(7, new java.sql.Date(newUser.getDob().getTime()));
			pstmt.setString(8, String.valueOf(newUser.getGender()));
			pstmt.setBoolean(9, newUser.isAdmin());
			pstmt.executeUpdate();
//			if( != 0) {
//				textStatus = "Account with succes was created";
//			}else{
//				throw new SQLException("Has detected error on create account");
//			}
//			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
//	            if (generatedKeys.next()) {
//	                newUser.setId(generatedKeys.getInt(1));
//	            }
//	            else {
//	                throw new SQLException("Creating user failed, no ID obtained.");
//	            }
//	        }
		} catch (SQLException e) {		}		
		return newUser;
	}
	
	//method to edit or update an existing user in database
	public String updateUser(User newUser){
		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"update users set firstname=?, lastname=?, email=?, picture=?, pass=?, phone=?, dob=?, gender=?, isadmin=? where id=?");
			pstmt.setString(1, newUser.getFirstName());
			pstmt.setString(2, newUser.getLastName());
			pstmt.setString(3, newUser.getEmail());			
			pstmt.setBytes(4, newUser.getPicture());
			pstmt.setString(5, newUser.getPassword());
			pstmt.setString(6, newUser.getPhone());
			pstmt.setDate(7, new java.sql.Date(newUser.getDob().getTime()));
			pstmt.setString(8, String.valueOf(newUser.getGender()));
			pstmt.setBoolean(9, newUser.isAdmin());
			pstmt.setInt(10, newUser.getId());
			if (pstmt.executeUpdate() != 0) {
				textStatus = "Account with succes was updated";
			}else{
				textStatus = "Has detected error on update account";
			}
		} catch (SQLException e) {
		}
		return textStatus;
	}
	
	//method to delete an existing user in database
	public String deleteUser(int id){
		try {
			PreparedStatement pstmt = conn.prepareStatement("delete from users where id=?;");
			pstmt.setInt(1, id);
			if (pstmt.executeUpdate() != 0) {
				textStatus = "Account with succes was deleted";
			}else{
				textStatus="Exception, account with this id not found in database ";
			}
		} catch (SQLException e) {
		}
		return textStatus;
	}
	
	//method to find an existing user in database by id
	public User findUserById(int id){
		User user = new User();
		try {

			PreparedStatement pstmt = conn.prepareStatement(
					"Select * from users where id = ?;");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setDob(rs.getDate("dob"));
				user.setGender(rs.getString("gender").toCharArray()[0]);
				System.out.println(rs.getBytes("picture").length + "from DAO");
				user.setPicture(rs.getBytes("picture"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAdmin(rs.getBoolean("isadmin"));
			}
		} catch (SQLException e) {

		}
		return user;
	}
	
	//get all users from database
	public List<User> getAllUsers() {
		return new ArrayList<User>();
	}
	public User findUserByEmail(String email) {
		User user = new User();
		try {

			PreparedStatement pstmt = conn.prepareStatement(
					"Select * from users where email like ?;");
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setDob(rs.getDate("dob"));
				user.setGender(rs.getString("gender").toCharArray()[0]);
				System.out.println(rs.getBytes("picture").length + "FROM DAO");
				user.setPicture(rs.getBytes("picture"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("pass"));				
				user.setPhone(rs.getString("phone"));
				user.setAdmin(rs.getBoolean("isadmin"));
			}
		} catch (SQLException e) {

		}
		return user;
	}
	public List<Genre> getAllGenre() throws SQLException{
		List<Genre> listGenre = new ArrayList<Genre>();
		PreparedStatement pstmt = conn.prepareStatement("select * from genre");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setId(rs.getInt("id"));
			genre.setName(rs.getString("name"));
			listGenre.add(genre);
		}
		return listGenre;
	}
}
