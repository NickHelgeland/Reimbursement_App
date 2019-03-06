package com.revature.model;

public class GradingFormat {
	private int gradingFormatId;
	private String gradingScale;
	private String passCondition;
	
	public GradingFormat() {
		super();
	}

	public GradingFormat(int gradingFormatId, String gradingScale, String passCondition) {
		super();
		this.gradingFormatId = gradingFormatId;
		this.gradingScale = gradingScale;
		this.passCondition = passCondition;
	}

	public int getGradingFormatId() {
		return gradingFormatId;
	}

	public void setGradingFormatId(int gradingFormatId) {
		this.gradingFormatId = gradingFormatId;
	}

	public String getGradingScale() {
		return gradingScale;
	}

	public void setGradingScale(String gradingScale) {
		this.gradingScale = gradingScale;
	}

	public String getPassCondition() {
		return passCondition;
	}

	public void setPassCondition(String passCondition) {
		this.passCondition = passCondition;
	}
}
