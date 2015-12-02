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
		
		//��ȡlist�е�����
		
		initData();
		
	}
	
	private void initData() {
		banjia = new ArrayList<SellerInfo>();
		for(int i = 0;i<2;i++){
			SellerInfo banjiainfo = new SellerInfo("���ϰ��", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "���������������δ��43��");
			banjia.add(banjiainfo);
		}
		addInfo("�������",banjia);
		
		
		weixiu = new ArrayList<SellerInfo>();
		for(int i = 0;i<3;i++){
			SellerInfo banjiainfo = new SellerInfo("��ǵ���", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "���������������δ��43��");
			weixiu.add(banjiainfo);
		}
		addInfo("�ҵ�ά��",weixiu);
		
		
		water = new ArrayList<SellerInfo>();
		for(int i = 0;i<5;i++){
			SellerInfo banjiainfo = new SellerInfo("����ˮҵ", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "���������������δ��43��");
			water.add(banjiainfo);
		}
		addInfo("��Ȫˮ����",water);
		
		feipinhuishou = new ArrayList<SellerInfo>();
		for(int i = 0;i<3;i++){
			SellerInfo banjiainfo = new SellerInfo("���߷�Ʒ��", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "���������������δ��43��");
			feipinhuishou.add(banjiainfo);
		}
		addInfo("��Ʒ����",feipinhuishou);
		
		kuaidi = new ArrayList<SellerInfo>();
		for(int i = 0;i<4;i++){
			SellerInfo banjiainfo = new SellerInfo("Բͨ�ٵ�", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "���������������δ��43��");
			kuaidi.add(banjiainfo);
		}
		addInfo("�������",kuaidi);
		
		kaisuo = new ArrayList<SellerInfo>();
		for(int i = 0;i<2;i++){
			SellerInfo banjiainfo = new SellerInfo("��������", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "���������������δ��43��");
			kaisuo.add(banjiainfo);
		}
		addInfo("��������",kaisuo);
		
		guandaoshutong = new ArrayList<SellerInfo>();
		for(int i = 0;i<4;i++){
			SellerInfo banjiainfo = new SellerInfo("һ·��ͨ", "012-65567", "http://image.haosou.com/zv?ch=wallpaper&t1=163&listtype=new&src=home_wallpaper#ch=wallpaper&t1=163&listtype=new&src=home_wallpaper&lightboxindex=0&groupid=da471d87cba41199b2be6096f18cd0dd&itemindex=0&dataindex=1", "���������������δ��43��");
			guandaoshutong.add(banjiainfo);
		}
		addInfo("�ܵ���ͨ",guandaoshutong);
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
				builder.setTitle("����绰").setMessage("�Ƿ񲦴�绰"+phone).
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
