package JavaSearchingPrograms;

public class LogarithmUsingBinarySearch {

    public static double logarithm(double base, double value) {
        double upper = 0, lower = 0, mid = 0;

        if (checkBase(base)) {
            if (checkValue(value)) {
                if (value < 1) {
                    lower = getLowerRange(base, value, 0);
                    upper = getUpperRange(base, value, lower);
                }
                else {
                    upper = getUpperRange(base, value, 0);
                    lower = getLowerRange(base, value, upper);
                }
                mid = (upper + lower) / 2;
                double log = Math.pow(base, mid);
                while (lower < upper && log != value) {
                    if (log < value)
                        lower = mid + 0.000000000000001;
                    else
                        upper = mid - 0.000000000000001;
                    mid = (upper + lower) / 2;
                    log = Math.pow(base, mid);
                }
            }
            else
                System.out.println("Value must be greater than zero");
        }
        else
            System.out.println("Base must be greater than zero " +
                    "and different from one");

        return mid;
    }

    public static boolean checkBase(double base) {
        return base > 0 && base != 1;
    }

    public static boolean checkValue(double value) {
        return value > 0;
    }

    public static double getUpperRange(double base, double value, double startValue) {
        while (Math.pow(base, startValue) < value)
            startValue++;
        return startValue;
    }

    public static double getLowerRange(double base, double value, double startValue) {
        while (Math.pow(base, startValue) > value)
            startValue--;
        return startValue;
    }

    public static void main(String[] args) {
        double base = 10;
        double value = 12;

        System.out.println("--------- 1 ----------");
        System.out.println("(Math.Log10()) base 10 logarithm of " + value +
                " is " + Math.log10(value));

        System.out.println("--------- 2 ----------");
        System.out.println("(logarithm()) base " + base + " logarithm of " + value +
                " is " + logarithm(base, value));

        System.out.println("--------- 3 ----------");
        base = 2.5;
        value = 8;
        System.out.println("(logarithm()) base " + base + " logarithm of " + value +
                " is " + logarithm(base, value));

        System.out.println("--------- 4 ----------");
        base = 2;
        System.out.println("(logarithm()) base " + base + " logarithm of " + value +
                " is " + logarithm(base, value));
    }
}
