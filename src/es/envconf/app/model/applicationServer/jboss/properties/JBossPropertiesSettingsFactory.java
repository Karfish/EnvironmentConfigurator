package es.envconf.app.model.applicationServer.jboss.properties;


import es.envconf.app.model.applicationServer.jboss.JBossVersion;

/**
 * Clase que aplica el patrón factoria para instanciar los objetos en función a la versión de JBoss que queramos utilizar
 * @author Miguel
 */
public class JBossPropertiesSettingsFactory {
    
    /**
     * Es función de la versión de JBOSS recibida como parámetro retornará un objeto con las propiedades del servidor u otro.
     * @param jBossVersion Versión de JBOSS a recuperar. Parámetro requerido. No puede ser nulo.
     * @return JBossPropertiesSettings correspondiente a la versión de JBOSS; null si la versión de JBOSS no corresponde
     * con las comparadas en este método.
     */
    public static final JBossPropertiesSettingsInterface getJBossPropertiesSettings(JBossVersion jBossVersion){
        if( jBossVersion == null)
            throw new IllegalArgumentException("No se puede obtener el objeto con las propiedades de JBOSS debido a que el objeto recibido"
                    + "como parámetro es nulo.");
        
        JBossPropertiesSettingsInterface jBossPropertiesSettings = null; // Por defecto
        
        if(JBossVersion.JBOSS_7_1_1.equals(jBossVersion)){
            jBossPropertiesSettings = JBossOldPropertiesSettings.getInstance();
        }else if(JBossVersion.JBOSS_6_4_0.equals(jBossVersion)){
            jBossPropertiesSettings = JBossNewPropertiesSettings.getInstance();
        }
        return jBossPropertiesSettings;
    }
    
}
