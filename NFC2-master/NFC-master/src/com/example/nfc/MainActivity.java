package com.example.nfc;

import ListViewAdapter.ListItem;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity 
{
	public static String phoneNumber, phoneText;
	EditText etPhnNumber, etText;
	public static int countNumbers;
	
	public static boolean listClickEnable;
	public static MainActivity instance;
	
	@Override
	public void onCreate(Bundle savedState)
	{
		super.onCreate(savedState);
		setContentView(R.layout.main_page);
		instance=this;
		listClickEnable = false;
		
		Button btnReceiveNFC = (Button) findViewById(R.id.ReceiveNFC);
		btnReceiveNFC.setOnClickListener(new OnClickListener() 
		{
			 @Override
			public void onClick(View arg0)
			{
				startActivity(new Intent(MainActivity.this, ReceiveNFC.class));
			}
		});

		Button btnSendNFC = (Button) findViewById(R.id.SendNFC);
		btnSendNFC.setOnClickListener(new OnClickListener() 
		{
		    @Override
			public void onClick(View arg0) 
		    {
		    	
		    	dialogs();
//				startActivity(new Intent(MainActivity.this, SendNFC.class));
			}
		});
		
		Button btnViewHistory = (Button) findViewById(R.id.btn_ViewHistory);
		btnViewHistory.setOnClickListener(new OnClickListener() 
		{
		    @Override
			public void onClick(View arg0) 
		    {
				startActivity(new Intent(MainActivity.this, SendList.class));
			}
		});
	}

	public void dialogs()
	{
		  final Dialog dialog = new Dialog(MainActivity.this);
          // Include dialog.xml file
          dialog.setContentView(R.layout.dialog);
          // Set dialog title
          dialog.setTitle("Add Phone Number");
//          dialog.setCancelable(false);

          // set values for custom dialog components - text, image and button
          etPhnNumber = (EditText) dialog.findViewById(R.id.et_AddNumber);
          etText = (EditText) dialog.findViewById(R.id.et_Text);

          dialog.show();
           
          Button btnSet = (Button) dialog.findViewById(R.id.btn_Set);
          btnSet.setOnClickListener(new OnClickListener() 
          {
              @Override
              public void onClick(View v)
              {
            	  phoneNumber = etPhnNumber.getText().toString();
            	  phoneText = etText.getText().toString();
            	  
            	  Log.d("MainActivity", "phone:"+phoneNumber);
            	  Log.d("MainActivity", "text:"+phoneText);
            	  
            	  startActivity(new Intent(MainActivity.this, SendNFC.class));
            	  dialog.dismiss();
            	  
            	  ListItem.Text[countNumbers]=phoneText;
            	  ListItem.Phone[countNumbers]=phoneNumber; 
            	  
            	  countNumbers++;
            	  Log.d("MainActivity", "countNumbers:"+countNumbers);
              }
          });
          
          Button btnDataExchange = (Button) dialog.findViewById(R.id.btn_DataExchange);
          btnDataExchange.setOnClickListener(new OnClickListener() 
          {
              @Override
              public void onClick(View v)
              {
            	  listClickEnable=true;
            	  startActivity(new Intent(MainActivity.this, SendList.class));
            	  dialog.dismiss();
              }
          });
	}
	
	
	@Override
	public void onResume()
	{
		super.onResume();
	}

	@Override
	public void onPause() 
	{
		super.onPause();
	}

}
