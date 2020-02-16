package com.kh.hanckerrank;

import com.kh.hackerrank.Sample;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SampleTest {

    @Test
    public void test_throw() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sample.oddNumbers(0, 1);
        });
    }

    @Test
    public void test_throw2() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sample.oddNumbers(1, 0);
        });
    }

    @Test
    public void test_throw3() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sample.oddNumbers(100001, 1);
        });
    }

    @Test
    public void test_throw4() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sample.oddNumbers(1, 100001);
        });
    }

    @Test
    public void test_throw5() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sample.oddNumbers(3, 1);
        });
    }

}