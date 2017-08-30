package mx.com.desoft.hidrogas.dto;

public class CatEstatusOrdenDTO {

	private int id;
	private String descripcion;

	public CatEstatusOrdenDTO() {
		super();
	}

	public CatEstatusOrdenDTO(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
