package global.scit.cosmetic.vo;

public class CosMember {
	private String usrid;
	private String usrpass;
	private String usrname;

	private String email;
	private int skinproblem;
	private int usrrole;

	public CosMember(String usrid, String usrpass, String usrname, String email,int skinproblem, int usrrole) {
		super();
		this.usrid = usrid;
		this.usrpass = usrpass;
		this.usrname = usrname;
		this.email=email;
		this.skinproblem = skinproblem;
		this.usrrole = usrrole;
	}

	public CosMember() {
		super();

	}
	public String getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
	public String getUsrpass() {
		return usrpass;
	}
	public void setUsrpass(String usrpass) {
		this.usrpass = usrpass;
	}
	public String getUsrname() {
		return usrname;
	}
	public void setUsrname(String usrname) {
		this.usrname = usrname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSkinproblem() {
		return skinproblem;
	}
	public void setSkinproblem(int skinproblem) {
		this.skinproblem = skinproblem;
	}
	//usrrole
	public int getUsrrole() {
		return usrrole;
	}
	public void setUsrrole(int usrrole) {
		this.usrrole = usrrole;
	}
	@Override
	public String toString() {
		String result =" 회원의 usrid = " + usrid +", usrname = " + usrname + "email = "+email+
				 ", usrrole = "+usrrole;
		result +=  ", 관심사 = " + skinproblem;
		if(skinproblem==1){
			result += "미백";
		}else if(skinproblem==2){
			result += "노화";
		}else if(skinproblem==3){
			result += "여드름";
		}
		return result;
	}
}