package com.veluxer.lesson3.frogJmp;

import java.util.Arrays;

public class Solution {
    public int solution(int x, int y, int d) {
        validate(x, y, d);
        return getCount(x, y, d);
    }

    private void validate(int x, int y, int d) {
        int[] arguments = new int[] {x, y, d};
        boolean isInvalidParameters = Arrays.stream(arguments)
                .anyMatch(it -> it < 1 || it > 1000000000);
        if (isInvalidParameters)
            throw new IllegalArgumentException();

        if (x > y)
            throw new IllegalArgumentException();
    }

    private int getCount(int x, int y, int d) {
        if (x == y)
            return 0;

        return (int) Math.ceil((y - x) / (double) d);
    }
}
