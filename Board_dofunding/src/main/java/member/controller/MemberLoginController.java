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
			//�Խ��� ���ο����� �α����� �������� �Խ������� ������
			session.removeAttribute(destination);
			return getPage; //					
		}		
		
	}

	//memberLoginForm.jsp���� �α��� Ŭ��
	@RequestMapping(value=command,method=RequestMethod.POST)
	public String doAction(MemberBean bean, HttpServletResponse response, HttpSession session) {

		response.setContentType("text/html; charset=UTF-8");

		System.out.println(bean.getId());
		System.out.println(bean.getPassword());

		MemberBean sbean =  memberDao.searchId(bean.getId());
		System.out.println("sbean:" + sbean); //null or �ּ�
		PrintWriter pw=null;
		if(sbean == null) { // ���̵� �������

			try {
				pw = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.println("<script> alert('�ش� ���̵� �������� �ʽ��ϴ�');</script>");
			pw.flush();
			return getPage;
		}//if
		
		else { // ���̵� ����

			if(sbean.getPassword().equals(bean.getPassword())) { // �����ġ��
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
			else { // �����ġ ����
				try {
					pw=response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
				}
				pw.println("<script> alert('����� �߸��Ǿ����ϴ�');</script>");
				pw.flush();
			}
			return getPage;
		}
	}

}
