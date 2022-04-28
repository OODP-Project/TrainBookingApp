package app.date;

import app.date.OverTimeException;

public class Time {

    private int hour;
    private int minutes;

    public Time(int hour, int minutes) throws OverTimeException {

        if (hour > 24 || hour < 0){
            throw new OverTimeException("Hour will be in range 0-24");
        }

        if (minutes > 60 || minutes < 0){
            throw new OverTimeException("Minutes will be in range 0-60");
        }

        this.hour = hour;
        this.minutes = minutes;
    }

    @Override
    public String toString(){
        return hour + " : " + minutes;
    }
}
