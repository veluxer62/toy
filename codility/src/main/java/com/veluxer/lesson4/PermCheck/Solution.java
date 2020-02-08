package com.veluxer.lesson4.PermCheck;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    public int solution(int[] arr) {
        int arrLength = arr.length;

        if (arrLength < 1 || arrLength > 100000)
            throw new IllegalArgumentException();

        if (Arrays.stream(arr).anyMatch(it -> it < 1 || it > 1000000000))
            throw new IllegalArgumentException();

        int maxValue = Arrays.stream(arr)
                .max().orElse(0);

        if (maxValue != arrLength)
            return 0;

        int arrSum = Arrays.stream(arr).distinct().sum();
        int expectedSum = IntStream.range(1, maxValue + 1).sum();

        if (arrSum != expectedSum)
            return 0;

        return 1;
    }
}
