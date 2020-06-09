package time;

public class Minute extends Hour {
    private int minute = 0;

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if(isValidMinute(minute))
            this.minute = minute;
        else
            System.out.println("Set minute between 0 and 59");
    }

    public static boolean isValidMinute(int minute) {
        return (minute >= 0 && minute < 60);
    }

    public void setFutureMinutes(int minute) {
        int sumMinute = this.minute + minute;

        if(sumMinute < 0)
            System.out.println("The minute cannot be less than zero." +
                    "You can use setMinute() to change it");
        else {
            this.minute = sumMinute % 60;

            if((sumMinute - this.minute) > 59)
                setFutureHours(sumMinute / 60);
        }
    }
}
