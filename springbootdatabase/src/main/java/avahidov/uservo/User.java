package avahidov.uservo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;



@JsonRootName(value = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

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

	public void setUser(String user) {
		this.user = user;
	}

	public void setBusinessOp(BusinessOp businessOp) {
		this.businessOp = businessOp;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}