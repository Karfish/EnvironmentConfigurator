package es.envconf.app.model.applicationServer.jboss;


import java.util.ArrayList;
import java.util.List;

/**
 * Distintas versiones de JBOSS
 * @author Miguel
 */
public enum JBossVersion {
    
    JBOSS_7_1_1 ("JBoss7.1.1"), JBOSS_6_4_0("JBoss6.4.0");
    
    private String nameVersion;
    
    JBossVersion(String nameVersion){
        this.nameVersion = nameVersion;
    }
    
    public String getNameVersion(){
        return this.nameVersion;
    }
    
    
    public static List getVersionNames(){
        List<String> jbossVersionNames = new ArrayList<>();
        jbossVersionNames.add(JBOSS_7_1_1.getNameVersion());
        jbossVersionNames.add(JBOSS_6_4_0.getNameVersion());
        return jbossVersionNames;
    }
    
    /**
     * Retorna un objeto JBossVersions que corresponda con la cadena recibida como parámetro
     * @param jbossVersion
     * @return 
     */
    public static JBossVersion jBossVersionByString(String jbossVersion){
        if( jbossVersion == null || "".equals(jbossVersion))
            throw new IllegalArgumentException("La cadena recibida es nula");
       JBossVersion jv = null;

       if( jbossVersion.equals(JBOSS_7_1_1.getNameVersion()) ){
           jv = JBOSS_7_1_1;
       }else if( jbossVersion.equals(JBOSS_6_4_0.getNameVersion()) ){
           jv = JBOSS_6_4_0;
       }
       return jv;
    }
    
}
