package com.kh.hackerrank;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseCompareTripletsTest {

    @Test
    public void test() {
        List<Integer> a = Arrays.asList(5, 6, 7);
        List<Integer> b = Arrays.asList(3, 6, 10);
        List<Integer> actual = Exercise.compareTriplets(a, b);
        assertEquals(Arrays.asList(1, 1), actual);
    }

    @Test
    public void test_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> a = Arrays.asList(-1, 6, 7);
            List<Integer> b = Arrays.asList(3, 6, 10);
            Exercise.compareTriplets(a, b);
        });
    }

    @Test
    public void test_throw2() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> a = Arrays.asList(1, 6, 7);
            List<Integer> b = Arrays.asList(3, 6, 1000);
            Exercise.compareTriplets(a, b);
        });
    }

    @Test
    public void test_throw3() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> a = Arrays.asList(1, 6, 7);
            List<Integer> b = Arrays.asList(3, 6, 10, 2);
            Exercise.compareTriplets(a, b);
        });
    }

}