package global.scit.cosmetic.vo;
public class CosAdmin {
private String admid;
private String admpass;
public CosAdmin() {
super();
// TODO Auto-generated constructor stub
}
public CosAdmin(String admid, String admpass) {
super();
this.admid = admid;
this.admpass = admpass;
}
public String getAdmid() {
return admid;
}
public void setAdmid(String admid) {
this.admid = admid;
}
public String getAdmpass() {
return admpass;
}
public void setAdmpass(String admpass) {
this.admpass = admpass;
}
@Override
public String toString() {
return "CosAdmin [admid=" + admid + ", admpass=" + admpass + "]";
}
}