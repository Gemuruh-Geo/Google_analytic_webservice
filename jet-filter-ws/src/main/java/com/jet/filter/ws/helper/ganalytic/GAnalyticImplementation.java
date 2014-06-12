package com.jet.filter.ws.helper.ganalytic;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;
import com.jet.filter.ws.model.TrackingSite;
/*
 * @author:Gemuruh Geo Pratama
 * */
public class GAnalyticImplementation {
	
	private JsonFactory JSON_FACTORY;
	private HttpTransport HTTP_TRANSPORT;
	private String APPLICATION_NAME;
	private String TRACKING_CODE;
	
	private GAnalyticOauthHelper oauthHelper;
	
	private GoogleAuthorizationCodeFlow flow;
	
	private TrackingSite trackingSite;
	
	
	//private static final String WEB_CREDENTIAL = "{\"web\":{\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"client_secret\":xxxxx",\"token_uri\":\"https://accounts.google.com/o/oauth2/token\",\"client_email\":\"68720858343-acn2ald2leg8ejhn98t6jji7p3gqu3gs@developer.gserviceaccount.com\",\"redirect_uris\":[\"http://localhost:8080/jet-filter-ws/pull\"],\"client_x509_cert_url\":\"https://www.googleapis.com/robot/v1/metadata/x509/68720858343-acn2ald2leg8ejhn98t6jji7p3gqu3gs@developer.gserviceaccount.com\",\"client_id\":\"68720858343-acn2ald2leg8ejhn98t6jji7p3gqu3gs.apps.googleusercontent.com\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"javascript_origins\":[\"http://localhost:8080\"]}}";
	
	private static final String WEB_CREDENTIAL = "{\"web\":{\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"client_secret\":\"xxxxxx",\"token_uri\":\"https://accounts.google.com/o/oauth2/token\",\"client_email\":\"68720858343-6d7k4vfltk1mkuabulornqc5lf4l7h76@developer.gserviceaccount.com\",\"redirect_uris\":[\"http://ec2-54-253-45-215.ap-southeast-2.compute.amazonaws.com/jet-filter-ws/pull\"],\"client_x509_cert_url\":\"https://www.googleapis.com/robot/v1/metadata/x509/68720858343-6d7k4vfltk1mkuabulornqc5lf4l7h76@developer.gserviceaccount.com\",\"client_id\":\"68720858343-6d7k4vfltk1mkuabulornqc5lf4l7h76.apps.googleusercontent.com\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"javascript_origins\":[\"http://ec2-54-253-45-215.ap-southeast-2.compute.amazonaws.com\"]}}";
	public GAnalyticImplementation(){
		
	}
	
	public GAnalyticImplementation(Builder builder) {
		this.JSON_FACTORY = builder.JSON_FACTORY;
		this.HTTP_TRANSPORT = builder.HTTP_TRANSPORT;
		this.APPLICATION_NAME = builder.APPLICATION_NAME;
		this.TRACKING_CODE = builder.TRACKING_CODE;
	}
	
	
	public static class Builder{
		private final JsonFactory JSON_FACTORY;
		private final HttpTransport HTTP_TRANSPORT;
		private final String APPLICATION_NAME;
		
		private String TRACKING_CODE;
		
		public Builder(JsonFactory jsonFactory,HttpTransport httpTransport,String applicationName){
			this.JSON_FACTORY = jsonFactory;
			this.HTTP_TRANSPORT = httpTransport;
			this.APPLICATION_NAME = applicationName;
		}
		
		
		public Builder loadTrackingCode(String trackingCode){
			this.TRACKING_CODE = trackingCode;
			return this;
		}
		
		public GAnalyticImplementation build(){
			return new GAnalyticImplementation(this);
		}
		
	}
	
	
	public int initAuthorization() throws IOException{
		
		trackingSite = GaDbHelper.getTrackingSiteByTrackingCode(TRACKING_CODE);
		try{
			//String WEB_CREDENTIAL = parseClientSecret(trackingSite.getAttributeJson());
			
			oauthHelper = new GAnalyticOauthHelper.Builder(JSON_FACTORY, HTTP_TRANSPORT).
					setApplicationName(APPLICATION_NAME).getBClientSecret(TRACKING_CODE,WEB_CREDENTIAL).build();
			
			
			
			
			flow = oauthHelper.createCodeFlow();
			return 1;
			
		}catch(Exception e){
			return 0;
		}
		
		
		
	}
	
	public TrackingSite getTrackingSite(String tracking_code){
		return GaDbHelper.getTrackingSiteByTrackingCode(tracking_code);
	}
	
	
	public String retreiveProfileIdAt(Analytics analytics,int index) throws IOException{
		 
		
		Accounts accounts = analytics.management().accounts().list().execute();
		
		
		if(accounts.isEmpty()){
			System.out.println("No Account");
			System.exit(1);
		}
		
		String accountId = accounts.getItems().get(index).getId();
		
		
		Webproperties webProperties = analytics.management().webproperties().list(accountId).execute();
		if(webProperties.isEmpty()){
			System.out.println("No Web Properties");
			System.exit(1);
		}
		String webPropertiesId = webProperties.getItems().get(index).getId();
		
		Profiles profiles = analytics.management().profiles().list(accountId, webPropertiesId).execute();
		if(profiles.isEmpty()){
			System.out.println("No Profile");
			System.exit(1);
		}
		String profileid = profiles.getItems().get(index).getId();
		
		
		return profileid;
	}
	
	public String retreiveProfileIdAt(Analytics analytics) throws IOException{
		
		Accounts accounts = analytics.management().accounts().list().execute();
		
		String trackingID = parseTrackingId();
		if(trackingID==null){
			return "";
		}
		
		
		int startindex = trackingID.indexOf("-", trackingID.indexOf("-")+1)+1;
		int endindex = trackingID.length();
		
		int index = Integer.parseInt(trackingID.substring(startindex, endindex))-1;
		
		
		
		if(accounts.isEmpty()){
			System.out.println("No Account");
			System.exit(1);
		}
		
		String accountId = accounts.getItems().get(index).getId(); 
		
		Webproperties webProperties = analytics.management().webproperties().list(accountId).execute();
		if(webProperties.isEmpty()){
			System.out.println("No Web Properties");
			System.exit(1);
		}
		String webPropertiesId = webProperties.getItems().get(index).getId();
		
		Profiles profiles = analytics.management().profiles().list(accountId, webPropertiesId).execute();
		if(profiles.isEmpty()){
			System.out.println("No Profile");
			System.exit(1);
		}
		String profileid = profiles.getItems().get(index).getId();
		return profileid;
		
	}
	
	
	public GaData executeGaData(GaDataProperties gaDataProperties, Analytics analytics) throws IOException{
		
		
		String profileId = retreiveProfileIdAt(analytics);
		GaData gaData = analytics.data().ga()
		.get("ga:"+profileId, gaDataProperties.getStartDate(), gaDataProperties.getEndDate(), gaDataProperties.getMetrics()).
		setDimensions(gaDataProperties.getDimension()).
		execute();
		return gaData;
	}
	
	public GaData executeGaDataFilterBy(GaDataProperties gaDataProperties, Analytics analytics, String filter) throws IOException{
		String profileId = retreiveProfileIdAt(analytics);
		GaData gaData = analytics.data().ga()
		.get("ga:"+profileId, gaDataProperties.getStartDate(), gaDataProperties.getEndDate(), gaDataProperties.getMetrics())
		.setDimensions(gaDataProperties.getDimension())
		.setFilters(filter)
		.execute();
		return gaData;
	}

	public GoogleAuthorizationCodeFlow getAuthorizationCodeFlow(){
		return flow;
	}
	
	public Analytics getAnalytics(String authCode,Credential credential) throws IOException{
		return oauthHelper.createAnalytics(flow, authCode,credential);
	}
	public GAnalyticOauthHelper getOauthHelper(){
		return oauthHelper;
	}
	
	
	public Credential getCredential(GoogleAuthorizationCodeFlow flow,String authorizationCode){
		String config_json = null;
		try{
			config_json = trackingSite.getConfigJson();
			
		}catch(NullPointerException ex){
			System.err.println("trackingSite is null");
			ex.printStackTrace();
		}
		
		Credential credential = null;
		try {
			credential = oauthHelper.getUserCredential(flow, authorizationCode,config_json,TRACKING_CODE);
		} catch (IOException e) {
			
			e.printStackTrace();
		}catch(NullPointerException np){
			System.err.println("Credential null");
			np.printStackTrace();
		}
		return credential;
	}
	
	/*Catatan ini harus diperhatikan null ketika webAnalytics==null/ googlaAnalytics==null/ tracking code==null*/
	private String parseTrackingId() throws JsonProcessingException, IOException{
		//TrackingSite trackingSite = GaDbHelper.getTrackingSiteByTrackingCode(TRACKING_CODE);
		if(trackingSite!=null){
			ObjectMapper mapper = new ObjectMapper();
			String result = mapper.readTree(trackingSite.getConfigJson()).get("webAnalytics").get("GoogleAnalytics").get("trackingCode").asText();
			return result;
		}
		return null;
		
	}
	private String parseClientSecret(String attributes_json) throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> res = mapper.readValue(attributes_json, LinkedHashMap.class);
		
		res.remove("accountNumber");
		
		String result = mapper.writeValueAsString(res);
		
		return result;
		
	}
	
	
	
}
