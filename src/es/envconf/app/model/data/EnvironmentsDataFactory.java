package es.envconf.app.model.data;

import es.envconf.app.model.data.builder.EnvironmentsBuilder;
import es.envconf.app.model.data.builder.impl.EnvironmentsBuilderImpl;
import es.envconf.app.model.data.dao.EnvironmentsDAO;
import es.envconf.app.model.data.dao.exception.EnvironmentsDAOException;
import es.envconf.app.model.data.dao.impl.EnvironmentsDAOImplJson;
import es.envconf.app.model.service.exception.EnvironmentException;

/**
 * Clase que aplica el patrón factoria para instanciar los objetos
 * @author Miguel
 */
public class EnvironmentsDataFactory {

    /**
     * Instanciación de la implementación de la interfaz EnvironmentsDAO
     * @return EnvironmentsDAO
     * @throws EnvironmentException Se produce cuando ha habido un error al recuperar
     * los entornos
     */
    public static EnvironmentsDAO getEnvironmentDAO() throws EnvironmentsDAOException {
        return new EnvironmentsDAOImplJson();
    }

    /**
     * Instanciación de la implementación de la interfaz EnvironmentsBuilder
     * @return
     */
    public static EnvironmentsBuilder getEnvironmentsBuilder(){
        return new EnvironmentsBuilderImpl();
    }

}
