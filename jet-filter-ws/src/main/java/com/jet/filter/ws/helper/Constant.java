package com.jet.filter.ws.helper;

public class Constant {

	public static final String EMPTY_STRING = "";
	public static final String COMMA_DELIMITER = ",";
	public static final String FILTER_CATEGORY = "ga:eventCategory=@Phone";
	public static final String GA_SERVICE = "ganalytic";
	public static final String PULL_EVENT_SERVICE = "pullevent";
	public static final String MERGE_SERVICE = "merge";
	public static final String WT_SERVICE = "webtracker";
	public static final String CT_SERVICE = "callTracker";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static class ErrorMessage {
		public static final String INVALID_INPUT_DATA = "{\"error\":\"Invalid Input data\"}";
		public static final String NO_ACCOUNT_OR_DATA_FORMAT_WRONG = "{\"error\":\"No Account Or Data Format Wrong\"}";
		public static final String NO_ACCESS_TOKEN = "{\"error\":\"no access token\"}";
		public static final String NO_DATA = "{\"error\":\"no data\"}";
	}

	public static class Dimension {
		public static final String EVENT_LABEL = "ga:eventLabel";
		public static final String GA_SOURCE = "ga:source";
		public static final String GA_MEDIUM = "ga:medium";
		public static final String GA_CAMPAIGN = "ga:campaign";
		public static final String GA_KEYWORD = "ga:keyword";
		public static final String GA_LANDING_PAGE_PATH = "ga:landingPagePath";
		public static final String GA_REFERRAL_PATH = "ga:referralPath";
		public static final String GA_SESSIONS = "ga:sessions";

		public static final String WT_SOURCE = "wt:source";
		public static final String WT_MEDIUM = "wt:medium";
		public static final String WT_CAMPAIGN = "wt:campaign";
		public static final String WT_KEYWORD = "wt:keyword";

		public static final String CT_SOURCE = "ct:source";
		public static final String CT_MEDIUM = "ct:medium";
		public static final String CT_CAMPAIGN = "ct:campaign";
		public static final String CT_KEYWORD = "ct:keyword";
	}
}
