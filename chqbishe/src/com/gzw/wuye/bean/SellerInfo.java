package com.gzw.wuye.bean;

public class SellerInfo {
	
	private String name;//商家店名
	
	private String phonenum;//商家电话
	
	private String imgurl;//商家门面图片
	
	private String address;//商家地理位置
	
	public SellerInfo() {
		super();
	}

	public SellerInfo(String name, String phonenum, String imgurl,
			String address) {
		super();
		this.name = name;
		this.phonenum = phonenum;
		this.imgurl = imgurl;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
