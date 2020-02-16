package com.kh.hackerrank;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionSegmentTest {

    @Test
    public void test0() {
        int x = 2;
        List<Integer> arr = Arrays.asList(8,2,4);
        int actual = Solution.segment(x, arr);
        assertEquals(2, actual);
    }

    @Test
    public void test() {
        int x = 1;
        List<Integer> arr = Arrays.asList(1, 2, 3, 1, 2);
        int actual = Solution.segment(x, arr);
        assertEquals(3, actual);
    }

    @Test
    public void test2() {
        int x = 2;
        List<Integer> arr = Arrays.asList(1,1,1);
        int actual = Solution.segment(x, arr);
        assertEquals(1, actual);
    }

    @Test
    public void test3() {
        int x = 3;
        List<Integer> arr = Arrays.asList(2,5,4,6,8);
        int actual = Solution.segment(x, arr);
        assertEquals(4, actual);
    }

    @Test
    public void test_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            Integer[] arr = new Integer[((int) Math.pow(10, 6)) + 1];
            List<Integer> integers = Arrays.asList(arr);
            Solution.segment(2, integers);
        });
    }

    @Test
    public void test_throw2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Integer[] arr = new Integer[]{};
            List<Integer> integers = Arrays.asList(arr);
            Solution.segment(2, integers);
        });
    }

    @Test
    public void test_throw3() {
        assertThrows(IllegalArgumentException.class, () -> {
            Integer[] arr = new Integer[]{1, 2};
            List<Integer> integers = Arrays.asList(arr);
            Solution.segment(3, integers);
        });
    }

    @Test
    public void test_throw4() {
        assertThrows(IllegalArgumentException.class, () -> {
            Integer[] arr = new Integer[]{1, 2};
            List<Integer> integers = Arrays.asList(arr);
            Solution.segment(0, integers);
        });
    }

    @Test
    public void test_throw5() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> integers = Arrays.asList(1, ((int) Math.pow(10, 9)) + 1);
            Solution.segment(2, integers);
        });
    }

    @Test
    public void test_throw6() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> integers = Arrays.asList(1, 0);
            Solution.segment(2, integers);
        });
    }

}