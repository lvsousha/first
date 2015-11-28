package com.zcl.model;

import com.zcl.model.User;

import java.util.Date;

import java.io.Serializable;

public class Role implements Serializable {
	private static final long serialVersionUID = 1447039680623L;

    private Integer id;
    private User user;
    private String code;
    private String name;
    private String comment;
    private Date created;
    private Date updated;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}
	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}
	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}
	public void setCreated(Date created){
		this.created = created;
	}

	public Date getCreated(){
		return created;
	}
	public void setUpdated(Date updated){
		this.updated = updated;
	}

	public Date getUpdated(){
		return updated;
	}
}