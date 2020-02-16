package com.kh.hackerrank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseSimpleArraySumTest {

    @Test
    public void test() {
        int[] arr = {1,2,3,4,10,11};
        int actual = Exercise.simpleArraySum(arr);
        assertEquals(31, actual);
    }

    @Test
    public void test_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            int[] arr = new int[0];
            Exercise.simpleArraySum(arr);
        });
    }

    @Test
    public void test_throw2() {
        assertThrows(IllegalArgumentException.class, () -> {
            int[] arr = {1, 10000, 0};
            Exercise.simpleArraySum(arr);
        });
    }
}