package com.example.vinhthinhgroup.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.model.GioHang;
import com.example.vinhthinhgroup.model.SPTheoLoai;
import com.example.vinhthinhgroup.util.CheckConnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietSP extends AppCompatActivity {
    Toolbar toolbarct;
    ImageView imgct;
    TextView txtten,txtgia,txtsl,txttinhnang,txtdvt,txtghichu;
    EditText editsl;
    Button btndatmua;
    int MaSP = 0;
    String TenSP = "";
    String DVT = "";
    int Gia = 0;
    String TinhNang = "";
    int SoLuong = 0;
    String GhiChu = "";
    String Hinh = "";

    public static ArrayList<GioHang> manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask
        setContentView(R.layout.activity_chi_tiet_sp);
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            Anhxa();
            AvtionToolbar();
            GetInfor();
            EventButton();
        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.vinhthinhgroup.activity.GioHang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TrangChu.manggiohang.size()>0){
                    int sl = 0;
                    if(editsl.getText().toString().isEmpty() || Integer.parseInt(editsl.getText().toString())==0){
                        sl = 1;
                    }
                    else{
                        sl = Integer.parseInt(editsl.getText().toString());
                    }
                    boolean exist = false;
                    for(int i = 0;i<TrangChu.manggiohang.size();i++){
                        if(TrangChu.manggiohang.get(i).getMaSP() == MaSP){
                            TrangChu.manggiohang.get(i).setSoluong(TrangChu.manggiohang.get(i).getSoluong() + sl);
                            TrangChu.manggiohang.get(i).setGia(Gia * TrangChu.manggiohang.get(i).getSoluong());
                            exist = true;
                        }
                    }

                    if(exist == false){
                        int soluong = 0;
                        if(editsl.getText().toString().isEmpty() || Integer.parseInt(editsl.getText().toString())==0){
                            soluong = 1;
                        }
                        else{
                            soluong = Integer.parseInt(editsl.getText().toString());
                        }
                        long Giamoi = soluong * Gia;
                        TrangChu.manggiohang.add(new GioHang(MaSP, TenSP, Giamoi, Hinh, soluong));
                    }
                }else{
                    int soluong = 0;
                    if(editsl.getText().toString().isEmpty() || Integer.parseInt(editsl.getText().toString())==0){
                        soluong = 1;
                    }
                    else{
                        soluong = Integer.parseInt(editsl.getText().toString());
                    }
                    long Giamoi = soluong * Gia;
                    TrangChu.manggiohang.add(new GioHang(MaSP, TenSP, Giamoi, Hinh, soluong));
                }
                Intent intent = new Intent(getApplicationContext(), com.example.vinhthinhgroup.activity.GioHang.class);
                startActivity(intent);
            }
        });
    }

    private void GetInfor() {
        SPTheoLoai sanPham = (SPTheoLoai) getIntent().getSerializableExtra("CTSP");
        MaSP = sanPham.getMaSP();
        TenSP = sanPham.getTenSP();
        DVT = sanPham.getDVT();
        Gia = sanPham.getGia();
        TinhNang = sanPham.getTinhNang();
        SoLuong = sanPham.getSoLuong();
        GhiChu = sanPham.getGhiChu();
        Hinh = sanPham.getHinh();
        txtten.setText(TenSP);
        txtdvt.setText("DVT: "+DVT);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá: " + decimalFormat.format(Gia) + " VNĐ");
        txtsl.setText("Số lượng còn: "+SoLuong);
        if(sanPham.getTinhNang().toString().equals("null"))
            txttinhnang.setText(" ");
        else
            txttinhnang.setText(TinhNang);
        if(sanPham.getGhiChu().toString().equals("null"))
            txttinhnang.setText(" ");
        else
            txtghichu.setText(GhiChu);
        Picasso.with(getApplicationContext()).load(Hinh)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imgct);
        if(sanPham.getSoLuong() == 0){
            btndatmua.setVisibility(View.INVISIBLE);
            editsl.setVisibility(View.INVISIBLE);
        }
        else{
            btndatmua.setVisibility(View.VISIBLE);
            editsl.setVisibility(View.VISIBLE);
        }
    }

    private void AvtionToolbar() {
        setSupportActionBar(toolbarct);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarct = findViewById(R.id.toolbarctsp);
        imgct = findViewById(R.id.imageviewctsp);
        txtten = findViewById(R.id.textviewtenctsp);
        txtgia = findViewById(R.id.textviewgiactsp);
        txttinhnang = findViewById(R.id.textviewtinhnang);
        txtsl = findViewById(R.id.textviewsl);
        txtdvt = findViewById(R.id.textviewdvt);
        txtghichu = findViewById(R.id.textviewghichu);
        btndatmua = findViewById(R.id.buttondatmua);
        editsl = findViewById(R.id.edittextsl);
        if (manggiohang != null){

        }else{
            manggiohang = new ArrayList<>();
        }
    }
}
