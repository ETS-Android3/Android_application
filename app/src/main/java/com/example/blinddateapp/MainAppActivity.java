package com.example.blinddateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainAppActivity extends AppCompatActivity {
    private String name1, number1, name2, number2, name3, number3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);
        Intent intent = getIntent();
        name1 = intent.getExtras().getString("name0");
        number1 = intent.getExtras().getString("number0");
        name2 = intent.getExtras().getString("name1");
        number2 = intent.getExtras().getString("number1");
        name3 = intent.getExtras().getString("name2");
        number3 = intent.getExtras().getString("number2");
        String num1_1 = number1.substring(0, 3);
        String num1_2 = number1.substring(3,7);
        String num1_3 = number1.substring(7);
        number1 = num1_1 + "-" + num1_2 + "-" + num1_3;
        String num2_1 = number2.substring(0, 3);
        String num2_2 = number2.substring(3,7);
        String num2_3 = number2.substring(7);
        number2 = num2_1 + "-" + num2_2 + "-" + num2_3;
        String num3_1 = number3.substring(0, 3);
        String num3_2 = number3.substring(3,7);
        String num3_3 = number3.substring(7);
        number3 = num3_1 + "-" + num3_2 + "-" + num3_3;

        TextView text_name1 = (TextView) findViewById(R.id.name1);
        text_name1.setText(name1);
        TextView text_number1 = (TextView) findViewById(R.id.number1);
        text_number1.setText(number1);
        TextView text_name2 = (TextView) findViewById(R.id.name2);
        text_name2.setText(name2);
        TextView text_number2 = (TextView) findViewById(R.id.number2);
        text_number2.setText(number2);
        TextView text_name3 = (TextView) findViewById(R.id.name3);
        text_name3.setText(name3);
        TextView text_number3 = (TextView) findViewById(R.id.number3);
        text_number3.setText(number3);



        Button home_button = (Button) findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent0 = new Intent(MainAppActivity.this, MainActivity.class);
                startActivity(intent0);
            }
        });


    }
}