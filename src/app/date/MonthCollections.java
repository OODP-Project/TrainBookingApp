package app.date;

import java.util.ArrayList;
import java.util.List;

public class MonthCollections {

    private List<Month> months = new ArrayList<>();

    public MonthCollections(){
        months.add(new Month("January", 31));
        months.add(new Month("February", 28));
        months.add(new Month("March", 31));
        months.add(new Month("April", 30));
        months.add(new Month("May", 31));
        months.add(new Month("June", 30));
        months.add(new Month("July", 31));
        months.add(new Month("August", 31));
        months.add(new Month("September", 30));
        months.add(new Month("October", 31));
        months.add(new Month("November", 30));
        months.add(new Month("December", 31));
    }

    public Month getMonth(int num) throws MonthNumException {
        try {
            return months.get(num);
        } catch (IndexOutOfBoundsException e){
            throw new MonthNumException("Num of month must be in range 1- 12");
        }
    }

}
