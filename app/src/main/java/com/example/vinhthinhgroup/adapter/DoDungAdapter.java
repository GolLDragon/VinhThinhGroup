package com.example.vinhthinhgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.model.DoDung;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DoDungAdapter extends BaseAdapter {
    ArrayList<DoDung> arrayListdodung;
    Context context;

    public DoDungAdapter(Context context, ArrayList<DoDung> arrayListdodung) {
        this.arrayListdodung = arrayListdodung;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListdodung.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListdodung.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txttendodung;
        ImageView imgdodung;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DoDungAdapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new DoDungAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_dodung,null);
            viewHolder.txttendodung = view.findViewById(R.id.textviewdodung);
            viewHolder.imgdodung = view.findViewById(R.id.imageviewdodung);
            view.setTag(viewHolder);
        }else{
            viewHolder = (DoDungAdapter.ViewHolder) view.getTag();
        }
        DoDung doDung = (DoDung) getItem(i);
        viewHolder.txttendodung.setText(doDung.getTenDoDung());
        Picasso.with(context).load(doDung.getHinh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.imgdodung);
        return view;
    }
}
