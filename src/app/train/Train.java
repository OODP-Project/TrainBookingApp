package app.train;

import app.Destination;

public class Train {

    private int seatNum;
    private AbstractTrainClass c;
    private Destination destination;

    public int getPrice() {
        return c.getIncludePrice() + destination.getPrice();
    }

    public void setTrainClass(AbstractTrainClass c) {
        this.c = c;
    }

    public void setSeatNum(int num) throws SeatNumNotFoundException {
        if (num < 1 || num > c.getNumSeats()){
            throw new SeatNumNotFoundException("Doesn't have this seat number");
        }

        this.seatNum = num;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public ITrainClass getTrainClass() {
        return c;
    }

    public void setDestination(Destination destination){
        this.destination = destination;
    }

    public Destination getDestination() {
        return destination;
    }
}
