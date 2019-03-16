package com.example.android.okas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private ProgressDialog progressDialog;
    private Button Btn_login;
    private EditText Edtext_email,Edtext_password;
    private TextView Text_signin;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {

            SharedPreferences sharedPreferences=getSharedPreferences("Node_ip",MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            sharedPreferences.edit().putString("ip_key","192.168.43,69");
            finish();
            startActivity(new Intent(this,RoomActivity.class));
        }



        progressDialog=new ProgressDialog(this);
        Btn_login=(Button)findViewById(R.id.Btn_login);
        Edtext_email=(EditText)findViewById(R.id.Edtext_email);
        Edtext_password=(EditText)findViewById(R.id.Edtext_password);
        Text_signin=(TextView)findViewById(R.id.Text_signin);

        Btn_login.setOnClickListener((View.OnClickListener) this);
        Text_signin.setOnClickListener((View.OnClickListener) this);
    }


    private void registerUser() {
        String email = Edtext_email.getText().toString().trim();
        String password = Edtext_password.getText().toString().trim();
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
        progressDialog.setMessage("Registering user please wait");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(SignUpActivity.this, "registration done", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),RoomActivity.class));
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"Failed to register",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view)
    {
        if(view==Btn_login)
            registerUser();
        else if(view==Text_signin)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

    }
}

