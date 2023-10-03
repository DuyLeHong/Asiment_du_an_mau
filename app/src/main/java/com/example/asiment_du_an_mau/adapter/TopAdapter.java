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
import com.example.asiment_du_an_mau.frament.TopFragment;
import com.example.asiment_du_an_mau.model.Top;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment fragment;
    private ArrayList<Top> lists;
    TextView tvSach,tvSL;
    ImageView imgdel;
    public TopAdapter(@NonNull Context context, TopFragment fragment,ArrayList<Top> lists) {
        super(context, 0,lists);
        this.context=context;
        this.lists=lists;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v=convertView;
        if (v==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.top_item,null);
        }
        final Top item=lists.get(position);
        if (item!=null){
            tvSach=v.findViewById(R.id.tvSach);
            tvSach.setText("Sách:   "+item.tenSach);
            tvSL=v.findViewById(R.id.tvSoLuong);
            tvSL.setText("Số Lượng:  "+item.soLuong);
        }
        return v;
    }
}
