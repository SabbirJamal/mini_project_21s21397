package com.example.mini_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText uid,pass;

    Button b;
    registerationdatabase empadddb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b=findViewById(R.id.login);
        uid=findViewById(R.id.idtxt);
        pass=findViewById(R.id.passtxt);

        empadddb=new registerationdatabase(this);

        getpass();

    }

    public void getpass(){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=uid.getText().toString();
                String p=pass.getText().toString();
                if (id.equals("admin") && p.equals("admin"))
                {
                    Intent intent=new Intent(MainActivity.this, admin_page.class);
                    startActivity(intent);
                }

                else
                {
                    try{
                        String rp=empadddb.getpass(id);
                        if (p.equals(rp))
                        {
                            Toast.makeText(MainActivity.this,"WELCOME",Toast.LENGTH_SHORT).show();
                            uid.setText("");
                            pass.setText("");
                            Intent intent=new Intent(MainActivity.this, employee_page.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}