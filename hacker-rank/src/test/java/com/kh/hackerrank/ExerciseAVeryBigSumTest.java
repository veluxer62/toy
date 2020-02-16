package com.kh.hackerrank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseAVeryBigSumTest {

    @Test
    public void test() {
        long[] arr = {1000000001, 1000000002, 1000000003, 1000000004, 1000000005};
        long actual = Exercise.aVeryBigSum(arr);
        assertEquals(5000000015L, actual);
    }

    @Test
    public void test_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            long[] arr = new long[11];
            Exercise.aVeryBigSum(arr);
        });
    }
    
    @Test
    public void test_throw2() {
        assertThrows(IllegalArgumentException.class, () -> {
            long[] arr = {-1, 2, 5};
            Exercise.aVeryBigSum(arr);
        });
    }

    @Test
    public void test_throw3() {
        assertThrows(IllegalArgumentException.class, () -> {
            long[] arr = {0, 2, 5, ((long) Math.pow(10, 10)) + 1};
            Exercise.aVeryBigSum(arr);
        });
    }
}