package avahidov.uservo;

import com.fasterxml.jackson.annotation.JsonProperty;



public class User{

	@JsonProperty("user")
	private String user;

	@JsonProperty("business_op")
	private BusinessOp businessOp;

	@JsonProperty("mod_date")
	private String modDate;

	@JsonProperty("create_date")
	private String createDate;

	public String getUser(){
		return user;
	}

	public BusinessOp getBusinessOp(){
		return businessOp;
	}

	public String getModDate(){
		return modDate;
	}

	public String getCreateDate(){
		return createDate;
	}
}