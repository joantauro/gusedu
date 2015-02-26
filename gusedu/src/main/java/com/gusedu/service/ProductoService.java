package com.gusedu.service;

import java.util.List;

import com.gusedu.model.Producto;

public interface ProductoService {

	public List<Producto> getAllProductos();
	
	public Producto getProductoById(Integer idProducto);
	
}
