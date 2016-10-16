package com.lin.alipay.login;

public class Record {
	
	private String time;
	private String name;
	private String amount;
	private String status;
	
	public Record() {
	}

	public Record(String time, String name, String amount, String status) {
		this.time = time;
		this.name = name;
		this.amount = amount;
		this.status = status;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Record [time=" + time + ", name=" + name + ", amount=" + amount + ", status=" + status + "]";
	}

}
