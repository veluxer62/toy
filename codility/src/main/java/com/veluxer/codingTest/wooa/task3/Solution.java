package com.veluxer.codingTest.wooa.task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(final int[] arr) {
        validate(arr);

        final int[] distinctAndSortArray = Arrays.stream(arr)
                .distinct()
                .sorted()
                .toArray();

        final Map<Integer, Integer> numberStack =
                getNumberStack(distinctAndSortArray);

        return numberStack.keySet().stream()
                .filter(it -> numberStack.get(it) > 1)
                .max(Integer::compare)
                .orElse(0);
    }

    private Map<Integer, Integer> getNumberStack(int[] distinctAndSortArray) {
        final Map<Integer, Integer> numberStack = new HashMap<>();

        for (int number : distinctAndSortArray) {
            int absoluteNumber = Math.abs(number);
            int existCount = numberStack.getOrDefault(absoluteNumber, 0);
            numberStack.put(absoluteNumber, existCount + 1);
        }
        return numberStack;
    }

    private void validate(int[] arr) {
        int arrLength = arr.length;

        boolean isInvalidateArrayLength = arrLength < 1 || arrLength > 100000;
        if (isInvalidateArrayLength)
            throw new IllegalArgumentException();

        boolean arrayHaveInvalidateNumber = Arrays.stream(arr)
                .anyMatch(it -> it < -1000000000 || it > 1000000000);
        if (arrayHaveInvalidateNumber)
            throw new IllegalArgumentException();
    }
}
