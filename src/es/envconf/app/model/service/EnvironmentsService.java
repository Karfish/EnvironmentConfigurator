package es.envconf.app.model.service;

import es.envconf.app.model.dto.EnvironmentDTO;
import es.envconf.app.model.service.exception.EnvironmentException;

import java.util.List;

/**
 * Operaciones CRUD sobre los entornos.
 * @author Miguel Godoy
 */
public interface EnvironmentsService {

    /**
     * Devuelve la lista de los entornos disponibles.
     * @return Lista de entornos.  Retorna una lista vacía si no existen entornos.
     * @throws EnvironmentException
     */
    public List<EnvironmentDTO> getEnvironments() throws EnvironmentException;

    /**
     * Crea un nuevo entorno
     * @param environmentDTO Parámetro requerido. No debe ser nulo.
     * @return true si ha podido realizar la acción; false en caso contrario
     * @throws EnvironmentException Se produce si ha ocurrido algún problema al crear el entorno
     */
    public boolean createEnvironment(EnvironmentDTO environmentDTO) throws EnvironmentException;

    /**
     * Actualiza un entorno
     * @param environmentDTO Parámetro requerido. No debe ser nulo.
     * @return true si ha podido realizar la acción; false en caso contrario
     * @throws EnvironmentException Se produce si ha ocurrido algún problema al modificar el entorno
     */
    public boolean updateEnvironment(EnvironmentDTO environmentDTO) throws EnvironmentException;

    /**
     * Elimina un entorno
     * @param environmentDTO Parámetro requerido. No debe ser nulo.
     * @return true si ha podido realizar la acción; false en caso contrario
     * @throws EnvironmentException Se produce si ha ocurrido algún problema al eliminar el entorno
     */
    public boolean deleteEnvironment(EnvironmentDTO environmentDTO) throws EnvironmentException;

    /**
     * Elimina un entorno
     * @param nameProject Parámetro requerido. No debe ser nulo.
     * @return true si ha podido realizar la acción; false en caso contrario
     * @throws EnvironmentException Se produce si ha ocurrido algún problema al eliminar el entorno
     */
    public boolean deleteEnvironment(String nameProject) throws EnvironmentException;
}