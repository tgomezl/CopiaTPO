package administracion.tpo.dao;

import administracion.tpo.modelo.Edificio;
import administracion.tpo.modelo.Reclamo;
import administracion.tpo.modelo.Unidad;
import administracion.tpo.repository.IRepositoryEdificio;
import administracion.tpo.repository.IRepositoryReclamo;
import administracion.tpo.repository.IRepositoryUnidad;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public class UnidadDAO {
    private static UnidadDAO instance;
    
    //@Autowired
    //private IRepositoryEdificio edificiorepo;

    private UnidadDAO(){
    }

    public static UnidadDAO getInstance() {
        if(instance==null){
            instance = new UnidadDAO();
        }
        return instance;
    }

    public List<Unidad> getAll(IRepositoryUnidad iRepositoryUnidad){
        return iRepositoryUnidad.findAll();
    }
    public Optional<Unidad> getById(int numero, IRepositoryUnidad iRepositoryUnidad){
        return iRepositoryUnidad.findById(numero);
    }

    public void save(Unidad unidad, IRepositoryUnidad iRepositoryUnidad){
        iRepositoryUnidad.save(unidad);
    }

    public void delete(Unidad unidad, IRepositoryUnidad iRepositoryUnidad){
    	//ACA TENGO ACCESO A LA UNIDAD COMPLETA!!!!
    	//(liberar aca?)
    	//
        iRepositoryUnidad.delete(unidad);
    }
    
    public void delete(Integer idunidad, IRepositoryUnidad iRepositoryUnidad){
        iRepositoryUnidad.deleteById(idunidad);
    }
    
    public void update(Unidad unidad, IRepositoryUnidad iRepositoryUnidad){
        if (iRepositoryUnidad.existsById(unidad.getId())) {
        	iRepositoryUnidad.save(unidad);
        }
    }

	public Unidad crearUnidad(int idedificio, Unidad uni, IRepositoryUnidad repositoriounidad,
			IRepositoryEdificio repositoryEdificio) {
		// TODO Auto-generated method stub
		if(repositoryEdificio.existsById(idedificio)) {
			Edificio edi=repositoryEdificio.findById(idedificio).get();
			uni.setEdificio(edi);
			uni.setHabitado(false);
			Unidad creada=repositoriounidad.save(uni);
			return creada;
		}else {
			System.out.println(" no existe ese edificio");
			return null;
		}
		
	}


}
