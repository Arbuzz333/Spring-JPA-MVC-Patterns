package avahidov.uservo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChannelItem{

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
}