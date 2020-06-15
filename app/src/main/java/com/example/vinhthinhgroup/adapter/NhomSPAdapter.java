package com.example.vinhthinhgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.model.Nhom;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NhomSPAdapter extends BaseAdapter {
    ArrayList<Nhom> arrayListnhomsp;
    Context context;

    public NhomSPAdapter(Context context, ArrayList<Nhom> arrayListnhomsp) {
        this.arrayListnhomsp = arrayListnhomsp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListnhomsp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListnhomsp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txttennhomsp;
        ImageView imgnhomsp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_nhomsp,null);
            viewHolder.txttennhomsp = view.findViewById(R.id.textviewnhomsp);
            viewHolder.imgnhomsp = view.findViewById(R.id.imageviewnhomsp);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Nhom nhomSP = (Nhom) getItem(i);
        viewHolder.txttennhomsp.setText(nhomSP.getTenNhom());
        Picasso.with(context).load(nhomSP.getHinh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.imgnhomsp);
        return view;
    }
}
