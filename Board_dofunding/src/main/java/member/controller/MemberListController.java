package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import member.model.MemberBean;
import member.model.MemberDao;
import utility.Paging;

@Controller
public class MemberListController {

	private final String command="/memberList.mem";
	private String getPage="memberList";

	@Autowired
	private MemberDao memberDao;
	
	// MemberRegisterController ø‰√ª
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(
			@RequestParam(value="whatColumn", required=false) String whatColumn,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="pageNumber", required=false) String pageNumber,
			HttpServletRequest request) {

		Map<String, String> map=new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");

		int totalCount=memberDao.totalCount(map);
		String url=request.getContextPath()+command;
		Paging pageInfo=new Paging(pageNumber, null, totalCount, url, whatColumn, keyword);

		List<MemberBean> list=memberDao.memberList(pageInfo, map);

		ModelAndView mav=new ModelAndView();
		mav.addObject("list",list);
		mav.addObject("totalCount",totalCount);
		mav.addObject("pageInfo",pageInfo);
		mav.setViewName(getPage);
		return mav;
	}
}