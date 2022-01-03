package com.example.firstproject;

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
        login_password = (EditText) viewgroup.findViewById(R.id.login_password);
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
                    System.out.println(cursor.getString(0));
                    System.out.println(cursor.getString(1));
                    System.out.println(cursor.getString(2));
                    System.out.println(cursor.getString(3));

                    if (cursor.getString(1).equals(user_name) && cursor.getString(2).equals(user_password)) {
                        System.out.println(1);
                        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                        builder.setTitle("알림창").setMessage("로그인에 성공하셨습니다!!");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
            }
        });


        return viewgroup;
    }
}
