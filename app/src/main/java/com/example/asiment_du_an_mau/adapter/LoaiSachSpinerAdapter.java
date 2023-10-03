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
import com.example.asiment_du_an_mau.model.LoaiSach;

import java.util.ArrayList;

public class LoaiSachSpinerAdapter extends ArrayAdapter<LoaiSach> {
    private  Context context;
    private ArrayList<LoaiSach> lists;
    TextView tvMALoaiSp,tvTenLoaiSp;
    public LoaiSachSpinerAdapter(@NonNull Context context, int resource,ArrayList<LoaiSach>lists) {
        super(context, 0,lists);
        this.context=context;
        this.lists=lists;
    }

    public LoaiSachSpinerAdapter(Context context, ArrayList<LoaiSach> listloaisach) {
        super(context,0,listloaisach);
        this.context=context;
        this.lists=listloaisach;

    }

//    public LoaiSachSpinerAdapter(Context context, ArrayList<LoaiSach> listloaisach) {
//        super();
//    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaisach_item_spiner,null,false);
        }
        final LoaiSach item=lists.get(position);
        if (item==null){
            tvMALoaiSp=v.findViewById(R.id.tvMALoaiSp);
            tvTenLoaiSp=v.findViewById(R.id.tvTenLoaiSp);
            tvMALoaiSp.setText(item.maLoai);
            tvTenLoaiSp.setText(item.tenLoai);
                    }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaisach_item_spiner,null,false);
        }
        final LoaiSach item=lists.get(position);
        if (v==null){
            tvTenLoaiSp=v.findViewById(R.id.tvTenLoaiSp);
            tvMALoaiSp=v.findViewById(R.id.tvMALoaiSp);
            tvMALoaiSp.setText(item.maLoai);
            tvTenLoaiSp.setText(item.tenLoai);
        }
        return v;
    }
}
