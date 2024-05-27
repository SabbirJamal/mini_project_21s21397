package com.example.mini_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class seach_order extends AppCompatActivity {

    Button b,view;
    EditText id;
    saleDB sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach_order);


        b=findViewById(R.id.home);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(seach_order.this, employee_page.class);
                startActivity(intent);
            }
        });

        sdb=new saleDB(this);
        id=findViewById(R.id.idtxt);
        view=findViewById(R.id.cancel);
        viewsales();
    }

    public void viewsales(){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iid=id.getText().toString();
                Cursor c=sdb.viewsales(iid);
                if(c.getCount()==0)
                {
                    viewcustomerdetails("Error","Nothing Found");
                    return;
                }
                StringBuffer sb=new StringBuffer();
                while(c.moveToNext())
                {
                    sb.append("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-"+"\n");
                    sb.append("Order ID :- "+c.getString(0)+"\n");
                    sb.append("Total Payable :- "+c.getString(1)+"\n");
                    sb.append("Total Items :- "+c.getString(2)+"\n");
                    sb.append("Delivery Date :- "+c.getString(3)+"\n");
                    sb.append("Phone Number :- "+c.getString(4)+"\n");
                    sb.append("---------------------------------------"+"\n");
                }
                viewcustomerdetails("Order Details",sb.toString());
            }
        });
    }

    public void viewcustomerdetails(String title,String mes){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(title);
        ad.setMessage(mes);
        ad.show();
    }
}