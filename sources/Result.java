package me.huding.power.bean;

public class Result<T> {
	public static final int OK = 0;
	
	public static final int ER = 1;
	
	private int code;
	
	private String msg;
	
	private T data;
	
	private String raw;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + data + ", raw=" + raw + "]";
	}
	
	
}
