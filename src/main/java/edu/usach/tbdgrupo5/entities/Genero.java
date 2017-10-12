package edu.usach.tbdgrupo5.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the genero database table.
 * 
 */
@Entity
@NamedQuery(name="Genero.findAll", query="SELECT g FROM Genero g")
public class Genero implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idgenero;

	private String nombre;

	//bi-directional many-to-one association to Artista
	@OneToMany(mappedBy="genero")
	private List<Artista> artistas;

	public Genero() {
	}

	public int getIdgenero() {
		return this.idgenero;
	}

	public void setIdgenero(int idgenero) {
		this.idgenero = idgenero;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Artista> getArtistas() {
		return this.artistas;
	}

	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	public Artista addArtista(Artista artista) {
		getArtistas().add(artista);
		artista.setGenero(this);

		return artista;
	}

	public Artista removeArtista(Artista artista) {
		getArtistas().remove(artista);
		artista.setGenero(null);

		return artista;
	}

}