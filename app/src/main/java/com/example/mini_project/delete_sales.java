package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete_sales extends AppCompatActivity {

    Button b,del;
    EditText id;

    saleDB sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_sales);

        b=findViewById(R.id.home);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(delete_sales.this, employee_page.class);
                startActivity(intent);
            }
        });

        id=findViewById(R.id.idtxt);
        del=findViewById(R.id.cancel);


        sdb=new saleDB(this);
        deleteOrder();

    }

    public void deleteOrder(){
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iid=id.getText().toString();
                Integer delete=sdb.deleteOrder(iid);
                if(delete>0){
                    Toast.makeText(delete_sales.this,"Order Cancelled Successfully",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(delete_sales.this,employee_page.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(delete_sales.this,"Couldn't Cancel",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}