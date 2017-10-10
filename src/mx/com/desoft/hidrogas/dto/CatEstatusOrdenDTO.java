package mx.com.desoft.hidrogas.dto;

public class CatEstatusOrdenDTO {

	private Integer id;
	private String descripcion;

	public CatEstatusOrdenDTO() {
		super();
	}

	public CatEstatusOrdenDTO(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
