package com.example.vinhthinhgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.model.Loai;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiSPAdapter extends BaseAdapter{
    ArrayList<Loai> arrayListloaisp;
    Context context;

    public LoaiSPAdapter(Context context, ArrayList<Loai> arrayListloaisp) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListloaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txttenloaisp;
        ImageView imgloaisp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LoaiSPAdapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new LoaiSPAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.txttenloaisp = view.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp = view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);
        }else{
            viewHolder = (LoaiSPAdapter.ViewHolder) view.getTag();
        }
        Loai loaiSP = (Loai) getItem(i);
        viewHolder.txttenloaisp.setText(loaiSP.getTenLoai());
        Picasso.with(context).load(loaiSP.getHinh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.imgloaisp);
        return view;
    }
}
