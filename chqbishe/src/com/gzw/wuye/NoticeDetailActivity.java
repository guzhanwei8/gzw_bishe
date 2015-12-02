package com.gzw.wuye;

import com.gzw.wuye.bean.Notices;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class NoticeDetailActivity extends Activity {
	
	private Notices notices;
	@ViewInject(R.id.noticedetail_theme)
	TextView tvtheme;
	@ViewInject(R.id.noticedetail_author)
	TextView tvauthor;
	@ViewInject(R.id.noticedetail_time)
	TextView tvtime;
	@ViewInject(R.id.noticedetail_text)
	TextView tvtext;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.noticedetail_activity);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}

		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.statusbar_bg);
		
		ViewUtils.inject(this);
		
		//int noticeId = getIntent().getIntExtra("noticeid", 0);
		
		//去数据库查询指定id消息
		
		//notices = dao.getNoticeByid();
		
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
		
		tvtheme.setText(notices.getTheme());
		tvauthor.setText(notices.getAuthor());
		tvtime.setText(notices.getTime());
		tvtext.setText(notices.getText());
	}

}
