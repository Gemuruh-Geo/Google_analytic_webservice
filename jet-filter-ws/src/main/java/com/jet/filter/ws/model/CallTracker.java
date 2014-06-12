package com.jet.filter.ws.model;

import java.util.List;

public class CallTracker {
	private List<String> call_ref_no;
	private List<String> source;
	private List<String> medium;
	private List<String> campaign;
	private List<String> keywords;
	
	public CallTracker(){}
	
	
	
	public CallTracker(List<String> call_ref_no, List<String> source,
			List<String> medium, List<String> campaign, List<String> keywords) {
		super();
		this.call_ref_no = call_ref_no;
		this.source = source;
		this.medium = medium;
		this.campaign = campaign;
		this.keywords = keywords;
	}



	public List<String> getCall_ref_no() {
		return call_ref_no;
	}
	public void setCall_ref_no(List<String> call_ref_no) {
		this.call_ref_no = call_ref_no;
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
	
	
	
}
