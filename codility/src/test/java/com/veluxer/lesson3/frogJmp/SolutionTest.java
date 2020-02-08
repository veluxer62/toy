package com.veluxer.lesson3.frogJmp;

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
            sut.solution(-1, 1, 1);
        });
    }

    @Test
    public void test2() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(1, -1, 1);
        });
    }

    @Test
    public void test3() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(1, 1, -1);
        });
    }

    @Test
    public void test4() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(1000000001, 1, 1);
        });
    }

    @Test
    public void test5() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(1, 1000000001, 1);
        });
    }

    @Test
    public void test6() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(1, 1, 1000000001);
        });
    }

    @Test
    public void test7() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(2, 1, 1);
        });
    }

    @Test
    public void test8() {
        int actual = sut.solution(10, 85, 30);
        assertEquals(3, actual);
    }

}