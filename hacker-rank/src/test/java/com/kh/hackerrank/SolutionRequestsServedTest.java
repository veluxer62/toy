package com.kh.hackerrank;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SolutionRequestsServedTest {

    @Test
    public void test() {
        List<Integer> arr = Arrays.asList(0, 1, 1, 2, 4, 5);
        List<Integer> top = Arrays.asList(5);
        int actual = Solution.requestsServed(arr, top);
        assertEquals(5, actual);
    }

    @Test
    public void test2() {
        List<Integer> arr = Arrays.asList(1,2,2,3,4,5,6,6,13,16);
        List<Integer> top = Arrays.asList(10,15);
        int actual = Solution.requestsServed(arr, top);
        assertEquals(9, actual);
    }

    @Test
    public void test3() {
        List<Integer> arr = Arrays.asList(2,2,4,8,11,28,30);
        List<Integer> top = Arrays.asList(0,5,5,15);
        int actual = Solution.requestsServed(arr, top);
        assertEquals(5, actual);
    }

    @Test
    public void test4() {
        List<Integer> arr = Arrays.asList(0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
        List<Integer> top = Arrays.asList(6,6,6,6);
        int actual = Solution.requestsServed(arr, top);
        assertEquals(17, actual);
    }

    @Test
    public void test5() {
        List<Integer> arr = Arrays.asList(8,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
        List<Integer> top = Arrays.asList(6,6,6,6);
        int actual = Solution.requestsServed(arr, top);
        assertEquals(16, actual);
    }

    @Test
    public void test_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            Integer[] arr = new Integer[((int) Math.pow(10, 5) + 1)];
            List<Integer> integers = Arrays.asList(arr);
            Solution.requestsServed(integers, Arrays.asList(10, 15));
        });
    }

    @Test
    public void test_throw2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Integer[] arr = new Integer[]{};
            List<Integer> integers = Arrays.asList(arr);
            Solution.requestsServed(integers, Arrays.asList(10, 15));
        });
    }

    @Test
    public void test_throw3() {
        assertThrows(IllegalArgumentException.class, () -> {
            Integer[] topArr = new Integer[31];
            Solution.requestsServed(Arrays.asList(10, 15), Arrays.asList(topArr));
        });
    }

    @Test
    public void test_throw4() {
        assertThrows(IllegalArgumentException.class, () -> {
            Integer[] topArr = new Integer[]{};
            Solution.requestsServed(Arrays.asList(10, 15), Arrays.asList(topArr));
        });
    }

    @Test
    public void test_throw5() {
        assertThrows(IllegalArgumentException.class, () -> {
            Integer[] topArr = new Integer[]{};
            Solution.requestsServed(Arrays.asList(1,2,3,4,5,6,-1), Arrays.asList(10, 15));
        });
    }

    @Test
    public void test_throw7() {
        assertThrows(IllegalArgumentException.class, () -> {
            Integer[] topArr = new Integer[]{};
            Solution.requestsServed(Arrays.asList(1,2,3,4,5,6,60), Arrays.asList(10, 15));
        });
    }

    @Test
    public void test_throw8() {
        assertThrows(IllegalArgumentException.class, () -> {
            Solution.requestsServed(Arrays.asList(1,2,3,4,5,6,30), Arrays.asList(-1, 15));
        });
    }

    @Test
    public void test_throw9() {
        assertThrows(IllegalArgumentException.class, () -> {
            Solution.requestsServed(Arrays.asList(1,2,3,4,5,6,30), Arrays.asList(1, 60));
        });
    }

}