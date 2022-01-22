package com.example.equi_split_try1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    public EditText emailID,password;
    Button btnSignUp;
    Button btnSignIn;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mDisplayDate= (EditText) findViewById(R.id.etDOB);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view){
                Calendar cal= Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(SignUpActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        
        mDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker,int year,int month,int day){
                month = month + 1;
                String DOB= month + "/" + day + "/" + year;
                mDisplayDate.setText(DOB);

            }
        };


        mFirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignUp.setOnClickListener(new View.OnClickListener(){
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
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(SignUpActivity.this,new OnCompleteListener<AuthResult>(){
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task){
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(SignUpActivity.this,"SignUp Unsuccessful,Please Try Again!",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(SignUpActivity.this,page1.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnSignIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

}