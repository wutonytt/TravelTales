package edu.illinois.cs465.traveltales.ui.add;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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

public class AddFragment extends Fragment {

    private FragmentAddBinding binding;

    RecyclerView recyclerView;
    TextView textView;
    Button choose_picture;
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
        textView = root.findViewById(R.id.text_add);
        choose_picture = root.findViewById(R.id.choose_picture);
        adapter = new RecyclerAdapter(uri);
        recyclerView = root.findViewById(R.id.recyclerview_gallery_image);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 4));
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
                //startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                activityResultLauncher.launch(intent);

            }
        });

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if(result.getResultCode() == RESULT_OK && null != result.getData()){
                            int count = result.getData().getClipData().getItemCount();
                            for(int j=0; j<count; j++){
                                uri.add(result.getData().getClipData().getItemAt(j).getUri());
                            }
                            adapter.notifyDataSetChanged();
                            textView.setText("Photos ("+count+")");
                        }
                        else if(result.getData().getData() != null){
                            String imageUrl  = result.getData().getData().getPath();
                            uri.add(Uri.parse(imageUrl));
                        }
                    }
                });
//        Button choose = new Button(getActivity().findViewById(R.id.choose_picture));

        //final Button choosePicture = binding.choosePicture;
        final TextView textView = binding.textAdd;
        addViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}