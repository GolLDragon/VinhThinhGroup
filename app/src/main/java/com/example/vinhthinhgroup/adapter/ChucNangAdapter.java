package com.example.vinhthinhgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.model.ChucNang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChucNangAdapter extends BaseAdapter {
    ArrayList<ChucNang> arrayListmenu;
    Context context;

    public ChucNangAdapter( ArrayList<ChucNang> arrayListmenu, Context context) {
        this.arrayListmenu = arrayListmenu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListmenu.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListmenu.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txttenmenu;
        ImageView imgmenu;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChucNangAdapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ChucNangAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_menu,null);
            viewHolder.txttenmenu = view.findViewById(R.id.textviewmenu);
            viewHolder.imgmenu = view.findViewById(R.id.imageviewmenu);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ChucNangAdapter.ViewHolder) view.getTag();
        }
        ChucNang menu = (ChucNang) getItem(i);
        viewHolder.txttenmenu.setText(menu.getTenChucNang());
        Picasso.with(context).load(menu.getHinh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.imgmenu);
        return view;
    }
}
