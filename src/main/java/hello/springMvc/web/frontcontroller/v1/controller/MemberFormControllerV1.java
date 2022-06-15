package hello.springMvc.web.frontcontroller.v1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.springMvc.web.frontcontroller.v1.ControllerV1;

public class MemberFormControllerV1 implements ControllerV1{
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String viewPath = "/WEB-INF/views/new-form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); // control -> view  MOVE set
		dispatcher.forward(request, response); // Real move
	}

}
