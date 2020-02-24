package pettadop.group.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginErrorController 
{
	@RequestMapping("/loginerror")
	public ModelAndView Show()
	{
		ModelAndView model = new ModelAndView();
		
		
		model.setViewName("loginerror.jsp");
		
		
		return model;
	}

}
