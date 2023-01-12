package it.itsar.mappedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
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
    TextView casanapoli, casamarsiglia, casavigevano;
    MaterialCardView cardnapoli, cardmarsiglia, cardvigevano;

    ArrayList<LatLng> puntidiinteresse = new ArrayList<>(Arrays.asList(
            new LatLng(43.313408494995215, 5.440629846937431),
            new LatLng(45.32497218514314, 8.871504482106992),
            new LatLng(40.902226980344835, 14.23215066541254)
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casanapoli = findViewById(R.id.casanapoli);
        casamarsiglia = findViewById(R.id.casamarsiglia);
        casavigevano = findViewById(R.id.casavigevano);
        cardnapoli = findViewById(R.id.cardnapoli);
        cardvigevano = findViewById(R.id.cardvigevano);
        cardmarsiglia = findViewById(R.id.cardmarsiglia);


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

        cardmarsiglia.setOnClickListener(view -> {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(casaMarsiglia));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        });
        cardvigevano.setOnClickListener(view -> {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(casaVigevano));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        });
        cardnapoli.setOnClickListener(view -> {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(casaScampia));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        });

    }


}