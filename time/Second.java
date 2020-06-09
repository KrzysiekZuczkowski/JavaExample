package time;

public class Second extends Minute{
    private int second = 0;

    public int getSecond() {
        return second;
    }
    
    public void setSecond(int second) {
        if(isValidSecond(second))
            this.second = second;
        else
            System.out.println("Set second between 0 and 59");
    }

    public static boolean isValidSecond(int second) {
        return (second >= 0 && second < 60);
    }
    
    public void setFutureSeconds(long second) {
        long sumSecond = this.second + second;

        if(sumSecond < 0)
            System.out.println("The second cannot be less than zero." +
                    "You can use setSecond() to change it");
        else {
            this.second = (int) sumSecond % 60;

            if(sumSecond - this.second > 59)
                setFutureMinutes((int) (sumSecond / 60));
        }
    }
}
