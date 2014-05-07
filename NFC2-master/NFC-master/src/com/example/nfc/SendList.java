package com.example.nfc;

import com.example.nfc.R;

import ListViewAdapter.ListItem;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SendList extends Activity {
	/** Called when the activity is first created. */
	ListView listView;
	String phn, txt;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_main);
		listView = (ListView) findViewById(R.id.lv_country);
		
		listView.setOnItemClickListener(new OnItemClickListener() 
		{
		    @Override
		    public void onItemClick(AdapterView<?> parent, View view,
		            int position, long id) 
		    {

		    	if(MainActivity.listClickEnable==true)
		    	{
		    		if(ListItem.Text[position]!=null || ListItem.Phone[position]!=null)
		    		{
		    			txt= ListItem.Text[position];
		    			phn= ListItem.Phone[position];
		        
		    			Log.i("Hello!", "phn:"+phn);
		    			Log.i("Hello!", "txt:"+txt);
		    		
		    			MainActivity.phoneNumber = ListItem.Phone[position];
		    			MainActivity.listClickEnable = false;
		    			startActivity(new Intent(getBaseContext(), SendNFC.class));
		    			finish();
		    		}
		    		
		    	}
		        
		    }

		});
		
		listView.setAdapter(new EfficientAdapter(this));
	}

	private static class EfficientAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public EfficientAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		public int getCount() {
			return ListItem.Phone.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.two_col_row, null);
				holder = new ViewHolder();
				holder.text1 = (TextView) convertView
						.findViewById(R.id.TextView01);
				holder.text2 = (TextView) convertView
						.findViewById(R.id.TextView02);

				convertView.setTag(holder);
			}
			else 
			{
				holder = (ViewHolder) convertView.getTag();
			}

			holder.text1.setText(ListItem.Phone[position]);
			holder.text2.setText(ListItem.Text[position]);
			
			return convertView;
		}

		static class ViewHolder 
		{
			TextView text1;
			TextView text2;
		}
	}
}