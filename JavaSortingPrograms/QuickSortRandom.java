package JavaSortingPrograms;

import static JavaSortingPrograms.BubbleSort.randomArray;
import static JavaSortingPrograms.BubbleSort.printOfArray;
import  static JavaSortingPrograms.BubbleSort.replace;

import java.util.Random;

public class QuickSortRandom {

    public static void callQuickSortRandom(int[] A) {
        int stop = A.length - 1;
        System.out.println("Array after quick sort from position: "
                + 0 + " to position: " + stop);
        quickSortRandom(A, 0, stop);
    }

    public static void callQuickSortRandom(int[] A, int start, int stop) {
        if(!BubbleSort.isInRange(A, start, stop)) {
            start = 0;
            stop = A.length - 1;
        }
        System.out.println("Array after quick sort from position: "
                + start + " to position: " + stop);
        quickSortRandom(A, start, stop);
    }

    public static void quickSortRandom(int[] A, int start, int stop) {
        if (start < stop) {
            int random = randomPart(A, start, stop);
            quickSortRandom(A, start, random - 1);
            quickSortRandom(A,random + 1, stop);
        }
    }

    public static int randomPart(int[] A, int start, int stop) {
        Random rd = new Random();
        int random = rd.nextInt(stop + 1);

        while (random < start)
            random = rd.nextInt(stop + 1);

        replace(A, random, stop);
        return partition(A, start, stop);
    }

    public static int partition(int[] A, int start, int stop) {
        int i, j, last;

        last = A[stop];
        for (j = i = start; j < stop; j++) {
            if (A[j] <= last)
                replace(A, i++, j);
        }
        replace(A, i, stop);
        return i;
    }

    public static void main(String[] args) {
        int[] A = randomArray(10, 10);
        System.out.println("Array before quick sort");
        printOfArray(A);

        System.out.println("------------ 1 -----------");
        callQuickSortRandom(A, 2, 7);
        printOfArray(A);

        System.out.println("------------ 2 -----------");
        callQuickSortRandom(A);
        printOfArray(A);
    }
}
