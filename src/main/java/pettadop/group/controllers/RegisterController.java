package pettadop.group.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import pettadop.group.Register;

@Controller
public class RegisterController 
{
    String uri = "http://localhost:5000/register";

	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("register.jsp", "register", new Register());
    }
	
	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
    public String submit(@Valid @ModelAttribute("register")Register register, 
      BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("firstName", register.getFirstName());
        model.addAttribute("lastName", register.getLastName());
		model.addAttribute("cell", register.getCell());
        model.addAttribute("email", register.getEmail());
		model.addAttribute("password", register.getPassword());
        //System.out.println(register.getEmail());
        //System.out.println(register.getPassword());

        String bodyString = "{\"firstName\":\"" + register.firstName + "\"," + 
                            "\"lastName\":\"" + register.lastName + "\"," +
                            "\"cell\":\"" + register.cell + "\"," +
                            "\"email\":\"" + register.email + "\"," +
                            "\"password\":\"" + register.password + "\"}";

        //System.out.println(bodyString);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(bodyString, headers);
        String message = restTemplate.postForObject(uri, entity, String.class);

        System.out.println(message);

        if (message.equals("A new user has been registered."))
            return "/registersuccess";
        else
            return "/registerfailed";
		
	}





}

