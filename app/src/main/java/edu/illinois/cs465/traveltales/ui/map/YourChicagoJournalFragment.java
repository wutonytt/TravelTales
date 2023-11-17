package edu.illinois.cs465.traveltales.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

        final TextView samjournaltext = binding.tonyjournaltext;
        samjournaltext.setText("Sam's Chicago Journal");

        final ImageView profilepicture = binding.profilePicture;
        profilepicture.setImageResource(R.drawable.profilepicture);

        final TextView username = binding.username;
        username.setText("Sam");

        final ImageView image1 = binding.picture1;
        final ImageView image2 = binding.picture2;
        final ImageView image3 = binding.picture3;
        final ImageView image4 = binding.picture4;
        final ImageView image5 = binding.picture5;
        final ImageView image6 = binding.picture6;

        int chicago = R.drawable.chicago;
        int chicago2 = R.drawable.chicago2;
        int chicago3 = R.drawable.chicago3;
        int chicago4 = R.drawable.chicago4;
        int chicago5 = R.drawable.chicago5;
        int chicago6 = R.drawable.chicago6;

        image1.setImageResource(chicago);
        image2.setImageResource(chicago2);
        image3.setImageResource(chicago3);
        image4.setImageResource(chicago4);
        image5.setImageResource(chicago5);
        image6.setImageResource(chicago6);







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
