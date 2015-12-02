package com.gzw.wuye.adapter;


import java.util.List;

import com.gzw.wuye.R;
import com.gzw.wuye.bean.SellerInfo;
import com.xunfang.widgets.SmartImageView;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class FuwuAdapter extends BaseExpandableListAdapter {
	
	private List<String> group = null;
	private List<List<SellerInfo>> child = null;
	private Context context;
	private LayoutInflater inflater = null;
	
	public FuwuAdapter(List<String> group, List<List<SellerInfo>> child,
			Context context) {
		super();
		this.group = group;
		this.child = child;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getGroupCount() {
		return child.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return child.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return group.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return child.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String s = group.get(groupPosition);
		return getGenericView(s);
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_list_fuwu,parent, false);
			new ViewHolder(convertView);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		
		SellerInfo info = child.get(groupPosition).get(childPosition);
		String url = info.getImgurl();
		holder.iv_icon.setImageUrl(url,R.drawable.loading,R.drawable.loading);
		holder.tv_name.setText(info.getName());
		holder.phone.setText(info.getPhonenum());
		holder.address.setText(info.getAddress());
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
	
	public TextView getGenericView(String s) {    
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(    
                ViewGroup.LayoutParams.FILL_PARENT, 60);  
        TextView text = new TextView(context);    
        text.setLayoutParams(lp);    
        text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);    
        text.setPadding(60, 0, 0, 0);    
        text.setText(s);   
        text.setTextSize(20f);
        return text;    
    } 
	
	
	class ViewHolder {
		SmartImageView iv_icon;
		TextView tv_name;
		TextView phone;
		TextView address;
		public ViewHolder(View view) {
			iv_icon = (SmartImageView) view.findViewById(R.id.seller_icon);
			tv_name = (TextView) view.findViewById(R.id.seller_name);
			phone = (TextView) view.findViewById(R.id.seller_phone);
			address = (TextView) view.findViewById(R.id.seller_address);
			view.setTag(this);
		}
	}

}
