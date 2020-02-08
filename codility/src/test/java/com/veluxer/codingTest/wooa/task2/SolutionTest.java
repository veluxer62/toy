package com.veluxer.codingTest.wooa.task2;

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
                        "my.song.mp3 11b\ngreateSong.flac 1000b\nnot3.txt 5b\nvideo.mp4 200b\ngame.exe 100b\nmov!e.mkv 10000b ",
                        "music 1011b\nimages 0b\nmovies 10200b\nother 105b"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("init")
    public void parameterized_test(String given, String expected) {
        String actual = sut.solution(given);
        assertEquals(expected, actual);
    }

    @Test
    public void test() {
        String t1 = "111b".replaceAll("\\D", "");
        String t2 = "adsfa".replaceAll("\\D", "");
        System.out.println(t1);
        System.out.println(t2);

    }

    @Test
    public void exception_test() {
        assertThrows(IllegalArgumentException.class, () -> {
            sut.solution("");
        });
    }


}