package com.veluxer.codingTest.wooa.task1;

import java.util.Arrays;

public class Solution {
    public String solution(int u, int l, int[] arr) {
        validate(u, l, arr);

        if (u + l != Arrays.stream(arr).sum())
            return "IMPOSSIBLE";

        int[][] board = getBoard(u, arr);

        return getBoardString(board);
    }

    private int[][] getBoard(int u, int[] arr) {
        int arrLength = arr.length;

        int[][] board = new int[2][arrLength];
        Arrays.fill(board[0], 0);
        Arrays.fill(board[1], 0);

        for (int i = 0; i < arrLength; i++) {
            int element = arr[i];
            if (element == 2) {
                board[0][i] = 1;
                board[1][i] = 1;
            }
            else {
                if (Arrays.stream(board[0]).sum() < u) {
                    board[0][i] = 1;
                }
                else {
                    board[1][i] = 1;
                }
            }
        }
        return board;
    }

    private String getBoardString(int[][] result) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                sb.append(result[i][j]);
            }
            if (i < result.length - 1)
                sb.append(",");
        }
        return sb.toString();
    }

    private void validate(int u, int l, int[] arr) {
        if (u < 0 || u > 100000)
            throw new IllegalArgumentException();

        if (l < 0 || l > 100000)
            throw new IllegalArgumentException();

        int arrayLength = arr.length;
        if (arrayLength < 1 || arrayLength > 10000)
            throw new IllegalArgumentException();

        if (!Arrays.stream(arr).allMatch(num -> num >= 0 && num <= 2))
            throw new IllegalArgumentException();
    }
}
