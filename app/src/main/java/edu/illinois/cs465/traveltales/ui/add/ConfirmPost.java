package edu.illinois.cs465.traveltales.ui.add;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.illinois.cs465.traveltales.Global;
import edu.illinois.cs465.traveltales.MainActivity;
import edu.illinois.cs465.traveltales.R;

public class ConfirmPost extends AppCompatActivity {

    ArrayList<Uri> images;
    int coverPhotoId;
    String title;
    String location;
    String description;
    int visibility = 1; // default

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_post);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        images = (ArrayList<Uri>) getIntent().getSerializableExtra("selected_images");
        coverPhotoId = getIntent().getIntExtra("cover_photo_id", 0);
        title = getIntent().getStringExtra("title");
        location = getIntent().getStringExtra("location");
        description = getIntent().getStringExtra("description");
        visibility = getIntent().getIntExtra("visibility", 1);

        ImageView imageView = findViewById(R.id.confirm_cover_image);
        imageView.setImageURI(images.get(coverPhotoId));

        TextView textViewTitle = findViewById(R.id.confirm_title);
        TextView textViewLocation = findViewById(R.id.confirm_location);
        TextView textViewDescription = findViewById(R.id.confirm_description);

        textViewTitle.setText(title);
        textViewLocation.setText(location);
        textViewDescription.setText(description);

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), SelectedPhotos.class);
            intent.putExtra("selected_images", images);
            Log.v("tony", "Sending the cover photo id =" + coverPhotoId);
            intent.putExtra("cover_photo_id", coverPhotoId);
            startActivity(intent);
        });

        Switch toggleSwitch = findViewById(R.id.toggleSwitch);
        toggleSwitch.setChecked((visibility==0));
        Log.v("ray", String.valueOf(visibility));

        toggleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle toggle switch state change
                if (isChecked) {
                    visibility = 0; // public
                } else {
                    visibility = 1; // private
                }
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_action_bar_done, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_next) {

            System.out.println("VISIBILITY: " + visibility);

            Toast.makeText(this, "Uploading new post...", Toast.LENGTH_SHORT).show();
            // TODO: store input value and return to the previous page with updated identifier

            Log.v("ray", "Setting the global variable");
            ((Global) this.getApplication()).images = images;
            ((Global) this.getApplication()).coverPhotoId = coverPhotoId;
            ((Global) this.getApplication()).title = title;
            ((Global) this.getApplication()).location = location;
            ((Global) this.getApplication()).description = description;
            ((Global) this.getApplication()).journal_count = 4;
            ((Global) this.getApplication()).visibility = visibility;

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("id", 1);
            intent.putExtra("selected_images", images);
            Log.v("tony", "Sending the cover photo id =" + coverPhotoId);
            intent.putExtra("cover_photo_id", coverPhotoId);
            intent.putExtra("title", title);
            intent.putExtra("location", location);
            intent.putExtra("description", description);
            intent.putExtra("visibility", visibility);


            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
