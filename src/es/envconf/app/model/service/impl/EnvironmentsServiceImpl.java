package es.envconf.app.model.service.impl;

import es.envconf.app.model.data.EnvironmentsDataFactory;
import es.envconf.app.model.data.builder.EnvironmentsBuilder;
import es.envconf.app.model.data.dao.EnvironmentsDAO;
import es.envconf.app.model.data.dao.exception.EnvironmentsDAOException;
import es.envconf.app.model.data.pojo.Environment;
import es.envconf.app.model.dto.EnvironmentDTO;
import es.envconf.app.model.service.EnvironmentsService;
import es.envconf.app.model.service.exception.EnvironmentException;

import java.util.Collections;
import java.util.List;

public class EnvironmentsServiceImpl implements EnvironmentsService {

    private EnvironmentsDAO     environmentsData;
    private EnvironmentsBuilder environmentsBuilder;

    public EnvironmentsServiceImpl() throws EnvironmentException{
        try {
            this.environmentsData = EnvironmentsDataFactory.getEnvironmentDAO();
        }catch (EnvironmentsDAOException ex){
            throw new EnvironmentException(ex);
        }
        this.environmentsBuilder = EnvironmentsDataFactory.getEnvironmentsBuilder();
    }


    @Override
    public List<EnvironmentDTO> getEnvironments() throws EnvironmentException {
        List<Environment> environments ;
        try {
            environments = environmentsData.getEnvironments();
            List<EnvironmentDTO> environmentsDTO = Collections.EMPTY_LIST;
            if (environments != null && !environments.isEmpty())
                environmentsDTO = environmentsBuilder.buildListDTO(environments);
            return environmentsDTO;
        }catch (EnvironmentsDAOException ex){
            throw new EnvironmentException(ex);
        }
    }

    @Override
    public boolean createEnvironment(EnvironmentDTO environmentDTO) throws EnvironmentException {
        return false;
    }

    @Override
    public boolean updateEnvironment(EnvironmentDTO environmentDTO) throws EnvironmentException {
        return false;
    }

    @Override
    public boolean deleteEnvironment(EnvironmentDTO environmentDTO) throws EnvironmentException {
        return false;
    }

    @Override
    public boolean deleteEnvironment(String nameProject) throws EnvironmentException {
        return false;
    }
}
