package hello.springMvc.web.frontcontroller.v5;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.springMvc.web.frontcontroller.ModelView;
import hello.springMvc.web.frontcontroller.v4.ControllerV4;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter{
	
	@Override
	public boolean support(Object handler) {
		return (handler instanceof ControllerV4);
	}
	
	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handle)
			throws ServletException, IOException {
		ControllerV4 controller = (ControllerV4) handle;
		HashMap<String, Object> model = new HashMap<>();
		
		Map<String, String> paramMap = createParamMap(request);
		String viewName = controller.process(paramMap, model);
		
		ModelView mv = new ModelView(viewName);
		mv.setModel(model);
		
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
