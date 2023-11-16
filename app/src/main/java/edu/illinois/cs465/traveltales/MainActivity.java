package edu.illinois.cs465.traveltales;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.illinois.cs465.traveltales.databinding.ActivityMainBinding;
import edu.illinois.cs465.traveltales.ui.add.AddFragment;
import edu.illinois.cs465.traveltales.ui.home.HomeFragment;
import edu.illinois.cs465.traveltales.ui.map.MapFragment;
import edu.illinois.cs465.traveltales.ui.profile.ProfileFragment;
import edu.illinois.cs465.traveltales.ui.search.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Navbar
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_profile);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();

        if(!String.valueOf(getIntent().getCategories()).equals("{android.intent.category.LAUNCHER}")){
            Log.v("ray", "get cat LAUNCHER " + String.valueOf(getIntent().getCategories()));

            int coverPhotoId = getIntent().getIntExtra("cover_photo_id", 0);
            ArrayList<Uri> images = (ArrayList<Uri>) getIntent().getSerializableExtra("selected_images");

            ProfileFragment newFragment = ProfileFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putInt("cover_photo_id", coverPhotoId);
            String coverphotouri = images.get(coverPhotoId).toString();
            bundle.putString("cover_photo_uri",coverphotouri);

            newFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, newFragment).commit();
        }

        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.v("ray", "nav bar clicked");
                if(item.getItemId() == R.id.navigation_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
                    return true;
                }
                else if(item.getItemId() == R.id.navigation_search){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();
                    return true;
                }
                else if(item.getItemId() ==  R.id.navigation_add){
                    Log.v("ray", "R.id = navigation_add");
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new AddFragment()).commit();
                    return true;
                }
                else if(item.getItemId() == R.id.navigation_map){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapFragment()).commit();
                    return true;
                }
                else if(item.getItemId() == R.id.navigation_profile){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
                    return true;
                }
                return false;
            }
        });
    }
}