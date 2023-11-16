package edu.illinois.cs465.traveltales.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.FragmentAnotherUserPageBinding;
import edu.illinois.cs465.traveltales.databinding.FragmentSearchBinding;

public class AnotherUserPageFragment extends Fragment {
    private FragmentAnotherUserPageBinding binding;

    public static AnotherUserPageFragment newInstance() {
        AnotherUserPageFragment fragment = new AnotherUserPageFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAnotherUserPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button button = binding.seeDescriptionButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked on See Descriptions Button");
                openJournalDescription();
            }
        });

        return root;
    }

    private void openJournalDescription() {
        UserJournalDescriptionFragment userJournalDescriptionFragment = UserJournalDescriptionFragment.newInstance();

        System.out.println("Attempting to Open Journal");
        // Replace the current fragment with the detail fragment
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, userJournalDescriptionFragment)
                .commit();
    }
}
