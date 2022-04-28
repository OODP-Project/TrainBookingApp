package app.date;

/**
 * An exception to be thrown when create time that over 24 over or less than 0 hour
 * And when minutes are more than 60 or less than 0 minutes
 */
public class OverTimeException extends Exception {

    public OverTimeException(String message){
        super(message);
    }
}
