package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_date extends AppCompatActivity {

    EditText id,newdate;
    saleDB sdb;
    Button u,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_date);

        id=findViewById(R.id.idtxt);
        newdate=findViewById(R.id.ndate);
        u=findViewById(R.id.update);
        sdb=new saleDB(this);

        updatedelivery();



        b=findViewById(R.id.home);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(update_date.this, employee_page.class);
                startActivity(intent);
            }
        });
    }

    public void updatedelivery(){
        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String custid=id.getText().toString();
                String nd=newdate.getText().toString();
                boolean updatedlv=sdb.updatedelivery(nd,custid);
                if (updatedlv==true)
                {
                    Toast.makeText(update_date.this, "Delivery Date updated", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(update_date.this,employee_page.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(update_date.this, "Date not updated", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}