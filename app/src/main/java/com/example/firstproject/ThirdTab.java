package com.example.firstproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

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
        EditText login_name = (EditText) viewgroup.findViewById(R.id.login_name);
        EditText login_number = (EditText) viewgroup.findViewById(R.id.login_number);
        EditText login_password = (EditText) viewgroup.findViewById(R.id.login_password);
        Button join_button = (Button) viewgroup.findViewById(R.id.join_button);

        join_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        Button login_button = (Button) viewgroup.findViewById(R.id.login_button);

        return viewgroup;
    }
}
