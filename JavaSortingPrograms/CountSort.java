package JavaSortingPrograms;

import static JavaSortingPrograms.BubbleSort.randomArray;
import static JavaSortingPrograms.BubbleSort.printOfArray;

public class CountSort {

    public static void callCountSortArrays(int[] A) {
        arraysForCountSort(A, 0, A.length - 1);
    }

    public static void callCountSortArrays(int[] A, int start, int stop) {
        if (!BubbleSort.isInRange(A, start, stop)) {
            start = 0;
            stop = A.length - 1;
        }
        arraysForCountSort(A, start, stop);
    }

    public static void arraysForCountSort(int[] A, int start, int stop) {
        System.out.println("Array after count sort from position: "
                + start + " to position: " + stop);

        int i;
        int numberOfNegatives = getNumberOfNegative(A, start, stop);
        int numberOfPositives = getNumberOfPositive(A, start, stop);
        int[] tempNegative = new int[numberOfNegatives];
        int[] tempPositive = new int[numberOfPositives];
        int tN = 0, tP = 0;

        for (i = start; i <= stop; i++)
            if (A[i] < 0)
                tempNegative[tN++] = A[i];
            else
                tempPositive[tP++] = A[i];

        if (numberOfNegatives > 0) {
            for (i = 0; i < numberOfNegatives; i++)
                tempNegative[i] *= - 1;

            tempNegative = countSort(tempNegative);

            reverse(tempNegative, numberOfNegatives);

            for (i = 0; i < numberOfNegatives; i++)
                tempNegative[i] *= -1;

            for (i = 0; i < numberOfNegatives; i++)
                A[start++] = tempNegative[i];
        }
        if (numberOfPositives > 0) {

            tempPositive = countSort(tempPositive);

            for (i = 0; i < numberOfPositives; i++)
                A[start++] = tempPositive[i];
        }
    }

    public static int getNumberOfNegative(int[] A, int start, int stop) {
        int negative = 0;
        for(int i = start; i <= stop; i++)
            if(A[i] < 0)
                negative++;

        return negative;
    }

    public static int getNumberOfPositive(int[] A, int start, int stop)                {
        int positive = 0;
        for (int i = start; i <= stop; i++)
            if (A[i] >= 0)
                positive++;

        return positive;
    }

    public static int[] countSort(int[] A) {
        int start = 0;
        int stop = A.length - 1;
        int max = getMaximum(A, start, stop);
        int i;
        int[] C = new int[max + 1];
        int[] sortedArr = new int[stop + 1];

        for (int j : A)
            C[j] = C[j] + 1;

        for (i = 1; i <= max; i++)
            C[i] = C[i - 1] + C[i];

        for (i = stop; i >= 0; i--) {
            sortedArr[C[A[i]] - 1] = A[i];
            C[A[i]] = C[A[i]] - 1;
        }
        return sortedArr;
    }

    public static int getMaximum(int[] A, int start, int stop) {
        int max = A[start];
        for (int i = start + 1; i <= stop; i++)
            if (A[i] > max)
                max = A[i];

        return max;
    }

    public static void reverse(int[] A, int arrayLength) {
        int n = arrayLength / 2;
        for (int i = 0; i < n; i++) {
            int temp = A[i];
            A[i] = A[arrayLength - 1 - i];
            A[arrayLength - 1 - i] = temp;
        }
    }

//------------------------------------------------------------------------------------------------------

    public static void callCountSortExample2(int[] A) {
        countSortExample2(A, 0, A.length - 1);
    }

    public static void callCountSortExample2(int[] A, int start, int stop) {
        if (!BubbleSort.isInRange(A, start, stop)) {
            start = 0;
            stop = A.length - 1;
        }
        countSortExample2(A, start, stop);
    }

    public static void countSortExample2(int[] A, int start, int stop) {
        System.out.println("Array after count sort (example2) from position: "
                + start + " to position: " + stop);

        int amountOfElementsBArr = stop - start + 1;
        int[] B = new int[amountOfElementsBArr];
        int i;
        int min = getMinimum(A, start, stop);
        int max = getMaximum(A, start, stop);

        if (min < 0) {
            min = getAbsolute(min);
            for (i = start; i <= stop; i++)
                A[i] += min;
        } else
            min = 0;

        int amountOfElementsCArr = min + max + 1;
        int[] C = new int[amountOfElementsCArr];

        for (i = start; i <= stop; i++)
            C[A[i]] += 1;

        for (i = 1; i < amountOfElementsCArr; i++)
            C[i] = C[i - 1] + C[i];

        for (i = stop; i >= start; i--) {
            B[C[A[i]] - 1] = A[i];
            C[A[i]] = C[A[i]] - 1;
        }
        for (i = 0; i < amountOfElementsBArr; i++)
            A[start++] = B[i] - min;
    }

    public static int getMinimum(int[] A, int start, int stop) {
        int min = A[start];
        for (int i = start + 1; i <= stop; i++)
            if (A[i] < min)
                min = A[i];

        return min;
    }

    public static int getAbsolute(int min) {
        return min * -1;
    }

    public static void main(String[] args) {
        int[] A = randomArray(10, 10);
        System.out.println("Array before count sort");
        printOfArray(A);

        System.out.println("------------ 1 -----------");
        callCountSortArrays(A, 2, 7);
        printOfArray(A);

        System.out.println("------------ 2 -----------");
        callCountSortArrays(A);
        printOfArray(A);


        System.out.println("------------ 3 -----------");
        A = randomArray(10, 10);
        System.out.println("Array before count sort (example2)");
        printOfArray(A);

        System.out.println("------------ 4 -----------");
        callCountSortExample2(A, 2, 7);
        printOfArray(A);

        System.out.println("------------ 5 -----------");
        callCountSortExample2(A);
        printOfArray(A);
    }
}

