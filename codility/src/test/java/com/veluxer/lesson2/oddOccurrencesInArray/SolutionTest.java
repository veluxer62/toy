package com.veluxer.lesson2.oddOccurrencesInArray;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    public void test() {
        Solution sut = new Solution();
        int[] arr = {9, 3, 9, 3, 9, 7, 9};
        int actual = sut.solution(arr);
        assertEquals(7, actual);
    }

}