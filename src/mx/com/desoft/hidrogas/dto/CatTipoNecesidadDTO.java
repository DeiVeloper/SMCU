package mx.com.desoft.hidrogas.dto;

public class CatTipoNecesidadDTO {

	private Long tipoNecesidadId;
    private String descripcion;

    public CatTipoNecesidadDTO() {
	}

	public CatTipoNecesidadDTO(Long tipoNecesidadId, String descripcion) {
        this.tipoNecesidadId = tipoNecesidadId;
        this.descripcion = descripcion;
    }

	public CatTipoNecesidadDTO(String descripcion) {
        this.descripcion = descripcion;
    }

	public Long getTipoNecesidadId() {
		return tipoNecesidadId;
	}

	public void setTipoNecesidadId(Long tipoNecesidadId) {
		this.tipoNecesidadId = tipoNecesidadId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}
