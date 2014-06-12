package com.jet.filter.ws.helper;
/*
 * @author:Gemuruh Geo Pratama
 * */
public enum Medium {
	ORGANIC("organic"),
	REFERRAL("referral"),
	CPC("cpc"),
	PPC("ppc");
	
	public String medium;
	Medium(String medium){
		this.medium = medium;
	}
}