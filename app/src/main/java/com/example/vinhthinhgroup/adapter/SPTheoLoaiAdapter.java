package com.example.vinhthinhgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.activity.SP;
import com.example.vinhthinhgroup.model.Loai;
import com.example.vinhthinhgroup.model.SPTheoLoai;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SPTheoLoaiAdapter extends BaseAdapter {
    ArrayList<SPTheoLoai> arrayListsptheoloai;
    Context context;

    public SPTheoLoaiAdapter(Context context, ArrayList<SPTheoLoai> arrayListloaisp) {
        this.arrayListsptheoloai = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListsptheoloai.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListsptheoloai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txttensptheoloai;
        ImageView imgsptheoloai;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SPTheoLoaiAdapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new SPTheoLoaiAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_sptheoloai,null);
            viewHolder.txttensptheoloai = view.findViewById(R.id.textviewsptheoloai);
            viewHolder.imgsptheoloai = view.findViewById(R.id.imageviewsptheoloai);
            view.setTag(viewHolder);
        }else{
            viewHolder = (SPTheoLoaiAdapter.ViewHolder) view.getTag();
        }
        SPTheoLoai SPtheoloai = (SPTheoLoai) getItem(i);
        viewHolder.txttensptheoloai.setText(SPtheoloai.getTenSP());
        Picasso.with(context).load(SPtheoloai.getHinh())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(viewHolder.imgsptheoloai);
        return view;
    }
}
