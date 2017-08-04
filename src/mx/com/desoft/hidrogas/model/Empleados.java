package mx.com.desoft.hidrogas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Profesor")
public class Empleados {
	
	@Id
	@Column(name="NoNomina")
	private int id;
	
	@Column(name="Nombre")
	private String nombre;
	
	public Empleados() {
	}

	public Empleados(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
