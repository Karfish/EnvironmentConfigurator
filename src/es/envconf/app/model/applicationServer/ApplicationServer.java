package es.envconf.app.model.applicationServer;

import es.envconf.app.model.applicationServer.dto.ApplicationServerDTO;
import es.envconf.app.model.applicationServer.exception.AppServerException;

/**
 * Interfaz creada para los servidores de aplicaciones. Todo servidor JBOSS deberá implementar esta interfaz.
 * @author Miguel Godoy
 */
public interface ApplicationServer {

    /**
     * Comprueba si el proyecto ya existe en la ruta establecida para el servidor de aplicaciones.
     * @return True si existe; false en caso contrario
     */
    public boolean isProjectExists();
    
    /**
     * Crea la carpeta en la que se encontrarán los ficheros del servidor.
     * @param appServerDTO DTO con la información necesaria para el crear el servidor de aplicaciones
     * Parámetro requerido. No debe ser nulo
     * @return Resultado de la ejecución del método
     * @throws AppServerException Se produce si ha ocurrido un error durante la creación del proyecto.
     */
    public String createProjectFolder(ApplicationServerDTO appServerDTO) throws AppServerException;
    
    /**
     * Copia los ficheros del servidor en base a un conjunto de plantillas.
     * @param appServerDTO DTO con la información necesaria para el servidor de aplicaciones
     * Parámetro requerido. No debe ser nulo
     * @return Resultado de la ejecución del método
     * @throws AppServerException Se produce si ha ocurrido un error durante la copia de ficheros.
     */
    public String copyConfigurationDirectoryTemplate(ApplicationServerDTO appServerDTO) throws AppServerException;
    
    /**
     * Crea los ficheros de configuración para el servidor.
     * @param appServerDTO DTO con la información necesaria para el servidor de aplicaciones
     * Parámetro requerido. No debe ser nulo
     * @return Resultado de la ejecución del método
     * @throws AppServerException Se produce si ha ocurrido un error durante la creación de ficheros de configuración.
     */
    public String createConfigurationFiles(ApplicationServerDTO appServerDTO) throws AppServerException;
    
    /**
     * Crea los ficheros para poder arrancar el servidor.
     * @param appServerDTO DTO con la información necesaria para el servidor de aplicaciones
     * Parámetro requerido. No debe ser nulo
     * @return Resultado de la ejecución del método
     * @throws AppServerException Se produce si ha ocurrido un error durante la creación de ficheros de configuración.
     */
    public String createRunFiles(ApplicationServerDTO appServerDTO) throws AppServerException;
    
    /**
     * Retorna la ruta donde se encuentra el fichero que arranca el servidor.
     * @return 
     */
    public String getServerRunPath();
    
    /**
     * Retorna la ruta donde se desplegará el war.
     * @return 
     */
    public String getPathWar();
    
    
}
