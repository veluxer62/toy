package com.veluxer.lesson2.oddOccurrencesInArray;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int solution(final int[] arr) {

        if (arr.length > 1000000 || arr.length % 2 == 0)
            throw new IllegalArgumentException();

        Map<Integer, Integer> occurrence = new HashMap<>();

        for (int num : arr) {
            Integer value = occurrence.getOrDefault(num, 0);
            occurrence.put(num, value + 1);
        }

        return occurrence.entrySet()
                .stream()
                .filter(it -> it.getValue() < 2)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }
}
