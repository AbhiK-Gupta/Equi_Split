package com.example.equi_split_try1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    public Button SignUp_btn;
    public EditText emailID,password;
    Button btnSignUp;
    Button btnSignIn;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnLogin);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null)
                {
                    Toast.makeText(MainActivity.this,"You are Logged In!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,page1.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this,"Please LogIn!",Toast.LENGTH_SHORT).show();
                }
            }
        };
        SignUp_btn = (Button) findViewById(R.id.btnSignUp);

        SignUp_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent1);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String email = emailID.getText().toString();
                String pass = password.getText().toString();
                if(email.isEmpty())
                {
                    emailID.setError("Please Enter Email ID");
                    emailID.requestFocus();
                }


                else if(pass.isEmpty())
                {
                    password.setError("Please Enter Email ID");
                    password.requestFocus();
                }
                else if (!(email.isEmpty() && pass.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task){
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Login Error, Please Try Again!!",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intToHome = new Intent(MainActivity.this,page1.class);
                                startActivity(intToHome);

                            }
                        }

                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}