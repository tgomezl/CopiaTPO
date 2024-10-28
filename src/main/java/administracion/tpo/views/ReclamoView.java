package administracion.tpo.views;

import java.util.Iterator;
import java.util.List;

import administracion.tpo.modelo.*;

public class ReclamoView {

	private int numero;
	//private PersonaView usuario;
	private String dniusuario;
	//private EdificioView edificio;
	private Integer idedificio;
	private String ubicacion;
	private String descripcion;
	//private UnidadView unidad;
	private Integer idunidad;
	private Estado estado;
	private List<Imagen> imagenes;
	
	public ReclamoView() {
		
	}
	
	public ReclamoView(Reclamo rec) {
		this.numero=rec.getNumero();
		this.dniusuario=rec.getUsuario().getDocumento();
		idedificio=rec.getEdificio().getCodigo();
		ubicacion=rec.getUbicacion();
		descripcion=rec.getDescripcion();
		idunidad=rec.getUnidad().getId();
		estado=rec.getEstado();
		imagenes=rec.getImagenes();
	}

	
}
