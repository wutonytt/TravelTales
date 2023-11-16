package edu.illinois.cs465.traveltales.ui.profile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private int coverPhotoId;
    private ImageView addImageView;
    private ImageView addPencil;


    // initial journal counts: set3
    int journal_count = 3;

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
        
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            // get the args
            coverPhotoId = bundle.getInt("cover_photo_id", 0);
            String coverPhotouri = this.getArguments().getString("cover_photo_uri", "");
            // set the args to the fragment
            addImageView = root.findViewById(R.id.picture4);
            addImageView.setImageURI(Uri.parse(coverPhotouri));
            addPencil = root.findViewById(R.id.pencilIcon4);
            addPencil.setImageResource(R.drawable.pencil);
            journal_count+=1;
            journalCount.setText(String.valueOf(journal_count));

            // progress bar - animate from 0 to 100 in 2000 milliseconds
            // after animation completes, progress bar is set back to invisible
            final ProgressBar progressbar = binding.progressBar;
            progressbar.setVisibility(View.VISIBLE);

            ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressbar, "progress", 0, 100);
            progressAnimator.setDuration(2000);
            progressAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressbar.setVisibility(View.INVISIBLE);
                }
            });

            progressAnimator.start();

            // Debug log
            Log.v("ray", "****  fragment pass to here " + journal_count);
        }
        return root;
    }

    public static ProfileFragment newInstance() {
        final ProfileFragment fragment = new ProfileFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}