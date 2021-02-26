package avahidov.uservo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class HintItem {

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

	public void setCode(String code) {
		this.code = code;
	}

	public void setPilot(boolean pilot) {
		this.pilot = pilot;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}