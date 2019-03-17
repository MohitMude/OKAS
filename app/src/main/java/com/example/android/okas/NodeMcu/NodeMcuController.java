package com.example.android.okas.NodeMcu;

import android.content.SharedPreferences;
import android.os.AsyncTask;


import com.example.android.okas.helper.Logcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

public class NodeMcuController {
    String onoff;
    String ip;
    Logcat logcat;

    public void StatusChange (String statusSet,String ip) {
        this.onoff = statusSet;
        Date currentTime = Calendar.getInstance().getTime();
        logcat =new Logcat();
        logcat.appendLog(currentTime+" : NodeMcu accessed with command "+onoff);

        this.ip=ip;

        String serverIP = "192.168.43.70" + onoff;
        TaskEsp taskEsp=new TaskEsp(serverIP);
        taskEsp.execute();
    }

       public class TaskEsp extends AsyncTask<Void, Void, String> {

            String server;


            TaskEsp(String server){
                this.server = server;
            }

            @Override
            protected String doInBackground(Void... params) {

                final String p = "http://"+server;



                String serverResponse = "";


                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL(p).openConnection());

                    if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                        InputStream inputStream = null;
                        inputStream = httpURLConnection.getInputStream();
                        BufferedReader bufferedReader =
                                new BufferedReader(new InputStreamReader(inputStream));
                        serverResponse = bufferedReader.readLine();

                        inputStream.close();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    serverResponse = e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    serverResponse = e.getMessage();
                }


                return serverResponse;
            }


        }
    }

