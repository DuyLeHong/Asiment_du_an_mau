package com.example.asiment_du_an_mau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.asiment_du_an_mau.dao.ThuThuDAO;

public class LogInActivity extends AppCompatActivity {
    EditText tv_name;
    EditText tv_pass;
    Button btn_dangnhap, btn_huy;
    Intent intent;
    ThuThuDAO thuthuDao;
    CheckBox checkBox;
    String strUser, strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        tv_name = findViewById(R.id.id_name);
        tv_pass = findViewById(R.id.id_pass);
        btn_dangnhap = findViewById(R.id.btndangnhap);
        btn_huy = findViewById(R.id.btnhuy);
        checkBox = findViewById(R.id.checkboxpass);
        thuthuDao = new ThuThuDAO(this);
        // đọc user và pass
        SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        tv_name.setText(preferences.getString("USERNAME", ""));
        tv_pass.setText(preferences.getString("PASSWORD", ""));
        checkBox.setChecked(preferences.getBoolean("REMEMBER", false));
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_name.setText("");
                tv_pass.setText("");
            }
        });
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();

            }
        });

    }

    public void checkLogin() {
        strUser = tv_name.getText().toString();
        strPass = tv_pass.getText().toString();
        if (strUser.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tên Đăng Nhập Và Mật Khẩu Không Được Bỏ Trống",
                    Toast.LENGTH_LONG).show();
        } else {
            if (thuthuDao.checkLogin(strUser, strPass) > 0 || (strUser.equals("admin") && strPass.equals("admin"))) {
                Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_LONG).show();
                rememberUser(strUser, strPass, checkBox.isChecked());
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", strUser);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Tên Đăng Nhập Hoặc Mật Khẩu Không Đúng", Toast.LENGTH_LONG).show();

            }
        }
    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status) {
            // xóa tính năng lưu chữ trước đó
            editor.clear();
        } else {
            //Lưu dữ liệu
            editor.putString("USERNAME", u);
            editor.putString("PASSWORD", p);
            editor.putBoolean("REMEMBER", status);

        }
        //lưu dữ liệu toàn bộ
        editor.commit();
    }
}