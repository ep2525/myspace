package board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.model.BoardBean;
import board.model.BoardDao;

@Controller
public class BoardContentController {

	private final String command="/content.bv";
	private String getPage="content";

	@Autowired
	private BoardDao boardDao;

	//boardList.jsp get¹æ½Ä 
	@RequestMapping(value=command,method = RequestMethod.GET)
	public ModelAndView doAction(
			@RequestParam(value="pageNumber")String pageNumber,
			BoardBean article,
			HttpServletRequest request
			) {
		BoardBean detail =boardDao.getArticle(article);
		System.out.println(detail.getSubject()+":controller");
		ModelAndView mav=new ModelAndView();
		mav.addObject("article", detail);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName(getPage);
		return mav;
	}
		
	
	
}
