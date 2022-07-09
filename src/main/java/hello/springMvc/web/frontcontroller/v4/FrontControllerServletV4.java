package hello.springMvc.web.frontcontroller.v4;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.springMvc.web.frontcontroller.ModelView;
import hello.springMvc.web.frontcontroller.MyView;
import hello.springMvc.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.springMvc.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.springMvc.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.springMvc.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.springMvc.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.springMvc.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.springMvc.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.springMvc.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.springMvc.web.frontcontroller.v4.controller.MemberSaveControllerV4;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*") // *v1 하위 모든 패턴 다 들어올 수 있음 
public class FrontControllerServletV4 extends HttpServlet{
	
	private Map<String, ControllerV4> controllerMap = new HashMap<>();
	
	public FrontControllerServletV4() {
		controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
		controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
		controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service!");
		
		String requestURI = request.getRequestURI();
		ControllerV4 controller = controllerMap.get(requestURI);
		if(controller == null ) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		Map<String, String> paramMap = createParamMap(request);
		Map<String, Object> model = new HashMap<>();
		String viewName  = controller.process(paramMap, model);
		
		MyView view = viewResolver(viewName); 
		
		view.render(model, request, response);
	}
	
	private MyView viewResolver(String viewName) {return new MyView("/WEB-INF/views/"+viewName+".jsp");};
	
	
	private Map<String, String> createParamMap(HttpServletRequest request){
		Map<String, String> paramMap = new HashMap<>();
		
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
			String name = (String)params.nextElement();
			paramMap.put(name, request.getParameter(name));
		}
		return paramMap;
	}
}
