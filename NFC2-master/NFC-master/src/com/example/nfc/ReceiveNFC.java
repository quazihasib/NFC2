package com.example.nfc;

import java.util.Arrays;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ReceiveNFC extends Activity
{
	private NfcAdapter mNfcAdapter;
	private PendingIntent mPendingIntent;
	private IntentFilter[] mIntentFilters;
	private TextView mTextView;
	private String[][] mNFCTechLists;
	
	public static String DEBUG_TAG = ReceiveNFC.class.getSimpleName();

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedState) 
	{
		super.onCreate(savedState);
		setContentView(R.layout.receive_data);
		
		mTextView = (TextView)findViewById(R.id.tv);
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		

		if (mNfcAdapter != null) 
		{
			mTextView.setText("Read an NFC");
		} 
		else 
		{
			mTextView.setText("This phone is not NFC enabled.");
		}

		// create an intent with tag data and deliver to this activity
		mPendingIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

		// set an intent filter for all MIME data
		IntentFilter ndefIntent = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
		try 
		{
			ndefIntent.addDataType("*/*");
			mIntentFilters = new IntentFilter[]
					{ 
						ndefIntent 
					};
		}
		catch (Exception e)
		{
			Log.e("TagDispatch", e.toString());
		}

		mNFCTechLists = new String[][] { new String[] 
				{
					NfcF.class.getName()
				} 
		};
	}

	@SuppressLint("NewApi")
	@Override
	public void onNewIntent(Intent intent)
	{        
		String action = intent.getAction();
		Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

		String s = action + "\n\n" + tag.toString();

		// parse through all NDEF messages and their records and pick text type only
		Parcelable[] data = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		
		if (data != null)
		{
			try
			{
				for (int i = 0; i < data.length; i++)
				{					
					NdefRecord [] recs = ((NdefMessage)data[i]).getRecords();
					for (int j = 0; j < recs.length; j++) 
					{
						if (recs[j].getTnf() == NdefRecord.TNF_WELL_KNOWN &&
								Arrays.equals(recs[j].getType(), NdefRecord.RTD_TEXT))
						{

							byte[] payload = recs[j].getPayload();
							String textEncoding = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
							int langCodeLen = payload[0] & 0077;

							s = (new String(payload, langCodeLen + 1,
											payload.length - langCodeLen - 1, textEncoding) );
							Log.d(DEBUG_TAG, "Receive phone:"+s);
							
							call(s);
						}
					}
				}
			} 
			catch (Exception e) 
			{
				Log.e("TagDispatch", e.toString());
			}

		}

		mTextView.setText(s);
	}

	public void call(String number)
	{
		// TODO Auto-generated method stub
		 try 
		    {
		        Intent callIntent = new Intent(Intent.ACTION_CALL);
		        callIntent.setData(Uri.parse("tel:"+number));
		        startActivity(callIntent);
		    } catch (ActivityNotFoundException activityException) {
		         Log.e("helloandroid dialing example", "Call failed", activityException);
		    }
//		 try {
//		        Intent callIntent = new Intent(Intent.ACTION_CALL);
//		        callIntent.setData(Uri.parse("tel:01923962820"));
//		        startActivity(callIntent);
//		    } catch (ActivityNotFoundException activityException) {
//		         Log.e("helloandroid dialing example", "Call failed", activityException);
//		    }
		 
	}
	
	

	@SuppressLint("NewApi")
	@Override
	public void onResume()
	{
		super.onResume();

		if (mNfcAdapter != null)
		{
			mNfcAdapter.enableForegroundDispatch(this, mPendingIntent, mIntentFilters, mNFCTechLists);
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void onPause()
	{
		super.onPause();

		if (mNfcAdapter != null)
		{
			mNfcAdapter.disableForegroundDispatch(this);
		}
	}
}
