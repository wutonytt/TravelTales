package edu.illinois.cs465.traveltales;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

import edu.illinois.cs465.traveltales.databinding.ActivityMainBinding;
import edu.illinois.cs465.traveltales.ui.add.AddFragment;
import edu.illinois.cs465.traveltales.ui.home.HomeFragment;
import edu.illinois.cs465.traveltales.ui.map.MapFragment;
import edu.illinois.cs465.traveltales.ui.profile.ProfileFragment;
import edu.illinois.cs465.traveltales.ui.search.SearchFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Navbar
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_profile);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
        Log.v("tony", String.valueOf((getIntent())));
        Log.v("ray", "get cat " + getIntent().getCategories());

        Intent intent = getIntent();
        int intent_id = intent.getIntExtra("id", 0);
        if (intent_id == 1) {       // from confirm post
            Log.v("tony", "from clicking confirm post");
            int coverPhotoId = getIntent().getIntExtra("cover_photo_id", 0);
            ArrayList<Uri> images = (ArrayList<Uri>) getIntent().getSerializableExtra("selected_images");

            ProfileFragment newFragment = ProfileFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putInt("cover_photo_id", coverPhotoId);
            String coverphotouri = images.get(coverPhotoId).toString();
            bundle.putString("cover_photo_uri",coverphotouri);


            newFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, newFragment).commit();



        } else if (intent_id == 2) {        // from edit post
            Log.v("tony", "from clicking edit post");

            // get intent extras
            Serializable images = intent.getSerializableExtra("images");
            int coverPhotoId = intent.getIntExtra("coverPhotoId", -1);
            String title = intent.getStringExtra("title");
            String location = intent.getStringExtra("location");
            String description = intent.getStringExtra("description");
            int visibility = intent.getIntExtra("visibility", 1);
            // set extras as bundle for the new fragment
            Bundle bundle = new Bundle();
            bundle.putInt("id", 2);     // edit
            bundle.putSerializable("images", images);
            bundle.putInt("coverPhotoId", coverPhotoId);
            bundle.putString("title", title);
            bundle.putString("location", location);
            bundle.putString("description", description);
            bundle.putInt("visibility", visibility);

            // replace fragment with new AddFragment instance
            AddFragment newFragment = AddFragment.newInstance();
            newFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, newFragment).commit();
            navView.setSelectedItemId(R.id.navigation_add);
        }

        navView.setOnItemSelectedListener(item -> {
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
        });
    }
}