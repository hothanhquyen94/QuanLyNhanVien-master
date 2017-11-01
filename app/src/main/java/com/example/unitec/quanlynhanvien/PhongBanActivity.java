package com.example.unitec.quanlynhanvien;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.system.ErrnoException;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.unitec.quanlynhanvien.Adapter.CustomPhongBan;
import com.example.unitec.quanlynhanvien.DAO.PhongBanDAO;
import com.example.unitec.quanlynhanvien.DTO.PhongBanDTO;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhongBanActivity extends AppCompatActivity {
    ListView listPhongBan;
    LinearLayout linearLayout;
    EditText txtThemPhongBan;
    Button btnThemPhongBan;
    PhongBanDAO dbPhongBan;
    CustomPhongBan adapterCustom;
    List<PhongBanDTO> DanhSach_phongBanDTOs;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban);
        listPhongBan = (ListView) findViewById(R.id.listPhongBan);
        linearLayout = (LinearLayout)findViewById(R.id.layout_phongban) ;


        registerForContextMenu(listPhongBan);
        registerForContextMenu(linearLayout);
        dbPhongBan = new PhongBanDAO(this);
        loadListViewPhongBan();

        listPhongBan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                return false;
            }
        });


    }
    public void loadListViewPhongBan(){
        DanhSach_phongBanDTOs = new ArrayList<PhongBanDTO>();
        DanhSach_phongBanDTOs= dbPhongBan.AllPhongBan();
        adapterCustom = new CustomPhongBan(this,R.layout.custom_listview_phongban,DanhSach_phongBanDTOs);
        listPhongBan.setAdapter(adapterCustom);

    }

    /**
     * Menu click vào list view danh sach phong ban de chinh sua danh sach phòng ban
     *
     * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_chucnang,menu);
        menu.setHeaderTitle("TÙY CHỌN");
        menu.setHeaderIcon(R.drawable.system);
        if(v.getId()==R.id.listPhongBan){
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(false);
        }
        if (v.getId() == R.id.layout_phongban){
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(false);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menuXoaPhongBan:
                int maPhongBan = DanhSach_phongBanDTOs.get(position).getMaPhongBan();
                int check = dbPhongBan.XoaPhongBan(maPhongBan);
                if(check != -1){
                    Toast.makeText(getApplicationContext(),"DELETE SUCCESS!",Toast.LENGTH_LONG).show();
                    loadListViewPhongBan();
                }else {
                    Toast.makeText(getApplicationContext(),"DELETE ERROR!",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuThemPhongBan:
                final Dialog dialog = new Dialog(this);
                dialog.setTitle("THÊM PHÒNG BAN");
                dialog.setContentView(R.layout.layout_dangki);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.themphongban_layout);
                txtThemPhongBan = (EditText)dialog.findViewById(R.id.textPersonnal);
                btnThemPhongBan =(Button)dialog.findViewById(R.id.btnThemPhongBan);
                dialog.show();

                btnThemPhongBan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PhongBanDTO phongBanDTO = new PhongBanDTO();
                        phongBanDTO.setTenPhongBan(txtThemPhongBan.getText().toString());
                        dbPhongBan.themPhongBan(phongBanDTO);
                        loadListViewPhongBan();
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"ADD STAFF SUCCESS!",Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.menuSuaPhongBan:
                final Dialog dialogSua = new Dialog(this);
                dialogSua.setTitle("SỬA PHÒNG BAN");
                dialogSua.setContentView(R.layout.suaphongban_layout);
                dialogSua.show();
                final EditText editSuaTen = (EditText)dialogSua.findViewById(R.id.suaTenPB);
                EditText editMaPBsua = (EditText)dialogSua.findViewById(R.id.suaMaPB);
                Button btnSua = (Button)dialogSua.findViewById(R.id.dongySua);
                editMaPBsua.setEnabled(false);
                editMaPBsua.setText(String.valueOf(DanhSach_phongBanDTOs.get(position).getMaPhongBan()));
                btnSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PhongBanDTO phongBanDTO = new PhongBanDTO();
                        phongBanDTO.setTenPhongBan(editSuaTen.getText().toString());
                        phongBanDTO.setMaPhongBan((DanhSach_phongBanDTOs.get(position).getMaPhongBan()));
                        if (dbPhongBan.SuaPhongBan(phongBanDTO) != -1){
                            Toast.makeText(getApplicationContext(),"UPDATE SUCCESS!",Toast.LENGTH_LONG).show();
                            loadListViewPhongBan();
                            dialogSua.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(),"UPDATE ERROR!",Toast.LENGTH_LONG).show();
                        }

                    }
                });


                break;
        }
        return super.onContextItemSelected(item);
    }


    /**
     * Menu lua chon chế độ xem
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuphongban,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menuHeThong:
               
                break;
            case R.id.menuPhongBan:
               
                break;
            case R.id.menuLienLac:
                
                break;
            case R.id.menuNhanVien:
               
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
