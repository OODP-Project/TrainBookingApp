package app.date;

public class Date {

    private int day;
    private Month month;
    private Time time;

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Month getMonth() {
        return month;
    }

    public void setTime(Time time){
        this.time = time;
    }

    public Time getTime(){
        return time;
    }

}
