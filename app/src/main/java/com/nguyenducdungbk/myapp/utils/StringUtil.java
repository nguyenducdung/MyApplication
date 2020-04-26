package com.nguyenducdungbk.myapp.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtil {
    private static final String PHONE_PATTERN = "^((\\+84)|(84)|(0))[0-9]{9,10}$";

    public static boolean isPhoneValid(String phoneNumber) {
        return phoneNumber.matches(PHONE_PATTERN);
    }

    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll(" ").replaceAll(" ", "").toLowerCase();
    }

    public static String createEmail(String name) {
        return removeAccent(name) + "@gmail.com";
    }
}
