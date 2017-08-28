package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cat_tipo_lista_refaccion database table.
 * 
 */
@Entity
@Table(name="cat_tipo_lista_refaccion")
@NamedQuery(name="CatTipoListaRefaccion.findAll", query="SELECT c FROM CatTipoListaRefaccion c")
public class CatTipoListaRefaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tipo_refaccion_id")
	private int tipoRefaccionId;

	private String descripcion;

	//bi-directional many-to-one association to ListaRefaccione
	@OneToMany(mappedBy="catTipoListaRefaccion")
	private List<ListaRefacciones> listaRefacciones;

	public CatTipoListaRefaccion() {
	}

	public int getTipoRefaccionId() {
		return this.tipoRefaccionId;
	}

	public void setTipoRefaccionId(int tipoRefaccionId) {
		this.tipoRefaccionId = tipoRefaccionId;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<ListaRefacciones> getListaRefacciones() {
		return this.listaRefacciones;
	}

	public void setListaRefacciones(List<ListaRefacciones> listaRefacciones) {
		this.listaRefacciones = listaRefacciones;
	}

	public ListaRefacciones addListaRefaccione(ListaRefacciones listaRefaccione) {
		getListaRefacciones().add(listaRefaccione);
		listaRefaccione.setCatTipoListaRefaccion(this);

		return listaRefaccione;
	}

	public ListaRefacciones removeListaRefaccione(ListaRefacciones listaRefaccione) {
		getListaRefacciones().remove(listaRefaccione);
		listaRefaccione.setCatTipoListaRefaccion(null);

		return listaRefaccione;
	}

}