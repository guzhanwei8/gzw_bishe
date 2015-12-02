package com.gzw.wuye;

import com.gzw.wuye.config.WuyeConfig;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.media.tv.TvTrackInfo;
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

public class ShuidianqiActivity extends Activity implements OnCheckedChangeListener {

	@ViewInject(R.id.shui_dian_cb)
	CheckBox shui_dian_cb;
	@ViewInject(R.id.shui_shui_cb)
	CheckBox shui_shui_cb;
	@ViewInject(R.id.shui_qi_cb)
	CheckBox shui_qi_cb;

	@ViewInject(R.id.shui_dian_yu)
	TextView shui_dian_yu;
	@ViewInject(R.id.shui_qi_yu)
	TextView shui_qi_yu;
	@ViewInject(R.id.shui_shui_yu)
	TextView shui_shui_yu;

	@ViewInject(R.id.shui_dian_money)
	EditText etshui_dian_money;
	@ViewInject(R.id.shui_qi_money)
	EditText etshui_qi_money;
	@ViewInject(R.id.shui_shui_money)
	EditText etshui_shui_money;

	@ViewInject(R.id.shui_total)
	TextView tvshui_total;

	private double dian;
	private double dian_r;
	private double shui;
	private double shui_r;
	private double qi;
	private double qi_r;
	private double total_money;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shuidianqi_activity);
		
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

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// 去服务器获取用户余额

		double dian_yu = 20.19;
		double shui_yu = 20.19;
		double qi_yu = 20.19;

		shui_dian_yu.setText(dian_yu + "");
		shui_shui_yu.setText(shui_yu + "");
		shui_qi_yu.setText(qi_yu + "");

	}

	private void initView() {
		etshui_dian_money.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				shui_dian_cb.setChecked(false);
			}

			@Override
			public void afterTextChanged(Editable s) {

				String str = etshui_dian_money.getText().toString().trim();
				if (TextUtils.isEmpty(str)) {
					str = "0";
				}
				dian = Integer.valueOf(str);
			}
		});

		etshui_qi_money.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				shui_qi_cb.setChecked(false);
			}

			@Override
			public void afterTextChanged(Editable s) {

				String str = etshui_qi_money.getText().toString().trim();
				if (TextUtils.isEmpty(str)) {
					str = "0";
				}
				qi = Integer.valueOf(str);
			}
		});

		etshui_shui_money.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				shui_shui_cb.setChecked(false);
			}

			@Override
			public void afterTextChanged(Editable s) {

				String str = etshui_shui_money.getText().toString().trim();
				if (TextUtils.isEmpty(str)) {
					str = "0";
				}
				shui = Integer.valueOf(str);
			}
		});

		shui_dian_cb.setOnCheckedChangeListener(this);
		shui_shui_cb.setOnCheckedChangeListener(this);
		shui_qi_cb.setOnCheckedChangeListener(this);

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.shui_dian_cb:
			if(isChecked){
				total_money = total_money+dian;
				dian_r = dian;
			}else{
				total_money = total_money-dian;
				dian_r = 0;
			}
			tvshui_total.setText(total_money+"");
			break;
		case R.id.shui_shui_cb:
			if(isChecked){
				total_money = total_money+shui;
				shui_r = shui;
			}else{
				total_money = total_money-shui;
				shui_r = 0;
			}
			tvshui_total.setText(total_money+"");
			break;
		case R.id.shui_qi_cb:
			if(isChecked){
				total_money = total_money+qi;
				qi_r = qi;
			}else{
				total_money = total_money-qi;
				qi_r = 0;
			}
			tvshui_total.setText(total_money+"");
			break;

		default:
			break;
		}
	}
	
	@OnClick(R.id.shui_go)
	private void goOrderPage(View view){
		
		if(total_money==0){
			Toast.makeText(ShuidianqiActivity.this, "请选择缴费项目", 0).show();
			return;
		}
		Intent intent = new Intent(ShuidianqiActivity.this,SelectZhifuActivity.class);
		
		String instruction = "本次充值信息自来水"+dian_r+";"+"电"+shui_r+";"+"天然气"+qi_r+";"+"共计"+total_money+";";
		intent.putExtra("instruction", instruction);
		intent.putExtra("total_money", total_money);
		
		startActivity(intent);
	}

}
