package pettadop.group.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterFailedController {

	//http://localhost:8080/home
	
	//Gifs used
	//Angry GIF by Jimmy the Bull
	
	@RequestMapping("/registerfailed")
	public ModelAndView Show()
	{
		ModelAndView model = new ModelAndView();
		
		
		model.setViewName("registerfailed.jsp");
		
		
		return model;
	}

}