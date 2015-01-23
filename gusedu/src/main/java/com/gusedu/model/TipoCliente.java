package com.gusedu.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_CLIENTE")
public class TipoCliente {
	
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "TCL_CODIGO")
    private Integer idTipoCliente;	

    @Column(nullable = true, name = "TCL_DESCRIPCION")
    private String descripcion;
    
	@OneToMany(mappedBy="cliTipoCliente")
	private Collection<Cliente> tclClientes;    
    
}
