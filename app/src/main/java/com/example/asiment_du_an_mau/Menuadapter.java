package com.example.asiment_du_an_mau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Menuadapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Itemmenu> list;

    public Menuadapter(Context context, int layout, List<Itemmenu> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView tv;
        ImageView im;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(layout,null);
            viewHolder=new ViewHolder();
            viewHolder.tv=convertView.findViewById(R.id.tvitem);
            viewHolder.im=convertView.findViewById(R.id.imgicon);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(list.get(position).tenMenu);
        viewHolder.im.setImageResource(list.get(position).icon);

        return convertView;
    }
}
