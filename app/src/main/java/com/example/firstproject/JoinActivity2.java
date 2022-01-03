package
        com.example.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity2 extends AppCompatActivity {

    Button join_button2;
    EditText join_number;
    public static FriendRequestDBHelper SDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        join_button2 = (Button) findViewById(R.id.join_button2);
        join_number = (EditText) findViewById(R.id.join_number);
        SDB = new FriendRequestDBHelper(this);
        join_button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                SDB.addTable(join_number.getText().toString());
                SDB.close();
                Intent intent = new Intent(getApplicationContext(), SendRequestActivity.class);
                intent.putExtra("user_number",join_number.getText().toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }
}
