package com.example.android.okas;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //private EditText edtxtLoginLocalPart;
    //private EditText edtextLoginDomain;
    private EditText edtxtemail;
    private Button buttonsignin;
    private EditText edtxtpassword;
    private TextView txtviewsignup;
    private FirebaseAuth firebaseAuth;
     FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonsignin=(Button)findViewById(R.id.Btn_Signin);
       // edtxtLoginLocalPart=(EditText)findViewById(R.id.Edtext_login_local_part);
        //edtextLoginDomain=(EditText)findViewById(R.id.Edtext_login_domain);
        edtxtemail=(EditText)findViewById(R.id.Edtxt_login_email);
        edtxtpassword=(EditText)findViewById(R.id.Edtext_loginpassword);
        txtviewsignup=(TextView) findViewById(R.id.Text_signup);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null)
        {
            //start profile activity
            finish();
            startActivity(new Intent(this,RoomActivity.class));
        }
        buttonsignin.setOnClickListener(this);
        txtviewsignup.setOnClickListener(this);


    }

    private void userlogin()
    {
        //String email=(String)edtxtLoginLocalPart.getText().toString().trim()+"@"+edtextLoginDomain.getText().toString().trim()+"iitism.com";
        String password=(String)edtxtpassword.getText().toString().trim();
        String email=(String)edtxtemail.getText().toString().trim();
        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Email field cannot be blank", Toast.LENGTH_SHORT).show();
            return ;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Password field cannot be blank",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), RoomActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        if(v==buttonsignin)
        {
            userlogin();
        }
        else if(v==txtviewsignup)
        {
            finish();
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }
}

