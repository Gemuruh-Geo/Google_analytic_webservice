package com.jet.filter.ws.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.jet.filter.ws.model.session.Websession;
import com.jet.filter.ws.model.session.WebtrackerSession;
import com.jet.filter.ws.model.session.WebtrackerSessionOutput;

public class SessionDaoImpl implements SessionDao{
	
	private JdbcTemplate jdbcTemplate;

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String getNavigation(final UUID sessionid) {
		
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		String result =jdbcTemplate.execute(new CallableStatementCreator() {
			
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				CallableStatement cs = con.prepareCall("{?=call getnavigation(?)}");
				
				cs.registerOutParameter(1, Types.VARCHAR);
				cs.setObject(2,sessionid);
				return cs;
				
			}
		}, new CallableStatementCallback<String>() {

			@Override
			public String doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				
				
				cs.execute();
				String result = cs.getString(1);
				return result;
				
			}
			
		});
		
		
		return result;
	}

	@Override
	public String getSessionHistory(final UUID originSessionid) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		String result = jdbcTemplate.execute(new CallableStatementCreator() {
			
			@Override
			public CallableStatement createCallableStatement(Connection con)
					throws SQLException {
				
				CallableStatement cs = con.prepareCall("{?=call getsessionhistory(?)}");
				cs.registerOutParameter(1, Types.VARCHAR);
				cs.setObject(2, originSessionid);
				return cs;
			}
		}, new CallableStatementCallback<String>() {
			@Override
			public String doInCallableStatement(CallableStatement cs)
					throws SQLException, DataAccessException {
				
				cs.execute();
				String result = cs.getString(1);
				
				return result;
			}
		});
		
		
		return result;
	}

	@Override
	public List<Websession> getWebSession(final String startdate, final String enddate,
			final String[] tracking_code){
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		
		List<Websession> websessions = jdbcTemplate.execute(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				
				String sql = "select * from getwebsessions(?,?,?)";
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				
				Date startDated = null;
				Date endDated = null;
				try {
					startDated = dateFormat.parse(startdate);
					endDated = dateFormat.parse(enddate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				java.sql.Timestamp startDateSq = new java.sql.Timestamp(startDated.getTime());
				java.sql.Timestamp endDateSq = new java.sql.Timestamp(endDated.getTime());
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setTimestamp(1, startDateSq);
				ps.setTimestamp(2, endDateSq);
				
				Array sqlArray = con.createArrayOf("varchar", tracking_code);
				ps.setArray(3, sqlArray);

			return ps;
			}
		},new PreparedStatementCallback<List<Websession>>() {

			@Override
			public List<Websession> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				
				List<Websession> webss = new ArrayList<Websession>();
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					String call_ref_no = rs.getString("call_ref_no");
					String session_type = rs.getString("session_type");
					
					Date adjusted_call_time = null;
					if(rs.getTimestamp("adjusted_call_time")!=null){
						adjusted_call_time = new Date(rs.getTimestamp("adjusted_call_time").getTime());
					}
					
					Date timeStamp = null; 
					if(rs.getTimestamp("timeStamp")!=null){
						timeStamp = new Date(rs.getTimestamp("timeStamp").getTime());
					}
					
					
					String time_elapsed_until_conversion = rs.getString("time_elapsed_until_conversion");
					
					String tracking_site_code = rs.getString("tracking_site_code");
					
					Date session_time = null;
					if(rs.getTimestamp("session_time")!=null){
						session_time = new Date(rs.getTimestamp("session_time").getTime());
					}
					
					
					String landingpage_url = rs.getString("landingpage_url");
					String landingpage_referrer_url = rs.getString("landingpage_referrer_url");
					String keywords = rs.getString("keywords");
					String landingpage_utmkeywords = rs.getString("landingpage_utmkeywords");
					String landingpage_utmkeyword = rs.getString("landingpage_utmkeyword");
					String landingpage_keywords = rs.getString("landingpage_keywords");
					
					String landingpage_utmterm = rs.getString("landingpage_utmterm");
					String landingpage_referrer_utmterm = rs.getString("landingpage_referrer_utmterm");
					String landingpage_referrer_keywords = rs.getString("landingpage_referrer_keywords");
					String google_keyword = rs.getString("google_keyword");
					String source = rs.getString("source");
					String landingpage_utmsource = rs.getString("landingpage_utmsource");
					String landingpage_referrer_source = rs.getString("landingpage_referrer_source");
					String landingpage_referrer_shortname = rs.getString("landingpage_referrer_shortname");
					String google_source = rs.getString("google_source");
					String medium = rs.getString("medium");
					String landingpage_utmmedium = rs.getString("landingpage_utmmedium");
					String landingpage_referrer_medium = rs.getString("landingpage_referrer_medium");
					String google_medium = rs.getString("google_medium");
					String campaign = rs.getString("campaign");
					String landingpage_referrer_utmcampaign = rs.getString("landingpage_referrer_utmcampaign");
					String landingpage_referrer_campaign = rs.getString("landingpage_referrer_campaign");
					String google_campaign = rs.getString("google_campaign");
					String cookies_utmz = rs.getString("cookies_utmz");
					
					Websession ws = new Websession(call_ref_no, session_type, 
							adjusted_call_time,
							timeStamp,
							time_elapsed_until_conversion,
							tracking_site_code, session_time,
							landingpage_url,
							landingpage_referrer_url,
							keywords,
							landingpage_utmkeywords,
							landingpage_utmkeyword,
							landingpage_keywords,
							landingpage_utmterm,
							landingpage_referrer_utmterm,
							landingpage_referrer_keywords,
							google_keyword,
							source,
							landingpage_utmsource,
							landingpage_referrer_source,
							landingpage_referrer_shortname,
							google_source,
							medium,
							landingpage_utmmedium,
							landingpage_referrer_medium,
							google_medium,
							campaign,
							landingpage_referrer_utmcampaign,
							landingpage_referrer_campaign,
							google_campaign,
							cookies_utmz);
					
					//Websession ws = new Websession();
					ws.setCall_ref_no(call_ref_no);
					
					webss.add(ws);
					 
				}
				
				return webss;
			}
			
		});
		
		
		
		
		return websessions;
	}
	
	@Override
	public WebtrackerSessionOutput getWebtrackerSession(final String startdate,
			final String enddate, final String[] tracking_codes) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		
		WebtrackerSessionOutput webtrackerSessionsO = jdbcTemplate.execute(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				
				
				String sql = "select * from getWTWebsession(?,?,?)";
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				Date startDated = null;
				Date endDated = null;
				try {
					startDated = dateFormat.parse(startdate);
					
					endDated = dateFormat.parse(enddate);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				java.sql.Timestamp startDateSq = new java.sql.Timestamp(startDated.getTime());
				java.sql.Timestamp endDateSq = new java.sql.Timestamp(endDated.getTime());
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setTimestamp(1, startDateSq);
				ps.setTimestamp(2, endDateSq);
				
				Array sqlArray = con.createArrayOf("varchar", tracking_codes);
				ps.setArray(3, sqlArray);
				
				
				
				return ps;
			}
		}, new PreparedStatementCallback<WebtrackerSessionOutput>() {

			@Override
			public WebtrackerSessionOutput doInPreparedStatement(
					PreparedStatement ps) throws SQLException,
					DataAccessException {
				
				
				ResultSet rs = ps.executeQuery();
				
				
				List<String> googleSessionID = new ArrayList<String>();
				List<String> jetOriginalSessionID = new ArrayList<String>();
				
				List<String> jetConversionSessionID = new ArrayList<String>();
				List<String> googleUserid = new ArrayList<String>();
				List<String> callRefNo = new ArrayList<String>();
				List<String> dateOfEvent = new ArrayList<String>();
				List<String> timeOfEvent = new ArrayList<String>();
				List<String> source = new ArrayList<String>();
				List<String> medium = new ArrayList<String>();
				List<String> campaign = new ArrayList<String>();
				List<String> keywords = new ArrayList<String>();
				List<String> landingPathPage = new ArrayList<String>();
				List<String> referalUrl = new ArrayList<String>();
				List<String> tracking_code = new ArrayList<String>();
				while(rs.next()){
					
					String utma = rs.getString("cookies_utma");
					if(utma==null || utma.equals("")){
						googleSessionID.add("");
					}else{
						StringTokenizer st = new StringTokenizer(utma, ".");
						String[]ss = new String[st.countTokens()];
						int i = 0;
						while(st.hasMoreTokens()){
							ss[i] = st.nextToken();
							i++;
						}
						googleSessionID.add(ss[1]);
					}
					
					
					
					String session_type = rs.getString("session_type");
					
					if(session_type.equals("original session & conversion session")){
						jetOriginalSessionID.add(rs.getString("session_id"));
						jetConversionSessionID.add(rs.getString("session_id"));
						
					}else if(session_type.equals("original session")){
						jetOriginalSessionID.add(rs.getString("session_id"));
						jetConversionSessionID.add("");
						
					}else if(session_type.equals("conversion session")){
						jetOriginalSessionID.add("");
						jetConversionSessionID.add(rs.getString("session_id"));
						
					}else{
						jetOriginalSessionID.add("");
						jetConversionSessionID.add("");
						
					}
					
					boolean res1 = (rs.getString("user_id")==null||rs.getString("user_id").equals(""))?googleUserid.add(""):googleUserid.add(rs.getString("user_id"));
					boolean res2 = (rs.getString("call_ref_no")==null||rs.getString("call_ref_no").equals(""))?callRefNo.add(""):callRefNo.add(rs.getString("call_ref_no"));
					boolean res3 = (rs.getString("call_date_event")==null||rs.getString("call_date_event").equals(""))?dateOfEvent.add(""):dateOfEvent.add(rs.getString("call_date_event"));
					boolean res4 = (rs.getString("call_time_event")==null||rs.getString("call_time_event").equals(""))?timeOfEvent.add(""):timeOfEvent.add(rs.getString("call_time_event"));
					boolean res5 = (rs.getString("source")==null||rs.getString("source").equals(""))?source.add(""):source.add(rs.getString("source"));
					boolean res6 = (rs.getString("medium")==null||rs.getString("medium").equals(""))?medium.add(""):medium.add(rs.getString("medium"));
					boolean res7 = (rs.getString("campaign")==null||rs.getString("campaign").equals(""))?campaign.add(""):campaign.add(rs.getString("campaign"));
					boolean res8 = (rs.getString("keywords")==null||rs.getString("keywords").equals(""))?keywords.add(""):keywords.add(rs.getString("keywords"));
					boolean res9 = (rs.getString("landingpage_url")==null||rs.getString("landingpage_url").equals(""))?landingPathPage.add(""):landingPathPage.add(rs.getString("landingpage_url"));
					boolean res10 = (rs.getString("landingpage_referrer_url")==null||rs.getString("landingpage_referrer_url").equals(""))?referalUrl.add(""):referalUrl.add(rs.getString("landingpage_referrer_url"));
					boolean res11= (rs.getString("tracking_site_code")==null||rs.getString("tracking_site_code").equals(""))?tracking_code.add(""):tracking_code.add( rs.getString("tracking_site_code"));					
					
				}
				
				
				
				return new WebtrackerSessionOutput(googleSessionID, jetOriginalSessionID, jetConversionSessionID, googleUserid, callRefNo, dateOfEvent, timeOfEvent, source, medium, campaign, keywords, landingPathPage, referalUrl, tracking_code);
			}

			
			
		});
		
		return webtrackerSessionsO;
	}

	
}
