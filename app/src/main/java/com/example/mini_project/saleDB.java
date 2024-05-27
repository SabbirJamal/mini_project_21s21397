package com.example.mini_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class saleDB extends SQLiteOpenHelper {
    public static final String dbn="saledatabase.db";
    public static final String saltabl="sales_table";
    public static final String oid="Order_ID";
    public static final String titem="Total_Items";
    public static final String tamt="Total_Amount";
    public static final String d="Date";
    public static final String p="Phone_Number";

    public saleDB(Context context){super(context,dbn,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+saltabl+" ("+oid+" TEXT PRIMARY KEY, "+titem+" TEXT, "+tamt+" TEXT, "+d+" TEXT, "+p+" TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldb, int newdb) {
        db.execSQL("DROP TABLE IF EXISTS "+saltabl);
        onCreate(db);
    }

    public boolean addsales(String Order_ID, String Total_Items, String Total_Amount, String Date, String Phone_Number){
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(oid,Order_ID);
        contentValues.put(titem,Total_Items);
        contentValues.put(tamt,Total_Amount);
        contentValues.put(d,Date);
        contentValues.put(p,Phone_Number);
        long result=db.insert(saltabl,null,contentValues);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Integer deleteOrder(String oid){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(saltabl,"Order_ID=?",new String []{oid});
    }
    public Cursor viewsales(String phn)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+saltabl+" WHERE Phone_Number="+phn,null);
        return  cursor;
    }

    public Cursor viewallsales(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT  * FROM "+saltabl,null);
        return cursor;
    }

    public boolean updatedelivery(String dvl,String oid){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(d,dvl);
        db.update(saltabl,contentValues,"Order_ID=?",new String[]{oid});
        return true;
    }
}
