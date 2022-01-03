package
        com.example.firstproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReceiveRequestActivity extends AppCompatActivity {

    FriendRequestDBHelper SDB;
    Cursor cursor;
    private RecyclerView receiveRequestRecyclerview;
    public static ReceiveRequestAdapter adapter;
    private ArrayList<String> list = new ArrayList<>();

    private String sSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_request);

        receiveRequestRecyclerview = findViewById(R.id.receive_request_recyclerview);

        String user_number = getIntent().getStringExtra("user_number");

        SDB = new FriendRequestDBHelper(this);
        SQLiteDatabase db = SDB.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM friend_request;", null);
        cursor.moveToFirst();
        do {
            int sender = cursor.getInt(0);
            int a= cursor.getInt(1);
            int b= cursor.getInt(2);
            int c= cursor.getInt(3);
            int d= cursor.getInt(4);
            String sa = Integer.toString(a);
            String sb = Integer.toString(b);
            String sc = Integer.toString(c);
            String sd = Integer.toString(d);
            sSender = Integer.toString(sender);
            //firstPerson.setText(sb);
            if(sa.compareTo(user_number) == 0)
            {
                list.add(sSender);
                adapter = new ReceiveRequestAdapter(1,this, list,user_number,sSender);
                //SDB.acceptRequest("1111", sSender);
                //SDB.acceptRequest(sSender, sa);
                //firstPerson.setText("back");
            }
            if(sb.compareTo(user_number) == 0)
            {
                list.add(sSender);
                adapter = new ReceiveRequestAdapter(2,this, list,user_number,sSender);
                //SDB.acceptRequest("1111", sSender);
                //SDB.acceptRequest(sSender, sb);
            }
            if(sc.compareTo(user_number) == 0)
            {
                list.add(sSender);
                adapter = new ReceiveRequestAdapter(3,this, list,user_number,sSender);
                //SDB.acceptRequest("1111", sSender);
                //SDB.acceptRequest(sSender, sc);
            }
            if(sd.compareTo(user_number) == 0)
            {
                list.add(sSender);
                adapter = new ReceiveRequestAdapter(4,this, list,user_number,sSender);
                //SDB.acceptRequest("1111", sSender);
                //SDB.acceptRequest(sSender, sd);
            }
       }while(cursor.moveToNext());


        receiveRequestRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        receiveRequestRecyclerview.setAdapter(adapter);

    }
}
