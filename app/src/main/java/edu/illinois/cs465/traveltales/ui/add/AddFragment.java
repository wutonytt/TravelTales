package edu.illinois.cs465.traveltales.ui.add;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import edu.illinois.cs465.traveltales.MainActivity;
import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.RecyclerAdapter;
import edu.illinois.cs465.traveltales.databinding.ActivityMainBinding;
import edu.illinois.cs465.traveltales.databinding.FragmentAddBinding;

public class AddFragment extends Fragment implements RecyclerAdapter.OnCoverPhotoSelectedListener {

    private FragmentAddBinding binding;

    public int totalcount = 0;
    private int coverPhotoId = -1;
    private static final int PICK_IMAGE = 1;
    RecyclerView recyclerView;
    TextView textView;
    TextView coverphototextView;
    Button choose_picture;
    Button choose_picture_rest;
    Button choose_picture_done;
    ArrayList<Uri> uri = new ArrayList<>();
    RecyclerAdapter adapter;
    ActivityResultLauncher<Intent> activityResultLauncher;
    private static final int Read_Permission = 101;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        AddViewModel addViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);

        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        coverphototextView = root.findViewById(R.id.text_choose_cover_photo);
        choose_picture = root.findViewById(R.id.choose_picture);
        choose_picture_done = root.findViewById(R.id.choose_picture_done);
        choose_picture_rest = root.findViewById(R.id.choose_picture_reset);
        adapter = new RecyclerAdapter(uri);
        adapter.setOnCoverPhotoSelectedListener(this);
        recyclerView = root.findViewById(R.id.recyclerview_gallery_image);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        recyclerView.setAdapter(adapter);

        choose_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, Read_Permission);
                }

                Intent intent = new Intent();
                intent.setType("image/*");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                }
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                //activityResultLauncher.launch(intent);

            }
        });

        choose_picture_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(uri.size() == 0){
                    Toast.makeText(getContext(), "Require to choose at least one photo", Toast.LENGTH_LONG).show();
                    return ;
                }

                if(coverPhotoId == -1){
                    Toast.makeText(getContext(), "Require to choose a cover photo", Toast.LENGTH_LONG).show();
                    return ;
                }

                Log.v("ray_log", "Done button pressed");
                Intent intent = new Intent(requireContext(), AddCoverPhoto.class);
                intent.putExtra("selected_images", uri);
                Log.v("ray", "Sending the cover photo id =" + coverPhotoId);
                intent.putExtra("cover_photo_id", coverPhotoId);
                startActivity(intent);
            }
        });


        choose_picture_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalcount = 0;
                uri.clear();
                adapter.notifyDataSetChanged();
                coverphototextView.setText("Please Select Photos");
            }
        });
        return root;
    }

    // Select image activity
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data!=null){
            // Select multiple images
            if(data.getClipData()!= null){
                int count = data.getClipData().getItemCount();
                for(int j=0; j<count; j++){
                    uri.add(data.getClipData().getItemAt(j).getUri());
                }
                totalcount += count;
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Select ("+totalcount+") photos", Toast.LENGTH_LONG).show();
                coverphototextView.setText("Choose One Cover Photo");
            }
            // Select a single image
            else{
                uri.add(data.getData());
            }
        }
        // No image selected
        else{
            Toast.makeText(getContext(), "Select (0) photo", Toast.LENGTH_LONG).show();
        }
    };

    public void onCoverPhotoSelected(int selectedCoverPhotoId) {
        coverPhotoId = selectedCoverPhotoId;
        Log.v("ray", "Current cover photo is " + coverPhotoId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}