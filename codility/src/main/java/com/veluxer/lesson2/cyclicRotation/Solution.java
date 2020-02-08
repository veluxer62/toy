package com.veluxer.lesson2.cyclicRotation;

class Solution {
    public int[] solution(final int[] arr, final int rotateCount)
            throws IllegalArgumentException {

        if (rotateCount < 0 || rotateCount > 100)
            throw new IllegalArgumentException();

        int[] result = arr;

        for (int i = 0; i < rotateCount; i++) {
            result = shiftArray(result);
        }

        return result;
    }

    private int[] shiftArray(final int[] arr) {
        int arrayLength = arr.length;

        int[] result = new int[arrayLength];

        for (int i = 0; i < arrayLength; i++) {
            int copyIndex = i + 1;

            if (copyIndex == arrayLength)
                result[0] = arr[i];
            else
                result[copyIndex] = arr[i];
        }
        return result;
    }
}
