package avahidov.uservo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessOp{

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

	public void setCode(String code) {
		this.code = code;
	}

	public void setChannel(List<ChannelItem> channel) {
		this.channel = channel;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public void setStep(List<StepItem> step) {
		this.step = step;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}