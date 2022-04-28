package app.train;

/**
 * An exception to be thrown when set number of the seat out of range of the train class in setSeatNum in class Train
 */
public class SeatNumNotFoundException extends Exception{

    public SeatNumNotFoundException(String message){
        super(message);
    }
}
