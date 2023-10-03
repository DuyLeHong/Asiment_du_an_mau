package com.example.asiment_du_an_mau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asiment_du_an_mau.R;
import com.example.asiment_du_an_mau.frament.Loai_Sach_Fragment;
import com.example.asiment_du_an_mau.frament.Thanh_Vien_Fragment;
import com.example.asiment_du_an_mau.model.LoaiSach;
import com.example.asiment_du_an_mau.model.ThanhVien;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    Loai_Sach_Fragment fragment;
    private ArrayList<LoaiSach>lists;
    TextView tvMaLS,tvTenLS;
    ImageView imgDel;

    public LoaiSachAdapter(@NonNull Context context, Loai_Sach_Fragment fragment, ArrayList<LoaiSach> lists) {
        super(context,0,lists);
        this.fragment = fragment;
        this.lists = lists;
        this.context=context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
            v=inflater.inflate(R.layout.loaisach_item,null);
        }
        final LoaiSach iten=lists.get(position);
        if (iten !=null){
            tvMaLS=v.findViewById(R.id.tvMaLS);
            tvTenLS=v.findViewById(R.id.tvTenLS);

            imgDel=v.findViewById(R.id.imgDeleteLS);
            tvMaLS.setText("Mã Loại Sách"+iten.maLoai);
            tvTenLS.setText("Tên Loại Sách"+iten.tenLoai);

        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(iten.maLoai));
            }
        });
        return v;
    }
}
