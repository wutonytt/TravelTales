package edu.illinois.cs465.traveltales.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.FragmentUserJournalDescriptionBinding;

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
        description.setText("Chicago was a whirlwind of urban wonders! From the mesmerizing 'Bean' at Millennium Park to savoring noodles in Chinatown, exploring the Art Institute's artistic treasure trove, and indulging in deep-dish pizza at Lou Malnati's, it was a day filled with vibrant experiences. The city's energy pulsated through the Magnificent Mile and Navy Pier, ending with a mesmerizing skyline against the night sky—a day etched with Chicago's unique charm and bustling spirit.");

        final TextView title = binding.Title;
        title.setText("Chicago Adventures");

        final TextView location = binding.location;
        location.setText("Chicago, United States");

        final ImageView coverImage = binding.coverImage;
        coverImage.setImageResource(R.drawable.chicago2);



        return root;
    }
}
