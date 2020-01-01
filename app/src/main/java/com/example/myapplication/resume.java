package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.lang.reflect.Array;
import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class resume extends AppCompatActivity {

    AutoCompleteTextView mycity;
    EditText name,address,mobile_no;
    Button save1;
    Spinner state_spin;
    String selected_state,selected_city,Name,Address,Mobile_No;
    ArrayAdapter<String> unit_adapter;
    ArrayAdapter stateadapt;
    String[] cities;
    Context cnt;
    Resources res;
    FirebaseDatabase fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);
        mycity=(AutoCompleteTextView) findViewById(R.id.city);
        state_spin = (Spinner) findViewById(R.id.state);
        stateadapt = ArrayAdapter.createFromResource(
                this, R.array.state, android.R.layout.simple_spinner_item);
        stateadapt.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        state_spin.setAdapter(stateadapt);
        name=(EditText) findViewById(R.id.name);
        address=(EditText) findViewById(R.id.address);
        mobile_no=(EditText) findViewById(R.id.mobno);
        save1=(Button) findViewById(R.id.save);
        cnt=getApplicationContext();
        res=getResources();

        //state_spin.setAdapter(new emptyspin(adapter,R.layout.textbox,this));
        state_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int iden = parent.getId();

                if (iden == R.id.state) {
                    int flag=0;
                    String state1 = parent.getSelectedItem().toString();
                    int end=state1.indexOf(" ");
                    //Toast.makeText(getApplicationContext()city1,Toast.LENGTH_SHORT).show();
                    if(end>0) {
                        state1 = state1.substring(0, end);
                    }
                    //Toast.makeText(getApplicationContext(), city1, Toast.LENGTH_SHORT).show();
                    res=getResources();
                    int id1=res.getIdentifier(state1,"array",cnt.getPackageName());
                    cities=res.getStringArray(id1);
                    unit_adapter = new ArrayAdapter<String>
                            (parent.getContext(),android.R.layout.simple_dropdown_item_1line,cities);
                    for(int i=0;i<cities.length;i++)
                    {
                        if (cities[i].equals(mycity.getText().toString())) {
                            flag++;
                            break;
                        }
                    }
                    if(flag==0)
                        mycity.setError("Invalid city");
                }
                mycity.setError("Select City");
                mycity.setAdapter(unit_adapter);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Toast.makeText(getApplicationContext(),"helo",Toast.LENGTH_SHORT).show();

        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selected_state=state_spin.getSelectedItem().toString();
                selected_city=mycity.getText().toString();
                Name=name.getText().toString();
                Address=address.getText().toString();
                Mobile_No=mobile_no.getText().toString();
                personaldetailspojo ob=new personaldetailspojo( selected_city,selected_state,Name,Address,Mobile_No);
                if(TextUtils.isEmpty(Name))
                {
                    name.setError("Enter your name");
                    Toast.makeText(getApplicationContext(),"name",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(Address))
                {
                    address.setError("Enter address");
                    Toast.makeText(getApplicationContext(),"add",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(Mobile_No))
                {
                    mobile_no.setError("Enter your Mobile Number");
                    Toast.makeText(getApplicationContext(),"mob",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(selected_city))
                {
                    mycity.setError("Enter your city");
                    Toast.makeText(getApplicationContext(),"city",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    fb=FirebaseDatabase.getInstance();
                    DatabaseReference dbref=fb.getReference();
                    dbref.child("resume").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(ob);
                    Toast.makeText(getApplicationContext(),"here",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(resume.this,education.class);
                    startActivity(i);
                }

            /*Bundle bun=new Bundle();
            bun.putString("name_res",Name);
            bun.putString("add_res",Address);
            bun.putString("name_res",Mobile_No);
            bun.putString("name_res",selected_state);
            bun.putString("name_res",selected_city);*/
            }
        });
    }

}