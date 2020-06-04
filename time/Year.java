package time;

public class Year {
    private int year = 1;

    public int getYear() {
        return year;
    }

    public static boolean isValidYear (int year) {
        return year >= 0;
    }

    public void setYear(int year) {
        if (isValidYear(year))
            this.year = year;
        else {
            System.out.println("The year cannot be lees than zero");
        }
    }

    public void setFutureYears(int year) {
        this.year += year;
        if (!isValidYear(this.year))  {
            System.out.println("The year cannot be lees than zero");
            this.year = 0;
        }
    }
}

