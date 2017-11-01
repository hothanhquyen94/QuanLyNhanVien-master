package com.example.unitec.quanlynhanvien.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Unitec on 31/10/2017.
 */

public class Database extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "QLNhanVien";
    private static int DATABASE_VERSION = 1;

    public static String TABLE_NHANVIEN ="NhanVien";
    public static String MaNV_NHANVIEN ="MaNV";
    public static String TenNV_NHANVIEN ="TenNV";
    public static String SDT_NHANVIEN ="SoDT";
    public static String GioiTinhNV_NHANVIEN ="GioiTinh";
    public static String DiaChi_NHANVIEN ="DiaChi";
    public static String EmailNV_NHANVIEN ="Email";
    public static String LuongNV_NHANVIEN ="Luong";



    public static String TABLE_PHONGBAN ="PhongBan";
    public static String MaPB_PHONGBAN ="MaPB";
    public static String TenPB_PHONGBAN ="TenPB";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String taoBangNhanVien = " create table " + TABLE_NHANVIEN+" ( "+
                MaNV_NHANVIEN+ " Integer primary key autoincrement, " +
                TenNV_NHANVIEN + " text, " +
                SDT_NHANVIEN +" text,"+
                GioiTinhNV_NHANVIEN +" text, "+
                DiaChi_NHANVIEN +" text, "+
                EmailNV_NHANVIEN +" text, "+
                LuongNV_NHANVIEN +" Integer, "+
                MaPB_PHONGBAN + " integer Constraint PK_MAPB_NhanVien References PhongBan( MaPB ) on delete cascade );";
        String taoBangPhongBan =  " create table " + TABLE_PHONGBAN
                +"("
                + MaPB_PHONGBAN+" Integer primary key autoincrement, "
                + TenPB_PHONGBAN + " text );";

        sqLiteDatabase.execSQL(taoBangPhongBan);
        sqLiteDatabase.execSQL(taoBangNhanVien);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("crop table if exists "+TABLE_PHONGBAN);
        sqLiteDatabase.execSQL("crop table if exists "+TABLE_NHANVIEN);
    }
}
