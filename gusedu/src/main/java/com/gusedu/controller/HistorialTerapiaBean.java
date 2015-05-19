package com.gusedu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.gusedu.model.Cliente;
import com.gusedu.model.Terapia;
import com.gusedu.model.TerapiaPar;
import com.gusedu.service.ClienteService;
import com.gusedu.service.TerapiaService;


@Component
@Scope(value="session")
public class HistorialTerapiaBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	TerapiaService terapiaService;
	
	@Autowired
	ClienteService clienteService;
	

	private Cliente cliente;

   private List<String> rowNames = new ArrayList<String>();
   private List<String> colNames = new ArrayList<String>();
   private ArrayList<ArrayList<ArrayList<String>>> data3D = new ArrayList<ArrayList<ArrayList<String>>>();
   
   private List<Terapia> listaterapia;
   private List<TerapiaPar> listaterapiapar;


	public HistorialTerapiaBean()
	{
		
	}
	@PostConstruct
	public void construir()
	{
		listaterapia = new ArrayList<>();
		 cliente= new Cliente();
		 cliente =clienteService.getClienteById(145);
		 listaterapia=terapiaService.terapiasPorCliente(cliente);
		// System.out.println("Cantidad Par:"+listaterapiapar.size());
		 Terapia terapia = new Terapia();
		 terapia=terapiaService.terapiaById(93);
	      listaterapiapar=terapiaService.getAllTerapiaParbyTerapia(terapia);
	     // System.out.println("Cantidad Terapia :"+listaterapia.size());
	        for(int j=0;j<listaterapiapar.size();j++)
	        {
	            rowNames.add(listaterapiapar.get(j).getTxpPar().getParPunto1().getNombre()+"-"+listaterapiapar.get(j).getTxpPar().getParPunto2().getNombre());
	        }
	        System.out.println(listaterapia.size());
	        for(int i=0;i<listaterapia.size();i++)
	        {
	            colNames.add(listaterapia.get(i).getFechaRealizada() +""); 
	        }
	        
	        for (int i = 0; i < rowNames.size(); i++) {
	            data3D.add(new ArrayList<ArrayList<String>>());
	            for (int j = 0; j < colNames.size(); j++) {
	                data3D.get(i).add(new ArrayList<String>());
	            }
	        }
	        
	        for(int i=0;i<listaterapiapar.size();i++)
	        {
	            for(int j=0;j<listaterapia.size();j++)
	            {
	            	 data3D.get(i).get(j).add(terapiaService.getAllParbyAllTerapia(listaterapia.get(j), listaterapiapar.get(i).getTxpPar()));
	            }
	        }
	}
	
	public void prueba()
	{
		System.out.println("Hola");
		/* Cliente  cli= new Cliente();
		 cli.setIdCliente(120);
		 listaterapia=terapiaservice.terapiasPorCliente(cli);
		 System.out.println("Cantidad Par:"+listaterapiapar.size());
		 Terapia terapia = new Terapia();
		 terapia.setIdTerapia(87);
	      listaterapiapar=terapiaservice.getAllTerapiaParbyTerapia(terapia);
	      System.out.println("Cantidad Terapia :"+listaterapia.size());*/
	}


    public List<TerapiaPar> getListaterapiapar() {
        return listaterapiapar;
    }

    public List<Terapia> getListaterapia() {
        return listaterapia;
    }
    


    public List<String> getRowNames() {
        return rowNames;
    }

    public void setRowNames(List<String> rowNames) {
        this.rowNames = rowNames;
    }

    public List<String> getColNames() {
        return colNames;
    }

    public void setColNames(List<String> colNames) {
        this.colNames = colNames;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getData3D() {
        return data3D;
    }

    public void setData3D(ArrayList<ArrayList<ArrayList<String>>> data3D) {
        this.data3D = data3D;
    }


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


}
