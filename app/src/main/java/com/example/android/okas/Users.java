package com.example.android.okas;

import com.example.android.okas.SignUpActivity;

public class Users {
    String user_name;
    String room_no;
    String room_IP;
    String password;

    public Users()
    {

    }
    public Users(String username,String roomno,String roomIP,String password)
    {
        this.user_name=user_name;
        this.password=password;
        this.room_IP=room_IP;
        this.room_no=room_no;
    }

    public String getUsername() {
        return user_name;
    }

    public String getRoomno() {
        return room_no;
    }

    public String getRoomIP() {
        return room_IP;
    }

    public String getPassword() {
        return password;
    }
}

