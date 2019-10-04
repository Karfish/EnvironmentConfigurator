package es.envconf.app.model.applicationServer;


import es.envconf.app.common.utils.TextUtils;
import es.envconf.app.model.applicationServer.builder.ApplicationServerBuilder;
import es.envconf.app.model.applicationServer.builder.impl.ApplicationServerBuilderImpl;
import es.envconf.app.model.applicationServer.exception.AppServerException;
import es.envconf.app.model.applicationServer.jboss.JBoss;
import es.envconf.app.model.applicationServer.jboss.JBossVersion;

/**
 * Clase que aplica el patrón factoria para instanciar los objetos correspondientes al servidor de aplicaciones.
 * @author Miguel Godoy
 */
public class ApplicationServerFactory {
 
    /**
     * Retorna un objeto de tipo ApplicationServer
     * @param nameProject
     * @param jBossVersion
     * @return 
     */
    public static final ApplicationServer getApplicationServer(String nameProject, JBossVersion jBossVersion) throws AppServerException {
        if( TextUtils.esNuloVacio(nameProject) || jBossVersion == null )
            throw new IllegalArgumentException("No se puede instanciar el objeto si el nombre de proyecto recibido o la versión de servidor es nula");
        
        return new JBoss(nameProject, jBossVersion);
    }
    
    public static final ApplicationServerBuilder getApplicationServerBuilder(){
        return new ApplicationServerBuilderImpl();
    }
}

