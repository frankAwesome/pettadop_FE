package pettadop.group.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import pettadop.group.LoginCurrentUserSetter;
import pettadop.group.PetCurrentSetter;



@Controller
public class CelebrateController {
	
	@Autowired
	PetCurrentSetter petCurrentSetter;
	
	@RequestMapping("/celebrate")
	public ModelAndView Show()
	{
		ModelAndView model = new ModelAndView();
		
		model.addObject("currentUserName",LoginCurrentUserSetter.firstName);
		model.addObject("currentUserNumber",LoginCurrentUserSetter.cell);
		model.setViewName("celebrate.jsp");
		
		RestTemplate restTemplate = new RestTemplate();
		
		String body = Long.toString(PetCurrentSetter.Id) + "," +LoginCurrentUserSetter.firstName + ","+LoginCurrentUserSetter.cell;
		String uri = "http://localhost:5000/petadopted";
        

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        restTemplate.postForObject(uri, entity, String.class);
		
		
		return model;
	}


}
