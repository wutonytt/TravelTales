package edu.illinois.cs465.traveltales.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
//import edu.illinois.cs465.traveltales.ui.location.LocationActivity;
import edu.illinois.cs465.traveltales.R;

import edu.illinois.cs465.traveltales.databinding.FragmentMapBinding;



public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener{

    private FragmentMapBinding binding;
    private GoogleMap googleMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MapViewModel mapViewModel =
                new ViewModelProvider(this).get(MapViewModel.class);

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            String apiKey = getString(R.string.google_maps_key);
            mapFragment.getMapAsync(googleMap1 -> {
                if (googleMap != null) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                    googleMap.setMyLocationEnabled(true);
                }
            });
        }

        final TextView textView = binding.textMap;
        mapViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        if (googleMap != null) {
            String apiKey = getString(R.string.google_maps_key);

            // Set up markers for different locations
            LatLng location1 = new LatLng(37.7749, -122.4194);
            LatLng location2 = new LatLng(37.7749, -122.4294);

            googleMap.addMarker(new MarkerOptions().position(location1).title("Location 1"));
            googleMap.addMarker(new MarkerOptions().position(location2).title("Location 2"));

//        googleMap.setOnMarkerClickListener(this);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location1, 12));
        }

    }

    @Override
    public void onMapClick(LatLng latLng) {
        return;
    }
//
//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        // Handle marker click, navigate to location page
//        navigateToLocationPage(marker.getTitle());
//        return true;
//    }

//    private void navigateToLocationPage(String locationTitle) {
//        // Start a new activity or fragment for the specific location
//        // Pass any necessary data to the location page
//        // For example, using Intent to start a new activity:
//        Intent locationIntent = new Intent(getActivity(), LocationActivity.class);
//        locationIntent.putExtra("locationTitle", locationTitle);
//        startActivity(locationIntent);
//    }

}