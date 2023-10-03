package com.example.asiment_du_an_mau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asiment_du_an_mau.database.Sqldatabase;
import com.example.asiment_du_an_mau.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;
    public  SachDAO(Context context){
        Sqldatabase sqldatabase=new Sqldatabase(context);
        db=sqldatabase.getWritableDatabase();
    }
    public long insert(Sach sach){
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenSach",sach.tenSach);
        contentValues.put("giaThue",sach.giaThue);
        return db.insert("Sach",null,contentValues);
    }
    public int update(Sach sach){
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenSach",sach.tenSach);
        contentValues.put("giaSach",sach.giaThue);
        return  db.update("Sach",contentValues,"maSach=?",new String[]{String.valueOf(sach.maSach)});
    }
    public int delete(String id){
        return db.delete("Sach","maSach=?",new String[]{id});
    }
    public List<Sach>getAll(){
        String sql="select * from Sach";
        return getData(sql);
    }
    public Sach getID(String id){
        String sql="select * from Sach where maSach=?";
        List<Sach>list=getData(sql,id);
        return list.get(0);
    }
    private List<Sach>getData(String sql,String... selectionArgs){
        List<Sach>list=new ArrayList<>();
        Cursor cursor=db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            Sach sach=new Sach();
            sach.maSach=Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach")));
            sach.tenSach=cursor.getString(cursor.getColumnIndex("tenSach"));
            sach.giaThue=Integer.parseInt(cursor.getString(cursor.getColumnIndex("giaThue")));
            list.add(sach);
        }
        return  list;
    }
}
