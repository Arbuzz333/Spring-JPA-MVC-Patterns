package avahidov.hintvo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;



public class BusinessOpItem{

	@JsonProperty("code")
	private String code;

	@JsonProperty("mod_date")
	private String modDate;

	@JsonProperty("step")
	private List<StepItem> step;

	@JsonProperty("title")
	private String title;

	@JsonProperty("user")
	private List<UserItem> user;

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

	public void setStep(List<StepItem> step){
		this.step = step;
	}

	public List<StepItem> getStep(){
		return step;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUser(List<UserItem> user){
		this.user = user;
	}

	public List<UserItem> getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"BusinessOpItem{" + 
			"code = '" + code + '\'' + 
			",mod_date = '" + modDate + '\'' + 
			",step = '" + step + '\'' + 
			",title = '" + title + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}