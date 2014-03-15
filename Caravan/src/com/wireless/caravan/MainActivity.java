package com.wireless.caravan;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Interface to display the buttons like functionality
		// 1. Google Maps Navigation (Bhargav)
		// 2. Messenger application (Sindhu)
		
		ArrayList<String> array=new ArrayList<String>();
		array.add("Maps");
		array.add("Messenger");
		
		ListView list=(ListView) findViewById(R.id.listView);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        list.setAdapter(arrayAdapter); 
      
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	  @Override
        	  public void onItemClick(AdapterView<?> parent, View view,
        	    int position, long id) {
        		  Intent i = null;
        		 
        		  if(position==0)
        			i=new Intent(getApplicationContext(), MapActivity.class);
        		  if(position==1)
        			i=new Intent(getApplicationContext(), MessengerActivity.class);
        		
        		startActivity(i);
        	  }
        });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
