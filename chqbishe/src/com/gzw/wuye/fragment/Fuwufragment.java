package com.gzw.wuye.fragment;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.gzw.wuye.R;
import com.gzw.wuye.adapter.FuwuAdapter;
import com.gzw.wuye.bean.SellerInfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.AlteredCharSequence;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;


public class Fuwufragment extends Fragment{
	
	private ExpandableListView myexpandlist;
	private FuwuAdapter adapter;
	private List<String> group = new ArrayList<String>();
	private List<List<SellerInfo>> child = new ArrayList<List<SellerInfo>>();
	private List<SellerInfo> banjia;
	private List<SellerInfo> weixiu;
	private List<SellerInfo> water;
	private List<SellerInfo> feipinhuishou;
	private List<SellerInfo> kaisuo;
	private List<SellerInfo> kuaidi;
	private List<SellerInfo> guandaoshutong;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//获取list中的数据
		
		initData();
		
	}
	
	private void initData() {
		banjia = new ArrayList<SellerInfo>();
		for(int i = 0;i<2;i++){
			SellerInfo banjiainfo = new SellerInfo("蚂蚁搬家", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "宝安龙华新区民治大道43号");
			banjia.add(banjiainfo);
		}
		addInfo("物流搬家",banjia);
		
		
		weixiu = new ArrayList<SellerInfo>();
		for(int i = 0;i<3;i++){
			SellerInfo banjiainfo = new SellerInfo("李记电器", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "宝安龙华新区民治大道43号");
			weixiu.add(banjiainfo);
		}
		addInfo("家电维修",weixiu);
		
		
		water = new ArrayList<SellerInfo>();
		for(int i = 0;i<5;i++){
			SellerInfo banjiainfo = new SellerInfo("怡宝水业", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "宝安龙华新区民治大道43号");
			water.add(banjiainfo);
		}
		addInfo("矿泉水配送",water);
		
		feipinhuishou = new ArrayList<SellerInfo>();
		for(int i = 0;i<3;i++){
			SellerInfo banjiainfo = new SellerInfo("东边废品厂", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "宝安龙华新区民治大道43号");
			feipinhuishou.add(banjiainfo);
		}
		addInfo("废品回收",feipinhuishou);
		
		kuaidi = new ArrayList<SellerInfo>();
		for(int i = 0;i<4;i++){
			SellerInfo banjiainfo = new SellerInfo("圆通速递", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "宝安龙华新区民治大道43号");
			kuaidi.add(banjiainfo);
		}
		addInfo("快递上门",kuaidi);
		
		kaisuo = new ArrayList<SellerInfo>();
		for(int i = 0;i<2;i++){
			SellerInfo banjiainfo = new SellerInfo("放心锁记", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "宝安龙华新区民治大道43号");
			kaisuo.add(banjiainfo);
		}
		addInfo("开锁换锁",kaisuo);
		
		guandaoshutong = new ArrayList<SellerInfo>();
		for(int i = 0;i<4;i++){
			SellerInfo banjiainfo = new SellerInfo("一路畅通", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "宝安龙华新区民治大道43号");
			guandaoshutong.add(banjiainfo);
		}
		addInfo("管道疏通",guandaoshutong);
	}

	private void addInfo(String childname, List<SellerInfo> childitem) {
		
		group.add(childname);
          
        child.add(childitem);  
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fuwu_fragment, container,false);
		
		myexpandlist = (ExpandableListView) view.findViewById(R.id.fuwu_expandlistview);
		
		adapter = new FuwuAdapter(group,child,getActivity());
		
		myexpandlist.setAdapter(adapter);
		
		myexpandlist.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				SellerInfo info = child.get(groupPosition).get(childPosition);
				final String phone = info.getPhonenum();
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("拨打电话").setMessage("是否拨打电话"+phone).
				setPositiveButton("yes", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:"+phone));
						startActivity(intent);
					}
				}).setNegativeButton("no", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
				}).show();
				
				
				return false;
			}
		});
		
		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeView(view);
		}
		
		return view;
	}

}
