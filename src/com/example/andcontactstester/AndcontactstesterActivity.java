package com.example.andcontactstester;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AndcontactstesterActivity extends Activity {
	private final int SHOW_SUB_ACTIVITY=1;
	private final int PICK_CONTACT=2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.andcontactstester);
        
        Button goToAndContact = (Button) findViewById(R.id.goToAndContact);
        Button goToContactPicker = (Button) findViewById(R.id.goToContactPicker);
        
        goToAndContact.setOnClickListener(new OnClickListener()
        {
            public void onClick(View arg0)
            {
            	Intent intent = new Intent("com.dn.andContact");
            	startActivityForResult(intent, SHOW_SUB_ACTIVITY);
            
            }
            
        });
        
        goToContactPicker.setOnClickListener(new OnClickListener()
        {
            public void onClick(View arg0)
            {
            	Intent intent1 = new Intent ("com.dn.pick");
            	startActivityForResult(intent1, PICK_CONTACT);
            }
            
        });
    }  
    
    
        @Override
        public void onActivityResult(int reqCode, int resCode, Intent data) 
        {
        	super.onActivityResult(reqCode, resCode, data);

        	switch(reqCode) 
        	{
            	case (PICK_CONTACT) : 
            	{
            		if (resCode == Activity.RESULT_OK) 
            		{
                        TextView tv = (TextView)findViewById(R.id.andContacttxt);
                        tv.setText("");
                        TextView tv1 = (TextView)findViewById(R.id.contactPickertxt);
                        tv1.setText(data.getData().toString());
            		}
            		break;
            	}
            	
            	case (SHOW_SUB_ACTIVITY) :
            	{
            		if (resCode == RESULT_OK)
            		{
            			TextView tv1 = (TextView)findViewById(R.id.andContacttxt);
            			tv1.setText(data.getData().toString());
            			TextView tv = (TextView)findViewById(R.id.contactPickertxt);
            			tv.setText("");
            		}
            	}
        	}
        
    
    }
    
   
}
