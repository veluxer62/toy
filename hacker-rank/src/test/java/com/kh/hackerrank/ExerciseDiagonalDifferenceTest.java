package com.kh.hackerrank;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseDiagonalDifferenceTest {

    @Test
    public void test() {
        List<List<Integer>> arr = Arrays.asList(
                Arrays.asList(11, 2, 4),
                Arrays.asList(4, 5, 6),
                Arrays.asList(10, 8, -12)
        );
        int actual = Exercise.diagonalDifference(arr);
        assertEquals(15, actual);
    }

}