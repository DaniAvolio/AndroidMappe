package it.itsar.mappedemo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean permissionDenied = false;
    GoogleMap mMap;
    ListView listView;
    LatLng defaultLocation = new LatLng(43.08767655030836, 12.406018762361617);
    private FusedLocationProviderClient fusedLocationProviderClient;
    private boolean locationpermissioneGranted;
    public static int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION =1;
    private Location lastKnownLocation;
    private static final int DEFAULT_ZOOM = 15;




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

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

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
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();


        mMap.moveCamera(CameraUpdateFactory.newLatLng(defaultLocation));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(5));

        for(int i = 0; i< puntidiinteresse.size(); i++){
            mMap.addMarker(new MarkerOptions()
                    .position(puntidiinteresse.get(i).getLatLng())
                    .title(puntidiinteresse.get(i).getDescrizione()));
        }

        listView.setOnItemClickListener((adapterView, view, i, l) -> {

            mMap.moveCamera(CameraUpdateFactory.newLatLng(puntidiinteresse.get(i).getLatLng()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(puntidiinteresse.get(i).getLatLng(), 15));
        });

    }

    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            return;
        }

        // 2. Otherwise, request location permissions from the user.

    }
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }





       /* if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION) || PermissionUtils
                .isPermissionGranted(permissions, grantResults,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Permission was denied. Display an error message
            // Display the missing permission error dialog when the fragments resume.
            permissionDenied = true;
        }

        */



    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (permissionDenied) {
            // Permission was not granted, display error dialog.
         //   showMissingPermissionError();
            permissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     * private void showMissingPermissionError() {
     *
     *         PermissionUtils.PermissionDeniedDialog
     *                 .newInstance(true).show(getSupportFragmentManager(), "dialog");
     *     }
     */

}