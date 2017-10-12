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

import edu.usach.tbdgrupo5.entities.Keyword;
import edu.usach.tbdgrupo5.repository.KeywordRepository;

@RestController  
@RequestMapping("/keywords")
public class KeywordService {
	
	@Autowired
	private KeywordRepository keywordRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Keyword> getAllUsers() {
		return keywordRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Keyword findOne(@PathVariable("id") Integer id) {
		return keywordRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Keyword create(@RequestBody Keyword resource) {
	     return keywordRepository.save(resource);
	}
	 
}

