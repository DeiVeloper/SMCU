package mx.com.desoft.hidrogas.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the seguimiento_orden database table.
 *
 */
@Entity
@Table(name="seguimiento_orden")
@NamedQuery(name="SeguimientoOrden.findAll", query="SELECT s FROM SeguimientoOrden s")
public class SeguimientoOrden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_seguimiento;

	//bi-directional one-to-one association to OrdenTrabajo
	@OneToOne
	@JoinColumn(name="folio", referencedColumnName = "folio", nullable = false)
	private OrdenTrabajo ordenTrabajo;

	@Column(name="trabajos_realizados")
	private String trabajosRealizados;

	@Column(name="observaciones")
	private String observaciones;

	@Column(name="reparacion_mayor")
	private int reparacionMayor;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_repara_mayor")
	private Date fechaReparaMayor;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="nomina_registro")
	private int nominaRegistro;
        
        @Column(name="descripcion_pu")
	private String descripcionPu;
        
        @Column(name="descripcion_ps")
	private String descripcionPs;

	//bi-directional many-to-one association to SeguimientosEmpleado
//	@OneToMany(mappedBy="seguimientoOrden")
//	private List<SeguimientosEmpleado> seguimientosEmpleados;

	public SeguimientoOrden() {
	}

	public SeguimientoOrden(int id_seguimiento, OrdenTrabajo ordenTrabajo, String trabajosRealizados, String observaciones,
			int reparacionMayor, Date fechaReparaMayor, Date fechaRegistro, int nominaRegistro) {
		super();
		this.id_seguimiento = id_seguimiento;
		this.ordenTrabajo = ordenTrabajo;
		this.trabajosRealizados = trabajosRealizados;
		this.observaciones = observaciones;
		this.reparacionMayor = reparacionMayor;
		this.fechaReparaMayor = fechaReparaMayor;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
	}

	public SeguimientoOrden(OrdenTrabajo ordenTrabajo, String trabajosRealizados, String observaciones,
			int reparacionMayor, Date fechaReparaMayor, Date fechaRegistro, int nominaRegistro) {
		super();
		this.ordenTrabajo = ordenTrabajo;
		this.trabajosRealizados = trabajosRealizados;
		this.observaciones = observaciones;
		this.reparacionMayor = reparacionMayor;
		this.fechaReparaMayor = fechaReparaMayor;
		this.fechaRegistro = fechaRegistro;
		this.nominaRegistro = nominaRegistro;
	}

        public SeguimientoOrden(int id_seguimiento, OrdenTrabajo ordenTrabajo, String trabajosRealizados, String observaciones, int reparacionMayor, Date fechaReparaMayor, Date fechaRegistro, int nominaRegistro, String descripcionPu, String descripcionPs) {
            this.id_seguimiento = id_seguimiento;
            this.ordenTrabajo = ordenTrabajo;
            this.trabajosRealizados = trabajosRealizados;
            this.observaciones = observaciones;
            this.reparacionMayor = reparacionMayor;
            this.fechaReparaMayor = fechaReparaMayor;
            this.fechaRegistro = fechaRegistro;
            this.nominaRegistro = nominaRegistro;
            this.descripcionPu = descripcionPu;
            this.descripcionPs = descripcionPs;
        }
        
   

	public int getId_seguimiento() {
		return id_seguimiento;
	}

	public void setId_seguimiento(int id_seguimiento) {
		this.id_seguimiento = id_seguimiento;
	}

        
	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaReparaMayor() {
		return this.fechaReparaMayor;
	}

	public void setFechaReparaMayor(Date fechaReparaMayor) {
		this.fechaReparaMayor = fechaReparaMayor;
	}

	public int getNominaRegistro() {
		return this.nominaRegistro;
	}

	public void setNominaRegistro(int nominaRegistro) {
		this.nominaRegistro = nominaRegistro;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getReparacionMayor() {
		return this.reparacionMayor;
	}

	public void setReparacionMayor(int reparacionMayor) {
		this.reparacionMayor = reparacionMayor;
	}

	public String getTrabajosRealizados() {
		return this.trabajosRealizados;
	}

	public void setTrabajosRealizados(String trabajosRealizados) {
		this.trabajosRealizados = trabajosRealizados;
	}

	public OrdenTrabajo getOrdenTrabajo() {
		return this.ordenTrabajo;
	}

	public void setOrdenTrabajo(OrdenTrabajo ordenTrabajo) {
		this.ordenTrabajo = ordenTrabajo;
	}
        
        /**
         * @return the descripcionPu
         */
        public String getDescripcionPu() {
            return descripcionPu;
        }

        /**
         * @param descripcionPu the descripcionPu to set
         */
        public void setDescripcionPu(String descripcionPu) {
            this.descripcionPu = descripcionPu;
        }

        /**
         * @return the descripcionPs
         */
        public String getDescripcionPs() {
            return descripcionPs;
        }

        /**
         * @param descripcionPs the descripcionPs to set
         */
        public void setDescripcionPs(String descripcionPs) {
            this.descripcionPs = descripcionPs;
        }


//	public List<SeguimientosEmpleado> getSeguimientosEmpleados() {
//		return this.seguimientosEmpleados;
//	}
//
//	public void setSeguimientosEmpleados(List<SeguimientosEmpleado> seguimientosEmpleados) {
//		this.seguimientosEmpleados = seguimientosEmpleados;
//	}

//	public SeguimientosEmpleado addSeguimientosEmpleado(SeguimientosEmpleado seguimientosEmpleado) {
//		getSeguimientosEmpleados().add(seguimientosEmpleado);
//		seguimientosEmpleado.setSeguimientoOrden(this);
//
//		return seguimientosEmpleado;
//	}
//
//	public SeguimientosEmpleado removeSeguimientosEmpleado(SeguimientosEmpleado seguimientosEmpleado) {
//		getSeguimientosEmpleados().remove(seguimientosEmpleado);
//		seguimientosEmpleado.setSeguimientoOrden(null);
//
//		return seguimientosEmpleado;
//	}

}