package hello.springMvc.web.frontcontroller.v3;

import java.util.Map;

import hello.springMvc.web.frontcontroller.ModelView;

public interface ControllerV3 {
	
	ModelView process(Map<String, String> paramMap);
}
