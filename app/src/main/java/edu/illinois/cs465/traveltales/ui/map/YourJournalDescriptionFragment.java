package edu.illinois.cs465.traveltales.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.illinois.cs465.traveltales.databinding.FragmentUserJournalDescriptionBinding;
import edu.illinois.cs465.traveltales.ui.search.UserJournalDescriptionFragment;

public class YourJournalDescriptionFragment extends Fragment {

    private FragmentUserJournalDescriptionBinding binding;

    public static YourJournalDescriptionFragment newInstance() {
        YourJournalDescriptionFragment fragment = new YourJournalDescriptionFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserJournalDescriptionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // TODO:
            // change cover image
            // change description
            // change title

        final TextView description = binding.description;

        description.setText("NEW TEXT");

        return root;
    }
}
