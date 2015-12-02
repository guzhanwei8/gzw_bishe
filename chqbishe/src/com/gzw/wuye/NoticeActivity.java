package com.gzw.wuye;

import com.gzw.wuye.fragment.Notice_new_fragment;
import com.gzw.wuye.fragment.Notice_old_framgment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class NoticeActivity extends Activity {
	
	@ViewInject(R.id.notice_new)
	Button notice_new;
	@ViewInject(R.id.notice_old)
	Button notice_old;
	@ViewInject(R.id.notice_back)
	Button notice_back;
	private FragmentManager fragmentManager;
	private Notice_new_fragment notice_newfragment;
	private Notice_old_framgment notice_oldfragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.notice_activity);
		
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}

		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.statusbar_bg);
		
		
		ViewUtils.inject(this);
		
		fragmentManager = getFragmentManager();
		
		showFragment(0);
	}
	
	@TargetApi(19) 
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}
	
	
	@OnClick({R.id.notice_back,R.id.notice_new,R.id.notice_old})
	private void click(View view){
		switch (view.getId()) {
		case R.id.notice_back:
			finish();
			break;
		case R.id.notice_new:
			showFragment(0);
			break;
		case R.id.notice_old:
			showFragment(1);
			break;
		default:
			break;
		}
	}


	private void showFragment(int index) {
		//首先清除所有状态
		clearSelection();
		
		//开启一个fragment事务
		FragmentTransaction  transaction = fragmentManager.beginTransaction();
		
		//先隐藏掉所有的fragment，防止多个fragment同时出现在界面上
		hideAllFragment(transaction);
		
		switch (index) {
		case 0:
			//点击了button，改变控件的背景
			notice_new.setTextColor(Color.parseColor("#ffffff"));
			notice_new.setBackgroundResource(R.drawable.detailsleft);
			//判断fragment是否创建，已经创建就显示出来，没有就创建一个放到activity中
			if(notice_newfragment == null){
				notice_newfragment = new Notice_new_fragment();
				transaction.add(R.id.notice_fraglayout, notice_newfragment);
			}else{
				transaction.show(notice_newfragment);
			}
			
			break;
		case 1:
			//点击了button，改变控件的背景
			notice_old.setTextColor(Color.parseColor("#ffffff"));
			notice_old.setBackgroundResource(R.drawable.detailsright);
			//判断fragment是否创建，已经创建就显示出来，没有就创建一个放到activity中
			if(notice_oldfragment == null){
				notice_oldfragment = new Notice_old_framgment();
				transaction.add(R.id.notice_fraglayout, notice_oldfragment);
			}else{
				transaction.show(notice_oldfragment);
			}
			
			break;

		default:
			break;
		}
		
		transaction.commit();
	}
	/**
	 * 隐藏掉所有的fragment
	* @description TODO
	* @return void    返回类型
	 */
	private void hideAllFragment(FragmentTransaction transaction) {
		
		if(notice_newfragment!=null){
			transaction.hide(notice_newfragment);
		}
		if(notice_oldfragment!=null){
			transaction.hide(notice_oldfragment);
		}
	}


	/**
	 * 将所有按钮状态设回未选中
	* @description TODO
	* @return void    返回类型
	 */
	private void clearSelection() {
		
		notice_new.setTextColor(Color.parseColor("#999900"));
		notice_new.setBackgroundResource(R.drawable.detailsleft1);
		
		notice_old.setTextColor(Color.parseColor("#999900"));
		notice_old.setBackgroundResource(R.drawable.detailsright1);
		
	}

}
