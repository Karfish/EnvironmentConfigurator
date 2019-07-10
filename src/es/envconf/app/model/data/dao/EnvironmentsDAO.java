package es.envconf.app.model.data.dao;

import java.util.List;

import es.envconf.app.model.data.dao.exception.EnvironmentsDAOException;
import es.envconf.app.model.data.pojo.Environment;

/**
 * Operaciones que se pueden realizar con los entornos.
 * @author Miguel Godoy
 */
public interface EnvironmentsDAO {

    /**
     * Carga los entornos en memoria
     * @throws EnvironmentsDAOException Se produce si no se han podido cargar los entornos.
     */
    public void loadEnvironments() throws EnvironmentsDAOException;
    
    /**
     * Devuelve la lista de los entornos disponibles.
     * @return Lista de entornos.
     * @throws EnvironmentsDAOException Se produce si ha ocurrido algún problema al recuperar la lista.
     */    
    public List<Environment> getEnvironments() throws EnvironmentsDAOException;
    
    /**
     * Crea un nuevo entorno
     * @param environment Parámetro requerido. No debe ser nulo.
     * @return true si ha podido crear el entorno; false en caso contrario.
     * @throws EnvironmentsDAOException Se produce si ha ocurrido algún problema al crear el entorno
     */
    public boolean createEnvironment(Environment environment) throws EnvironmentsDAOException;
    
    /**
     * Actualiza el entorno recibido como parámetro
     * @param environment Parámetro requerido. No debe ser nulo.
     * @return true si ha podido modificar el entorno; false en caso contrario.
     * @throws EnvironmentsDAOException Se produce si ha ocurrido algún problema al modificar el entorno
     */
    public boolean updateEnvironment(Environment environment) throws EnvironmentsDAOException;
    
    /**
     * Elimina el entorno recibido como parámetro
     * @param environment Parámetro requerido. No debe ser nulo.
     * @return true si ha podido eliminar el entorno; false en caso contrario.
     * @throws EnvironmentsDAOException Se produce si ha ocurrido algún problema al eliminar el entorno
     */
    public boolean deleteEnvironment(Environment environment) throws EnvironmentsDAOException;
    
    /**
     * Elimina un entorno por su nombre de proyecto
     * @param nameProject Parámetro requerido. No debe ser nulo.
     * @return  true si ha podido eliminar el entorno; false en caso contrario.
     * @throws EnvironmentsDAOException Se produce si ha ocurrido algún problema al eliminar el entorno
     */
    public boolean deleteEnvironment(String nameProject) throws EnvironmentsDAOException;
}
