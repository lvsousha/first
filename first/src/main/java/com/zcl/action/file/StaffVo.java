package com.zcl.action.file;

public class StaffVo {

	private String staffname ;

	  private String staffgender ;

	  private String staffprofession ;

	  private String staffdepartment ; // 部门

	  //如果改为double类型，这个上传的值不能为空值，所有改为string类型
	  private String staffsalary ; //月薪

	  private String staffzone ; //区号
	  private String stafftelnumber ; //号码

	  private String staffmobile ; //手机号码

	  private String staffemail ; //邮箱

	  private String staffprovince ;
	  private String staffcity ;
	  private String staffcounty ;
	  private String staffpartaddress ;

	  /**
	   * 这个file类型在这里是有问题的，不象struts可进行封装，如果有上传文件，
	   * 就不能整个对象传过去了，所有要进行删除
	   */
	  //private File avatar ; //头像

	  public String getStaffname() {
	    return staffname;
	  }

	  public void setStaffname(String staffname) {
	    this.staffname = staffname;
	  }

	  public String getStaffgender() {
	    return staffgender;
	  }

	  public void setStaffgender(String staffgender) {
	    this.staffgender = staffgender;
	  }

	  public String getStaffprofession() {
	    return staffprofession;
	  }

	  public void setStaffprofession(String staffprofession) {
	    this.staffprofession = staffprofession;
	  }

	  public String getStaffdepartment() {
	    return staffdepartment;
	  }

	  public void setStaffdepartment(String staffdepartment) {
	    this.staffdepartment = staffdepartment;
	  }

	  public String getStaffsalary() {
	    return staffsalary;
	  }

	  public void setStaffsalary(String staffsalary) {
	    this.staffsalary = staffsalary;
	  }

	  public String getStaffzone() {
	    return staffzone;
	  }

	  public void setStaffzone(String staffzone) {
	    this.staffzone = staffzone;
	  }

	  public String getStafftelnumber() {
	    return stafftelnumber;
	  }

	  public void setStafftelnumber(String stafftelnumber) {
	    this.stafftelnumber = stafftelnumber;
	  }

	  public String getStaffmobile() {
	    return staffmobile;
	  }

	  public void setStaffmobile(String staffmobile) {
	    this.staffmobile = staffmobile;
	  }

	  public String getStaffemail() {
	    return staffemail;
	  }

	  public void setStaffemail(String staffemail) {
	    this.staffemail = staffemail;
	  }

	  public String getStaffprovince() {
	    return staffprovince;
	  }

	  public void setStaffprovince(String staffprovince) {
	    this.staffprovince = staffprovince;
	  }

	  public String getStaffcity() {
	    return staffcity;
	  }

	  public void setStaffcity(String staffcity) {
	    this.staffcity = staffcity;
	  }

	  public String getStaffcounty() {
	    return staffcounty;
	  }

	  public void setStaffcounty(String staffcounty) {
	    this.staffcounty = staffcounty;
	  }

	  public String getStaffpartaddress() {
	    return staffpartaddress;
	  }

	  public void setStaffpartaddress(String staffpartaddress) {
	    this.staffpartaddress = staffpartaddress;
	  }
	
}
