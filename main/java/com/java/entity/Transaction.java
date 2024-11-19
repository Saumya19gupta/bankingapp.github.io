package com.java.entity;

import jakarta.persistence.*;

@Entity
public class Transaction
{
	@Id
	@GeneratedValue(generator = "tran_seq")
	@SequenceGenerator(name = "tran_seq",initialValue = 111111111,allocationSize = 1)
	private int trid;
	private long fromac;
	private int amount;
	private String date;
	private String time;
	private String type;
	private long toac;
	public int getTrid() {
		return trid;
	}
	public void setTrid(int trid) {
		this.trid = trid;
	}
	
	public long getFromac() {
		return fromac;
	}
	public void setFromac(long fromac) {
		this.fromac = fromac;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getToac() {
		return toac;
	}
	public void setToac(long toac) {
		this.toac = toac;
	}
		
}

