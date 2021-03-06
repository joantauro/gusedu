package com.gusedu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gusedu.model.Enfermedad;
import com.gusedu.model.EnfermedadPar;
import com.gusedu.model.Grupo;
import com.gusedu.model.Par;
import com.gusedu.model.Punto;
import com.gusedu.model.Sintoma;
import com.gusedu.model.SintomaPar;
import com.gusedu.service.EnfermedadService;
import com.gusedu.service.GrupoService;
import com.gusedu.service.ParService;
import com.gusedu.service.PuntoService;
import com.gusedu.service.SintomaService;
import com.gusedu.util.StaticUtil;

@Component
@Scope(value="session")
public class ParBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	ParService parService;

	@Autowired
	PuntoService puntoService;

	@Autowired
	GrupoService grupoService;

	@Autowired
	EnfermedadService enfermedadService;

	@Autowired
	SintomaService sintomaService;

	private Par par;
	private List<Par> pares; 
	private List<Par> parcito;
	private List<Par> npar;
	private List<Grupo> grupos;List<Par> result = new ArrayList<>();

	private Punto punto1;
	private Punto punto2;
	private UploadedFile uploadedFile;
	private Grupo grupoSeleccionado;

	private Par parSeleccionado;
	private List<Enfermedad> enfermedadesPar;
	private List<Sintoma> sintomasPar;
	private Enfermedad enfermedadAdd;
	private Sintoma sintomaAdd;
	private List<Enfermedad> enfermedadesAll;
	private List<Sintoma> sintomaAll;

	private EnfermedadPar expToDelete;
	private SintomaPar sxpToDelete;

	private String query;

	int ascP1;
	int ascP2;
	int goiz;
	
	private List<Punto> allPuntos;

	public ParBean() {
		par = new Par();
		par.setParGrupo(new Grupo());
		punto1 = new Punto();
		punto2 = new Punto();
		parSeleccionado = new Par();
		enfermedadAdd = new Enfermedad();
		sintomaAdd = new Sintoma();
		grupoSeleccionado = new Grupo();
	}

	public void clean()
	{
		par = new Par();
		par.setParGrupo(new Grupo());
		punto1 = new Punto();
		punto2 = new Punto();
		parSeleccionado = new Par();
		enfermedadAdd = new Enfermedad();
		sintomaAdd = new Sintoma();
		grupoSeleccionado = new Grupo();
	}
	
	@PostConstruct
	public void post(){
		listarptos();
	}
	
	public Par getPar() {
		return par;
	}

	public void setPar(Par par) {
		this.par = par;
	}

	public Punto getPunto1() {
		return punto1;
	}

	public String getQuery() {
		return query;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setPunto1(Punto punto1) {
		this.punto1 = punto1;
	}

	public Punto getPunto2() {
		return punto2;
	}

	public void setPunto2(Punto punto2) {
		this.punto2 = punto2;
	}

	public List<Par> getPares() {
		if (query != null) {
			if (!query.isEmpty()) {
				return pares;
			}
		}
		if (ascP1 != 0 || ascP2 != 0 || goiz != 0) {
			return pares;
		}
		return parService.getAllPares();
	}

	public void setPares(List<Par> pares) {
		this.pares = pares;
	}

	public ParService getParService() {
		return parService;
	}

	public void setParService(ParService parService) {
		this.parService = parService;
	}

	public PuntoService getPuntoService() {
		return puntoService;
	}

	public void setPuntoService(PuntoService puntoService) {
		this.puntoService = puntoService;
	}

	public Grupo getGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}

	public List<Grupo> getGrupos() {
		grupos = grupoService.getAllGrupos();
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Par getParSeleccionado() {
		return parSeleccionado;
	}

	public void setParSeleccionado(Par parSeleccionado) {
		this.parSeleccionado = parSeleccionado;
	}

	public List<Enfermedad> getEnfermedadesPar() {
		return enfermedadesPar;
	}

	public void setEnfermedadesPar(List<Enfermedad> enfermedadesPar) {
		this.enfermedadesPar = enfermedadesPar;
	}

	public List<Sintoma> getSintomasPar() {
		return sintomasPar;
	}

	public void setSintomasPar(List<Sintoma> sintomasPar) {
		this.sintomasPar = sintomasPar;
	}

	public Enfermedad getEnfermedadAdd() {
		return enfermedadAdd;
	}

	public void setEnfermedadAdd(Enfermedad enfermedadAdd) {
		this.enfermedadAdd = enfermedadAdd;
	}

	public Sintoma getSintomaAdd() {
		return sintomaAdd;
	}

	public void setSintomaAdd(Sintoma sintomaAdd) {
		this.sintomaAdd = sintomaAdd;
	}

	public List<Enfermedad> getEnfermedadesAll() {
		enfermedadesAll = enfermedadService.getAll();
		return enfermedadesAll;
	}

	public void setEnfermedadesAll(List<Enfermedad> enfermedadesAll) {
		this.enfermedadesAll = enfermedadesAll;
	}

	public List<Sintoma> getSintomaAll() {
		sintomaAll = sintomaService.getAll();
		return sintomaAll;
	}

	public void setSintomaAll(List<Sintoma> sintomaAll) {
		this.sintomaAll = sintomaAll;
	}

	public String backToConsultar() {
		par = new Par();
		punto1 = new Punto();
		punto2 = new Punto();
		grupoSeleccionado = new Grupo();
		par = new Par();
		par.setParPunto1(punto1);
		par.setParPunto2(punto2);
		par.setParGrupo(grupoSeleccionado);
		return "pm:consultarPares?transition=flip";
	}

	public String toFileUpload(int idPar) {
		par = parService.parById(idPar);
		return "pm:uploadImage?transition=flip";
	}

	public String cancelUpload() {
		par = new Par();
		return "pm:consultarPares?Transition=flip";
	}

	public String handleFileUpload() {
		String name = uploadedFile.getFileName();
		System.out.println(name);
		return null;
	}

	public String saveFile(String fileName, InputStream in) {
		String destino = "C:\\gusedu\\uploads\\";
		try {
			OutputStream out = new FileOutputStream(
					new File(destino + fileName));
			int readBytes = 0;
			byte[] bytes = new byte[1024];

			while ((readBytes = in.read(bytes)) != -1) {
				out.write(bytes, 0, readBytes);
			}

			in.close();
			out.flush();
			out.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return destino + fileName;
	}

	public String toRegistrar() {
		par = new Par();
		punto1 = new Punto();
		punto2 = new Punto();
		par = new Par();
		par.setParPunto1(punto1);
		par.setParPunto2(punto2);
		par.setParGrupo(grupoSeleccionado);
		return "pm:nuevoPar?transition=flip";
	}

	public void toRegistrarWeb()
	{
		par = new Par();
		punto1 = new Punto();
		punto2 = new Punto();
		par = new Par();
		par.setParPunto1(punto1);
		par.setParPunto2(punto2);
		par.setParGrupo(grupoSeleccionado);
	}
	
	public List<Punto> autoCompletar(String query) {
		//List<Punto> allPuntos = puntoService.getAllPuntos();
		List<Punto> puntosFiltrados = new ArrayList<Punto>();

		for (int i = 0; i < allPuntos.size(); i++) {
			Punto punto = allPuntos.get(i);
			if (punto.getNombre().toLowerCase().startsWith(query)) {
				puntosFiltrados.add(punto);
			}
		}
		return puntosFiltrados;
	}

	public void devolverautoCompletar()
	{
		
	}
	
	public void agregarPar2()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Punto p = (Punto) fc.getExternalContext().getSessionMap().get("punto");
		Grupo gr = new Grupo();
		gr.setIdGrupo(5);
		
		if(punto2==null)
		{
			StaticUtil.errorMessage("Error", "El punto que elegio no existe");
			punto2 = new Punto();
			return;
		}else
		{
			System.out.println("No es nulo :-)");
		}
		
		System.out.println("Grupo : "+gr.getIdGrupo());
		System.out.println("Punto 1 : "+p.getIdPunto());
		System.out.println("Punto 2 : "+punto2.getIdPunto());
		
		par.setParGrupo(gr);
		par.setParPunto1(p);
		par.setParPunto2(punto2);
		
		Par newPar = parService.parByPuntos(p, punto2, gr);
		if (newPar != null) {
			 
			TerapiaBean objetoTBean =(TerapiaBean) fc.getExternalContext().getSessionMap().get("terapiaBean");
			objetoTBean.addPar2(newPar.getIdPar());
			par = new Par();
			punto2 = new Punto();
			return  ;
		}else
		{
			if (parService.savePar(par)) {
				 
				/*StaticUtil.correctMesage("�xito",
						"Se ha a�adido correctamente el par");
				*/TerapiaBean objetoTBean =(TerapiaBean) fc.getExternalContext().getSessionMap().get("terapiaBean");
				objetoTBean.addPar2(par.getIdPar());
				par = new Par();
				punto2 = new Punto();
		 
			} else {
				StaticUtil.errorMessage("Error", "Hubo un error al a�adir el par");
			 
			}
		}
		
	}
	
	public String agregarPar() {
		par.setParGrupo(grupoSeleccionado);
		par.setParPunto1(punto1);
		par.setParPunto2(punto2);
		Par newPar = parService.parByPuntos(punto1, punto2, grupoSeleccionado);
		if (newPar != null) {
			StaticUtil.errorMessage("Error", "El par ya existe");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.getFlash().setKeepMessages(true);
			return "pm:nuevoPar";
		}
		if (parService.savePar(par)) {
			grupoSeleccionado = new Grupo();
			StaticUtil.correctMesage("�xito",
					"Se ha a�adido correctamente el par");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.getFlash().setKeepMessages(true);
			return "pm:consultarPares";
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al a�adir el par");
			return "pm:nuevoPar";
		}
	}
 
	public void agregarParWeb() {
		System.out.println(grupoSeleccionado);
		/*if(grupoSeleccionado.getIdGrupo()==0 )
		{
			StaticUtil.errorMessage("Error", "Seleccione un grupo para los pares");
			return;
		}*/
		
		par.setParGrupo(grupoSeleccionado);
		par.setParPunto1(punto1);
		par.setParPunto2(punto2);
		Par newPar = parService.parByPuntos(punto1, punto2, grupoSeleccionado);
		if (newPar != null) {
			StaticUtil.errorMessage("Error", "El par ya existe");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.getFlash().setKeepMessages(true);
		
		clean();
		return;
		}
		if (parService.savePar(par)) {
			grupoSeleccionado = new Grupo();
			StaticUtil.correctMesage("�xito",
					"Se ha a�adido correctamente el par");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.getFlash().setKeepMessages(true);
			//RequestContext.getCurrentInstance().execute("PF('dlgAddPar').hide();");
			clean();
		} else {
			StaticUtil.errorMessage("Error", "Hubo un error al a�adir el par");
	 
		}
	}

	public String cargarPar(int id) {
		parSeleccionado = parService.parById(id);
		enfermedadesPar = parService.getEnfermedades(parSeleccionado);
		sintomasPar = parService.getSintomas(parSeleccionado);
		return "pm:detallePar?transition=flip";
	}
	
	public void cargarParWeb(int id)
	{
		parSeleccionado = parService.parById(id); 
	}
	
	public void cargarPar2(int id)
	{
		parSeleccionado = parService.parById(id);
	}

	public String toDetalle() {
		return "pm:detallePar";
	}

	public String backToDetalle() {
		enfermedadAdd = new Enfermedad();
		sintomaAdd = new Sintoma();
		enfermedadesPar = parService.getEnfermedades(parSeleccionado);
		sintomasPar = parService.getSintomas(parSeleccionado);
		par = new Par();
		par.setParPunto1(punto1);
		par.setParPunto2(punto2);
		par.setParGrupo(grupoSeleccionado);
		return "pm:detallePar";
	}

	public String addEnfermedad() {
		EnfermedadPar toAdd = new EnfermedadPar();
		toAdd.setExpEnfermedad(enfermedadAdd);
		toAdd.setExpPar(parSeleccionado);
		if (enfermedadService.saveEnfermedadPar(toAdd)) {
			StaticUtil.correctMesage("�xito",
					"Se ha a�adido correctamente la enfermedad");
			StaticUtil.keepMessages();
			enfermedadAdd = new Enfermedad();
			enfermedadesPar = parService.getEnfermedades(parSeleccionado);
			sintomasPar = parService.getSintomas(parSeleccionado);
			return "pm:detallePar";
		} else {
			StaticUtil
					.correctMesage("Error", "No se pudo a�adir la enfermedad");
			return null;
		}
	}

	public String addSintoma() {
		SintomaPar toAdd = new SintomaPar();
		toAdd.setSxpPar(parSeleccionado);
		toAdd.setSxpSintoma(sintomaAdd);
		if (enfermedadService.saveSintomaPar(toAdd)) {
			StaticUtil.correctMesage("�xito",
					"Se ha a�adido correctamente el sintoma");
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			context.getFlash().setKeepMessages(true);
			return backToDetalle();
		} else {
			StaticUtil.correctMesage("Error", "No se pudo a�adir el sintoma");
			return null;
		}
	}

	public void cargarPreRemove(int id) {
		enfermedadAdd = enfermedadService.getById(id);
	}

	public void cargarPreRemoveSintoma(int id) {
		sintomaAdd = sintomaService.getById(id);
	}

	public void removeSintomaPar() {
		sxpToDelete = enfermedadService.getByParameters(sintomaAdd,
				parSeleccionado);
		enfermedadService.deleteSintomaPar(sxpToDelete);
		sintomaAdd = new Sintoma();
	}

	public void removeEnfermedadPar() {
		expToDelete = enfermedadService.getByParameters(enfermedadAdd,
				parSeleccionado);
		enfermedadService.deleteEnfermedadPar(expToDelete);
		enfermedadAdd = new Enfermedad();
	}

	public void cancelar() {
		enfermedadAdd = new Enfermedad();
		sintomaAdd = new Sintoma();
	}

	public void cancelarPar() {
		parSeleccionado = new Par();
	}

	public void cargarRemovePar(int id) {
		parSeleccionado = parService.parById(id);
	}

	public void removePar() {
		parService.deletePar(parSeleccionado);
		parSeleccionado = new Par();
	}

	public String cargarUpdatePar(int id) {
		par = parService.parById(id);
		punto1 = par.getParPunto1();
		punto2 = par.getParPunto2();
		grupoSeleccionado = par.getParGrupo();
		return "pm:editarPar?transition=flip";
	}
	public void cargarUpdateParWeb(int id) {
		par = parService.parById(id);
		/*punto1 = par.getParPunto1();
		punto2 = par.getParPunto2();
		grupoSeleccionado = par.getParGrupo();*/
	}

	public String mergePar() {
		par.setParPunto1(punto1);
		par.setParPunto2(punto2);
		par.setParGrupo(grupoSeleccionado);
		// Par newPar = parService.parByPuntos(punto1, punto2,
		// grupoSeleccionado);
		// if (newPar != null) {
		// StaticUtil.errorMessage("Error", "El par ya existe");
		// ExternalContext context = FacesContext.getCurrentInstance()
		// .getExternalContext();
		// context.getFlash().setKeepMessages(true);
		// return null;
		// }
		parService.updatePar(par);
		par = new Par();
		return "pm:consultarPares?transition=flip";
	}
	
	public void mergeParWeb()
	{
		//par.setParPunto1(punto1);
		//par.setParPunto2(punto2);
		//par.setParGrupo(grupoSeleccionado);
		parService.updatePar(par);
		StaticUtil.correctMesage("Exito", "Se modifico los datos del par");
		clean();
		
	}

	public void filtrarBusqueda() {
		pares = parService.getAllPares();
		List<Par> filtrados = new ArrayList<>();
		for (Par p : pares) {
			if (p.getParPunto1().getNombre().toLowerCase().contains(query)
					|| p.getParPunto2().getNombre().toLowerCase()
							.contains(query)) {
				filtrados.add(p);
			}
		}
		pares = filtrados;
	}

	public void orderAscP1() {
		ascP1 = 1;
		ascP2 = 0;
		goiz = 0;
		pares = parService.getAllParesOrderByP1();
	}

	public void orderAscP2() {
		ascP1 = 0;
		ascP2 = 1;
		goiz = 0;
		pares = parService.getAllParesOrderByP2();
	}

	public void orderGoiz() {
		ascP1 = 0;
		ascP2 = 0;
		goiz = 1;
		pares = parService.getAllParesOrderGoiz();
	}

	
	public void buscar(int p1)
	{
		Punto p = new Punto();
		p.setIdPunto(p1);
		parcito=parService.paresByPunto(p);
		System.out.println("Lista : "+parcito.size());
	}

	public void insertarPar(Integer idpar)
	{
		
		par= parService.parById(idpar);
		
		result.add(par);
		npar=result;
		System.out.println(npar.size());
	}
	public List<Par> getNpar() {
		return npar;
	}

	public void setNpar(List<Par> npar) {
		this.npar = npar;
	}


	
	public void prueba()
	{
		System.out.println("si funco");
	}

	public List<Par> getParcito() {
		return parcito;
	}

	public void setParcito(List<Par> parcito) {
		this.parcito = parcito;
	}

	public List<Punto> getAllPuntos() {
		return allPuntos;
	}
	
	public void listarptos()
	{
		allPuntos = puntoService.getAllPuntos();
	}

	public void setAllPuntos(List<Punto> allPuntos) {
		this.allPuntos = allPuntos;
	}
	
}
