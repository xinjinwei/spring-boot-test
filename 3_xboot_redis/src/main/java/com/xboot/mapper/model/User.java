package com.xboot.mapper.model;

import java.io.Serializable;

import com.xboot.enums.UserSexEnum;

import lombok.Data;
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String passWord;
	private UserSexEnum userSex;
	private String nickName;
	private String email;
	private String regTime;
	public User() {
		super();
	}
	public User(String email, String nickname, String password, String userName, String regTime) {
		super();
		this.email = email;
		this.nickName = nickname;
		this.passWord = password;
		this.userName = userName;
		this.regTime = regTime;
	}
	public User(String userName, String passWord, UserSexEnum userSex) {
		super();
		this.passWord = passWord;
		this.userName = userName;
		this.userSex = userSex;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "userName " + this.userName + ", pasword " + this.passWord + "sex " + userSex.MAN;
	}

}