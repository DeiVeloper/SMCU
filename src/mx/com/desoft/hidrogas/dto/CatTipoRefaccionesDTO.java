package mx.com.desoft.hidrogas.dto;

import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component
public class CatTipoRefaccionesDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int tipoRefaccionId;
	private String descripcion;
	private int cantidad;

	public CatTipoRefaccionesDTO() {
	}

	public CatTipoRefaccionesDTO(int tipoRefaccionId, String descripcion, int cantidad) {
		this.tipoRefaccionId = tipoRefaccionId;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public CatTipoRefaccionesDTO(String descripcion, int cantidad) {
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}
	
	public CatTipoRefaccionesDTO(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTipoRefaccionId() {
		return tipoRefaccionId;
	}

	public void setTipoRefaccionId(int tipoRefaccionId) {
		this.tipoRefaccionId = tipoRefaccionId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
