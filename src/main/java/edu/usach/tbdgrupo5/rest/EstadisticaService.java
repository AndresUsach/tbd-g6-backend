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

import edu.usach.tbdgrupo5.entities.Estadistica;
import edu.usach.tbdgrupo5.repository.EstadisticaRepository;

@RestController  
@RequestMapping("/estadisticas")
public class EstadisticaService {
	
	@Autowired
	private EstadisticaRepository estadisticaRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Estadistica> getAllUsers() {
		return estadisticaRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Estadistica findOne(@PathVariable("id") Integer id) {
		return estadisticaRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Estadistica create(@RequestBody Estadistica resource) {
	     return estadisticaRepository.save(resource);
	}
	 
}