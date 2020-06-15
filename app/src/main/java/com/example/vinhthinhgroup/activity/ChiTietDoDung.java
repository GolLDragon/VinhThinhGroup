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
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.model.CongSuat;
import com.example.vinhthinhgroup.model.DoDung;
import com.example.vinhthinhgroup.util.CheckConnection;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChiTietDoDung extends AppCompatActivity {

    Toolbar toolbarctdd;
    ImageView imgctdd;
    TextView txttendd,txtcs;
    EditText editsldd;
    Button btnthem;
    int MaDoDung = 0;
    String TenDoDung = "";
    String Hinh = "";
    int CongSuat = 0;
    int MaPhong = 0;

    public static ArrayList<CongSuat> mangcongsuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask
        setContentView(R.layout.activity_chi_tiet_do_dung);
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
        getMenuInflater().inflate(R.menu.menutong, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menucstong:
                Intent intent = new Intent(getApplicationContext(), CongSuatTong.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TrangChu.mangcongsuat.size()>0){
                    int sl = 0;
                    if(editsldd.getText().toString().isEmpty() || Integer.parseInt(editsldd.getText().toString())==0){
                        sl = 1;
                    }
                    else{
                        sl = Integer.parseInt(editsldd.getText().toString());
                    }
                    boolean exist = false;
                    for(int i = 0;i<TrangChu.mangcongsuat.size();i++){
                        if(TrangChu.mangcongsuat.get(i).getMaDoDung() == MaDoDung){
                            TrangChu.mangcongsuat.get(i).setSoluong(TrangChu.mangcongsuat.get(i).getSoluong() + sl);
                            TrangChu.mangcongsuat.get(i).setCongSuat(CongSuat * TrangChu.mangcongsuat.get(i).getSoluong());
                            exist = true;
                        }
                    }

                    if(exist == false){
                        //int soluong = Integer.parseInt(editsldd.getText().toString());
                        int soluong = 0;
                        if(editsldd.getText().toString().isEmpty() || Integer.parseInt(editsldd.getText().toString())==0){
                            soluong = 1;
                        }
                        else{
                            soluong = Integer.parseInt(editsldd.getText().toString());
                        }
                        int Congsuatmoi = soluong * CongSuat;
                        TrangChu.mangcongsuat.add(new CongSuat(MaDoDung, TenDoDung, Hinh, Congsuatmoi, soluong, MaPhong));
                    }
                }else{
                    int soluong = 0;
                    if(editsldd.getText().toString().isEmpty() || Integer.parseInt(editsldd.getText().toString())==0){
                        soluong = 1;
                    }
                    else{
                        soluong = Integer.parseInt(editsldd.getText().toString());
                    }
                    int Congsuatmoi = soluong * CongSuat;
                    TrangChu.mangcongsuat.add(new CongSuat(MaDoDung, TenDoDung, Hinh, Congsuatmoi, soluong, MaPhong));
                }
                Intent intent = new Intent(getApplicationContext(), com.example.vinhthinhgroup.activity.CongSuatTong.class);
                startActivity(intent);
            }
        });
    }

    private void GetInfor() {
        DoDung doDung = (DoDung) getIntent().getSerializableExtra("CTDD");
        MaDoDung = doDung.getMaDoDung();
        TenDoDung = doDung.getTenDoDung();
        Hinh = doDung.getHinh();
        CongSuat = doDung.getCongSuat();
        MaPhong = doDung.getMaPhong();
        txttendd.setText(TenDoDung);
        txtcs.setText("Công suất: "+CongSuat+" W");


        Picasso.with(getApplicationContext()).load(Hinh)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(imgctdd);
    }

    private void AvtionToolbar() {
        setSupportActionBar(toolbarctdd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarctdd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarctdd = findViewById(R.id.toolbarctdodung);
        imgctdd = findViewById(R.id.imageviewctdd);
        txttendd = findViewById(R.id.textviewtenctdd);
        txtcs = findViewById(R.id.textviewcs);
        btnthem = findViewById(R.id.buttonthemdodung);
        editsldd = findViewById(R.id.edittextsldd);
        if (mangcongsuat != null){

        }else{
            mangcongsuat = new ArrayList<>();
        }
    }
}
