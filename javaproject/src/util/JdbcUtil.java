package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	public static void close(ResultSet rs){
		if(rs!=null){
			try{
				 rs.close();
			}catch(SQLException e){}
		}
	}
	public static void close(Connection con){
		if(con!=null){
			try {
				if(!con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {}
		}
	}
	public static void close(PreparedStatement pstmt){
		if(pstmt!=null){
			try{
				 pstmt.close();
			}catch(SQLException e){}
		}
	}
	public static void close(Statement stmt){
		if(stmt!=null){
			try{
				 stmt.close();
			}catch(SQLException e){}
		}
	}
}
