package es.envconf.app.model.applicationServer.exception;

/**
 * Excepcion que puede causar las clases de los entornos.
 */
public class AppServerException extends Exception{
    
    public AppServerException(){
        super();
    }
    
    public AppServerException(String msg){
        super(msg);
    }
    
     public AppServerException(Throwable cause ){
        super(cause);
    }   
    
    public AppServerException(String msg, Throwable cause ){
        super(msg, cause);
    }

}
