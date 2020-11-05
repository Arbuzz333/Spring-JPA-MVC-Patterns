package avahidov.hintvo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;




public class Channel{

	@JsonProperty("code")
	private String code;

	@JsonProperty("hint")
	private Hint hint;

	@JsonProperty("business_op")
	private List<BusinessOpItem> businessOp;

	@JsonProperty("mod_date")
	private String modDate;

	@JsonProperty("title")
	private String title;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setHint(Hint hint){
		this.hint = hint;
	}

	public Hint getHint(){
		return hint;
	}

	public void setBusinessOp(List<BusinessOpItem> businessOp){
		this.businessOp = businessOp;
	}

	public List<BusinessOpItem> getBusinessOp(){
		return businessOp;
	}

	public void setModDate(String modDate){
		this.modDate = modDate;
	}

	public String getModDate(){
		return modDate;
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
			"Channel{" + 
			"code = '" + code + '\'' + 
			",hint = '" + hint + '\'' + 
			",business_op = '" + businessOp + '\'' + 
			",mod_date = '" + modDate + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}