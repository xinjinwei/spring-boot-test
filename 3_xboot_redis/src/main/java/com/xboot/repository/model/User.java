package com.xboot.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String passWord;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private Date regTime;
	@Column(nullable = false)
	private String nickname;
	
    public User() {

    }
    

    public User(String userName, String passWord, Date regTime) {
        this.userName = userName;
        this.passWord = passWord;
        this.regTime = regTime;
    }
    
  
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", age=" + age +
                ", regTime=" + regTime +
                '}';
    }
}
