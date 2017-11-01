package com.example.unitec.quanlynhanvien;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Button btnDangKi,btnThoatDK,btnDangNhap,btnThoatDN;
    EditText editUser,editPass,editUserDK,editPassDK,editPassDKagain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("Configuration", Context.MODE_PRIVATE);
        editUser = (EditText)findViewById(R.id.txtuser);
        editPass = (EditText)findViewById(R.id.txtpass);

    }
    // Đăng nhập tài khoản
    public void DangNhap(View view){
        String matkhau_config,taikhoan_config,taikhoanDN,matkhauDN;
        taikhoan_config = sharedPreferences.getString("TaiKhoan","");
        matkhau_config =sharedPreferences.getString("MatKhau","");
        taikhoanDN = editUser.getText().toString();
        matkhauDN = editPass.getText().toString();

        if (taikhoanDN.equals(taikhoan_config) && matkhauDN.equals(matkhau_config)){
            Toast.makeText(this,"ĐĂNG NHẬP THÀNH CÔNG",Toast.LENGTH_LONG).show();
            Intent intentPhongBan = new Intent(this,PhongBanActivity.class);
            startActivity(intentPhongBan);
        }else {
            Toast.makeText(this,"ĐĂNG NHẬP THẤT BẠI",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_content,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuDangKi){
            String taikhoan,matkhau;
            taikhoan = sharedPreferences.getString("TaiKhoan","");
            matkhau = sharedPreferences.getString("MatKhau","");

            if (taikhoan.trim().length()==0 || matkhau.trim().length()==0 ){

                final Dialog dialog = new Dialog(this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dangki);
                dialog.setTitle("ĐĂNG KÝ TÀI KHOẢN");
                dialog.setCanceledOnTouchOutside(false);
                editPassDK = (EditText) dialog.findViewById(R.id.txtdangkipass);
                editPassDKagain = (EditText) dialog.findViewById(R.id.txtdangkipass1);
                editUserDK = (EditText)dialog.findViewById(R.id.txtdangkiuser);
                btnDangKi = (Button)dialog.findViewById(R.id.btndangki);
                btnDangKi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String matkhaudk,matkhaudk1;

                        matkhaudk = editPassDK.getText().toString();
                        matkhaudk1 = editPassDKagain.getText().toString();
                        if (!matkhaudk.equals(matkhaudk1)){
                            Toast.makeText(getApplicationContext(),"Mật khẩu nhập lại không khớp",Toast.LENGTH_LONG).show();
                        }else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("MatKhau",editPassDK.getText().toString());
                            editor.putString("TaiKhoan",editUserDK.getText().toString());
                            editor.commit();
                            Toast.makeText(getApplicationContext(),"ĐĂNG KÍ THÀNH CÔNG",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            } else {
                Toast.makeText(getApplicationContext(),"Bạn không thể đăng kí! Tài khoản đã tồn tại",Toast.LENGTH_LONG).show();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
