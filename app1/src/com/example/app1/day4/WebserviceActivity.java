package com.example.app1.day4;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app1.R;

public class WebserviceActivity extends Activity {
	private static final String URL_ORF = "http://rss.orf.at/news.xml";
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.webservice);

		getActionBar().setSubtitle("Subtitle in ActionBar");
//		getActionBar().hide();
		
		if( Build.VERSION.SDK_INT >= 9){
			Log.v("gek", "sdk-version:" + Build.VERSION.SDK_INT);
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		    StrictMode.setThreadPolicy(policy); 
		}
		
		Button butGetData = (Button) findViewById(R.id.butWebserviceData);
		butGetData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getData();
			}
		});
		
		Button butGetDataSynchron = (Button) findViewById(R.id.butWebserviceDataSynchron);
		butGetDataSynchron.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getDataSynchron();
			}
		});
		
		lv = (ListView) findViewById(R.id.lvWebservice);
		lv.setBackgroundColor(Color.LTGRAY);
	}

	public void getDataSynchron() {
	  URL url;
      try {
		url = new URL(URL_ORF);

		  URLConnection connection;
		  connection = url.openConnection();

		  HttpURLConnection httpConnection = (HttpURLConnection)connection;
		  int responseCode = httpConnection.getResponseCode();

		  if (responseCode == HttpURLConnection.HTTP_OK) {
		    InputStream in = httpConnection.getInputStream();
		    String[] news = getNewsArray(in);
		    fillListView(news);
		  }
		  else {
			  Toast.makeText(this, "http response code:" + responseCode, Toast.LENGTH_SHORT).show();
		  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getData() {
		AufbauAsync task = new AufbauAsync();
        task.execute(new String[] { URL_ORF });
	}
	
	public void fillListView(String[] news) {
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, news);
        lv.setAdapter(aa);
	}
	
	protected class AufbauAsync extends AsyncTask<String, Void, String[]> {
        protected String[] doInBackground(String[] urls) {
	        try {
	            for (String url : urls) {   
		            DefaultHttpClient httpClient = new DefaultHttpClient();
		            HttpGet get = new HttpGet(url);
		            HttpResponse httpResponse = httpClient.execute(get);
		            HttpEntity httpEntity = httpResponse.getEntity();
		            InputStream is = httpEntity.getContent();
		            String[] newsArray = getNewsArray(is);
		            return newsArray;
	            } 
	        } catch (UnsupportedEncodingException e) {
	
	        } catch (ClientProtocolException e) {
	
	        } catch (Exception e) {
	        	throw new RuntimeException("Something bad has happened", e);
	        }
			return null;
        }

	    protected void onPostExecute(String[] resultArray) { 
	    	try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, resultArray);
	        lv.setAdapter(aa);
	        
	       Toast.makeText(getApplicationContext(), "fertig.", Toast.LENGTH_SHORT).show();
	    }
	}
	
	private String[] getNewsArray(InputStream is) throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        
        Document dom = db.parse(is);
        Element docEle = dom.getDocumentElement();
        
        NodeList nl = docEle.getElementsByTagName("item");
        
        
        String[] temp_news = new String[nl.getLength()];
        
        if (nl != null && nl.getLength() > 0) {
          for (int i = 0 ; i < nl.getLength();i++){ 
            Element entry = (Element)nl.item(i);
            Element title = (Element)entry.getElementsByTagName("title").item(0);
            Element link = (Element)entry.getElementsByTagName("link").item(0);

            String details = title.getFirstChild().getNodeValue();
            temp_news[i]=details;
          }
        }
        
        return temp_news;
	}

}
