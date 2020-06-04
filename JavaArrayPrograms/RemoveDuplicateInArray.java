package JavaArrayPrograms;


import static JavaArrayPrograms.FrequencyOfEachElementInArray.*;

public class RemoveDuplicateInArray {

    public static int[] removeDuplicateInArray(int[] arr, int numberRemoved) {

        int length = arr.length;
        if (!checkNumberRemoved(numberRemoved, arr.length)) {
            System.out.println("Number of elements to delete must be " +
                    "between " + 2 + " and " + arr.length +
                    ", for example 2(duplicate).");
            numberRemoved = 2;
        }
        int min = getMinimum(arr);
        if (min < 0)
            OutNegatives(arr, min);
        else
            min = 0;

        int max = getMaximum(arr);
        int[] temp = new int[max + 1];

        for (int element : arr)
            temp[element] += 1;

        for (int element : temp)
            if (element == numberRemoved)
                length = length - numberRemoved + 1;

        int[] arr1 = new int[length];

        arrayWithNegatives(arr, min);
        int i = 0;
        for (int element : arr) {
            if (temp[element - min] > 0)
                arr1[i++] = element;
            if (temp[element - min] == numberRemoved)
                temp[element - min] = 0;
        }
        return arr1;
    }

    public static boolean checkNumberRemoved(int numberRemoved, int length) {
        return numberRemoved >= 2 && numberRemoved <= length;
    }

    public static void main(String[] args) {

        int[] arr = new int[] {1, 2, 2, 4, -5, -5, -5, 6 ,6}; //randomArray(10, 5);
        System.out.println("Original array");
        printArray(arr);
        arr = removeDuplicateInArray(arr, 3);
        System.out.println("Array after removal");
        printArray(arr);

    }
}