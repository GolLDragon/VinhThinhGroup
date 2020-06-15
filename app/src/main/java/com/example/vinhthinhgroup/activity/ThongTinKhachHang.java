package com.example.vinhthinhgroup.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.util.CheckConnection;
import com.example.vinhthinhgroup.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHang extends AppCompatActivity {
    TextView textViewngay;
    EditText editTexttenkh, editTextsdt, editTextdiachi;
    Button buttonxacnhan, buttontrove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask
        setContentView(R.layout.activity_thong_tin_khach_hang);

        Anhxa();

        //Lấy ngày hiện tại
        DateFormat dateFormatter = new SimpleDateFormat("dd / MM / yyyy");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s = dateFormatter.format(today);
        textViewngay.setText(s);

        buttontrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void EventButton() {
        buttonxacnhan.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                //lấy ngày hiện tại gán vào ngày đặt
                DateFormat dateFormatter = new SimpleDateFormat("dd / MM / yyyy");
                dateFormatter.setLenient(false);

                String tt = "Chưa giao";

                final String ten = editTexttenkh.getText().toString().trim();

                final String sdt = editTextsdt.getText().toString().trim();

                final String diachi = editTextdiachi.getText().toString().trim();

                Date date = new Date();
                String pattern = "yyyy-MM-dd";
                SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                String mysqlDateString = formatter.format(date);

                final String ngaydat = mysqlDateString;

                final String tinhtrang = tt;

                if(ten.length()>0 && sdt.length()>0 && diachi.length()>0){
                    buttonxacnhan.setVisibility(View.INVISIBLE);
                    buttontrove.setVisibility(View.INVISIBLE);
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.DuongDanDonHang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                            if(Integer.parseInt(madonhang) > 0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.DuongDanCTHoaDon, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")){
                                            TrangChu.manggiohang.clear();
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn đã đặt hàng thành công !!!");
                                            Intent intent = new Intent(getApplicationContext(),TrangChu.class);
                                            startActivity(intent);
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục !!!");
                                        }else{
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Lỗi !!!");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for(int i=0; i<TrangChu.manggiohang.size(); i++){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("MaHD", madonhang);
                                                jsonObject.put("MaSP", TrangChu.manggiohang.get(i).getMaSP());
                                                jsonObject.put("TenSP", TrangChu.manggiohang.get(i).getTenSP());
                                                jsonObject.put("SL", TrangChu.manggiohang.get(i).getSoluong());
                                                jsonObject.put("Gia", TrangChu.manggiohang.get(i).getGia());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap = new HashMap<String,String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String,String>();
                            hashMap.put("HoTenKH",ten);
                            hashMap.put("SDT",sdt);
                            hashMap.put("DiaChi",diachi);
                            hashMap.put("NgayDat",ngaydat);
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

    private void Anhxa() {
        textViewngay = findViewById(R.id.textviewngaydat);
        editTexttenkh = findViewById(R.id.edittexttenkh);
        editTextsdt = findViewById(R.id.edittextsdtkh);
        editTextdiachi = findViewById(R.id.edittextdiachi);
        buttonxacnhan = findViewById(R.id.buttonxacnhan);
        buttontrove = findViewById(R.id.buttontrove);
    }
}
