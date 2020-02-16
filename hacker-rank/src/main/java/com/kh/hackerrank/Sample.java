package com.kh.hackerrank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sample {

    public static String findNumber(List<Integer> arr, int k) {
        List<Integer> collect = arr.stream().distinct().collect(Collectors.toList());

        String result = "NO";

        if (collect.contains(k))
            result = "YES";

        return result;
    }

    public static List<Integer> oddNumbers(int l, int r) {
        if (l < 1 || l > 100000)
            throw new IllegalArgumentException();

        if (r < 1 || r > 100000)
            throw new IllegalArgumentException();

        if (l > r)
            throw new IllegalArgumentException();

        return IntStream.range(l, r + 1)
                .filter(it -> it % 2 == 1)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }



}
