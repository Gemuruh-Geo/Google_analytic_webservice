package com.jet.filter.ws.model.session;

import java.util.List;

public class SessionQueryResult {
	private String navigation;
	private String sessionhistory;
	private List<Websession>websession;
	private List<WebtrackerSession> webtrackerSessions;
	
	public SessionQueryResult(){
		
	}

	public String getNavigation() {
		return navigation;
	}

	public void setNavigation(String navigation) {
		this.navigation = navigation;
	}

	public String getSessionhistory() {
		return sessionhistory;
	}

	public void setSessionhistory(String sessionhistory) {
		this.sessionhistory = sessionhistory;
	}

	public List<Websession> getWebsession() {
		return websession;
	}

	public void setWebsession(List<Websession> websession) {
		this.websession = websession;
	}

	public List<WebtrackerSession> getWebtrackerSessions() {
		return webtrackerSessions;
	}

	public void setWebtrackerSessions(List<WebtrackerSession> webtrackerSessions) {
		this.webtrackerSessions = webtrackerSessions;
	}

	
}
