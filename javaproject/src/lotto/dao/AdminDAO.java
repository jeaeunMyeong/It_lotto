package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lotto.dto.UserDTO;
import project.Lotto_game;
import util.JdbcUtil;

public class AdminDAO {
	Connection con;
//	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	MemberDAO mdao = new MemberDAO();
	UserDAO udao = new UserDAO();
	
	public AdminDAO() {
		try {
			con = mdao.dbConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public UserDTO[] selectAll(){
		
		try{
			
			String sql="select * from lotto_user";
//			System.out.println(sql);
			ps=con.prepareStatement(sql);
//			System.out.println("sql문이 문제인가????"); 
			rs=ps.executeQuery();
//			System.out.println("rs까진 동작하겠지??");
			//ArayList 공통 부분을 메소드 만들자 
			UserDTO[] arr=udao.makeArray(rs);//ArrayList에 있는 객체의 개수를 전달-->고정 배열로 처리
			
			return arr;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
	}
	
	public int dropID(String id){
		try{
			
			String sql ="delete from lotto_user where id='"+id+"'";
			
			ps=con.prepareStatement(sql);
			
			int n=ps.executeUpdate();
			if(n>0){
				String sql1 ="delete from lotto_member where id='"+id+"'";
				
				ps=con.prepareStatement(sql1);
				n = ps.executeUpdate();
			}
			return n;
							
			
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
			
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
	}
}
