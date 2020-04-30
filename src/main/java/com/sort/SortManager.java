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

    private static void swap(int a, int b, int[] array) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}