package JavaSortingPrograms;

import java.util.Random;

public class BubbleSort {

    public static void callBubbleSort(int[] A, int start, int stop) {
        if (!isInRange(A, start, stop)) {
            start = 0;
            stop = A.length - 1;
        }
        bubbleSort(A, start, stop);
    }

    public static void callBubbleSort(int[] A) {
        bubbleSort(A, 0, A.length - 1);
    }

    public static boolean isInRange(int[] A,  int start, int stop) {
        int numberOfElements = A.length;
        if (start < 0 || start >= numberOfElements) {
            System.out.println("Start position: " + start + " out of array range");
            if (stop < start || stop >= numberOfElements)
                System.out.println("Stop position: " + stop + " out of array range");
            return false;
        } else if (stop < start || stop >= numberOfElements) {
            System.out.println("Stop position: " + stop + " out of array range");
            return false;
        } else
            return true;
    }

    public static void bubbleSort(int[] A, int start, int stop) {
        System.out.println("Array after bubble sort from position: "
                + start + " to position: " + stop);

        for(int i = stop; i > start; i--)
            for (int j = start; j < i; j++)
                if (A[j] > A[j + 1])
                    replace(A, j, j + 1);
    }

    public static void replace(int[]A, int first, int second) {
        int temp = A[first];
        A[first] = A[second];
        A[second] = temp;
    }

    public static void callBubbleSortGuard(int[] A, int start, int stop) {
        if (!isInRange(A, start, stop)) {
            start = 0;
            stop = A.length - 1;
        }
        bubbleSortGuard(A, start, stop);
    }

    public static void callBubbleSortGuard(int[] A) {
        bubbleSortGuard(A, 0, A.length - 1);
    }

    public static void bubbleSortGuard(int[] A, int start, int stop) {
        System.out.println("Array after bubble sort (guard) from position: "
                + start + " to position: " + stop);

        int guard;
        while (stop > start) {
            guard = start;
            for (int j = start; j < stop; j++)
                if (A[j] > A[j + 1]) {
                    replace(A, j, j + 1);
                    guard = j;
                }
            stop = guard;
        }
    }

    public static int[] randomArray(int length, int range) {
        int[] A = new int[length];
        int repeatedPosition = -1, i, amountOfNegativeValues;
        Random rd = new Random();
        for (i = 0; i < length; i++)
            A[i] = rd.nextInt(range + 1);

        amountOfNegativeValues = rd.nextInt(length);
        for (i = 1; i < amountOfNegativeValues; i++) {
            int positionNegative = rd.nextInt(length);
            while (positionNegative == repeatedPosition)
                positionNegative = rd.nextInt(length);

            A[positionNegative] = A[positionNegative] * -1;
            repeatedPosition = positionNegative;
        }
        return A;
    }

    public static void printOfArray(int[] A) {
        for (int i : A)
            System.out.print(i + " ");

        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = randomArray(10, 23);
        System.out.println("Array before bubble sort");
        printOfArray(A);

        System.out.println("------------ 1 -----------");
        callBubbleSort(A, 2, 7);
        printOfArray(A);

        System.out.println("------------ 2 -----------");
        callBubbleSort(A);
        printOfArray(A);

        System.out.println("------------ 3 -----------");
        A = randomArray(10, 10);
        System.out.println("Array before bubble sort (guard)");
        printOfArray(A);

        System.out.println("------------ 4 -----------");
        callBubbleSortGuard(A, 2, 7);
        printOfArray(A);

        System.out.println("------------ 5 -----------");
        callBubbleSortGuard(A);
        printOfArray(A);
    }
}
