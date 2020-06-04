package JavaArrayPrograms;

import static JavaArrayPrograms.FrequencyOfEachElementInArray.printArray;

public class RotateLeft {

    public static void rotateLeft(int[] arr, int numberTimes) {
        int i, j;
        numberTimes = numberTimes % arr.length;

        for (i = 0; i < numberTimes; i++) {
            int first = arr[0];
            for (j = 1; j < arr.length; j++)
                arr[j - 1] = arr[j];

            arr[j - 1] = first;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2,  3, 4, 5};
        System.out.println("Original array");
        printArray(arr);

        System.out.println("Array after left rotation");
        rotateLeft(arr, 23);
        printArray(arr);
    }
}
