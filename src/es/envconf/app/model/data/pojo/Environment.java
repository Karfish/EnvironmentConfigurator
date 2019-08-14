package es.envconf.app.model.data.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Clase utilizada para recuperar los elementos en formato JSON de los ficheros de entornos.
 * @author Miguel
 */
public class Environment implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @SerializedName("project")
    private String project;         // Nombre del proyecto

    @SerializedName("descProject")
    private String descProject;     // Descripción del proyecto

    @SerializedName("branch")
    private String branch;          // Rama del repositorio a la que apuntará el proyecto

    @SerializedName("portApp")	
    private String portApp;         // Puerto sobre el que levantamos la aplicación

    @SerializedName("portDebug")		
    private String portDebug;       // Puerto de debug sobre el que podrá depurar

    @SerializedName("userbd")		
    private  String userbd;         // Usuario de la base de datos

    @SerializedName("passbd")
    private  String passbd;         // Contraseña del usuario de base de datos

    @SerializedName("host")
    private  String host;           // Equipo donde se encuentra alojada la base de datos

    @SerializedName("portBD")
    private  String portBD;         // Puerto de escucha de la base de datos

    @SerializedName("serviceName")
    private  String serviceName;    // Nombre por el que se puede conectar a la instancia de base de datos
    
    
    public Environment(){}
    
    public Environment(String project , String descProject, String branch , String portApp , String portDebug,
                       String userBD  , String passBD , String host    , String portBD , String serviceName){
        this.project     = project;
        this.branch      = branch;
        this.portApp     = portApp;
        this.portDebug   = portDebug;
        this.userbd      = userBD;
        this.passbd      = passBD;
        this.host        = host;
        this.portBD      = portBD;
        this.serviceName = serviceName;
    }

    /**
     * @return the project
     */
    public String getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(String project) {
        this.project = project;
    }

    /**
     * @return the descProject
     */
    public String getDescProject() {  return descProject;  }

    /**
     * @param descProject the descProject to set
     */
    public void setDescProject(String descProject) {  this.descProject = descProject;  }

    /**
     * @return the branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * @return the portApp
     */
    public String getPortApp() {
        return portApp;
    }

    /**
     * @param portApp the portApp to set
     */
    public void setPortApp(String portApp) {
        this.portApp = portApp;
    }

    /**
     * @return the portDebug
     */
    public String getPortDebug() {
        return portDebug;
    }

    /**
     * @param portDebug the portDebug to set
     */
    public void setPortDebug(String portDebug) {
        this.portDebug = portDebug;
    }

    /**
     * @return the userbd
     */
    public String getUserbd() {
        return userbd;
    }

    /**
     * @param userbd the userbd to set
     */
    public void setUserbd(String userbd) {
        this.userbd = userbd;
    }

    /**
     * @return the passbd
     */
    public String getPassbd() {
        return passbd;
    }

    /**
     * @param passbd the passbd to set
     */
    public void setPassbd(String passbd) {
        this.passbd = passbd;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the portBD
     */
    public String getPortBD() {
        return portBD;
    }

    /**
     * @param portBD the portBD to set
     */
    public void setPortBD(String portBD) {
        this.portBD = portBD;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    @Override
    public String toString() {
      return "Data [project=" + project + ", descProject=" + descProject + ", branch=" + branch + ", portApp="+ portApp + ", portDebug=" + portDebug
    		  +", userbd=" + userbd +", passbd=" + passbd +", host=" + host +", portBD=" + portBD +", serviceName=" + serviceName + "]";
    }  
    
}
