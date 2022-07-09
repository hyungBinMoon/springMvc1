 package hello.springMvc.web.frontcontroller.v4.controller;

import java.util.Map;

import hello.springMvc.web.frontcontroller.ModelView;
import hello.springMvc.web.frontcontroller.v3.ControllerV3;
import hello.springMvc.web.frontcontroller.v4.ControllerV4;

public class MemberFormControllerV4 implements ControllerV4{
	
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		return "new-form";
	}

}
