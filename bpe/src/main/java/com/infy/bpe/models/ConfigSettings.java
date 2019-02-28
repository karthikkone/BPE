package com.infy.bpe.models;

import java.util.ArrayList;

public class ConfigSettings {
	private String sfdcUser;
	private String sfdcPassword;
	private String proxyUser;
	private String proxyPassword;
	private String authUrl;
	private String host;
	private int port;
	
	
	
	private String bestPractices;
	private String codeStyle;
	private String design;
	private String errorProne;
	private String performance;
	private String security;
	
	
	private ArrayList<String> metadataList;

	  
	  
	public String getBestPractices() {
		return bestPractices;
	}
	public void setBestPractices(String bestPractices) {
		this.bestPractices = bestPractices;
	}
	public String getCodeStyle() {
		return codeStyle;
	}
	public void setCodeStyle(String codeStyle) {
		this.codeStyle = codeStyle;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	public String getErrorProne() {
		return errorProne;
	}
	public void setErrorProne(String errorProne) {
		this.errorProne = errorProne;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getSfdcUser() {
		return sfdcUser;
	}
	public void setSfdcUser(String sfdcUser) {
		this.sfdcUser = sfdcUser;
	}
	public String getSfdcPassword() {
		return sfdcPassword;
	}
	public void setSfdcPassword(String sfdcPassword) {
		this.sfdcPassword = sfdcPassword;
	}
	public String getProxyUser() {
		return proxyUser;
	}
	public void setProxyUser(String proxyUser) {
		this.proxyUser = proxyUser;
	}
	public String getProxyPassword() {
		return proxyPassword;
	}
	public void setProxyPassword(String proxyPassword) {
		this.proxyPassword = proxyPassword;
	}
	public String getAuthUrl() {
		return authUrl;
	}
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}	
	public ArrayList<String> getMetadataList() {
		return metadataList;
	}
	public void setMetadataList(ArrayList<String> metadataList) {
		this.metadataList = metadataList;
	}
}
