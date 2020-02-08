package com.veluxer.lesson1.binaryGap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    public void solution_return_zero_if_given_argument_is_negative() {
        Solution sut = new Solution();
        int actual = sut.solution(-1);
        assertEquals(0, actual);
    }

    public static Stream<Arguments> initTestData() {
        return Stream.of(
                Arguments.of(32, 0),
                Arguments.of(1041, 5),
                Arguments.of(561892, 3),
                Arguments.of(9, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("initTestData")
    public void solution_return_if_given_argument_is_positive(
            int number, int expected) {
        Solution sut = new Solution();
        int actual = sut.solution(number);
        assertEquals(expected, actual);
    }

}