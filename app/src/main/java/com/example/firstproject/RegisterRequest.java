package com.example.firstproject;


import java.util.HashMap;
import java.util.Map;

public class RegisterRequest{
    private Map<String, String> map;

    public RegisterRequest(String UserName, String UserNum, String UserPwd, String UserGender){
        map = new HashMap<>();
        map.put("user_name", UserName);
        map.put("user_number", UserNum);
        map.put("user_password", UserPwd);
        map.put("user_gender", UserGender);
    }

}
