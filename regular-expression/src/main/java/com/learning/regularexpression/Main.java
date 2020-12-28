package com.learning.regularexpression;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String content = "112*11*0";

        String pattern = "\\d*\\*\\d*\\*\\d*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);
    }
}
