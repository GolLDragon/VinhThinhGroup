package com.example.vinhthinhgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.model.GoiYThietBi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThietBiAdapter extends BaseAdapter {
    ArrayList<GoiYThietBi> arrayListthietbi;
    Context context;

    public ThietBiAdapter(Context context, ArrayList<GoiYThietBi> arrayListthietbi) {
        this.arrayListthietbi = arrayListthietbi;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListthietbi.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListthietbi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txttenthietbi;
        ImageView imgthietbi;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_thietbi,null);
            viewHolder.txttenthietbi = view.findViewById(R.id.textviewthietbi);
            viewHolder.imgthietbi = view.findViewById(R.id.imageviewthietbi);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        GoiYThietBi thietBi = (GoiYThietBi) getItem(i);
        viewHolder.txttenthietbi.setText(thietBi.getTenThietBi());
        Picasso.with(context).load(thietBi.getHinh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.imgthietbi);
        return view;
    }
}
