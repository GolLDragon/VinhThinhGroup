package com.example.vinhthinhgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.model.Phong;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhongAdapter extends BaseAdapter{
    ArrayList<Phong> arrayListphong;
    Context context;

    public PhongAdapter(Context context, ArrayList<Phong> arrayListphong) {
        this.arrayListphong = arrayListphong;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListphong.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListphong.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txttenphong;
        ImageView imgphong;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PhongAdapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new PhongAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_phong,null);
            viewHolder.txttenphong = view.findViewById(R.id.textviewphong);
            viewHolder.imgphong = view.findViewById(R.id.imageviewphong);
            view.setTag(viewHolder);
        }else{
            viewHolder = (PhongAdapter.ViewHolder) view.getTag();
        }
        Phong phong = (Phong) getItem(i);
        viewHolder.txttenphong.setText(phong.getTenPhong());
        Picasso.with(context).load(phong.getHinh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.imgphong);
        return view;
    }
}
