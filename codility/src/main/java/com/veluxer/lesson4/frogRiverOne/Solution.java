package com.veluxer.lesson4.frogRiverOne;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    public int solution(int x, int[] arr) {
        int arrLength = arr.length;
        if (x < 1 || x > 100000)
            throw new IllegalArgumentException();

        if (arrLength < 1 || arrLength > 100000)
            throw new IllegalArgumentException();

        int findIdx = Arrays.binarySearch(arr, x);

        if (findIdx < 0)
            return -1;

        int[] arrays = Arrays.copyOfRange(arr, 0, findIdx + 1);
        boolean b = Arrays.stream(arrays)
                .distinct()
                .allMatch(it -> IntStream.range(1, x).anyMatch(n -> n == it));

        if (!b)
            return findIdx;

        return -1;
    }
}
