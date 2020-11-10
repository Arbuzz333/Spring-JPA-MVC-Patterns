package avahidov.uservo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BusinessOp{

	@JsonProperty("code")
	private String code;

	@JsonProperty("channel")
	private List<ChannelItem> channel;

	@JsonProperty("mod_date")
	private String modDate;

	@JsonProperty("step")
	private List<StepItem> step;

	@JsonProperty("title")
	private String title;

	public String getCode(){
		return code;
	}

	public List<ChannelItem> getChannel(){
		return channel;
	}

	public String getModDate(){
		return modDate;
	}

	public List<StepItem> getStep(){
		return step;
	}

	public String getTitle(){
		return title;
	}
}