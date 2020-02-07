package com.veluxer.lesson3.tapeEquilibrium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    private Solution sut;

    @BeforeEach
    public void init() {
        sut = new Solution();
    }

    @Test
    public void test() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(new int[1]);
        });
    }

    @Test
    public void test2() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(new int[100001]);
        });
    }

    @Test
    public void test3() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(new int[] {0, 1001, 3});
        });
    }

    @Test
    public void test4() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(new int[] {0, -1001, 10});
        });
    }

    @Test
    public void test5() {
        int actual = sut.solution(new int[]{3, 1, 2, 4, 3});
        assertEquals(1, actual);
    }
}