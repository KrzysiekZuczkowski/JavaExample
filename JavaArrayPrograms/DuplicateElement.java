package JavaArrayPrograms;

import static JavaArrayPrograms.FrequencyOfEachElementInArray.*;

public class DuplicateElement {

    public static void printDuplicateElements(int[] arr) {
        int min = getMinimum(arr);
        if (min < 0)
            OutNegatives(arr, min);
        else
            min = 0;

        int max = getMaximum(arr);
        int[] temp = new int[max + 1];

        for (int element : arr)
            temp[element] += 1;

        if (min < 0)
            arrayWithNegatives(arr, min);

        System.out.println("Duplicate elements in given array: ");
        for (int i = 0; i < arr.length; i++)
            if (temp[arr[i] - min] == 2) {
                System.out.println("Element " + arr[i] +
                        " at index " + i);
            temp[arr[i] - min] = 0;
            }
    }


    public static void main(String[] args) {

        int [] arr = randomArray(10, 5);
        printArray(arr);
//        System.out.println("Duplicate elements in given array: ");
//        //Searches for duplicate element
//        for(int i = 0; i < arr.length; i++) {
//            for(int j = i + 1; j < arr.length; j++) {
//                if(arr[i] == arr[j])
//                    System.out.println(arr[j]);
//            }
//        }
        printDuplicateElements(arr);
        printArray(arr);
    }
}