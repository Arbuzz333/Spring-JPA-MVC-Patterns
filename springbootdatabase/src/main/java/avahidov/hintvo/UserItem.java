package avahidov.hintvo;

import com.fasterxml.jackson.annotation.JsonProperty;



public class UserItem{

	@JsonProperty("mod_date")
	private String modDate;

	@JsonProperty("business_code")
	private String businessCode;

	@JsonProperty("create_date")
	private String createDate;

	@JsonProperty("user")
	private String user;

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

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setUser(String user){
		this.user = user;
	}

	public String getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"UserItem{" + 
			"mod_date = '" + modDate + '\'' + 
			",business_code = '" + businessCode + '\'' + 
			",create_date = '" + createDate + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}