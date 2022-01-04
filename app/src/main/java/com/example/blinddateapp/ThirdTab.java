package com.example.blinddateapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdTab extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private EditText login_name;
    private EditText login_number;
    private EditText login_password;
    private Button join_button;
    private Button login_button;

    private String user_name;
    private String user_number;
    private String user_password;
    private String user_gender;
    private int q1, q2, q3, q4, q5, q6, q7, q8, q9;
    private String q10;

    public ThirdTab() {
    }

    public static ThirdTab newInstance(String param1, String param2) {
        ThirdTab fragment = new ThirdTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.fragment_third_tab, container, false);

        login_name = (EditText) viewgroup.findViewById(R.id.login_name);
        login_number = (EditText) viewgroup.findViewById(R.id.login_number);
        login_password = (EditText) viewgroup.findViewById(R.id.mbti);
        join_button = (Button) viewgroup.findViewById(R.id.join_button);

        join_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), JoinActivity.class);
                startActivity(intent);
            }
        });

        login_button = (Button) viewgroup.findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name = login_name.getText().toString();
                user_password = login_password.getText().toString();
                user_number = login_number.getText().toString();

                if(user_name.getBytes().length <= 0 || user_number.getBytes().length <= 0 || user_password.getBytes().length <= 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                    builder.setTitle("알림창").setMessage("값을 입력해주세요");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

                else {
                    DBHelper helper = new DBHelper(requireActivity(), "FirstProject.db", null, 1);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    String sql = "select * from login where number = '" + user_number + "'";
                    Cursor cursor = db.rawQuery(sql, null);
                    cursor.moveToFirst();
//                    System.out.println(cursor.getColumnIndex("name")); => 1
//                    System.out.println(cursor.getColumnIndex("password")); => 2
//                    System.out.println(cursor.getColumnIndex("number")); => 0
//                    System.out.println(cursor.getColumnIndex("gender")); => 3
                    if(cursor.getCount() != 0) {
                        System.out.println(cursor.getString(0));
                        System.out.println(cursor.getString(1));
                        System.out.println(cursor.getString(2));
                        System.out.println(cursor.getString(3));

                        if (cursor.getString(1).equals(user_name) && cursor.getString(2).equals(user_password)) {
                            SDBHelper shelper = new SDBHelper(requireActivity(), "FirstProject.db", null, 1);
                            SQLiteDatabase sdb = shelper.getWritableDatabase();
                            String sql0 = "select * from survey where number = '" + user_number + "'";
                            Cursor cursor0 = sdb.rawQuery(sql0, null);
                            cursor0.moveToFirst();
                            q1 = cursor0.getInt(4);
                            q2 = cursor0.getInt(5);
                            q3 = cursor0.getInt(6);
                            q4 = cursor0.getInt(7);
                            q5 = cursor0.getInt(8);
                            q6 = cursor0.getInt(9);
                            q7 = cursor0.getInt(10);
                            q8 = cursor0.getInt(11);
                            q9 = cursor0.getInt(12);
                            q10 = cursor0.getString(13);
                            user_gender = cursor0.getString(0);

                            String sql1;
                            Cursor cursor1;
                            if(user_gender.equals("man")) {
                                sql1 = "select * from survey where gender = 'woman'";
                                cursor1 = sdb.rawQuery(sql1, null);
                            }
                            else{
                                sql1 = "select * from survey where gender = 'man'";
                                cursor1 = sdb.rawQuery(sql1, null);
                            }
                            cursor1.moveToFirst();
                            int n = cursor1.getCount();
                            if(n > 0){
                                ArrayList<Double> arr = new ArrayList<Double>();
                                ArrayList<Double> tmp = new ArrayList<Double>();
                                for(int i = 0; i < n; i++){
                                    double x = RMSE_algorithm(cursor1.getInt(4), cursor1.getInt(5), cursor1.getInt(6),
                                            cursor1.getInt(7), cursor1.getInt(8), cursor1.getInt(9),
                                            cursor1.getInt(10), cursor1.getInt(11), cursor1.getInt(12),
                                            cursor1.getString(13));
                                    arr.add(x);
                                    tmp.add(x);
                                    cursor1.moveToNext();
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
                                    cursor1.moveToPosition(index0);
                                    String name0 = cursor1.getString(2);
                                    String number0 = cursor1.getString(1);

                                    cursor1.moveToPosition(index1);
                                    String name1 = cursor1.getString(2);
                                    String number1 = cursor1.getString(1);

                                    cursor1.moveToPosition(index2);
                                    String name2 = cursor1.getString(2);
                                    String number2 = cursor1.getString(1);

                                    Intent intent = new Intent(requireActivity(), MainAppActivity.class);
                                    intent.putExtra("name0", name0);
                                    intent.putExtra("number0", number0);
                                    intent.putExtra("name1", name1);
                                    intent.putExtra("number1", number1);
                                    intent.putExtra("name2", name2);
                                    intent.putExtra("number2", number2);

                                    startActivity(intent);
                            }

                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                            builder.setTitle("알림창").setMessage("비밀번호를 다시 확인해주세요.");
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }
                }
            }
        });


        return viewgroup;
    }
    private double RMSE_algorithm(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9, String x10) {
        double result = Math.sqrt((Math.pow(x1 - q1, 2) * 0.99 + Math.pow(x2 - q2, 2) * 0.98 + Math.pow(x3 - q3, 2) * 0.97
                + Math.pow(x4 - q4, 2) * 1.01 + Math.pow(x5 - q5, 2) * 1.02 + Math.pow(x6 - q6, 2) * 1.03
                + Math.pow(x7 - q7, 2) * 1.04 + Math.pow(x8 - q8, 2) * 0.96 + Math.pow(x9 - q9, 2)) * 0.95 / 9);
        return result;
    }
}
