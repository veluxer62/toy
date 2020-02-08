package com.veluxer.lesson5;

import java.util.*;

public class Solution {
    public int solution(int[] arr) {
        int arrLength = arr.length;

        if (arrLength < 1 || arrLength > 100000)
            throw new IllegalArgumentException();

        if (!Arrays.stream(arr).allMatch(it -> it == 0 || it == 1))
            throw new IllegalArgumentException();

        int cnt = 0;
        for (int i = 0; i < arrLength; i++) {
            int element = arr[i];
            if (element == 0) {
                for (int j = 0; j < arrLength; j++) {
                    int secondElement = arr[j];
                    if (secondElement == 1 && i < j)
                        cnt++;
                }
            }
        }

        if (cnt > 1000000000)
            return -1;

        return cnt;
    }
}
