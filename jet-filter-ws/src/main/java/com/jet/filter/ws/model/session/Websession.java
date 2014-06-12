package com.jet.filter.ws.model.session;

import java.util.Date;

public class Websession {
	
	private String call_ref_no;
	private String session_type;
	private Date adjusted_call_time;
	private Date timeStamp;
	private String time_elapsed_until_conversion;
	private String tracking_site_code;
	private Date session_time;
	private String landingpage_url;
	private String landingpage_referrer_url;
	private String keywords;
	private String landingpage_utmkeywords;
	private String landingpage_utmkeyword;
	private String landingpage_keywords;
	
	private String landingpage_utmterm;
	private String landingpage_referrer_utmterm;
	private String landingpage_referrer_keywords;
	private String google_keyword;
	private String source;
	private String landingpage_utmsource;
	private String landingpage_referrer_source;
	private String landingpage_referrer_shortname;
	private String google_source;
	private String medium;
	private String landingpage_utmmedium;
	private String landingpage_referrer_medium;
	private String google_medium;
	private String campaign;
	private String landingpage_referrer_utmcampaign;
	private String landingpage_referrer_campaign;
	private String google_campaign;
	private String cookies_utmz;
	
	public Websession(){}
	
	

	

	public Websession(String call_ref_no, String session_type,
			Date adjusted_call_time, Date timeStamp,
			String time_elapsed_until_conversion, String tracking_site_code,
			Date session_time, String landingpage_url,
			String landingpage_referrer_url, String keywords,
			String landingpage_utmkeywords, String landingpage_utmkeyword,
			String landingpage_keywords, String landingpage_utmterm,
			String landingpage_referrer_utmterm,
			String landingpage_referrer_keywords, String google_keyword,
			String source, String landingpage_utmsource,
			String landingpage_referrer_source,
			String landingpage_referrer_shortname, String google_source,
			String medium, String landingpage_utmmedium,
			String landingpage_referrer_medium, String google_medium,
			String campaign, String landingpage_referrer_utmcampaign,
			String landingpage_referrer_campaign, String google_campaign,
			String cookies_utmz) {
		super();
		this.call_ref_no = call_ref_no;
		this.session_type = session_type;
		this.adjusted_call_time = adjusted_call_time;
		this.timeStamp = timeStamp;
		this.time_elapsed_until_conversion = time_elapsed_until_conversion;
		this.tracking_site_code = tracking_site_code;
		this.session_time = session_time;
		this.landingpage_url = landingpage_url;
		this.landingpage_referrer_url = landingpage_referrer_url;
		this.keywords = keywords;
		this.landingpage_utmkeywords = landingpage_utmkeywords;
		this.landingpage_utmkeyword = landingpage_utmkeyword;
		this.landingpage_keywords = landingpage_keywords;
		this.landingpage_utmterm = landingpage_utmterm;
		this.landingpage_referrer_utmterm = landingpage_referrer_utmterm;
		this.landingpage_referrer_keywords = landingpage_referrer_keywords;
		this.google_keyword = google_keyword;
		this.source = source;
		this.landingpage_utmsource = landingpage_utmsource;
		this.landingpage_referrer_source = landingpage_referrer_source;
		this.landingpage_referrer_shortname = landingpage_referrer_shortname;
		this.google_source = google_source;
		this.medium = medium;
		this.landingpage_utmmedium = landingpage_utmmedium;
		this.landingpage_referrer_medium = landingpage_referrer_medium;
		this.google_medium = google_medium;
		this.campaign = campaign;
		this.landingpage_referrer_utmcampaign = landingpage_referrer_utmcampaign;
		this.landingpage_referrer_campaign = landingpage_referrer_campaign;
		this.google_campaign = google_campaign;
		this.cookies_utmz = cookies_utmz;
	}





	public String getTime_elapsed_until_conversion() {
		return time_elapsed_until_conversion;
	}





	public void setTime_elapsed_until_conversion(
			String time_elapsed_until_conversion) {
		this.time_elapsed_until_conversion = time_elapsed_until_conversion;
	}





	public String getCall_ref_no() {
		return call_ref_no;
	}

	public void setCall_ref_no(String call_ref_no) {
		this.call_ref_no = call_ref_no;
	}

	public String getSession_type() {
		return session_type;
	}

	public void setSession_type(String session_type) {
		this.session_type = session_type;
	}

	public Date getAdjusted_call_time() {
		return adjusted_call_time;
	}

	public void setAdjusted_call_time(Date adjusted_call_time) {
		this.adjusted_call_time = adjusted_call_time;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	

	public String getTracking_site_code() {
		return tracking_site_code;
	}

	public void setTracking_site_code(String tracking_site_code) {
		this.tracking_site_code = tracking_site_code;
	}

	public Date getSession_time() {
		return session_time;
	}

	public void setSession_time(Date session_time) {
		this.session_time = session_time;
	}

	public String getLandingpage_url() {
		return landingpage_url;
	}

	public void setLandingpage_url(String landingpage_url) {
		this.landingpage_url = landingpage_url;
	}

	public String getLandingpage_referrer_url() {
		return landingpage_referrer_url;
	}

	public void setLandingpage_referrer_url(String landingpage_referrer_url) {
		this.landingpage_referrer_url = landingpage_referrer_url;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLandingpage_utmkeywords() {
		return landingpage_utmkeywords;
	}

	public void setLandingpage_utmkeywords(String landingpage_utmkeywords) {
		this.landingpage_utmkeywords = landingpage_utmkeywords;
	}

	public String getLandingpage_utmkeyword() {
		return landingpage_utmkeyword;
	}

	public void setLandingpage_utmkeyword(String landingpage_utmkeyword) {
		this.landingpage_utmkeyword = landingpage_utmkeyword;
	}

	public String getLandingpage_keywords() {
		return landingpage_keywords;
	}

	public void setLandingpage_keywords(String landingpage_keywords) {
		this.landingpage_keywords = landingpage_keywords;
	}

	public String getLandingpage_utmterm() {
		return landingpage_utmterm;
	}

	public void setLandingpage_utmterm(String landingpage_utmterm) {
		this.landingpage_utmterm = landingpage_utmterm;
	}

	public String getLandingpage_referrer_utmterm() {
		return landingpage_referrer_utmterm;
	}

	public void setLandingpage_referrer_utmterm(String landingpage_referrer_utmterm) {
		this.landingpage_referrer_utmterm = landingpage_referrer_utmterm;
	}

	public String getLandingpage_referrer_keywords() {
		return landingpage_referrer_keywords;
	}

	public void setLandingpage_referrer_keywords(
			String landingpage_referrer_keywords) {
		this.landingpage_referrer_keywords = landingpage_referrer_keywords;
	}

	public String getGoogle_keyword() {
		return google_keyword;
	}

	public void setGoogle_keyword(String google_keyword) {
		this.google_keyword = google_keyword;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLandingpage_utmsource() {
		return landingpage_utmsource;
	}

	public void setLandingpage_utmsource(String landingpage_utmsource) {
		this.landingpage_utmsource = landingpage_utmsource;
	}

	public String getLandingpage_referrer_source() {
		return landingpage_referrer_source;
	}

	public void setLandingpage_referrer_source(String landingpage_referrer_source) {
		this.landingpage_referrer_source = landingpage_referrer_source;
	}

	public String getLandingpage_referrer_shortname() {
		return landingpage_referrer_shortname;
	}

	public void setLandingpage_referrer_shortname(
			String landingpage_referrer_shortname) {
		this.landingpage_referrer_shortname = landingpage_referrer_shortname;
	}

	public String getGoogle_source() {
		return google_source;
	}

	public void setGoogle_source(String google_source) {
		this.google_source = google_source;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getLandingpage_utmmedium() {
		return landingpage_utmmedium;
	}

	public void setLandingpage_utmmedium(String landingpage_utmmedium) {
		this.landingpage_utmmedium = landingpage_utmmedium;
	}

	public String getLandingpage_referrer_medium() {
		return landingpage_referrer_medium;
	}

	public void setLandingpage_referrer_medium(String landingpage_referrer_medium) {
		this.landingpage_referrer_medium = landingpage_referrer_medium;
	}

	public String getGoogle_medium() {
		return google_medium;
	}

	public void setGoogle_medium(String google_medium) {
		this.google_medium = google_medium;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getLandingpage_referrer_utmcampaign() {
		return landingpage_referrer_utmcampaign;
	}

	public void setLandingpage_referrer_utmcampaign(
			String landingpage_referrer_utmcampaign) {
		this.landingpage_referrer_utmcampaign = landingpage_referrer_utmcampaign;
	}

	public String getLandingpage_referrer_campaign() {
		return landingpage_referrer_campaign;
	}

	public void setLandingpage_referrer_campaign(
			String landingpage_referrer_campaign) {
		this.landingpage_referrer_campaign = landingpage_referrer_campaign;
	}

	public String getGoogle_campaign() {
		return google_campaign;
	}

	public void setGoogle_campaign(String google_campaign) {
		this.google_campaign = google_campaign;
	}

	public String getCookies_utmz() {
		return cookies_utmz;
	}

	public void setCookies_utmz(String cookies_utmz) {
		this.cookies_utmz = cookies_utmz;
	}
	
	
	
	
}
