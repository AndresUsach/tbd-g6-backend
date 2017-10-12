package edu.usach.tbdgrupo5.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.usach.tbdgrupo5.entities.Genero;
import edu.usach.tbdgrupo5.repository.GeneroRepository;

@RestController  
@RequestMapping("/generos")
public class GeneroService {
	
	@Autowired
	private GeneroRepository generoRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Genero> getAllUsers() {
		return generoRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Genero findOne(@PathVariable("id") Integer id) {
		return generoRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Genero create(@RequestBody Genero resource) {
	     return generoRepository.save(resource);
	}
	 
}
