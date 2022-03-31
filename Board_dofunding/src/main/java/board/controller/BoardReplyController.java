package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class BoardReplyController {

	private final String command="/replyForm.bv";
	private String getPage="replyForm";
	private String gotoPage="redirect:/boardList.bv";
	
	@Autowired
	private BoardDao boardDao;

	private PrintWriter pw =null;
	
	//content.jsp get방식 replyForm.bv
	@RequestMapping(value=command,method = RequestMethod.GET)
	public String doAction(
			@RequestParam(value="pageNumber")String pageNumber,
			BoardBean article,
			HttpServletRequest request,
			HttpSession session) {
			request.setAttribute("article", article);
			request.setAttribute("pageNumber", pageNumber);
			return getPage;

	}
	
	//replyForm.jsp post방식 replyForm.bv
	@RequestMapping(value=command,method = RequestMethod.POST)
	public String doAction(
			@RequestParam(value="pageNumber")String pageNumber,
			BoardBean article,
			HttpServletRequest request,
			HttpServletResponse response) {	
		//writer/subject/email/content/password/Reg_date/Ip
		//ref/re_step/re_level
		response.setContentType("text/html; charset=UTF-8");
		article.setReg_date(new Timestamp(System.currentTimeMillis()));
		System.out.println(request.getRemoteAddr());
		article.setIp(request.getRemoteAddr());
		int cnt=boardDao.replyArticle(article);
		
		if(cnt>0){
			return gotoPage+"?pageNumber="+pageNumber;	
		}
		else{	
			try {
				pw=response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.println("<script> alert('답글 작성이 실패했습니다');</script>");
			pw.flush();
			request.setAttribute("article", article);
			request.setAttribute("pageNumber", pageNumber);			
			return getPage;
		}
		
	}
	
	
}
