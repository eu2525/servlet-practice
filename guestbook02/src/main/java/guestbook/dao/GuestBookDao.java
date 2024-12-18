package guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import guestbook.vo.GuestBookVo;

public class GuestBookDao {
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		
			String url = "jdbc:mariadb://192.168.0.10:3306/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}
	

	public List<GuestBookVo> findAll() {
		List<GuestBookVo> result = new ArrayList<>();
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select id, name,contents, date_format(reg_date, '%Y-%m-%d') , reg_date from guestbook order by reg_date desc" );
			ResultSet rs = pstmt.executeQuery();
		){
			while(rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String contents = rs.getString(3);
				String regDate = rs.getString(4);

				GuestBookVo vo = new GuestBookVo();
				vo.setId(id);
				vo.setName(name);
				vo.setContents(contents);
				vo.setRegDate(regDate);
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return result;		
	}


	public int insert(GuestBookVo vo) {
		int count = 0;		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into guestbook values(null, ?, ?, now(), ?)");
		) {

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());
			
			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		return count;
		
	}


	public int deleteByIdandPassword(Long id, String password) {
		
		int count =0;
		
		try (
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement("delete from guestbook where password = ? and id = ?");
		){	
			pstmt.setString(1, password);
			pstmt.setLong(2, id);
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		return count;
	}
}