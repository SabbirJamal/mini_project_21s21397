package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class employee_page extends AppCompatActivity {

    Button emplsales, d, u,s,l,ep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);

        emplsales=findViewById(R.id.sale);

        emplsales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(employee_page.this, employee_sale.class);
                startActivity(intent);
            }
        });



        d=findViewById(R.id.delete);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(employee_page.this, delete_sales.class);
                startActivity(intent);
            }
        });



        u=findViewById(R.id.updatedate);
        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(employee_page.this, update_date.class);
                startActivity(intent);
            }
        });


        s=findViewById(R.id.search);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(employee_page.this, seach_order.class);
                startActivity(intent);
            }
        });

        l=findViewById(R.id.logout);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(employee_page.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ep=findViewById(R.id.exit);
        ep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

    }
}