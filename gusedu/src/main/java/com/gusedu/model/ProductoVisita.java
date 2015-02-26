package com.gusedu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTO_VISITA")
public class ProductoVisita {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "PXV_CODIGO")
    private Integer idProductoVisita;      
    
	@Column(nullable=true, name="PXV_CANTIDAD")
	private Double cantidad;
	
	@Column(nullable=true, name="PXV_COSTO_PARCIAL")
	private Double costoParcial;	
	
	@Column(nullable=true, name="PXV_CURRENCY_SYMBOL")
	private String currencySymbol;		
	
    @ManyToOne @JoinColumn(name="PRO_CODIGO", nullable=false)
    private Producto pxvProducto;   
    
    @ManyToOne @JoinColumn(name="VIS_CODIGO", nullable=false)
    private Visita pxvVisita; 

	public Integer getIdProductoVisita() {
		return idProductoVisita;
	}

	public void setIdProductoVisita(Integer idProductoVisita) {
		this.idProductoVisita = idProductoVisita;
	}

	public Producto getPxvProducto() {
		return pxvProducto;
	}

	public void setPxvProducto(Producto pxvProducto) {
		this.pxvProducto = pxvProducto;
	}

	public Visita getPxvVisita() {
		return pxvVisita;
	}

	public void setPxvVisita(Visita pxvVisita) {
		this.pxvVisita = pxvVisita;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public Double getCostoParcial() {
		return costoParcial;
	}

	public void setCostoParcial(Double costoParcial) {
		this.costoParcial = costoParcial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idProductoVisita == null) ? 0 : idProductoVisita.hashCode());
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
		ProductoVisita other = (ProductoVisita) obj;
		if (idProductoVisita == null) {
			if (other.idProductoVisita != null)
				return false;
		} else if (!idProductoVisita.equals(other.idProductoVisita))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductoVisita [idProductoVisita=" + idProductoVisita
				+ ", pxvProducto=" + pxvProducto + ", pxvVisita=" + pxvVisita
				+ ", cantidad=" + cantidad + "]";
	}	
    
}
