package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
public class BoardDeleteController {
	
	private final String command="/deleteForm.bv";
	private String getPage="deleteForm";
	private String gotoPage="redirect:/content.bv";
	
	@Autowired
	private BoardDao boardDao;

	//content.jsp get방식 updateForm.bv
	@RequestMapping(value=command,method = RequestMethod.GET)
	public String doAction(
			@RequestParam(value="pageNumber")String pageNumber,
			BoardBean article,
			HttpServletRequest request
			) {	
		request.setAttribute("article", article);
		request.setAttribute("pageNumber", pageNumber);
		return getPage;
	}	

	//boardList.jsp get방식 writeArticle.bv
	@RequestMapping(value=command,method = RequestMethod.POST)
	public String doAction(
			@RequestParam(value="pageNumber") String pageNumber,
			BoardBean article,
			HttpServletRequest request,
			HttpServletResponse response
			) {	
		String passwd=article.getPasswd();
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter pw =null;	
		
		if(passwd.equals("")){	//입력 누락
			try {
				pw=response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.println("<script> alert('비밀번호 입력 누락');</script>");
			pw.flush();
			request.setAttribute("article", article);
			request.setAttribute("pageNumber", pageNumber);
			return getPage;
		}
		else
		{
			int cnt=boardDao.deleteArticle(article,passwd);			
			try {
				pw=response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(cnt>0) {		//삭제성공
				return gotoPage+"?pageNumber="+pageNumber;
			}
			
			if(cnt == -2) {	//비밀번호가 일치 않하면
				pw.println("<script> alert('작성글의 비밀번호와 일치하지 않습니다.');</script>");
				pw.flush();
			}
			else {
				pw.println("<script> alert('글 수정이 실패했습니다');</script>");
				pw.flush();
			}	
			request.setAttribute("article", article);
			request.setAttribute("pageNumber", pageNumber);
			return getPage;
		}
			

	}	
	
	
}
