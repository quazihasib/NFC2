package com.example.nfc;

import java.nio.charset.Charset;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SendNFC extends Activity 
{
	private NfcAdapter mNfcAdapter;
	private NdefMessage mNdefMessage;
	private TextView mTextView;

	static EditText etPhnNumber;
	static EditText etText;
	Button btnSend, btnDataExchange;
	public static String phnNumber, text;
	CharSequence a;
	TextView et;
	
	public static String DEBUG_TAG = SendNFC.class.getSimpleName();
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedState)
	{
		super.onCreate(savedState);
		setContentView(R.layout.send_data);
		
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

	    etPhnNumber = (EditText) findViewById(R.id.et_AddNumber);
	    etText = (EditText) findViewById(R.id.et_Text);
	    
	    btnSend= (Button) findViewById(R.id.btn_Send);
	    btnDataExchange= (Button) findViewById(R.id.btn_DataExchange);
	    et = (TextView)findViewById(R.id.textView1);
	    
		btnSend.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				phnNumber= etPhnNumber.getText().toString();
				text = etText.getText().toString();
		 		
//				Log.d(DEBUG_TAG,"phn:"+phnNumber);
//				Log.d(DEBUG_TAG, "text:"+text);
				// create an NDEF message with two records of plain text type
//				mNdefMessage = new NdefMessage(new NdefRecord[]
//				{
						createNewTextRecord(""+phnNumber, Locale.ENGLISH, true);
//				});
			}
		});
		
		// create an NDEF message with two records of plain text type
//		mNdefMessage = new NdefMessage(new NdefRecord[]
//		{
//				createNewTextRecord("", Locale.ENGLISH, true)
//		});
	}

	@SuppressLint("NewApi")
	public static NdefRecord createNewTextRecord(String text, Locale locale, boolean encodeInUtf8) 
	{
//		phnNumber= etPhnNumber.getText().toString();
//		Log.d(DEBUG_TAG,"phn:"+phnNumber);
//		text=phnNumber;
//		Log.d(DEBUG_TAG,"phn:"+phnNumber);
		byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));

		Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") : Charset.forName("UTF-16");
		byte[] textBytes = text.getBytes(utfEncoding);

		int utfBit = encodeInUtf8 ? 0 : (1 << 7);
		char status = (char) (utfBit + langBytes.length);

		byte[] data = new byte[1 + langBytes.length + textBytes.length];
		data[0] = (byte) status;
		System.arraycopy(langBytes, 0, data, 1, langBytes.length);
		System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);

		return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
	}

	@SuppressLint("NewApi")
	@Override
	public void onResume()
	{
		super.onResume();

		if (mNfcAdapter != null)
		{
			mNfcAdapter.enableForegroundNdefPush(this, mNdefMessage);
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void onPause()
	{
		super.onPause();

		if (mNfcAdapter != null)
		{
			mNfcAdapter.disableForegroundNdefPush(this);
		}
	}
}
