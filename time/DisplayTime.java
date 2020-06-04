package time;

public class DisplayTime {

    public static String toString(Second s) {
        String hour = "" + s.getHour();
        String minute = "" + s.getMinute();
        String second = "" + s.getSecond();

        if (s.getHour() < 10)
            hour = "0" + s.getHour();
        if(s.getMinute() < 10)
            minute = "0" + s.getMinute();
        if(s.getSecond() < 10)
            second = "0" + s.getSecond();

        return hour + ":" + minute + ":" + second
            + '\n' + s.getDayName() + '\n' +
            s.getDay() + s.getMonthName() + s.getYear();
    }

    public static void main(String [] args) {
        Second s = new Second();
        System.out.println(DisplayTime.toString(s));

        System.out.println("------------ 1 -----------");
        s.setFutureDays(15);
        System.out.println(DisplayTime.toString(s));

        System.out.println("------------ 2 -----------");
        s.setFutureSeconds(63746352000L);
        System.out.println(DisplayTime.toString(s));

        System.out.println("------------ 3 -----------");
        s.setYear(2020);
        s.setFutureDays(29);
        s.setFutureMinutes(5);
        s.setFutureHours(6);
        s.setSecond(69);
        System.out.println(DisplayTime.toString(s));

        System.out.println("------------ 4 -----------");
        s.setYear(2019);
        System.out.println(DisplayTime.toString(s));
    }


}
