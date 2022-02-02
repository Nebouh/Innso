package com.innso.exercice.dto;

public enum Canal {
	MAIL("MAIL"), SMS("SMS"), FACEBOOK("FACEBOOK"), TWITTER("TWITTER");
	
	private String canal;

	Canal(String canal) {
		this.canal = canal;
	}

	public String getCanal() {
		return canal;
	}
}
