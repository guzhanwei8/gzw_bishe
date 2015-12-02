package com.gzw.wuye;

import com.gzw.wuye.config.WuyeConfig;
import com.gzw.wuye.fragment.Jiaofei_new_fragment;
import com.gzw.wuye.fragment.Jiaofei_old_fragment;
import com.lidroid.xutils.ViewUtils;
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
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class JiaofeiActivity extends Activity implements OnClickListener {

	@ViewInject(R.id.jiaofei_back)
	Button jiaofeiback;
	@ViewInject(R.id.jiaofei_new)
	Button jiaofeinew;
	@ViewInject(R.id.jiaofei_old)
	Button jiaofeiold;
	private FragmentManager fragmentManager;
	private Jiaofei_new_fragment jiaofei_new_fragment;
	private Jiaofei_old_fragment jiaofei_old_fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.jiaofei_activity);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}

		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.statusbar_bg);

		ViewUtils.inject(this);

		fragmentManager = getFragmentManager();

		showFragment(0);

		jiaofeinew.setOnClickListener(this);

		jiaofeiold.setOnClickListener(this);

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
		// ������ȥ�����������շѱ�׼
		WuyeConfig.Dianshi_money = 10;
		WuyeConfig.Wangluo_money = 60;
		WuyeConfig.GuanLi_money = 50;
		WuyeConfig.Weisheng_money = 20;

	}

	private void showFragment(int index) {
		// �����������״̬
		clearSelection();

		// ����һ��fragment����
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		// �����ص����е�fragment����ֹ���fragmentͬʱ�����ڽ�����
		hideAllFragment(transaction);

		switch (index) {
		case 0:
			// �����button���ı�ؼ��ı���
			jiaofeinew.setTextColor(Color.parseColor("#ffffff"));
			jiaofeinew.setBackgroundResource(R.drawable.detailsleft);
			// �ж�fragment�Ƿ񴴽����Ѿ���������ʾ������û�оʹ���һ���ŵ�activity��
			if (jiaofei_new_fragment == null) {
				jiaofei_new_fragment = new Jiaofei_new_fragment();
				transaction.add(R.id.jiaofei_fraglayout, jiaofei_new_fragment);
			} else {
				transaction.show(jiaofei_new_fragment);
			}

			break;
		case 1:
			// �����button���ı�ؼ��ı���
			jiaofeiold.setTextColor(Color.parseColor("#ffffff"));
			jiaofeiold.setBackgroundResource(R.drawable.detailsright);
			// �ж�fragment�Ƿ񴴽����Ѿ���������ʾ������û�оʹ���һ���ŵ�activity��
			if (jiaofei_old_fragment == null) {
				jiaofei_old_fragment = new Jiaofei_old_fragment();
				transaction.add(R.id.jiaofei_fraglayout, jiaofei_old_fragment);
			} else {
				transaction.show(jiaofei_old_fragment);
			}

			break;

		default:
			break;
		}

		transaction.commit();
	}

	private void hideAllFragment(FragmentTransaction transaction) {

		if (jiaofei_new_fragment != null) {
			transaction.hide(jiaofei_new_fragment);
		}
		if (jiaofei_old_fragment != null) {
			transaction.hide(jiaofei_old_fragment);
		}
	}

	/**
	 * �����а�ť״̬���δѡ��
	 * 
	 * @description TODO
	 * @return void ��������
	 */
	private void clearSelection() {

		jiaofeinew.setTextColor(Color.parseColor("#999900"));
		jiaofeinew.setBackgroundResource(R.drawable.detailsleft1);

		jiaofeiold.setTextColor(Color.parseColor("#999900"));
		jiaofeiold.setBackgroundResource(R.drawable.detailsright1);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.jiaofei_new:
			showFragment(0);
			break;

		default:
			showFragment(1);
			break;
		}
	}

	@OnClick(R.id.jiaofei_back)
	private void goBack(View view) {
		finish();
	}

}
