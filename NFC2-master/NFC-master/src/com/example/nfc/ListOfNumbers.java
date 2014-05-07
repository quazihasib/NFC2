package com.example.nfc;

import java.util.ArrayList;

import com.example.nfc.R;

import ListViewAdapter.CustomListAdapter;
import ListViewAdapter.NewsItem;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class ListOfNumbers  extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);

		ArrayList<NewsItem> image_details = getListData();
		final ListView lv1 = (ListView) findViewById(R.id.custom_list);

		lv1.setAdapter(new CustomListAdapter(this, image_details));
		lv1.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) 
			{
				Object o = lv1.getItemAtPosition(position);
				NewsItem newsData = (NewsItem) o;
				Toast.makeText(ListOfNumbers.this, "Selected :" + " " + newsData, Toast.LENGTH_SHORT).show();
			
			}
		});
	}

	private ArrayList<NewsItem> getListData()
	{
		ArrayList<NewsItem> results = new ArrayList<NewsItem>();
		
		NewsItem newsData = new NewsItem();
		newsData.setPhoneNumber("Dance of Democracy");
		newsData.setText("Pankaj Gupta");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setPhoneNumber("Major Naxal attacks in the past");
		newsData.setText("Pankaj Gupta");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setPhoneNumber("BCCI suspends Gurunath pending inquiry ");
		newsData.setText("Rajiv Chandan");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setPhoneNumber("Life convict can`t claim freedom after 14 yrs: SC");
		newsData.setText("Pankaj Gupta");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setPhoneNumber("Indian Army refuses to share info on soldiers mutilated at LoC");
		newsData.setText("Pankaj Gupta");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setPhoneNumber("French soldier stabbed; link to Woolwich attack being probed");
		newsData.setText("Sudeep Nanda");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setPhoneNumber("Major Naxal attacks in the past");
		newsData.setText("Pankaj Gupta");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setPhoneNumber("BCCI suspends Gurunath pending inquiry ");
		newsData.setText("Rajiv Chandan");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setPhoneNumber("Life convict can`t claim freedom after 14 yrs: SC");
		newsData.setText("Pankaj Gupta");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setPhoneNumber("Indian Army refuses to share info on soldiers mutilated at LoC");
		newsData.setText("Pankaj Gupta");
		results.add(newsData);

		newsData = new NewsItem();
		newsData.setPhoneNumber("French soldier stabbed; link to Woolwich attack being probed");
		newsData.setText("Sudeep Nanda");
		results.add(newsData);
		
		return results;
	}

}
