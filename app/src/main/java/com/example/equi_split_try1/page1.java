package com.example.equi_split_try1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class page1 extends AppCompatActivity implements View.OnClickListener {

    LinearLayout layoutlist;
    Button buttonAdd;

    List<String> eventList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        layoutlist = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.btn_add_event);

        buttonAdd.setOnClickListener(this);


        eventList.add("Event");
        eventList.add("Road Trip");
        eventList.add("Shopping");
        eventList.add("Dinner/Lunch");

    }

    @Override
    public void onClick(View v) {

        addView();

    }

    private void addView() {

        View EventView = getLayoutInflater().inflate(R.layout.row_add_event,null,false);

        EditText editText = (EditText)EventView.findViewById(R.id.edit_event_name);
        AppCompatSpinner spinnerTeam = (AppCompatSpinner)EventView.findViewById(R.id.spinner_team);
        ImageView imageClose= (ImageView)EventView.findViewById(R.id.image_remove);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,eventList);
        spinnerTeam.setAdapter(arrayAdapter);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeView(EventView);
            }
        });

        layoutlist.addView(EventView);


    }

    private void removeView(View view) {

        layoutlist.removeView(view);

    }
}