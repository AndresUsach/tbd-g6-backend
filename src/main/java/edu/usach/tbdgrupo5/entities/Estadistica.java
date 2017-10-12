package edu.usach.tbdgrupo5.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the estadistica database table.
 * 
 */
@Entity
@NamedQuery(name="Estadistica.findAll", query="SELECT e FROM Estadistica e")
public class Estadistica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idestadistica;

	private String comentarioNegativo;

	private String comentarioPositivo;

	//bi-directional many-to-one association to Artista
	@ManyToOne
	@JoinColumn(name="idartista")
	private Artista artista;

	public Estadistica() {
	}

	public int getIdestadistica() {
		return this.idestadistica;
	}

	public void setIdestadistica(int idestadistica) {
		this.idestadistica = idestadistica;
	}

	public String getComentarioNegativo() {
		return this.comentarioNegativo;
	}

	public void setComentarioNegativo(String comentarioNegativo) {
		this.comentarioNegativo = comentarioNegativo;
	}

	public String getComentarioPositivo() {
		return this.comentarioPositivo;
	}

	public void setComentarioPositivo(String comentarioPositivo) {
		this.comentarioPositivo = comentarioPositivo;
	}

	public Artista getArtista() {
		return this.artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

}