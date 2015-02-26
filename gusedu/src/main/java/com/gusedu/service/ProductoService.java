package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Producto;
import com.gusedu.model.ProductoVisita;
import com.gusedu.model.Visita;

public interface ProductoService {

	public List<Producto> getAllProductos();
	
	public List<ProductoVisita> getAllProductosByVisita(Visita visita);
	
	public Producto getProductoById(Integer idProducto);
	
	public boolean updateProducto(Producto producto);
	
	public boolean saveProductoVisita(ProductoVisita productoVisita);
	
}
