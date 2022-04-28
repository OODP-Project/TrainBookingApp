package app.train;

import app.date.Date;
import app.train.Train;

public class Ticket {

    private String customerName;
    private Date date;
    private Train train;

    public Ticket(String customerName, Date date, Train train){
        this.customerName = customerName;
        this.date = date;
        this.train = train;
    }

    public void displayInformation(){
        System.out.println("---------- Train ticket ----------");
        System.out.println("customer : " + customerName);
        System.out.println("Train class : " + train.getTrainClass());
        System.out.println("Destination : " + train.getDestination().getName());
        System.out.println("Depart date : " + date.getDay() + " " + date.getMonth());
        System.out.println("Depart time : " + date.getTime());
        System.out.println("seat number : " + train.getSeatNum());
        System.out.println("Price : " + train.getPrice() + " baht");
        System.out.println("-----------------------------------");
    }
}
