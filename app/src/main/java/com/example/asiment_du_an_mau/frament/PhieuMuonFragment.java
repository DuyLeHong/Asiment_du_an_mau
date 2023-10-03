package com.example.asiment_du_an_mau.frament;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asiment_du_an_mau.R;
import com.example.asiment_du_an_mau.adapter.LoaiSachAdapter;
import com.example.asiment_du_an_mau.adapter.PhieuMuonAdapter;
import com.example.asiment_du_an_mau.adapter.SachSpinerAdapter;
import com.example.asiment_du_an_mau.adapter.ThanhVienSpinerAdapter;
import com.example.asiment_du_an_mau.dao.PhieuMuonDAO;
import com.example.asiment_du_an_mau.dao.SachDAO;
import com.example.asiment_du_an_mau.dao.ThanhVienDAO;
import com.example.asiment_du_an_mau.model.LoaiSach;
import com.example.asiment_du_an_mau.model.PhieuMuon;
import com.example.asiment_du_an_mau.model.Sach;
import com.example.asiment_du_an_mau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class PhieuMuonFragment extends Fragment {
ListView lv;
ArrayList<PhieuMuon>list;
FloatingActionButton fab;
Dialog dialog;
EditText edMaPM;
Spinner spTV,spSach;
TextView tvNgay,tvTienThue;
CheckBox chkTraSach;
Button btnSave,btnCancel;
static PhieuMuonDAO dao;
PhieuMuonAdapter adapter;
PhieuMuon item;
ThanhVienSpinerAdapter thanhVienSpinerAdapter;
ArrayList<ThanhVien> listThanhvien;
ThanhVienDAO thanhVienDAO;
ThanhVien thanhVien;
int maThanhVien;
SachSpinerAdapter sachSpinerAdapter;
ArrayList<Sach>listSach;
SachDAO sachDAO;
Sach sach;
int maSach,tienThue;
int positionTV,positionSach;
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_phieu_muon,container,false);
        lv=v.findViewById(R.id.lvPhieuMuon);
        fab=v.findViewById(R.id.fab);
        dao=new PhieuMuonDAO(getActivity());
        capNhatlv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lv.setOnItemLongClickListener((parent, view, position, id) -> {
            item=list.get(position);
            openDialog(getActivity(),1);
            return false;

        });
        // Inflate the layout for this fragment
        return v;
    }
    protected  void  openDialog(Context context,final int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.phieumuon_dialog);
        edMaPM=dialog.findViewById(R.id.edMaPM);
        spTV=dialog.findViewById(R.id.spMaTV);
        spSach=dialog.findViewById(R.id.spMaSach);
        tvNgay=dialog.findViewById(R.id.tvNgay);
        tvTienThue=dialog.findViewById(R.id.tvTienThue);
        chkTraSach=dialog.findViewById(R.id.chkTraSach);
        btnCancel=dialog.findViewById(R.id.btnCancelPM);
        btnSave=dialog.findViewById(R.id.btnSavePM);
        thanhVienDAO=new ThanhVienDAO(context);
        listThanhvien= (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinerAdapter=new ThanhVienSpinerAdapter(context,listThanhvien);
        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien=listThanhvien.get(position).maTV;
                Toast.makeText(context,"Chọn"+listThanhvien.get(position).hoTen,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDAO=new SachDAO(context);
        listSach=new ArrayList<Sach>();
        listSach= (ArrayList<Sach>) sachDAO.getAll();
        spSach.setAdapter(sachSpinerAdapter);
        //lấy masach
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach=listSach.get(position).maSach;
                tienThue=listSach.get(position).giaThue;
                Toast.makeText(context,"Chọn"+listSach.get(position).tenSach,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMaPM.setEnabled(false);
        if (type!=0){
            edMaPM.setText(String.valueOf(item.maPH));
            for (int i=0;i<listThanhvien.size();i++){
                if (item.maTV==(listThanhvien.get(i).maTV)){
                    positionTV=i;
                }spTV.setSelection(positionTV);
            }
            for (int i=0;i<listSach.size();i++)
                if (item.maSach==(listSach.get(i).maSach)){
                    positionSach=i;
                }spSach.setSelection(positionSach);
                tvNgay.setText("Ngày Thuê:"+sdf.format(item.ngay));
                tvTienThue.setText("Tiền Thuê:"+item.tienThue);
                if (item.traSach==1){
                    chkTraSach.setChecked(true);
                }
                else {
                    chkTraSach.setChecked(false);
                }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item=new PhieuMuon();
                item.maSach=maSach;
                item.maTV=maThanhVien;
               // item.ngay=sdf.format(new Date());
                item.tienThue=tienThue;
                if (chkTraSach.isChecked()){
                    item.traSach=1;
                }
                else {
                    item.traSach=0;
                }
                if (validate()>0){
                    if (type==0){
                        if (dao.insert(item)>0){
                            Toast.makeText(context,"Thêm thành công",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        item.maPH=Integer.parseInt(edMaPM.getText().toString());
                        if (dao.update(item)>0){
                            Toast.makeText(context,"Sửa thành công",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_LONG).show();
                        }
                    }
                    capNhatlv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatlv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog=builder.create();
        builder.show();
    }
    void capNhatlv(){
        list= (ArrayList<PhieuMuon>) dao.getAll();
//        list= (ArrayList<PhieuMuon>) dao.getAll();
        adapter=new PhieuMuonAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check=1;
//        if (ed.getText().length()==0){
//            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();
//            check=-1;
//        }
        return  check;
    }
}