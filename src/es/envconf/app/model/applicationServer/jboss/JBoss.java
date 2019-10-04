package es.envconf.app.model.applicationServer.jboss;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.envconf.app.common.utils.CadenasBusquedaReemplazo;
import es.envconf.app.common.utils.FileUtils;
import es.envconf.app.model.applicationServer.ApplicationServer;
import es.envconf.app.model.applicationServer.dto.ApplicationServerDTO;
import es.envconf.app.model.applicationServer.exception.AppServerException;
import es.envconf.app.model.applicationServer.jboss.utils.JBossPaths;


/**
 * Clase implementación del servidor de aplicaciones JBOSS
 * @author Miguel Godoy
 */
public class JBoss implements ApplicationServer {
    
    private JBossPaths paths = null;
    
    /**
     * Constructor por defecto
     * @param nameProject Nombre del entorno
     * @param jbossVersion Versión de JBoss a utilizar
     * @throws AppServerException Se produce cuando ha habido algún problema al recuperar las rutas necesarias
     * de configuración de JBoss
     */
    public JBoss(String nameProject, JBossVersion jbossVersion) throws AppServerException {
        paths = new JBossPaths(nameProject, jbossVersion);
    }
    
    /**
     * Comprueba si el proyecto ya existe en la ruta establecida para el servidor de aplicaciones.
     * @return True si existe; false en caso contrario
     */
    @Override
    public boolean isProjectExists(){
        return FileUtils.existsFolder(paths.getPathProject());
    }
    
    /**
     * Crea la carpeta en la que se encontrarán los ficheros del servidor.
     * @param appServerDTO DTO con la información necesaria para el crear el servidor de aplicaciones
     * Parámetro requerido. No debe ser nulo
     * @return Resultado de la ejecución del método
     * @throws AppServerException Se produce si ha ocurrido un error durante la creación del proyecto.
     */    
    @Override
    public String createProjectFolder(ApplicationServerDTO appServerDTO)throws AppServerException {
       if( appServerDTO == null )
           throw new IllegalArgumentException("No se puede crear el directorio para el servidor debido a que el parámetro recibido es nulo.");
        try {
            String resultado = "Instancia creada"; 
            FileUtils.createFolder(paths.getPathProjectsInAplicationServer(), appServerDTO.getProject());
            return resultado;
        } catch (Exception e) {
            throw new AppServerException("Error al crear el proyecto " + appServerDTO.getProject(), e);
        }
    }
    
    /**
     * Copia los ficheros del servidor en base a un conjunto de plantillas.
     * @param appServerDTO DTO con la información necesaria para el servidor de aplicaciones
     * Parámetro requerido. No debe ser nulo
     * @return Resultado de la ejecución del método
     * @throws AppServerException Se produce si ha ocurrido un error durante la copia de ficheros.
     */    
    @Override
    public String copyConfigurationDirectoryTemplate(ApplicationServerDTO appServerDTO) throws AppServerException {
       if( appServerDTO == null )
           throw new IllegalArgumentException("No se puede copiar la carpeta de configuración debido a que el parámetro recibido es nulo.");
        try {
            String dirOrigen  = paths.getPathFilesAppServer(); 
            File fileOrigen   = new File(dirOrigen);
            String dirDestino = paths.getPathProjectsInAplicationServer() + "/" + appServerDTO.getProject();
            File fileDestino  = new File(dirDestino);

            FileUtils.copyDirectoryComplete(fileOrigen, fileDestino);
            StringBuilder resultado = new StringBuilder();
            resultado.append("Copiado directorio template de JBoss de ").append(dirOrigen).append(" a ").append(dirDestino);
            return resultado.toString();
        } catch (Exception ex) {
            throw new AppServerException("Error al copiar los ficheros de configuracion del servidor del proyecto " + appServerDTO.getProject(), ex);
        }   
    }

    
    /**
     * Crea los ficheros de configuración para el servidor.
     * @param appServerDTO DTO con la información necesaria para el servidor de aplicaciones
     * Parámetro requerido. No debe ser nulo
     * @return Resultado de la ejecución del método
     * @throws AppServerException Se produce si ha ocurrido un error durante la creación de ficheros de configuración.
     */    
    @Override
    public String createConfigurationFiles(ApplicationServerDTO appServerDTO) throws AppServerException {
       if( appServerDTO == null )
           throw new IllegalArgumentException("No se pueden crear los ficheros de configuración debido a que el parámetro recibido es nulo."); 
       
       // Por el momento este método solo necesita crear el fichero Standalone, por eso se implementa como un método privado
       // por si en un futuro pudiera ser necesario la creación de varios ficheros.
       return _createFileStandaloneXML(appServerDTO);
    }
        
    /**
     * Crea el fichero standalone.xml sustituyendo las variables del fichero de la plantilla por los valores propios
     * de nuestro nuevo proyecto.
     * @param appServerDTO Objeto con la información para poder sustituir las variables de la plantilla.
     * @return  Resultado de la operación
     * @throws AppServerException 
     */
    private String _createFileStandaloneXML(ApplicationServerDTO appServerDTO) throws AppServerException {
        if( appServerDTO == null )
           throw new IllegalArgumentException("No se pueden crear el fichero standalone.xml debido a que el parámetro recibido es nulo."); 
        
        try {   
            List<CadenasBusquedaReemplazo> listaCadenas = new ArrayList();
            listaCadenas.add(new CadenasBusquedaReemplazo("USERTEMPLATE", appServerDTO.getUserbd()));
            listaCadenas.add(new CadenasBusquedaReemplazo("PASSTEMPLATE", appServerDTO.getPassbd()));
            StringBuilder server = new StringBuilder();
            server.append("oracle:thin:@//").append(appServerDTO.getHost()).append(":").append(appServerDTO.getPortBD()).append("/").append(appServerDTO.getServiceName());
            listaCadenas.add(new CadenasBusquedaReemplazo("SERVERTEMPLATE",server.toString()));

            File createdFileStandaloneXML = FileUtils.createFile(paths.getPathDestinationStandaloneXML());
            FileUtils.copyFileContentAndReplaceString(new FileReader(paths.getPathTemplateStandaloneXML()), new FileWriter(createdFileStandaloneXML), listaCadenas);
            return "Fichero standalone.xml creado correctamente en " + createdFileStandaloneXML.getPath();
        } catch (Exception ex) {
           throw new AppServerException("Error al crear el fichero standalone.xml del proyecto " + appServerDTO.getProject(), ex);
        }
    }

    
    /**
     * Crea los ficheros para poder arrancar el servidor.
     * @param appServerDTO DTO con la información necesaria para el servidor de aplicaciones
     * Parámetro requerido. No debe ser nulo
     * @return Resultado de la ejecución del método
     * @throws AppServerException Se produce si ha ocurrido un error durante la creación de ficheros de configuración.
     */    
    @Override
    public String createRunFiles(ApplicationServerDTO appServerDTO) throws AppServerException {
        if( appServerDTO == null )
           throw new IllegalArgumentException("No se pueden crear los ficheros para arrancar el servidor debido a que el parámetro recibido es nulo."); 
        try{
            StringBuilder resultado = new StringBuilder();
            String resultadoIndividual= _createFileStandaloneBat(appServerDTO);
            resultado.append(resultadoIndividual).append("\n\t");
            resultadoIndividual = _createFileRunBat(appServerDTO);
            resultado.append(resultadoIndividual);
            return resultado.toString();
        } catch (Exception ex) {
           throw new AppServerException("ERROR al crear los ficheros para arrancar el servidor del proyecto " + appServerDTO.getProject(), ex);
        }
    }
    
    /**
    /**
     * Copia el template de Standalone.bat a la ruta del servidor y reemplaza las cadenas necesarias para la configuración del servidor
     * @param appServerDTO DTO con la información con la que sustituirá las variables de la plantilla
     * @return Cadena con el resultado de la ejecución
     * @throws AppServerException Se produce cuando no se ha podido recuperar alguna propiedad necesaria para la creación
     * del fichero
     * @throws IOException Ocurre cuando ha habido un problema al reemplazar el contenido de los ficheros de configuración o en su mismo creación.
     */
    private String _createFileStandaloneBat(ApplicationServerDTO appServerDTO) throws AppServerException,IOException{
        List<CadenasBusquedaReemplazo> listaCadenas = new ArrayList();
        listaCadenas.add(new CadenasBusquedaReemplazo("JAVA_HOME_TEMPLATE", paths.getPathJDK()));
        listaCadenas.add(new CadenasBusquedaReemplazo("JBOSS_TEMPLATE",     paths.getPathAplicationServer()));
        listaCadenas.add(new CadenasBusquedaReemplazo("PROJECT_TEMPLATE",   appServerDTO.getProject()));
        listaCadenas.add(new CadenasBusquedaReemplazo("ADDRESS_TEMPLATE",   appServerDTO.getPortDebug()));
        
        File createdFileStandaloneBat           = FileUtils.createFile(paths.getPathDestinationStandaloneBat());
        FileUtils.copyFileContentAndReplaceString(new FileReader(paths.getPathTemplateStandaloneBat()), new FileWriter(createdFileStandaloneBat), listaCadenas); 
        return "Creado fichero standalone.bat " + createdFileStandaloneBat.getAbsolutePath();
    }
    
    /**
     * Copia el template de run_standalone.bat a la ruta del servidor y reemplaza las cadenas necesarias para la puesta en marcha del servidor y
     * apertura del programa de recogida de logs
     * @param appServerDTO DTO con la información con la que sustituirá las variables de la plantilla
     * @return Cadena con el resultado de la ejecución
     * @throws IOException 
     */
    private String _createFileRunBat(ApplicationServerDTO appServerDTO) throws IOException{
        List<CadenasBusquedaReemplazo> listaCadenas = new ArrayList();
        listaCadenas.add(new CadenasBusquedaReemplazo("OFFSETTEMPLATE", appServerDTO.getPortApp()));
        listaCadenas.add(new CadenasBusquedaReemplazo("TEMPLATE", appServerDTO.getProject()));

        File createdFileRunBat = FileUtils.createFile(paths.getPathDestinationRunBat());
        FileUtils.copyFileContentAndReplaceString(new FileReader(paths.getPathTemplateRunBat()), new FileWriter(createdFileRunBat), listaCadenas);         
        return "Fichero run_" + appServerDTO.getProject() + ".bat creado en " + createdFileRunBat.getAbsolutePath();
    }

    /**
     * Retorna la ruta donde se encuentra el fichero que arranca el servidor.
     * @return 
     */    
    @Override
    public String getServerRunPath(){
        return paths.getPathDestinationRunBat();
    }    

    /**
     * Retorna la ruta donde se desplegará el war.
     * @return 
     */    
    @Override
    public String getPathWar(){
      return paths.getPathHCISWar();
    }
       
}
