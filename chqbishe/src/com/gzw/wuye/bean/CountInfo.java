package com.gzw.wuye.bean;

public class CountInfo {
	
	private  String ZhiFuFangShi = "zhifubao";
	
	private  String ZhiFuXiangMu = "";
	
	private  String ZhiFuTime = "";
	
	private  String ZhiFuDetail = "";
	
	private  double Zhifutotal ;
	
	private  int id;

	

	public CountInfo() {
		super();
	}



	public CountInfo(String zhiFuFangShi, String zhiFuXiangMu,
			String zhiFuTime, String zhiFuDetail) {
		super();
		ZhiFuFangShi = zhiFuFangShi;
		ZhiFuXiangMu = zhiFuXiangMu;
		ZhiFuTime = zhiFuTime;
		ZhiFuDetail = zhiFuDetail;
	}



	public String getZhiFuFangShi() {
		return ZhiFuFangShi;
	}



	public void setZhiFuFangShi(String zhiFuFangShi) {
		ZhiFuFangShi = zhiFuFangShi;
	}



	public String getZhiFuXiangMu() {
		return ZhiFuXiangMu;
	}



	public void setZhiFuXiangMu(String zhiFuXiangMu) {
		ZhiFuXiangMu = zhiFuXiangMu;
	}



	public String getZhiFuTime() {
		return ZhiFuTime;
	}



	public void setZhiFuTime(String zhiFuTime) {
		ZhiFuTime = zhiFuTime;
	}



	public String getZhiFuDetail() {
		return ZhiFuDetail;
	}



	public void setZhiFuDetail(String zhiFuDetail) {
		ZhiFuDetail = zhiFuDetail;
	}



	public double getZhifutotal() {
		return Zhifutotal;
	}



	public void setZhifutotal(double zhifutotal) {
		Zhifutotal = zhifutotal;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
}
