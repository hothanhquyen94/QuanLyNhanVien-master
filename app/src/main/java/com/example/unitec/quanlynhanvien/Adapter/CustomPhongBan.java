package com.example.unitec.quanlynhanvien.Adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.unitec.quanlynhanvien.DTO.PhongBanDTO;
import com.example.unitec.quanlynhanvien.R;

import java.util.List;

/**
 * Created by QuyenHT on 11/1/2017.
 */

public class CustomPhongBan extends ArrayAdapter<PhongBanDTO> {
    Context context;
    int resource;
    List<PhongBanDTO> objects;

    public CustomPhongBan( Context context, int resource, List<PhongBanDTO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_listview_phongban,parent,false);
        TextView TenPhongBan = (TextView)view.findViewById(R.id.viewPhongBan);
        TextView MaPhongBan = (TextView)view.findViewById(R.id.maPhongBan);
        TextView SoNhanVien = (TextView)view.findViewById(R.id.soNhanVien);

        PhongBanDTO phongBanDTO = objects.get(position);
        TenPhongBan.setText("Tên Phòng Ban: "+phongBanDTO.getTenPhongBan().toString());
        MaPhongBan.setText("Mã Phòng Ban: "+String.valueOf(phongBanDTO.getMaPhongBan()));
        SoNhanVien.setText("Số Nhân Viên: "+String.valueOf(phongBanDTO.getSoNhanVien()));

        return view;
    }
}
