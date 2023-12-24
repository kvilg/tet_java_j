import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort{


    public static void quickSort(ArrayList<FileConf> arr, int from, int to) {

        if (from < to) {

            int divideIndex = partition(arr, from, to);

            quickSort(arr, from, divideIndex - 1);

            quickSort(arr, divideIndex, to);
        }
    }

    private static int partition(ArrayList<FileConf> arr, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;

        String pivot = arr.get(from + (to - from) / 2).getName();
        while (leftIndex <= rightIndex) {

            while (arr.get(leftIndex).getName().compareTo(pivot) > 0) {
                leftIndex++;
            }

            while (arr.get(rightIndex).getName().compareTo(pivot) < 0) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                swap(arr, rightIndex, leftIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private static void swap(ArrayList<FileConf> array, int index1, int index2) {
        FileConf tmp = array.get(index1);
        array.set(index1,array.get(index2));
        array.set(index2,tmp);
    }
}

class asd{


    public static void main(String[] args) {


        int [] array = new int[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};

        System.out.println(arrayToString(array));

        quickSort(array, 0, array.length - 1);

        System.out.println(arrayToString(array));
    }

    public static void quickSort(int[] arr, int from, int to) {

        if (from < to) {

            int divideIndex = partition(arr, from, to);

            quickSort(arr, from, divideIndex - 1);

            quickSort(arr, divideIndex, to);
        }
    }

    private static int partition(int[] arr, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;

        int pivot = arr[from + (to - from) / 2];
        while (leftIndex <= rightIndex) {

            while (arr[leftIndex] < pivot) {
                leftIndex++;
            }

            while (arr[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                swap(arr, rightIndex, leftIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private static void swap(int[] array, int index1, int index2) {
        int tmp  = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }

}