package app;

import app.date.OverTimeException;
import app.date.Time;

import java.util.List;


public class Destination {

    private String name;
    private int price;
    private List<Time> times;

    public Destination(String name, int price, List<Time> times){
        this.name = name;
        this.price = price;
        this.times = times;
    }

    public List<Time> getTimes() {
        return times;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
