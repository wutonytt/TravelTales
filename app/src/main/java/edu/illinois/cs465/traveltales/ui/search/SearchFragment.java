package edu.illinois.cs465.traveltales.ui.search;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final androidx.appcompat.widget.SearchView searchView = binding.searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the query submission if needed

                System.out.println("Submitted Text");
                if (query.equals("Europe") || query.equals("europe")){
                   System.out.println("Typed Europe");

                    // update journal entries
                    // get each of the 6 images
                    final ImageView image1 = binding.picture1;
                    final ImageView image2 = binding.picture2;
                    final ImageView image3 = binding.picture3;
                    final ImageView image4 = binding.picture4;
                    final ImageView image5 = binding.picture5;
                    final ImageView image6 = binding.picture6;

                    // get each of the 6 europe images to replace with
                    int spain = R.drawable.spain;
                    int sweden = R.drawable.sweden;
                    int poland = R.drawable.poland;
                    int switzerland = R.drawable.switzerland;
                    int paris = R.drawable.paris;
                    int amsterdam = R.drawable.amsterdam1;

                    // Set all the images to european images
                    image1.setImageResource(spain);
                    image2.setImageResource(sweden);
                    image3.setImageResource(poland);
                    image4.setImageResource(switzerland);
                    image5.setImageResource(paris);
                    image6.setImageResource(amsterdam);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        final ImageView image6 = binding.picture6;
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click for picture1
                openTonyJournalFragment();
            }
        });

        return root;
    }

    private void openTonyJournalFragment() {
        AnotherUserPageFragment anotherUserPageFragment = AnotherUserPageFragment.newInstance();

        // Replace the current fragment with the detail fragment
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, anotherUserPageFragment)
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}