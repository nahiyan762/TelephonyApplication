package com.example.hp.telephonyapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2,et3,et4,et5;
    Uri u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et1 = findViewById(R.id.et2);
        et1 = findViewById(R.id.et3);
        et1 = findViewById(R.id.et4);
        et1 = findViewById(R.id.et5);
    }

    public void sendSMS(View view)
    {
        Intent sIntent=new Intent(this,Sent.class);
        Intent dIntent=new Intent(this,Deliver.class);
        PendingIntent ps_intent=PendingIntent.getActivity
                (this,0,sIntent,0);
        PendingIntent pd_intent=PendingIntent.getActivity
                (this,0,dIntent,0);
        SmsManager sManager=SmsManager.getDefault();
        sManager.sendTextMessage(et1.getText().toString(),
                null,et2.getText().toString(),ps_intent,pd_intent);
    }

    public void call(View view)
    {
        Intent i=new Intent();
        i.setAction(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+et1.getText().toString()));
        startActivity(i);

    }

    public void attach(View view)
    {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("*/*");
        startActivityForResult(i,123);
    }

    public void sendMail(View view)
    {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL,new String[]{et3.getText().toString()} );
        i.putExtra(Intent.EXTRA_SUBJECT, et4.getText().toString());
        i.putExtra(Intent.EXTRA_STREAM,et5.getText().toString());
        i.setType("message/rfc822");
        startActivity(i.createChooser(i,"Select Ant Email Client"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        u=data.getData();
    }

    public void javaMail(View view)
    {}
}
