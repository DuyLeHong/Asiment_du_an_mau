package com.example.asiment_du_an_mau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asiment_du_an_mau.dao.ThuThuDAO;
import com.example.asiment_du_an_mau.frament.AddUserFragment;
import com.example.asiment_du_an_mau.frament.ChangePassFragment;
import com.example.asiment_du_an_mau.frament.DoanhThuFragment;
import com.example.asiment_du_an_mau.frament.Loai_Sach_Fragment;
import com.example.asiment_du_an_mau.frament.PhieuMuonFragment;
import com.example.asiment_du_an_mau.frament.SachFragment;
import com.example.asiment_du_an_mau.frament.Thanh_Vien_Fragment;
import com.example.asiment_du_an_mau.frament.TopFragment;
import com.example.asiment_du_an_mau.model.PhieuMuon;
import com.example.asiment_du_an_mau.model.Top;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import static com.google.android.material.navigation.NavigationView.*;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;
    View mHeaderView;
    ThuThuDAO thuThuDAO;
    TextView edUser;
    ArrayList<Itemmenu> arrayList;
//    Menuadapter menuadapter;
    FrameLayout fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        AnhXa();


       //  set tool bar thay thế cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab=getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_action_menu);
       ab.setDisplayHomeAsUpEnabled(true);
        FragmentManager manager=getSupportFragmentManager();
//        PhieuMuonFragment phieuMuonFragment=new PhieuMuonFragment();
//        manager.beginTransaction().replace(R.id.flConten,phieuMuonFragment).commit();
        NavigationView nv=findViewById(R.id.navigationview);
        //show user is header
        mHeaderView=nv.getHeaderView(0);
        edUser=mHeaderView.findViewById(R.id.tvUSer);
        Intent i=getIntent();
        String user=i.getStringExtra("user");

        edUser.setText("Welcome"+user+"!");
        // admin có quyền add user
        if (user.equalsIgnoreCase("admin")){
            nv.getMenu().findItem(R.id.sub_addUser).setVisible(true);
        }
        nv.setNavigationItemSelectedListener(item -> {
            FragmentManager manager1=getSupportFragmentManager();
            switch (item.getItemId()){
                case R.id.nav_PhieuMuon:
                    setTitle("Quản Lý Phiếu Mượn");
                    PhieuMuonFragment phieuMuonFragment1=new PhieuMuonFragment();
                    manager1.beginTransaction().replace(R.id.flConten,phieuMuonFragment1).commit();
                    break;
                case R.id.nav_LoaiSach:
                    setTitle("Quản Lý Loại Sách");
                    Loai_Sach_Fragment loai_sach_fragment=new Loai_Sach_Fragment();
                    manager1.beginTransaction().replace(R.id.flConten,loai_sach_fragment).commit();
                    break;
                case R.id.nav_Sach:
                    setTitle("Quản Lý Sách");
                    SachFragment sachFragment=new SachFragment();
                    manager1.beginTransaction().replace(R.id.flConten,sachFragment).commit();
//                    SachFragment sachFragment=new SachFragment();
//                    manager1.beginTransaction().replace(R.id.flConten,sachFragment).commit();
                    break;
                case  R.id.nav_ThanhVien:
                    setTitle("Quản Lý Thành Viên");
                    Thanh_Vien_Fragment thanh_vien_fragment=new Thanh_Vien_Fragment();
                    manager1.beginTransaction().replace(R.id.flConten,thanh_vien_fragment).commit();
                    break;
                case R.id.sub_top:
                    setTitle("Top 10 sách cho thuê nhiều nhất");
                    TopFragment topFragment=new TopFragment();
                    manager1.beginTransaction().replace(R.id.flConten,topFragment).commit();
                    break;
                case R.id.sub_doanhthu:
                    setTitle("Thống kê doanh thu");
                    DoanhThuFragment doanhThuFragment=new DoanhThuFragment();
                    manager1.beginTransaction().replace(R.id.flConten,doanhThuFragment).commit();
                    break;
                case R.id.sub_addUser:
                    setTitle("Thêm người dùng");
                    AddUserFragment addUserFragment=new AddUserFragment();
                    manager1.beginTransaction().replace(R.id.flConten,addUserFragment).commit();
                    break;
                    case R.id.sub_Pass:
                        setTitle("Thay đổi mật khẩu");
                        ChangePassFragment changePassFragment=new ChangePassFragment();
                        manager1.beginTransaction().replace(R.id.flConten,changePassFragment).commit();
                        break;
                case R.id.sub_logout:
                    startActivity(new Intent(getApplicationContext(),LogInActivity.class));
                    finish();
                    break;

            }
            drawerLayout.closeDrawers();
            return  false;
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    
    private void AnhXa(){
        fragment=findViewById(R.id.flConten);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigationview);
        listView=findViewById(R.id.lv);
    }
}