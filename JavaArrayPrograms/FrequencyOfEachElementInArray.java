package JavaArrayPrograms;

import java.util.Random;

public class FrequencyOfEachElementInArray {

    public static void frequencyExample1(int[] arr) {
        int[] frq = new int[arr.length];
        int visited = -1;
        int min = getMinimum(arr);
        if (min < 0)
            OutNegatives(arr, min);

        for (int i = 0; i < arr.length; i++) {
            int counter = 1;
            if (frq[i] == visited)
                continue;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    counter++;
                    frq[j] = visited;
                }
            }
            frq[i] = counter;
        }
        if (min < 0)
            arrayWithNegatives(arr, min);

        displaysFrequencyOfEachElement(arr, frq);
    }

    public static int getMinimum(int[] arr)  {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < min)
                min = arr[i];

        return min;
    }

    public static void OutNegatives(int[] arr, int min) {
        for (int i = 0; i < arr.length; i++)
            arr[i] -= min;
    }

    public static void arrayWithNegatives(int[] arr, int min) {
        for (int i = 0; i < arr.length; i++)
            arr[i] += min;
    }

    public static void displaysFrequencyOfEachElement(int[] arr, int[] frq) {
        int visited = -1;
        System.out.println("-------------------------");
        System.out.println("Element  |  Frequency" );
        System.out.println("-------------------------");
        for (int i = 0; i < arr.length; i++) {
            if (frq[i] != visited)
                System.out.printf("   %2d     |      %d       \n", arr[i], frq[i]);
        }
        System.out.println("-------------------------");

    }

    public static void frequencyExample2(int[] arr) {
        int min = getMinimum(arr);
        if (min < 0)
            OutNegatives(arr, min);
        else
            min = 0;

        int max = getMaximum(arr);
        int[] frq = new int[max + 1];

        for (int i = 0; i < arr.length; i++)
            frq[arr[i]] = frq[arr[i]] + 1;

        displaysFrequencyOfEachElementExample2(arr, frq, min);

        if (min < 0)
            arrayWithNegatives(arr, min);
    }

    public static int getMaximum(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];

        return max;
    }

    public static void displaysFrequencyOfEachElementExample2(
                                    int[] arr, int[] frq, int min) {
        int visited = -1;
        System.out.println("-------------------------");
        System.out.println("Element  |  Frequency" );
        System.out.println("-------------------------");
        for (int i = 0; i < arr.length; i++)
            if (frq[arr[i]] != visited) {
                System.out.printf(
                        "   %2d     |      %d       \n", arr[i] + min, frq[arr[i]]);
                frq[arr[i]] = visited;
            }
        System.out.println("-------------------------");
    }

    public static int[] randomArray(int length, int range) {
        int[] arr = new int[length];
        boolean[] position = new boolean[length];
        int amountOfNegativeValues, i;
        Random rd = new Random();

        for (i = 0; i < length; i++)
            arr[i] = rd.nextInt(range + 1);

        amountOfNegativeValues = rd.nextInt(length);
        for (i = 1; i < amountOfNegativeValues; i++) {

            int positionNegative = rd.nextInt(length);
            while (position[positionNegative])
                positionNegative = rd.nextInt(length);

            arr[positionNegative] *= -1;
            position[positionNegative] = true;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int element : arr)
            System.out.print(element + " ");

        System.out.println();
    }

    public static void printArray(boolean[] arr) {
        for (boolean element : arr)
            System.out.print(element + " ");

        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = randomArray(3, 5);
        printArray(arr);
        frequencyExample1(arr);

        frequencyExample2(arr);

    }
}
