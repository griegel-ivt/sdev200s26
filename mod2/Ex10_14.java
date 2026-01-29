package mod2;
import java.util.GregorianCalendar;

public class Ex10_14 {
    public static void main(String[] args) {
        MyDate date1 = new MyDate();
        MyDate date2 = new MyDate(34355555133101L);

        System.out.println("date1 year: " + date1.getYear());
        System.out.println("date1 month: " + date1.getMonth());
        System.out.println("date1 day: " + date1.getDay());
        System.out.println("date2 year: " + date2.getYear());
        System.out.println("date2 month: " + date2.getMonth());
        System.out.println("date2 day: " + date2.getDay());
    }
}

class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate() {
        setDate(System.currentTimeMillis());
    }
    public MyDate(long elapsedTime) {
        setDate(elapsedTime);
    }
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }
    public int getMonth() {
        return month;
    }
    public int getDay() {
        return day;
    }
    public final void setDate(long elapsedTime) {
        GregorianCalendar greg = new GregorianCalendar();
        greg.setTimeInMillis(elapsedTime);
        year = greg.get(GregorianCalendar.YEAR);
        month = greg.get(GregorianCalendar.MONTH);
        day = greg.get(GregorianCalendar.DAY_OF_MONTH);
    }
}
