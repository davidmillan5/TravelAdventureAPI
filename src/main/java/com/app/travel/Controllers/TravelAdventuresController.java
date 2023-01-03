package com.app.travel.Controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.travel.Model.Adventure;
import com.app.travel.Repository.AdventureRepository;

@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {
	
	private final AdventureRepository adventureRepository;

	 public TravelAdventuresController(AdventureRepository adventureRepo) {
		    this.adventureRepository = adventureRepo;
		  }

	// Add controller methods below:
	 
	@GetMapping()
	public Iterable<Adventure> getAllAdventures(){
	return this.adventureRepository.findAll();
	}
	
	
	@GetMapping("/bycountry/{country}")
	public List<Adventure> getByCountry(@PathVariable("country") String country){
		return this.adventureRepository.findByCountry(country);
	}
	
	
	@GetMapping("/bystate")
	public List<Adventure> getByState(@RequestParam(name="state")String state){
		return this.adventureRepository.findByState(state);
	}
	
	@PostMapping()
	public Adventure createNewAdventure(@RequestBody Adventure adventure) {
		Adventure newAdventure = this.adventureRepository.save(adventure);
		return newAdventure;
	}

	
	@PutMapping("/{id}")
	public Adventure updateAdventure(@PathVariable("id")Integer id, @RequestBody Adventure adventure) {
		Optional<Adventure> adventureToUpdateOptional = this.adventureRepository.findById(id);
		
		if(!adventureToUpdateOptional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		
		Adventure adventureToUpdate = adventureToUpdateOptional.get();
		
		if(!adventure.getBlogCompleted()) {
			adventureToUpdate.setBlogCompleted(true);
		}else {
			adventureToUpdate.setBlogCompleted(false);
		}
		
		
		Adventure updatedAdventure = this.adventureRepository.save(adventureToUpdate);
		
		return updatedAdventure;
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteAdventure(@PathVariable("id")Integer id) {
		Optional<Adventure> adventureToDeleteOptional = this.adventureRepository.findById(id);
		
		if(adventureToDeleteOptional.isPresent()) {
			Adventure adventureToDelete = adventureToDeleteOptional.get();
			this.adventureRepository.delete(adventureToDelete);
		}
	}
	
	
}
