package test.mySql.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	
	@RequestMapping("/goWeb")
	public String goWeb() {
		return "index.html";
	}
}
