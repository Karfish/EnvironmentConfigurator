package es.envconf.app.common.utils;

/**
 * Clase que almacenará dos cadenas con el fin de buscar por una y reemplazar por la otra 
 * @author Miguel Godoy
 */
public class CadenasBusquedaReemplazo {

    private String searchedString = ""; // Cadena a buscar para reemplazar
    private String replaceString  = ""; // Cadena que reemplaza al texto buscado

    /**
     * Constructor de la clase
     * @param searchedString Cadena a buscar. Parámetro requerido. No debe ser nulo.
     * @param replaceString  Cadena que reemplaza a la cadena a buscar. Parámetro requerido. No debe ser nulo.
     */
    public CadenasBusquedaReemplazo(String searchedString,String replaceString ){
        if( searchedString == null || replaceString == null )
            throw new IllegalArgumentException("Cadena a buscar recibida o cadena sustitutiva recibida nula");
            
        this.searchedString = searchedString;
        this.replaceString = replaceString;
    }
    
    /**
     * Devuelve el valor de la cadena a buscar
     * @return Cadena a buscar.
     */
    public String getSearchedString() {
        return searchedString;
    }
    
    /**
     * Devuelve el valor de la cadena que reemplazará a la cadena buscada
     * @return Cadena que reemplaza a la buscada.
     */
    public String getReplaceString() {
        return replaceString;
    }
    
}
