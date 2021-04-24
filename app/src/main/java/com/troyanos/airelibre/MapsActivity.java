package com.troyanos.airelibre;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;

import org.json.JSONException;

import java.io.IOException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng montevideo = new LatLng(-34.8977, -56.1644);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(montevideo, 13));
        mMap.setMinZoomPreference(10f);
        mMap.setMaxZoomPreference(20f);

        GeoJsonLineStringStyle lineStringStyle = new GeoJsonLineStringStyle();
        lineStringStyle.setColor(Color.RED);
        lineStringStyle.setWidth(6);

        GeoJsonLayer plazas = null;
        try {
            plazas = new GeoJsonLayer(mMap, R.raw.plazas, getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        plazas.addLayerToMap();

        GeoJsonLayer bicicircuitos = null;
        try {
            bicicircuitos = new GeoJsonLayer(mMap, R.raw.bicicircuitos, getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bicicircuitos.addLayerToMap();

        plazas.getDefaultPointStyle();
        bicicircuitos.getDefaultLineStringStyle();

        /*
        // Colorear ciclovia según tipo
        for (GeoJsonFeature bicicircuito : bicicircuitos.getFeatures()) {
            if (bicicircuito.hasProperty("TIPO")) {
                String tipo = bicicircuito.getProperty("TIPO");
                if (tipo == "1"){

                }
            }
        }*/

    }
}