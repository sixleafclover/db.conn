package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//컨트롤러 역할을 함, 컨트롤러 역할을 하기 위해 MemberServlet안에 연결객체(MemberDAO)가 있어야함
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberServlet() {
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
	    PrintWriter out=response.getWriter();	
		
	    MemberDAO dao = new MemberDAO();
	    
	    List<MemberVO> memberList = dao.listMembers();
	    
	    out.print("<html><body>");
	    out.print("<table  border=1><tr align='center' bgcolor='lightgreen'>");
	    out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td></tr>");
	    
	    for (int i=0; i<memberList.size();i++){
	    	MemberVO memberVO = memberList.get(i);
	    	String id=memberVO.getId();
			String pwd = memberVO.getPwd();
			String name=memberVO.getName();
			String email=memberVO.getEmail();
			Date joinDate = memberVO.getJoinDate();
			out.print("<tr><td>"+id+"</td><td>"+pwd+"</td><td>"+name+"</td><td>"+email+"</td><td>"+joinDate+"</td></tr>");	
	    }
	    out.print("</table></body></html>");
	    
	}

}
