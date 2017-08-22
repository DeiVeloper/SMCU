package mx.com.desoft.hidrogas;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
 
/**
 * Clase que cada cierto tiempo reporta que la aplicaci&oacute;n esta viva.
 * @author ManuelFrancisco
 */
@Component
public class ApplicationReporter {
     
    /**
     * Logger para la clase.
     */
    private static final Logger LOGGER =
        Logger.getLogger(ApplicationReporter.class);
     
    //@Scheduled(fixedDelay = 10000)
    public void reportApplication() {
        LOGGER.info("La aplicacion esta corriendo.");
    }
}
