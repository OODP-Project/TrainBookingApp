package app.date;

public class Month {

    private String name;
    private int dayRange;

    public Month(String name, int dayRange){
        this.name = name;
        this.dayRange = dayRange;
    }

    public int getDay(int dayNum) throws DayOutOfMouthException {
        if (dayNum > dayRange || dayRange < 1){
            throw new DayOutOfMouthException("This mount doesn't have this day number");
        }

        return dayNum;
    }

    public String getDayRange(){
        return "1-" + dayRange;
    }

    @Override
    public String toString(){
        return name;
    }

}
