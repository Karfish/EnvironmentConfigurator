package es.envconf.app.model.applicationServer.jboss.properties;


import es.envconf.app.model.properties.PropertiesSettingsException;
import es.envconf.app.model.properties.AbstractPropertiesSettings;

/**
 *
 * @author Miguel
 */
public abstract class AbstractJBossPropertiesSettings extends AbstractPropertiesSettings implements JBossPropertiesSettingsInterface{

    
    private static final String PATH_ROOT             = "pathRoot" ;
    private static final String PATH_FILES            = "pathFiles" ;
    private static final String PATH_FILES_TEMPLATES  = "pathFilesTemplate";
    
    public AbstractJBossPropertiesSettings(String configurationFileName){
        super(configurationFileName);
    }
    
    @Override
    public String getPropertyPathRoot() throws PropertiesSettingsException {
        return getProperty(PATH_ROOT);
    }
    
    @Override
    public String getPropertyPathFiles() throws PropertiesSettingsException{
        return getProperty(PATH_FILES);
    }
    
    @Override
    public String getPropertyPathFileTemplates()throws PropertiesSettingsException{
        return getProperty(PATH_FILES_TEMPLATES);
    }
}
