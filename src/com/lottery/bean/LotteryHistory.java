package com.lottery.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 彩票历史开奖记录
 * 
 * @author wangzhen
 *
 */
public class LotteryHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String number;
	private String date;
	private List<String> redBalls;
	private List<String> blueBalls;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getRedBalls() {
		return redBalls;
	}

	public void setRedBalls(List<String> redBalls) {
		this.redBalls = redBalls;
	}

	public List<String> getBlueBalls() {
		return blueBalls;
	}

	public void setBlueBalls(List<String> blueBalls) {
		this.blueBalls = blueBalls;
	}

}
