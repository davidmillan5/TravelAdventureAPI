package com.app.travel.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.travel.Model.Adventure;

public interface AdventureRepository extends CrudRepository<Adventure, Integer>{
	
	public List<Adventure> findByCountry(String country);
	public List<Adventure> findByState(String state);
	
}
