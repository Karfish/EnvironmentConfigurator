package es.envconf.app.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene métodos estáticos de ayuda para el trabajo con ficheros
 * @author Miguel Godoy
 */
public class FileUtils {
    
    /**
     * Comprueba si existe la ruta recibida como parámetro.
     * @param path Ruta buscada
     * @return Devuelve true en caso de que exista; false en caso contrario
     */
    public static boolean existsFolder(String path){
        File folder = new File(path);
        return folder.exists();
    }
    
    /**
     * Crea un directorio en la ruta que se pasa como argumento.
     * @param path Ruta donde crear el directorio. Parámetro requerido. No debe ser nulo
     * @param nameFolder Nombre del directorio a crear. Parámetro requerido. No debe ser nulo
     * @return true si ha podido crear el directorio; false en caso contrario.
     */
    public static boolean createFolder(String path, String nameFolder){
        if( TextUtils.esNuloVacio(path) || TextUtils.esNuloVacio(nameFolder) )
            throw new IllegalArgumentException("No se puede crear el directorio debido a que la ruta recibida o el nombre del directorio es nulo.");
        
        String absolutePath = path + "\\" + nameFolder;
        File folder = new File(absolutePath);

        boolean result = false;
        if(!folder.exists()){
            result = folder.mkdir();
        }
        return result;
    }
    
    /**
     * Crea un fichero.
     * @param fileInPath Nombre del fichero. Si la cadena contiene una ruta, el fichero se creará en dicha ruta.
     * En caso contrario, en fichero se generara en la raíz del proyecto. Parámetro requerido. No debe ser nulo.
     * @return true si ha podido crear el fichero; false en caso contrario
     */
    public static File createFile(String fileInPath){
        if( TextUtils.esNuloVacio(fileInPath))
            throw new IllegalArgumentException("No se puede crear el fichero debido a que la cadena recibida es nula.");
        return new File(fileInPath);
    }
    
     /**
     * Dada una ruta junto con el nombre del fichero, crea el fichero y escribe el contenido del segundo parámetro en él.
     * @param path Ruta y nombre del fichero.  Parámetro requerido. No debe ser nulo.
     * @param contentOfFile Contenido del fichero.  Parámetro requerido. No debe ser nulo.
     * @return Devuelve true si ha podido crear el fichero satisfactoriamente; false en caso contrario
     * @throws Exception Error al escribir en el fichero o cerrar el mismo.
     */
    public static boolean createFile(String path, String contentOfFile) throws Exception{
        if( TextUtils.esNuloVacio(path) || TextUtils.esNuloVacio(contentOfFile) )
            throw new IllegalArgumentException("No se puede crear el fichero debido a que la cadena recibida o el contenido a escribir es nulo.");
        boolean result = false;
        FileWriter fichero = null;
        
        File file = new File(path);
        if(!file.exists()){
            fichero = new FileWriter(path);
            fichero.write(contentOfFile);
            fichero.close();
            result = true;
        }
        if (null != fichero) fichero.close();

        return result;
    }
    
    /**
     * Elimina un fichero
     * @param fileInPath Ruta y nombre del fichero. Parámetro requerido. No debe ser nulo.
     * @return Devuelve true si se ha eliminado el fichero; false en caso contrario
     */
    public static boolean deleteFile(String fileInPath){
        if( TextUtils.esNuloVacio(fileInPath) )
            throw new IllegalArgumentException("No se puede eliminar el fichero debido a que la cadena recibida es nula.");
        File file = new File(fileInPath);
        return (file.exists()) ? file.delete() : false;
    }
    
    /**
     * Dado un fichero origen y una ruta de destino, copia el contenido del mismo y reemplaza la cadena que se recibe como tercer parámetro por la
     * cadena recibida como cuarto parámetro
     * @param sourceFile Fichero de origen. Parámetro requerido. No debe ser nulo.      
     * @param destinationFile Fichero de destino. Parámetro requerido. No debe ser nulo.
     * @param stringSearched Cadena a buscar para reemplazar. Parámetro requerido. No debe ser nulo.
     * @param stringReplace Cadena que reemplaza a la buscada. Parámetro requerido. No debe ser nulo,puede estar vacio
     * @throws IOException error producido al copiar el fichero.
     */
    public static void copyFileContentAndReplaceString(FileReader sourceFile, FileWriter destinationFile,String stringSearched ,String stringReplace ) throws IOException{
        if( sourceFile == null || destinationFile == null || TextUtils.esNuloVacio(stringSearched) || stringReplace == null)
            throw new IllegalArgumentException("No se copiar el fichero reemplazando su contenido debido a que alguno de los parámetros recibidos es nulo");
        
        List<CadenasBusquedaReemplazo> listaCadenas = new ArrayList();
        listaCadenas.add(new CadenasBusquedaReemplazo(stringSearched, stringReplace));
        copyFileContentAndReplaceString(sourceFile, destinationFile, listaCadenas);    
    }
    
    /**
     * Dado un fichero origen y una ruta de destino, copia el contenido del mismo reemplazando una lista de cadenas por el texto asociado a cada elemento de la lista.
     * @param sourceFile Fichero de origen. Parámetro requerido. No debe ser nulo.
     * @param destinationFile Fichero de destino. Parámetro requerido. No debe ser nulo.
     * @param cadenas   Lista de objetos CadenasBusquedaReemplazo en el que cada objeto contiene el texto a buscar y el texto por el que reemplazarlo.
     * Parámetro requerido. No debe ser nulo ni ser una lista vacía.
     * @throws IOException 
     */
    public static void copyFileContentAndReplaceString(FileReader sourceFile, FileWriter destinationFile,List<CadenasBusquedaReemplazo> cadenas) throws IOException{
        if( sourceFile == null || destinationFile == null || ( cadenas == null || cadenas.isEmpty()) )
            throw new IllegalArgumentException("No se copiar el fichero reemplazando su contenido debido a que alguno de los parámetros recibidos es nulo");
           
   	BufferedWriter bw = new BufferedWriter(destinationFile);
        bw.write("");
        
        BufferedReader br = new BufferedReader(sourceFile);
     
        String readString ="";
        while((readString = br.readLine())!=null) {
            for(CadenasBusquedaReemplazo cad : cadenas ){
               if(readString.contains(cad.getSearchedString())){
                   readString = readString.replaceAll(cad.getSearchedString(), cad.getReplaceString());
               } 
            }
            bw.write(readString);
            bw.newLine();          
        }
        br.close();	
        bw.close();
    }
    
    /**
     * Copia un fichero de una ruta origen dada a una destino
     * @param from Fichero origen. Parámetro requerido. No debe ser nulo.
     * @param to Fichero destino. Parámetro requerido. No debe ser nulo.
     * @throws IOException 
     */
    public static void copyFile(String from , String to) throws IOException{
        if( TextUtils.esNuloVacio(from) || TextUtils.esNuloVacio(to) )
            throw new IllegalArgumentException("No se ha podido copiar el fichero debido a que la cadena con la ruta origen"
                    + " o la cadena con la ruta destino es nula.");
        
        Path FROM = Paths.get(from);
        Path TO = Paths.get(to);
       //sobreescribir el fichero de destino, si existe, y copiar los atributos, incluyendo los permisos rwx
       CopyOption[] options = new CopyOption[]{
           StandardCopyOption.REPLACE_EXISTING,
           StandardCopyOption.COPY_ATTRIBUTES
       }; 
       Files.copy(FROM, TO, options);       
    }
    
    /**
     * Copia un directorio completo a otra ruta.
     * @param from Directorio de origen
     * @param to Directorio destino (si el directorio destino no existe, lo crea)
     * @throws Exception 
     */
    public static void copyDirectoryComplete(File from , File to) throws Exception{
        try {
            if (from.isDirectory()) { 
                if (!to.exists()) to.mkdir(); 

                    String[] hijos = from.list(); 
                    for (int i=0; i < hijos.length; i++) { 
                        copyDirectoryComplete(new File(from, hijos[i]), new File(to, hijos[i])); 
                    }
                } else { 
                    _copyFileContent(from, to); 
                }
        } catch (Exception e) {
                throw e;
        }   
    }
    
    /**
     * Copia el contenido de un fichero en otro
     * @param dirOrigen Fichero de origen
     * @param dirDestino Fichero de destino
     * @throws Exception 
     */
    private static void _copyFileContent(File dirOrigen, File dirDestino) throws Exception { 
        InputStream in = new FileInputStream(dirOrigen); 
        OutputStream out = new FileOutputStream(dirDestino); 

        byte[] buffer = new byte[1024];
        int len;

        try {
            while ((len = in.read(buffer)) > 0) { 
                    out.write(buffer, 0, len); 
            }
            out.flush();
        } catch (Exception e) {
                throw e;
        } finally {
                in.close(); 
                out.close(); 
        }
    }
    
    /**
     * Dada una ruta, devuelve los ficheros que se encuentren en ella cuya extensión coincida con la recibida como parámetro
     * @param path Ruta de la que listar los ficheros. Parámetro requerido. No debe ser nulo.
     * @param extension Extensión de los ficheros a buscar. Parámetro requerido. No debe ser nulo.
     * @return Array de nombres de ficheros encontrados con dicha extensión
     */
    public static String[] getListOfFilesByExtension(String path, String extension){
        if( TextUtils.esNuloVacio(path) || TextUtils.esNuloVacio(extension) )
            throw new IllegalArgumentException("No se han podido buscar los ficheros con determinada extensión debido a que la ruta de búsqueda"
                                                + "o el nombre de la extensión recibida es nulo");
        
    	FilenameFilter filter = new FilenameFilter() {
    	    @Override
    	    public boolean accept(File file, String name) {
	    	    if (name.endsWith(extension)) {
	    	        return true;
	    	    } else {
	    	        return false;
	    	    }   
    	    };
    	};
       File directorio = new File(path); //directorio a listar
       return directorio.list(filter);	
    }
    
    /**
     * Dado un path (absoluto o relativo) devuelve la ruta absoluta del mismo
     * @param path Nombre del path. Parámetro requerido. No debe ser nulo.
     * @return Ruta absoluta de dicho path; devuelve "" si no existe la ruta
     */
    public static String getAbsolutePath(String path){
        if( TextUtils.esNuloVacio(path))
            throw new IllegalArgumentException("No se puede buscar la ruta debido a que el nombre de ruta recibido es nulo");
        String absolutePath = "";
        File file = new File(path);
        if(file.exists()){
            absolutePath = file.getAbsolutePath();
        }
        return absolutePath;
    }
     
}
