package com.example.asiment_du_an_mau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asiment_du_an_mau.R;
import com.example.asiment_du_an_mau.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienSpinerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien>lists;
    TextView tvMaTV,tvTenTV;

    public ThanhVienSpinerAdapter(@NonNull Context context, ArrayList<ThanhVien>lists) {
        super(context,0,lists);
        this.context=context;
        this.lists=lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanh_vien_item_spiner,null,false);
        }
        final ThanhVien item=lists.get(position);
        if (item !=null){
            tvMaTV=v.findViewById(R.id.tvMATVSp);
            tvTenTV=v.findViewById(R.id.tvTenTVSp);
            tvMaTV.setText(item.maTV+".");
            tvTenTV.setText(item.hoTen);
        }
        return v;
    }
    // khi người dùng click spiner hiện thị lên

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.thanh_vien_item_spiner,null,false);
        }
        final ThanhVien item=lists.get(position);
        if (item !=null){
            tvMaTV=v.findViewById(R.id.tvMATVSp);
            tvTenTV=v.findViewById(R.id.tvTenTVSp);
            tvMaTV.setText(item.maTV+".");
            tvTenTV.setText(item.hoTen);
        }
        return v;
    }
}
