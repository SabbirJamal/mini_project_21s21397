package com.example.mini_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_page extends AppCompatActivity {

    Button addemp, views,viewemp,ep;

    registerationdatabase emadddb;
    saleDB sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        addemp=findViewById(R.id.add);
        viewemp=findViewById(R.id.empview);


        addemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin_page.this, add_employee.class);
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

        emadddb=new registerationdatabase(this);
        getAllAccounts();

        views=findViewById(R.id.cs);
        sdb=new saleDB(this);
        viewallsales();

    }

    public void getAllAccounts(){
        viewemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=emadddb.getAllAccounts();
                if(c.getCount()==0)
                {
                    viewdetails("Error","No Accounts Found");
                    return;
                }
                StringBuffer sb=new StringBuffer();
                while(c.moveToNext())
                {
                    sb.append("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-"+"\n");
                    sb.append("Employee ID :- "+c.getString(0)+"\n");
                    sb.append("Employee Name :- "+c.getString(1)+"\n");
                    sb.append("Employee Type :- "+c.getString(2)+"\n");
                    sb.append("Employee Salary :- "+c.getString(3)+"\n");
                    sb.append("Employee Pass :- "+c.getString(4)+"\n");
                    sb.append("---------------------------------------"+"\n");
                }
                viewdetails("Employee Details",sb.toString());
            }
        });
    }
    public void viewallsales(){
        views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=sdb.viewallsales();
                if(c.getCount()==0)
                {
                    viewdetails("Error","No Orders Found");
                }
                StringBuffer sb=new StringBuffer();
                while(c.moveToNext())
                {
                    sb.append("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-"+"\n");
                    sb.append("Order ID :- "+c.getString(0)+"\n");
                    sb.append("Total Items :- "+c.getString(1)+"\n");
                    sb.append("Total Amount :- "+c.getString(2)+"\n");
                    sb.append("Deliver Date :- "+c.getString(3)+"\n");
                    sb.append("Phone Number :- "+c.getString(4)+"\n");
                    sb.append("---------------------------------------"+"\n");
                }
                viewdetails("Order Details",sb.toString());
            }
        });
    }


    public void viewdetails(String title,String mes){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(title);
        ad.setMessage(mes);
        ad.show();
    }

}