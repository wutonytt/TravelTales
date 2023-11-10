package edu.illinois.cs465.traveltales.ui.add;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import edu.illinois.cs465.traveltales.GridAdapter;
import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.ClickPhotoBinding;

public class ClickPhotoActivity extends AppCompatActivity {

    ClickPhotoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding = ClickPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO: integrate db here
        int[] image = {R.drawable.eiffel_tower, R.drawable.louvre_museum, R.drawable.arc_de_triomphe};

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
