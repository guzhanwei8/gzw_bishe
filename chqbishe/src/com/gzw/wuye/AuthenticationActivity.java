package com.gzw.wuye;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.WriterException;
import com.gzw.wuye.config.WuyeConfig;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zxing.activity.CaptureActivity;
import com.zxing.encoding.EncodingHandler;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AuthenticationActivity extends Activity {
	@ViewInject(R.id.iv_qr_image)
	ImageView qrImgImageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.authentication_activity);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}

		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.statusbar_bg);
        
        
        ViewUtils.inject(this);
        
        createImg();
        
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
    
    @OnClick(R.id.shenfen_back)
    private void back(View view){
    	finish();
    }
    @OnClick(R.id.shenfen_sao)
    private void saoyisao(View view){
    	Intent openCameraIntent = new Intent(AuthenticationActivity.this,CaptureActivity.class);
		startActivityForResult(openCameraIntent, 0);
    }
    @OnClick(R.id.shenfen_img)
    private void imgoneself(View  view){

    	createImg();
    	
    	Toast.makeText(AuthenticationActivity.this, "刷新成功", 0).show();
	
    }
    
    private void createImg(){
    	try {
			//去服务器获取当时时间
			String time = "";
			String info = WuyeConfig.UserName+time;
			//加密生成16进制的字符串
			String info1 = "jhjh";
			if (!info1.equals("")) {
				//根据字符串生成的图片,350为生成图片的大小
				Bitmap qrCodeBitmap = EncodingHandler.createQRCode(info1, 400);
				
				qrImgImageView.setImageBitmap(qrCodeBitmap);
				
			}else {
				Toast.makeText(AuthenticationActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
			}
			
		} catch (WriterException e) {
			e.printStackTrace();
		}
    }
   
    
    /*
     * (non-Javadoc)处理扫描结果
     * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
     */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			Toast.makeText(AuthenticationActivity.this,scanResult, 0).show();
		}
	}
}