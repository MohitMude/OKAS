package com.example.android.okas;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class OpenOverInternet extends AppCompatActivity {

    private Socket client;
    private PrintWriter printWriter;
    private TextView textView;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_over_internet);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    printWriter = new PrintWriter(client.getOutputStream(), true);
                    printWriter.write(message);
                    printWriter.flush();
                    printWriter.close();
                    client.close();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
