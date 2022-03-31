package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberLoginController {

	private final String command = "loginForm.mem";
	private final String getPage = "memberLoginForm";
	// /WEB-INF/member/memberLoginForm.jsp

	@Autowired
	private MemberDao memberDao;

	// boardList.jsp
	@RequestMapping(value=command,method=RequestMethod.GET)
	public String doAction(HttpSession session) {
		String destination = (String)session.getAttribute("destination");
		if(destination==null) {
			return getPage;
		}
		else {
			//게시판 메인에서의 로그인은 목적지가 게시판으로 가야함
			session.removeAttribute(destination);
			return getPage; //					
		}		
		
	}

	//memberLoginForm.jsp에서 로그인 클릭
	@RequestMapping(value=command,method=RequestMethod.POST)
	public String doAction(MemberBean bean, HttpServletResponse response, HttpSession session) {

		response.setContentType("text/html; charset=UTF-8");

		System.out.println(bean.getId());
		System.out.println(bean.getPassword());

		MemberBean sbean =  memberDao.searchId(bean.getId());
		System.out.println("sbean:" + sbean); //null or 주소
		PrintWriter pw=null;
		if(sbean == null) { // 아이디가 존재안함

			try {
				pw = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.println("<script> alert('해당 아이디가 존재하지 않습니다');</script>");
			pw.flush();
			return getPage;
		}//if
		
		else { // 아이디가 존재

			if(sbean.getPassword().equals(bean.getPassword())) { // 비번일치함
				session.setAttribute("loginInfo", sbean);
				// 
				String destination = (String)session.getAttribute("destination");
				if(destination==null) {
					return "redirect:/boardList.bv";
				}
				else {
					return destination; //"redirect:/insert.prd"					
				}
			}
			else { // 비번일치 안함
				try {
					pw=response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
				}
				pw.println("<script> alert('비번이 잘못되었습니다');</script>");
				pw.flush();
			}
			return getPage;
		}
	}

}
