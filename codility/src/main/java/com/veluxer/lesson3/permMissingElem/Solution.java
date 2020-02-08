package com.veluxer.lesson3.permMissingElem;

import java.util.Arrays;

public class Solution {
    public int solution(int[] arr) {
        Arrays.sort(arr);

        if (arr.length < 2)
            return arr.length + 1;

        return getMissingElement(arr);
    }

    private int getMissingElement(int[] arr) {
        int missingElement = 0;
        int prevElement = 0;
        for (int element : arr) {
            boolean isMissing = element - prevElement > 1;
            if (isMissing) {
                missingElement = prevElement + 1;
                break;
            } else {
                prevElement = element;
            }
        }
        return missingElement;
    }
}
