package global.scit.cosmetic.vo;
public class MemberSkinInfo {
private String usrid;
private String skinproblem;
public MemberSkinInfo() {
super();
// TODO Auto-generated constructor stub
}
public MemberSkinInfo(String usrid, String skinproblem) {
super();
this.usrid = usrid;
this.skinproblem = skinproblem;
}
public String getUsrid() {
return usrid;
}
public void setUsrid(String usrid) {
this.usrid = usrid;
}
public String getSkinproblem() {
return skinproblem;
}
public void setSkinproblem(String skinproblem) {
this.skinproblem = skinproblem;
}
@Override
public String toString() {
return "usrid : " + usrid + ", skinproblem : " + skinproblem;
}
}