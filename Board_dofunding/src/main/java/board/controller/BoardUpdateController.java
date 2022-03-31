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
public class BoardUpdateController {

	private final String command="/updateForm.bv";
	private String getPage="updateForm";
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
		BoardBean detail=boardDao.oneSelect(article);
		System.out.println(detail.getSubject()+"controller");
		request.setAttribute("article", detail);
		request.setAttribute("pageNumber", pageNumber);
		return getPage;
	}	

	//boardList.jsp get방식 writeArticle.bv
	@RequestMapping(value=command,method = RequestMethod.POST)
	public String doAction(
			// @ModelAttribute("article") @Valid 스크립트로 빼냇음 
			@RequestParam(value="pageNumber") String pageNumber,
			BoardBean article,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			) {	
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw =null;	
		int cnt=boardDao.updateArticle(article);
		if(cnt>0){	//업데이트 성공
			return gotoPage+"?pageNumber="+pageNumber+"&num="+article.getNum();
		}
		else
		{
			try {
				pw=response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(cnt == -2) {	//비밀번호가 일치 않하면
				pw.println("<script> alert('작성글의 비밀번호가 일치하지 않습니다.');</script>");
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
