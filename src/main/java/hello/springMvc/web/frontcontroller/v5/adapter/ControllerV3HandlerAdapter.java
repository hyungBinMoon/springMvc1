package hello.springMvc.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.springMvc.web.frontcontroller.ModelView;
import hello.springMvc.web.frontcontroller.v3.ControllerV3;
import hello.springMvc.web.frontcontroller.v5.MyHandlerAdapter;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter{
	
	@Override
	public boolean support(Object handler) {
		return (handler instanceof ControllerV3);
	}
	
	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handle)
			throws ServletException, IOException {
		ControllerV3 controller = (ControllerV3) handle;
		
		Map<String, String> paramMap = createParamMap(request);
		ModelView mv = controller.process(paramMap);
		
		 return mv;
	}
	
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
