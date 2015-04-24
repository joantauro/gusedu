package com.gusedu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Persona;
import com.gusedu.model.TipoUsuario;
import com.gusedu.model.Usuario;
import com.gusedu.service.EmailService;
import com.gusedu.service.PersonaService;
import com.gusedu.service.TipoUsuarioService;
import com.gusedu.service.UsuarioService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	UsuarioService usuarioservice;
	
	@Autowired
	TipoUsuarioService tipousuarioservice;
	
	@Autowired
	PersonaService personaservice;
	
	private Usuario usuario;
	private Persona persona;
	
	private List<Usuario> usuarios;
	
	private String query;
	private String queryTU;
	private boolean mesM;
	private int cantM;
	private Date fechaM;
	private String tiempo;
	private long diasRestantes;
	
	private TipoUsuario tipousuario;
	private List<TipoUsuario> tipousuarios;
	
	public UsuarioBean()
	{
		mesM=true; cantM=0;tiempo="Dias";
		usuario = new Usuario(); 
		persona = new Persona();
		usuario.setUsuTipoUsuario(new TipoUsuario());
		tipousuario = new TipoUsuario();
		if(usuario.getIdUsuario()!=null){
			mesM=usuario.getEsActivo() ? false : true;
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		if (query != null) {
			if (!query.isEmpty()) {
				return usuarios;
			}
		}
		return usuarioservice.getAll();
	}
	
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String preAdd() {
		usuario = new Usuario();
		usuario.setUsuTipoUsuario(new TipoUsuario());
		return "pm:agregarUsuario?transition=flip";
	}
	
	public String add() {
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "Este codigo de usuario ya esta registrado");
			return null;
		}
		if (usuarioservice.saveUsuario(usuario)) {
			StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente el usuario");
			usuario = new Usuario();
			return "pm:datosUsuario?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo registrar el usuario");
			return null;
		}
	}
	
	
	public String preUpdate(int idUsuario) {
		usuario = usuarioservice.getUsuarioeById(idUsuario);
		fechaM=usuario.getFechafinm();
		mesM=usuario.getEsActivo() ? false : true;
		diasRestantes=StaticUtil.diasRestantes(fechaM);
		//datosClinicos?faces-redirect=true
		return "editarUsuario?faces-redirect=true";
	}
	
	public String update() {
		if (usuarioservice.updateUsuario(usuario)) {
			StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente el usuario");
			//		return "consultarPacientes?faces-redirect=true";
			return "gestionUsuario?faces-redirect=true";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar el usuario");
			return null;
		}
	}
	
	public void preDelete(int idUsuario) {
		usuario = usuarioservice.getUsuarioeById(idUsuario);
	}

	public void delete() {
		
		usuario.setEsActivo(false);
		usuario.setFechafinm(new Date());
		if (usuarioservice.updateUsuario(usuario)) {
			StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente el usuario");
		 
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar el usuario");
 
		}
		usuario = new Usuario();
	}
	
	public void cancelar() {
		usuario = new Usuario();
		usuario.setUsuTipoUsuario(new TipoUsuario());
		
		tipousuario = new TipoUsuario();
	}
	
	public void filtrarBusqueda() {
		usuarios = usuarioservice.getAll();
		List<Usuario> filtrados = new ArrayList<>();
		for (Usuario u : usuarios) {
			if (u.getEmpresa().toLowerCase().contains(query.toLowerCase())) {
				filtrados.add(u);
			}
		}
		usuarios = filtrados;
	}


	   public List<TipoUsuario> getTipousuarios() {
		   if (queryTU != null) {
				if (!queryTU.isEmpty()) {
					return tipousuarios;
				}
			}
		  return tipousuarioservice.getAll();
	    }

	public TipoUsuario getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;
	}

	public String preAddTU() {
		tipousuario = new TipoUsuario();
		return "pm:agregarTipoUsuario?transition=flip";
	}
	public String AddTU() {
		if (esRepetidoTU()) {
			StaticUtil.errorMessage("Error", "El nombre está duplicado");
			return null;
		}
		
		if (tipousuarioservice.saveTipoUsuario(tipousuario)) {
			StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente el tipo de Usuario");
			System.out.println("Exito : "+ tipousuario.getDescripcion());
			tipousuario = new TipoUsuario();
			return "pm:datosTipoUsuario?transition=flip";
		} else {
			System.out.println("Fail");
			StaticUtil.errorMessage("Error", "No se pudo registrar el tipo de usuario");
			return null;
		}
	}
	
	public String preUpdateTU(int idTipoUsuario) {
		tipousuario = tipousuarioservice.getUsuarioeById(idTipoUsuario);
		return "pm:editarTipoUsuario?transition=flip";
	}
	
	public String updateTU() {
		if (tipousuarioservice.updateTipoUsuario(tipousuario)) {
			StaticUtil.correctMesage("Éxito", "Se ha actualizado correctamente el tipo usuario");
			return "pm:datosTipoUsuario?transition=flip";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo actualizar el tipo de usuario");
			return null;
		}
	}
	
	public String preDatosUsuario(int idTipoUsuario){
		usuario = usuarioservice.getUsuarioeById(idTipoUsuario);
		if(usuario.getUsuPersona()!=null)
		{
			persona=personaservice.getPersonaById(usuario.getUsuPersona().getIdPersona());
		}
		return "registrarUsuPersona?faces-redirect=true";
	}
	
	public String DatosUsuario()
	{
		if(persona.getIdPersona()==null)
		{
			if (personaservice.savePersona(persona)) {
				StaticUtil.correctMesage("Éxito", "Se ha registrado correctamente los datos del usuario");
				usuario.setUsuPersona(persona);
				usuarioservice.updateUsuario(usuario);
				
				persona = new Persona();
				usuario = new Usuario();
				usuario.setUsuTipoUsuario(new TipoUsuario());
				return "gestionUsuario?faces-redirect=true";
			} else {
				StaticUtil.errorMessage("Error", "No se pudo registrar los datos del usuario");
				return null;
			}
		}else
		{
			if (personaservice.updatePersona(persona)) {
				StaticUtil.correctMesage("Éxito", "Se ha modificado correctamente los datos del usuario");
				persona = new Persona();
				usuario = new Usuario();
				usuario.setUsuTipoUsuario(new TipoUsuario());
				return "gestionUsuario?faces-redirect=true";
			} else {
				StaticUtil.errorMessage("Error", "No se pudo registrar los datos del usuario");
				return null;
			}
		}

	}
	
	public void preDeleteTU(int idTipoUsuario) {
		tipousuario = tipousuarioservice.getUsuarioeById(idTipoUsuario);
	}

	public void deleteTU() {
		if (tipousuarioservice.deleteTipoUsuario(tipousuario)) {
			StaticUtil.correctMesage("Éxito", "Se ha eliminado correctamente el tipo de usuario");
		 
		} else {
			StaticUtil.errorMessage("Error", "No se pudo eliminar el tipo de usuario");
 
		}
		tipousuario = new TipoUsuario();
	}
	public void filtrarBusquedaTU() {
		tipousuarios = tipousuarioservice.getAll();
		
		List<TipoUsuario> filtrados = new ArrayList<>();
		for (TipoUsuario u : tipousuarios) {
			if (u.getDescripcion().toLowerCase().contains(queryTU.toLowerCase())) {
				filtrados.add(u);
			}
		}
		tipousuarios = filtrados;
	}


	public String getQueryTU() {
		return queryTU;
	}

	public void setQueryTU(String queryTU) {
		this.queryTU = queryTU;
	}
	
	public boolean esRepetido() {
		boolean repetido = false;
		for (Usuario u : usuarioservice.getAll()) {  
			if (u.getUsuario().equalsIgnoreCase(usuario.getUsuario())) {
				repetido = true;
				return repetido;
			}
		}
		return repetido;
	}
	
	public boolean esRepetidoTU() {
		boolean repetido = false;
		for (TipoUsuario tu : tipousuarioservice.getAll()) {  
			if (tu.getDescripcion().equalsIgnoreCase(tipousuario.getDescripcion())) {
				repetido = true;
				return repetido;
			}
		}
		return repetido;
	}
	
	public void addMessage()
	{
		mesM=usuario.getEsActivo() ? false : true;
		if(mesM)
		{
			cantM=0;
		}else
		{
			cantM=1;
			usuario.setFechafinm(new Date());
			cargarFecha();
		}
	}
	
	public void Aumento()
	{
		mesM=usuario.getEsActivo() ? false : true;
		if(mesM)
		{
			cantM=0;
			usuario.setFechafinm(new Date());
		}else
		{
			cantM=1;
			usuario.setFechafinm(fechaM);
			cargarFecha();
		}
	}
	public void cargarFecha()
	{
		usuario.setFechafinm(StaticUtil.sumarRestarDiasFecha(StaticUtil.getFechaActual(), cantM,tiempo));
	}
	public void aumentarMembresia(){
		usuario.setFechafinm(StaticUtil.sumarRestarDiasFecha(fechaM, cantM,tiempo));
	}

	public void activarDemonio()
	{
		System.out.println("Inicio de Daemon.");
        long delay = 20;// demoraantes de la primera ejecucion
        long period = 180; //periodo entre ejecuciones ( en segundos)
        startUpTimer(delay, period);
        System.out.println("Daemon iniciado");
	}

	public void PruebaSP()
	{
		System.out.println("Estado : "+usuario.getEsActivo());
		/*EmailService ser = new EmailService();
		ser.ejecutarStoredProcedure();*/
		/*ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		EmployeeDao dao = (EmployeeDao) ctx.getBean("employeeDao"); 
		//calling stored procedure using DAO method 
		System.out.println("Employee name for id 103 is : " + dao.getEmployeeName(103));*/
		}
	
	public boolean isMesM() {
		return mesM;
	}

	public void setMesM(boolean mesM) {
		this.mesM = mesM;
	}

	public int getCantM() {
		return cantM;
	}

	public void setCantM(int cantM) {
		this.cantM = cantM;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
    public  void startUpTimer(long delay, long period)
    {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() { 
                List<Usuario> listmembresia = usuarioservice.getAllFinMembresia(0);
                List<Usuario> listacorreo = usuarioservice.getAllFinMembresia(6);
                Usuario u = new Usuario();
                for(int i=0;i<listmembresia.size();i++)
                {
                	u=listmembresia.get(i);
                    u.setEsActivo(false);
                    usuarioservice.updateUsuario(u);
                }
                for(int j=0;j<listacorreo.size();j++)
                {
                	EmailService obj = new EmailService();
                	
            		obj.enviarEmail(listacorreo.get(j).getUsuPersona().getCorreo());
            		System.out.println("Correo : "+listacorreo.get(j).getUsuPersona().getCorreo());
                }
            }
        };
        //Inicia el timer con demora inicial y periodo especificados en milisegundos
        timer.schedule(task, delay*1000, period*1000);
    }

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Date getFechaM() {
		return fechaM;
	}

	public void setFechaM(Date fechaM) {
		this.fechaM = fechaM;
	}

	public long getDiasRestantes() {
		return diasRestantes;
	}

	public void setDiasRestantes(long diasRestantes) {
		this.diasRestantes = diasRestantes;
	}
}
