package com.gusedu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "MENSAJE")
public class Mensaje {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "MEN_CODIGO")
    private Integer idMensaje;
	
    @Column(nullable = true, name = "MEN_TEXTO")
    private String texto;
    
    @Column(nullable = true, name = "MEN_TITULO")
    private String titulo;
    
    @Column(nullable = true, name = "MEN_FEC_ENVIADO")
    private Date fechaEnviado;
    
    @Column(nullable = true, name = "MEN_LEIDO")
    private Boolean esLeido;

	public Integer getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(Integer idMensaje) {
		this.idMensaje = idMensaje;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaEnviado() {
		return fechaEnviado;
	}

	public void setFechaEnviado(Date fechaEnviado) {
		this.fechaEnviado = fechaEnviado;
	}

	public Boolean getEsLeido() {
		return esLeido;
	}

	public void setEsLeido(Boolean esLeido) {
		this.esLeido = esLeido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idMensaje == null) ? 0 : idMensaje.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		if (idMensaje == null) {
			if (other.idMensaje != null)
				return false;
		} else if (!idMensaje.equals(other.idMensaje))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mensaje [idMensaje=" + idMensaje + ", texto=" + texto
				+ ", titulo=" + titulo + ", fechaEnviado=" + fechaEnviado
				+ ", esLeido=" + esLeido + "]";
	}       	
    
}
