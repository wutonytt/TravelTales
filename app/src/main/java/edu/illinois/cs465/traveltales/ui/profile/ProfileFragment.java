package edu.illinois.cs465.traveltales.ui.profile;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private int coverPhotoId;
    private String coverPhotopath;
    private ImageView addImageView;

    int journal_count = 4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        // binding object allows you to interact with the views in the layout
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView journalCount = binding.journalCount;
        final GridLayout journalImages = binding.picturesGrid;
        final ImageView flagImage = binding.flagImage;

        // TODO: when there is another post made
            // update journalCount value
            // update latest country traveled
            // update journal Images to have another image of the latest photo

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            coverPhotoId = bundle.getInt("cover_photo_id", 0);
            String coverPhotouri = this.getArguments().getString("cover_photo_uri", "");
            addImageView = root.findViewById(R.id.picture5);
            addImageView.setImageURI(Uri.parse(coverPhotouri));
            journal_count+=1;
            journalCount.setText(String.valueOf(journal_count));
            Log.v("ray", "****  fragment pass to here " + journal_count);
        }

        return root;
    }

    public static ProfileFragment newInstance() {
        final ProfileFragment fragment = new ProfileFragment();
        final Bundle args = new Bundle();
//        args.putString("cover_photo_path", coverPhotopath);
//        args.putInt("cover_photo_id", coverPhotoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}