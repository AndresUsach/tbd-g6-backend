package edu.usach.tbdgrupo5.entities;

import java.io.Serializable;
import javax.persistence.*;
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

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to Genero
	@ManyToOne
	@JoinColumn(name="idgenero")
	private Genero genero;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idusuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Estadistica
	@OneToMany(mappedBy="artista")
	private List<Estadistica> estadisticas;

	//bi-directional many-to-many association to Keyword
	@ManyToMany(mappedBy="artistas")
	private List<Keyword> keywords;

	public Artista() {
	}

	public int getIdartista() {
		return this.idartista;
	}

	public void setIdartista(int idartista) {
		this.idartista = idartista;
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

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Estadistica> getEstadisticas() {
		return this.estadisticas;
	}

	public void setEstadisticas(List<Estadistica> estadisticas) {
		this.estadisticas = estadisticas;
	}

	public Estadistica addEstadistica(Estadistica estadistica) {
		getEstadisticas().add(estadistica);
		estadistica.setArtista(this);

		return estadistica;
	}

	public Estadistica removeEstadistica(Estadistica estadistica) {
		getEstadisticas().remove(estadistica);
		estadistica.setArtista(null);

		return estadistica;
	}

	public List<Keyword> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

}