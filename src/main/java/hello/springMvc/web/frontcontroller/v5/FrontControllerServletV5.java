package hello.springMvc.web.frontcontroller.v5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.springMvc.web.frontcontroller.ModelView;
import hello.springMvc.web.frontcontroller.MyView;
import hello.springMvc.web.frontcontroller.v3.ControllerV3;
import hello.springMvc.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.springMvc.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.springMvc.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.springMvc.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.springMvc.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.springMvc.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.springMvc.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet{
	
	private final Map<String, Object> handlerMappingMap = new HashMap<>();
	private List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
	
	public FrontControllerServletV5() {
		initHandlerMappingMap();
		initHandlerAdapters();
		
	}
	
	public void initHandlerMappingMap() {
		handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
		handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
		handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
		handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
	}
	
	public void initHandlerAdapters() {
		handlerAdapters.add(new ControllerV3HandlerAdapter());
		handlerAdapters.add(new ControllerV4HandlerAdapter());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object handler =  getHandler(request);
		 
		if(handler == null ) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		MyHandlerAdapter adapter = getHandlerAdapter(handler);
		ModelView mv = adapter.handle(request, response, handler);
		
		String viewName = mv.getViewName();
		 
		MyView view = viewResolver(viewName);
		
		
		view.render(mv.getModel(),request, response);
	}
	
	public Object getHandler(HttpServletRequest req) {
		String requestURI = req.getRequestURI();
		return handlerMappingMap.get(requestURI);
	}
	
	public MyHandlerAdapter getHandlerAdapter(Object handler) {
		for(MyHandlerAdapter adapter : handlerAdapters) {
			if(adapter.support(handler)) {
				return adapter; 
			}
		}
		throw new IllegalArgumentException("not found Handler!" + handler);
	}
	
	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}
}
