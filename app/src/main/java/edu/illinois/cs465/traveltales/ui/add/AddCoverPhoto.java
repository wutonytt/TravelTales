package edu.illinois.cs465.traveltales.ui.add;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.illinois.cs465.traveltales.R;

public class AddCoverPhoto extends AppCompatActivity {

    private ArrayList<Uri> uri = new ArrayList<>();
    private ImageView imageView;
    int coverPhotoId = 0;
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.custom_single_image);
        uri = (ArrayList<Uri>) getIntent().getSerializableExtra("selected_images");
        coverPhotoId = getIntent().getIntExtra("cover_photo_id", 0);
        imageView = findViewById(R.id.image);
        imageView.setImageURI(uri.get(coverPhotoId));
    }
}
