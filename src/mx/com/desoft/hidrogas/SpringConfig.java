package mx.com.desoft.hidrogas;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
//import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * Leemos los archivos de configuraci&oacute;n.
 */
@ImportResource(value = {
    "classpath:ApplicationContext.xml"
})
/*
 * Cargamos los beans desde los paquetes.
 */
@ComponentScan(basePackages = {"mx.com.desoft.hidrogas"},
    excludeFilters = {
        @ComponentScan.Filter(Configuration.class)})
/*
 * Marcamos el bean como un bean de configuraci&oacute;n.
 */
@Configuration
/*
 * Habilita el scheduling en la aplicaci&oacute;n.
 */
//@EnableScheduling
/**
 * Clase que carga el contexto de Spring.
 */
public class SpringConfig { 
	
	     
    /**
     * Logger para la clase.
     */
    private static final Logger LOGGER = Logger.getLogger(SpringConfig.class);
 
    @PostConstruct
    public void init() {
        LOGGER.info("Ha iniciado el contexto spring.");
    }
     
    @PreDestroy
    public void destroy() {
        LOGGER.info("El contexto esta a punto de ser destruido.");
    }
}

