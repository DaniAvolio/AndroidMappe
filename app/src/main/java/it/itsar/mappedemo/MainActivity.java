package it.itsar.mappedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    ListView listView;

    ArrayList<LatLng> puntidiinteresse = new ArrayList<>(Arrays.asList(
            new LatLng(43.313408494995215, 5.440629846937431),
            new LatLng(45.32497218514314, 8.871504482106992),
            new LatLng(40.902226980344835, 14.23215066541254)
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.mialistview);

        ArrayAdapter<LatLng> arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                puntidiinteresse
                );
        listView.setAdapter(arrayAdapter);




        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(this);

    }

    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        LatLng casaMarsiglia = new LatLng(43.313408494995215, 5.440629846937431);
        mMap.addMarker(new MarkerOptions()
                .position(casaMarsiglia)
                .title("Casa Marsiglia"));

        LatLng casaVigevano = new LatLng(45.32497218514314, 8.871504482106992);
        mMap.addMarker(new MarkerOptions()
                .position(casaVigevano)
                .title("Casa Vigevano"));

        LatLng casaScampia = new LatLng(40.902226980344835, 14.23215066541254);
        mMap.addMarker(new MarkerOptions()
                .position(casaScampia)
                .title("Casa Scampia"));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(casaScampia));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(7));

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(puntidiinteresse.get(i)));

        });

    }


}