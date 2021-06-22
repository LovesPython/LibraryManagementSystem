package lendRet;

import java.io.Serializable;

public class MemberBean implements Serializable{
	private int id;
	private String name;
	private String address;
	private String tel;
	private String email;
	private String password;
	private String birthday;
	private String joinDate;
	private String withdrawalDate;

	public MemberBean() {
	}
	public MemberBean(int id,String name,String address,String tel,String email,String birthday){
		this.id=id;
		this.name=name;
		this.address=address;
		this.tel=tel;
		this.email=email;
		this.birthday=birthday;
	}
	public MemberBean(String name,String address,String tel,String email,String birthday){
		this.name=name;
		this.address=address;
		this.tel=tel;
		this.email=email;
		this.birthday=birthday;
	}
	public MemberBean(int id,String name,String address,String tel,String email,String birthday,String joinDate,String withdrawalDate){
		this.id=id;
		this.name=name;
		this.address=address;
		this.tel=tel;
		this.email=email;
		this.birthday=birthday;
		this.joinDate=joinDate;
		this.withdrawalDate=withdrawalDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getWithdrawalDate() {
		return withdrawalDate;
	}
	public void setWithdrawalDate(String withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}

}