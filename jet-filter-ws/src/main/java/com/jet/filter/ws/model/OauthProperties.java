package com.jet.filter.ws.model;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.jet.filter.ws.helper.ganalytic.GAnalyticOauthHelper;

public class OauthProperties {
	private GAnalyticOauthHelper oauthelper;
	private GoogleAuthorizationCodeFlow codeFlow;

	public OauthProperties(GAnalyticOauthHelper oauthHelper,
			GoogleAuthorizationCodeFlow codeFlow) {
		this.oauthelper = oauthHelper;
		this.codeFlow = codeFlow;
	}

	public GAnalyticOauthHelper getHelper() {
		return oauthelper;
	}

	public GoogleAuthorizationCodeFlow getCodeFlow() {
		return codeFlow;
	}
}
