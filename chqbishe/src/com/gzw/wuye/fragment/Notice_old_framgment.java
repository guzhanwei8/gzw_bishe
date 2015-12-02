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
		//�����ݿ����õ�δ����Ϣ
		for(int i = 0;i<5;i++){
			Notices notice = new Notices();
			notice.setAuthor("�ΰ�����");
			notice.setText("���һ��ʱ�䷢���е�����û����ҵ����ӷ������ڳ��Ż�����Ϣʱ�غ��Ŵ�������������������������绰012-123456");
			notice.setTime("12��1�� 12��10");
			notice.setTheme("���ڼ�ǿ�ΰ�����֪ͨ");
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
		//����idȥ���ݿ⽫����״̬��Ϊ�Ѷ�
		/**
		 * δ�����
		 */
		Intent intent = new Intent(getActivity(),NoticeDetailActivity.class);
		intent.putExtra("noticeid", noticeid);
		startActivity(intent);
	}

}
