package com.android.PcTivo;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends Activity{

	private ImageView mPlayButton;
	private ImageView mPrevButton;
	private ImageView mStopButton;
	private ImageView mNextButton;
	private ImageView mVolumeUpButton;
	private ImageView mVolumeDownButton;
	
	private TextView mText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayButton = (ImageView)findViewById(R.id.playButton);
        mPrevButton = (ImageView)findViewById(R.id.prevButton);
        mStopButton = (ImageView)findViewById(R.id.stopButton);
        mNextButton = (ImageView)findViewById(R.id.nextButton);
        mVolumeUpButton = (ImageView)findViewById(R.id.volumeUpButton);
        mVolumeDownButton = (ImageView)findViewById(R.id.volumeDownButton);
        
        
        mPlayButton.setOnClickListener(mButtonListener);
        mPrevButton.setOnClickListener(mButtonListener);
        mStopButton.setOnClickListener(mButtonListener);
        mNextButton.setOnClickListener(mButtonListener);
        mVolumeUpButton.setOnClickListener(mButtonListener);
        mVolumeDownButton.setOnClickListener(mButtonListener);
        
        mText= (TextView)findViewById(R.id.buttonPressedText);
        
    }
    private OnClickListener mButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			sendToServer((String)v.getTag());
			
		}
    	
    };
	private void sendToServer(String button) {
		mText.setText(button);
		new PcTivoHttpClient(button, callback).execute();
	}

	public void onListResponse(JSONObject jsonObject) {
		
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    Callback callback = new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			String json = (String)msg.obj;
			
			return true;
		}
    	
    };
    
}
