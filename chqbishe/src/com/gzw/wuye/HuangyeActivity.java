package com.gzw.wuye;

import java.util.ArrayList;
import java.util.List;

import com.gzw.wuye.R.drawable;
import com.gzw.wuye.adapter.ViewpagerAdapter;
import com.gzw.wuye.fragment.Fuwufragment;
import com.gzw.wuye.fragment.Juweihuifragment;
import com.gzw.wuye.fragment.Wuyefragment;
import com.gzw.wuye.fragment.Yulefragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HuangyeActivity extends FragmentActivity implements
		OnPageChangeListener {

	
	private ViewPager myviewpager;

	@ViewInject(R.id.huangye_juweihui_img)
	ImageView juweihui_img;
	@ViewInject(R.id.huangye_juweihui_text)
	TextView juweihui_text;

	@ViewInject(R.id.huangye_wuye_img)
	ImageView wuye_img;
	@ViewInject(R.id.huangye_wuye_text)
	TextView wuye_text;

	@ViewInject(R.id.huangye_yule_img)
	ImageView yule_img;
	@ViewInject(R.id.huangye_yule_text)
	TextView yule_text;

	@ViewInject(R.id.huangye_fuwu_img)
	ImageView fuwu_img;
	@ViewInject(R.id.huangye_fuwu_text)
	TextView fuwu_text;
	private List<Fragment> fragmentslist = new ArrayList<Fragment>();

	private Juweihuifragment juweihuifragment;
	private Wuyefragment wuyefragment;
	private Yulefragment yulefragment;
	private Fuwufragment fuwufragment;
	private ViewpagerAdapter viewpageradapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.huangye_activity);
		
		 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				setTranslucentStatus(true);
			}

			SystemBarTintManager tintManager = new SystemBarTintManager(this);
			tintManager.setStatusBarTintEnabled(true);
			tintManager.setStatusBarTintResource(R.color.statusbar_bg);

		ViewUtils.inject(this);

		initView();
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

	private void initView() {
		
		
		juweihuifragment = new Juweihuifragment();
		
		wuyefragment = new Wuyefragment();
		
		yulefragment = new Yulefragment();
		
		fuwufragment = new Fuwufragment();
		
		myviewpager = (ViewPager) findViewById(R.id.huangye_viewpager);
		
		fragmentslist.add(juweihuifragment);
		fragmentslist.add(wuyefragment);
		fragmentslist.add(yulefragment);
		fragmentslist.add(fuwufragment);

		viewpageradapter = new ViewpagerAdapter(getSupportFragmentManager(),fragmentslist);
		
		myviewpager.setAdapter(viewpageradapter);

		myviewpager.setOnPageChangeListener(this);

		myviewpager.setCurrentItem(0);
		
		juweihui_img.setBackgroundResource(drawable.juweihui);
		juweihui_text.setTextColor(Color.parseColor("#ffffff"));
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		switch (arg0) {
		case 0:
			clearall();
			juweihui_img.setBackgroundResource(drawable.juweihui);
			juweihui_text.setTextColor(Color.parseColor("#ffffff"));
			break;
		case 1:
			clearall();
			wuye_img.setBackgroundResource(drawable.wuye);
			wuye_text.setTextColor(Color.parseColor("#ffffff"));
			break;
		case 2:
			clearall();
			yule_img.setBackgroundResource(drawable.yule);
			yule_text.setTextColor(Color.parseColor("#ffffff"));
			break;
		case 3:
			clearall();
			fuwu_img.setBackgroundResource(drawable.fuwu);
			fuwu_text.setTextColor(Color.parseColor("#ffffff"));
			break;
		}
	}

	private void clearall() {
		juweihui_img.setBackgroundResource(drawable.juweihui1);
		wuye_img.setBackgroundResource(drawable.wuye1);
		yule_img.setBackgroundResource(drawable.yule1);
		fuwu_img.setBackgroundResource(drawable.fuwu1);
		
		juweihui_text.setTextColor(Color.parseColor("#003333"));
		wuye_text.setTextColor(Color.parseColor("#003333"));
		yule_text.setTextColor(Color.parseColor("#003333"));
		fuwu_text.setTextColor(Color.parseColor("#003333"));
		
	}
	
	
	@OnClick({R.id.huangye_juweihui_rl,R.id.huangye_wuye_rl,R.id.huangye_yule_rl,R.id.huangye_fuwu_rl})
	private void click(View view){
		
		switch (view.getId()) {
		case R.id.huangye_juweihui_rl:
			clearall();
			juweihui_img.setBackgroundResource(drawable.juweihui);
			juweihui_text.setTextColor(Color.parseColor("#ffffff"));
			myviewpager.setCurrentItem(0);
			break;
		case R.id.huangye_wuye_rl:
			clearall();
			wuye_img.setBackgroundResource(drawable.wuye);
			wuye_text.setTextColor(Color.parseColor("#ffffff"));
			myviewpager.setCurrentItem(1);
			break;
		case R.id.huangye_yule_rl:
			clearall();
			yule_img.setBackgroundResource(drawable.yule);
			yule_text.setTextColor(Color.parseColor("#ffffff"));
			myviewpager.setCurrentItem(2);
			break;
		case R.id.huangye_fuwu_rl:
			clearall();
			fuwu_img.setBackgroundResource(drawable.fuwu);
			fuwu_text.setTextColor(Color.parseColor("#ffffff"));
			myviewpager.setCurrentItem(3);
			break;
		default:
			break;
		}
		
	}
	
	
	@OnClick(R.id.huangye_back)
	private void back(View view){
		finish();
	}

}
