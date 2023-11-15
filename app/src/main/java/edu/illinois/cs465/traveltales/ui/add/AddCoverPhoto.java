package edu.illinois.cs465.traveltales.ui.add;

import edu.illinois.cs465.traveltales.ui.profile.ProfileFragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.ActivityMainBinding;
import edu.illinois.cs465.traveltales.ui.profile.ProfileFragment;
import edu.illinois.cs465.traveltales.ui.add.AddFragment;

public class AddCoverPhoto extends AppCompatActivity {

    private ArrayList<Uri> uri = new ArrayList<>();
    private ImageView imageView;
    private ActivityMainBinding binding;
    int coverPhotoId = 0;
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
//        setContentView(R.layout.custom_single_image);
//        uri = (ArrayList<Uri>) getIntent().getSerializableExtra("selected_images");
//        coverPhotoId = getIntent().getIntExtra("cover_photo_id", 0);
//        imageView = findViewById(R.id.image);
//        imageView.setImageURI(uri.get(coverPhotoId));
        setContentView(R.layout.activity_main);

        int coverPhotoId = getIntent().getIntExtra("cover_photo_id", 0);
        ArrayList<Uri> images = (ArrayList<Uri>) getIntent().getSerializableExtra("selected_images");

        ProfileFragment newFragment = ProfileFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putInt("cover_photo_id", coverPhotoId);
        String coverphotouri = images.get(coverPhotoId).toString();
        bundle.putString("cover_photo_uri",coverphotouri);
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment, and add the transaction to the back stack
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_add, R.id.navigation_map, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }
}
