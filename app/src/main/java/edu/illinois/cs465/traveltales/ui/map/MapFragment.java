package edu.illinois.cs465.traveltales.ui.map;

import android.os.Bundle;
import android.util.Log;
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
//            mapFragment.getMapAsync(map-> {
//                if (googleMap != null) {
//                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
////                    googleMap.setMyLocationEnabled(true);
//                }
//            });
            mapFragment.getMapAsync(this);
            Log.d("supportFragment", "support map fragment loop finished");
        }

//        final TextView textView = binding.textMap;
//        mapViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        Log.d("onCreate", "on create loop finished");
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        Log.d("MapLoop", "enter onMap");
        googleMap = map;
        if (googleMap != null) {
            String apiKey = getString(R.string.google_maps_key);
            Log.d("MapLoop", "In map loop");

            // Set up markers for different locations
//            LatLng location1 = new LatLng(37.7749, -122.4194);
//            LatLng location2 = new LatLng(37.7749, -122.4294);

//
//            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chicago, 10));
//            googleMap.addMarker(new MarkerOptions().position(chicago).title("Chicago"));

//            googleMap.addMarker(new MarkerOptions().position(location1).title("Location 1"));
//            googleMap.addMarker(new MarkerOptions().position(location2).title("Location 2"));

//        googleMap.setOnMarkerClickListener(this);
//            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location1, 12));

            LatLng madrid = new LatLng(40.416775, -3.703790);
            LatLng chicago= new LatLng(41.8781, -87.6298);
            LatLng nyc = new LatLng(40.730610, -73.935242);
            LatLng london = new LatLng(51.509865, -0.118092);

            MarkerOptions madridMarker = new MarkerOptions()
                    .position(madrid)
                    .title("Madrid");

            MarkerOptions chicagoMarker = new MarkerOptions()
                    .position(chicago)
                    .title("Chicago");

            MarkerOptions nycMarker = new MarkerOptions()
                    .position(nyc)
                    .title("NYC");

            MarkerOptions londonMarker = new MarkerOptions()
                    .position(london)
                    .title("London");

            googleMap.addMarker(madridMarker);
            googleMap.addMarker(chicagoMarker);
            googleMap.addMarker(nycMarker);
            googleMap.addMarker(londonMarker);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chicago, 5));
            Log.d("MapFragment", "Marker added to the map");

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