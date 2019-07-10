package es.envconf.app.model.properties;

/**
 * Clase que provee el acceso a las rutas generales del proyecto (ruta de ficheros de entornos, plantillas para los IDE's, ...)
 * @author Miguel Godoy
 */
public class GeneralPropertiesSettings extends AbstractPropertiesSettings{
    
    private static GeneralPropertiesSettings generalPropertiesSettings;
    /**
     * Atributos estáticos que corresponde con cada propiedad del fichero conf.properties
     */
    private static final String  ENVIRONMENTS_FILES                 = "environmentsFiles" ;
    private static final String  EXECUTABLE_SHORTCUTS               = "executableShortcuts" ;
    private static final String  SHORTCUT_FOLDER                    = "shortcutsFolder" ;
    private static final String  JDK                                = "JDK" ;
    private static final String  ENVIRONMENTS_PROJECTS_ROOT_FOLDER  = "environmentsProjectsRootFolder" ;
    private static final String  FILE_TEMPLATE_FOLDER_SCM           = "fileTemplateFolderSCM" ;
    private static final String  FILE_TEMPLATE_FOLDER_IDE           = "fileTemplateFolderIDE" ;
    
    public static GeneralPropertiesSettings getInstance() {
        if(generalPropertiesSettings == null){
            generalPropertiesSettings = new GeneralPropertiesSettings();
        }
        return generalPropertiesSettings;
    }
    
    private GeneralPropertiesSettings(){
        super("./conf/general.properties");
    }
    
    public String getPathEnvironmentsFiles() throws PropertiesSettingsException{
        return getProperty(ENVIRONMENTS_FILES);
    }
    
    public String getPathExecutableShortcuts() throws PropertiesSettingsException{
        return getProperty(EXECUTABLE_SHORTCUTS);
    }   
    
    public String getPathShortcutsFolder() throws PropertiesSettingsException{
        return getProperty(SHORTCUT_FOLDER);
    }
    
    public String getPathJDK() throws PropertiesSettingsException{
        return getProperty(JDK);
    }  
    
    public String getPathEnvironmentProjectsRootFolder() throws PropertiesSettingsException{
        return getProperty(ENVIRONMENTS_PROJECTS_ROOT_FOLDER);
    }
    
    public String getPathFileTemplateFolderSCM()throws PropertiesSettingsException{
        return getProperty(FILE_TEMPLATE_FOLDER_SCM);
    }   

    public String getPathFileTemplateFolderIDE() throws PropertiesSettingsException{
        return getProperty(FILE_TEMPLATE_FOLDER_IDE);
    }     
    
}
