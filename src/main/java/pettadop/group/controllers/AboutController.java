package pettadop.group.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController {

	//http://localhost:8080/home
	//or http://localhost:8080/
	
	//Gifs used
	//Angry GIF by Jimmy the Bull
	
	@RequestMapping(value = {"/about"})
	
	public ModelAndView Show()
	{
		ModelAndView model = new ModelAndView();
		
		
		model.setViewName("about.jsp");
		
		
		return model;
	}

}