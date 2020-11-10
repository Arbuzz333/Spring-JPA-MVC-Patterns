package avahidov.uservo;

import com.fasterxml.jackson.annotation.JsonProperty;



public class StepItem{

	@JsonProperty("code")
	private String code;

	@JsonProperty("mod_date")
	private String modDate;

	@JsonProperty("business_code")
	private String businessCode;

	@JsonProperty("title")
	private String title;

	public String getCode(){
		return code;
	}

	public String getModDate(){
		return modDate;
	}

	public String getBusinessCode(){
		return businessCode;
	}

	public String getTitle(){
		return title;
	}
}