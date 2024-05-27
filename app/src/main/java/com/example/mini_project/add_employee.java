package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class add_employee extends AppCompatActivity {
    RadioButton r1,r2;
    EditText et;

    EditText empid,empfname,etype,empsal,epass;

    Button addemp,h,c;
    registerationdatabase empadddb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        r1=findViewById(R.id.rb1);
        r2=findViewById(R.id.rb2);
        et=findViewById(R.id.emptype);

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("SALES MAN");
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("TAILOR");
            }
        });

        empid=findViewById(R.id.idtxt);
        empfname=findViewById(R.id.ntxt);
        etype=findViewById(R.id.emptype);
        empsal=findViewById(R.id.salary);
        epass=findViewById(R.id.emppass);
        addemp=findViewById(R.id.addemployee);

        c=findViewById(R.id.clear);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empid.setText("");
                empfname.setText("");
                etype.setText("");
                empsal.setText("");
                empid.setText("");
            }
        });



        add_employee();
        empadddb=new registerationdatabase(this);

        h=findViewById(R.id.home);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(add_employee.this, admin_page.class);
                startActivity(intent);
            }
        });



    }
    public void add_employee(){
        addemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eid=empid.getText().toString();
                String efn=empfname.getText().toString();
                String et=etype.getText().toString();
                String es=empsal.getText().toString();
                String ep=epass.getText().toString();
                boolean insert=empadddb.addemployee(eid,efn,et,es,ep);
                if(insert==true)
                {
                    Toast.makeText(add_employee.this,"Employee has been Created Successfully", Toast.LENGTH_SHORT).show();
                    Intent login=new Intent(add_employee.this,MainActivity.class);
                    startActivity(login);
                }
                else
                {
                    Toast.makeText(add_employee.this,"Employee is already existing", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}