package JavaSortingPrograms;

import static JavaSortingPrograms.BubbleSort.printOfArray;

public class InsertionSort {

    public static void callInsertionSort(int[] A) {
        insertionSort(A, 0, A.length - 1);
    }

    public static void callInsertionSort(int[] A, int start, int stop) {
        if (!BubbleSort.isInRange(A, start, stop)) {
            start = 0;
            stop = A.length - 1;
        }
        insertionSort(A, start, stop);
    }

    public static void insertionSort(int[] A, int start, int stop) {
        System.out.println("Array after insertion sort from position: "
                + start + " to position: " + stop);

        int i, j, key;
        for (j = start + 1; j <= stop; j++) {
            key = A[j];
            i = j - 1;
            while (i >= start && A[i] > key) {
                A[i + 1] = A[i];
                i--;
            }

            A[i + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] A = BubbleSort.randomArray(10, 10);
        System.out.println("Array before insertion sort");
        printOfArray(A);

        System.out.println("------------ 1 -----------");
        callInsertionSort(A, 2, 7);
        printOfArray(A);

        System.out.println("------------ 2 -----------");
        callInsertionSort(A);
        printOfArray(A);
    }
}
