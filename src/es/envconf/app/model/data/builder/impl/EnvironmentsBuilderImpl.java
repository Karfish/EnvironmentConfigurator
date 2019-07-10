package es.envconf.app.model.data.builder.impl;


import es.envconf.app.model.data.builder.EnvironmentsBuilder;
import es.envconf.app.model.data.pojo.Environment;
import es.envconf.app.model.dto.EnvironmentDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz EnvironmentsBuilder para la construcción de objetos de tipo Environment y EnvironmentDTO
 * @author Miguel Godoy
 */
public class EnvironmentsBuilderImpl implements EnvironmentsBuilder {

    /**
     * Contruye un objeto Environment a partir de un EnvironmentDTO.
     * @param environmentDTO Parámetro requerido. No debe ser nulo.
     * @return Retorna un objeto Environment con la información del objeto environmentDTO recibido como parámetro.
     */
    @Override
    public Environment build(EnvironmentDTO environmentDTO) {
        _illegalArgumentExceptionSiObjetoNulo(environmentDTO, Environment.class);
        
        Environment environment = new Environment();
        environment.setProject     ( environmentDTO.getProject());
        environment.setBranch      ( environmentDTO.getBranch());
        environment.setPortApp     ( environmentDTO.getPortApp());
        environment.setPortDebug   ( environmentDTO.getPortDebug());
        environment.setUserbd      ( environmentDTO.getUserbd());
        environment.setPassbd      ( environmentDTO.getPassbd());
        environment.setPortBD      ( environmentDTO.getPortBD());
        environment.setHost        ( environmentDTO.getHost());
        environment.setServiceName ( environmentDTO.getServiceName());  
        return environment;
    }
    
    /**
     * Construye una lista de objetos Environment a partir de una lista de objetos EnvironmentDTO.
     * @param listEnvironmentsDTO Parámetro requerido. No debe ser nulo.
     * @return Lista de entornos construidos
     */
    @Override
    public List<Environment> buildListEntities(List<EnvironmentDTO> listEnvironmentsDTO) {
        _illegalArgumentExceptionSiListaNulaVacia(listEnvironmentsDTO, Environment.class);
        
        List<Environment> listEnvironments = new ArrayList<>();
        for (EnvironmentDTO environmentDTO : listEnvironmentsDTO) { 
            Environment environment = build(environmentDTO);
            listEnvironments.add(environment);
        }
        return listEnvironments;
    }
    
    /**
     * Contruye un objeto EnvironmentDTO a partir de un Environment.
     * @param environment Parámetro requerido. No debe ser nulo.
     * @return EnvironmentDTO
     */
    @Override
    public EnvironmentDTO build(Environment environment) {
        _illegalArgumentExceptionSiObjetoNulo(environment, EnvironmentDTO.class);
        
        EnvironmentDTO environmentDTO = new EnvironmentDTO();   
        environmentDTO.setProject     ( environment.getProject());
        environmentDTO.setBranch      ( environment.getBranch());
        environmentDTO.setPortApp     ( environment.getPortApp());
        environmentDTO.setPortDebug   ( environment.getPortDebug());
        environmentDTO.setUserbd      ( environment.getUserbd());
        environmentDTO.setPassbd      ( environment.getPassbd());
        environmentDTO.setPortBD      ( environment.getPortBD());
        environmentDTO.setHost        ( environment.getHost());
        environmentDTO.setServiceName ( environment.getServiceName());       
        return environmentDTO;
    }

    
    /**
     * Construye una lista de objetos EnvironmentDTO a partir de una lista de objetos Environment.
     * @param listEnvironments Parámetro requerido. No debe ser nulo.
     * @return Lista de entornos
     */
    @Override
    public List<EnvironmentDTO> buildListDTO(List<Environment> listEnvironments) {
        _illegalArgumentExceptionSiListaNulaVacia(listEnvironments, EnvironmentDTO.class);
        
        List<EnvironmentDTO> listEnvironmentsDTO = new ArrayList<>();
        for (Environment environment : listEnvironments) { 
            EnvironmentDTO environmentDTO = build(environment);
            listEnvironmentsDTO.add(environmentDTO);
        }       
        return listEnvironmentsDTO;
    }
    
    private void _illegalArgumentExceptionSiObjetoNulo(Object objeto, Class nombreClase){
        if( objeto == null ) throw new IllegalArgumentException("No se puede construir el objeto de " + nombreClase + " debido a que el parámetro recibido es nulo");
    }
    
    private void _illegalArgumentExceptionSiListaNulaVacia(List<?> lista, Class nombreClase){
        if( lista == null || lista.isEmpty() ) 
            throw new IllegalArgumentException("No se puede construir la lista de objetos de " + nombreClase + " debido a que el parámetro recibido es nulo");
    }

}
