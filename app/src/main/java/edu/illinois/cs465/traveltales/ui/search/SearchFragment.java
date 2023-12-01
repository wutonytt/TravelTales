package edu.illinois.cs465.traveltales.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;

import edu.illinois.cs465.traveltales.R;
import edu.illinois.cs465.traveltales.ui.search.design.GridviewAdapter;
import edu.illinois.cs465.traveltales.ui.search.model.ArrayListModel;
import edu.illinois.cs465.traveltales.ui.search.model.PlacesModel;


public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener {

    private GridviewAdapter gridviewAdapter;
    private ArrayList<PlacesModel> originalData;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize original data
        originalData = new ArrayListModel().setListData();

        GridView gridView = root.findViewById(R.id.my_grid_view);
        gridviewAdapter = new GridviewAdapter(requireContext(), originalData);
        gridView.setAdapter(gridviewAdapter);
        gridView.setOnItemClickListener(this);

        // Initialize SearchView
        SearchView searchView = root.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return true;
            }
        });

        return root;

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(requireContext(), AnotherUserPageActivity.class));
    }

    private void filterData(String query) {
        List<PlacesModel> filteredData = new ArrayList<>();

        for (PlacesModel model : originalData) {
            if (model.getLetters().toLowerCase().contains(query.toLowerCase())) {
                filteredData.add(model);
            }
        }

        gridviewAdapter.setData((ArrayList<PlacesModel>) filteredData);
    }


}
