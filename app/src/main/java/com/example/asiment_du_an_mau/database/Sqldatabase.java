package com.example.asiment_du_an_mau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sqldatabase extends SQLiteOpenHelper {
    public static final String dbName = "PNLIB";
    public static final int dbVersion = 2;

    public Sqldatabase(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThuThu = "create table ThuThu(" +
                "maTT TEXT not null  PRIMARY KEY," +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL)";
        db.execSQL(createTableThuThu);
        db.execSQL("insert into ThuThu Values ('admin', 'duyle', '123456')");

        // DATA THÀNH VIÊN
        String createTableThanhVien = "create table ThanhVien(" +
                "maTV INTEGER not null PRIMARY KEY AUTOINCREMENT," +
                "hoTen TEXT NOT NULL," +
                "namSinh TEXT NOT NULL)";
        db.execSQL(createTableThanhVien);
        db.execSQL("insert into ThanhVien (hoten, namsinh) values ('Tuan Long', '2002')");
        db.execSQL("insert into ThanhVien (hoten, namsinh) values ('Minh Anh', '2003')");


        //data loại sách
        String createTableLoaiSach = "create table LoaiSach(" +
                "maLoai INTEGER not null PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableLoaiSach);

        db.execSQL("insert into LoaiSach (tenLoai) values ('Tieu thuyet')");
        db.execSQL("insert into LoaiSach (tenLoai) values ('Giao khoa')");
        db.execSQL("insert into LoaiSach (tenLoai) values ('Truyen tranh')");

        //data sách
        String createTableSach = "create table Sach(" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSach TEXT NOT NULL," +
                "giaThue INTEGER NOT NULL," +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(createTableSach);

        db.execSQL("insert into Sach (tenSach, giaThue, maLoai) values ('Song o day song', 20000, 1)");
        db.execSQL("insert into Sach (tenSach, giaThue, maLoai) values ('Rung xa nu', 30000, 1)");
        db.execSQL("insert into Sach (tenSach, giaThue, maLoai) values ('Toan lop 1', 10000, 2)");
        db.execSQL("insert into Sach (tenSach, giaThue, maLoai) values ('Toan lop 2', 15000, 2)");

        // data phiếu mượn
        String createTablePhieuMuon = "create table PhieuMuon(" +
                "maPH  INTEGER not null PRIMARY KEY AUTOINCREMENT," +
                "maTT TEXT not null REFERENCES ThuThu(maTT)," +
                "maTV INTEGER not null REFERENCES ThanhVien(maTV)," +
                "maSach Integer not null REFERENCES Sach(maSach)," +
                "tienThue Integer NOT NULL," +
                "ngay TEXT NOT NULL," +
                "traSach integer NOT NULL)";
        db.execSQL(createTablePhieuMuon);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion != oldVersion) {
            String dropTableThuThu = "drop table if exists ThuThu";
            db.execSQL(dropTableThuThu);
            String dropTableThanhVien = "drop table if exists ThanhVien";
            db.execSQL(dropTableThanhVien);
            String dropTableLoaiSach = "drop table if exists LoaiSach";
            db.execSQL(dropTableLoaiSach);
            String dropTableSach = "drop table if exists Sach";
            db.execSQL(dropTableSach);
            String dropTablePhieuMuon = "drop table if exists PhieuMuon";
            db.execSQL(dropTablePhieuMuon);

            onCreate(db);
        }

    }
}
