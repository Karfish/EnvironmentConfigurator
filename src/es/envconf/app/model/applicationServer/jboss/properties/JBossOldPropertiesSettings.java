package es.envconf.app.model.applicationServer.jboss.properties;

/**
 *
 * @author Miguel
 */
public class JBossOldPropertiesSettings extends AbstractJBossPropertiesSettings implements JBossPropertiesSettingsInterface{

    private static JBossPropertiesSettingsInterface jBossPropertiesSettings;
    
    public static JBossPropertiesSettingsInterface getInstance() {
        if(jBossPropertiesSettings == null){
            jBossPropertiesSettings = new JBossOldPropertiesSettings();
        }
        return jBossPropertiesSettings;
    }
    
    private JBossOldPropertiesSettings(){
        super("./conf/jbossOld.properties");
    }
}
