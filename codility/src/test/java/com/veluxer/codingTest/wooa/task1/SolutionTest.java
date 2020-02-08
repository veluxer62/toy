package com.veluxer.codingTest.wooa.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
                Arguments.of(
                        2,
                        2,
                        new int[]{2, 0, 2, 0},
                        "1010,1010"
                ),
                Arguments.of(
                        2,
                        3,
                        new int[]{0, 0, 1, 1, 2},
                        "IMPOSSIBLE"
                ),
                Arguments.of(
                        3,
                        2,
                        new int[]{2, 1, 1, 0, 1},
                        "11001,10100"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("init")
    public void parameterized_test(int u, int l, int[] arr, String expected) {
        String actual = sut.solution(u, l, arr);
        assertEquals(expected, actual);
    }

    @Test
    public void test() {

    }

    @Test
    public void exception_test() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(-1, 3, new int[]{1, 3});
        });
    }

    @Test
    public void exception_test2() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(100001, 3, new int[]{1, 3});
        });
    }

    @Test
    public void exception_test3() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(3, -1, new int[]{1, 3});
        });
    }

    @Test
    public void exception_test4() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(3, 100001, new int[]{1, 0});
        });
    }

    @Test
    public void exception_test5() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(3, 2, new int[]{});
        });
    }

    @Test
    public void exception_test6() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(3, 2, new int[100001]);
        });
    }

    @Test
    public void exception_test7() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(3, 2, new int[]{1, 3, 0});
        });
    }
}