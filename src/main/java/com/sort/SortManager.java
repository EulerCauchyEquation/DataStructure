package com.sort;

/**
 * internal sort
 *
 * @author 송훤출
 * @since 20.04.30
 */
public class SortManager {

    /**
     * bubble sort : O(N^2)
     *
     * @param array Array
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean flag = true;

            for (int j = 0; j < array.length - (i + 1); j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1, array);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * Selection Sort : O(N^2)
     *
     * @param array array
     */
    public static void selectionSort(int[] array) {
        int min;

        for (int i = 0; i < array.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < array.length; j++) {
                min = array[min] > array[j] ? j : min;
            }
            swap(i, min, array);
        }
    }

    public static void quickSort(int[] array) {
        quickSortUtil(0, array.length - 1, array);
    }

    private static void quickSortUtil(int start, int end, int[] array) {
        if (end - start < 1) {
            return;
        }

        int part = partition(start, end, array);
        quickSortUtil(start, part - 1, array);
        quickSortUtil(part, end, array);
    }

    private static int partition(int start, int end, int[] array) {
        int pivot = start;

        while (start <= end) {
            while (array[start] < array[pivot]) start++;
            while (array[end] > array[pivot]) end--;
            if (start <= end) {
                swap(start, end, array);
                start++;
                end--;
            }
        }

        return start;
    }

    private static void swap(int a, int b, int[] array) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}