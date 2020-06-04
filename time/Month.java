package time;

public class Month extends Year {
    private String monthName = " January ";
    private int month = 1;

    public int getMonth() {
        return month;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonth(int month) {
        if (isValidMonth(month)) {
            this.month = month;
            this.monthName = getMonthName(month);
        }
        else
            System.out.println("Set month between 1 and 12");
    }

    public void setFutureMonths(int month) {
        int sumMonth = this.month + month;

        if(sumMonth < 0)
            System.out.println("The month cannot be less than zero." +
                    "You can use setMonth() to change it");
        else {
            if (sumMonth % 12 == 0)
                this.month = 12;
                //setMonth(12);
            else
                this.month = sumMonth % 12;
            //setMonth(sumMonth % 12);
            this.monthName = getMonthName(this.month);

            if (sumMonth - this.month > 11)
                setFutureYears((sumMonth) / 12);
        }
    }

    public static boolean isValidMonth (int month) {
        return (month > 0 && month < 13);
    }

    public static String getMonthName(int month) {
        String monthName = "";
        switch (month) {
            case 1 : return " January ";
            case 2:  return " February ";
            case 3:  return  " March ";
            case 4:  return " April ";
            case 5:  return " May ";
            case 6:  return " June ";
            case 7:  return " July ";
            case 8:  return " August ";
            case 9:  return " September ";
            case 10: return " October ";
            case 11: return " November ";
            case 12: return " December ";
        }
        return monthName;
    }
}
