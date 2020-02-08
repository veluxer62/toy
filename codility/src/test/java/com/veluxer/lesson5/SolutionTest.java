package com.veluxer.lesson5;

import org.junit.jupiter.api.Assertions;
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
                Arguments.of(
                        new int[] {0, 1, 0, 1, 1},
                        5
                )
        );
    }

    @ParameterizedTest
    @MethodSource("init")
    public void parameterized_test(int[] arr, int expected) {
        int actual = sut.solution(arr);
        assertEquals(expected, actual);
    }

    //    @Test
    //    public void test() {
    //
    //    }

    @Test
    public void exception_test() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(new int[]{});
        });
    }

    @Test
    public void exception_test2() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(new int[100001]);
        });
    }

    @Test
    public void exception_test3() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(new int[]{2, 1, 0});
        });
    }
}


