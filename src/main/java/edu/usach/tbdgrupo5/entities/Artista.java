package edu.usach.tbdgrupo5.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the artista database table.
 * 
 */
@Entity
@NamedQuery(name="Artista.findAll", query="SELECT a FROM Artista a")
public class Artista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idartista;

	private int comentariosNegativos;

	private int comentariosPositivos;

	private int comentariosNeutros;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Genero
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="idgenero")
	private Genero genero;

	//bi-directional many-to-many association to Keyword
	@ManyToMany(mappedBy="artistas")
	@JsonIgnore
	private List<Keyword> keywords;

	public Artista() {
	}

	public int getIdartista() {
		return this.idartista;
	}

	public void setIdartista(int idartista) {
		this.idartista = idartista;
	}

	public int getComentariosNegativos() {
		return this.comentariosNegativos;
	}

	public void setComentariosNegativos(int comentariosNegativos) {
		this.comentariosNegativos = comentariosNegativos;
	}

	public int getComentariosPositivos() {
		return this.comentariosPositivos;
	}

	public void setComentariosPositivos(int comentariosPositivos) {
		this.comentariosPositivos = comentariosPositivos;
	}

	public int getComentariosNeutros() {
		return comentariosNeutros;
	}

	public void setComentariosNeutros(int comentariosNeutros) {
		this.comentariosNeutros = comentariosNeutros;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public List<Keyword> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

}