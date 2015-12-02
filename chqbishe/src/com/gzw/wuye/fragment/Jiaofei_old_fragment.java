package com.gzw.wuye.fragment;

import java.util.ArrayList;
import java.util.List;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.gzw.wuye.CountDetailActivity;
import com.gzw.wuye.R;
import com.gzw.wuye.R.drawable;
import com.gzw.wuye.bean.CountInfo;
import com.gzw.wuye.bean.Notices;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Jiaofei_old_fragment extends Fragment {

	private SwipeMenuListView listview;
	private List<CountInfo> data = new ArrayList<CountInfo>();
	
	private CountAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		for(int i = 0;i<2;i++){
			CountInfo info = new CountInfo();
			info.setZhiFuDetail("zhifubao");
			info.setZhiFuDetail("支付宝转账2075元");
			info.setZhiFuTime("2015-11-24");
			info.setZhiFuXiangMu("物业费");
			data.add(info);
		}
		CountInfo info = new CountInfo("weixing", "水电费", "2015-10-24", "微信转账200元");
		data.add(info);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.jiaofei_count, container, false);

		listview = (SwipeMenuListView) view.findViewById(R.id.count_listview);
		
		adapter = new CountAdapter();

		listview.setAdapter(adapter);

		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				// create "open" item
				SwipeMenuItem openItem = new SwipeMenuItem(getActivity());
				// set item background
				openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
						0xCE)));
				// set item width
				openItem.setWidth(dp2px(90));
				// set item title
				openItem.setTitle("Open");
				// set item title fontsize
				openItem.setTitleSize(18);
				// set item title font color
				openItem.setTitleColor(Color.WHITE);
				// add to menu
				menu.addMenuItem(openItem);

				// create "delete" item
				SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
						0x3F, 0x25)));
				// set item width
				deleteItem.setWidth(dp2px(90));
				// set a icon
				deleteItem.setIcon(R.drawable.ic_delete);
				// add to menu
				menu.addMenuItem(deleteItem);

			}
		};

		listview.setMenuCreator(creator);

		listview.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu,
					int index) {

				CountInfo info = data.get(position);

				switch (index) {
				case 0:
					open(info);
					break;

				case 1:
					delete(info, position);
					break;
				}

				return false;
			}

		});
		return view;
	}

	private void delete(CountInfo info, int position) {
		int id = info.getId();
		// 去服务器请求消息或删除

		// 返回删除信息，200，将该条信息从adapter中移去

		data.remove(position);

	}

	private void open(CountInfo info) {

		int id = info.getId();

		Intent intent = new Intent(getActivity(), CountDetailActivity.class);

		intent.putExtra("id", id);

		startActivity(intent);

	}

	class CountAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public CountInfo getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if (convertView == null) {
				convertView = View.inflate(getActivity(),
						R.layout.item_list_count, null);
				new ViewHolder(convertView);
			}
			ViewHolder holder = (ViewHolder) convertView.getTag();
			CountInfo info = data.get(position);
			System.out.println(info.getZhiFuFangShi()+info.getZhiFuDetail()+data.size()+position);
			holder.iv_icon.setBackgroundResource(info.getZhiFuFangShi().equals(
					"zhifubao") ? drawable.zhi : drawable.weixing);
			holder.tv_name.setText(info.getZhiFuXiangMu());
			holder.time.setText(info.getZhiFuTime());
			holder.number.setText(info.getZhiFuDetail());
			
			return convertView;
		}
		class ViewHolder {
			ImageView iv_icon;
			TextView tv_name;
			TextView number;
			TextView time;
			public ViewHolder(View view) {
				iv_icon = (ImageView) view.findViewById(R.id.cout_icon);
				tv_name = (TextView) view.findViewById(R.id.cout_type);
				number = (TextView) view.findViewById(R.id.cout_num);
				time = (TextView) view.findViewById(R.id.cout_time);
				view.setTag(this);
			}
		}
		
	}
	


	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}
}
