package pettadop.group.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import pettadop.group.Login;
import pettadop.group.LoginCurrentUserSetter;
import pettadop.group.LoginTokenSetter;



@Controller
public class LoginController {
	final String uri = "http://localhost:5000/login";
	final String uriTwo = "http://localhost:5000/getinfo";
	
	@Autowired
	LoginCurrentUserSetter currentUser;
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("login.jsp", "login", new Login());
    }
	
	@RequestMapping(value = "/logged", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("login")Login login, 
      BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("email", login.getEmail());
        model.addAttribute("password", login.getPassword());
        
        System.out.println(login.getEmail());
        System.out.println(login.getPassword());
        
        String body = "{"+ "\"" +"email"+ "\"" +":"+"\"" + login.getEmail() + "\"" + "," + 
        		"\"" +"password"+ "\"" +":"+"\"" + login.getPassword() + "\"" +"}";
        
        System.out.println(body);
        
        RestTemplate restTemplate = new RestTemplate();
        

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        String token = restTemplate.postForObject(uri, entity, String.class);
        
        System.out.println(token);
        
        LoginTokenSetter.token = token;
        
        if (token.equals("supersecretkeytoken"))
        {
        	RestTemplate restTempTwo = new RestTemplate();
        	HttpHeaders headersTwo = new HttpHeaders();
            headersTwo.setContentType(MediaType.APPLICATION_JSON);
            headersTwo.set("token", LoginTokenSetter.token); 
            HttpEntity<String> entityTwo = new HttpEntity<String>(body, headersTwo);
            String current = restTempTwo.postForObject(uriTwo, entityTwo, String.class);
            
            System.out.println(current);
            
            String[] values = current.split(",");
            
            LoginCurrentUserSetter.cell = values[0];
            LoginCurrentUserSetter.firstName = values[1];
            LoginCurrentUserSetter.lastName = values[2];
            
            System.out.println(LoginCurrentUserSetter.firstName);
        	
        	return "/adoption";
        }
        else
        	return "/loginerror";
        
            
        
    }

    
    
    
}
