package Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.UserDAO;
import Model.User;
import Model.Genre;
import Utils.DBUtils;

public class UserService {
	UserDAO userDAO;
	DBUtils dbConn= new DBUtils();
	Connection conn = dbConn.getConnect();

	public UserService(){
		userDAO = new UserDAO(conn);
	}
	
	public String updateUser(User newUser) {
		// method to edit or update an user
		 return userDAO.updateUser(newUser);		
	}

	public boolean checkUser(User newUser) {
		// check if exist an user with this email		
		return userDAO.checkUser(newUser);
	}

	public User addUser(User newUser) throws SQLException {
		// method to add a new user
//		if(checkUser(newUser)){
			return userDAO.addUser(newUser);
//		}else{
//			throw new SQLException("Accunt with this email already exists in database");
//		}
	}
	public User findUserById(int id){
		return userDAO.findUserById(id);
	}
	public User findUserByEmail(String email){
		return userDAO.findUserByEmail(email);
	}
	public List<Genre> getAllGenre() throws SQLException{
		return userDAO.getAllGenre();
	}
}
