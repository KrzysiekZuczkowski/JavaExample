package JavaSortingPrograms;

import static JavaSortingPrograms.BubbleSort.replace;
import static JavaSortingPrograms.BubbleSort.printOfArray;

public class SelectionSort {

    public static void callSelectionSort(int[] A) {
        selectionSort(A, 0, A.length - 1);
    }

    public static void callSelectionSort(int[] A, int start, int stop) {
        if (!BubbleSort.isInRange(A, start, stop)) {
            start = 0;
            stop = A.length - 1;
        }
        selectionSort(A, start, stop);
    }

    public static void selectionSort(int[] A, int start, int stop) {
        System.out.println("Array after selection sort from position: "
                + start + " to position: " + stop);

        for (int i = start; i < stop; i++) {
            int minIndex = getMinimum(A, i, stop);
            replace(A, i, minIndex);
        }
    }

    public static int getMinimum(int[] A, int start, int stop) {
        int minIndex = start;
        for (int i = start + 1; i <= stop; i++)
            if (A[i] < A[minIndex])
                minIndex = i;

        return minIndex;
    }

    public static void main(String[] args) {
        System.out.println("------------1-----------");

        int[] A = BubbleSort.randomArray(10, 10);
        System.out.println("Array before selection sort");
        printOfArray(A);

        System.out.println("------------2-----------");

        callSelectionSort(A, 2, 7);
        printOfArray(A);

        System.out.println("------------3-----------");

        callSelectionSort(A);
        printOfArray(A);
    }
}
