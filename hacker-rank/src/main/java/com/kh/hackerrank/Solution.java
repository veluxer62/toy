package com.kh.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static int splitIntoTwo(List<Integer> arr) {
        if (arr.size() < 2 || arr.size() > 100000)
            throw new IllegalArgumentException();

        if (arr.stream().anyMatch(it -> it < -10000 || it > 10000))
            throw new IllegalArgumentException();

        int biggerThanRightSumCount = 0;
        int loopLength = arr.size() - 1;
        for (int i = 0; i < loopLength; i++) {
            List<Integer> leftList = arr.subList(0, i + 1);
            List<Integer> rightList = arr.subList(i + 1, arr.size());
            Integer leftSum = leftList.stream().reduce(0, Integer::sum);
            Integer rightSum = rightList.stream().reduce(0, Integer::sum);
            if (leftSum > rightSum)
                biggerThanRightSumCount++;
        }

        return biggerThanRightSumCount;
    }

    public static int requestsServed(List<Integer> timestamp, List<Integer> top) {
        int timestampLength = timestamp.size();
        if (timestampLength < 1 || timestampLength > 100000)
            throw new IllegalArgumentException();

        if (timestamp.stream().anyMatch(it -> it < 0 || it > 59))
            throw new IllegalArgumentException();

        int topLength = top.size();
        if (topLength < 1 || topLength > 30)
            throw new IllegalArgumentException();

        if (top.stream().anyMatch(it -> it < 0 || it > 59))
            throw new IllegalArgumentException();

        List<Integer> sortedList = timestamp.stream().sorted().collect(Collectors.toList());
        List<Integer> responseList = new ArrayList<>();
        top.forEach(atTop -> {
            int cnt = 0;
            for (int i = sortedList.size(); i > 0; i--) {
                int itemIndex = i - 1;
                Integer responseTime = sortedList.get(itemIndex);

                if (atTop >= responseTime) {
                    cnt++;
                    if (cnt > 5) break;

                    responseList.add(responseTime);
                    sortedList.remove(responseTime);
                }
            }
        });

        return responseList.size();
    }

    public static int segment(int x, List<Integer> arr) {
        validate(x, arr);

        if (x == 1)
            return arr.stream()
                    .max(Integer::compareTo)
                    .orElseThrow(IllegalAccessError::new);

        List<Integer> minList = new ArrayList<>();

        int loopSize = arr.size() - x;
        for (int i = 0; i <= loopSize; i++) {
            minList.add(arr.subList(i, i + x).stream()
                    .min(Integer::compareTo)
                    .orElseThrow(IllegalArgumentException::new));
        }

        return minList.stream()
                .max(Integer::compareTo)
                .orElseThrow(IllegalArgumentException::new);
    }

    private static void validate(int x, List<Integer> arr) {
        if (arr.size() < 1 || arr.size() > 1000000)
            throw new IllegalArgumentException();

        if (x < 1 || x > arr.size())
            throw new IllegalArgumentException();

        if (arr.stream().anyMatch(it -> it < 1 || it > 1000000000))
            throw new IllegalArgumentException();
    }

}
