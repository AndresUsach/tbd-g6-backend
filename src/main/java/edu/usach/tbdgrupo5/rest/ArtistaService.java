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

import edu.usach.tbdgrupo5.entities.Artista;
import edu.usach.tbdgrupo5.repository.ArtistaRepository;

@RestController  
@RequestMapping("/artistas")
public class ArtistaService {
	
	@Autowired
	private ArtistaRepository artistaRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Artista> getAllUsers() {
		return artistaRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Artista findOne(@PathVariable("id") Integer id) {
		return artistaRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Artista create(@RequestBody Artista resource) {
	     return artistaRepository.save(resource);
	}
	 
}
