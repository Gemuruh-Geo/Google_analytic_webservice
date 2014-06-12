package com.jet.filter.ws.model;
/*
 * @author:Gemuruh Geo Pratama
 * */
public class OutputData {
	private String siteCode;
	private AnalyticData analyticFormattedData;
	
	public OutputData(){}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public AnalyticData getAnalyticFormattedData() {
		return analyticFormattedData;
	}

	public void setAnalyticFormattedData(AnalyticData analyticFormattedData) {
		this.analyticFormattedData = analyticFormattedData;
	}
	
	
}
