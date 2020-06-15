package com.example.vinhthinhgroup.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.adapter.DoDungAdapter;
import com.example.vinhthinhgroup.model.CongSuat;
import com.example.vinhthinhgroup.model.DoDung;
import com.example.vinhthinhgroup.util.CheckConnection;
import com.example.vinhthinhgroup.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChonDoDung extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewdodung;
    DoDungAdapter doDungAdapter;
    ArrayList<DoDung> mangdodung;
    int MaDoDung = 0;
    int page = 1;
    View footerview;
    boolean isLoading = false;
    mHandler mHandler;
    boolean limitdata = false;

    public static ArrayList<CongSuat> mangcongsuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask
        setContentView(R.layout.activity_chon_do_dung);
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            Anhxa();
            GetMaDoDung();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }

    private void GetMaDoDung() {
        MaDoDung = getIntent().getIntExtra("MaPhong",-1);
        Log.d("giatri",MaDoDung+"");
    }

    private void LoadMoreData() {
        listViewdodung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChiTietDoDung.class);
                intent.putExtra("CTDD",mangdodung.get(i));
                startActivity(intent);
            }
        });
        listViewdodung.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FristItem, int VisibleItem, int TotalItem) {
                if(FristItem + VisibleItem == TotalItem && TotalItem != 0 && isLoading == false && limitdata == false){
                    isLoading = true;
                    Thread threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.DuongDanDoDung+String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int MaDoDung = 0;
                String TenDoDung = "";
                String Hinh = "";
                int CongSuat = 0;
                int MaPhong = 0;
                if(response != null && response.length() != 2){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            MaDoDung = jsonObject.getInt("MaDoDung");
                            TenDoDung = jsonObject.getString("TenDoDung");
                            Hinh = jsonObject.getString("Hinh");
                            CongSuat = jsonObject.getInt("CongSuat");
                            MaPhong = jsonObject.getInt("MaPhong");
                            mangdodung.add(new DoDung(MaDoDung,TenDoDung,Hinh,CongSuat,MaPhong));
                            doDungAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    limitdata = true;
                    listViewdodung.removeFooterView(footerview);
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("MaPhong",String.valueOf(MaDoDung));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutong, menu);
        return true;
    }

    //Dua vao mang cong suat tong
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menucstong:
                Intent intent = new Intent(getApplicationContext(), CongSuatTong.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbardodung);
        listViewdodung = findViewById(R.id.listviewdodung);
        mangdodung = new ArrayList<>();
        doDungAdapter = new DoDungAdapter(getApplicationContext(),mangdodung);
        listViewdodung.setAdapter(doDungAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        mHandler = new mHandler();
        if (mangcongsuat != null){

        }else{
            mangcongsuat = new ArrayList<>();
        }
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    listViewdodung.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
