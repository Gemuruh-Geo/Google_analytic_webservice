package com.jet.filter.ws.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * @author:Gemuruh Geo Pratama
 * */
public class RegexMatcher {
	public static Matcher getRegexMatcher(String regex,String value){
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		return pattern.matcher(value);
	}
}
