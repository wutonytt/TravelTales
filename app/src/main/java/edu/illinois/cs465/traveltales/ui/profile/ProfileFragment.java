package edu.illinois.cs465.traveltales.ui.profile;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import edu.illinois.cs465.traveltales.Global;
import edu.illinois.cs465.traveltales.MainActivity;
import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    int coverPhotoId = -1;
    ImageView addImageView;
    ImageView addPencil;


    // initial journal counts: set3
    int journal_count = 3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // binding object allows you to interact with the views in the layout
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView journalCount = binding.journalCount;

        coverPhotoId = ((Global) this.getActivity().getApplication()).coverPhotoId;
        if(coverPhotoId != -1){
            Log.v("ray", "shared data = " + ((Global) this.getActivity().getApplication()).images );
            Uri coverPhotouri = ((Global) this.getActivity().getApplication()).images.get(coverPhotoId);
            addImageView = root.findViewById(R.id.picture4);
            addImageView.setImageURI(coverPhotouri);
            addPencil = root.findViewById(R.id.pencilIcon4);
            addPencil.setImageResource(R.drawable.ic_edit_white_24dp);
            addPencil.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("id", 2);
                Global global = (Global) getActivity().getApplication();
                System.out.println(global);
                intent.putExtra("images", global.images);
                intent.putExtra("coverPhotoId", global.coverPhotoId);
                intent.putExtra("title", global.title);
                intent.putExtra("location", global.location);
                intent.putExtra("description", global.description);
                startActivity(intent);
            });
            journal_count += 1;
            journalCount.setText(String.valueOf(journal_count));
        }

        Bundle bundle = this.getArguments();
        if (bundle != null) {
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