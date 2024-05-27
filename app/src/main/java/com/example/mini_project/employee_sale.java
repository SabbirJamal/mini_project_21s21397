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

public class employee_sale extends AppCompatActivity {

    EditText da,qd,sa,qs,aa,qa,tot;
    EditText custid,date,totitemtxt,totamt,custp;
    CheckBox rb1,rb2,rb3;
    Button c,s,cl;

    EditText totitem,dre,shai,aba;

    private dresscalc dcalc;
    private shailacalc scalc;
    private abayacalc acalc;
    private totcalc tc;

    String n1,n2,n3;

    saleDB sdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_sale);



        rb1=findViewById(R.id.rb1);
        da=findViewById(R.id.dtxt);
        qd=findViewById(R.id.qdtxt);

        rb2=findViewById(R.id.rb2);
        sa=findViewById(R.id.stxt);
        qs=findViewById(R.id.qstxt);

        rb3=findViewById(R.id.rb3);
        aa=findViewById(R.id.atxt);
        qa=findViewById(R.id.qatxt);

        totitem=findViewById(R.id.totitem);
        dre=findViewById(R.id.qdtxt);
        shai=findViewById(R.id.qstxt);
        aba=findViewById(R.id.qatxt);



        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                da.setText("5");
                n1="Dress";
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sa.setText("2");
                n2="Sheila";
            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aa.setText("15");
                n3="Abaya";
            }
        });

        dcalc=new dresscalc();
        scalc=new shailacalc();
        acalc=new abayacalc();
        tc=new totcalc();

        //total calculation
        tot=findViewById(R.id.tottxt);
        c=findViewById(R.id.calc);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d=da.getText().toString();
                String s=sa.getText().toString();
                String a=aa.getText().toString();

                String dq=qd.getText().toString();
                String sq=qs.getText().toString();
                String aq=qa.getText().toString();

                //conversion to double
                Double dd=Double.parseDouble(d);
                Double ds=Double.parseDouble(s);
                Double da=Double.parseDouble(a);

                Double ddq=Double.parseDouble(dq);
                Double dsq=Double.parseDouble(sq);
                Double daq=Double.parseDouble(aq);

                Double calc1=dcalc.dresscalculation(dd,ddq);
                Double calc2=scalc.shailacalculation(ds,dsq);
                Double calc3=acalc.abayacalculation(da,daq);

                //calculation of total
                Double t=tc.totalcalculation(calc1,calc2,calc3);

                tot.setText(""+t);
                tot.setVisibility(View.VISIBLE);

                totitem.setText(n1+"-"+dq+","+n2+"-"+sq+","+n3+"-"+aq);
            }
        });


        //codes for inputting data into sales database
        s=findViewById(R.id.sale);
        custid=findViewById(R.id.idtxt);
        totamt=findViewById(R.id.tottxt);
        totitemtxt=findViewById(R.id.totitem);
        date=findViewById(R.id.dtxt);
        custp=findViewById(R.id.ptxt);

        cl=findViewById(R.id.clear);
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totitem.setText("");
                totitemtxt.setText("");
                custid.setText("");
                date.setText("");
                custp.setText("");
                da.setText("");
                qd.setText("");
                sa.setText("");
                qs.setText("");
                aa.setText("");
                qa.setText("");
            }
        });

        sdb=new saleDB(this);

        addsales();

    }

    public void addsales(){
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid=custid.getText().toString();
                String tamt=totamt.getText().toString();
                String tottxt=totitemtxt.getText().toString();
                String da=date.getText().toString();
                String phoneno=custp.getText().toString();
                boolean insert=sdb.addsales(uid,tottxt,tamt,da,phoneno);
                if(insert==true)
                {
                    Toast.makeText(employee_sale.this,"Order sucessfully placed", Toast.LENGTH_SHORT).show();
                    Intent login=new Intent(employee_sale.this,employee_page.class);
                    startActivity(login);
                }
                else
                {
                    Toast.makeText(employee_sale.this,"Order Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}