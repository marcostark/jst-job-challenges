package br.com.marcosouza.justamobile.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import br.com.marcosouza.justamobile.R;
import br.com.marcosouza.justamobile.model.CollectionPoints;
import br.com.marcosouza.justamobile.model.CollectionPointsResponse;
import br.com.marcosouza.justamobile.ui.viewmodels.CollectionPointsViewModel;
import br.com.marcosouza.justamobile.util.Utils;


public class RecyclingMapActivity extends SupportMapFragment implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener{

    private CollectionPointsViewModel collectionPointsViewModel;
    private ArrayList<CollectionPoints> collectionPoints= new ArrayList <> ();
    private GoogleMap mMap;
    private final LatLng SERRATALHADA = new LatLng(-7.981889, -38.289306);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        collectionPointsViewModel =
                ViewModelProviders.of(this).get(CollectionPointsViewModel.class);
        collectionPointsViewModel.init();

        collectionPointsViewModel.getNeighbordhoodsRepository().observe(this,
                new Observer<CollectionPointsResponse>() {
            @Override
            public void onChanged(CollectionPointsResponse neighborhoodsResponse) {
                if(neighborhoodsResponse == null){
                    return;
                }
                if(neighborhoodsResponse.getError() == null){
                    List<CollectionPoints> newsPoints = neighborhoodsResponse.getResults();
                    collectionPoints.addAll(newsPoints);
                    getMapAsync(RecyclingMapActivity.this);
                } else {
                    Throwable e = neighborhoodsResponse.getError();
                    Utils.messageConnectFailed(getActivity(), e);
                }

            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.addMarker(new MarkerOptions().position(SERRATALHADA).title("Serra Talhada - PE"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                SERRATALHADA, 14f));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SERRATALHADA,30));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 1000, null);

        createMarkups(mMap);



    }

    private void createMarkups(GoogleMap googleMap){

        for(CollectionPoints collectionPoints: collectionPoints){

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
