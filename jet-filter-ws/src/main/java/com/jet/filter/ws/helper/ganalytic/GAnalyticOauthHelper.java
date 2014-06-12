package com.jet.filter.ws.helper.ganalytic;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;





import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
/*
 * @author:Gemuruh Geo Pratama
 * */
public class GAnalyticOauthHelper {
	private JsonFactory jsonFactory;
	private HttpTransport httpTransport;
	private String applicationName;
	private String CALLBACK_URI;
	
	private GoogleClientSecrets clientSecrets;
	
	
	public GAnalyticOauthHelper(){
		
	}
	
	public GAnalyticOauthHelper(Builder builder){
		this.jsonFactory = builder.jsonFactory;
		this.httpTransport = builder.httpTransport;
		this.applicationName = builder.applicationName;
		this.clientSecrets = builder.clientSecrets;
		this.CALLBACK_URI = clientSecrets.getWeb().getRedirectUris().get(0);
		
	}
	
	public static class Builder{
		
		private final JsonFactory jsonFactory;
		private final HttpTransport httpTransport;
		private String applicationName = "";
		private GoogleClientSecrets clientSecrets;
		
		
		
		public Builder(JsonFactory jsonFactory,HttpTransport httpTransport){
			this.jsonFactory = jsonFactory;
			this.httpTransport = httpTransport;
		}
		public Builder setApplicationName(String applicationName){
			this.applicationName = applicationName;
			return this;
		}
		
		public Builder getBClientSecret(String tracking_code, String attributes_json){
			
			
			if(attributes_json!=null && !attributes_json.equals("")){
				
				Reader reader = new InputStreamReader(new ByteArrayInputStream(attributes_json.getBytes()));
				try {
					
					clientSecrets = GoogleClientSecrets.load(jsonFactory, reader);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			
			return this;
		}
		
		public GoogleClientSecrets getClientSecret(){
			return this.clientSecrets;
		}
		
		public GAnalyticOauthHelper build(){
			return new GAnalyticOauthHelper(this);
		}
	}
	
	public GoogleAuthorizationCodeFlow createCodeFlow() throws IOException{
		return new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, 
				Collections.singletonList(AnalyticsScopes.ANALYTICS_READONLY)).build();
	}
	
	public String buildCallBackURI(GoogleAuthorizationCodeFlow flow){
		
		final GoogleAuthorizationCodeRequestUrl authUrl = flow.newAuthorizationUrl();
		return authUrl.setRedirectUri(CALLBACK_URI).setAccessType("offline").build();
		
	}
	
	public Analytics createAnalytics(GoogleAuthorizationCodeFlow flow,String authorizationCode,Credential credential) throws IOException{
		return new Analytics.Builder(httpTransport, jsonFactory, credential).setApplicationName(applicationName).build();
	}
	
	public GoogleClientSecrets getClientSecret(){
		return clientSecrets;
	}
	
	
	public Credential getUserCredential(GoogleAuthorizationCodeFlow flow, String authorizationCode,String config_json, String TRACKING_CODE) throws IOException{
		
		
		Map<String, String> authTokenAndRefreshToken = getTokenAndRefreshToken(config_json);
		
		
		if(authTokenAndRefreshToken==null && authorizationCode!=null){
			
			System.out.println("======================= Masuk 1");
			
			GoogleTokenResponse response = flow.newTokenRequest(authorizationCode).setRedirectUri(CALLBACK_URI).execute();
			
			Credential credential = flow.createAndStoreCredential(response, null);
			
			String authToken = credential.getAccessToken();
			String refreshToken = credential.getRefreshToken();

			
			String new_config_json = contstructAuthTokenAndRefreshToken(config_json,refreshToken,authToken);
			
			int result = GaDbHelper.updateConfigJson(new_config_json, TRACKING_CODE);
			
			return credential;
			
		}else if(authTokenAndRefreshToken!=null && authorizationCode==null){
			
			String accessToken = authTokenAndRefreshToken.get("auth_token");
			String tokenRefresh =authTokenAndRefreshToken.get("refresh_token"); 
			
			System.out.println("======================= Masuk 2");
			Credential gc = null;
			
			//incase refresh token==null
			if(tokenRefresh==null){
				gc = new GoogleCredential().setAccessToken(accessToken);
				
			}else{
				gc = new GoogleCredential.Builder().
						setClientSecrets(clientSecrets).
						setJsonFactory(jsonFactory).
						setTransport(httpTransport).build().
						setRefreshToken(tokenRefresh).setAccessToken(accessToken);
			}
			
			
			return gc;
		}else{ 
			return null;
		}
		
		
	}
		
	private Map<String, String> getTokenAndRefreshToken(String jsonGA) throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode headNode = mapper.readTree(jsonGA);
		Map<String, Object> head_map = mapper.readValue(jsonGA, LinkedHashMap.class);
		
		JsonNode node_tracking_code = headNode.get("webAnalytics")==null?null:headNode.get("webAnalytics").get("GoogleAnalytics")==null?null:headNode.get("webAnalytics").get("GoogleAnalytics").get("trackingCode");
		
		if(node_tracking_code!=null){
			Map<String, Object> res = ((Map<String, Object>)((Map<String, Object>)head_map.get("webAnalytics")).get("GoogleAnalytics"));
			
			String refresh_token = (String)res.get("refresh_token");
			String auth_token = (String)res.get("auth_token");
			if(/*refresh_token!=null &&*/ auth_token!=null){
				Map<String, String> credential = new LinkedHashMap<String, String>();
				credential.put("refresh_token", (String)res.get("refresh_token"));
				credential.put("auth_token", (String)res.get("auth_token"));
				return credential;
			}else{
				return null;
			}
			
			
		}
		return null;
		
		
	}
	
	private String contstructAuthTokenAndRefreshToken(String jsonGA,String refresh_token,String auth_token) throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode headNode = mapper.readTree(jsonGA);
		Map<String, Object> head_map = mapper.readValue(jsonGA, LinkedHashMap.class);
		
		JsonNode node_tracking_code = headNode.get("webAnalytics")==null?null:headNode.get("webAnalytics").get("GoogleAnalytics")==null?null:headNode.get("webAnalytics").get("GoogleAnalytics").get("trackingCode");
		
		if(node_tracking_code!=null){
			Map<String, Object> res = ((Map<String, Object>)((Map<String, Object>)head_map.get("webAnalytics")).get("GoogleAnalytics"));
			res.put("refresh_token", refresh_token);
			res.put("auth_token", auth_token);
			return mapper.writeValueAsString(head_map);
		}
		return null;
	}
	
	
	
	
}
