package avahidov.uservo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ChannelItem {

	@JsonProperty("code")
	private String code;

	@JsonProperty("hint")
	private List<HintItem> hint;

	@JsonProperty("mod_date")
	private String modDate;

	@JsonProperty("title")
	private String title;

	public String getCode(){
		return code;
	}

	public List<HintItem> getHint(){
		return hint;
	}

	public String getModDate(){
		return modDate;
	}

	public String getTitle(){
		return title;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setHint(List<HintItem> hint) {
		this.hint = hint;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}