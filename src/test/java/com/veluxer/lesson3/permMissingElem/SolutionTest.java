package com.veluxer.lesson3.permMissingElem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    public void test() {
        Solution sut = new Solution();
        int[] arr = {2, 3, 1, 5};
        int actual = sut.solution(arr);
        assertEquals(4, actual);
    }

    @Test
    public void test_empty() {
        Solution sut = new Solution();
        int[] arr = {};
        int actual = sut.solution(arr);
        assertEquals(1, actual);
    }

    @Test
    public void test_single() {
        Solution sut = new Solution();
        int[] arr = {1};
        int actual = sut.solution(arr);
        assertEquals(2, actual);
    }

    @Test
    public void test_first_missing() {
        Solution sut = new Solution();
        int[] arr = {2, 3, 4, 5};
        int actual = sut.solution(arr);
        assertEquals(1, actual);
    }

}