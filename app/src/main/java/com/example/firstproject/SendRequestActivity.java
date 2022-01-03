package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SendRequestActivity extends AppCompatActivity {


    Button first;
    Button second;
    Button third;
    Button fourth;
    Button toReceive;
    FriendRequestDBHelper SDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request);
        setTitle("FriendRequest");

        //SDB = openOrCreateDatabase("SendRequestDB.db",MODE_PRIVATE,null);
        Intent intent = getIntent();
        SDB = new FriendRequestDBHelper(this);
        first = (Button) findViewById(R.id.first);
        second = (Button) findViewById(R.id.second);
        third = (Button) findViewById(R.id.third);
        fourth = (Button) findViewById(R.id.fourth);
        toReceive = (Button) findViewById((R.id.toReceive));
        String user_number = intent.getStringExtra("user_number");
        AddData1(user_number);
        AddData2(user_number);
        AddData3(user_number);
        AddData4(user_number);
        toReceive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(getApplication(), ReceiveRequestActivity.class);
                intent.putExtra("user_number",user_number);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);*/
                Intent intent = new Intent(getApplicationContext(), MainAppActivity.class);
                intent.putExtra("user_number",user_number);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

    }
    public void AddData1(String user_number) {
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SDB.sendRequest1(user_number,"1111");
            }
        });
    }
    public void AddData2(String user_number) {
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SDB.sendRequest2(user_number,"2222");
            }
        });
    }
    public void AddData3(String user_number) {
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SDB.sendRequest3(user_number,"3333");
            }
        });
    }
    public void AddData4(String user_number) {
        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SDB.sendRequest4(user_number,"4444");
            }
        });
    }

}