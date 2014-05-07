package com.example.nfc;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity 
{
	public static String phoneNumber, phoneText;
	EditText etPhnNumber, etText;
	
	@Override
	public void onCreate(Bundle savedState)
	{
		super.onCreate(savedState);
		setContentView(R.layout.main_page);

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
		    	
				startActivity(new Intent(MainActivity.this, ListOfNumbers.class));
			}
		});
	}

	public void dialogs()
	{
		  final Dialog dialog = new Dialog(MainActivity.this);
          // Include dialog.xml file
          dialog.setContentView(R.layout.dialog);
          // Set dialog title
          dialog.setTitle("Custom Dialog");
          dialog.setCancelable(false);

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
            	  
            	  Log.d("a", "phone:"+phoneNumber);
            	  Log.d("a", "text:"+phoneText);
            	  
            	  startActivity(new Intent(MainActivity.this, SendNFC.class));
            	  dialog.dismiss();
              }
          });
          
//          Button btnDataExchange = (Button) dialog.findViewById(R.id.btn_DataExchange);
//          btnDataExchange.setOnClickListener(new OnClickListener() 
//          {
//              @Override
//              public void onClick(View v)
//              {
//            	  startActivity(new Intent(MainActivity.this, List.class));
//            	  dialog.dismiss();
//              }
//          });
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
