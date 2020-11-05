package avahidov.hintvo;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Hint{

	@JsonProperty("code")
	private String code;

	@JsonProperty("pilot")
	private Boolean pilot;

	@JsonProperty("mod_date")
	private String modDate;

	@JsonProperty("text")
	private String text;

	@JsonProperty("type")
	private String type;

	@JsonProperty("status")
	private String status;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setPilot(Boolean pilot){
		this.pilot = pilot;
	}

	public boolean isPilot(){
		return pilot;
	}

	public void setModDate(String modDate){
		this.modDate = modDate;
	}

	public String getModDate(){
		return modDate;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Hint{" + 
			"code = '" + code + '\'' + 
			",pilot = '" + pilot + '\'' + 
			",mod_date = '" + modDate + '\'' + 
			",text = '" + text + '\'' + 
			",type = '" + type + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}