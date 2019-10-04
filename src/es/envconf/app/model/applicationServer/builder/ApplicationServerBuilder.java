package es.envconf.app.model.applicationServer.builder;


import es.envconf.app.model.applicationServer.dto.ApplicationServerDTO;
import es.envconf.app.model.dto.EnvironmentDTO;

/**
 * Interfaz con los métodos necesarios para crear objetos complejos (DTOs) del servidor de aplicaciones.
 * @author Miguel Godoy
 */
public interface ApplicationServerBuilder {
    
    /**
     * Construye un objeto ApplicationServerDTO a partir de un environmentDTO con la información requerida por el servidor de aplicaciones.
     * @param environmentDTO Parámetro requerido. No debe ser nulo.
     * @return Objecto ApplicationServerDTO
     */
    public ApplicationServerDTO build(EnvironmentDTO environmentDTO);
    
}
