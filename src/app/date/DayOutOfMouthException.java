package app.date;

/**
 * An exception to be thrown when get day out of range of the mount in getDay() of class Month
 */
public class DayOutOfMouthException extends Exception{

    public DayOutOfMouthException(String message){
        super(message);
    }
}
