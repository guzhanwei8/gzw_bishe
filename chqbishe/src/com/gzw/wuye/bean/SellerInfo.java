package com.gzw.wuye.bean;

public class SellerInfo {
	
	private String name;//�̼ҵ���
	
	private String phonenum;//�̼ҵ绰
	
	private String imgurl;//�̼�����ͼƬ
	
	private String address;//�̼ҵ���λ��
	
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
