package com.example.vinhthinhgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.activity.TrangChu;
import com.example.vinhthinhgroup.model.CongSuat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CongSuatAdapter extends BaseAdapter {
    Context context;
    ArrayList<CongSuat> arraycongSuat;

    public CongSuatAdapter(Context context, ArrayList<CongSuat> arraycongSuat) {
        this.context = context;
        this.arraycongSuat = arraycongSuat;
    }

    @Override
    public int getCount() {
        return arraycongSuat.size();
    }

    @Override
    public Object getItem(int i) {
        return arraycongSuat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttendodung, txtcsdodung, txtmaphongdd;
        public ImageView imgdodung;
        public Button btntru, btngia, btncong;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_congsuat,null);
            viewHolder.txttendodung = view.findViewById(R.id.textviewtendodungcs);
            viewHolder.txtcsdodung = view.findViewById(R.id.textviewcongsuatcs);
            viewHolder.txtmaphongdd = view.findViewById(R.id.textviewmaloaiphong);
            viewHolder.imgdodung = view.findViewById(R.id.imagecongsuatcs);
            viewHolder.btntru = view.findViewById(R.id.buttontrucs);
            viewHolder.btngia = view.findViewById(R.id.buttongiatrics);
            viewHolder.btncong = view.findViewById(R.id.buttoncongcs);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        CongSuat congSuat = (CongSuat) getItem(i);

        viewHolder.txttendodung.setText(congSuat.getTenDoDung());
        viewHolder.txtcsdodung.setText(congSuat.getCongSuat() + " W");
        if(congSuat.getMaPhong()==1){
            viewHolder.txtmaphongdd.setText("Phòng khách");
        }
        if(congSuat.getMaPhong()==2){
            viewHolder.txtmaphongdd.setText("Phòng ăn");
        }
        if(congSuat.getMaPhong()==3){
            viewHolder.txtmaphongdd.setText("Phòng ngủ");
        }
        //viewHolder.txtmaphongdd.setText(String.valueOf(congSuat.getMaPhong()));

        Picasso.with(context).load(congSuat.getHinh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.imgdodung);
        viewHolder.btngia.setText(congSuat.getSoluong() + "");
        int sl = Integer.parseInt(viewHolder.btngia.getText().toString());
        if(sl > 1){
            viewHolder.btncong.setVisibility(View.VISIBLE);
            viewHolder.btntru.setVisibility(View.VISIBLE);
        }
        else if(sl == 1){
            viewHolder.btntru.setVisibility(View.INVISIBLE);
            viewHolder.btncong.setVisibility(View.VISIBLE);
        }

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btngia.getText().toString())+1;
                int slht = TrangChu.mangcongsuat.get(i).getSoluong();
                int csht = TrangChu.mangcongsuat.get(i).getCongSuat();

                TrangChu.mangcongsuat.get(i).setSoluong(slmoinhat);
                int csmoinhat = (csht * slmoinhat)/ slht;
                TrangChu.mangcongsuat.get(i).setCongSuat(csmoinhat);

                finalViewHolder.txtcsdodung.setText(csmoinhat + " W");
                com.example.vinhthinhgroup.activity.CongSuatTong.EventUltil();
                if(slmoinhat > 9){
                    finalViewHolder.btncong.setVisibility(View.VISIBLE);
                    finalViewHolder.btntru.setVisibility(View.VISIBLE);
                    finalViewHolder.btngia.setText(String.valueOf(slmoinhat));
                }
                else{
                    finalViewHolder.btncong.setVisibility(View.VISIBLE);
                    finalViewHolder.btntru.setVisibility(View.VISIBLE);
                    finalViewHolder.btngia.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder.btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btngia.getText().toString())-1;
                int slht = TrangChu.mangcongsuat.get(i).getSoluong();
                int csht = TrangChu.mangcongsuat.get(i).getCongSuat();

                TrangChu.mangcongsuat.get(i).setSoluong(slmoinhat);
                int csmoinhat = (csht * slmoinhat)/ slht;
                TrangChu.mangcongsuat.get(i).setCongSuat(csmoinhat);

                finalViewHolder.txtcsdodung.setText(csmoinhat + " W");
                com.example.vinhthinhgroup.activity.CongSuatTong.EventUltil();
                if(slmoinhat < 2){
                    finalViewHolder.btntru.setVisibility(View.INVISIBLE);
                    finalViewHolder.btncong.setVisibility(View.VISIBLE);
                    finalViewHolder.btngia.setText(String.valueOf(slmoinhat));
                }
                else{
                    finalViewHolder.btncong.setVisibility(View.VISIBLE);
                    finalViewHolder.btntru.setVisibility(View.VISIBLE);
                    finalViewHolder.btngia.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;
    }
}
