package cz.muni.fi.pv168.common;

/**
 * This exception is thrown when you try to use an entity that can not be used for the operation.
 *
 * @author Katarina Bulkova
 */
public class IllegalEntityException extends RuntimeException {

    /**
     * Creates new instance of
     * <code>IllegalEntityException</code> without detail message.
     *
     */
    public IllegalEntityException() {
    }

    /**
     * Constructs an instance of
     * <code>IllegalEntityException</code> with the specified detail
     * message.
     *
     * @param msg detail message
     */
    public IllegalEntityException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of
     * <code>IllegalEntityException</code> with the specified detail
     * message and cause.
     *
     * @param msg detail message
     * @param throwable the cause
     */
    public IllegalEntityException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
