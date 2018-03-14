package cz.muni.fi.pv168.common;

/**
 * This exception indicates service failure.
 *
 * @author Katarina Bulkova
 */
public class ServiceFailureException extends RuntimeException {

    /**
     * Creates an instance of
     * <code>IllegalEntityException</code> with detail message.
     *
     * @param msg detail message
     */
    public ServiceFailureException(String msg) {
        super(msg);
    }

    /**
     * Creates an instance of
     * <code>IllegalEntityException</code> with detail cause.
     *
     * @param cause detail cause
     */
    public ServiceFailureException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates an instance of
     * <code>IllegalEntityException</code> with detail message and cause.
     *
     * @param message detail message
     * @param cause detail cause
     */
    public ServiceFailureException(String message, Throwable cause) {
        super(message, cause);
    }

}
