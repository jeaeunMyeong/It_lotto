package lotto.dto;

public class MemberDTO {
	private String id;
	private String pwd;
	private int admin;
	
	public MemberDTO(String id,String pwd,int admin) {
		super();
		this.id=id;
		this.pwd=pwd;
		this.admin=admin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	
	
	
}
