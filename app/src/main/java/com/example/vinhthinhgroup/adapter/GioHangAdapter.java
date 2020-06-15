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
import com.example.vinhthinhgroup.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arraygiohang;

    public GioHangAdapter(Context context, ArrayList<GioHang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txttengiohang, txtgiamonhang;
        public ImageView imggiohang;
        public Button btntru, btngia, btncong;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txttengiohang = view.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiamonhang = view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang = view.findViewById(R.id.imagegiohang);
            viewHolder.btntru = view.findViewById(R.id.buttontru);
            viewHolder.btngia = view.findViewById(R.id.buttongiatri);
            viewHolder.btncong = view.findViewById(R.id.buttoncong);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        GioHang gioHang = (GioHang) getItem(i);
        viewHolder.txttengiohang.setText(gioHang.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiamonhang.setText(decimalFormat.format(gioHang.getGia()) + " VNĐ");
        Picasso.with(context).load(gioHang.getHinh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.imggiohang);
        viewHolder.btngia.setText(gioHang.getSoluong() + "");
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
                int slht = TrangChu.manggiohang.get(i).getSoluong();
                long giaht = TrangChu.manggiohang.get(i).getGia();

                TrangChu.manggiohang.get(i).setSoluong(slmoinhat);
                long giamoinhat = (giaht * slmoinhat)/ slht;
                TrangChu.manggiohang.get(i).setGia(giamoinhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtgiamonhang.setText(decimalFormat.format(giamoinhat) + " VNĐ");
                com.example.vinhthinhgroup.activity.GioHang.EventUltil();
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
                int slht = TrangChu.manggiohang.get(i).getSoluong();
                long giaht = TrangChu.manggiohang.get(i).getGia();

                TrangChu.manggiohang.get(i).setSoluong(slmoinhat);
                long giamoinhat = (giaht * slmoinhat)/ slht;
                TrangChu.manggiohang.get(i).setGia(giamoinhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtgiamonhang.setText(decimalFormat.format(giamoinhat) + " VNĐ");
                com.example.vinhthinhgroup.activity.GioHang.EventUltil();
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
