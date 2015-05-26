package com.gusedu.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Usuario;
import com.gusedu.service.AuthService;
import com.gusedu.service.UsuarioService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value = "session")
public class Auth implements Serializable{

	private static final long serialVersionUID = -1283444186289243937L;

	@Autowired
	AuthService authService;

	@Autowired
	UsuarioService usuarioService;
	
	private String username;
	private String password;
	
	private Usuario userLogged;
	private String passactual;
	private String passnueva;
	private String passrepeat;

	public void cambio()
	{
		
		if(!passactual.equals(userLogged.getPassword()))
		{
			StaticUtil.errorMessage("Error", "Por favor ingrese su contraseña actual");
			return;
		}
		if(!passnueva.equals(passrepeat))
		{
			StaticUtil.errorMessage("Error", "Las password ingresadas no coinciden");
			return;
		}
		userLogged.setPassword(passnueva);
		usuarioService.updateUsuario(userLogged);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgpass').hide();");
		StaticUtil.correctMesage("Éxito", "Se ha cambiado la contraseña");
		cleaner();
		context.update("formpass");
		
	}
	
	public void cleaner()
	{
		passnueva="";
		passactual="";
		passrepeat="";
	}
	
	public String login() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		try {
			if (username == null) {
				StaticUtil.errorMessage("Error ", "El usuario no es válido");
				return null;
			}
			if (username.isEmpty()) {
				StaticUtil.errorMessage("Error ", "Debe ingresar un usuario");
				return null;
			}
			if (password == null) {
				StaticUtil.errorMessage("Error ", "La contraseña no es válida");
				return null;
			}
			if (password.isEmpty()) {
				StaticUtil.errorMessage("Error ", "Debe ingresar una contraseña");
				return null;
			}

			Usuario usuario = authService.find(username, password);

			if (usuario == null) {
				StaticUtil.errorMessage("Los datos ingresados no son correctos", "");
				return null;
			} else {
				if(usuario.getUsuPersona()==null)
				{
					StaticUtil.correctMesage("Éxito ", "Bienvenido al sistema");
					StaticUtil.keepMessages();
				}else
				{
					StaticUtil.correctMesage("Éxito ", "Bienvenido al sistema "+usuario.getUsuPersona().getNombres()+" "+usuario.getUsuPersona().getApellidoPaterno()+" "+usuario.getUsuPersona().getApellidoMaterno());
					StaticUtil.keepMessages();
				}
				request.getSession().setAttribute("userLogged", usuario);				
				setUserLogged(usuario);	
				if(usuario.getUsuTipoUsuario().getIdTipoUsuario()==2 || usuario.getUsuTipoUsuario().getIdTipoUsuario()==3)
				{
					return "/web/Principal?faces-redirect=true";
				}
				/*if(usuario.getUsuTipoUsuario().getIdTipoUsuario()==3){
					return "/mobile/index.jsf";
				}*/
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/home.xhtml?faces-redirect=true";
	}
	
	public String cancel(){
		this.username = "";
		this.password = "";
		this.userLogged = new Usuario();
		return "/home.xhtml?faces-redirect=true";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUserLogged() {
		return userLogged;
	}

	public void setUserLogged(Usuario userLogged) {
		this.userLogged = userLogged;
	}

	public String getPassactual() {
		return passactual;
	}

	public void setPassactual(String passactual) {
		this.passactual = passactual;
	}

	public String getPassnueva() {
		return passnueva;
	}

	public void setPassnueva(String passnueva) {
		this.passnueva = passnueva;
	}

	public String getPassrepeat() {
		return passrepeat;
	}

	public void setPassrepeat(String passrepeat) {
		this.passrepeat = passrepeat;
	}

	
	
}
