package com.veluxer.lesson2.cyclicRotation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SolutionTest {

    public static Stream<Arguments> init() {
        return Stream.of(
                Arguments.of(new int[]{3, 8, 9, 7, 6}, 1, new int[]{6, 3, 8, 9, 7}),
                Arguments.of(new int[]{3, 8, 9, 7, 6}, 3, new int[]{9, 7, 6, 3, 8}),
                Arguments.of(new int[]{0, 0, 0}, 1, new int[]{0, 0, 0}),
                Arguments.of(new int[]{1, 2, 3, 4}, 4, new int[]{1, 2, 3, 4})
        );
    }

    @ParameterizedTest
    @MethodSource("init")
    public void solution_return_array_correctly_if_given_array_expected(
            int[] arr, int rotateCount, int[] expected
    ) {
        Solution sut = new Solution();
        int[] actual = sut.solution(arr, rotateCount);
        assertArrayEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 101})
    public void solution_throw_IllegalArgumentException_if_given_rotateCount_is_invalid(
            int rotateCount
    ) {
        Solution sut = new Solution();
        assertThrows(IllegalArgumentException.class, () ->
                sut.solution(new int[]{3, 8, 9, 7, 6}, rotateCount));
    }
}