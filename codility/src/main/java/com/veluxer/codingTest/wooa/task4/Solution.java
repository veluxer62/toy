package com.veluxer.codingTest.wooa.task4;

import java.util.Arrays;

public class Solution {
    public int solution(int[] arr1, int[] arr2) {
        int arr1Length = arr1.length;
        int arr2Length = arr2.length;;
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int i = 0;
        for (int k = 0; k < arr1Length; k++) {
            if (i < arr2Length - 1 && arr2[i] < arr1[k])
                i += 1;
            if (arr1[k] == arr2[i])
                return arr1[k];
        }
        return -1;
    }
}
