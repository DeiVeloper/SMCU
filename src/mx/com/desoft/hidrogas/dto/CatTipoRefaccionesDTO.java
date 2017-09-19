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

	public CatTipoRefaccionesDTO() {
	}

	public CatTipoRefaccionesDTO(int tipoRefaccionId, String descripcion) {
		this.tipoRefaccionId = tipoRefaccionId;
		this.descripcion = descripcion;
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

}
