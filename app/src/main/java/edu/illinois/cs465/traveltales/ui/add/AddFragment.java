package edu.illinois.cs465.traveltales.ui.add;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

    private static final int Read_Permission = 101;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        AddViewModel addViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);

        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        Button choose = new Button(getActivity().findViewById(R.id.choose_picture));

        final Button choosePicture = binding.choosePicture;
        //addViewModel.getText().observe(getViewLifecycleOwner(),choosePicture:);
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