package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] result = StringUtils.substringsBetween("ABCDEFG","B","D");
        System.out.println(Arrays.toString(result));
    }
}