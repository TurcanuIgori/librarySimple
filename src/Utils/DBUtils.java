package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	public Connection getConnect(){
		Connection conn=null;			
			try{
				Class.forName("org.postgresql.Driver");				
				conn=DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "postgre");
			}catch(Exception e){				
			}
			return conn;		
	}
	
	public void closeConnection(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
