package time;

public class Day extends Month {
    private int day = 1;
    private String dayName = "Monday";

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

    public int getMonthDaysNumber(int month, int year) {
        int monthDays = 0;
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                monthDays = 31;
                break;
            case 4: case 6: case 9: case 11:
                monthDays = 30;
                break;
            case 2:
                monthDays = 28;
                if (isALeapYear(year))
                    monthDays = 29;
                break;
        }
        return monthDays;
    }

    public boolean isALeapYear(int year) {
        return ((year % 4 == 0 && !(year % 100 == 0)) || (year % 400 == 0));
    }

    public String getDayName(int day) {
        switch(day) {
            case 0: return "Sunday";
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
        }
        return "";
    }

    public int getDaysNumber() {
        int sumDays = this.day;
        int month = 1;
        int year = 1;
        int monthCounter = 12;
        int monthNumber = getYear() * 12 + getMonth() - 1;

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
}
