package com.veluxer.lesson3.tapeEquilibrium;

import java.util.Arrays;

public class Solution {
    public int solution(final int[] arr) {
        int arrLength = arr.length;

        if (arrLength < 2 || arrLength > 100000)
            throw new IllegalArgumentException();

        if (Arrays.stream(arr)
                .anyMatch(a -> a > 1000 || a < -1000)) {
            throw new IllegalArgumentException();
        }

        int[] absoluteDifferenceGaps = new int[arrLength - 1];

        for (int i = 1; i < arrLength; i++) {
            int[] beforeArr = Arrays.copyOfRange(arr, 0, i);
            int[] afterAtt = Arrays.copyOfRange(arr, i, arrLength);

            int left = Arrays.stream(beforeArr).sum();
            int right = Arrays.stream(afterAtt).sum();
            absoluteDifferenceGaps[i - 1] = Math.abs(left - right);
        }
        return Arrays
                .stream(absoluteDifferenceGaps)
                .min()
                .orElseThrow(NullPointerException::new);
    }
}
