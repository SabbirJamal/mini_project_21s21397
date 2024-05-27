package com.example.mini_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class registerationdatabase extends SQLiteOpenHelper {

    public static final String regdb ="registeration.db";
    public static final String regtbl="employee_details";
    public static final String empid="Employee_ID";
    public static final String empname="Employee_Name";
    public static final String emptype="Employee_Type";
    public static final String empsal="Employee_Salary";
    public static final String emppass="Employee_Pass";

    public registerationdatabase(Context context){super(context,regdb,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+regtbl+" ("+empid+" TEXT PRIMARY KEY, "+empname+" TEXT, "+emptype+" TEXT,"+empsal+" TEXT, "+emppass+" TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int olddb, int newdb) {
        db.execSQL("DROP TABLE IF EXISTS "+regtbl);
        onCreate(db);
    }

    public boolean addemployee(String Employee_ID, String Employee_Name, String Employee_Type, String Employee_Salary,String Employee_Pass){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(empid,Employee_ID);
        contentValues.put(empname,Employee_Name);
        contentValues.put(emptype,Employee_Type);
        contentValues.put(empsal,Employee_Salary);
        contentValues.put(emppass,Employee_Pass);

        long result=db.insert(regtbl,null,contentValues);
        db.close();
        if(result==-1)
            return false;
        else
            return true;
    }


    public String getpass(String employeeid){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+regtbl+" WHERE "+empid+" ='"+employeeid+"'",null);
        cursor.moveToFirst();
        String Password=cursor.getString(4);
        db.close();
        cursor.close();
        return Password;
    }

    public Cursor getAllAccounts(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT  * FROM "+regtbl,null);
        return cursor;
    }



}
