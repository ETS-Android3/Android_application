package com.example.blinddateapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FirstTabDialog extends DialogFragment {

    public static final String TAG_EVENT_DIALOG = "dialog_event";

    public FirstTabDialog() {
    }

    public static FirstTabDialog getInstance() {
        FirstTabDialog d = new FirstTabDialog();
        return d;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_tab_dialog_fragment, container);
        final TextView enterName = v.findViewById(R.id.enterName);
        final TextView enterNumber = v.findViewById(R.id.enterNumber);
        final EditText editName = v.findViewById(R.id.editName);
        final EditText editNumber = v.findViewById(R.id.editNumber);
        final Button confirm = v.findViewById(R.id.confirm);
        final Button cancel = v.findViewById(R.id.cancel);

        confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                ContactsVO newData = new ContactsVO();
                String number1 = editNumber.getText().toString();
                String num1_1 = number1.substring(0, 3);
                String num1_2 = number1.substring(3, 7);
                String num1_3 = number1.substring(7);
                String number = num1_1 + "-" + num1_2 + "-" + num1_3;
                newData.setName(editName.getText().toString());
                newData.setNumber(number);

                ArrayList<ContactsVO> list = new ArrayList<>();
                AssetManager assetManager = getResources().getAssets();
                try {
                    //InputStream fis = assetManager.open("contacts.json");
                    File file = new File("/data/data/com.example.blinddateapp/test.json");
                    boolean isExists = file.exists();
                    if (isExists) {

                    } else {
                        FileWriter fw = null;
                        String filename = getContext().getDataDir() + "/";
                        fw = new FileWriter(filename + "test.json");
                        BufferedWriter bufwr = new BufferedWriter(fw);
                        bufwr.write("{\n" +
                                "  \"contacts\": []\n" +
                                "}");// TODO 파일에 넣을 String값
                        bufwr.close();
                    }
                    FileInputStream fis = new FileInputStream(file);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    fis.close();
                    String json = new String(buffer, "UTF-8");

                    JSONObject jsonObject = new JSONObject(json);

                    JSONArray jsonArray = jsonObject.getJSONArray("contacts");

                    JSONObject as = new JSONObject();
                    as.put("name", newData.getName());
                    as.put("number", newData.getNumber());
                    jsonArray.put(as);
                    jsonObject.put("contacts", jsonArray);
                    //enterName.setText(jsonObject.toString());
                    //Gson gson = new Gson();
                    //String a = gson.toJson(jsonArray);
                    FileWriter fw = null;
                    String filename = getContext().getDataDir() + "/";
                    fw = new FileWriter(filename + "test.json");
                    BufferedWriter bufwr = new BufferedWriter(fw);
                    bufwr.write(jsonObject.toString());// TODO 파일에 넣을 String값
                    bufwr.close();


                    //FirstTab a = new FirstTab().getInstance();
                    //a.show(getActivity().getSupportFragmentManager(), FirstTabDialog.TAG_EVENT_DIALOG);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return v;
    }


}