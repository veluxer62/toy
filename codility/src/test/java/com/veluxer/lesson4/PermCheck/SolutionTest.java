package com.veluxer.lesson4.PermCheck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SolutionTest {

    private Solution sut;

    @BeforeEach
    public void before() {
        sut = new Solution();
    }

    public static Stream<Arguments> init() {
        return Stream.of(
                Arguments.of(
                        new int[] {4,1,3,2},
                        1
                ),
                Arguments.of(
                        new int[] {4,1,3},
                        0
                ),
                () -> {
                    int[] arr = new int[100000];
                    IntStream.range(1, 100001)
                            .forEach(it -> {
                                arr[it-1] = it;
                            });
                    return new Object[]{arr, 1};
                },
                Arguments.of(
                        new int[] {9, 5, 7, 3, 2, 7, 3, 1, 10, 8},
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
            sut.solution(new int[]{0, 1, 2});
        });
    }

    @Test
    public void exception_test4() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution(new int[]{1000000001, 1, 2});
        });
    }

//    @Test
//    public void test() {
//
//    }
//


}