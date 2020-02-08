package com.veluxer.codingTest.wooa.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
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
                        new int[] {3,2,-2,5,-3},
                        3
                ),
                Arguments.of(
                        new int[] {1,1,2,-1,2,-1},
                        1
                ),
                Arguments.of(
                        new int[] {1,2,3,-4},
                        0
                )
        );
    }

    @ParameterizedTest
    @MethodSource("init")
    public void parameterized_test(int[] arr, int expected) {
        int actual = sut.solution(arr);
        assertEquals(expected, actual);
    }

    @Test
    public void test() {
        int[] arr = new int[100000];
        Random ran = new Random();
        for (int i = 0; i < arr.length; i++) {
            int random = ran.nextInt(100000000);
            arr[i] = random;
        }
        arr[99999] = -1000000000;
        arr[99998] = 1000000000;
        int actual = sut.solution(arr);
        assertEquals(1000000000, actual);
    }

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
            sut.solution(new int[]{1, -1000000001});
        });
    }

    @Test
    public void exception_test4() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(new int[]{1, 1000000001, 2});
        });
    }

}