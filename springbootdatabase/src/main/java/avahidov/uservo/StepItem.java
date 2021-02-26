package avahidov.uservo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class StepItem {

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

	public void setCode(String code) {
		this.code = code;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}