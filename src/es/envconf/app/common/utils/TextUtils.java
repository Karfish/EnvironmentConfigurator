package es.envconf.app.common.utils;


import java.util.List;

/**
 * Clase que contiene métodos de ayuda para trabajar con cadenas
 * @author Miguel Godoy
 */
public class TextUtils {
    
    /**
     * Comprueba si una cadena es nula o está vacia
     * @param cadena
     * @return Devuelve true si la cadena es nula o vacia; false en caso contrario
     */
    public static boolean  esNuloVacio(String cadena){
        return cadena == null || cadena.length() == 0 ;
    }
    
    /**
     * Dada una cadena reemplaza las ocurrencias de la lista por otro texto
     * @param chain Cadena sobre la que realizará la búsqueda
     * @param cadenas Lista de objectos CadenasBusquedaReemplazo que contienen el texto a buscar y el texto por el que reemplazarlo
     * @return Cadena modificada
     */
    public static String replaceStrings(String chain, List<CadenasBusquedaReemplazo> cadenas ){
        if( esNuloVacio(chain) || (cadenas == null || cadenas.size()<1) )
            throw new IllegalArgumentException("La cadena recibida o la lista de palabras recibidas son nulas o no contiene elementos");
        for (CadenasBusquedaReemplazo cadena : cadenas) {
            chain = chain.replaceAll(cadena.getSearchedString(), cadena.getReplaceString());
        }
        return chain;    
    }
    
}
