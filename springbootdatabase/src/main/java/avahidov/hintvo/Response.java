package avahidov.hintvo;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Response{

	@JsonProperty("channel")
	private Channel channel;

	public void setChannel(Channel channel){
		this.channel = channel;
	}

	public Channel getChannel(){
		return channel;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"channel = '" + channel + '\'' + 
			"}";
		}
}