package com.gzw.wuye.fragment;

import com.gzw.wuye.JiaofeiActivity;
import com.gzw.wuye.R;
import com.gzw.wuye.ShuidianqiActivity;
import com.gzw.wuye.WuyeActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class Jiaofei_new_fragment extends Fragment implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.jiaofei_newfragment, container, false);

		RelativeLayout jiaofei_wuye = (RelativeLayout) view
				.findViewById(R.id.jiaofei_wuye);
		jiaofei_wuye.setOnClickListener(this);
		RelativeLayout jiaofei_shui = (RelativeLayout) view
				.findViewById(R.id.jiaofei_shui);
		jiaofei_shui.setOnClickListener(this);
		RelativeLayout jiaofei_ting = (RelativeLayout) view
				.findViewById(R.id.jiaofei_che);
		jiaofei_ting.setOnClickListener(this);
		RelativeLayout jiaofei_all = (RelativeLayout) view
				.findViewById(R.id.jiaofei_all);
		jiaofei_all.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jiaofei_wuye:
			Intent intent = new Intent(getActivity(),WuyeActivity.class);
			startActivity(intent);
			break;
		case R.id.jiaofei_shui:
			Intent intent1 = new Intent(getActivity(),ShuidianqiActivity.class);
			startActivity(intent1);
			break;
		case R.id.jiaofei_che:

			break;
		case R.id.jiaofei_all:

			break;

		default:
			break;
		}
	}

}
