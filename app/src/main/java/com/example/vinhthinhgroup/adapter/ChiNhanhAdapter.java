package com.example.vinhthinhgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.model.ChiNhanh;

import java.util.ArrayList;

public class ChiNhanhAdapter extends BaseAdapter {
    ArrayList<ChiNhanh> arrayListchinhanh;
    Context context;

    public ChiNhanhAdapter(Context context, ArrayList<ChiNhanh> arrayListchinhanh) {
        this.arrayListchinhanh = arrayListchinhanh;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListchinhanh.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListchinhanh.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView txtdiachichinhanh;
        TextView txtsdt;
        TextView txtmail;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChiNhanhAdapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ChiNhanhAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_chinhanh,null);
            viewHolder.txtdiachichinhanh = view.findViewById(R.id.textviewdiachichinhanh);
            viewHolder.txtsdt = view.findViewById(R.id.textviewsdtchinhanh);
            viewHolder.txtmail = view.findViewById(R.id.textviewmailchinhanh);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ChiNhanhAdapter.ViewHolder) view.getTag();
        }
        ChiNhanh chiNhanh = (ChiNhanh) getItem(i);
        viewHolder.txtdiachichinhanh.setText("Địa chỉ: "+chiNhanh.getDiaChi());
        viewHolder.txtsdt.setText("SĐT: "+chiNhanh.getSDT());
        viewHolder.txtmail.setText("Email: "+chiNhanh.getMail());
        return view;
    }
}
