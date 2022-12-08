package global.scit.cosmetic.vo;

public class CosMember {
	private String usrid;
	private String password;
	private String name;
	private int skinproblem;
	private int usrrole;

	public CosMember(String usrid, String password, String name,int skinproblem, int usrrole) {
		super();
		this.usrid = usrid;
		this.password = password;
		this.name = name;
		this.skinproblem = skinproblem;
		this.usrrole = usrrole;
	}

	public CosMember() {
		super();

	}

	public boolean getUsrid() {
		return usrid;
	}

	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return " \n 회원의 usrid = " + usrid + ", password = " + password + ", name = " + name + ", 관심사 = " + skinproblem
				+ "usrrole = "+usrrole ;
	}
}