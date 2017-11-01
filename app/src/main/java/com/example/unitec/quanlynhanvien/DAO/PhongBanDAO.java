package com.example.unitec.quanlynhanvien.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unitec.quanlynhanvien.DTO.PhongBanDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unitec on 01/11/2017.
 */

public class PhongBanDAO {
    Context context;
    Database databasehelper;
    SQLiteDatabase sqLiteDatabase;

    public PhongBanDAO(Context context){
        this.context = context;
        databasehelper = new Database(context);
    }

    public void themPhongBan(PhongBanDTO phongBanDTO){
        sqLiteDatabase = databasehelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.TenPB_PHONGBAN,phongBanDTO.getTenPhongBan());
        sqLiteDatabase.insert(Database.TABLE_PHONGBAN,null,contentValues);
        sqLiteDatabase.close();
    }
    public List<PhongBanDTO> AllPhongBan(){
        List<PhongBanDTO> list = new ArrayList<PhongBanDTO>();
        sqLiteDatabase = databasehelper.getWritableDatabase();
        String sql = " select * from "+Database.TABLE_PHONGBAN;

        Cursor cursor =  sqLiteDatabase.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            PhongBanDTO phongBanDTO = new PhongBanDTO();
            phongBanDTO.setTenPhongBan(cursor.getString(cursor.getColumnIndex(Database.TenPB_PHONGBAN)));
            phongBanDTO.setMaPhongBan(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Database.MaPB_PHONGBAN))));
            list.add(phongBanDTO);
            cursor.moveToNext();
        }
        return list;
    }

    public int XoaPhongBan(int id){
        sqLiteDatabase = databasehelper.getWritableDatabase();
        return sqLiteDatabase.delete(Database.TABLE_PHONGBAN,Database.MaPB_PHONGBAN+"=?",new String[] {String.valueOf(id)});
    }

    public int SuaPhongBan(PhongBanDTO phongBanDTO){
        sqLiteDatabase = databasehelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Database.TenPB_PHONGBAN,phongBanDTO.getTenPhongBan());
        return sqLiteDatabase.update(Database.TABLE_PHONGBAN,
                contentValues,Database.MaPB_PHONGBAN +"=?",
                new String[]{String.valueOf(phongBanDTO.getMaPhongBan())});
    }
}
