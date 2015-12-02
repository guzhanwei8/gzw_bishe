package com.gzw.wuye;

import com.gzw.wuye.R;
import com.gzw.wuye.config.WuyeConfig;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class WuyeActivity extends Activity implements OnCheckedChangeListener  {
	
	
	@ViewInject(R.id.wuye_dianshi_cb)
	CheckBox dianshi_cb;
	@ViewInject(R.id.wuye_guanli_cb)
	CheckBox guanli_cb;
	@ViewInject(R.id.wuye_wangluo_cb)
	CheckBox wangluo_cb;
	@ViewInject(R.id.wuye_weisheng_cb)
	CheckBox weisheng_cb;
	@ViewInject(R.id.wuye_go)
	TextView wuye_go;
	
	@ViewInject(R.id.wuye_dianshi_yue)
	EditText wuye_dianshi_yue;
	@ViewInject(R.id.wuye_guanli_yue)
	EditText wuye_guanli_yue;
	@ViewInject(R.id.wuye_wangluo_yue)
	EditText wuye_wangluo_yue;
	@ViewInject(R.id.wuye_weisheng_yue)
	EditText wuye_weisheng_yue;
	
	@ViewInject(R.id.wuye_dianshi_money)
	TextView wuye_dianshi_money;
	@ViewInject(R.id.wuye_guanli_money)
	TextView wuye_guanli_money;
	@ViewInject(R.id.wuye_wangluo_money)
	TextView wuye_wangluo_money;
	@ViewInject(R.id.wuye_weisheng_money)
	TextView wuye_weisheng_money;
	
	@ViewInject(R.id.wuye_total_money)
	TextView wuye_total_money;
	
	private int dianshi_money;
	private int dianshi_money_r;
	private int wangluo_money;
	private int wangluo_money_r;
	private int guanli_money;
	private int guanli_money_r;
	private int weisheng_money;
	private int weisheng_money_r;
	private int total_money;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.wuye_activity);
		
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
		wuye_dianshi_yue.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				dianshi_cb.setChecked(false);
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				String str = wuye_dianshi_yue.getText().toString().trim();
				if(TextUtils.isEmpty(str)){
					str = "0";
				}
				dianshi_money = Integer.valueOf(str)*WuyeConfig.Dianshi_money;
				wuye_dianshi_money.setText(dianshi_money+".00");
			}
		});
		
		wuye_wangluo_yue.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				wangluo_cb.setChecked(false);
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				String str = wuye_wangluo_yue.getText().toString().trim();
				if(TextUtils.isEmpty(str)){
					str = "0";
				}
				wangluo_money = Integer.valueOf(str)*WuyeConfig.Wangluo_money;
				wuye_wangluo_money.setText(wangluo_money+".00");
			}
		});
		
		wuye_guanli_yue.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				guanli_cb.setChecked(false);
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				String str = wuye_guanli_yue.getText().toString().trim();
				if(TextUtils.isEmpty(str)){
					str = "0";
				}
				guanli_money = Integer.valueOf(str)*WuyeConfig.GuanLi_money;
				wuye_guanli_money.setText(guanli_money+".00");
			}
		});
		
		wuye_weisheng_yue.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				weisheng_cb.setChecked(false);
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				String str = wuye_weisheng_yue.getText().toString().trim();
				if(TextUtils.isEmpty(str)){
					str = "0";
				}
				weisheng_money= Integer.valueOf(str)*WuyeConfig.Weisheng_money;
				wuye_weisheng_money.setText(weisheng_money+".00");
			}
		});
		
		dianshi_cb.setOnCheckedChangeListener(this);
		wangluo_cb.setOnCheckedChangeListener(this);
		guanli_cb.setOnCheckedChangeListener(this);
		weisheng_cb.setOnCheckedChangeListener(this);
		
		
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.wuye_dianshi_cb:
			if(isChecked){
				total_money = total_money+dianshi_money;
				dianshi_money_r = dianshi_money;
			}else{
				total_money = total_money-dianshi_money;
				dianshi_money_r = 0;
			}
			wuye_total_money.setText(total_money+".00");
			break;
		case R.id.wuye_guanli_cb:
			if(isChecked){
				total_money = total_money+guanli_money;
				guanli_money_r = guanli_money;
			}else{
				total_money = total_money-guanli_money;
				guanli_money_r = 0;
			}
			wuye_total_money.setText(total_money+".00");
			break;
		case R.id.wuye_wangluo_cb:
			if(isChecked){
				total_money = total_money+wangluo_money;
				wangluo_money_r = wangluo_money;
			}else{
				total_money = total_money-wangluo_money;
				wangluo_money_r = 0;
			}
			wuye_total_money.setText(total_money+".00");
			break;
		case R.id.wuye_weisheng_cb:
			if(isChecked){
				total_money = total_money+weisheng_money;
				weisheng_money_r = weisheng_money;
			}else{
				total_money = total_money-weisheng_money;
				weisheng_money_r = 0;
			}
			wuye_total_money.setText(total_money+".00");
			break;

		default:
			break;
		}
	}
	
	@OnClick(R.id.wuye_go)
	private void goOrderPage(View view){
		
		if(total_money==0){
			Toast.makeText(WuyeActivity.this, "请选择缴费项目", 0).show();
			return;
		}
		Intent intent = new Intent(WuyeActivity.this,SelectZhifuActivity.class);
		
		String instruction = "本次缴费包括有限电视费用"+dianshi_money_r+".0;"+"宽带费用"+wangluo_money_r+".0;"+"物业管理费用"+guanli_money_r+".0;"+"卫生"+weisheng_money_r+".0;"+"共计"+total_money+".0;";
		intent.putExtra("instruction", instruction);
		intent.putExtra("total_money", total_money);
		
		startActivity(intent);
		
	}


	
}
