package pettadop.group.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import pettadop.group.LoginCurrentUserSetter;
import pettadop.group.LoginTokenSetter;
import pettadop.group.models.Pet;
import pettadop.group.PetCurrentSetter;


@Controller
public class AdoptionController {
	
	boolean firstLoad = true;
	
	int AmountPets = 0;
	
	String PrevCeiling = "hidden";
	String NextCeiling = "visible";
	
	final String uri = "http://localhost:5000/pets";
	Pet[] pets;
	RestTemplate restTemplate = new RestTemplate();
	Pet[] objects;
	private int GlobalPetId = 0;
	//http://localhost:8080/adoption
	
	HttpHeaders headers = new HttpHeaders();
	
	ModelAndView model = new ModelAndView();	
	
	
	@RequestMapping("/adoption")
	public ModelAndView Show()
	{	
		headers.set("token", LoginTokenSetter.token); 
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		//ResponseEntity<Pets[]> responseEntity = restTemplate.getForEntity(uri, Pets[].class);
		
		ResponseEntity<Pet[]> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, Pet[].class);

		objects = responseEntity.getBody();
		//MediaType contentType = responseEntity.getHeaders().getContentType();
		//HttpStatus statusCode = responseEntity.getStatusCode();
		
		
		System.out.print(objects[0].petName);
		
		pets = objects;
		
		String initName = pets[GlobalPetId].petName;
		String initBreed = pets[GlobalPetId].breed;
		String initSex = pets[GlobalPetId].sex;
		String iniAge = Integer.toString(pets[GlobalPetId].age);
		String initStory = pets[GlobalPetId].story;
		String initLocation = pets[GlobalPetId].location;
		String initImagesrc = pets[GlobalPetId].imageUrlOne;
		String initImagesrcTwo = pets[GlobalPetId].imageUrlTwo;
		String initImagesrcThree = pets[GlobalPetId].imageUrlThree;
		boolean initAdopted = pets[GlobalPetId].adopted;
		
		PetCurrentSetter.Id =pets[GlobalPetId].id;
		
		String adoptedMessage = "";
		
		if (initAdopted == true)
		{
			adoptedMessage = "There are one or more people interested in this pet.";
		}
		else
		{
			adoptedMessage = "";
		}
	
		
		model.addObject("name", initName);
		model.addObject("breed", initBreed);
		model.addObject("sex", initSex);
		model.addObject("age", iniAge);
		model.addObject("story", initStory);
		model.addObject("location", initLocation);
		model.addObject("imagesrc", initImagesrc);
		model.addObject("imagesrcTwo", initImagesrcTwo);
		model.addObject("imagesrcThree", initImagesrcThree);
		model.addObject("adoptedMessage",adoptedMessage);
		
		model.addObject("hiddenPrev",PrevCeiling);
		model.addObject("hiddenNext",NextCeiling);
		
		model.addObject("currentUserName",LoginCurrentUserSetter.firstName);
		
		
		AmountPets = pets.length;
		

		model.setViewName("adoption.jsp");	
		
		return model;
	}
	
	public AdoptionController()
	{

	}
	
	@RequestMapping("/next")
	public String PostNext()
	{
		if (GlobalPetId < AmountPets - 1)
			GlobalPetId++;
		
		if (GlobalPetId == AmountPets -1)
			NextCeiling = "hidden";
		else
			NextCeiling = "visible";
		
		if (GlobalPetId == 0)
			PrevCeiling = "hidden";
		else
			PrevCeiling = "visible";

		System.out.print("Print");

		return "/adoption";
	}
	
	@RequestMapping("/previous")
	public String PostPrevious()
	{
		if (GlobalPetId > 0)
			GlobalPetId--;
		
		if (GlobalPetId == 0)
			PrevCeiling = "hidden";
		else
			PrevCeiling = "visible";
		
		if (GlobalPetId == AmountPets -1)
			NextCeiling = "hidden";
		else
			NextCeiling = "visible";


		System.out.print("Print");

		return "/adoption";
	}

}