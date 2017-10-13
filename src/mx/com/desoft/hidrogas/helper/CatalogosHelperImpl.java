package mx.com.desoft.hidrogas.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import mx.com.desoft.hidrogas.business.IAdministrarOrdenBusiness;
import mx.com.desoft.hidrogas.business.ICatalogoBusiness;
import mx.com.desoft.hidrogas.dto.CatEstatusOrdenDTO;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.dto.TipoReporteDTO;

@Component
public class CatalogosHelperImpl implements CatalogosHelper{

	@Autowired
	private ICatalogoBusiness catalogoBusinessImpl;

	@Autowired
	private IAdministrarOrdenBusiness administrarOrdenBusinessImpl;

	@Override
	public void llenarComboTipoReorte(ComboBox<TipoReporteDTO> combo) {
		combo.setItems(FXCollections.observableArrayList(catalogoBusinessImpl.findAllTipoReporte()));
		combo.setConverter(new StringConverter<TipoReporteDTO>() {
			@Override
			public String toString(TipoReporteDTO tipo) {
				if (tipo == null) {
					return null;
				} else {
					return tipo.getDescripcion();
				}
			}

			@Override
			public TipoReporteDTO fromString(String string) {
				return null;
			}
		});
	}

	@Override
	public void llenarComboOperador(ComboBox<EmpleadoDTO> combo) {
		combo.setItems(FXCollections.observableArrayList(catalogoBusinessImpl.findAllOperadores()));
		combo.setConverter(new StringConverter<EmpleadoDTO>() {
			@Override
			public String toString(EmpleadoDTO tipo) {
				if (tipo == null) {
					return null;
				} else {
					return tipo.getNominaEmpleado().toString().concat(" - ").concat(tipo.getNombreEmpleado())
							.concat(" ").concat(tipo.getApellidoPatEmpleado()).concat(" ")
							.concat(tipo.getApellidoMatEmpleado()).toString();
				}
			}

			@Override
			public EmpleadoDTO fromString(String string) {
				return null;
			}
		});
	}

	@Override
	public void llenarComboEconomico(ComboBox<EconomicoDTO> combo) {
		combo.setItems(FXCollections.observableArrayList(catalogoBusinessImpl.findAllEconomicos()));
		combo.setConverter(new StringConverter<EconomicoDTO>() {
			@Override
			public String toString(EconomicoDTO tipo) {
				if (tipo == null) {
					return null;
				} else {
					return tipo.getEconomicoId().toString();
				}
			}

			@Override
			public EconomicoDTO fromString(String string) {
				return null;
			}
		});
	}

	@Override
	public void llenarComboEstatus(ComboBox<CatEstatusOrdenDTO> combo) {
		combo.setItems(FXCollections.observableArrayList(administrarOrdenBusinessImpl.buscarTiposEstatus()));
		combo.setConverter(new StringConverter<CatEstatusOrdenDTO>() {
			@Override
			public String toString(CatEstatusOrdenDTO tipo) {
				if (tipo == null) {
					return null;
				} else {
					return tipo.getDescripcion();
				}
			}

			@Override
			public CatEstatusOrdenDTO fromString(String string) {
				return null;
			}
		});
	}

	@Override
	public void llenarComboTipoNecesidad(ComboBox<CatTipoNecesidadDTO> combo) {
		combo.setItems(FXCollections.observableArrayList(catalogoBusinessImpl.findAllTipoNecesidad()));
		combo.setConverter(new StringConverter<CatTipoNecesidadDTO>() {
			@Override
			public String toString(CatTipoNecesidadDTO tipo) {
				if (tipo == null) {
					return null;
				} else {
					return tipo.getDescripcion().toString();
				}
			}

			@Override
			public CatTipoNecesidadDTO fromString(String string) {
				return null;
			}
		});
	}

	@Override
	public void llenarComboCatTipoEmpleado(ComboBox<CatTipoEmpleadoDTO> combo) {
		combo.setItems(FXCollections.observableArrayList(catalogoBusinessImpl.findAllTipoEmpleados()));
    	combo.setConverter(new StringConverter<CatTipoEmpleadoDTO>() {
    		@Override
            public String toString(CatTipoEmpleadoDTO tipo) {
            	if (tipo == null) {
            		return null;
            	} else {
            		return tipo.getDescripcion();
            	}
            }

            @Override
            public CatTipoEmpleadoDTO fromString(String string) {
                   return null;
            }
    	});

	}

}