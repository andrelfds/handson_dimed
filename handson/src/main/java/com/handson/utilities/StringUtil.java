package com.handson.utilities;

public class StringUtil {
	public static String toNumericDigitsOnly(String s) {
		return s.replaceAll("\\D*", "");
	}
}
