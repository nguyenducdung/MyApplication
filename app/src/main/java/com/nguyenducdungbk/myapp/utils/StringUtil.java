package com.nguyenducdungbk.myapp.utils;

import android.annotation.SuppressLint;

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


    @SuppressLint("DefaultLocale")
    public static String convertViewQuantity(long view) {
        long million = 1000000;
        long thousand = 1000;
        String rs = view + "";
        if (view >= million) {
            double v = view * 1f / million;
            rs = String.format("%.1fTr", v);
        } else if (view >= 1000) {
            double v = view * 1f / thousand;
            rs = String.format("%.1f", v);
        }
        return (rs);
    }
}
