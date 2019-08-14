package es.envconf.app.model.data.dao.exception;

/**
 * Excepcion que puede causar las clases de los entornos.
 */
public class EnvironmentsDAOException extends Exception{
    
    public EnvironmentsDAOException(String msg){
        super(msg);
    }
    
    public EnvironmentsDAOException(Throwable cause ){
        super(cause);
    }
    
    public EnvironmentsDAOException(String msg, Throwable cause ){
        super(msg, cause);
    }

}
