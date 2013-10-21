package com.android.PcTivo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;

public class PcTivoHttpClient extends AsyncTask<Void, Void, Void>{
	private static final String mUrl = "http://192.168.1.105/resolver?data=eli:8080";

	private String mData;
	private HttpResponse mResponse = null;
	private Callback mCallback;
	public PcTivoHttpClient(String data, Callback callback) {
		mData = data;
		mCallback = callback;
	}
	@Override
	protected Void doInBackground(Void... params) {
		try {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("username", "eli");
			jsonObj.put("data", mData);

			// Create the POST object and add the parameters
			HttpPost httpPost = new HttpPost(mUrl);
			StringEntity entity = new StringEntity(jsonObj.toString(), HTTP.UTF_8);
			entity.setContentType("application/json");
			httpPost.setEntity(entity);

			HttpClient client = new DefaultHttpClient();
			mResponse = client.execute(httpPost);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		try{
			if(mResponse !=null) {
				System.err.println("Status: " +mResponse.getStatusLine().getStatusCode());

				switch (mResponse.getStatusLine().getStatusCode()) {
				case 200:
					Message msg = Message.obtain();
					String content = EntityUtils.toString(mResponse.getEntity());
					Handler handler = new Handler(mCallback);
					msg.obj = content;
					handler.sendMessage(msg);
					break;
				case 206:
					break;
				case 404:
					break;
				case 403:
					break;
				default:
					break;
				}

			} else {
				System.err.println("Status: Connection Failed");
			}
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
