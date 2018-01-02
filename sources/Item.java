package me.huding.power.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Item {
	private String returnCode;
	private String returnMsg;
	private String projTotalIrradiance;
	private String projPower;
	private String projEquivalentOperationHours;
	
	private String projRealTimePower;
	
	private String projDataTime;
	
	
	@SerializedName("deviceList")
	private List<Device> devices;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getProjTotalIrradiance() {
		return projTotalIrradiance;
	}

	public void setProjTotalIrradiance(String projTotalIrradiance) {
		this.projTotalIrradiance = projTotalIrradiance;
	}

	public String getProjPower() {
		return projPower;
	}

	public void setProjPower(String projPower) {
		this.projPower = projPower;
	}

	public String getProjEquivalentOperationHours() {
		return projEquivalentOperationHours;
	}

	public void setProjEquivalentOperationHours(String projEquivalentOperationHours) {
		this.projEquivalentOperationHours = projEquivalentOperationHours;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public String getProjRealTimePower() {
		return projRealTimePower;
	}

	public void setProjRealTimePower(String projRealTimePower) {
		this.projRealTimePower = projRealTimePower;
	}

	public String getProjDataTime() {
		return projDataTime;
	}

	public void setProjDataTime(String projDataTime) {
		this.projDataTime = projDataTime;
	}

	@Override
	public String toString() {
		return "Item [returnCode=" + returnCode + ", returnMsg=" + returnMsg + ", projTotalIrradiance="
				+ projTotalIrradiance + ", projPower=" + projPower + ", projEquivalentOperationHours="
				+ projEquivalentOperationHours + ", projRealTimePower=" + projRealTimePower + ", projDataTime="
				+ projDataTime + ", devices=" + devices + "]";
	}
	
	
	
	
}
