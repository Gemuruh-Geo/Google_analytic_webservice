package com.jet.filter.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.jet.filter.ws.model.CallTracker;

public class CallTrackerDaoImpl implements CallTrackerDao{
	
	private JdbcTemplate jdbcTemplate;
	
	private DataSource dataSource;
		
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Override
	public CallTracker getCallTracker(final String startDate, final String endDate,final int accountNumber) {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		CallTracker callTracker = jdbcTemplate.execute(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				String sql = "select "
						+ "ccs.callid as call_ref_no,"
						+ "ccs.source as source,"
						+ "ccs.medium as medium,"
						+ "ccs.campaign as campaign,"
						+ "ccs.keyword as keywords "
						+ "from call_conversions ccs join calls ccl on ccs.callid = ccl.call_ref_no and ccs.accountnumber = ccl.account_no "
						+ "join conversion_ratios ccr on ccr.call_id = ccs.callid  and ccs.accountnumber = ccr.accountnumber "
						+ "where ccs.callid!='' and TIMESTAMP(ccl.event_date,ccl.event_time) BETWEEN ? and ? and ccs.accountnumber = ?";
				
				
				
				Date startDated = null;
				Date endDated = null;
				try {
					startDated = dateFormat.parse(startDate);
					
					endDated = dateFormat.parse(endDate);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				java.sql.Timestamp startDateSq = new java.sql.Timestamp(startDated.getTime());
				java.sql.Timestamp endDateSq = new java.sql.Timestamp(endDated.getTime());
				
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setTimestamp(1, startDateSq);
				ps.setTimestamp(2, endDateSq);
				ps.setInt(3, accountNumber);
				
				return ps;
			}
		}, new PreparedStatementCallback<CallTracker>() {

			@Override
			public CallTracker doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				
				
				ResultSet rs = ps.executeQuery();
				
				List<String> call_ref_no = new ArrayList<String>();
				List<String> source = new ArrayList<String>();
				List<String> medium = new ArrayList<String>();
				List<String> campaign = new ArrayList<String>();
				List<String> keywords = new ArrayList<String>();
				while(rs.next()){
					
					boolean res1 = (rs.getString("call_ref_no")==null || rs.getString("call_ref_no")=="")?call_ref_no.add(""):call_ref_no.add(rs.getString("call_ref_no"));
					boolean res2 = (rs.getString("source")==null || rs.getString("source")=="")?source.add(""):source.add(rs.getString("source"));
					boolean res3 = (rs.getString("medium")==null || rs.getString("medium")=="")?medium.add(""):medium.add(rs.getString("medium"));
					boolean res4 = (rs.getString("campaign")==null || rs.getString("campaign")=="")?campaign.add(""):campaign.add(rs.getString("campaign"));
					boolean res5 = (rs.getString("keywords")==null || rs.getString("keywords")=="")?keywords.add(""):keywords.add(rs.getString("keywords"));
					
				}
				
				
				
				
				return new CallTracker(call_ref_no, source, medium, campaign, keywords);
			}
			
		});
		return callTracker;
	}

}
