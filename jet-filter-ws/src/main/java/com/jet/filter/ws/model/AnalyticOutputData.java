package com.jet.filter.ws.model;

import java.util.List;

public class AnalyticOutputData {
	private List<String> googleSessionID;
	private List<String> googleUserID;
	private List<String> source;
	private List<String> medium;
	private List<String> keyword;
	private List<String> campaign;
	private List<String> callRefno;
	private List<String> dateEvent;
	private List<String> timeEvent;
	private List<String> landingPagePath;
	private List<String> referralPath;
	
	public AnalyticOutputData(){}

	public AnalyticOutputData(List<String> googleSessionID,
			List<String> googleUserID, List<String> source,
			List<String> medium, List<String> keyword, List<String> campaign,
			List<String> callRefno, List<String> dateEvent,
			List<String> timeEvent, List<String> landingPagePath,
			List<String> referralPath) {
		super();
		this.googleSessionID = googleSessionID;
		this.googleUserID = googleUserID;
		this.source = source;
		this.medium = medium;
		this.keyword = keyword;
		this.campaign = campaign;
		this.callRefno = callRefno;
		this.dateEvent = dateEvent;
		this.timeEvent = timeEvent;
		this.landingPagePath = landingPagePath;
		this.referralPath = referralPath;
	}


	public List<String> getGoogleSessionID() {
		return googleSessionID;
	}


	public void setGoogleSessionID(List<String> googleSessionID) {
		this.googleSessionID = googleSessionID;
	}


	public List<String> getGoogleUserID() {
		return googleUserID;
	}


	public void setGoogleUserID(List<String> googleUserID) {
		this.googleUserID = googleUserID;
	}


	public List<String> getLandingPagePath() {
		return landingPagePath;
	}



	public void setLandingPagePath(List<String> landingPagePath) {
		this.landingPagePath = landingPagePath;
	}


	public List<String> getReferralPath() {
		return referralPath;
	}




	public void setReferralPath(List<String> referralPath) {
		this.referralPath = referralPath;
	}


	public List<String> getCallRefno() {
		return callRefno;
	}


	public void setCallRefno(List<String> callRefno) {
		this.callRefno = callRefno;
	}



	public List<String> getDateEvent() {
		return dateEvent;
	}



	public void setDateEvent(List<String> dateEvent) {
		this.dateEvent = dateEvent;
	}



	public List<String> getTimeEvent() {
		return timeEvent;
	}



	public void setTimeEvent(List<String> timeEvent) {
		this.timeEvent = timeEvent;
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

	public List<String> getKeyword() {
		return keyword;
	}

	public void setKeyword(List<String> keyword) {
		this.keyword = keyword;
	}

	public List<String> getCampaign() {
		return campaign;
	}

	public void setCampaign(List<String> campaign) {
		this.campaign = campaign;
	}
}
	
	
