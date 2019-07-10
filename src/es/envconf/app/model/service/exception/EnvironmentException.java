package es.envconf.app.model.service.exception;

/**
 * Excepcion que puede causar las clases de los entornos.
 */
public class EnvironmentException extends Exception{

    public EnvironmentException(String msg){
        super(msg);
    }

    public EnvironmentException(Throwable cause ){
        super(cause);
    }

    public EnvironmentException(String msg, Throwable cause ){
        super(msg, cause);
    }

}