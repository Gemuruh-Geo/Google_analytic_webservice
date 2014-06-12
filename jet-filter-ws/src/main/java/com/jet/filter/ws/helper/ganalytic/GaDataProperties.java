package com.jet.filter.ws.helper.ganalytic;
/*
 * @author: Gemuruh Geo Pratama
 * @version: 2014-04-25
 * 
 * */
public class GaDataProperties {
	private String startDate;
	private String endDate;
	private String metrics;
	private String dimension;
	private String sortBy;
	public static class Builder{
		private String startDate = "";
		private String endDate = "";
		private String metrics = "";
		private String dimension = "";
		private String sortBy = "";
		
		public Builder setStartDate(String startDate){
			this.startDate = startDate;
			return this;
		}
		public Builder setEndDate(String endDate){
			this.endDate = endDate;
			return this;
		}
		public Builder setMetrics(String metrics){
			this.metrics = metrics;
			return this;
		}
		public Builder setDimension(String dimension){
			this.dimension = dimension;
			return this;
		}
		public Builder setSortBy(String sortBy){
			this.sortBy = sortBy;
			return this;
		}
		public GaDataProperties build(){
			return new GaDataProperties(this);
		}
		
	}
	public GaDataProperties(Builder builder){
		this.dimension = builder.dimension;
		this.endDate = builder.endDate;
		this.metrics = builder.metrics;
		this.sortBy = builder.sortBy;
		this.startDate = builder.startDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public String getMetrics() {
		return metrics;
	}
	public String getDimension() {
		return dimension;
	}
	public String getSortBy() {
		return sortBy;
	}
	
	
}
