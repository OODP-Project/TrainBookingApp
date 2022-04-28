package app;

import app.date.*;
import app.train.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BookingTrainApp {

    private static final String DESTINATIONS_PATH = "destinations.dat";

    private final Scanner input = new Scanner(System.in);
    private final List<Destination> destinations = new ArrayList<>();
    private String userName;
    private final Date date = new Date();
    private final Train train = new Train();
    private Ticket ticket;

    public BookingTrainApp() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(DESTINATIONS_PATH))){
            reader.lines().forEach((line) -> {
                String[] data = line.split("//");
                String destName = data[0];
                int price = Integer.parseInt(data[1]);
                ArrayList<Time> times = new ArrayList<>();
                for (int i = 2; i <data.length; i++){
                    String[] timeSplit = data[i].split(":");
                    int hour = Integer.parseInt(timeSplit[0]);
                    int minutes = Integer.parseInt(timeSplit[1]);
                    try {
                        times.add(new Time(hour, minutes));
                    } catch (OverTimeException e) {
                        e.printStackTrace();
                    }
                }
                destinations.add(new Destination(destName, price, times));
            });
        }
    }

    public void start(){
        inputCustomer();
        selectDestination();
        selectDateTime();
        selectTrainClass();
        selectSeat();
        boolean confirmed = conFirmInformation();
        if (confirmed) {
            addPayment();
        } else {
            start();
        }
    }

    public void inputCustomer(){
        do {
            System.out.print("Enter your name : ");
            userName = input.nextLine();

            if (userName.isBlank()){
                System.out.println("You have your name");
            }
        } while (userName.isBlank());
    }

    public void selectDestination(){
        System.out.println("------------destinations----------");
        for (Destination destination : destinations)
            System.out.println((destinations.indexOf(destination)+1) + ".  " + destination.getName() + " " + destination.getPrice() + " baht");
        System.out.println("----------------------------------");

        // Input number of the destination
        System.out.print("Select number of destination : ");
        do {
            try {
                int desNum = Integer.parseInt(input.nextLine()) - 1;
                train.setDestination(destinations.get(desNum));
                break;
            } catch (NumberFormatException e){
                System.out.print("Please enter number : ");
            } catch (IndexOutOfBoundsException e){
                System.out.print("Please enter correct destination number : ");
            }
        } while (true);
    }

    public void selectDateTime() {
        // Get time
        selectMonth();
        selectDay();
        selectTime();
    }

    public void selectMonth(){
        MonthCollections months = new MonthCollections();
        // Get month
        System.out.println("-----------Select date------------");
        System.out.print("Select mount number : ");
        while (true) {
            try {
                int mountNum = Integer.parseInt(input.nextLine()) - 1;
                date.setMonth(months.getMonth(mountNum));
                break;
            } catch (NumberFormatException e) {
                System.out.print("Please enter number : ");
            } catch (MonthNumException e) {
                System.out.println(e.getMessage());
                System.out.print("Try again : ");
            }
        }
    }

    public void selectDay(){
        // Get day
        System.out.print("Select day in " + date.getMonth() + " (" + date.getMonth().getDayRange() + ") : ");
        while (true) {
            try {
                int dayNum = Integer.parseInt(input.nextLine()) - 1;
                date.setDay(date.getMonth().getDay(dayNum));
                break;
            } catch (DayOutOfMouthException e) {
                System.out.println(e.getMessage());
                System.out.print("Try again : ");
            }
        }
    }

    public void selectTime(){
        List<Time> destinationTimes = train.getDestination().getTimes();

        System.out.println("----------------------------------");
        System.out.println("Please select depart time");
        for (Time time : destinationTimes)
            System.out.println((destinationTimes.indexOf(time)+1) + ". " + time);
        System.out.println("----------------------------------");
        System.out.print("Select : ");

        while (true) {
            try {
                int time = Integer.parseInt(input.nextLine()) - 1;
                date.setTime(destinationTimes.get(time));
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please select the correct number of time");
                System.out.print("Try again : ");
            }
        }
    }

    public void selectTrainClass(){
        AbstractTrainClass[] trainClasses = new AbstractTrainClass[] {new NormalClassTrain(), new HighClassTrain(), new LowerClassTrain()};

        System.out.println("------------Train classes---------");
        System.out.println("1. normal class +" + trainClasses[0].getIncludePrice() + " Baht");
        System.out.println("2. high class +" + trainClasses[1].getIncludePrice() + " Baht");
        System.out.println("3. lower class +" + trainClasses[2].getIncludePrice() + " Baht");
        System.out.println("----------------------------------");

        System.out.print("Select train class : ");
        do {
            try {
                int trainClassNum = Integer.parseInt(input.nextLine()) - 1;
                train.setTrainClass(trainClasses[trainClassNum]);
                break;
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
                System.out.println("Please select correct number of class");
                System.out.print("Try again : ");
            }
        } while (true);
    }

    public void selectSeat(){
        System.out.println("------------Seat number-----------");
        System.out.print("Please select seat number 1 to " + train.getTrainClass().getNumSeats() + " : ");
        do {
            try {
                train.setSeatNum(Integer.parseInt(input.nextLine()));
                break;
            } catch (NumberFormatException | SeatNumNotFoundException e){
                System.out.println("Please select correct number of seat");
                System.out.print("Try again : ");
            }
        } while (true);

        ticket = new Ticket(userName, date, train);
    }

    public boolean conFirmInformation(){
        ticket.displayInformation();
        System.out.print("Confirm (y/n) ? : ");
        String confirm;
        do {
            confirm = input.nextLine().toLowerCase();

            if (!(Objects.equals(confirm, "y") || Objects.equals(confirm, "n"))){
                System.out.print("Please select only 'y' or 'n' : ");
            }

        } while (!(Objects.equals(confirm, "y") || Objects.equals(confirm, "n")));

        return confirm.equals("y");
    }

    public void addPayment(){
        int money = 0;

        System.out.println("----------------------------------");
        while (money < train.getPrice()){
            System.out.print("Please pay amount " + (train.getPrice() - money) + " : ");
            try {
                money += Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e){
                System.out.println("Please enter number!");
            }
        }

        if (money > train.getPrice()){
            System.out.println("This is your change " + (money - train.getPrice()) + " baht");
        }

        System.out.println("Thank you");
        System.out.println("Hope you have a good trip!");
        ticket.displayInformation();
    }

    public static void main(String[] args) throws IOException {
        BookingTrainApp app = new BookingTrainApp();
        app.start();
    }
}
