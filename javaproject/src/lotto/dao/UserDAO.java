package lotto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import lotto.dto.UserDTO;
import project.Lotto_game;
import project.User_main;
import util.JdbcUtil;


public class UserDAO {
	Connection con;
//	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	MemberDAO mdao = new MemberDAO();
	
	public UserDAO() {
		try {
			con = mdao.dbConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public UserDTO[] makeArray(ResultSet rs) throws SQLException{
		System.out.println("여기는 동작하나?");
		Vector<UserDTO> v = new Vector<UserDTO>();
		
		while(rs.next()){
			int no = rs.getInt("no");
			String id = rs.getString("id");
			int times = rs.getInt("times");
			int num1 = rs.getInt("num1");
			int num2 = rs.getInt("num2");
			int num3 = rs.getInt("num3");
			int num4 = rs.getInt("num4");
			int num5 = rs.getInt("num5");
			int num6 = rs.getInt("num6");
			String result = rs.getString("result");
			
			//한개의 레코드 DTO객체로 만들기
			UserDTO dto = new UserDTO(no,id,times,num1,num2,num3,num4,num5,num6,result);
			//객체만 저장하는 컬렉션 사용
			v.add(dto);
			
		}
		//벡터를 열로 담기
		UserDTO userArr[]=new UserDTO[v.size()];
		v.copyInto(userArr);//벡터에있는 데이터를 memArr에 전부 복사하라는 동작
		return userArr;
	}
	
	public UserDTO[] user_selectAll(String id){
	
		try{
			
			String sql="select * from lotto_user  where id='"+id+"'";
//			System.out.println(sql);
			ps=con.prepareStatement(sql);
//			System.out.println("sql문이 문제인가????"); 
			rs=ps.executeQuery();
//			System.out.println("rs까진 동작하겠지??");
			//ArayList 공통 부분을 메소드 만들자 
			UserDTO[] arr=makeArray(rs);//ArrayList에 있는 객체의 개수를 전달-->고정 배열로 처리
			
			return arr;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
	}
	public int add_game(String id, int times,int no){
		try{
			
			String sql ="INSERT INTO lotto_user VALUES(?,?,?,?,?,?,?,?,?,?)";
			ArrayList<Integer> lotto_game = Lotto_game.lotto_ran();
			
			ps=con.prepareStatement(sql);
			ps.setInt(1,no);
			ps.setString(2, id);
			ps.setInt(3, times);
			ps.setInt(4, lotto_game.get(0));
			ps.setInt(5, lotto_game.get(1));
			ps.setInt(6, lotto_game.get(2));
			ps.setInt(7, lotto_game.get(3));
			ps.setInt(8, lotto_game.get(4));
			ps.setInt(9, lotto_game.get(5));
			ps.setString(10, "발표전");
			
			int n=ps.executeUpdate();
			return n;
			
			
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
	} 

	public int count_no(String id){//반환타입 꼭 잇어야하므로 void -->int로 바꾸기
		try{
			System.out.println("쿼리문 시작전에러?");
			String sql = "select count(*) from lotto_user where id='"+id+"'";
			ps=con.prepareStatement(sql);
			System.out.println("쿼리문 시작후 ps에서 오류???");
			rs=ps.executeQuery();
			System.out.println("rs에서 오류 뜨나?");
			int k=0;
			while(rs.next()){
				k = rs.getInt("count(*)");
			}
			k=k+1;		
//			System.out.println(k);         //no값 확인을 위한 문장
			return k;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("에러~~~~~~~~~");
			return -1;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
		
	}
	//당첨확인 함수 작성중임 완성된거 아님///////////////////////////////////////////////
	
	public UserDTO[] result_check(String id,int times,int num1,int num2, int num3,int num4,int num5,int num6){
		
		
		try{
			
			String sql="select * from lotto_user where id='"+id+"' and times='"+times+"'";
			System.out.println(sql);
			ps=con.prepareStatement(sql);
			System.out.println("sql문이 문제인가????"); 
			rs=ps.executeQuery();
			System.out.println("rs까진 동작하겠지??");
			//ArayList 공통 부분을 메소드 만들자 
			UserDTO[] arr=resultArray(rs,num1,num2,num3,num4,num5,num6);//ArrayList에 있는 객체의 개수를 전달-->고정 배열로 처리
			
			return arr;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(ps);
		}
	}
	
	public UserDTO[] resultArray(ResultSet rs,int num1,int num2, int num3,int num4,int num5,int num6) throws SQLException{
		System.out.println("여기는 동작하나?");
		Vector<UserDTO> v = new Vector<UserDTO>();
		int[] check_num={num1,num2,num3,num4,num5,num6};
		int[] num=new int[6];
		int cnt = 0;
		while(rs.next()){
			int no = rs.getInt("no");
			String id = rs.getString("id");
			int times = rs.getInt("times");
			num[0] = rs.getInt("num1");
			num[1] = rs.getInt("num2");
			num[2] = rs.getInt("num3");
			num[3] = rs.getInt("num4");
			num[4] = rs.getInt("num5");
			num[5] = rs.getInt("num6");
			String result = rs.getString("result");
//			System.out.println(no);
//			System.out.println(id);
//			System.out.println(times);
//			System.out.println(num[0]);
//			System.out.println(num[1]);
//			System.out.println(num[2]);
//			System.out.println(num[3]);
//			System.out.println(num[4]);
//			System.out.println(num[5]);
//			System.out.println(result);

			for(int i=0;i<6;i++){
				for(int k=0;k<6;k++){
					if(num[i] == check_num[k])
						cnt++;
				}
			}
			if(cnt==3){
				System.out.println("5등"); 
				result = "5등";
			}else if(cnt==4){
				System.out.println("4등");
				result = "4등";
			}else if(cnt==5){
				System.out.println("3등");
				result = "3등";
			}else if(cnt==6){
				System.out.println("1등");
				result = "1등";
			}else{
				System.out.println("꽝!!!!!!!!");
				result = "꽝";
			}
			
			//한개의 레코드 DTO객체로 만들기
			UserDTO dto = new UserDTO(no,id,times,num[0],num[1],num[2],num[3],num[4],num[5],result);
			//객체만 저장하는 컬렉션 사용
			v.add(dto);
			cnt =0;
		}
		//벡터를 열로 담기
		UserDTO userArr[]=new UserDTO[v.size()];
		v.copyInto(userArr);//벡터에있는 데이터를 memArr에 전부 복사하라는 동작
		return userArr;
	}
	
	public void update_result(String id,int times,int num1,int num2, int num3,int num4,int num5,int num6){

		UserDTO arr[]=null;
		arr = result_check(id,times,num1,num2,num3,num4,num5,num6);
		String [][] data = new String[arr.length][2];
		for(int i=0;i<data.length;i++){
			data[i][0] = arr[i].getNo()+"";
			data[i][1] = arr[i].getResult()+"";
		}
		for(int i=0;i<arr.length;i++){
			String sql ="UPDATE lotto_user SET result=? where no=? ";
			
			try {
		
				ps=con.prepareStatement(sql);
				
				ps.setString(1, data[i][1]);
				ps.setInt(2, Integer.parseInt(data[i][0]));
				ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JdbcUtil.close(rs);
				JdbcUtil.close(ps);
			}
//			System.out.println("동작끝");  //정상동작하나 확인용
		}
	}
}
