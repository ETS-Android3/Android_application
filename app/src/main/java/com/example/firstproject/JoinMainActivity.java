package
        com.example.firstproject;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class JoinMainActivity extends AppCompatActivity implements FragmentListener{

    private int q1, q2, q3, q4, q5, q6, q7, q8, q9;
    private String q10;
    private String user_name, user_password, user_gender, user_number;

    Q1Fragment frameQ1;
    Q2Fragment frameQ2;
    Q3Fragment frameQ3;
    Q4Fragment frameQ4;
    Q5Fragment frameQ5;
    Q6Fragment frameQ6;
    Q7Fragment frameQ7;
    Q8Fragment frameQ8;
    Q9Fragment frameQ9;
    Q10Fragment frameQ10;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_main);
        frameQ1 = new Q1Fragment();
        frameQ2 = new Q2Fragment();
        frameQ3 = new Q3Fragment();
        frameQ4 = new Q4Fragment();
        frameQ5 = new Q5Fragment();
        frameQ6 = new Q6Fragment();
        frameQ7 = new Q7Fragment();
        frameQ8 = new Q8Fragment();
        frameQ9 = new Q9Fragment();
        frameQ10 = new Q10Fragment();

        Intent intent = getIntent();
        user_gender = intent.getExtras().getString("user_gender");
        user_password = intent.getExtras().getString("user_password");
        user_name = intent.getExtras().getString("user_name");
        user_number = intent.getExtras().getString("user_number");

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, frameQ1).commit();

    }
    @Override
    public void setQ1(int i) {
        q1 = i;
    }

    @Override
    public void nextQ1(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ2).commit();
    }

    @Override
    public void setQ2(int i) {
        q2 = i;
    }

    @Override
    public void nextQ2() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ3).commit();
    }

    @Override
    public void setQ3(int i) {
        q3 = i;
    }

    @Override
    public void nextQ3() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ4).commit();
    }

    @Override
    public void setQ4(int i) {
        q4 = i;
    }

    @Override
    public void nextQ4() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ5).commit();
    }

    @Override
    public void setQ5(int i) {
        q5 = i;
    }

    @Override
    public void nextQ5() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ6).commit();
    }

    @Override
    public void setQ6(int i) {
        q6 = i;
    }

    @Override
    public void nextQ6() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ7).commit();
    }

    @Override
    public void setQ7(int i) {
        q7 = i;
    }

    @Override
    public void nextQ7() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ8).commit();
    }

    @Override
    public void setQ8(int i) {
        q8 = i;
    }

    @Override
    public void nextQ8() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ9).commit();
    }

    @Override
    public void setQ9(int i) {
        q9 = i;
    }

    @Override
    public void nextQ9() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, frameQ10).commit();
    }

    @Override
    public void set10(String s) {
        q10 = s;
    }

    @Override
    public void submit() {
        SDBHelper helper = new SDBHelper(JoinMainActivity.this, "FirstProject.db", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("q10", q10);
        values.put("q9", q9);
        values.put("q8", q8);
        values.put("q7", q7);
        values.put("q6", q6);
        values.put("q5", q5);
        values.put("q4", q4);
        values.put("q3", q3);
        values.put("q2", q2);
        values.put("q1", q1);
        values.put("password", user_password);
        values.put("name", user_name);
        values.put("number", user_number);
        values.put("gender", user_gender);
        db.insert("survey",null, values);
        System.out.println("ok");

        String sql;
        Cursor cursor;
        if(user_gender.equals("man")) {
            sql = "select * from survey where gender = 'woman'";
            cursor = db.rawQuery(sql, null);
        }
        else{
            sql = "select * from survey where gender = 'man'";
            cursor = db.rawQuery(sql, null);
        }
        cursor.moveToFirst();
        int n = cursor.getCount();
        if(n > 0){
            ArrayList<Double> arr = new ArrayList<Double>();
            ArrayList<Double> tmp = new ArrayList<Double>();
            for(int i = 0; i < n; i++){
                double x = RMSE_algorithm(cursor.getInt(4), cursor.getInt(5), cursor.getInt(6),
                        cursor.getInt(7), cursor.getInt(8), cursor.getInt(9),
                        cursor.getInt(10), cursor.getInt(11), cursor.getInt(12),
                        cursor.getString(13));
                arr.add(x);
                tmp.add(x);
                cursor.moveToNext();
            }
            Collections.sort(arr);
            double min0 = arr.get(0);
            double min1 = arr.get(1);
            double min2 = arr.get(2);
            int index0 = -1;
            int index1 = -1;
            int index2 = -1;
            for(int j = 0; j < n; j++){
                double tmp0 = tmp.get(j);
                if(Double.compare(tmp0, min0) == 0)
                    index0 = j;
                if(Double.compare(tmp0, min1) == 0)
                    index1 = j;
                if(Double.compare(tmp0, min2) == 0)
                    index2 = j;
            }
            if(index0 == -1 || index1 == -1 || index2 == -1){
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinMainActivity.this);
                builder.setTitle("알림창").setMessage("데이터 수가 부족합니다.");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
            else{
                cursor.moveToPosition(index0);
                String name0 = cursor.getString(2);
                String number0 = cursor.getString(1);

                cursor.moveToPosition(index1);
                String name1 = cursor.getString(2);
                String number1 = cursor.getString(1);

                cursor.moveToPosition(index2);
                String name2 = cursor.getString(2);
                String number2 = cursor.getString(1);

                Intent intent = new Intent(JoinMainActivity.this, MainAppActivity.class);
                intent.putExtra("name0", name0);
                intent.putExtra("number0", number0);
                intent.putExtra("name1", name1);
                intent.putExtra("number1", number1);
                intent.putExtra("name2", name2);
                intent.putExtra("number2", number2);

                startActivity(intent);
            }
        }

    }

    private double RMSE_algorithm(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9, String x10){
        double result = Math.sqrt((Math.pow(x1-q1,2) + Math.pow(x2-q2,2) + Math.pow(x3-q3, 2)
                                + Math.pow(x4-q4,2) + Math.pow(x5-q5,2) + Math.pow(x6-q6, 2)
                                + Math.pow(x7-q7,2) + Math.pow(x8-q8,2) + Math.pow(x9-q9, 2))/9);
        return result;
    }


}
