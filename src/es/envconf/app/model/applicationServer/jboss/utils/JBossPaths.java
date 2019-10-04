package es.envconf.app.model.applicationServer.jboss.utils;

import es.envconf.app.common.utils.TextUtils;
import es.envconf.app.model.applicationServer.exception.AppServerException;
import es.envconf.app.model.applicationServer.jboss.JBossVersion;
import es.envconf.app.model.applicationServer.jboss.properties.JBossPropertiesSettingsFactory;
import es.envconf.app.model.applicationServer.jboss.properties.JBossPropertiesSettingsInterface;
import es.envconf.app.model.properties.GeneralPropertiesSettings;
import es.envconf.app.model.properties.PropertiesSettingsException;

/**
 * Clase que contiene las rutas esenciales de los ficheros del servidor de aplicaciones.
 * @author Miguel Godoy
 */
public class JBossPaths {
    
    private String nameProject        = ""; // Cadena con el nombre del proyecto
    private String pathRootJBoss      = ""; // Cadena con la ruta raíz del servidor de aplicaciones
    private String templateFiles      = ""; // Cadena con la ruta donde se encuentran los ficheros que se utilizan como plantillas.
    private String pathFilesAppServer = "";
    
    /**
     * Constructor de la clase
     * @param nameProject Nombre del proyecto
     * @param jBossVersion Versión de JBOSS
     * @throws AppServerException Se puede producir si no se han podido recuperar las rutas necesarias a los ficheros
     * donde se configurará JBoss
     */
    public JBossPaths(String nameProject, JBossVersion jBossVersion) throws AppServerException {
        if( TextUtils.esNuloVacio(nameProject) || jBossVersion == null )
            throw new IllegalArgumentException("No se puede instanciar el objeto si el nombre de proyecto recibido o la versión de servidor es nula");

        JBossPropertiesSettingsInterface jBossPropertiesSettings = JBossPropertiesSettingsFactory.getJBossPropertiesSettings(jBossVersion);
        this.nameProject        = nameProject;
        try{
            this.pathRootJBoss      = jBossPropertiesSettings.getPropertyPathRoot();
            this.templateFiles      = jBossPropertiesSettings.getPropertyPathFileTemplates();
            this.pathFilesAppServer = jBossPropertiesSettings.getPropertyPathFiles();
        }catch(PropertiesSettingsException ex){
            throw new AppServerException(ex);
        }
    }
    
    /**
     * Retorna la ruta raíz donde se ubica el servidor
     * @return 
     */
    public String getPathAplicationServer(){
        return this.pathRootJBoss;
    }
    
    /**
     * Retorna la ruta donde se encuentran los ficheros "plantilla" que se usarán para generar
     * los ficheros de configuración de cada servidor JBoss
     * @return 
     */
    public String getPathTemplateFiles(){
        return this.templateFiles;
    } 
    
    /**
     * Retorna la ruta donde se alojan los diferentes servidores de cada proyecto
     * @return 
     */
    public String getPathProjectsInAplicationServer(){
        return new StringBuilder(pathRootJBoss).append("/projects/").toString();
    }
    
    /**
     * 
     * @return 
     */
    public String getPathFilesAppServer(){
       return this.pathFilesAppServer;
    }
    
    /**
     * Retorna la ruta del servidor del proyecto en cuestión.
     * @return 
     */
    public String getPathProject(){
        return getPathProjectsInAplicationServer() + nameProject;
    }

    /**
     * Retorna la ruta completa donde se debe crear el fichero standalone_nameProject.bat donde
     * nameProject será sustituido por el nombre de proyecto que creemos
     * @return 
     */
    public String getPathDestinationStandaloneBat(){
        String pathStandaloneBatInServer = "/bin/standaloneBat/standalone_nameProject.bat".replaceAll("nameProject", this.nameProject);
        String string = new StringBuilder().append(getPathAplicationServer()).append(pathStandaloneBatInServer).toString();
        return string;
    }

    /**
     * Retorna la ruta completa donde se debe crear el fichero standalone.xml.
     * @return 
     */
    public String getPathDestinationStandaloneXML(){
        String pathStandaloneXMLInServer = "/projects/nameProject/configuration/standalone.xml".replaceAll("nameProject", this.nameProject);
        String string = new StringBuilder().append(getPathAplicationServer()).append(pathStandaloneXMLInServer).toString();
        return string;
    }

    /**
     * Retorna la ruta completa donde se debe crear el fichero _run_nameProject.bat donde nameProject
     * será sustituido por el nombre del proyecto en cuestión.
     * @return 
     */
    public String getPathDestinationRunBat(){
        String pathStandaloneXMLInServer = "/bin/_run_nameProject.bat".replaceAll("nameProject", this.nameProject);
        String string = new StringBuilder().append(getPathAplicationServer()).append(pathStandaloneXMLInServer).toString();
        return string;
    } 
    
    /**
     * Ruta donde se encuentra el fichero standalone.bat que se usará como plantilla.
     * @return 
     */
    public String getPathTemplateStandaloneBat(){
        String string = new StringBuilder().append(getPathTemplateFiles()).append("/standalone.bat").toString();
        return string;
    }
 
   /**
     * Ruta donde se encuentra el fichero standalone.xml que se usará como plantilla.
     * @return 
     */
    public String getPathTemplateStandaloneXML(){
        String string = new StringBuilder().append(getPathTemplateFiles()).append("/standalone.xml").toString();
        return string;
    }  
 
   /**
     * Ruta donde se encuentra el fichero run_standalone.bat que se usará como plantilla.
     * @return 
     */
    public String getPathTemplateRunBat(){
        String string = new StringBuilder().append(getPathTemplateFiles()).append("/run_standalone.bat").toString();
        return string;
    }  
    
   /**
     * Ruta del proyecto donde se desplegarán los war
     * @return 
     */
    public String getPathHCISWar(){
        String string = new StringBuilder().append(getPathAplicationServer()).append("/projects/").append(this.nameProject).append("/deployments/hcis.ear/web.war").toString();
        return string;
    }
    
    /**
     * Ruta donde se encuentra el JDK instalado
     * @return
     * @throws AppServerException Se produce cuando no se ha podido recuperar la propiedad donde se define donde se encuentra la JDK
     */
    public String getPathJDK() throws AppServerException{
        try{
            return GeneralPropertiesSettings.getInstance().getPathJDK();
        }catch(PropertiesSettingsException ex){
            throw new AppServerException(ex);
        }
    }
    
}
