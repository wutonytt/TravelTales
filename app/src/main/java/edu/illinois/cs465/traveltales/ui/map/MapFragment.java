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

            mapFragment.getMapAsync(this);
            Log.d("supportFragment", "support map fragment loop finished");
        }

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
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.setPadding(0, 100, 0, 200);

            Log.d("MapLoop", "In map loop");

            // Set up markers for different locations
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

            // create a listener, if you click on marker it opens your Chicago Journal
            // TODO: if you click once, shows the name of the city, if you double click, call "open your Chicago Journal"
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                private long lastClickTime = 0;
                private static final long DOUBLE_CLICK_TIME_DELTA = 3000; // milliseconds

                @Override
                public boolean onMarkerClick(Marker marker) {

                    // handle double clicking logic

//                    System.out.println("CLICKED ON MARKER");
//
//                    long clickTime = System.currentTimeMillis();
//                    if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
//
//
//                        // Handle double click (open your Chicago journal)
//                        openYourChicagoJournal();
//                    }
//                    lastClickTime = clickTime;

                    if (marker.getTitle().equals("Chicago")) {
                        openYourChicagoJournal();
                    }

                    return false;
                }
            });

        }

    }

    private void openYourChicagoJournal() {
        YourChicagoJournalFragment yourChicagoJournalFragment = YourChicagoJournalFragment.newInstance();

        // Replace the current fragment with the detail fragment
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, yourChicagoJournalFragment)
                .commit();

    }

    @Override
    public void onMapClick(LatLng latLng) {
        return;
    }
}