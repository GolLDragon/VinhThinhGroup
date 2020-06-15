package com.example.vinhthinhgroup.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.adapter.GioHangAdapter;
import com.example.vinhthinhgroup.util.CheckConnection;

import java.text.DecimalFormat;

public class GioHang extends AppCompatActivity {
    ListView listViewgiohang;
    TextView textViewthongbao;
    static TextView textViewtongtien;
    Button buttonthanhtoan, buttontieptucmua;
    Toolbar toolbargiohang;
    GioHangAdapter gioHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask
        setContentView(R.layout.activity_gio_hang);
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            AnhXa();
            ActionToolBar();
            CheckData();
            EventUltil();
            CatchOnItemListView();
            EventButton();
        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }

    private void EventButton() {
        buttontieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TrangChu.class);
                startActivity(intent);
            }
        });
        buttonthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TrangChu.manggiohang.size() > 0){
                    Intent intent = new Intent(getApplicationContext(),ThongTinKhachHang.class);
                    startActivity(intent);
                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Giỏ hàng chưa có sản phẩm nào!!!");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        listViewgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHang.this);
                builder.setTitle("XÁC NHẬN XÓA");
                builder.setMessage("Bạn có chắc muốn xóa !!!");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(TrangChu.manggiohang.size() <=0){
                            textViewthongbao.setVisibility(View.VISIBLE);
                        }
                        else{
                            TrangChu.manggiohang.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            EventUltil();
                            if(TrangChu.manggiohang.size()<=0){
                                textViewthongbao.setVisibility(View.VISIBLE);
                            }
                            else{
                                textViewthongbao.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                EventUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gioHangAdapter.notifyDataSetChanged();
                        EventUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUltil() {
        long tongtien = 0;
        for(int i = 0; i<TrangChu.manggiohang.size();i++){
            tongtien += TrangChu.manggiohang.get(i).getGia();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textViewtongtien.setText(decimalFormat.format(tongtien)+ " VNĐ");
    }

    private void CheckData() {
        if(TrangChu.manggiohang.size()<=0){
            gioHangAdapter.notifyDataSetChanged();
            textViewthongbao.setVisibility(View.VISIBLE);
            listViewgiohang.setVisibility(View.INVISIBLE);
        }else{
            gioHangAdapter.notifyDataSetChanged();
            textViewthongbao.setVisibility(View.INVISIBLE);
            listViewgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        listViewgiohang = findViewById(R.id.listviewgiohang);
        textViewthongbao = findViewById(R.id.textviewthongbao);
        textViewtongtien = findViewById(R.id.textviewgiatri);
        buttonthanhtoan = findViewById(R.id.buttonthanhtoan);
        buttontieptucmua = findViewById(R.id.buttonmuahangtiep);
        toolbargiohang = findViewById(R.id.toolbargiohang);
        gioHangAdapter = new GioHangAdapter(this,TrangChu.manggiohang);
        listViewgiohang.setAdapter(gioHangAdapter);
    }
}
