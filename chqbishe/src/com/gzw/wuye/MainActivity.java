package com.gzw.wuye;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import com.amap.api.location.AMapLocalWeatherForecast;
import com.amap.api.location.AMapLocalWeatherListener;
import com.amap.api.location.AMapLocalWeatherLive;
import com.amap.api.location.LocationManagerProxy;
import com.gzw.wuye.ui.CircularImage;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class MainActivity extends SlidingFragmentActivity implements AMapLocalWeatherListener{
	private SlidingMenu leftmenu;
	private int width;
	@ViewInject(R.id.home_geren)
	CircularImage user_photo;
	private CircularImage leftmenu_userphoto;
	private int hour;
	private int minute;
	@ViewInject(R.id.home_wether)
	TextView wether;
	@ViewInject(R.id.home_time1)
	TextView time1;
	private MyTimerTask mytask;
	private Timer timer = new Timer();
	private LocationManagerProxy mLocationManagerProxy;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if(msg.what == 1){
				initTime();
			}
			System.out.println("00000000000000");
			super.handleMessage(msg);
			
		};
	};
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}

		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.statusbar_bg);
        
        ViewUtils.inject(this);
        
        initLeftMenu();
        
        user_photo.setImageResource(R.drawable.face);
        
        initTime();
        
        if(timer !=null){
        	if(mytask != null){
        		mytask.cancel();}
        	mytask = new MyTimerTask();
			timer.schedule(mytask, 3000, 1000*10); // 60s后执行task,经过60s再次执行
        }
        
        mLocationManagerProxy = LocationManagerProxy.getInstance(this);
		//获取实时天气预报
		//如果需要同时请求实时、未来三天天气，请确保定位获取位置后使用,分开调用，可忽略本句。
		mLocationManagerProxy.requestWeatherUpdates(
				LocationManagerProxy.WEATHER_TYPE_LIVE, this);
		
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
    
    /**
     * 获取系统时间并显示
    * @description TODO
    * @return void    返回类型
     */
	private void initTime() {
		Calendar c = Calendar.getInstance();  
		hour = c.get(Calendar.HOUR_OF_DAY);  
		minute = c.get(Calendar.MINUTE)  ;
		String strhour = hour>9?hour+"":"0"+hour;
		String strminute = minute>9?minute+"":"0"+minute;
		String strtime = strhour+":"+strminute;
		time1.setText(strtime);
	}
	/**
	 * 初始化左侧菜单
	* @description TODO
	* @return void    返回类型
	 */
	private void initLeftMenu() {
		leftmenu = getSlidingMenu();
		setBehindContentView(R.layout.leftmenu);
		leftmenu.setMode(SlidingMenu.LEFT);
		leftmenu.setFadeDegree(0.35f);
		//leftmenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		leftmenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		leftmenu.setTouchmodeMarginThreshold(60);
		DisplayMetrics display = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(display);
		width = display.widthPixels;
		leftmenu.setBehindWidth((int) (0.7 * width));
		leftmenu_userphoto = (CircularImage)findViewById(R.id.leftmenu_userphoto);
		leftmenu_userphoto.setImageResource(R.drawable.face);
		
	}
	@OnClick({R.id.home_notice,R.id.home_jiaofei,R.id.home_huangye,R.id.home_baoxiu,R.id.home_hudong,R.id.home_shenfen})
	private void click(View view){
		switch (view.getId()) {
		case R.id.home_notice:
			Intent intent  = new Intent(MainActivity.this,NoticeActivity.class);
			startActivity(intent);
			break;
		case R.id.home_jiaofei:
			Intent intent1  = new Intent(MainActivity.this,JiaofeiActivity.class);
			startActivity(intent1);
			break;
		case R.id.home_huangye:
			Intent intent2  = new Intent(MainActivity.this,HuangyeActivity.class);
			startActivity(intent2);
			break;
		case R.id.home_shenfen:
			Intent intent3  = new Intent(MainActivity.this,AuthenticationActivity.class);
			startActivity(intent3);
			break;
		default:
			break;
		}
	}
	
	@OnClick(R.id.home_geren)
	private void showLeftMenu(View view){
		leftmenu.showMenu();
		
	}
	
	class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			Message ms = new Message();
			ms.what = 1;
			handler.sendMessage(ms);
		}
	}
	@Override
	public void onWeatherForecaseSearched(AMapLocalWeatherForecast arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onWeatherLiveSearched(AMapLocalWeatherLive arg0) {
		
		 wether.setText(arg0.getCity()+" "+arg0.getWeather()+" "+arg0.getTemperature()+"℃");
	}
   
}
