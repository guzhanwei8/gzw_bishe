package com.zxing.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Hashtable;
import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.gzw.wuye.R;
import com.gzw.wuye.config.WuyeConfig;
import com.gzw.wuye.utils.Utils;
import com.zxing.camera.CameraManager;
import com.zxing.decoding.CaptureActivityHandler;
import com.zxing.decoding.InactivityTimer;
import com.zxing.decoding.RGBLuminanceSource;
import com.zxing.view.ViewfinderView;

//��ά��Ĳ������
public class CaptureActivity extends Activity implements Callback {

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	private Button cancelScanButton;
	private ImageView openphoto;
	private ImageView openlight;
	private Bitmap scanBitmap;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.camera);
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		cancelScanButton = (Button) this.findViewById(R.id.btn_cancel_scan);
		openphoto = (ImageView) findViewById(R.id.mo_scanner_photo);
		openlight = (ImageView) findViewById(R.id.mo_scanner_light);
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
		// ���ذ�ť���˳�ɨ��
		cancelScanButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CaptureActivity.this.finish();
			}
		});
		openphoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				photo();
			}
		});
		openlight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				light();
			}
		});

	}

	boolean flag = true;

	/*
	 * �������
	 */
	protected void light() {
		if (flag == true) {
			flag = false;
			// �������
			CameraManager.get().openLight();

		} else {
			flag = true;
			// �������
			CameraManager.get().offLight();

		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	private String photo_path;

	// �����صĶ�ά��ͼƬ
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 200 && data != null) {

			String[] proj = { MediaStore.Images.Media.DATA };
			// ��ȡѡ��ͼƬ��·��
			Cursor cursor = getContentResolver().query(data.getData(), proj,
					null, null, null);

			if (cursor.moveToFirst()) {

				int column_index = cursor
						.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				photo_path = cursor.getString(column_index);
				if (photo_path == null) {
					//����uriȡ��ͼƬ·��
					photo_path = Utils.getPath(getApplicationContext(),
							data.getData());
					Log.i("123path  Utils", photo_path+".png");
				}
				Log.i("123path", photo_path);

			}

			cursor.close();

			new Thread(new Runnable() {

				@Override
				public void run() {

					Result result = scanningImage(photo_path);
					
					if (result == null) {
						Log.i("123", "   -----------");
						Looper.prepare();
						Toast.makeText(getApplicationContext(), "δ��⵽��ά��", 0)
								.show();
						Looper.loop();
					} else {
						// ���ݷ���
						String recode = recode(result.toString());
						Intent data = new Intent();
						data.putExtra("result", recode);
						setResult(300, data);
						finish();
					}
				}
			}).start();

		}

	}
	/*
	 * ������ؽ��������������
	 */
	private String recode(String str) {
		String formart = "";

		try {
			boolean ISO = Charset.forName("ISO-8859-1").newEncoder()
					.canEncode(str);
			if (ISO) {
				formart = new String(str.getBytes("ISO-8859-1"), "GB2312");
				Log.i("1234      ISO8859-1", formart);
			} else {
				formart = str;
				Log.i("1234      stringExtra", str);
			}
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		return formart;
	}
	//����ͼƬ��·����ȡbitmap��ɨ��ͼƬ�еĶ�ά�뷵�ؽ��
	protected Result scanningImage(String path) {
		if (TextUtils.isEmpty(path)) {
			return null;
		}
		Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
		hints.put(DecodeHintType.CHARACTER_SET, "utf-8"); // ���ö�ά�����ݵı���
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true; // �Ȼ�ȡԭ��С
		scanBitmap = BitmapFactory.decodeFile(path, options);
		options.inJustDecodeBounds = false; // ��ȡ�µĴ�С

		int sampleSize = (int) (options.outHeight / (float) 200);

		if (sampleSize <= 0)
			sampleSize = 1;
		options.inSampleSize = sampleSize;
		scanBitmap = BitmapFactory.decodeFile(path, options);

		RGBLuminanceSource source = new RGBLuminanceSource(scanBitmap);
		BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
		QRCodeReader reader = new QRCodeReader();
		try {

			return reader.decode(bitmap1, hints);

		} catch (Exception e) {

			e.printStackTrace();

		}
		return null;

	}
	
	
	
	/*
	 * ����ɨ����
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		String resultString = result.getText();
		// ���Ϊ�վ���ʾɨ��failed
		if (resultString.equals("")) {
			Toast.makeText(CaptureActivity.this, "Scan failed!",
					Toast.LENGTH_SHORT).show();
		} else {
			// �õ�ɨ����+�û���Ϣȥ��������֤�Ƿ�Ϸ����Ϸ���ʾ�ɹ����Ŵ�,�ſ����Ķ�ά���������Ϣʱ���ı�ţ���door01
			
			String virification = resultString+WuyeConfig.UserName;
			
			/**
			 * ���ܺ�ȥ��������֤
			 */
			
			//�õ���֤������Ϸ��ͷ���
			Intent resultIntent = new Intent();
			Bundle bundle = new Bundle();
			
			bundle.putString("result", "��֤ͨ�����Ŵ�");
			resultIntent.putExtras(bundle);
			this.setResult(RESULT_OK, resultIntent);
		}
		CaptureActivity.this.finish();
	}

	/**
	 * ��ͼ�⣬ѡ���ά��ͼƬ
	 * 
	 * @description TODO
	 * @return void ��������
	 */
	private void photo() {

		Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT); // "android.intent.action.GET_CONTENT"

		innerIntent.setType("image/*");

		Intent intent = Intent.createChooser(innerIntent, "ѡ���ά��ͼƬ");

		CaptureActivity.this.startActivityForResult(intent, 200);
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

}