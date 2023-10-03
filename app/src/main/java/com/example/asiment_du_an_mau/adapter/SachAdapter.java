package com.example.asiment_du_an_mau.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asiment_du_an_mau.R;
import com.example.asiment_du_an_mau.dao.LoaiSachDAO;
import com.example.asiment_du_an_mau.dao.SachDAO;
import com.example.asiment_du_an_mau.dao.ThanhVienDAO;
import com.example.asiment_du_an_mau.frament.PhieuMuonFragment;
import com.example.asiment_du_an_mau.frament.SachFragment;
import com.example.asiment_du_an_mau.model.LoaiSach;
import com.example.asiment_du_an_mau.model.PhieuMuon;
import com.example.asiment_du_an_mau.model.Sach;
import com.example.asiment_du_an_mau.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    Button btnDel;
    SachDAO sachDAO;
    ArrayList<Sach> lists;
    LoaiSachDAO loaiSachDAO;
    SachFragment fragment;
    TextView tvmasach, tvqlsach_maloai, tvqlsach_tensach, tvqlsach_tienthue;

    public SachAdapter(Context context, SachFragment fragment, ArrayList<Sach> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;

    }

    @Override
    public int getCount() {
        if (lists == null) {
            return 0;
        } else {
            return lists.size();
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sach_item, null);
        }
        final Sach item = lists.get(position);

        Sach sach = lists.get(position);
        tvmasach = view.findViewById(R.id.qlsach_masach);
        tvqlsach_maloai = view.findViewById(R.id.qlsach_maloai);
        tvqlsach_tensach = view.findViewById(R.id.qlsach_tensach);
        tvqlsach_tienthue = view.findViewById(R.id.qlsach_tienthue);
        btnDel = view.findViewById(R.id.btn_delete);


        tvmasach.setText(sach.maSach + "");
        tvqlsach_maloai.setText(sach.maLoai + "");
        tvqlsach_tensach.setText(sach.tenSach + "");
        tvqlsach_tienthue.setText(sach.giaThue + "");

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maSach));
            }
        });


        return view;
    }
}

