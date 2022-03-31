package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class BoardWriteController {

	private final String command="/writeArticle.bv";
	private String getPage="writeForm";
	private String gotoPage="redirect:/boardList.bv";
	
	@Autowired
	private BoardDao boardDao;

	//boardList.jsp get방식 writeArticle.bv
	@RequestMapping(value=command,method = RequestMethod.GET)
	public String doAction(HttpSession session) {	
		System.out.println("loginInfo:"+session.getAttribute("loginInfo")); // null
			
		if(session.getAttribute("loginInfo") == null) { // 로그인 안한 상태
			session.setAttribute("destination", "redirect:/writeArticle.bv");
			return "redirect:/loginForm.mem"; // MemberLoginController 
		}
		else {// 로그인 한 상태
			return getPage;// writeForm.jsp
		}	
	}
	
	//writeForm.jsp post방식 writeArticle.bv
	@RequestMapping(value=command,method = RequestMethod.POST)
	public String doAction(
			// @ModelAttribute("article") @Valid 스크립트로 빼냇음 
			BoardBean article,
			HttpServletRequest request,
			HttpServletResponse response) {
		//writer/subject/email/content/password/Reg_date/Ip
		response.setContentType("text/html; charset=UTF-8");			
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		System.out.println(request.getRemoteAddr());
		article.setIp(request.getRemoteAddr());
		int cnt=boardDao.insertArticle(article);
		PrintWriter pw =null;
		if(cnt>0){
			return gotoPage;	
		}
		else{	
			try {
				pw=response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.println("<script> alert('글 작성이 실패했습니다');</script>");
			pw.flush();
			return getPage;
		}
		
	}
	
	
	
	
}
