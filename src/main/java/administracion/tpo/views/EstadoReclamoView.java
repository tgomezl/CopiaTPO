package administracion.tpo.views;

import administracion.tpo.modelo.Estado;

public class EstadoReclamoView {
	Integer idreclamo;
	String estadoreclamo;
	Estado estado;
	
	public EstadoReclamoView() {
		
	}
	public Integer getIdreclamo() {
		return idreclamo;
	}
	public void setIdreclamo(Integer idreclamo) {
		this.idreclamo = idreclamo;
	}
	public String getEstadoreclamo() {
		return estadoreclamo;
	}
	public void setEstadoreclamo(String estadoreclamo) {
		this.estadoreclamo = estadoreclamo;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
