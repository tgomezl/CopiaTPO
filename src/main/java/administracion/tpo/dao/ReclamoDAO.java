package administracion.tpo.dao;


import administracion.tpo.modelo.Edificio;
import administracion.tpo.modelo.Imagen;
import administracion.tpo.modelo.Persona;
import administracion.tpo.modelo.Reclamo;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.repository.IRepositoryImagen;
import administracion.tpo.repository.IRepositoryPersona;
import administracion.tpo.repository.IRepositoryReclamo;
import administracion.tpo.repository.IRepositoryUnidad;
import administracion.tpo.views.CrearReclamoView;

import java.util.List;
import java.util.Optional;

public class ReclamoDAO {
    private static ReclamoDAO instance;


    private ReclamoDAO() {
    }

    public static ReclamoDAO getInstance() {
        if (instance == null) {
            instance = new ReclamoDAO();
        }
        return instance;
    }
    public List<Reclamo> getAll(IRepositoryReclamo iRepositoryReclamo){
        return iRepositoryReclamo.findAll();
    }
    public Optional<Reclamo> getById(int numero, IRepositoryReclamo iRepositoryReclamo){
        return iRepositoryReclamo.findById(numero);
    }

    public void save(Reclamo reclamo, IRepositoryReclamo iRepositoryReclamo){
        iRepositoryReclamo.save(reclamo);
    }

    public void delete(int numero, IRepositoryReclamo iRepositoryReclamo){
        iRepositoryReclamo.deleteById(numero);
    }
    
    public void update(Reclamo reclamo, IRepositoryReclamo iRepositoryReclamo){
        if (iRepositoryReclamo.existsById(reclamo.getNumero())) {
        	iRepositoryReclamo.save(reclamo);
        }
    }

	public Reclamo crearReclamo(CrearReclamoView crearReclamo, IRepositoryEdificio repoedi,
			IRepositoryReclamo repositorioreclamo, IRepositoryPersona repopersona, IRepositoryUnidad repounidad) {
		//todo tiene que existir
		
		Persona per=buscarPersona(repopersona, crearReclamo.getDniPersona());
		Unidad uni=buscarUnidad(repounidad, crearReclamo.getIdunidad());
		Edificio edi=buscarEdificio(repoedi, crearReclamo.getIdedificio());
	
		
		boolean bandera = checkHabilitadoParaCrearReclamo(per, uni);
		if(bandera) {
			Reclamo reclamo=new Reclamo();
			reclamo.setDescripcion(crearReclamo.getDescripcion());
			reclamo.setUbicacion(crearReclamo.getUbicacion());
			//aqui!!!!!
			reclamo.setUsuario(per);
			reclamo.setEdificio(edi);
			reclamo.setUnidad(uni);
			return repositorioreclamo.save(reclamo);
			
		}
		System.out.println("                      **************************");
		System.out.println("    id unidad: "+uni.getId()+"*************************");
		System.out.println("    persona: "+per.getNombre() +"**************************");
		System.out.println("esa persona no tiene permiso para reclamar sobre esa unidad");
		return null;
		
		
	}

	private Edificio buscarEdificio(IRepositoryEdificio repoedi, Integer idedificio) {
		// TODO Auto-generated method stub
		Optional<Edificio> edi=repoedi.findById(idedificio);
		if(edi.isPresent()) {
			return edi.get();
		}
		System.out.println("        ");
		System.out.println("       ");
		System.out.println("       ");
		System.out.println("        el edificio no existe en la bbd");
		return null;
	}

	private Unidad buscarUnidad(IRepositoryUnidad repounidad, Integer idunidad) {
		// TODO Auto-generated method stub
		Optional<Unidad> uni=repounidad.findById(idunidad);
		if(uni.isPresent()) {
			return uni.get();
		}
		System.out.println("        ");
		System.out.println("       ");
		System.out.println("       ");
		System.out.println("        la unidad no existe en la bbd");
		return null;
	}

	private Persona buscarPersona(IRepositoryPersona repopersona, String dnipersona) {
		// TODO Auto-generated method stub
		Optional<Persona> per=repopersona.findById(dnipersona);
		if(per.isPresent()) {
			return per.get();
		}
		System.out.println("        ");
		System.out.println("       ");
		System.out.println("       ");
		System.out.println("        la persona no existe en la bbd");
		return null;
	}

	private boolean checkHabilitadoParaCrearReclamo(Persona persona, Unidad unidad) {
		// TODO Auto-generated method stub
		if(unidad.getInquilinos().contains(persona)) {
			System.out.println(" es inquilino");
			return true;
		}
		if(unidad.getDuenios().contains(persona)) {
			System.out.println(" es dueño");
			return true;
		}
		System.out.println(" NO es dueño ni inquilino");
		return false;
		
	}
    
    


}
