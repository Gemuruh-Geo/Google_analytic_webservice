package com.jet.filter.ws.helper;

import java.util.regex.Pattern;
/*
 * @author:Gemuruh Geo Pratama
 * */
public enum Pattern_J {
	
	SITE_CODE("\\bsiteCode\\b") {
		@Override
		public boolean patternMatch(String value) {
			
			return Pattern.compile(SITE_CODE.pattern).matcher(value).find();
		}
	},
	VIEW("\\bview\\b") {
		@Override
		public boolean patternMatch(String value) {
			
			return Pattern.compile(VIEW.pattern).matcher(value).find();
		}
	},
	ANALYTIC_DATA("\\banalyticData\\b") {
		@Override
		public boolean patternMatch(String value) {
			
			return Pattern.compile(ANALYTIC_DATA.pattern).matcher(value).find();
		}
	},
	SOURCE("\\bsource\\b") {
		@Override
		public boolean patternMatch(String value) {
			
			return Pattern.compile(SOURCE.pattern).matcher(value).find();
		}
	},
	MEDIUM("\\bmedium\\b") {
		@Override
		public boolean patternMatch(String value) {
			return Pattern.compile(MEDIUM.pattern).matcher(value).find();
		}
	},
	CAMPAIGN("\\bcampaign\\b") {
		@Override
		public boolean patternMatch(String value) {
			return Pattern.compile(CAMPAIGN.pattern).matcher(value).find();
		}
	},
	KEYWORDS("\\bkeywords\\b") {
		@Override
		public boolean patternMatch(String value) {
			return Pattern.compile(KEYWORDS.pattern).matcher(value).find();
		}
	};
	
	private String pattern;
	
	private Pattern_J(String pattern){
		this.pattern = pattern;
		
	}
	
	public abstract boolean patternMatch(String value);
}
