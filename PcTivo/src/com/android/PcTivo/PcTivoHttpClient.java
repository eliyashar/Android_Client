package com.android.PcTivo;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class PcTivoHttpClient extends AsyncTask<Void, Void, Void>{
	private static final String mUrl = "http://192.168.1.105/resolver?data=eli:8080";
	
	private String mData;
	private HttpResponse mResponse = null;
	public PcTivoHttpClient(String data) {
		mData = data;
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
		if(mResponse !=null) {
			System.err.println("Status: " +mResponse.getStatusLine().getStatusCode());
		} else {
			System.err.println("Status: Connection Failed");
		}
	}
}
