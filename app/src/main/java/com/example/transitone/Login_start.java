package com.example.transitone;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login_start extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_start);

        AppCompatButton loginbtn = findViewById(R.id.button);
        TextView reg = findViewById(R.id.registration);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        EditText password = findViewById(R.id.editTextTextPassword);

        mAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String emailtxt = String.valueOf(email.getText());
                String passwordtxt = String.valueOf(password.getText());

                if (TextUtils.isEmpty(emailtxt) | TextUtils.isEmpty(passwordtxt)){
                    Toast.makeText(Login_start.this, "Enter the required fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(emailtxt,passwordtxt)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(Login_start.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(Login_start.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }



            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),register_start.class);
                startActivity(intent);
            }
        });

    }
}