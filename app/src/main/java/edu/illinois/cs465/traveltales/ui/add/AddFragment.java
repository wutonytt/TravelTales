package edu.illinois.cs465.traveltales.ui.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.FragmentAddBinding;

public class AddFragment extends Fragment implements View.OnClickListener {

    private FragmentAddBinding binding;
    Button buttonNext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddViewModel addViewModel =
                new ViewModelProvider(this).get(AddViewModel.class);

        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAdd;
        addViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        buttonNext = (Button) root.findViewById(R.id.button_next);

        buttonNext.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_next) {
            Intent intent = new Intent(v.getContext(), ClickPhotoActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}