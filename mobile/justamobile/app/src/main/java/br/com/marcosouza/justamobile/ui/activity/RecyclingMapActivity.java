package br.com.marcosouza.justamobile.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;
import java.util.List;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.model.CollectionPoints;
import br.com.marcosouza.justamobile.util.Utils;


public class RecyclingMapActivity extends SupportMapFragment implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener{

    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        createMarkups(mMap);
    }

    private void createMarkups(GoogleMap googleMap){

        // TODO pegar localização automatica e buscar na api os dados relativos a cidade
        LatLng serraTalhada = new LatLng(-7.982203, -38.289372);
        googleMap.addMarker(new MarkerOptions().position(serraTalhada).title("Serra Talhada - PE"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(serraTalhada));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13.0f));

        for(CollectionPoints collectionPoints: Utils.getMarkers()){

            LatLng coordinate = new LatLng(Double.parseDouble(collectionPoints.getLat()),
                    Double.parseDouble(collectionPoints.getLon()));

            MarkerOptions marker = new MarkerOptions();
            marker.position(coordinate);
            marker.title(collectionPoints.getName());
            marker.snippet(collectionPoints.getMaterials());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.point_papel));
            googleMap.addMarker(marker);
        }
    }

    @Override
    public void onMapClick(LatLng latLng) { }
}
