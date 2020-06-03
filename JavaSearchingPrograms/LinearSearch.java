package JavaSearchingPrograms;

import java.util.Scanner;

public class LinearSearch {

    public static int linearSearch(int[] A, int key) {

        for (int i = 0; i < A.length; i++)
            if (A[i] == key)
                return i;

        return -1;
    }

    public static void main(String[] args) {

        int numberOfElements, key, i, index,  array[];
        Scanner in = new Scanner(System.in);

        System.out.println("Enter number of elements.");
        numberOfElements = in.nextInt();
        array = new int[numberOfElements];

        System.out.println("Enter those " + numberOfElements + " elements.");
        for (i = 0; i < numberOfElements; i++)
            array[i] = in.nextInt();

        System.out.println("Enter value to find.");
        key = in.nextInt();
        in.close();

        index = linearSearch(array, key);
        if (index == -1)
            System.out.println(key + " isn't present in array");
        else
            System.out.println("key value = " + key +
                        " is present at index " + index + ".");
    }
}
