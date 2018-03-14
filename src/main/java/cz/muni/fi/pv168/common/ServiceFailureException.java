package cz.muni.fi.pv168.common;

/**
 * This exception indicates service failure.
 *
 * @author Katarina Bulkova
 */
public class ServiceFailureException extends RuntimeException {

    /**
     * Creates new instance of
     * <code>IllegalEntityException</code> with detail message.
     *
     * @param msg
     */
    public ServiceFailureException(String msg) {
        super(msg);
    }

    public ServiceFailureException(Throwable cause) {
        super(cause);
    }

    public ServiceFailureException(String message, Throwable cause) {
        super(message, cause);
    }

}
