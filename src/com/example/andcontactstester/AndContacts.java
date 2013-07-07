package com.example.andcontactstester;




import java.util.ArrayList;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.app.TabActivity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class AndContacts extends TabActivity {

	TabHost mTabHost = null;

	/** Called when the activity is first created. */
	EditText txtname;
	EditText txtphone;
	EditText txtemail;
	EditText txtpostaladdress;
	private static Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.andcontacts);
    
        mTabHost = getTabHost(); 
        
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Contacts", getResources().getDrawable(R.drawable.contact)).setContent(R.id.contactsLayout)); 
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Music", getResources().getDrawable(R.drawable.music)).setContent(R.id.musicLayout)); 
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Video", getResources().getDrawable(R.drawable.video)).setContent(R.id.videoLayout)); 
         
        mTabHost.setCurrentTab(0); 
        AndContacts.context = getApplicationContext();
         txtname = (EditText) findViewById(R.id.txtname );
         txtphone = (EditText) findViewById(R.id.txtphone);
         txtemail = (EditText) findViewById(R.id.txtemail);
         txtpostaladdress = (EditText) findViewById(R.id.txtpostaladdress);
         
        txtname.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                	 String txtname_str=txtname.getText().toString();
                	 if (!(txtname_str.equals("")))
                		 Toast.makeText(AndContacts.getAppContext(),"Name: "+ txtname_str, Toast.LENGTH_SHORT).show();
                	 
                }
            }
            
        });
        
        txtphone.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                	 String txtphone_str=txtphone.getText().toString();
                	 if (!(txtphone_str.equals("")))
                		 Toast.makeText(AndContacts.getAppContext(),"Phone: "+ txtphone_str, Toast.LENGTH_SHORT).show();
                	 
                }
            }
            
        });
        
        txtemail.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                	 String txtemail_str=txtemail.getText().toString();
                	 if (!(txtemail_str.equals("")))
                		 Toast.makeText(AndContacts.getAppContext(),"Email: "+ txtemail_str, Toast.LENGTH_SHORT).show();
                	 
                }
            }
            
        });
        
        txtpostaladdress.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                	 String txtpostaladdress_str=txtpostaladdress.getText().toString();
                	 if (!(txtpostaladdress_str.equals("")))
                		 Toast.makeText(AndContacts.getAppContext(),"Post Address: "+ txtpostaladdress_str, Toast.LENGTH_SHORT).show();
                	 
                }
            }
            
        });
        
        
        Button buttonsave = (Button) findViewById(R.id.buttonsave);
        buttonsave.setOnClickListener(new OnClickListener()
        {
            public void onClick(View arg0)
            {
            	 txtname = (EditText) findViewById(R.id.txtname );
                 txtphone = (EditText) findViewById(R.id.txtphone);
                 txtemail = (EditText) findViewById(R.id.txtemail);
                 txtpostaladdress = (EditText) findViewById(R.id.txtpostaladdress);
                 
            	ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
            	int rawContactInsertIndex = ops.size();

            	ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI)
            	   .withValue(RawContacts.ACCOUNT_TYPE, null)
            	   .withValue(RawContacts.ACCOUNT_NAME,null )
            	   .build());
            	ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
            	   .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
            	   .withValue(Data.MIMETYPE,Phone.CONTENT_ITEM_TYPE)
            	   .withValue(Phone.NUMBER, txtphone.getText().toString())
            	   .build());
            	ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI)
            	   .withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
            	   .withValue(Data.MIMETYPE,StructuredName.CONTENT_ITEM_TYPE)
            	   .withValue(StructuredName.DISPLAY_NAME, txtname.getText().toString())
            	   .build());  
            	
            	try {
					ContentProviderResult[] res = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (OperationApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	Uri outURI = Uri.parse(txtname.getText().toString());
                Intent outData = new Intent();
                outData.setData(outURI);
                setResult(Activity.RESULT_OK, outData);
            	finish();
            }
            
        });
        
    
    }
    public static Context getAppContext() {
        return AndContacts.context;
    }
}
