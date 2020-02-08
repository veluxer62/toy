package com.veluxer.codingTest.wooa.task2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
    public String solution(String s) {
        validate(s);

        Map<String, Integer> stack = getFileTypeAndSize(s);

        return stack.entrySet().stream()
                .map(it -> String.format("%s %sb", it.getKey(), it.getValue()))
                .collect(Collectors.joining("\n"));
    }

    private Map<String, Integer> getFileTypeAndSize(String s) {
        String[] split = s.trim().split("\n");

        Map<String, Integer> stack = new LinkedHashMap<>();
        stack.put("music", 0);
        stack.put("images", 0);
        stack.put("movies", 0);
        stack.put("other", 0);

        for (String line : split) {
            String fileNameWithExt = getFileNameWithExtension(line);

            int fileSize = getFileSize(line);
            String fileType = getExtensionType(fileNameWithExt);
            Integer fileSizeOrZero = stack.getOrDefault(fileType, 0);

            stack.put(fileType, fileSizeOrZero + fileSize);
        }
        return stack;
    }

    private void validate(String s) {
        if (s.isEmpty())
            throw new IllegalArgumentException();

        if (s.split(System.lineSeparator()).length > 500)
            throw new IllegalArgumentException();
    }

    private String getFileNameWithExtension(String line) {
        int blankSplitIdx = line.lastIndexOf(" ");

        String fileNameWithExt = line.substring(0, blankSplitIdx);

        if (fileNameWithExt.length() > 30)
            throw new IllegalArgumentException();

//            String regex = "^[A-Za-z0-9]+(\\.(?!)(mp3|aac|flac|jpg|bmp|gif|mp4|avi|mkv|7z|txt|zip))$";
//
//            boolean matches = fileNameWithExt.matches(regex);
        return fileNameWithExt;
    }

    private int getFileSize(String line) {
        int blankSplitIdx = line.lastIndexOf(" ");
        String fileSizeWithByteString = line.substring(blankSplitIdx + 1).trim();
        String fileSize = fileSizeWithByteString.replaceAll("\\D", "");

        if (fileSize.isEmpty())
            throw new IllegalArgumentException();

        return Integer.parseInt(fileSize);
    }

    private String getExtensionType(String fileNameWithExt) {
        int pos = fileNameWithExt.lastIndexOf(".");
        String ext = fileNameWithExt.substring(pos + 1);
        String key;
        switch (ext) {
            case "mp3":
            case "aac":
            case "flac":
                key = "music";
                break;
            case "jpg":
            case "bmp":
            case "gif":
                key = "images";
                break;
            case "mp4":
            case "avi":
            case "mkv":
                key = "movies";
                break;
            default:
                key = "other";
        }
        return key;
    }
}
