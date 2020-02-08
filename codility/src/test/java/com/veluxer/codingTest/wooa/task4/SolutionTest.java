package com.veluxer.codingTest.wooa.task4;

import org.junit.jupiter.api.BeforeEach;
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
                        new int[]{1, 3, 2, 1},
                        new int[]{4, 2, 5, 3},
                        2
                ),
                Arguments.of(
                        new int[]{2, 1},
                        new int[]{3, 3},
                        -1
                ),
                Arguments.of(
                        new int[]{2, 1},
                        new int[]{3, 3, 2},
                        2
                ),
                Arguments.of(
                        new int[]{2, 1, 3},
                        new int[]{3, 3},
                        3
                ),
                Arguments.of(
                        new int[]{2},
                        new int[]{3},
                        -1
                ),
                Arguments.of(
                        new int[]{2},
                        new int[]{2},
                        2
                )
        );
    }

    @ParameterizedTest
    @MethodSource("init")
    public void parameterized_test(int[] arr1, int[] arr2, int expected) {
        int actual = sut.solution(arr1, arr2);
        assertEquals(expected, actual);
    }

    //    @Test
    //    public void test() {
    //
    //    }

//    @Test
//    public void exception_test() {
//        assertThrows(IllegalArgumentException.class, () -> {
//
//        });
//    }
}