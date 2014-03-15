package com.wireless.caravan;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class MessengerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messenger);
		
		Intent intent = getIntent();
		Toast.makeText(getApplicationContext(),
                "Intent success", Toast.LENGTH_SHORT)
                .show();
	
	}


}
