package com.kh.hackerrank;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionSplitIntoTwoTest {

    @Test
    public void test() {
        List<Integer> arr = Arrays.asList(10, 4, -8, 7);
        int actual = Solution.splitIntoTwo(arr);
        assertEquals(2, actual);
    }

    @Test
    public void test2() {
        List<Integer> arr = Arrays.asList(10, -5, 6);
        int actual = Solution.splitIntoTwo(arr);
        assertEquals(1, actual);
    }

    @Test
    public void test3() {
        List<Integer> arr = Arrays.asList(-3, -2, 10, 20, -30);
        int actual = Solution.splitIntoTwo(arr);
        assertEquals(2, actual);
    }

    @Test
    public void test_trow() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> arr = Arrays.asList(1);
            Solution.splitIntoTwo(arr);
        });
    }

    @Test
    public void test_trow2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Integer[] array = new Integer[((int) Math.pow(10, 5)) + 1];
            List<Integer> arr = Arrays.asList(array);
            Solution.splitIntoTwo(arr);
        });
    }

    @Test
    public void test_trow3() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> arr = Arrays.asList(1, -10001);
            Solution.splitIntoTwo(arr);
        });
    }

    @Test
    public void test_trow4() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> arr = Arrays.asList(1, 10001);
            Solution.splitIntoTwo(arr);
        });
    }
}