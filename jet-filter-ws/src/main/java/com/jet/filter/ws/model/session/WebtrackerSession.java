package com.jet.filter.ws.model.session;

import java.util.Date;

public class WebtrackerSession {
	
	private String call_ref_no;
	private String user_id;
	private String session_id;
	private String session_type;
	private Date session_time;
	private Date call_time;
	private String call_time_event;
	private String call_date_event;
	private String keywords;
	private String source;
	private String medium;
	private String campaign;
	private String landingpage_url;
	private String landingpage_referrer_url;
	private String cookies_utmz;
	private String tracking_site_code;
	
	public WebtrackerSession(){}

	public String getCall_ref_no() {
		return call_ref_no;
	}

	public void setCall_ref_no(String call_ref_no) {
		this.call_ref_no = call_ref_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getSession_type() {
		return session_type;
	}

	public void setSession_type(String session_type) {
		this.session_type = session_type;
	}

	public Date getSession_time() {
		return session_time;
	}

	public void setSession_time(Date session_time) {
		this.session_time = session_time;
	}

	public Date getCall_time() {
		return call_time;
	}

	public void setCall_time(Date call_time) {
		this.call_time = call_time;
	}

	public String getCall_time_event() {
		return call_time_event;
	}

	public void setCall_time_event(String call_time_event) {
		this.call_time_event = call_time_event;
	}

	public String getCall_date_event() {
		return call_date_event;
	}

	public void setCall_date_event(String call_date_event) {
		this.call_date_event = call_date_event;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
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

	public String getCookies_utmz() {
		return cookies_utmz;
	}

	public void setCookies_utmz(String cookies_utmz) {
		this.cookies_utmz = cookies_utmz;
	}

	public String getTracking_site_code() {
		return tracking_site_code;
	}

	public void setTracking_site_code(String tracking_site_code) {
		this.tracking_site_code = tracking_site_code;
	}

	
}
