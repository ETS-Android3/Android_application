package com.example.firstproject;

import static android.content.Intent.getIntent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.widget.GridView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondTab extends Fragment {

//    private static final String TAG = "CameraActivity";
//
//    public static final int REQUEST_TAKE_PHOTO = 10;
//    public static final int REQUEST_PERMISSION = 11;
//
//    private Button btnCamera;
//    private String mCurrentPhotoPath;

    private int[] imageIDs = new int[]{
            R.drawable.cat1,
            R.drawable.cat2,
            R.drawable.cat3,
            R.drawable.cat4,
            R.drawable.cat5,
            R.drawable.dog1,
            R.drawable.dog2,
            R.drawable.dog3,
            R.drawable.dog4,
            R.drawable.dog5,
            R.drawable.dog6,
            R.drawable.cat6,
            R.drawable.cat7,
            R.drawable.cat8,
            R.drawable.cat9,
            R.drawable.cat10,
            R.drawable.dog7,
            R.drawable.dog8,
            R.drawable.dog9,
            R.drawable.dog10
    };

    static int[] shuffleArray(int[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }
    //get context로 현재 context 불러오기, oncreateView에서 실행

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SecondTab cont;

    public SecondTab() {
        // Required empty public constructor
    }

    public static SecondTab newInstance(String param1, String param2) {
        SecondTab fragment = new SecondTab();
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

    public void btnClick(View view){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        cont = this;
//        checkPermission();
        // Inflate the layout for this fragment
        ViewGroup viewgroup = (ViewGroup) inflater.inflate(R.layout.fragment_second_tab, container, false);

//        View root = (View)inflater.inflate(R.layout.fragment_second_tab, container, false);
//        com.google.android.material.floatingactionbutton.FloatingActionButton shuffle_button = (com.google.android.material.floatingactionbutton.FloatingActionButton) root.findViewById(R.id.btnCam);
//        shuffle_button.setOnClickListener(this::btnClick);

        GridView gridViewImages = (GridView)viewgroup.findViewById(R.id.gridView);
        ImageGridAdapter imageGridAdapter = new ImageGridAdapter(inflater, container, this, imageIDs);
        gridViewImages.setAdapter(imageGridAdapter);

        View root = (View)inflater.inflate(R.layout.fragment_second_tab, container, false);
        com.google.android.material.floatingactionbutton.FloatingActionButton shuffle_button = (com.google.android.material.floatingactionbutton.FloatingActionButton) viewgroup.findViewById(R.id.btnCam);
        shuffle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageIDs = shuffleArray(imageIDs);
                GridView gridViewImages = (GridView)viewgroup.findViewById(R.id.gridView);
                ImageGridAdapter imageGridAdapter = new ImageGridAdapter(inflater, container, cont, imageIDs);
                gridViewImages.setAdapter(imageGridAdapter);
            }
        });

//        btnCamera = (Button) viewgroup.findViewById(R.id.btnCam);

//        loadImgArr();
//
//        btnCamera.setOnClickListener(v->captureSave());
        return viewgroup;
    }

//    public void captureSave() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if(takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null){
//            File tempDir = getCacheDir();
//        }
//    }
//
//    public void loadImgArr() {
//    }

//    public void checkPermission() {
//        int permissionCamera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
//        int permissionRead = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
//        int permissionWrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if(permissionCamera != PackageManager.PERMISSION_GRANTED
//                || permissionWrite != PackageManager.PERMISSION_GRANTED
//                || permissionRead != PackageManager.PERMISSION_GRANTED){
//            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
//                Toast.makeText(this, "이 앱을 실행하기 위해서는 권한이 필요합니다.").show();
//            }
//        }
//        ActivityCompat.requestPermissions(this, new String[]{
//                Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
//    }
}

