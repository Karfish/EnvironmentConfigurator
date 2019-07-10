package es.envconf.app.model.properties;

import es.envconf.app.common.utils.TextUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase abstracta que contiene las cadenas por las que se recuperarán las propiedades
 * @author Miguel
 */
public abstract class AbstractPropertiesSettings {
    
    private final Properties  propiedades = new Properties();
    private String configurationFileName;
   
   public AbstractPropertiesSettings(){}
    
    /**
     * Constructor de la clase
     * Establece la ruta del fichero de propiedades y carga estas en memoria.
     * @param configurationFileName Ruta del fichero de propiedades
     */
    public AbstractPropertiesSettings(String configurationFileName){
        _setConfigurationFileName(configurationFileName);
        _loadConfigurationFile();
    }
    
    private void _setConfigurationFileName(String configurationFileName){
        if( TextUtils.esNuloVacio(configurationFileName) )
            throw new IllegalArgumentException("No se puede establecer el nombre del fichero de propiedades ya que este"
                    + " es nulo o está vacío.");
        this.configurationFileName = configurationFileName;
    }
    
    /**
     * Carga el fichero de propiedades para poder recuperarlas en cualquier punto de la aplicación.
     */
    private void _loadConfigurationFile(){
        try {
            this.propiedades.load(new FileInputStream(configurationFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se ha encontrado el fichero de propiedades con ruta " + configurationFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el fichero de propiedades con ruta " + configurationFileName, e);
        }   
    }
    
    /**
     * Dada una cadena, la cual se encuentre como propiedad en conf.properties, devuelve el valor de la misma.
     * @param nameProperty Nombre de la propiedad
     * @return Valor de la propiedad. Si no existe la propiedad devuelve una cadena vacia
     */
    protected final String getProperty(String nameProperty) throws PropertiesSettingsException{
        if( TextUtils.esNuloVacio(nameProperty) ) 
            throw new IllegalArgumentException("No se puede recuperar la propiedad debido a que la cadena recibida es nula");
        String value = propiedades.getProperty(nameProperty);
        if( value == null ) throw new PropertiesSettingsException(nameProperty, this.configurationFileName);
        return value;
    }
    
    /**
     * Modifica el valor de una propiedad
     * @param nameProperty Nombre de la propiedad
     * @param valueProperty  Valor de la propiedad
     */
    public final void setProperty(String nameProperty,String valueProperty) throws IOException{
        if( TextUtils.esNuloVacio(nameProperty) || TextUtils.esNuloVacio(valueProperty))
            throw new IllegalArgumentException("No se ha podido modificar el valor de la propiedad ya que alguna de las "
                    + "cadenas recibidas es nula.");
        propiedades.setProperty(nameProperty, valueProperty);
        FileOutputStream fileOutputStream = new FileOutputStream(configurationFileName);
        propiedades.store(fileOutputStream, null);
    }
    
}
