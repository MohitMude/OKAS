package com.example.android.okas;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonsignin;
    private TextInputEditText edtxtemail;
    private TextInputEditText edtxtpassword;
    private TextView txtviewsignup;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonsignin=(Button)findViewById(R.id.Btn_Signin);
        edtxtemail=findViewById(R.id.Edtext_loginemail);
        edtxtpassword=findViewById(R.id.Edtext_loginpassword);
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
        String email=(String)edtxtemail.getText().toString().trim();
        String password=(String)edtxtpassword.getText().toString().trim();
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
            SharedPreferences sharedPreferences=getSharedPreferences("Node_ip",MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            sharedPreferences.edit().putString("ip_key","192.168.43.69");
            finish();
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }
}

