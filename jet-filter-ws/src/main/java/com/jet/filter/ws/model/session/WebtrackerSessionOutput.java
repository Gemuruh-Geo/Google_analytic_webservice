package com.jet.filter.ws.model.session;

import java.util.List;

public class WebtrackerSessionOutput {
	private List<String> googleSessionID;
	private List<String> jetOriginalSessionID;
	
	private List<String> jetConversionSessionID;
	private List<String> googleUserid;
	private List<String> callRefNo;
	private List<String> dateOfEvent;
	private List<String> timeOfEvent;
	private List<String> source;
	private List<String> medium;
	private List<String> campaign;
	private List<String> keywords;
	private List<String> landingPathPage;
	private List<String> referalUrl;
	private List<String> tracking_code;
	
	public WebtrackerSessionOutput(){}
	
	public WebtrackerSessionOutput(List<String> googleSessionID,
			List<String> jetOriginalSessionID,
			List<String> jetConversionSessionID, List<String> googleUserid,
			List<String> callRefNo, List<String> dateOfEvent,
			List<String> timeOfEvent, List<String> source, List<String> medium,
			List<String> campaign, List<String> keywords,
			List<String> landingPathPage, List<String> referalUrl,List<String>tracking_code) {
		super();
		this.googleSessionID = googleSessionID;
		this.jetOriginalSessionID = jetOriginalSessionID;
		this.jetConversionSessionID = jetConversionSessionID;
		this.googleUserid = googleUserid;
		this.callRefNo = callRefNo;
		this.dateOfEvent = dateOfEvent;
		this.timeOfEvent = timeOfEvent;
		this.source = source;
		this.medium = medium;
		this.campaign = campaign;
		this.keywords = keywords;
		this.landingPathPage = landingPathPage;
		this.referalUrl = referalUrl;
		this.tracking_code = tracking_code;
	}




	public List<String> getGoogleSessionID() {
		return googleSessionID;
	}

	public void setGoogleSessionID(List<String> googleSessionID) {
		this.googleSessionID = googleSessionID;
	}

	public List<String> getJetOriginalSessionID() {
		return jetOriginalSessionID;
	}

	public void setJetOriginalSessionID(List<String> jetOriginalSessionID) {
		this.jetOriginalSessionID = jetOriginalSessionID;
	}

	public List<String> getJetConversionSessionID() {
		return jetConversionSessionID;
	}

	public void setJetConversionSessionID(List<String> jetConversionSessionID) {
		this.jetConversionSessionID = jetConversionSessionID;
	}

	public List<String> getGoogleUserid() {
		return googleUserid;
	}

	public void setGoogleUserid(List<String> googleUserid) {
		this.googleUserid = googleUserid;
	}

	public List<String> getCallRefNo() {
		return callRefNo;
	}

	public void setCallRefNo(List<String> callRefNo) {
		this.callRefNo = callRefNo;
	}

	public List<String> getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(List<String> dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public List<String> getTimeOfEvent() {
		return timeOfEvent;
	}

	public void setTimeOfEvent(List<String> timeOfEvent) {
		this.timeOfEvent = timeOfEvent;
	}

	public List<String> getSource() {
		return source;
	}

	public void setSource(List<String> source) {
		this.source = source;
	}

	public List<String> getMedium() {
		return medium;
	}

	public void setMedium(List<String> medium) {
		this.medium = medium;
	}

	public List<String> getCampaign() {
		return campaign;
	}

	public void setCampaign(List<String> campaign) {
		this.campaign = campaign;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<String> getLandingPathPage() {
		return landingPathPage;
	}

	public void setLandingPathPage(List<String> landingPathPage) {
		this.landingPathPage = landingPathPage;
	}

	public List<String> getReferalUrl() {
		return referalUrl;
	}

	public void setReferalUrl(List<String> referalUrl) {
		this.referalUrl = referalUrl;
	}

	public List<String> getTracking_code() {
		return tracking_code;
	}

	public void setTracking_code(List<String> tracking_code) {
		this.tracking_code = tracking_code;
	}

	
	
}
