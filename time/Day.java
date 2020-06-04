package time;

public class Day extends Month {

    private String dayName = "Saturday";
    private int day = 1;

    public int getDay() {
        return day;
    }

    public String getDayName() {
        return dayName;
    }

    public void setMonth(int month) {
        super.setMonth(month);
        setFutureDays(0);
    }

    public void setYear(int year) {
        super.setYear(year);
        setFutureDays(0);
    }

    public int getDaysNumber() {
        int sumDays = this.day;
        int month = 1;
        int year = 0;
        int monthCounter = 1;
        //int monthDays = getMonthDaysNumber(month, year);
        int monthNumber = getYear() * 12 + getMonth();

        while (monthCounter < monthNumber) {
            sumDays += getMonthDaysNumber(month++, year);
            if (month == 13) {
                month = 1;
                year++;
            }
            monthCounter++;
        }
        return sumDays;
    }

    public void setDay(int day) {
        int monthDays = getMonthDaysNumber(getMonth(), getYear());
        if (day > 0 && day <= monthDays) {
            this.day = day;
            this.dayName = getDayName(getDaysNumber() % 7);
        }
        else
            System.out.println("Set day between 1 to " + monthDays);
    }

    public void setFutureDays(int day) {
        int sumDay = this.day + day;
        if (sumDay < 1)
            System.out.println("The day cannot be less than one." +
                    "You can use setDay() to change it");
        else {
            int monthNumber = getMonth();
            int yearNumber = getYear();
            int monthCounter = 0;
            int monthDays = getMonthDaysNumber(monthNumber, yearNumber);

            while (sumDay > monthDays) {
                sumDay -= monthDays;
                monthNumber++;
                monthCounter++;
                if (monthNumber == 13) {
                    monthNumber = 1;
                    yearNumber++;
                }
                monthDays = getMonthDaysNumber(monthNumber, yearNumber);
            }

            this.day = sumDay;
            if (monthCounter > 0)
                setFutureMonths(monthCounter);
            this.dayName = getDayName(getDaysNumber() % 7);
        }
    }

    public static int getMonthDaysNumber(int month, int year) {
        int monthDays = 0;
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                monthDays = 31;
                break;
            case 4: case 6: case 9: case 11:
                monthDays = 30;
                break;
            case 2:
                if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0))
                    monthDays = 29;
                else
                    monthDays = 28;
                break;
        }
        return monthDays;
    }

    public static String getDayName(int day) {
        String dayName = "";
        switch(day) {
            case 0: return "Friday";
            case 1: return "Saturday";
            case 2: return "Sunday";
            case 3: return "Monday";
            case 4: return "Tuesday";
            case 5: return "Wednesday";
            case 6: return "Thursday";
        }
        return dayName;
    }
}
