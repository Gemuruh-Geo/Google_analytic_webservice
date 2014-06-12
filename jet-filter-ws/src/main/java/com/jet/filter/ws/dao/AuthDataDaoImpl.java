package com.jet.filter.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.jet.filter.ws.model.TrackingSite;
import com.mysql.fabric.xmlrpc.base.Array;


/*
 * @author:Gemuruh Geo Pratama
 * */

public class AuthDataDaoImpl implements AuthDataDao{
	
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}



	@Override
	public TrackingSite getTrackingSiteByTrackingCode(String trackingCode) {
		// TODO Auto-generated method stub
		

		String sql= "select * from tracking_site where tracking_code=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<TrackingSite> trcks = new ArrayList<TrackingSite>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql,trackingCode);
		try{
			for(Map<String, Object> row:rows){
				TrackingSite trck = new TrackingSite();
				trck.setAttributeJson((String)row.get("attributes_json"));
				trck.setConfigJson((String)row.get("config_json"));
				trck.setTrackingCode((String) row.get("tracking_code"));
				
				trcks.add(trck);
			}
			return trcks.get(0);
		}catch(Exception e){
			System.out.println("Catch 1");
			return null;
		}
		
	}

	@Override
	public int updateConfigJson(String newConfigJson, String trackingCode) {
		
		String sql = "update tracking_site set config_json = ? where tracking_code = ?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.update(sql, newConfigJson,trackingCode);
		
	}



	@Override
	public List<String> getTrackingCode() {
		// TODO Auto-generated method stub
		
		final String sql = "select tracking_code from tracking_site";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<String> tracking_code = jdbcTemplate.execute(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				
				PreparedStatement ps = con.prepareStatement(sql);
				
				return ps;
			}
		}, new PreparedStatementCallback<List<String>>() {

			@Override
			public List<String> doInPreparedStatement(PreparedStatement ps)
					throws SQLException, DataAccessException {
				
				List<String> arrayList = new ArrayList<String>();
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					arrayList.add(rs.getString("tracking_code"));
				}
				
				return arrayList;
			}
			
		});
			
		return tracking_code;
	}



	@Override
	public int updateAttributJSON(String attJson,String tracking_code) {
		// TODO Auto-generated method stub
		String sql = "update tracking_site set attributes_json=? where tracking_code=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		return jdbcTemplate.update(sql, attJson,tracking_code);
		
	}

}
