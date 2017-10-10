package mx.com.desoft.hidrogas.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class TipoReporteDTO  implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -4127494576813322775L;

	private Integer idReporte;
	private String descripcion;

	public TipoReporteDTO() {
	}

	public TipoReporteDTO(Integer idReporte, String descripcion) {
		this.idReporte = idReporte;
		this.descripcion = descripcion;
	}


	public Integer getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Integer idReporte) {
		this.idReporte = idReporte;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}