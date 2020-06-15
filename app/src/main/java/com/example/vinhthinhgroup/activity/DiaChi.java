package com.example.vinhthinhgroup.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.vinhthinhgroup.R;
import com.example.vinhthinhgroup.model.ChiNhanh;
import com.example.vinhthinhgroup.model.GioHang;
import com.example.vinhthinhgroup.util.CheckConnection;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class DiaChi extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar toolbardiachi;
    private GoogleMap mMap;
    Button button;
    TextView txtdiachi,txtsdt,txtmail;
    int MaChiNhanh = 0;
    String DiaChi = "";
    String SDT = "";
    String Mail = "";
    double v1 = 0;
    double v2 = 0;

    public static ArrayList<GioHang> manggiohang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask
        setContentView(R.layout.activity_dia_chi);
        AnhXa();
        ActionBar();
        EventButton();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                    Intent intent = new Intent(DiaChi.this,ChiNhanhKhac.class);
                    startActivity(intent);
                }else{
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                }
            }
        });
    }

    private void AnhXa() {
        toolbardiachi = findViewById(R.id.toolbardiachi);
        button = findViewById(R.id.chinhanh);
        txtdiachi = findViewById(R.id.diachi);
        txtsdt = findViewById(R.id.sdt);
        txtmail = findViewById(R.id.email);
        if (manggiohang != null){

        }else{
            manggiohang = new ArrayList<>();
        }
    }

    private void ActionBar() {
        toolbardiachi = findViewById(R.id.toolbardiachi);
        button = findViewById(R.id.chinhanh);
        setSupportActionBar(toolbardiachi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardiachi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        ChiNhanh chiNhanh = (ChiNhanh) getIntent().getSerializableExtra("MaChiNhanh");
        MaChiNhanh = chiNhanh.getMaChiNhanh();
        DiaChi = chiNhanh.getDiaChi();
        SDT = chiNhanh.getSDT();
        Mail = chiNhanh.getMail();
        v1 = chiNhanh.getV1();
        v2 = chiNhanh.getV2();
        txtdiachi.setText("Địa chỉ: "+DiaChi);
        txtsdt.setText("SĐT: "+SDT);
        txtmail.setText("Email: "+Mail);

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng vinhthinh = new LatLng(v1, v2);
        mMap.addMarker(new MarkerOptions()
                .position(vinhthinh)
                .title("Vĩnh Thịnh")
                .snippet(DiaChi)
                .icon(BitmapDescriptorFactory.defaultMarker()));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(vinhthinh).zoom(90).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
