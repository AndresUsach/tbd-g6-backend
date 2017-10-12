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

import edu.usach.tbdgrupo5.entities.Usuario;
import edu.usach.tbdgrupo5.repository.UsuarioRepository;

@RestController  
@RequestMapping("/usuarios")
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Usuario> getAllUsers() {
		return usuarioRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Usuario findOne(@PathVariable("id") Integer id) {
		return usuarioRepository.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Usuario create(@RequestBody Usuario resource) {
	     return usuarioRepository.save(resource);
	}
	 
}