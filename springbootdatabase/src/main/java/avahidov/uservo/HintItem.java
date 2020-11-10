package avahidov.uservo;

import com.fasterxml.jackson.annotation.JsonProperty;



public class HintItem{

	@JsonProperty("code")
	private String code;

	@JsonProperty("pilot")
	private boolean pilot;

	@JsonProperty("mod_date")
	private String modDate;

	@JsonProperty("text")
	private String text;

	@JsonProperty("type")
	private String type;

	@JsonProperty("status")
	private String status;

	public String getCode(){
		return code;
	}

	public boolean isPilot(){
		return pilot;
	}

	public String getModDate(){
		return modDate;
	}

	public String getText(){
		return text;
	}

	public String getType(){
		return type;
	}

	public String getStatus(){
		return status;
	}
}