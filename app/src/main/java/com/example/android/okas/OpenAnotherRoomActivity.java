package com.example.android.okas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

public class OpenAnotherRoomActivity extends AppCompatActivity implements View.OnClickListener {
   private   Button btn_send_notification;
    NumberPicker numberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_another_room);

        numberPicker=(NumberPicker)findViewById(R.id.picker);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(0);
        btn_send_notification=(Button)findViewById(R.id.Btn_send_notification_to_owner);
        btn_send_notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btn_send_notification)
        {
            Toast.makeText(this,"Notification will be sent",Toast.LENGTH_SHORT).show();
        }
    }
}
