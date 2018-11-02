package com.jt.easymall.pojo;
/**
 * user_id        char(36)     utf8_general_ci  NO      PRI     (NULL)           select,insert,update,references  �û�id uuid ����                                        
user_name      varchar(20)  utf8_general_ci  NO      UNI     (NULL)           select,insert,update,references  �û���½����                                          
user_password  varchar(32)  utf8_general_ci  NO              (NULL)           select,insert,update,references  �û����� md5                                            
user_nickname  varchar(50)  utf8_general_ci  YES             �ϵ�           select,insert,update,references  �û��ǳ�                                                
user_email     varchar(30)  utf8_general_ci  YES             (NULL)           select,insert,update,references  �û�����                                                
user_type      int(11)      (NULL)           YES             0                select,insert,update,references  �û�״̬ 0(��ͨ�û�),1(����Ա),2(��������Ա)
 * @author tarena
 *
 */
public class User {
	private String userId;
	private String userName;
	private String userPassword;
	private String userNickname;
	private String userEmail;
	private Integer userType;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", userNickname=" + userNickname + ", userEmail=" + userEmail + ", userType=" + userType + "]";
	}
	
	
}
