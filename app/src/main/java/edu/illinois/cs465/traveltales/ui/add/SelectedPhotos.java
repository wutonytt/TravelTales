package edu.illinois.cs465.traveltales.ui.add;


import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.illinois.cs465.traveltales.GridAdapter;
import edu.illinois.cs465.traveltales.databinding.SelectedPhotoBinding;

public class SelectedPhotos extends AppCompatActivity {

    SelectedPhotoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = SelectedPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Uri> image = (ArrayList<Uri>) getIntent().getSerializableExtra("selected_images");
        int coverPhotoId = getIntent().getIntExtra("cover_photo_id", 0);

        // TODO: integrate db here
        GridAdapter gridAdapter = new GridAdapter(SelectedPhotos.this, image);

        binding.gridViewPhoto.setAdapter(gridAdapter);

//        binding.gridViewPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(ClickPhotoActivity.this, "You click on " + imageTitle[position], Toast.LENGTH_SHORT).show();
//
////                setContentView(R.layout.click_photo);
//                Intent intent = new Intent(view.getContext(), WriteDescriptionActivity.class);
////                intent.putExtra()
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
