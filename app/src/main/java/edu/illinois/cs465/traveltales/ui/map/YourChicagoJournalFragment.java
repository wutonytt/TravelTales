package edu.illinois.cs465.traveltales.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.FragmentAnotherUserPageBinding;
import edu.illinois.cs465.traveltales.ui.search.UserJournalDescriptionFragment;

public class YourChicagoJournalFragment extends Fragment {

    private FragmentAnotherUserPageBinding binding;

    public static YourChicagoJournalFragment newInstance() {
        YourChicagoJournalFragment fragment = new YourChicagoJournalFragment();
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAnotherUserPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button button = binding.seeDescriptionButton;

        // TODO:
        // get all the variables from binding
        // change from "Tony's Amsterdam Journal" to "Your Chicago Journal"
        // change all picture to Chicago Pictures
        // change profile picture to Sam's profile picture (named "profilepicture")
        // change username to Sam
        // make any other necessary updates
        // remove any commented out code

        final ImageView flagImage = binding.flag;
        // example:
        int usaflag = R.drawable.usaflag;

        // Set all the images to chicago images (find more online)
        // refer to Search Fragment for help
        flagImage.setImageResource(usaflag);

        // if you click on the button it brings you to the descriptions
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
        YourJournalDescriptionFragment yourJournalDescriptionFragment = YourJournalDescriptionFragment.newInstance();

        // Replace the current fragment with the detail fragment
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, yourJournalDescriptionFragment)
                .commit();
    }
}
