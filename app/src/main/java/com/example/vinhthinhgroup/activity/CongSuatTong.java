package com.example.vinhthinhgroup.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.adapter.CongSuatAdapter;
import com.example.vinhthinhgroup.model.SLPhong;
import com.example.vinhthinhgroup.util.CheckConnection;
import com.example.vinhthinhgroup.util.Server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CongSuatTong extends AppCompatActivity {
    ListView listViewcongsuat;
    TextView textViewthongbao,textViewngaydat;
    EditText editTexttenkh,editTextsdt;
    Button btndathangcs;
    static EditText editTextslpk,editTextslpa,editTextslpn;
    static TextView textViewtongcs,textViewtongcsphongkhach,textViewtongcsphongan,textViewtongcsphongngu,textViewcapchinh,textViewcappk,textViewcappa,textViewcappn;
    Toolbar toolbarcongsuat;
    CongSuatAdapter congSuatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask
        setContentView(R.layout.activity_cong_suat_tong);

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

        //Lấy ngày hiện tại
        DateFormat dateFormatter = new SimpleDateFormat("dd / MM / yyyy");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s = dateFormatter.format(today);
        textViewngaydat.setText(s);
    }

    private void EventButton() {
        btndathangcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //lấy ngày hiện tại gán vào ngày đặt
                DateFormat dateFormatter = new SimpleDateFormat("dd / MM / yyyy");
                dateFormatter.setLenient(false);

                String tt = "Chưa giao";

                final String ten = editTexttenkh.getText().toString().trim();

                final String sdt = editTextsdt.getText().toString().trim();

                Date date = new Date();
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                String mysqlDateString = formatter.format(date);

                final String ngaydat = mysqlDateString;

                final String chitiet = textViewcapchinh.getText().toString().trim()+"\n"+textViewcappk.getText().toString().trim()+"\n"+textViewcappa.getText().toString().trim()+"\n"+textViewcappn.getText().toString().trim();

                final String tinhtrang = tt;

                if(ten.length()>0 && sdt.length()>0 && TrangChu.mangcongsuat.size() > 0){
                    btndathangcs.setVisibility(View.INVISIBLE);
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.DuongDanDonHangCS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                                TrangChu.mangcongsuat.clear();
                                CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn đã đặt hàng thành công !!!");
                                Intent intent = new Intent(getApplicationContext(),TrangChu.class);
                                startActivity(intent);
                                CheckConnection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục !!!");
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String,String>();
                            hashMap.put("TenKH",ten);
                            hashMap.put("SDT",sdt);
                            hashMap.put("NgayDat",ngaydat);
                            hashMap.put("ChiTiet",chitiet);
                            hashMap.put("TinhTrang",tinhtrang);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                    //////////////////////////////////////////
                    String duongdan = Server.DuongDanMail;
                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    requestQueue.add(stringRequest1);
                    //////////////////////////////////////////

                }else{
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Hãy kiểm tra lại dữ liệu");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        listViewcongsuat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CongSuatTong.this);
                builder.setTitle("XÁC NHẬN XÓA");
                builder.setMessage("Bạn có chắc muốn xóa !!!");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(TrangChu.mangcongsuat.size() <=0){
                            textViewthongbao.setVisibility(View.VISIBLE);
                        }
                        else{
                            TrangChu.mangcongsuat.remove(position);
                            congSuatAdapter.notifyDataSetChanged();
                            EventUltil();
                            if(TrangChu.mangcongsuat.size()<=0){
                                textViewthongbao.setVisibility(View.VISIBLE);
                            }
                            else{
                                textViewthongbao.setVisibility(View.INVISIBLE);
                                congSuatAdapter.notifyDataSetChanged();
                                EventUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        congSuatAdapter.notifyDataSetChanged();
                        EventUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void CheckData() {
        if(TrangChu.mangcongsuat.size()<=0){
            congSuatAdapter.notifyDataSetChanged();
            textViewthongbao.setVisibility(View.VISIBLE);
            listViewcongsuat.setVisibility(View.INVISIBLE);
        }else{
            congSuatAdapter.notifyDataSetChanged();
            textViewthongbao.setVisibility(View.INVISIBLE);
            listViewcongsuat.setVisibility(View.VISIBLE);
        }
    }

    public static void EventUltil() {
        SLPhong slPhong = new SLPhong(SLPhong.getSlphongkhack(),SLPhong.getSlphongan(),SLPhong.getSlphongngu());
        int tongcs = 0;
        int tongcspk = 0;
        int tongcspa = 0;
        int tongcspn = 0;
        int slpk = 0;
        int slpa = 0;
        int slpn = 0;
        if(editTextslpk.getText().toString().isEmpty() || Integer.parseInt(editTextslpk.getText().toString()) == 0){
            slPhong.setSlphongkhack(1);
            slpk = slPhong.getSlphongkhack();
        }
        else if(editTextslpa.getText().toString().isEmpty() || Integer.parseInt(editTextslpa.getText().toString()) == 0){
            slPhong.setSlphongan(1);
            slpa = slPhong.getSlphongan();
        }
        else if(editTextslpn.getText().toString().isEmpty() || Integer.parseInt(editTextslpn.getText().toString()) == 0){
            slPhong.setSlphongngu(1);
            slpn = slPhong.getSlphongngu();
        }
        else{
            slPhong.setSlphongkhack(Integer.parseInt(editTextslpk.getText().toString()));
            slpk = slPhong.getSlphongkhack();
            slPhong.setSlphongan(Integer.parseInt(editTextslpa.getText().toString()));
            slpa = slPhong.getSlphongan();
            slPhong.setSlphongngu(Integer.parseInt(editTextslpn.getText().toString()));
            slpn = slPhong.getSlphongngu();
        }
        for(int i = 0; i<TrangChu.mangcongsuat.size();i++){
            if(TrangChu.mangcongsuat.get(i).getMaPhong()==1){
                tongcspk += TrangChu.mangcongsuat.get(i).getCongSuat();
            }
            else if(TrangChu.mangcongsuat.get(i).getMaPhong()==2){
                tongcspa += TrangChu.mangcongsuat.get(i).getCongSuat();
            }
            else{
                tongcspn += TrangChu.mangcongsuat.get(i).getCongSuat();
            }
        }

        tongcs = tongcspk*slpk + tongcspa*slpa + tongcspn*slpn;
        textViewtongcs.setText(tongcs + " W");
        textViewtongcsphongkhach.setText(tongcspk+ " W");
        textViewtongcsphongan.setText(tongcspa+ " W");
        textViewtongcsphongngu.setText(tongcspn+ " W");
        if(tongcs==0){
            textViewcapchinh.setText(" ");
        }
        else if(tongcs<=2000){
            textViewcapchinh.setText("1 cuộn CV 1.5");
        }
        else if(tongcs<=3300){
            textViewcapchinh.setText("1 cuộn CV 2.5");
        }
        else if(tongcs<=5200){
            textViewcapchinh.setText("1 cuộn CV 4.0");
        }
        else if(tongcs<=7800){
            textViewcapchinh.setText("1 cuộn CV 6.0");
        }
        else if(tongcs<=12000){
            textViewcapchinh.setText("1 cuộn CV 10");
        }
        else if(tongcs>12000){
            textViewcapchinh.setText("1 cuộn CV 11");
        }


        if(tongcspk==0){
            textViewcappk.setText(" ");
        }
        else if(tongcspk<=2000){
            textViewcappk.setText(1*slpk + " cuộn CV 1.5");
        }
        else if(tongcspk<=3300){
            textViewcappk.setText(1*slpk + " cuộn CV 2.5 và " + (float)1/2*slpk + " cuộn CV 1.5");
        }
        else if(tongcspk<=5200){
            textViewcappk.setText(1*slpk + " cuộn CV 4.0, 2.5 và " + (float)1/2*slpk + " cuộn CV 1.5");
        }
        else if(tongcspk<=7800){
            textViewcappk.setText(1*slpk + " cuộn CV 6.0, 4.0, 2.5 và " + (float)1/2*slpk + " cuộn CV 1.5");
        }
        else if(tongcspk<=12000){
            textViewcappk.setText(1*slpk + " cuộn CV 10, 6.0, 4.0, 2.5 và " + (float)1/2*slpk + " cuộn CV 1.5");
        }
        else if(tongcspk>12000){
            textViewcappk.setText(1*slpk + " cuộn CV 11, 10, 6.0, 4.0, 2.5 và " + (float)1/2*slpk + " cuộn CV 1.5");
        }


        if(tongcspa==0){
            textViewcappa.setText(" ");
        }
        else if(tongcspa<=2000){
            textViewcappa.setText(1*slpa + " cuộn CV 1.5");
        }
        else if(tongcspa<=3300){
            textViewcappa.setText(1*slpa + " cuộn CV 2.5 và " + (float)1/2*slpa + " cuộn CV 1.5");
        }
        else if(tongcspa<=5200){
            textViewcappa.setText(1*slpa + " cuộn CV 4.0, 2.5 và " + (float)1/2*slpa + " cuộn CV 1.5");
        }
        else if(tongcspa<=7800){
            textViewcappa.setText(1*slpa + " cuộn CV 6.0, 4.0, 2.5 và " + (float)1/2*slpa + " cuộn CV 1.5");
        }
        else if(tongcspa<=12000){
            textViewcappa.setText(1*slpa + " cuộn CV 10, 6.0, 4.0, 2.5 và " + (float)1/2*slpa + " cuộn CV 1.5");
        }
        else if(tongcspa>12000){
            textViewcappa.setText(1*slpa + " cuộn CV 11, 10, 6.0, 4.0, 2.5 và " + (float)1/2*slpa + " cuộn CV 1.5");
        }


        if(tongcspn==0){
            textViewcappn.setText(" ");
        }
        else if(tongcspn<=2000){
            textViewcappn.setText(1*slpn + " cuộn CV 1.5");
        }
        else if(tongcspn<=3300){
            textViewcappn.setText(1*slpn + " cuộn CV 2.5 và " + (float)1/2*slpn + " cuộn CV 1.5");
        }
        else if(tongcspn<=5200){
            textViewcappn.setText(1*slpn + " cuộn CV 4.0, 2.5 và " + (float)1/2*slpn + " cuộn CV 1.5");
        }
        else if(tongcspn<=7800){
            textViewcappn.setText(1*slpn + " cuộn CV 6.0, 4.0, 2.5 và " + (float)1/2*slpn + " cuộn CV 1.5");
        }
        else if(tongcspn<=12000){
            textViewcappn.setText(1*slpn + " cuộn CV 10, 6.0, 4.0, 2.5 và " + (float)1/2*slpn + " cuộn CV 1.5");
        }
        else if(tongcspn>12000){
            textViewcappn.setText(1*slpn + " cuộn CV 11, 10, 6.0, 4.0, 2.5 và " + (float)1/2*slpn + " cuộn CV 1.5");
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarcongsuat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarcongsuat.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        listViewcongsuat = findViewById(R.id.listviewcongsuatcs);
        textViewthongbao = findViewById(R.id.textviewthongbaocs);
        textViewtongcs = findViewById(R.id.textviewgiatritongcs);
        textViewtongcsphongkhach = findViewById(R.id.textviewgiatritongcspk);
        textViewtongcsphongan = findViewById(R.id.textviewgiatritongcspa);
        textViewtongcsphongngu = findViewById(R.id.textviewgiatritongcspn);
        textViewcapchinh = findViewById(R.id.textviewcapchinh);
        textViewcappk = findViewById(R.id.textviewcapphongkhach);
        textViewcappa = findViewById(R.id.textviewcapphongan);
        textViewcappn = findViewById(R.id.textviewcapphongngu);
        textViewngaydat = findViewById(R.id.textviewngaydatcs);
        editTextslpk = findViewById(R.id.edittextslphongkhach);
        editTextslpa = findViewById(R.id.edittextslphongan);
        editTextslpn = findViewById(R.id.edittextslphongngu);
        editTexttenkh = findViewById(R.id.edittexthotencs);
        editTextsdt = findViewById(R.id.edittextsdtcs);
        btndathangcs = findViewById(R.id.btndathangcs);
        toolbarcongsuat = findViewById(R.id.toolbarcongsuatcs);
        congSuatAdapter = new CongSuatAdapter(this,TrangChu.mangcongsuat);
        listViewcongsuat.setAdapter(congSuatAdapter);
    }
}
