package
        com.example.firstproject;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity<val> extends AppCompatActivity {

    private String user_name, user_number, user_password, user_gender;
    private EditText join_name, join_password, join_number;
    private Button join_button, delete_button;
    private CheckBox check_man, check_woman;
    public static FriendRequestDBHelper SDB;
    ThirdTab fragment_third_tab = new ThirdTab();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        join_name = (EditText) findViewById(R.id.join_name);
        join_password = (EditText) findViewById(R.id.join_password);
        join_number = (EditText) findViewById(R.id.join_number);
        check_man = (CheckBox) findViewById(R.id.check_man);
        check_woman = (CheckBox) findViewById(R.id.check_woman);
        join_button = (Button) findViewById(R.id.join_button2);
        delete_button = (Button) findViewById(R.id.delete_button);
        SDB = new FriendRequestDBHelper(this);

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        join_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                user_name = join_name.getText().toString();
                user_password = join_password.getText().toString();
                user_number = join_number.getText().toString();

                if(user_name.getBytes().length <= 0 || user_number.getBytes().length <= 0 || user_password.getBytes().length <= 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    builder.setTitle("알림창").setMessage("값을 입력해주세요.");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{
                    if((check_man.isChecked() && check_woman.isChecked())
                            || (!check_man.isChecked() && !check_woman.isChecked())){
                        AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                        builder.setTitle("알림창").setMessage("성별을 올바르게 체크해주세요.");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                    else{
                        if(check_man.isChecked() && !check_woman.isChecked())
                            user_gender = "man";
                        else
                            user_gender = "woman";

                        DBHelper helper = new DBHelper(JoinActivity.this, "FirstProject.db", null, 1);
                        SQLiteDatabase db = helper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("gender", user_gender);
                        values.put("password", user_password);
                        values.put("name", user_name);
                        values.put("number", user_number);
                        db.insert("login",null, values);

                        /*Intent intent = new Intent(JoinActivity.this, JoinSurveyActivity.class);
                        intent.putExtra("user_gender", user_gender);
                        intent.putExtra("user_password", user_password);
                        intent.putExtra("user_name", user_name);
                        intent.putExtra("user_number", user_number);
                        startActivity(intent);*/
                        Intent intent = new Intent(getApplicationContext(), SendRequestActivity.class);
                        intent.putExtra("user_number",join_number.getText().toString());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);

                    }

                }
                SDB.addTable(join_number.getText().toString());
                SDB.close();
            }

        });

    }
}
