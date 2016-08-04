package lotto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import lotto.dto.UserDTO;
import util.JdbcUtil;


public class MemberDAO {
	Connection con;
//	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	//////////////////////////////db하려면 무조건 써야함 위의 4가지
	
	public Connection dbConnect() throws Exception{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user="scott";
		String password = "tiger";
		
		con=DriverManager.getConnection(url, user, password);
		System.out.println("DB connected......");
		
		return con;
	}
	
	public int insertMember(String id,String pwd,int admin){
		try{
			String sql ="INSERT INTO lotto_member VALUES(?,?,?)";
			//prepareStatement를 사용하려면 정해지지않은 값을 ?로 사용한다 
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ps.setInt(3, admin);
			
			int n = ps.executeUpdate();
			return n;
			
		}catch(SQLException e){
//			e.printStackTrace();
			return -1;//오류를 리턴할때 -1사용함
		}finally{
			JdbcUtil.close(ps);
			
		}
	}
	public String login(String id,String pwd){
		try{
			String sql="SELECT * FROM lotto_member where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			
			int n = ps.executeUpdate();
			if(n !=0) {
				String sql2="SELECT * FROM lotto_member where id=? and pwd=?";
				ps=con.prepareStatement(sql2);
				ps.setString(1, id);
				ps.setString(2, pwd);
				
				int k = ps.executeUpdate();
				
				if(k == 1) return id;
				else {
					System.out.println("비밀번호가 일치하지 않습니다.");
					return "nopass";
				}
			}else{
				System.out.println("존재하지 않는 ID입니다.");
				return "noid";
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
	}
	
	public int div_admin(String id){
		try{
			int admin = 0;
			String sql="SELECT * FROM lotto_member where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				admin = rs.getInt("admin");
			}
			System.out.println("admin의 값은"+admin);
			return admin;
			
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
	}

		
	
}
