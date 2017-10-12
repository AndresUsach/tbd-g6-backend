package edu.usach.tbdgrupo5.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.usach.tbdgrupo5.entities.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {
	

}
