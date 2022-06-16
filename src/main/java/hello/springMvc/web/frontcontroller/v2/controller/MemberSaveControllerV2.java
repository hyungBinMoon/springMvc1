package hello.springMvc.web.frontcontroller.v2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.springMvc.domain.member.Member;
import hello.springMvc.domain.member.MemberRepository;
import hello.springMvc.web.frontcontroller.MyView;
import hello.springMvc.web.frontcontroller.v2.ControllerV2;

public class MemberSaveControllerV2 implements ControllerV2{
	
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age")); 
		
		Member member = new Member(username, age);
		memberRepository.save(member);
		
		//model 에 보관한다 
		request.setAttribute("member", member);
		
		
		return new MyView("/WEB-INF/views/save-result.jsp");
	}
}
