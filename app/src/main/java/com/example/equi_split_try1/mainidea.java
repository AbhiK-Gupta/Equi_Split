package com.example.equi_split_try1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class mainidea extends AppCompatActivity {
    Button btnLogout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainidea);
        btnLogout = findViewById(R.id.btnLogOut);
        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                Intent inttoMainActivity = new Intent(mainidea.this,MainActivity.class);
                startActivity(inttoMainActivity);
            }
        });
    }
}