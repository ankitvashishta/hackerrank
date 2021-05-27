package com.hackerrank.practice.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelCase {

	public int camelcase(String s) {
		// Write your code here
		Pattern pattern = Pattern.compile("([A-Z][a-z]+)");
		Matcher matcher = pattern.matcher(s);
		int i = 1;
		while(matcher.find()) {
			i++;
		}
		return i;
	}

}
