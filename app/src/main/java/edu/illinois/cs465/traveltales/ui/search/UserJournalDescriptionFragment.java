package edu.illinois.cs465.traveltales.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.illinois.cs465.traveltales.databinding.FragmentAnotherUserPageBinding;
import edu.illinois.cs465.traveltales.databinding.FragmentUserJournalDescriptionBinding;

public class UserJournalDescriptionFragment extends Fragment {
    private FragmentUserJournalDescriptionBinding binding;

    public static UserJournalDescriptionFragment newInstance() {
        UserJournalDescriptionFragment fragment = new UserJournalDescriptionFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserJournalDescriptionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}
