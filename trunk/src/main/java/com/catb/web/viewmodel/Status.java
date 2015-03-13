package com.catb.web.viewmodel;

public class Status {
	
	public static final Integer OK = 1;
	public static final Integer NOT_OK = 2;
	
	private Integer code;
	private String msg;
	
	public Status() {
		
	}

	public Status(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
