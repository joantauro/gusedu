package com.gusedu.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PAR")
public class Par {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "PAR_CODIGO")
    private Integer idPar;   
    
    @Column(nullable= true, name= "PAR_URL", length=300)
    private String url;    
    
    @ManyToOne @JoinColumn(name="PUN_CODIGO_P1", nullable=false)
    private Punto parPunto1;
    
    @ManyToOne @JoinColumn(name="PUN_CODIGO_P2", nullable=false)
    private Punto parPunto2;
    
    @ManyToOne @JoinColumn(name="GRU_CODIGO", nullable=false)
    private Grupo parGrupo;

	@OneToMany(mappedBy="sxpPar")
	private Collection<SintomaPar> parSxp;

	public Integer getIdPar() {
		return idPar;
	}

	public void setIdPar(Integer idPar) {
		this.idPar = idPar;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Punto getParPunto1() {
		return parPunto1;
	}

	public void setParPunto1(Punto parPunto1) {
		this.parPunto1 = parPunto1;
	}

	public Punto getParPunto2() {
		return parPunto2;
	}

	public void setParPunto2(Punto parPunto2) {
		this.parPunto2 = parPunto2;
	}

	public Grupo getParGrupo() {
		return parGrupo;
	}

	public void setParGrupo(Grupo parGrupo) {
		this.parGrupo = parGrupo;
	}

	public Collection<SintomaPar> getParSxp() {
		return parSxp;
	}

	public void setParSxp(Collection<SintomaPar> parSxp) {
		this.parSxp = parSxp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPar == null) ? 0 : idPar.hashCode());
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
		Par other = (Par) obj;
		if (idPar == null) {
			if (other.idPar != null)
				return false;
		} else if (!idPar.equals(other.idPar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Par [idPar=" + idPar + ", url="
				+ url + ", parPunto1=" + parPunto1 + ", parPunto2=" + parPunto2
				+ ", parGrupo=" + parGrupo + "]";
	}    
	
}
