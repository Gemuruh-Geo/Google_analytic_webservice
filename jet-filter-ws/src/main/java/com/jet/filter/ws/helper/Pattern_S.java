package com.jet.filter.ws.helper;

import java.util.regex.Pattern;
/*
 * @author:Gemuruh Geo Pratama
 * */
public enum Pattern_S {
	
	NONE("[(]\\bnone\\b[)]") {
		@Override
		public boolean patternMatch(String value) {
			// TODO Auto-generated method stub
			return Pattern.compile(NONE.pattern).matcher(value).matches();
		}
	},
	NOT_SET("[(]\\bnot set\\b[)]") {
		@Override
		public boolean patternMatch(String value) {
			// TODO Auto-generated method stub
			return Pattern.compile(NOT_SET.pattern).matcher(value).matches();
		}
	},
	DIRECT("[(]\\bdirect\\b[)]") {
		@Override
		public boolean patternMatch(String value) {
			// TODO Auto-generated method stub
			return Pattern.compile(DIRECT.pattern).matcher(value).matches();
		}
	};
	
	private String pattern;
	private Pattern_S(String pattern){
		this.pattern = pattern;
	}
	public abstract boolean patternMatch(String value);

}
