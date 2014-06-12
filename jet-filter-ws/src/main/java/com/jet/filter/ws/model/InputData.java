package com.jet.filter.ws.model;
/*
 * @author:Gemuruh Geo Pratama
 * */
public class InputData {
	private String siteCode;
	private String view;
	private AnalyticData analyticData;
	
	public InputData(){}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public AnalyticData getAnalyticData() {
		return analyticData;
	}

	public void setAnalyticData(AnalyticData analyticData) {
		this.analyticData = analyticData;
	}
	
	public String toString(){
		return "Sitecode: "+siteCode+", view: "+view+", source: "+analyticData.getSource();
	}
}
