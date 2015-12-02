package com.gzw.wuye.adapter;

import java.util.List;

import com.gzw.wuye.R;
import com.gzw.wuye.bean.Notices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Noticeadapter extends BaseAdapter{
	
	private List<Notices> data = null;
	private Context context;
	private LayoutInflater inflater = null;
	
	
	public Noticeadapter(List<Notices> data, Context context
			) {
		this.data = data;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.notice_fragmentitem, parent, false);
			holder.theme = (TextView) convertView.findViewById(R.id.notice_theme);
			holder.text = (TextView) convertView.findViewById(R.id.notice_text);
			holder.time = (TextView) convertView.findViewById(R.id.notice_time);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Notices notices = data.get(position);
		holder.theme.setText(notices.getTheme().length()>8?notices.getTheme().substring(0, 8)+"...":notices.getTheme());
		holder.text.setText(notices.getText());
		holder.time.setText(notices.getTime());
		return convertView;
	}
	
	class  ViewHolder{
		
		private TextView theme;
		private TextView text;
		private TextView time;
		
	}

}
