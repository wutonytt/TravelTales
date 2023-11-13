package edu.illinois.cs465.traveltales.ui.add;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.illinois.cs465.traveltales.GridAdapter;
import edu.illinois.cs465.traveltales.databinding.ClickPhotoBinding;

public class ClickPhotoActivity extends AppCompatActivity {

    ClickPhotoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = ClickPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Uri> image = (ArrayList<Uri>) getIntent().getSerializableExtra("selected_images");
        int coverPhotoId = getIntent().getIntExtra("cover_photo_id", 0);

        // TODO: integrate db here
        GridAdapter gridAdapter = new GridAdapter(ClickPhotoActivity.this, image);

        binding.gridViewPhoto.setAdapter(gridAdapter);

        binding.gridViewPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ClickPhotoActivity.this, "You click on " + imageTitle[position], Toast.LENGTH_SHORT).show();

//                setContentView(R.layout.click_photo);
                Intent intent = new Intent(view.getContext(), WriteDescriptionActivity.class);
//                intent.putExtra()
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
