package com.walmart.api.sell.utils;

import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class Validation {

    public boolean isPalindrome(String input) {
        String temp = input.replaceAll("\\s+", "").toLowerCase();
        return IntStream.range(0, input.length() / 2)
                .allMatch(i -> temp.charAt(i) == temp.charAt(temp.length() - i - 1));
    }
}
