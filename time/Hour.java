package time;

public class Hour extends Day {
    private int hour = 0;

    public int getHour() {
        return hour;
    }
    
    public void setHour(int hour) {
        if(isValidHour(hour))
            this.hour = hour;
        else
            System.out.println("Set hour between 0 and 23");
    }

    public static boolean isValidHour(int hour) {
        return (hour >= 0 && hour < 24);
    }
    
    public void setFutureHours(int hour) {
        int sumHour = this.hour + hour;
        if (sumHour < 0)
            System.out.println("The hour cannot be less than zero." +
                    "You can use setHour() to change it");
        else {
            this.hour = sumHour % 24;

            if (sumHour - this.hour > 23)
                setFutureDays((sumHour) / 24);
        }
    }
}
