package com.kh.hackerrank;

import java.util.Arrays;
import java.util.List;

public class Exercise {
    static int simpleArraySum(int[] arr) {
        if (arr.length < 1)
            throw new IllegalArgumentException();

        if (Arrays.stream(arr).anyMatch(it -> it > 1000))
            throw new IllegalArgumentException();

        return Arrays.stream(arr).sum();
    }

    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {

        if (a.stream().anyMatch(it -> it < 1 || it > 100))
            throw new IllegalArgumentException();

        if (b.stream().anyMatch(it -> it < 1 || it > 100))
            throw new IllegalArgumentException();

        if (a.size() != b.size())
            throw new IllegalArgumentException();

        int aScore = 0;
        int bScore = 0;
        for (int i = 0; i < a.size(); i++) {
            Integer aItem = a.get(i);
            Integer bItem = b.get(i);

            if (aItem > bItem)
                aScore++;

            if (bItem > aItem)
                bScore++;
        }

        return Arrays.asList(aScore, bScore);
    }

    static long aVeryBigSum(long[] ar) {
        if (ar.length > 10)
            throw new IllegalArgumentException();

        long pow = (long) Math.pow(10, 10);

        if (Arrays.stream(ar).anyMatch(it -> it < 0 || it > pow))
            throw new IllegalArgumentException();

        return Arrays.stream(ar).sum();
    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        if (arr.stream().anyMatch(it -> it.stream().anyMatch(it2 -> it2 < -100 || it2 > 100)))
            throw new IllegalArgumentException();

        int sumA = 0;
        int sumB = 0;
        for (int i = 0; i < arr.size(); i++) {
            List<Integer> subList = arr.get(i);
            sumA += subList.get(i);
            sumB += subList.get(subList.size() - (i + 1));
        }
        return Math.abs(sumA - sumB);
    }
}
