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
   String[] alphabet={"A","B","C","D"};
    NumberPicker numberPicker1;
    NumberPicker numberPicker2;
    NumberPicker numberPicker3;
    NumberPicker numberPicker4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_another_room);

        numberPicker1=(NumberPicker)findViewById(R.id.picker1);
        numberPicker2=(NumberPicker)findViewById(R.id.picker2);
        numberPicker3=(NumberPicker)findViewById(R.id.picker3);
        numberPicker4=(NumberPicker)findViewById(R.id.picker4);

        numberPicker1.setMaxValue(3);
        numberPicker1.setMinValue(0);
        numberPicker1.setDisplayedValues(alphabet);
        numberPicker1.setWrapSelectorWheel(false);

        numberPicker2.setMaxValue(9);
        numberPicker2.setMinValue(0);
        numberPicker2.setWrapSelectorWheel(false);

        numberPicker3.setMaxValue(9);
        numberPicker3.setMinValue(0);
        numberPicker3.setWrapSelectorWheel(false);

        numberPicker4.setMaxValue(9);
        numberPicker4.setMinValue(0);
        numberPicker4.setWrapSelectorWheel(false);

        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal1, int newVal1) {
                Toast.makeText(getApplicationContext(),"selected value is "+ alphabet[newVal1],Toast.LENGTH_SHORT).show();
            }
        });

        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal2, int newVal2) {
                Toast.makeText(getApplicationContext(),"selected value is "+ newVal2,Toast.LENGTH_SHORT).show();
            }
        });
        numberPicker3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal3, int newVal3) {
                Toast.makeText(getApplicationContext(),"selected value is "+ newVal3,Toast.LENGTH_SHORT).show();
            }
        });
        numberPicker4.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal4, int newVal4) {
                Toast.makeText(getApplicationContext(),"selected value is "+ newVal4,Toast.LENGTH_SHORT).show();
            }
        });

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
