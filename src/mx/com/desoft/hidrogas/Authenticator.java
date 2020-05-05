package mx.com.desoft.hidrogas;

import java.io.Serializable;

import mx.com.desoft.hidrogas.dto.EmpleadoDTO;

public class Authenticator implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1044512398864299120L;
	private static EmpleadoDTO usuarioSesion;
	
	public static EmpleadoDTO getUsuarioSesion() {
		return usuarioSesion;
	}
	public static void setUsuarioSesion(EmpleadoDTO usuarioSesion) {
		Authenticator.usuarioSesion = usuarioSesion;
	}

}
