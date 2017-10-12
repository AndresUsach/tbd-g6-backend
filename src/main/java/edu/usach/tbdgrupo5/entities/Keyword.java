package edu.usach.tbdgrupo5.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the keyword database table.
 * 
 */
@Entity
@NamedQuery(name="Keyword.findAll", query="SELECT k FROM Keyword k")
public class Keyword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idkeyword;

	private String nombre;

	//bi-directional many-to-many association to Artista
	@ManyToMany
	@JoinTable(
		name="keyword_artista"
		, joinColumns={
			@JoinColumn(name="idkeyword")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idartista")
			}
		)
	private List<Artista> artistas;

	public Keyword() {
	}

	public int getIdkeyword() {
		return this.idkeyword;
	}

	public void setIdkeyword(int idkeyword) {
		this.idkeyword = idkeyword;
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

}