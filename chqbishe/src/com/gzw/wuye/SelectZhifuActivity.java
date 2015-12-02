package com.gzw.wuye;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class SelectZhifuActivity extends Activity {
	
	@ViewInject(R.id.select_moneynum)
	TextView tvmonernum;
	@ViewInject(R.id.select_instruction)
	TextView tvinstruction;
	@ViewInject(R.id.select_zhifu)
	TextView selcet_zhifu;
	private ArrayAdapter<TextView> adapter ;
	
	private int total;
	private String instruction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.selectzhifu_activity);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}

		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.statusbar_bg);
		ViewUtils.inject(this);
		
		//拿到上个页面传来的数据
		Intent intent = getIntent();
		instruction = intent.getStringExtra("instruction");
		total = intent.getIntExtra("total_money", 0);
		
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
		
		tvinstruction.setText(instruction);
		
		tvmonernum.setText(total+".0");
		
	}

	@OnClick(R.id.select_go)
	private void goZhifu(View view){
		
		
		
	}

}
