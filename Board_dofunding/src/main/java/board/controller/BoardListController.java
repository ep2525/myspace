package board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.model.BoardBean;
import board.model.BoardDao;
import utility.Paging;

@Controller
public class BoardListController {

	private final String command="/boardList.bv";
	private String getPage="boardList";

	@Autowired
	private BoardDao boardDao;

	//start.jsp get방식 
	@RequestMapping(command)
	public ModelAndView doAction(
			@RequestParam(value="pageNumber",required = false)String pageNumber,
			@RequestParam(value="pageSize",required = false)String pageSize,
			//검색은 선택
			@RequestParam(value="whatColumn",required = false)String whatColumn,
			@RequestParam(value="keyword",required = false)String keyword,
			HttpServletRequest request
			) {
		//선택 검색은 선택
		Map<String, String> map=new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		
		int totalCount= boardDao.getArticleCount(map);
		System.out.println("totalCount:"+totalCount);
		
			
		String url=request.getContextPath()+command;
		if(pageSize==null) {
			pageSize="10";
		}
		Paging pageInfo=new Paging(pageNumber,pageSize,totalCount,url,whatColumn,keyword);
		List<BoardBean> articleList=boardDao.getArticles(pageInfo, map);
	
		ModelAndView mav=new ModelAndView();
		mav.addObject("articleList", articleList);
		mav.addObject("totalCount", totalCount);
		mav.addObject("pageInfo", pageInfo);
		mav.setViewName(getPage);
		return mav;
	}
	

}
