package khannedy.spring.rest;

import java.io.IOException;
import java.util.StringTokenizer;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jet.filter.ws.dao.SessionDaoHelper;
import com.jet.filter.ws.model.GoogleAnalyticInput;
import com.jet.filter.ws.model.session.WebtrackerSessionOutput;


public class Main {
	public static void main(String[] args) throws JsonProcessingException, IOException{
		/*
		WebtrackerSessionOutput wos = SessionDaoHelper.getWebtrackingSession("2013-09-24","2013-09-25",new String[]{"WT1506"});
		if(wos==null){
			System.out.println("Tai");
		}else{
			System.out.println(wos.getCallRefNo().size());
			System.out.println(wos.getCampaign().size());
			System.out.println(wos.getDateOfEvent().size());
			System.out.println(wos.getGoogleSessionID().size());
			System.out.println(wos.getGoogleUserid().size());
			System.out.println(wos.getJetConversionSessionID().size());
			System.out.println(wos.getJetOriginalSessionID().size());
			System.out.println(wos.getKeywords().size());
			System.out.println(wos.getLandingPathPage().size());
			System.out.println(wos.getMedium().size());
			System.out.println(wos.getReferalUrl().size());
			System.out.println(wos.getSource().size());
			System.out.println(wos.getTimeOfEvent().size());
			System.out.println(wos.getTracking_code().size());
			
			Gson gson = new GsonBuilder().create();
			System.out.println(gson.toJson(wos));
		}*/
		
		String s= "14397928.350140199.1379906407.1379906407.1380066198.2";
		
	}
	
}
