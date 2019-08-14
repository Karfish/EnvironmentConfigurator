package es.envconf.app.model.dto;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.lang.reflect.Field;

public class EnvironmentDTO implements Serializable {

    private SimpleStringProperty propertyName = new SimpleStringProperty();
    private String project;
    private String branch;
    private String portApp;
    private String portDebug;
    private String userbd;
    private String passbd;
    private String host;
    private String portBD;
    private String serviceName;
    private String repository;
    private String jbossVersion;


    public EnvironmentDTO(){}

    public String getPropertyName() {
        return propertyName.get();
    }

    public SimpleStringProperty propertyNameProperty() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName.set(propertyName);
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

    /**
     * @return the repository
     */
    public String getRepository() {
        return repository;
    }

    /**
     * @param repository the repository to set
     */
    public void setRepository(String repository) {
        this.repository = repository;
    }

    /**
     * @return the jbossVersion
     */
    public String getJbossVersion() {
        return jbossVersion;
    }

    /**
     * @param jbossVersion the jbossVersion to set
     */
    public void setJbossVersion(String jbossVersion) {
        this.jbossVersion = jbossVersion;
    }

    /**
     * Comprueba si alguno de los atributos de la clase contiene un valor vacio.
     * En caso de contener un valor vacío se considera que el objeto no es valido.
     * @return True si el objeto es válido; false en caso contrario.
     */
    public boolean isValidObject(){
        boolean validationResult = true;
        Class environmentDTO = EnvironmentDTO.class;
        Field[] userFields = environmentDTO.getDeclaredFields();
        for (Field userField : userFields) {
            try {
                if("".equals(userField.get(this))){
                    validationResult = false;
                    break;
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                validationResult = false;
            }
        }

        return validationResult;
    }

    @Override
    public String toString() {
        return  propertyName.getValue() ;
    }
}
