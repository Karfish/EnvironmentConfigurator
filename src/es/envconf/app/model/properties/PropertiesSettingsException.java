package es.envconf.app.model.properties;

/**
 * Excepción concreta para cuando una propiedad no ha podido ser recuperada
 * del fichero de configuración.
 * @author Miguel
 */
public class PropertiesSettingsException extends Exception{

    private final String nameProperty; // nombre de la propiedad
    private final String nameFile;     // nombre del fichero donde está buscando
    
    public PropertiesSettingsException(String nameProperty,String nameFile){
        super();
        this.nameProperty = nameProperty;
        this.nameFile = nameFile;
    }
    
    public PropertiesSettingsException(Throwable cause,String nameProperty,String nameFile){
        super(cause);
        this.nameProperty = nameProperty;
        this.nameFile = nameFile;
    }
    
    public PropertiesSettingsException(String message,Throwable cause, String nameProperty, String nameFile){
        super(message, cause);
        this.nameProperty = nameProperty;
        this.nameFile = nameFile;
    }
    
    @Override
    public String getMessage(){
        return "La propiedad \"" + this.nameProperty + "\" no se encuentra en el fichero " + this.nameFile;
    }
}
