package com.gusedu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadTerapia;
import com.gusedu.model.Par;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaTerapia;
import com.gusedu.model.Terapia;
import com.gusedu.service.EnfermedadService;
import com.gusedu.service.ParService;
import com.gusedu.service.SintomaService;
import com.gusedu.service.TerapiaService;
import com.gusedu.util.StaticUtil;

@Controller
public class TerapiaBean {

	@Autowired
	TerapiaService terapiaService;

	@Autowired
	EnfermedadService enfermedadService;

	@Autowired
	SintomaService sintomaService;

	@Autowired
	ParService parService;

	private Terapia terapia;
	private List<Terapia> terapias;

	private List<EnfermedadTerapia> terEnfermedades;
	private List<SintomaTerapia> terSintomas;
	private List<Par> paresSugeridos;

	private Enfermedad enfermedad;
	private Sintoma sintoma;

	private int index;
	private int sliderDolor;

	public TerapiaBean() {
		sliderDolor = 0;
		terapia = new Terapia();
		enfermedad = new Enfermedad();
		sintoma = new Sintoma();
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Terapia getTerapia() {
		return terapia;
	}

	public List<Par> getParesSugeridos() {
		paresSugeridos = getSugeridos();
		return paresSugeridos;
	}

	public void setParesSugeridos(List<Par> paresSugeridos) {
		this.paresSugeridos = paresSugeridos;
	}

	public void setTerapia(Terapia terapia) {
		this.terapia = terapia;
	}

	public int getSliderDolor() {
		return sliderDolor;
	}

	public void setSliderDolor(int sliderDolor) {
		this.sliderDolor = sliderDolor;
	}

	public List<EnfermedadTerapia> getTerEnfermedades() {
		terEnfermedades = terapiaService.getEnfermedadesByTerapia(terapia);
		return terEnfermedades;
	}

	public void setTerEnfermedades(List<EnfermedadTerapia> terEnfermedades) {
		this.terEnfermedades = terEnfermedades;
	}

	public List<SintomaTerapia> getTerSintomas() {
		terSintomas = terapiaService.getSintomasByTerapia(terapia);
		return terSintomas;
	}

	public void setTerSintomas(List<SintomaTerapia> terSintomas) {
		this.terSintomas = terSintomas;
	}

	public List<Terapia> getTerapias() {
		return terapias;
	}

	public void setTerapias(List<Terapia> terapias) {
		this.terapias = terapias;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Sintoma getSintoma() {
		return sintoma;
	}

	public void setSintoma(Sintoma sintoma) {
		this.sintoma = sintoma;
	}

	public String cargarTerapiaEspecifica(int id) {
		terapia = terapiaService.terapiaById(id);
		index = 0;
		return "gestionTerapia?faces-redirect=true";
	}

	public String addEnfermedad() {
		EnfermedadTerapia enfermedadTerapia = new EnfermedadTerapia();
		enfermedadTerapia.setExtTerapia(terapia);
		enfermedadTerapia.setExtEnfermedad(enfermedad);
		if (enfermedadRepetida()) {
			StaticUtil.errorMessage("Error", "La enfermedad ya existe");
			return null;
		} else {
			if (enfermedadService.saveEnfermedadTerapia(enfermedadTerapia)) {
				StaticUtil.correctMesage("Éxito",
						"Se agregó correctamente la enfermedad");
				enfermedad = new Enfermedad();
				index = 0;
				return "gestionTerapia?faces-redirect=true";
			} else {
				StaticUtil.errorMessage("Error",
						"Hubo un error al guardar la enfermedad");
				return null;
			}
		}
	}

	public String addSintoma() {
		SintomaTerapia sintomaTerapia = new SintomaTerapia();
		sintomaTerapia.setSxtTerapia(terapia);
		sintomaTerapia.setSxtSintoma(sintoma);
		if (sintomaRepetido()) {
			StaticUtil.errorMessage("Error", "El sintoma ya existe");
			return null;
		} else {
			sintomaTerapia.setDolor(sliderDolor);
			if (sintomaService.saveSintomaTerapia(sintomaTerapia)) {
				StaticUtil.correctMesage("Éxito",
						"Se agregó correctamente el sintoma");
				sintoma = new Sintoma();
				sliderDolor = 0;
				index = 1;
				return "gestionTerapia?faces-redirect=true";
			} else {
				StaticUtil.errorMessage("Error",
						"Hubo un error al guardar el sintoma");
				return null;
			}
		}
	}

	public List<Enfermedad> autoCompletarEnfermedad(String query) {
		List<Enfermedad> allEnfermedades = enfermedadService.getAll();
		List<Enfermedad> enfFiltrados = new ArrayList<Enfermedad>();

		for (int i = 0; i < allEnfermedades.size(); i++) {
			Enfermedad enfermedad = allEnfermedades.get(i);
			if (enfermedad.getNombre().toLowerCase().startsWith(query)) {
				enfFiltrados.add(enfermedad);
			}
		}
		return enfFiltrados;
	}

	public List<Sintoma> autoCompletarSintoma(String query) {
		List<Sintoma> allSintomas = sintomaService.getAll();
		List<Sintoma> sinFiltrados = new ArrayList<Sintoma>();

		for (int i = 0; i < allSintomas.size(); i++) {
			Sintoma sintoma = allSintomas.get(i);
			if (sintoma.getDescripcion().toLowerCase().startsWith(query)) {
				sinFiltrados.add(sintoma);
			}
		}
		return sinFiltrados;
	}

	public boolean enfermedadRepetida() {
		List<EnfermedadTerapia> terEnf = terapiaService
				.getEnfermedadesByTerapia(terapia);
		for (EnfermedadTerapia e : terEnf) {
			if (e.getExtEnfermedad().getNombre()
					.equalsIgnoreCase(enfermedad.getNombre())) {
				return true;
			}
		}
		return false;
	}

	public boolean sintomaRepetido() {
		List<SintomaTerapia> terSin = terapiaService
				.getSintomasByTerapia(terapia);
		for (SintomaTerapia e : terSin) {
			if (e.getSxtSintoma().getDescripcion()
					.equalsIgnoreCase(sintoma.getDescripcion())) {
				return true;
			}
		}
		return false;
	}

	public String finalizarEdicion() {
		return "gestionTerapiaDetalle";
	}
	
	public List<Par> getSugeridos() {
		List<Par> sugeridos = new ArrayList<>();
		terSintomas = terapiaService.getSintomasByTerapia(terapia);
		terEnfermedades = terapiaService.getEnfermedadesByTerapia(terapia);

		if (terSintomas != null) {
			for (SintomaTerapia sxt : terSintomas) {
				List<Par> paresSintoma = parService.getParesBySintoma(sxt.getSxtSintoma());
				if (paresSintoma != null) {
					for (Par par : paresSintoma) {
						boolean esExistente = false;
						if (sugeridos != null) {
							if (sugeridos.isEmpty()) {
								sugeridos.add(par);
							} else {
								for (Par sugerido : sugeridos) {
									if (sugerido.getIdPar() == par.getIdPar()) {
										esExistente = true;
										break;
									}									
								}
								if(esExistente == false){
									sugeridos.add(par);
								}
							}
						}
					}
				}
			}
		}

		if (terEnfermedades != null) {
			for (EnfermedadTerapia ext : terEnfermedades) {
				List<Par> paresEnfermedad = parService.getParesByEnfermedad(ext
						.getExtEnfermedad());
				if (paresEnfermedad != null) {
					for (Par par : paresEnfermedad) {
						boolean esExistente = false;
						if (sugeridos != null) {
							if (sugeridos.isEmpty()) {
								sugeridos.add(par);
							} else {
								for (Par sugerido : sugeridos) {
									if (sugerido.getIdPar() == par.getIdPar()) {
										esExistente = true;
										break;
									}									
								}
								if(esExistente == false){
									sugeridos.add(par);
								}
							}
						}
					}
				}
			}
		}
		return sugeridos;
	}

}
