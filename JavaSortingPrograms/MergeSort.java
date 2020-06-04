package JavaSortingPrograms;

import static JavaSortingPrograms.BubbleSort.printOfArray;

public class MergeSort {

    public static void callMergeSort(int[] A) {
        int stop = A.length - 1;
        System.out.println("Array after merge sort from position: "
                + 0 + " to position: " + stop);
        mergeSort(A, 0, stop);
    }

    public static void callMergeSort(int[] A, int start, int stop) {
        if (!BubbleSort.isInRange(A, start, stop)) {
            start = 0;
            stop = A.length - 1;
        }
        System.out.println("Array after merge sort from position: "
                + start + " to position: " + stop);
        mergeSort(A, start, stop);
    }

    public static void mergeSort(int[] A, int start, int stop) {
        if (start < stop) {
            int mid = (start + stop) / 2;
            mergeSort(A, start, mid);
            mergeSort(A, mid + 1, stop);
            merge(A, start, mid, stop);
        }
    }

    public static void merge(int[] A, int start, int mid, int stop) {
        int i, j, k;
        int guard = CountSort.getMaximum(A, start, stop);
        int numberOfElementsLeftArr = mid - start + 1;
        int numberOfElementsRightArr = stop - mid;
        int[] leftArr = new int[numberOfElementsLeftArr + 1];
        int[] rightArr = new int[numberOfElementsRightArr + 1];

        for (i = 0; i < numberOfElementsLeftArr; i++)
            leftArr[i] = A[start + i];

        for (j = 0; j < numberOfElementsRightArr; j++)
            rightArr[j] = A[mid + 1 + j];

        leftArr[numberOfElementsLeftArr] = guard + 1;
        rightArr[numberOfElementsRightArr] = guard + 1;
        i = j = 0;
        for (k = start; k <= stop; k++) {
            if (leftArr[i] <= rightArr[j])
                A[k] = leftArr[i++];
            else
                A[k] = rightArr[j++];
        }
    }

    public static void main(String[] args) {

        int[] A = BubbleSort.randomArray(10, 10);
        System.out.println("Array before merge sort");
        printOfArray(A);

        System.out.println("------------ 1 -----------");
        callMergeSort(A, 2, 7);
        printOfArray(A);

        System.out.println("------------ 2 -----------");
        callMergeSort(A);
        printOfArray(A);
    }
}
