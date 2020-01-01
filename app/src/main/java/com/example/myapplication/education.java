package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class education extends AppCompatActivity {

    EditText school,degree,date,state,city;
    String School,Degree,Date,State,City;
    FloatingActionButton add;
    FirebaseDatabase fd;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        school=findViewById(R.id.school);
        degree=findViewById(R.id.degree);
        date=findViewById(R.id.date);
        state=findViewById(R.id.state_edu);
        city=findViewById(R.id.city_edu);
        add=findViewById(R.id.add);
        fd=FirebaseDatabase.getInstance();
        ref=fd.getReference("resume").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("education");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                School=school.getText().toString();
                Degree=degree.getText().toString();
                Date=date.getText().toString();
                City=city.getText().toString();
                State=state.getText().toString();
                educationpojo ob=new educationpojo(School,Degree,Date,State,City);
                ref.push().setValue(ob);//setValue(ob);
                Intent i=new Intent(education.this,education.class);
                startActivity(i);
            }
        });
    }
}
