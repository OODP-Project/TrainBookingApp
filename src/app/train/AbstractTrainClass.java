package app.train;

public abstract class AbstractTrainClass implements ITrainClass{

    private int includePrice;
    private int numSeats;

    public AbstractTrainClass(int includePrice, int numSeat){
        this.includePrice = includePrice;
        this.numSeats = numSeat;
    }

    @Override
    public int getIncludePrice() {
        return includePrice;
    }

    @Override
    public int getNumSeats() {
        return numSeats;
    }

    @Override
    public abstract String toString();
}
