package com.veluxer.lesson4.frogRiverOne;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    private Solution sut;

    @BeforeEach
    public void before() {
        sut = new Solution();
    }

    public static Stream<Arguments> init() {
        return Stream.of(
                Arguments.of(5, new int[]{1,3,1,4,2,3,5,4})
        );
    }

    @Test
    public void test() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(0, new int[] {1, 2});
        });
    }

    @Test
    public void test2() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(1, new int[]{});
        });
    }

    @Test
    public void test3() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(100001, new int[]{1, 2});
        });
    }

    @Test
    public void test4() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(2, new int[100001]);
        });
    }

    @ParameterizedTest
    @MethodSource("init")
    public void paramTest(int x, int[] arr) {
        int actual = sut.solution(x, arr);
        assertEquals(6, actual);
    }

}