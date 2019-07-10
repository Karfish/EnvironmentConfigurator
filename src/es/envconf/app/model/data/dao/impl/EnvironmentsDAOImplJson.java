package es.envconf.app.model.data.dao.impl;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import es.envconf.app.model.data.dao.EnvironmentsDAO;
import es.envconf.app.model.data.dao.exception.EnvironmentsDAOException;
import java.io.IOException;

import es.envconf.app.model.data.pojo.Environment;
import es.envconf.app.model.properties.GeneralPropertiesSettings;
import es.envconf.app.model.properties.PropertiesSettingsException;
import es.envconf.app.common.utils.FileUtils;
import es.envconf.app.common.utils.TextUtils;

import java.io.FileNotFoundException;

/**
 * Implementación de las operaciones que se pueden realizar con los entornos.
 * @author Miguel Godoy
 */
public final class EnvironmentsDAOImplJson implements EnvironmentsDAO {
	
    private final String PATH;
    private static final String extFile = ".json";

    private List<Environment> environments = new ArrayList<>();

    /**
     * Constructor por defecto
     * @throws EnvironmentsDAOException Se puede producir por no poder recuperar la ruta donde se crearan los ficheros con la información
     * de los entornos o por no poder recuperar los ficheros ya creados.
     */
    public EnvironmentsDAOImplJson() throws EnvironmentsDAOException {
        try{
            PATH = GeneralPropertiesSettings.getInstance().getPathEnvironmentsFiles();
            loadEnvironments();
        }catch(PropertiesSettingsException ex){
            throw new EnvironmentsDAOException(ex);
        } 
    }

    /**
     * Carga los entornos en memoria
     * @throws EnvironmentsDAOException Se produce si no se han podido cargar los entornos.
     */
    @Override
    public void loadEnvironments() throws EnvironmentsDAOException {
        try {
            String [] _listaFicheros = _getListOfFilesJson();
            if( _listaFicheros != null && _listaFicheros.length > 0 ){
                Arrays.sort(_listaFicheros);
                for (String fichero : _listaFicheros) {
                    Environment environment = _getEnvironmentByFile(fichero);
                    if(environment != null){
                        environments.add(environment);
                    }    
                }
            }
        } catch (JsonIOException | IOException e) {
                throw new EnvironmentsDAOException("Error cargando los ficheros de configuración", e);
        } catch (Exception e) {
                throw new EnvironmentsDAOException("Error cargando los ficheros de configuración", e);
        }
    }
    
    /**
     * Devuelve la lista de ficheros json que se encuentren en la carpeta de environments
     * @return Lista de ficheros de entornos.
     */
    private String[] _getListOfFilesJson(){	
       return FileUtils.getListOfFilesByExtension(PATH, extFile);
    }
    

    /**
     * Devuelve la lista de los entornos disponibles.
     * @return Lista de entornos.
     * @throws EnvironmentsDAOException Se produce si ha ocurrido algún problema al recuperar la lista.
     */ 
    @Override
    public List<Environment> getEnvironments() throws EnvironmentsDAOException {
        return environments;
    }
    
    /**
     * Dado un nombre de fichero lee su contenido en Json y lo transforma a un objeto Environment
     * @param nameFile
     * @return Objeto con todos los datos del entorno.
     */
    private Environment _getEnvironmentByFile(String nameFile) throws JsonIOException, FileNotFoundException, IOException{
        Environment environment = null;
        JsonParser parser = new JsonParser();
        FileReader fr = null;
        fr = new FileReader(PATH + nameFile);
        JsonElement datos = parser.parse(fr);
        fr.close();
        Gson gson = new Gson();
        environment = gson.fromJson(datos, Environment.class);               
        return environment;
    }
	
    /**
     * Crea un nuevo entorno
     * @param environment Parámetro requerido. No debe ser nulo.
     * @return true si ha podido crear el entorno; false en caso contrario.
     * @throws EnvironmentsDAOException Se produce si ha ocurrido algún problema al crear el entorno
     */
    @Override
    public boolean createEnvironment(Environment environment) throws EnvironmentsDAOException {
        if( environment == null )
            throw new IllegalArgumentException("No se puede crear el entorno debido a que el objeto recibido es nulo");
        Gson gson = new Gson();
        try{
            String jsonString = gson.toJson(environment);
            StringBuilder pathAndNameFile = new StringBuilder().append(PATH).append(environment.getProject()).append(extFile);
            return FileUtils.createFile(pathAndNameFile.toString(), jsonString);
        }catch(Exception ex){
            throw new EnvironmentsDAOException("No se ha podido crear el entorno.", ex);
        }   
    }

    /**
     * Actualiza el entorno recibido como parámetro
     * @param environment Parámetro requerido. No debe ser nulo.
     * @return true si ha podido modificar el entorno; false en caso contrario.
     * @throws EnvironmentsDAOException Se produce si ha ocurrido algún problema al modificar el entorno
     */
    @Override
    public boolean updateEnvironment(Environment environment) throws EnvironmentsDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Elimina el entorno recibido como parámetro
     * @param environment Parámetro requerido. No debe ser nulo.
     * @return true si ha podido eliminar el entorno; false en caso contrario.
     * @throws EnvironmentsDAOException Se produce si ha ocurrido algún problema al eliminar el entorno
     */
    @Override
    public boolean deleteEnvironment(Environment environment) throws EnvironmentsDAOException {
        if( environment == null )
            throw new IllegalArgumentException("No se puede eliminar el entorno debido a que el objeto recibido es nulo");
        return deleteEnvironment(environment.getProject());
    }

    /**
     * Elimina un entorno por su nombre de proyecto
     * @param nameProject Parámetro requerido. No debe ser nulo.
     * @return  true si ha podido eliminar el entorno; false en caso contrario.
     * @throws EnvironmentsDAOException Se produce si ha ocurrido algún problema al eliminar el entorno
     */
    @Override
    public boolean deleteEnvironment(String nameProject) throws EnvironmentsDAOException {
        if( TextUtils.esNuloVacio(nameProject) )
            throw new IllegalArgumentException("No se puede eliminar el entorno debido a que la cadena recibida es nula o está vacía");
        try{
            String pathFile = PATH + nameProject + extFile;
            return FileUtils.deleteFile(pathFile);
        }catch(Exception ex){
            throw new EnvironmentsDAOException("Error al eliminar el proyecto.", ex);
        }
    }
    
}
