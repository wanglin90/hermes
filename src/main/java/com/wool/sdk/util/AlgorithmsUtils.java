package com.wool.sdk.util;

/**
 * Created by wanglin on 17-2-15.
 */
public class AlgorithmsUtils {

    /**
     * quick sort
     *
     * @param origin
     * @return
     */
    public static int[] quickSort(int[] origin) {

        if (origin.length < 0) {
            throw new IllegalArgumentException("origin's length must be not less than zero . ");
        }

        return quickSort(origin, 0, origin.length);
    }

    private static int[] quickSort(int[] origin, int low, int high) {

        if (low < high) {

            int i = low;
            int j = high;
            int temp = origin[i];

            while (i < j) {

                while (i < j && origin[j] >= temp) {
                    j--;
                }

                if (i < j) {
                    origin[i] = origin[j];
                    i++;
                }

                while (i < j && origin[i] < temp) {
                    i++;
                }

                if (i < j) {
                    origin[j] = origin[i];
                    j--;
                }

            }

            origin[i] = temp;

            quickSort(origin, 0, i - 1);
            quickSort(origin, i + 1, high);
        }

        return origin;
    }

    /**
     * bubble sort
     *
     * @param origin
     * @return
     */
    public static int[] bubbleSort(int[] origin) {

        return null;
    }

}
