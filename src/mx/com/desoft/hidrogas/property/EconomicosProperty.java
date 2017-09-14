package mx.com.desoft.hidrogas.property;

import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class EconomicosProperty {

	private StringProperty economico;
	private StringProperty chofer;
	private StringProperty ayudante;
	private Button botonEditar;
	private Button botonBorrar;
	
	public EconomicosProperty() {
	}

	public StringProperty getEconomico() {
		return economico;
	}

	public void setEconomico(StringProperty economico) {
		this.economico = economico;
	}

	public StringProperty getChofer() {
		return chofer;
	}

	public void setChofer(StringProperty chofer) {
		this.chofer = chofer;
	}

	public StringProperty getAyudante() {
		return ayudante;
	}

	public void setAyudante(StringProperty ayudante) {
		this.ayudante = ayudante;
	}

	

	public Button getBotonBorrar() {
		return botonBorrar;
	}

	public void setBotonBorrar(Button botonBorrar) {
		this.botonBorrar = botonBorrar;
	}

	public Button getBotonEditar() {
		return botonEditar;
	}

	public void setBotonEditar(Button botonEditar) {
		this.botonEditar = botonEditar;
	}
	
}
