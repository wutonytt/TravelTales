package edu.illinois.cs465.traveltales;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.illinois.cs465.traveltales.databinding.ActivityMainBinding;
import edu.illinois.cs465.traveltales.ui.add.AddFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    RecyclerView recyclerView;
    TextView textView;
    Button choose_picture;
    ArrayList<Uri> uri = new ArrayList<>();
    RecyclerAdapter adapter;

    private static final int Read_Permission = 101;

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Navbar
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_add, R.id.navigation_map, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Recycler View
//        setContentView(R.layout.fragment_add);
//        textView = findViewById(R.id.text_add);
//        choose_picture = findViewById(R.id.choose_picture);
//        adapter = new RecyclerAdapter(uri);
//        recyclerView = findViewById(R.id.recyclerview_gallery_image);
//        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
//        recyclerView.setAdapter(adapter);
//
//        choose_picture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, Read_Permission);
//                }
//
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                }
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                //startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
//                activityResultLauncher.launch(intent);
//
//            }
//        });
//
//        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//
//                        if(result.getResultCode() == RESULT_OK && null != result.getData()){
//                            int count = result.getData().getClipData().getItemCount();
//                            for(int j=0; j<count; j++){
//                                uri.add(result.getData().getClipData().getItemAt(j).getUri());
//                            }
//                            adapter.notifyDataSetChanged();
//                            textView.setText("Photos ("+count+")");
//                        }
//                        else if(result.getData().getData() != null){
//                            String imageUrl  = result.getData().getData().getPath();
//                            uri.add(Uri.parse(imageUrl));
//                        }
//                    }
//                });
    }
}