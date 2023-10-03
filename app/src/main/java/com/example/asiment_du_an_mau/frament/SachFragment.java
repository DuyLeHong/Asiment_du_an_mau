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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asiment_du_an_mau.R;
import com.example.asiment_du_an_mau.adapter.LoaiSachSpinerAdapter;
import com.example.asiment_du_an_mau.adapter.PhieuMuonAdapter;
import com.example.asiment_du_an_mau.adapter.SachAdapter;
import com.example.asiment_du_an_mau.adapter.SachSpinerAdapter;
import com.example.asiment_du_an_mau.dao.LoaiSachDAO;
import com.example.asiment_du_an_mau.dao.SachDAO;
import com.example.asiment_du_an_mau.model.LoaiSach;
import com.example.asiment_du_an_mau.model.PhieuMuon;
import com.example.asiment_du_an_mau.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;


public class SachFragment extends Fragment {
    TextInputEditText tenSach,giaThue;
    ListView lv;
    Dialog dialog;
    View view;
    Sach sach;
    int maloaiSach;
    static SachDAO sachDAO;
    ArrayList<Sach>lists;
    LoaiSachSpinerAdapter loaiSachSpinerAdapter;
    ArrayList<LoaiSach>listloaisach;
    SachAdapter adapter;
    LoaiSachDAO loaiSachDAO;
    Button ok,del;
    FloatingActionButton fab;

    int positionLS;
    SachSpinerAdapter sachSpinerAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sach,container,false);
        lv=view.findViewById(R.id.lvsach);
        fab=view.findViewById(R.id.fab);
        sachDAO=new SachDAO(getActivity());
        capNhatlv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lv.setOnItemLongClickListener((parent, view1, position, id) -> {
            sach=lists.get(position);
            openDialog(getActivity(),1);
            return false;
        });

        // Inflate the layout for this fragment
        return view;
    }
    protected void openDialog(Context context,final  int type){
        dialog=new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        EditText edMaSach =dialog.findViewById(R.id.edMaSach);
        Spinner spMaSach=dialog.findViewById(R.id.spMaSach);
        TextView tvTenSach=dialog.findViewById(R.id.tvTenSach);
        TextView tvGiaThue=dialog.findViewById(R.id.tvGiaThue);
        Button btnSavePM=dialog.findViewById(R.id.btnSavePM);
        Button btnCancelPM=dialog.findViewById(R.id.btnCancelPM);
        loaiSachDAO=new LoaiSachDAO(context);
        listloaisach= (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        loaiSachSpinerAdapter=new LoaiSachSpinerAdapter(context,listloaisach);
        spMaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maloaiSach=listloaisach.get(position).maLoai;
                Toast.makeText(context, "Chọn"+listloaisach.get(position).tenLoai, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
      edMaSach.setEnabled(false);
      if (type!=0){
          edMaSach.setText(String.valueOf(sach.maSach));
          for (int i=0;i<lists.size();i++){
              if (sach.maLoai==(lists.get(i).maLoai)){
                  positionLS=i;
              }spMaSach.setSelection(positionLS);
              tvTenSach.setText("Tên Sách:"+sach.tenSach);
              tvGiaThue.setText("Tiền thuê:"+sach.giaThue);
          }

      }
      btnCancelPM.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              dialog.dismiss();
          }
      });
      btnSavePM.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              sach=new Sach();
//              sach.maSach=maloaiSach;
              sach.maLoai=maloaiSach;

              if (validate()>0){
                  if (type==0){
                      if (sachDAO.insert(sach)>0){
                          Toast.makeText(context,"Thêm thành công",Toast.LENGTH_LONG).show();

                      }
                      else {
                          Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_LONG).show();
                      }
                  }
                  else {
                      sach.maLoai=Integer.parseInt(edMaSach.getText().toString());
                      if (sachDAO.update(sach)>0){
                          Toast.makeText(context,"Sửa thành công",Toast.LENGTH_LONG).show();

                      }
                      else {
                          Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
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
                sachDAO.delete(Id);
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
        lists= (ArrayList<Sach>) sachDAO.getAll();
        adapter=new SachAdapter(getActivity(),this,lists);
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