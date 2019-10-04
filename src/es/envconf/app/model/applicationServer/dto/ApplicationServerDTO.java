package es.envconf.app.model.applicationServer.dto;

/**
 * Data transfer object con la información necesaria para el servidor de aplicaciones.
 * @author Miguel Godoy
 */
public class ApplicationServerDTO {
    
    private String project;
    private String portApp;		
    private String portDebug;	
    private String userbd;
    private String passbd;
    private String host;
    private String portBD;
    private String serviceName;

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
    
}
