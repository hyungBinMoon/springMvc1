package hello.springMvc.web.frontcontroller.v4.controller;

import java.util.List;
import java.util.Map;

import hello.springMvc.domain.member.Member;
import hello.springMvc.domain.member.MemberRepository;
import hello.springMvc.web.frontcontroller.ModelView;
import hello.springMvc.web.frontcontroller.v3.ControllerV3;
import hello.springMvc.web.frontcontroller.v4.ControllerV4;

public class MemberListControllerV4 implements ControllerV4{
	
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		List<Member> members = memberRepository.findAll();
		
		model.put("members", members);
		
		return "members";
	}
}
