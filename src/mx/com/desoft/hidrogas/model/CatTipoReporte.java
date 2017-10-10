package mx.com.desoft.hidrogas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the cat_tipo_necesidad database table.
 *
 */
@Entity
@Table(name="cat_tipo_reportes")
public class CatTipoReporte {

	private Integer tipoReporteId;
	private String descripcion;

	public CatTipoReporte() {
	}

	public CatTipoReporte(Integer tipoReporteId, String descripcion) {
		this.tipoReporteId = tipoReporteId;
		this.descripcion = descripcion;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tipo_reporte_id")
	public Integer getTipoReporteId() {
		return tipoReporteId;
	}

	public void setTipoReporteId(Integer tipoReporteId) {
		this.tipoReporteId = tipoReporteId;
	}

	@Column(name="descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



}
