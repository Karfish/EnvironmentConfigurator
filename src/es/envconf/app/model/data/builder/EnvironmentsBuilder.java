package es.envconf.app.model.data.builder;

import es.envconf.app.model.data.pojo.Environment;
import es.envconf.app.model.dto.EnvironmentDTO;
import java.util.List;


/**
 * Clase para la construcción de objetos de tipo Environment y EnvironmentDTO
 * @author Miguel
 */
public interface EnvironmentsBuilder {
    
    /**
     * Contruye un objeto Environment a partir de un EnvironmentDTO.
     * @param environmentDTO Parámetro requerido. No debe ser nulo.
     * @return Retorna un objeto Environment con la información del objeto environmentDTO recibido como parámetro.
     */
    public Environment build(EnvironmentDTO environmentDTO);
    
    /**
     * Construye una lista de objetos Environment a partir de una lista de objetos EnvironmentDTO.
     * @param listEnvironmentsDTO Parámetro requerido. No debe ser nulo.
     * @return Lista de entornos construidos
     */
    public List<Environment> buildListEntities(List<EnvironmentDTO> listEnvironmentsDTO); 
    
    /**
     * Contruye un objeto EnvironmentDTO a partir de un Environment.
     * @param environment Parámetro requerido. No debe ser nulo.
     * @return EnvironmentDTO
     */
    public EnvironmentDTO build(Environment environment);
    
    /**
     * Construye una lista de objetos EnvironmentDTO a partir de una lista de objetos Environment.
     * @param listEnvironments Parámetro requerido. No debe ser nulo.
     * @return Lista de entornos
     */
    public List<EnvironmentDTO> buildListDTO(List<Environment> listEnvironments);
    


}
