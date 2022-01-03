package com.example.firstproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainAppActivity extends AppCompatActivity {

    private RecyclerView thirdTabRecyclerview;
    public static ThirdTabMainAdapter adapter;
    private ArrayList<String> nameList = new ArrayList<String>();
    private ArrayList<String> numberList = new ArrayList<String>();
    FriendRequestDBHelper FDB;
    DBHelper LDB;
    FloatingActionButton toReceive;
    Cursor cursor;
    Cursor cursor2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_tab_main_activity);
        Intent intent = getIntent();

        thirdTabRecyclerview = findViewById(R.id.thirdTabRecyclerview);
        toReceive = (FloatingActionButton) findViewById(R.id.thirdTabToReceive);

        String user_number = getIntent().getStringExtra("user_number");
        FDB = new FriendRequestDBHelper(this);
        SQLiteDatabase fdb = FDB.getWritableDatabase();
        cursor = fdb.rawQuery("SELECT * FROM friend_request;", null);
        cursor.moveToFirst();
        do {
            int me = cursor.getInt(0);
            int friend = cursor.getInt(5);
            String sMe = Integer.toString(me);
            String sFriend = Integer.toString(friend);
            //firstPerson.setText(sb);
            if((sMe.compareTo(user_number) == 0) && sFriend.compareTo("0") != 0)
            {
                numberList.add(sFriend);
                //System.out.println(numberList);
                //SDB.acceptRequest("1111", sSender);
                //SDB.acceptRequest(sSender, sd);
            }
        }while(cursor.moveToNext());
        LDB = new DBHelper(this,"FirstProject.db",null,1);
        SQLiteDatabase ldb = LDB.getWritableDatabase();
        cursor2 = ldb.rawQuery("SELECT * FROM login;", null);

        for(int i= 0; i < numberList.size();i++) {
            cursor2.moveToFirst();
            do {
                int num = cursor2.getInt(0);
                String name = cursor2.getString(1);
                String sNum = Integer.toString(num);
                if (sNum.compareTo(numberList.get(i)) == 0) {
                    nameList.add(name);
                    //System.out.println(nameList);
                }
            } while (cursor2.moveToNext());
        }
        //System.out.println(nameList.get(0));
        //System.out.println(numberList.get(0));
        adapter = new ThirdTabMainAdapter(this, nameList, numberList);
        thirdTabRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        thirdTabRecyclerview.setAdapter(adapter);

        toReceive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ReceiveRequestActivity.class);
                intent.putExtra("user_number",user_number);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });
    }
}