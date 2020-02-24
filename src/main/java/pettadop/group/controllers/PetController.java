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

import pettadop.group.models.Pet;


@Controller
public class PetController {

    String uri = "http://localhost:5000/petregistration";

    @RequestMapping(value="/petregistration", method=RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("pet.jsp", "pet", new Pet());
    }

    @RequestMapping(value = "/newpet", method = RequestMethod.GET)
    public String submit(@Valid @ModelAttribute("register")Pet pet, 
      BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("petName", pet.getPetName());
        model.addAttribute("breed", pet.getBreed());
		model.addAttribute("sex", pet.getSex());
        model.addAttribute("age", pet.getAge());
        model.addAttribute("story", pet.getStory());
        model.addAttribute("imageUrlOne", pet.getImageUrlOne());
        model.addAttribute("location", pet.getLocation());
        //System.out.println(register.getEmail());
        //System.out.println(register.getPassword());

        String bodyString = "{\"petName\":\"" + pet.petName + "\"," + 
                            "\"breed\":\"" + pet.breed + "\"," +
                            "\"sex\":\"" + pet.sex + "\"," +
                            "\"age\":\"" + pet.age + "\"," +
                            "\"story\":\"" + pet.story + "\"," +
                            "\"imageUrlOne\":\"" + pet.imageUrlOne + "\"," +
                            "\"location\":\"" + pet.location + "\"}";

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