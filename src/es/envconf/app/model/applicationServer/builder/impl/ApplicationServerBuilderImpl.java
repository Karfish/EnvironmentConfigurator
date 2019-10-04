package es.envconf.app.model.applicationServer.builder.impl;


import es.envconf.app.model.applicationServer.builder.ApplicationServerBuilder;
import es.envconf.app.model.applicationServer.dto.ApplicationServerDTO;
import es.envconf.app.model.dto.EnvironmentDTO;

/**
 * Clase para la creación de objetos complejos (DTOs)
 * @author Miguel Godoy
 */
public class ApplicationServerBuilderImpl implements ApplicationServerBuilder {

    /**
     * Construye un objeto ApplicationServerDTO a partir de un environmentDTO con la información requerida por el servidor de aplicaciones.
     * @param environmentDTO Parámetro requerido. No debe ser nulo.
     * @return Objecto ApplicationServerDTO
     */
    @Override
    public ApplicationServerDTO build(EnvironmentDTO environmentDTO) {
        if( environmentDTO == null )
            throw new IllegalArgumentException("No se puede instanciar el objeto si el nombre de proyecto recibido o la versión de servidor es nula");
        
        ApplicationServerDTO newAppServerDTO = new ApplicationServerDTO();
        newAppServerDTO.setProject(environmentDTO.getProject());
        newAppServerDTO.setUserbd(environmentDTO.getUserbd());
        newAppServerDTO.setPassbd(environmentDTO.getPassbd());
        newAppServerDTO.setPortBD(environmentDTO.getPortBD());
        newAppServerDTO.setHost(environmentDTO.getHost());
        newAppServerDTO.setServiceName(environmentDTO.getServiceName());
        newAppServerDTO.setPortDebug(environmentDTO.getPortDebug());
        newAppServerDTO.setPortApp(environmentDTO.getPortApp());
        return newAppServerDTO;
    }

}
