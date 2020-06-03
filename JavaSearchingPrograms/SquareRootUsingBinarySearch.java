package JavaSearchingPrograms;

public class SquareRootUsingBinarySearch {

    public static double squareRoot(double value) {
        double upper = getUpperRange(value);
        double lower = getLowerRange(value, upper);

        double mid = (upper + lower) / 2;
        double square = mid * mid;
        while (lower < upper && square != value) {
            if (square < value)
                lower = mid + 0.000000000000001;
            else
                upper = mid - 0.000000000000001;
            mid = (upper + lower) / 2;
            square = mid * mid;
        }
        return mid;
    }

    public static double getUpperRange(double value) {
        double upper = 0;
        while (upper * upper < value)
            upper++;

        return upper;
    }

    public static double getLowerRange(double value, double upper) {
        while (upper * upper > value)
            upper--;
        return upper;
    }

    public static void main(String[] args) {
        double value = 9.5;

        System.out.println("--------- 1 ----------");
        System.out.println("Math.sqrt of " + value + " is " + Math.sqrt(value));

        System.out.println("--------- 2 ----------");
        System.out.println("squareRoot of " + value + " is " + squareRoot(value));
    }
}
