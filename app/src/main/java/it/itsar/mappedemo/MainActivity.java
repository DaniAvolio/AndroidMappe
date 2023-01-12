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

    ArrayList<PuntoDiInteresse> puntidiinteresse = new ArrayList<>(Arrays.asList(
            new PuntoDiInteresse("Casa a Napoli", "casa nonni a secondigliano",40.902226980344835 , 14.23215066541254),
            new PuntoDiInteresse("Casa a Marsiglia", "casa genitori a marsiglia", 43.313408494995215, 5.4406298469374310),
            new PuntoDiInteresse("Casa a Vigevano", "casa mia a vigevano",45.32497218514314 ,8.871504482106992),
            new PuntoDiInteresse("Colosseo", "monumento storico romano",41.890385889031506, 12.492230897852393),
            new PuntoDiInteresse("Piazza ducale", "piazza di vigevano",45.31736824362621, 8.858049426808405),
            new PuntoDiInteresse("Stadio Diego Armando maradona", "stadio della SSC Napoli",40.82806630515032, 14.193082538790664),
            new PuntoDiInteresse("Stadio San Siro", "stadio del Milan e Inter",45.478326399796025, 9.12368696891075),
            new PuntoDiInteresse("Stadio juventus stadium", "stadio della juventus",45.10973351878945, 7.641318184245245)

            ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.mialistview);

        ArrayAdapter<PuntoDiInteresse> arrayAdapter = new ArrayAdapter<>(MainActivity.this,
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

        mMap.moveCamera(CameraUpdateFactory.newLatLng(puntidiinteresse.get(0).getLatLng()));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(7));

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            mMap.addMarker(new MarkerOptions()
                    .position(puntidiinteresse.get(i).getLatLng())
                    .title(puntidiinteresse.get(i).getDescrizione()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(puntidiinteresse.get(i).getLatLng()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(puntidiinteresse.get(i).getLatLng(), 15));
        });

    }


}