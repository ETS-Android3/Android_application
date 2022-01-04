package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager viewPager = findViewById(R.id.viewpager);
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        // 탭에 이미지 추가(이미지를 안 넣을거면 38번 줄까지는 생략해도 됨)
        ArrayList<Integer> image = new ArrayList<>();
        image.add(R.drawable.contact);
        image.add(R.drawable.gallery);
        image.add(R.drawable.star);


        for (int i = 0; i < 3; i++)
        {
            tabLayout.getTabAt(i).setIcon(image.get(i));
        }

        DBHelper helper;
        SQLiteDatabase db;
        helper = new DBHelper(MainActivity.this, "FirstProject.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);

        SDBHelper shelper;
        SQLiteDatabase sdb;
        shelper = new SDBHelper(MainActivity.this, "FirstProject.db", null, 1);
        sdb = helper.getWritableDatabase();
        shelper.onCreate(sdb);
    }
}

    /*public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ContentLayout, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }
}*/