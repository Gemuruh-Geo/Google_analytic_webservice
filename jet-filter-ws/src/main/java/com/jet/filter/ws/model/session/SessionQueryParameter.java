package com.jet.filter.ws.model.session;

public class SessionQueryParameter {
	
	private String sessionid;
	private String originsessionid;
	
	private String startdate;
	private String enddate;
	private String[] tracking_codes;
	
	
	public SessionQueryParameter(){}


	public String getSessionid() {
		return sessionid;
	}


	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}


	public String getOriginsessionid() {
		return originsessionid;
	}


	public void setOriginsessionid(String originsessionid) {
		this.originsessionid = originsessionid;
	}


	public String getStartdate() {
		return startdate;
	}


	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}


	public String getEnddate() {
		return enddate;
	}


	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}


	public String[] getTracking_codes() {
		return tracking_codes;
	}


	public void setTracking_codes(String[] tracking_codes) {
		this.tracking_codes = tracking_codes;
	}


	
	
}
