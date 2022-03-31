package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberRegisterController {
	private final String command = "register.mem";
	private final String getPage = "memberRegisterForm";
	private String gotoPage="redirect:/memberList.mem"; // MemberListController

	@Autowired
	private MemberDao memberDao;
	
	// memberLoginForm.jsp���� ȸ�������ϱ� Ŭ��
	@RequestMapping(value=command,method=RequestMethod.GET)
	public String doAction() {

		return getPage;
	}

	// memberRegisterForm.jsp �߰��ϱ� Ŭ��
	@RequestMapping(value=command,method=RequestMethod.POST)
	public ModelAndView doAction(
			@ModelAttribute("member") @Valid MemberBean member,
			BindingResult result,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}
		
		PrintWriter pw=null;
		int cnt = memberDao.insertMember(member);
		if(cnt>0) {
			mav.setViewName(gotoPage);			
		}
		else if(cnt==-2) {
			try {
				pw=response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.println("<script> alert('�̹� �����ϴ� ȸ���Դϴ�.');</script>");
			pw.flush();
			mav.setViewName(getPage);	
		}
		else {
			mav.setViewName(getPage);			
		}
		return mav;
		
	}
}