package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//DB에 접근(Access)해서 데이터 생성/조회/수정/삭제(CURD)
public class MemberDAO {
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String user = "scott";
	private static final String pwd = "12341234";
	
	Connection conn;
	Statement stmt;
	//조회하는 메서드
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		connDB();
		String query="select * from t_member";
		System.out.println("실행하고자 하는 쿼리:"+ query);
		
		//sql 문구를 만들 수 있게 한 연결 객체가 해당 sql 문구를 매개변수로 넣어서 실행
		
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				Date joinDate=rs.getDate("joinDate");
				System.out.println(id+pwd+name+email+joinDate);
				//가져온 위의 데이터를 어딘가에 저장해서 웹에 나오게 해야하는데
				//우리는 그 저장할 틀을 MemberVO로 만들어 놓음
				//우선 저장할 MemberVO객체 생성
				
				MemberVO vo = new MemberVO();
				vo.setId(id); vo.setPwd(pwd);
				vo.setName(name); vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("SQL 관련 예외");
		}
		return list;
	}
	
	//DB에 연결하는 메서드
	void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			//Driver : The basic service for managing a set of JDBC drivers. 
			conn = DriverManager.getConnection(url, user, pwd);
			stmt =conn.createStatement(); //DB에 SQL을 보내기 위한 객체를 만듦 (Creates a Statement object for sendingSQL statements to the database.)
			  
		} catch (Exception e) {
			System.out.println("오라클 드라이버 관련 예외 발생");
		}
	}
}
