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

    /**
     * quick sort : O(n log n)
     *
     * @param array array
     */
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

        return end;
    }

    private static void swap(int a, int b, int[] array) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * insertion sort : O(N^2)
     *
     * @param array array
     */
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];

            int j;
            for (j = i - 1; (j >= 0) && (key < array[j]); j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = key;
        }
    }

    /**
     * merge sort : O(n log n)
     *
     * @param array array
     */
    public static void mergeSort(int[] array) {
        int[] temp = new int[array.length];
        mergeSortUtil(0, array.length - 1, temp, array);
    }

    private static void mergeSortUtil(int start, int end, int[] temp, int[] array) {
        if (end - start > 0) {
            // divide
            int mid = (start + end) / 2;

            // conquer
            mergeSortUtil(start, mid, temp, array);
            mergeSortUtil(mid + 1, end, temp, array);
            // combine
            merge(start, mid, end, temp, array);
        }
    }

    private static void merge(int start, int mid, int end, int[] temp, int[] array) {
        for (int i = start; i <= end; i++) {
            temp[i] = array[i];
        }
        int part1 = start;
        int part2 = mid + 1;
        int index = start;
        while (part1 <= mid && part2 <= end) {
            if (temp[part1] < temp[part2]) {
                array[index] = temp[part1];
                part1++;
            } else {
                array[index] = temp[part2];
                part2++;
            }
            index++;
        }

        for (int i = part1; i <= mid; i++) {
            array[index++] = temp[part1];
        }
    }
}