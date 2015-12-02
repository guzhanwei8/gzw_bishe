package com.gzw.wuye.fragment;

import java.util.ArrayList;
import java.util.List;

import com.gzw.wuye.NoticeDetailActivity;
import com.gzw.wuye.R;
import com.gzw.wuye.adapter.Noticeadapter;
import com.gzw.wuye.bean.Notices;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Notice_old_framgment extends Fragment implements OnItemClickListener{
	
	private ListView listview;
	private Noticeadapter adapter;
	private List<Notices> data = new ArrayList<Notices>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//从数据库中拿到未读消息
		for(int i = 0;i<5;i++){
			Notices notice = new Notices();
			notice.setAuthor("治安中心");
			notice.setText("最近一段时间发现有盗贼出没，请业主多加防范，在出门或者休息时关好门窗，发现情况立即报警，报警电话012-123456");
			notice.setTime("12月1日 12：10");
			notice.setTheme("关于加强治安防范通知");
			data.add(notice);
		}
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View notice_oldfragment = inflater.inflate(R.layout.notice_fragment,container, false);
		
		listview = (ListView) notice_oldfragment.findViewById(R.id.notice_fragment_list);
		
		adapter = new Noticeadapter(data, getActivity());
		
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(this);
		
		return notice_oldfragment;
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		int noticeid = data.get(position).getId();
		//根据id去数据库将数据状态改为已读
		/**
		 * 未完待续
		 */
		Intent intent = new Intent(getActivity(),NoticeDetailActivity.class);
		intent.putExtra("noticeid", noticeid);
		startActivity(intent);
	}

}
