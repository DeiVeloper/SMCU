package mx.com.desoft.hidrogas.helper;

import javafx.scene.control.ComboBox;
import mx.com.desoft.hidrogas.dto.CatEstatusOrdenDTO;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.dto.TipoReporteDTO;

public interface CatalogosHelper {

	public void llenarComboTipoReorte(ComboBox<TipoReporteDTO> combo);

	public void llenarComboOperador(ComboBox<EmpleadoDTO> combo);

	public void llenarComboEconomico(ComboBox<EconomicoDTO> combo);

	public void llenarComboEstatus(ComboBox<CatEstatusOrdenDTO> combo);

	public void llenarComboTipoNecesidad(ComboBox<CatTipoNecesidadDTO> combo);

	public void llenarComboCatTipoEmpleado(ComboBox<CatTipoEmpleadoDTO> combo);


}
