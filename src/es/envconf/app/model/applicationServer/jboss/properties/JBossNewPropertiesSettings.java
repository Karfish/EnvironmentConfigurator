package es.envconf.app.model.applicationServer.jboss.properties;



/**
 *
 * @author Miguel
 */
public class JBossNewPropertiesSettings extends AbstractJBossPropertiesSettings implements JBossPropertiesSettingsInterface{

    private static JBossPropertiesSettingsInterface jBossPropertiesSettings;
    
    public static JBossPropertiesSettingsInterface getInstance() {
        if(jBossPropertiesSettings == null){
            jBossPropertiesSettings = new JBossNewPropertiesSettings();
        }
        return jBossPropertiesSettings;
    }
  
    private JBossNewPropertiesSettings(){
        super("./conf/jbossNew.properties");
    }
}
