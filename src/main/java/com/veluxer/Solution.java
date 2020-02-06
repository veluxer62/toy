package com.veluxer;

public class Solution {
    public int solution(int decimalNumber) {
        if (decimalNumber < 0) return 0;

        String binary = Integer.toString(decimalNumber, 2);
        char[] binaryChars = binary.toCharArray();

        BinaryGapCalculator binaryGapCalculator = new BinaryGapCalculator(binaryChars);
        binaryGapCalculator.calculate();

        int biggestNumberZeroCount = binaryGapCalculator.getBiggestNumberZeroCount();
        int numberOneCount = binaryGapCalculator.getNumberOneCount();

        if (numberOneCount == 1) return 0;

        return biggestNumberZeroCount;
    }

    private static class BinaryGapCalculator {
        private char[] binaryChars;
        private int biggestNumberZeroCount;
        private int numberOneCount;

        public BinaryGapCalculator(char... binaryChars) {
            this.binaryChars = binaryChars;
        }

        public int getBiggestNumberZeroCount() {
            return biggestNumberZeroCount;
        }

        public int getNumberOneCount() {
            return numberOneCount;
        }

        public void calculate() {
            biggestNumberZeroCount = 0;
            numberOneCount = 0;
            int numberZeroCount = 0;

            for (char binaryChar : binaryChars) {
                boolean isNumberOneChar = binaryChar == 49;
                if (isNumberOneChar) {
                    numberOneCount++;
                    if (biggestNumberZeroCount < numberZeroCount) {
                        biggestNumberZeroCount = numberZeroCount;
                    }
                    numberZeroCount = 0;
                } else {
                    numberZeroCount ++;
                }
            }
        }
    }
}
