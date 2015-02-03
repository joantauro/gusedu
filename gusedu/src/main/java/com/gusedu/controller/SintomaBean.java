package com.gusedu.controller;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.Sintoma;
import com.gusedu.service.SintomaService;
import com.gusedu.util.StaticUtil;

@Controller
public class SintomaBean {

	private Sintoma sintoma;
	private List<Sintoma> sintomas;

	@Autowired
	SintomaService sintomaService;

	public SintomaBean() {
		sintoma = new Sintoma();
	}

	public Sintoma getSintoma() {
		return sintoma;
	}

	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	public List<Sintoma> getSintomas() {
		sintomas = sintomaService.getAll();
		return sintomas;
	}

	public void setSintomas(List<Sintoma> sintomas) {
		this.sintomas = sintomas;
	}

	public String preAdd() {
		sintoma = new Sintoma();
		return "pm:agregarSintoma?transition=flip";
	}

	public String add() {
		if (esRepetido()) {
			StaticUtil.errorMessage("Error", "El nombre está duplicado");
			sintoma = new Sintoma();
			return "pm:agregarSintoma?faces-redirect=true";
		}
		if (sintomaService.saveSintoma(sintoma)) {
			StaticUtil.correctMesage("Éxito",
					"Se ha registrado correctamente el sintoma");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.getFlash().setKeepMessages(true);
			return "pm:gestionSintoma?faces-redirect=true";
		} else {
			StaticUtil.errorMessage("Error", "No se pudo registrar el sintoma");
			return "pm:agregarSintoma?faces-redirect=true";
		}
	}

	public String preUpdate(int id) {
		sintoma = sintomaService.getById(id);
		return "pm:editarSintoma?transition=flip";
	}

	public String update() {
		if (sintomaService.updateSintoma(sintoma)) {
			StaticUtil.correctMesage("Éxito",
					"Se ha actualizado correctamente el sintoma");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.getFlash().setKeepMessages(true);
			return "pm:gestionSintoma?faces-redirect=true";
		} else {
			StaticUtil
					.errorMessage("Error", "No se pudo actualizar el sintoma");
			return "pm:editarSintoma?faces-redirect=true";
		}
	}

	public void preDelete(int id) {
		sintoma = sintomaService.getById(id);
	}

	public void delete() {
		sintomaService.deleteSintoma(sintoma);
		sintoma = new Sintoma();
	}

	public void cancelar() {
		sintoma = new Sintoma();
	}

	public boolean esRepetido() {
		boolean repetido = false;
		if (sintoma.getDescripcion() == null) {
			return repetido;
		}
		for (Sintoma s : sintomaService.getAll()) {
			if (sintoma.getDescripcion().equalsIgnoreCase(s.getDescripcion())) {
				repetido = true;
				return repetido;
			}
		}
		return repetido;
	}

}
