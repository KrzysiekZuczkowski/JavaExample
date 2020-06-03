package JavaSearchingPrograms;

import java.util.Scanner;
import static  JavaSortingPrograms.BubbleSort.randomArray;
import static  JavaSortingPrograms.BubbleSort.isInRange;
import static  JavaSortingPrograms.BubbleSort.printOfArray;
import static JavaSortingPrograms.QuickSortRandom.quickSortRandom;

public class BinarySearch {

        public static int binarySearch(int[] arr, int first, int last, int key) {
            int mid = (first + last) / 2;
            while (first < last) {
                if (arr[mid] < key)
                    first = mid + 1;
                else
                    last = mid;
                mid = (first + last) / 2;
            }
            if (arr[mid] == key)
                return mid;

            return -1;
        }

    public static int binarySearchRecursion(int arr[], int first, int last, int key){
        if (last >= first) {
            int mid = (last + first) / 2;
            if (arr[mid] == key)
                return mid;
            if (arr[mid] > key)
                return binarySearch(arr, first, mid-1, key);//search in left subarray
            else
                return binarySearch(arr, mid+1, last, key);//search in right subarray
        }
        return -1;
    }

    public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int arr[] = randomArray(10, 5);
            int first = 2, last = 7;
            int key;
            if (!isInRange(arr, first, last)) {
                first = 0;
                last = arr.length - 1;
            }
            quickSortRandom(arr, first, last);
            System.out.println("Array after quick sort from position: "
                    + first + " to position: " + last);
            printOfArray(arr);

            System.out.println();

            System.out.println("Enter value to find.");
            key = in.nextInt();
            in.close();

            System.out.println("--------- 1 ----------");
            int index = binarySearch(arr, first, last, key);
            if (index == -1)
                System.out.println(key + " isn't present in array from position: "
                    + first + " to position: " + last);
            else
                System.out.println("key value = " + key +
                        " is present at index " + index + ".");

            System.out.println("--------- 2 ----------");
            int index1 = binarySearchRecursion(arr, first, last, key);
            if (index1 == -1)
                System.out.println(key + " isn't present in array from position: "
                    + first + " to position: " + last);
            else
                System.out.println("key value = " + key +
                        " is present at index " + index1 + ".");

        }
}
