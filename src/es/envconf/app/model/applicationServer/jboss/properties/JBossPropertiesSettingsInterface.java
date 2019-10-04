package es.envconf.app.model.applicationServer.jboss.properties;

import es.envconf.app.model.properties.PropertiesSettingsException;

/**
 * Interfaz con los métodos comunes para cada configuración de propiedades de cada servidor JBOSS
 * @author Miguel Godoy
 */
public interface JBossPropertiesSettingsInterface {
   
    /**
     * Retorna la ruta raíz del servidor de aplicaciones JBOSS
     * donde se crearán los diferentes servidores para los proyectos.
     * @return 
     * @throws PropertiesSettingsException Se produce cuando no se ha podido recuperar la propiedad del fichero de configuración
     */
    public String getPropertyPathRoot() throws PropertiesSettingsException;
    
    /**
     * Retorna la ruta en la que se encuentra la carpeta correspondiente al
     * servidor "base" la cual será copiada por cada proyecto
     * @return 
     * @throws PropertiesSettingsException Se produce cuando no se ha podido recuperar la propiedad del fichero de configuración
     */
    public String getPropertyPathFiles()throws PropertiesSettingsException;
    
    /**
     * Retorna la ruta donde se encuentran los ficheros "plantilla" del servidor en cuestión.
     * Estos ficheros serán los que se modifiquen para contener la configuración de cada proyecto.
     * de configuración del servidor.
     * @return 
     * @throws PropertiesSettingsException Se produce cuando no se ha podido recuperar la propiedad del fichero de configuración
     */
    public String getPropertyPathFileTemplates() throws PropertiesSettingsException;
}
