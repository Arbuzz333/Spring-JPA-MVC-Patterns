package avahidov.hintvo;

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

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setModDate(String modDate){
		this.modDate = modDate;
	}

	public String getModDate(){
		return modDate;
	}

	public void setBusinessCode(String businessCode){
		this.businessCode = businessCode;
	}

	public String getBusinessCode(){
		return businessCode;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"StepItem{" + 
			"code = '" + code + '\'' + 
			",mod_date = '" + modDate + '\'' + 
			",business_code = '" + businessCode + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}