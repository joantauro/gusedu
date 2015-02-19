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
@Table(name = "PRODUCTO")
public class Producto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "PRO_CODIGO")
    private Integer idProducto;
	
	@Column(nullable=true, name="PRO_SKU", length=10)
	private String sku;
	
	@Column(nullable=true, name="PRO_DESCRIPCION_C", length=30)
	private String descripcionCorta;
	
	@Column(nullable=true, name="PRO_DESCRIPCION_M", length=70)
	private String descripcionMedia;
	
	@Column(nullable=true, name="PRO_DESCRIPCION_L", length=100)
	private String descripcionLarga;	
	
	@Column(nullable=true, name="PRO_COSTO_UNITARIO")
	private Double costoUnitario;
	
	@Column(nullable=true, name="PRO_EXISTENCIAS")
	private Double existencias;	
	
    @ManyToOne @JoinColumn(name="TPR_CODIGO", nullable=false)
    private TipoProducto proTipoProducto;	
    
    @ManyToOne @JoinColumn(name="UME_CODIGO", nullable=false)
    private UnidadMedida proUnidadMedida;   
    
    @ManyToOne @JoinColumn(name="MOD_CODIGO", nullable=false)
    private Modelo proModelo;    
	
	@OneToMany(mappedBy="pxvProducto")
	private Collection<ProductoVisita> proProductoVisitas;

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	public String getDescripcionMedia() {
		return descripcionMedia;
	}

	public void setDescripcionMedia(String descripcionMedia) {
		this.descripcionMedia = descripcionMedia;
	}

	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	public Double getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(Double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public Double getExistencias() {
		return existencias;
	}

	public void setExistencias(Double existencias) {
		this.existencias = existencias;
	}

	public TipoProducto getProTipoProducto() {
		return proTipoProducto;
	}

	public void setProTipoProducto(TipoProducto proTipoProducto) {
		this.proTipoProducto = proTipoProducto;
	}

	public UnidadMedida getProUnidadMedida() {
		return proUnidadMedida;
	}

	public void setProUnidadMedida(UnidadMedida proUnidadMedida) {
		this.proUnidadMedida = proUnidadMedida;
	}

	public Modelo getProModelo() {
		return proModelo;
	}

	public void setProModelo(Modelo proModelo) {
		this.proModelo = proModelo;
	}

	public Collection<ProductoVisita> getProProductoVisitas() {
		return proProductoVisitas;
	}

	public void setProProductoVisitas(Collection<ProductoVisita> proProductoVisitas) {
		this.proProductoVisitas = proProductoVisitas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idProducto == null) ? 0 : idProducto.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		Producto other = (Producto) obj;
		if (idProducto == null) {
			if (other.idProducto != null)
				return false;
		} else if (!idProducto.equals(other.idProducto))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", sku=" + sku
				+ ", descripcionCorta=" + descripcionCorta
				+ ", descripcionMedia=" + descripcionMedia
				+ ", descripcionLarga=" + descripcionLarga + ", costoUnitario="
				+ costoUnitario + ", existencias=" + existencias + "]";
	}

}
