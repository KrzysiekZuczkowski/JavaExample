package JavaArrayPrograms;

import static JavaArrayPrograms.FrequencyOfEachElementInArray.printArray;

public class RotateRight {

    public static void rotateRight(int[] arr, int numberTimes) {
        int i, j;
        numberTimes = numberTimes % arr.length;

        for (i = 0; i < numberTimes; i++) {
            int last = arr[arr.length - 1];
            for (j = arr.length - 1; j > 0; j--)
                arr[j] = arr[j - 1];

            arr[j] = last;
        }
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original array");
        printArray(arr);

        System.out.println("Array after right rotation");
        rotateRight(arr, 12);
        printArray(arr);
    }
}

