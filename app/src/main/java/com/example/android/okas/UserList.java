package com.example.android.okas;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class UserList extends ArrayAdapter<Users> {
    private int position;
    private Activity context;
    private List<Users> usersList;

    public UserList(Context context, int resource, List<Users> usersList) {
        super(context, resource);
        this.usersList = usersList;

    }


}
